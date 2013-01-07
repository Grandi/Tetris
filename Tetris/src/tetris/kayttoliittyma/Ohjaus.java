
package tetris.kayttoliittyma;

import java.util.Random;
import tetris.sovelluslogiikka.pelimekaniikka.PelinTila;
import tetris.sovelluslogiikka.pelimekaniikka.PelitilanteenPiirtaja;
import tetris.sovelluslogiikka.pelimekaniikka.TetriminonAsettelija;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.pelialue.PelialueenRakentaja;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Suunta;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.tetrimino.TetriminonRakentaja;

/** Luokka, joka yhdistää sovelluslogiikan komponentit Tetris-peliksi. 
 * @author grandi
 */
public class Ohjaus
{
    private PelitilanteenPiirtaja pelitilanteenPiirtaja;
    private Tetrimino tetrimino;
    private Pelialue pelialue;
    private PelinTila tila;

    public Ohjaus()
    {
        this.pelialue = new Pelialue(new Sijainti(0, 0), 10, 20);
        
        //new PelialueenRakentaja(pelialue).esitaytaRivit(3);
        
        uusiTetrimino();
        this.tila = new PelinTila();
    }
    
    /** Asettaa sen pelitilanteen piirtäjän, jolla pelitilanne tahdotaan piirtää.
     * @param pelitilanteenPiirtaja Tahdottu pelitilanteen piirtäjä.
     */
    public void asetaPelitilanteenPiirtaja(PelitilanteenPiirtaja pelitilanteenPiirtaja)
    {
        this.pelitilanteenPiirtaja = pelitilanteenPiirtaja;
        
        pelitilanteenPiirtaja.lisaaSeurattavaPalikkakokoelma(tetrimino.palikkakokoelma());
        pelitilanteenPiirtaja.lisaaSeurattavaPalikkakokoelma(pelialue);
    }
    
    /** Kertoo, onko peli käynnissä. 
     * @return True, jos peli on käynnissä, muutoin false.
     */
    public boolean peliOnKaynnissa()
    {
        return !tila.peliOnPaattynyt();
    }
    
    private void uusiTetrimino()
    {
        if(tetrimino != null)
            tetrimino.alusta();
        else
            tetrimino = new Tetrimino();

        Sijainti sijainti = new Sijainti(pelialue.alue().alkupiste().x() + pelialue.alue().leveys()/2, 1);
        new TetriminonRakentaja(tetrimino, new Random()).luoTyypillinenTetrisPalikka(sijainti);
        
        alustaTetriminonSijainti();
        paivitaTilanne();
    }
    
    private void alustaTetriminonSijainti()
    {
        if(!asettelija().yritaPoistuaTormaamasta(Suunta.ALAS))
            tila.paataPeli();
    }
    
    private TetriminonAsettelija asettelija()
    {
        return new TetriminonAsettelija(tetrimino, pelialue);
    }
    
    /** Välittää käyttäjän tahdon siirtää tetriminoa.
     * @param suunta Suunta, johon tetriminoa tahdotaan siirtää.
     */
    public void siirraTetriminoa(Suunta suunta)
    {
        if(suunta == Suunta.YLOS || !tila.voiLiikuttaa())
            return;
        
        asettelija().kokeileSiirtaa(suunta);
        tila.nappainAjastin().paivita();
        
        //System.out.println("Foo: " + tetrimino.sijainti());
        paivitaTilanne();
    }
    
    /** Välittää käyttäjän tahdon kääntää tetriminoa.
     * @param suunta Suunta, johon tetriminoa tahdotaan kääntää.
     */
    public void kaannaTetriminoa(Suunta suunta)
    {
        if(suunta == Suunta.YLOS || suunta == Suunta.ALAS || !tila.voiLiikuttaa())
            return;

        asettelija().kokeileKaantaa(suunta);
        tila.nappainAjastin().paivita();
        paivitaTilanne();
        
        //System.out.println(tetrimino.asento());
    }
    
    /** Välittää käyttäjän tahdon pudottaa tetrimino.
     */
    public void pudotaTetrimino()
    {
        if(!tila.voiLiikuttaa())
            return;

        asettelija().pudotaAlas();
        
        tila.nappainAjastin().paivita();
        tila.tiputusAjastin().paivita();
        
        paivitaTilanne();
    }
    
    private void paivitaTetriminonTiputtaminen()
    {
        if(!asettelija().kokeileSiirtaa(Suunta.ALAS))
        {
            new PelialueenRakentaja(pelialue).tungePalikat(tetrimino);
            uusiTetrimino();
        }
        
        tila.tiputusAjastin().paivita();
        paivitaTilanne();
    }

    private void paivitaTilanne()
    {
        if(pelitilanteenPiirtaja != null)
            this.pelitilanteenPiirtaja.paivitaTilanne();
        
        //paivitaAavetetrimino();
    }
    
    /** Metodi, jota kutsumalla pelin pelaaminen alkaa.
     */
    public void pelaa()
    {
        while(peliOnKaynnissa())
            if(tila.tiputusAjastin().onKulunut(500))
                paivitaTetriminonTiputtaminen();
    }
}
