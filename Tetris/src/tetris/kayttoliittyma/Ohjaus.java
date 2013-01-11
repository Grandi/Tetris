
package tetris.kayttoliittyma;

import java.util.Random;
import tetris.sovelluslogiikka.muutos.PelitilanteenPaivittaja;
import tetris.sovelluslogiikka.pelimekaniikka.Pelitilanne;
import tetris.sovelluslogiikka.pelimekaniikka.PelitilanteenPiirtaja;
import tetris.sovelluslogiikka.pelimekaniikka.TetriminonAsettelija;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.pelialue.PelialueenRakentaja;
import tetris.sovelluslogiikka.pelimekaniikka.Aavetetrimino;
import tetris.sovelluslogiikka.pelimekaniikka.Asetukset;
import tetris.sovelluslogiikka.pelimekaniikka.Komento;
import tetris.sovelluslogiikka.sekalaiset.Ajastin;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Suunta;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Vari;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.tetrimino.TetriminonRakentaja;

/** Luokka, joka yhdistää sovelluslogiikan komponentit Tetris-peliksi. 
 * @author grandi
 */
public class Ohjaus implements Runnable
{
    /** Piirtää pelitilanteen. */
    private PelitilanteenPiirtaja pelitilanteenPiirtaja;
    
    /** Päivittää pelitilanteen. */
    private PelitilanteenPaivittaja pelialueenPaivittaja;
    
    /** Pitää huolta aavetetriminosta. */
    private Aavetetrimino aavetetrimino;
    
    /** Tetrimino, jota pelissä ohjataan. */
    private Tetrimino tetrimino;
    
    /** Pelialue, jolla pelataan. */
    private Pelialue pelialue;
    
    /** Asetukset, joiden mukaan toimitaan. */
    private Asetukset asetukset;
    
    /** Pelitilanne, joka kirjaa pisteet ja vastaavat. */
    private Pelitilanne pelitilanne;
    
    /** Lazy evaluation -ratkaisu. Komennot välitetään näiden kautta. */
    private Komento kayttajanKomento;
    
    /**
     * @param asetukset Asetus-olio, jonka mukaan peli rakennetaan.
     */
    public Ohjaus(Asetukset asetukset)
    {
        this.kayttajanKomento = Komento.EI_KOMENTOA;
        this.asetukset = asetukset;
        this.pelitilanne = new Pelitilanne();
        
        this.pelialue = new Pelialue(new Sijainti(0, 0), 10, 20);
        rakennaPelialue();
        uusiTetrimino();
        
        this.pelialueenPaivittaja = new PelitilanteenPaivittaja(pelialue, pelitilanne);
        this.aavetetrimino = new Aavetetrimino(tetrimino, pelialue);
        
        pelitilanne.aseta(Pelitilanne.Tunniste.VAIKEUSTASO, asetukset.aloitusvaikeustaso());
    }
    
    /** Käsittelee käyttäjän antaman komennon.
     */
    public void kasitteleKayttajanKomento()
    {
        switch(kayttajanKomento)
        {
            case SIIRTO_VASEMMALLE: siirraTetriminoa(Suunta.VASEN); break;
            case SIIRTO_OIKEALLE:   siirraTetriminoa(Suunta.OIKEA); break;
            case KAANTAMINEN:       kaannaTetriminoa(Suunta.OIKEA); break;
            case PUDOTTAMINEN:      pudotaTetrimino(); break;
            case TAUOTTAMINEN:
                this.pelitilanteenPiirtaja.paivitaTilanne();
                if(pelitilanne.onTauolla())
                    pelitilanne.jatkaPelia();
                else
                    pelitilanne.tauota();
                break;
                
            case UUSI_PELI: alustaUusiPeli(); break;
        }
        
        kayttajanKomento = Komento.EI_KOMENTOA;
    }
    
    /** Alustaa uuden pelin.
     */
    private void alustaUusiPeli()
    {
        pelitilanne.alusta();
        pelialueenPaivittaja.tyhjennaMuutokset();
        pelialue.tyhjenna();
        
        rakennaPelialue();
        uusiTetrimino();
        
        aavetetrimino.paivita();
        paivitaTilanne();
        
        pelitilanne.aseta(Pelitilanne.Tunniste.VAIKEUSTASO, asetukset.aloitusvaikeustaso());
        uusiTetrimino();
        paivitaTilanne();
    }
    
    /** Antaa pelille komennon.
     * @param komento Komento, joka pelille tahdotaan antaa.
     */
    public void annaKomento(Komento komento)
    {
        kayttajanKomento = komento;
    }
    
    /** Palauttaa nykyisen pelitilanteen. */
    public Pelitilanne pelitilanne()
    {
        return pelitilanne;
    }
    
    /** Asettaa sen pelitilanteen piirtäjän, jolla pelitilanne tahdotaan piirtää.
     * @param pelitilanteenPiirtaja Tahdottu pelitilanteen piirtäjä.
     */
    public void asetaPelitilanteenPiirtaja(PelitilanteenPiirtaja pelitilanteenPiirtaja)
    {
        this.pelitilanteenPiirtaja = pelitilanteenPiirtaja;
        
        pelitilanteenPiirtaja.lisaaSeurattavaPalikkakokoelma(pelialue);
        pelitilanteenPiirtaja.lisaaSeurattavaPalikkakokoelma(tetrimino.palikkakokoelma());
        pelitilanteenPiirtaja.lisaaSeurattavaPalikkakokoelma(aavetetrimino.tetrimino().palikkakokoelma());
    }
    
    /** Kertoo, onko peli käynnissä. 
     * @return True, jos peli on käynnissä, muutoin false.
     */
    public boolean peliOnKaynnissa()
    {
        return !pelitilanne.peliOnPaattynyt();
    }
    
    /** Rakentaa uuden pelialueen.
     */
    private void rakennaPelialue()
    {
        PelialueenRakentaja rakentaja = new PelialueenRakentaja(pelialue);
        
        if(asetukset.esitaytettavatRivit() > 0)
            rakentaja.esitaytaRivit(asetukset.esitaytettavatRivit());

        rakentaja.varita(asetukset.varipaletti());
    }
    
    /** Luo uuden tetriminon ruudun yläosaan.
     */
    private void uusiTetrimino()
    {
        if(tetrimino != null)
            tetrimino.alusta();
        else
            tetrimino = new Tetrimino();
        
        if(pelitilanne.peliOnPaattynyt())
            return;

        rakennaTetrimino();
        alustaTetriminonSijainti();
    }
    
    /** Rakentaa uuden tetriminon pelaajalle.
     */
    private void rakennaTetrimino()
    {
        Sijainti sijainti = new Sijainti(pelialue.alue().alkupiste().x() + pelialue.alue().leveys()/2, 0);
        TetriminonRakentaja rakentaja = new TetriminonRakentaja(tetrimino, new Random());
        
        rakentaja.luoTyypillinenTetrisPalikka(sijainti, asetukset.palikoidenMaaraTetriminossa());
        rakentaja.varita(asetukset.varipaletti());
    }
    
    /** Alustaa uuden tetriminon sijainnin.
     */
    private void alustaTetriminonSijainti()
    {
        while(asettelija().kokeileSiirtaa(Suunta.YLOS));
        
        if(!asettelija().yritaPoistuaTormaamasta(Suunta.ALAS))
            paataPeli();
    }
    
    /**
     * @return Uusi TetriminonAsettelija-olio tetriminolle ja pelialueelle.
     */
    private TetriminonAsettelija asettelija()
    {
        return new TetriminonAsettelija(tetrimino, pelialue);
    }
    
    /** Välittää käyttäjän tahdon siirtää tetriminoa.
     * @param suunta Suunta, johon tetriminoa tahdotaan siirtää.
     */
    private void siirraTetriminoa(Suunta suunta)
    {
        if(suunta == Suunta.YLOS || !pelitilanne.voiLiikuttaa())
            return;
        
        asettelija().kokeileSiirtaa(suunta);        
        paivitaTilanne();
    }
    
    /** Välittää käyttäjän tahdon kääntää tetriminoa.
     * @param suunta Suunta, johon tetriminoa tahdotaan kääntää.
     */
    private void kaannaTetriminoa(Suunta suunta)
    {
        if(suunta == Suunta.YLOS || suunta == Suunta.ALAS || !pelitilanne.voiLiikuttaa())
            return;

        asettelija().kokeileKaantaa(suunta);
        paivitaTilanne();
    }
    
    /** Välittää käyttäjän tahdon pudottaa tetrimino.
     */
    private void pudotaTetrimino()
    {
        if(!pelitilanne.voiLiikuttaa())
            return;

        asettelija().pudotaAlas();
        
        pelitilanne.tiputusAjastin().paivita();
        paivitaTilanne();
    }
    
    /** Kertoo, onko tetrimino pelialueen yläosassa.
     * @return True, jos on. Muutoin false.
     */
    private boolean tetriminoOnPelialueenYlaosassa()
    {
        for(Palikka palikka : tetrimino.palikkakokoelma().palikat())
            if(palikka.sijainti().y() == pelialue.alue().alkupiste().y())
                return true;
        
        return false;
    }
    
    /** Tiputtaa tetriminoa ja samalla huolehtii mahdollisesta pelin päättymisestä.
     */
    private void tiputaTetriminoa()
    {
        if(!asettelija().kokeileSiirtaa(Suunta.ALAS))
            if(tetriminoOnPelialueenYlaosassa())
                paataPeli();
            else
            {
                new PelialueenRakentaja(pelialue).tungePalikat(tetrimino);
                uusiTetrimino();
            }
        
        pelitilanne.tiputusAjastin().paivita();
        paivitaTilanne();
    }
    
    /** Päivittää tetriminon tippumisen säännöllisin väliajoin.
     */
    private void paivitaTetriminonTippuminen()
    {
        if(pelitilanne.onTauolla() || pelitilanne.peliOnPaattynyt())
            return;
        
        int vauhti = pelitilanne.arvo(Pelitilanne.Tunniste.VAIKEUSTASO) * 50;
        if(vauhti >= 500)
            vauhti = 450;

        if(pelitilanne.tiputusAjastin().onKulunut(500 - vauhti ))
            tiputaTetriminoa();
    }

    /** Päivittää pelialueen piirtämisen ja aavetetriminon.
     */
    private void paivitaTilanne()
    {
        if(pelialueenPaivittaja.onValmis())
            aavetetrimino.paivita();
        
        pelialueenPaivittaja.paivita();
        
        if(pelitilanteenPiirtaja != null)
            this.pelitilanteenPiirtaja.paivitaTilanne();
    }
    
    /** Häivyttää palikat. On osa haivytaPalikat()-metodia.
     * @param ajastin Ajastin, joka päivitetään.
     */
    private void haivyta(Ajastin ajastin)
    {
        new PelialueenRakentaja(pelialue).himmenna(1);
        paivitaTilanne();
        ajastin.paivita();
    }
    
    /** Häivyttää palikat, tätä käytetään lopetusanimaationa.
     */
    private void haivytaPalikat()
    {
        Ajastin ajastin = new Ajastin();
        while(pelialue.lisattyja() > 0)
        {
            Vari vari = ((TetrisPalikka)pelialue.palikat().get(0)).vari();
            if(vari.peittavyys() <= 20)
                break;

            if(ajastin.onKulunut(1))
                haivyta(ajastin);
        }
    }
    
    /** Päättää pelin.
     */
    private void paataPeli()
    {
        haivytaPalikat();
        pelitilanne.paataPeli();
    }
    
    /** Palauttaa pelissä käytetyt asetukset.
     * @return Pelin Asetukset-olio.
     */
    public Asetukset asetukset()
    {
        return asetukset;
    }

    /** Pyörittää pelin pääsilmukkaa.
     */
    @Override public void run()
    {
        while(true)
        {
            if(kayttajanKomento != Komento.EI_KOMENTOA)
                kasitteleKayttajanKomento();
            
            if(!peliOnKaynnissa())
                this.pelitilanteenPiirtaja.paivitaTilanne();
            else
            {
                paivitaTetriminonTippuminen();

                if(!pelialueenPaivittaja.onValmis())
                    paivitaTilanne();
            }
        }
    }
}
