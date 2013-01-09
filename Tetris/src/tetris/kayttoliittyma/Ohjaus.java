
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
public class Ohjaus
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
    

    public Ohjaus(Asetukset asetukset)
    {
        this.asetukset = asetukset;
        this.pelitilanne = new Pelitilanne();
        
        this.pelialue = new Pelialue(new Sijainti(0, 0), 10, 20);
        rakennaPelialue();
        uusiTetrimino();
        
        this.pelialueenPaivittaja = new PelitilanteenPaivittaja(pelialue, pelitilanne);
        this.aavetetrimino = new Aavetetrimino(tetrimino, pelialue);
    }
    
    /** Alustaa uuden pelin.
     */
    public void alustaUusiPeli()
    {
        pelitilanne.alusta();
        pelialueenPaivittaja.tyhjennaMuutokset();
        pelialue.tyhjenna();
        
        rakennaPelialue();
        uusiTetrimino();
        
        aavetetrimino.paivita();
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
        
        pelitilanteenPiirtaja.lisaaSeurattavaPalikkakokoelma(tetrimino.palikkakokoelma());
        pelitilanteenPiirtaja.lisaaSeurattavaPalikkakokoelma(pelialue);
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

        rakentaja.varita();
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

        Sijainti sijainti = new Sijainti(pelialue.alue().alkupiste().x() + pelialue.alue().leveys()/2, 0);
        TetriminonRakentaja rakentaja = new TetriminonRakentaja(tetrimino, new Random());
        
        if(asetukset.palikoidenMaaraTetriminossa() != 0)
            rakentaja.luoTyypillinenTetrisPalikka(sijainti, asetukset.palikoidenMaaraTetriminossa());
        else
            rakentaja.luoTyypillinenTetrisPalikka(sijainti, new Random().nextInt(2) + 4);
        
        rakentaja.varita();
        alustaTetriminonSijainti();
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
    public void siirraTetriminoa(Suunta suunta)
    {
        if(suunta == Suunta.YLOS || !pelitilanne.voiLiikuttaa())
            return;
        
        asettelija().kokeileSiirtaa(suunta);
        pelitilanne.nappainAjastin().paivita();
        
        paivitaTilanne();
    }
    
    /** Välittää käyttäjän tahdon kääntää tetriminoa.
     * @param suunta Suunta, johon tetriminoa tahdotaan kääntää.
     */
    public void kaannaTetriminoa(Suunta suunta)
    {
        if(suunta == Suunta.YLOS || suunta == Suunta.ALAS || !pelitilanne.voiLiikuttaa())
            return;

        asettelija().kokeileKaantaa(suunta);
        pelitilanne.nappainAjastin().paivita();
        
        paivitaTilanne();
    }
    
    /** Välittää käyttäjän tahdon pudottaa tetrimino.
     */
    public void pudotaTetrimino()
    {
        if(!pelitilanne.voiLiikuttaa())
            return;

        asettelija().pudotaAlas();
        
        pelitilanne.nappainAjastin().paivita();
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
    
    /** Päivittää tetriminon tiputtamisen ja samalla huolehtii mahdollisesta pelin päättymisestä.
     */
    private void paivitaTetriminonTiputtaminen()
    {
        if(!asettelija().kokeileSiirtaa(Suunta.ALAS))
        {
            if(tetriminoOnPelialueenYlaosassa())
                paataPeli();
            else
            {
                new PelialueenRakentaja(pelialue).tungePalikat(tetrimino);
                uusiTetrimino();
            }
        }
        
        pelitilanne.tiputusAjastin().paivita();
        paivitaTilanne();
    }

    /** Päivittää pelialueen piirtämisen ja aavetetriminon.
     */
    private void paivitaTilanne()
    {
        if(pelialueenPaivittaja.onValmis())
            aavetetrimino.paivita();
        
        if(pelitilanteenPiirtaja != null)
            this.pelitilanteenPiirtaja.paivitaTilanne();
    }
    
    /** Päättää pelin.
     */
    private void paataPeli()
    {
        if(pelialue.lisattyja() > 0)
        {
            Ajastin ajastin = new Ajastin();
            while(true)
            {
                Vari vari = ((TetrisPalikka)pelialue.palikat().get(0)).vari();
                if(vari.peittavyys() <= 20)
                    break;
                
                if(ajastin.onKulunut(1))
                {
                    new PelialueenRakentaja(pelialue).himmenna(1);
                    paivitaTilanne();
                    ajastin.paivita();
                }
            }
        }
        
        pelitilanne.paataPeli();
    }
    
    /** Metodi, jota kutsumalla pelin pelaaminen alkaa.
     */
    public void pelaa()
    {
        while(true)
        {
            if(pelitilanne.onTauolla() || !peliOnKaynnissa())
            {
                this.pelitilanteenPiirtaja.paivitaTilanne();
                continue;
            }
            
            if(pelitilanne.tiputusAjastin().onKulunut(500 - pelitilanne.arvo(Pelitilanne.Tunniste.VAIKEUSTASO) * 50 ))
                paivitaTetriminonTiputtaminen();
            
            pelialueenPaivittaja.paivita();
            if(!pelialueenPaivittaja.onValmis())
                paivitaTilanne();
        }
    }
    
    /** Palauttaa pelissä käytetyt asetukset.
     * @return Pelin Asetukset-olio.
     */
    public Asetukset asetukset()
    {
        return asetukset;
    }
}
