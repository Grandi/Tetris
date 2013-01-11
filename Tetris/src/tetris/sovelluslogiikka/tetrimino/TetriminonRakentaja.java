
package tetris.sovelluslogiikka.tetrimino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Random;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Vari;

/** Rakentaa tetriminoja. Toisin sanoen sisällyttää joitain algoritmeja niiden muodostamiseen.
 * @author grandi
 */
public class TetriminonRakentaja
{
    /** Tetrimino, jolle tehdään muutoksia. */
    private Tetrimino tetrimino;
    
    /** Satunnaisgeneraattori, joka huolehtii rakentamisen satunnaisuudesta. */
    private Random satunnaisgeneraattori;
    
    /** 
     * @param tetrimino Tetrimino, jolle muutoksia tahdotaan suorittaa.
     * @param satunnaisgeneraattori Käyttäjän valitsema satunnaisgeneraattori.
     */
    public TetriminonRakentaja(Tetrimino tetrimino, Random satunnaisgeneraattori)
    {
        this.tetrimino = tetrimino;
        this.satunnaisgeneraattori = satunnaisgeneraattori;
    }
    
    /** Luo uuden tyhjän tetriminon ja alustaa oman satunnaisgeneraattorin.
     */
    public TetriminonRakentaja()
    {
        this.tetrimino = new Tetrimino();
        this.satunnaisgeneraattori = new Random();
    }

    /** Tunkee tetriminoon uusia palikoita satunnaisiin sijainteihin.
     * @param haluttuMaara Kuinka monta palikkaa tetriminossa tahdotaan olevan.
     */
    public void tungeUusiaSatunnaisiaPalikoita(int haluttuMaara)
    {
        while(tetrimino.palikkakokoelma().lisattyja() < haluttuMaara)
            tungeUusiPalikkaSattumanvaraisesti();
    }
    
    /** Luo neljä palikkaa sisältävän tetriminon.
     * @param sijainti Sijainti, johon tetrimino tahdotaan.
     */
    public void luoTyypillinenTetrisPalikka(Sijainti sijainti, int koko)
    {
        tungeUusiaSatunnaisiaPalikoita(koko);
        tetrimino.asetaSijainniksi(sijainti);
    }   
    
    /** Antaa kaikille tetriminossa oleville palikoille tietyn värin.
     * @param varivalinta Haluttu väri.
     */
    public void varita(Vari varivalinta)
    {
        for(Palikka palikka : tetrimino.palikkakokoelma().palikat())
            ((TetrisPalikka)palikka).vari().aseta(varivalinta);
    }
    
    /** Värittää tetriminon palikat sattumanvaraisella värillä.
     */
    public void varita(Vari[] paletti)
    {
        varita(paletti[satunnaisgeneraattori.nextInt(paletti.length)]);
    }
    
    /** Luo uuden palikan. Tämä on vain tällainen lyhentävä wrapperifunktio.
     * @param sijainti Sijainti, johon palikka sijoitetaan.
     * @return Palauttaa tetriminoon kuuluvan TetriminoPalikan.
     */
    private TetriminoPalikka uusiPalikka(Sijainti sijainti)
    {
        return new TetriminoPalikka(sijainti, tetrimino);
    }
    
    /** Luo uuden palikan. Tämä on vain tällainen lyhentävä wrapperifunktio. 
     *
     * @param x Palikan x-koordinaatti.
     * @param y Palikan y-koordinaatti.
     * @return Palauttaa tetriminoon kuuluvan TetriminoPalikan.
     */
    private TetriminoPalikka uusiPalikka(int x, int y)
    {
        return new TetriminoPalikka(new Sijainti(x, y), tetrimino);
    }    
    
    /** Tunkee tetriminoon ensimmäisen palikan.
     */
    private void tungeEnsimmainenPalikka()
    {
        tetrimino.palikkakokoelma().tungePalikka(uusiPalikka(new Sijainti(0, 0)));
    }
    
    /** Tunkee tetriminoon uuden palikan sattumanvaraiseen paikkaan. Kuitenkin niin,
     * että tetriminosääntö säilyy (kaikki palikat toistensa vieressä).
     * @return Palauttaa true, mikäli palikan lisääminen onnistui. Muutoin false.
     */
    private boolean tungeUusiPalikkaSattumanvaraisesti()
    {
        if(tetrimino.palikkakokoelma().lisattyja() > 0)
            return tungeUusiPalikkaJonkinEdellisenViereen();
        
        tungeEnsimmainenPalikka();
        return true;
    }
    
    /** Lisää tetriminoon uuden palikan jonkin aiemmin tungetun palikan viereen.
     * @return True, jos lisääminen onnistui. False, jos lisääminen ei onnistunut.
     */
    private boolean tungeUusiPalikkaJonkinEdellisenViereen()
    {
        ArrayList<Palikka> palikat = tetrimino.palikkakokoelma().palikat();
        int indeksi = satunnaisgeneraattori.nextInt(palikat.size());

        return tungeUusiPalikkaSattumanvaraisesti(((TetriminoPalikka)palikat.get( indeksi )).viereisetSijainnit());
    }
    
    /** Tunkee tetriminoon uuden palikan sattumanvaraisesti johonkin annetuista sijainneista.
     * @param sijainnit Sijainnit, joista yhteen palikka tahdotaan asettaa.
     * @return True, jos palikka saatiin asetettua johonkin annetuista sijainneista. Muutoin false.
     */
    private boolean tungeUusiPalikkaSattumanvaraisesti(ArrayList<Sijainti> sijainnit)
    {
        Collections.shuffle(sijainnit);
        
        for(Sijainti viereinen : sijainnit)
            if(tetrimino.palikkakokoelma().tungePalikka(uusiPalikka(viereinen)))
                return true;
        
        return false;
    }
    
    /** Palauttaa tetriminon, jolle luokan toimintoja on sovellettu.
     * @return Rakennettu tetrimino.
     */
    public Tetrimino rakennettuTetrimino()
    {
        return tetrimino;
    }

    /** Rakentaa tetriminon annetusta boolean-taulukko-mallista.
     * 
     * @param sijainti Sijainti, johon tetrimino tahdotaan sijoittaa.
     * @param malli Kaksiuloitteinen boolean-taulukko, jossa truet merkitsevät palikoita.
     * @return Palauttaa true, jos malli oli kelvollinen. Muuten false.
     */
    public boolean rakennaMallista(Sijainti sijainti, boolean[][] malli)
    {
        for(int y = 0; y < malli.length; y++)
            for(int x = 0; x < malli[y].length; x++)
                if(malli[y][x] && !tetrimino.palikkakokoelma().tungePalikka(uusiPalikka(x, y)))
                    return false;
        
        tetrimino.asetaSijainniksi(new Sijainti(sijainti.x(), sijainti.y()));
        return true;
    }
    
    /** Kopioi tetriminon. Eroaa Tetrimino-luokan omasta kopiointiluokasta siten, että tässä
     * uudesta tetriminosta tulee itsenäinen, eli se ei jaa samoja palikkakokoelma-resursseja.
     * 
     * @param toinen Tetrimino, joka tahdotaan kopioida.
     */
    public void rakennaToisenTetriminonPohjalta(Tetrimino toinen)
    {
        tetrimino.alusta();

        //try {
            for(Palikka palikka : toinen.palikkakokoelma().palikat())
            {
                if(palikka == null) continue;
                TetriminoPalikka uusi = new TetriminoPalikka(((TetriminoPalikka)palikka).alkuperainenSijainti(), tetrimino);
                tetrimino.palikkakokoelma().tungePalikka(uusi);
            }
        //} catch(ConcurrentModificationException e) {}
        
        tetrimino.laitaAsentoon(toinen.asento());
        tetrimino.asetaSijainniksi(toinen.siirtyma());
    }
}
