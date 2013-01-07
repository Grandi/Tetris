
package tetris.sovelluslogiikka.tetrimino;

import java.util.ArrayList;
import java.util.Collections;
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
    private Tetrimino tetrimino;
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
    public void luoTyypillinenTetrisPalikka(Sijainti sijainti)
    {
        tungeUusiaSatunnaisiaPalikoita(4);
        
        tetrimino.asetaSijainniksi(sijainti);
        varita(new Vari(50, 50, 50, 255));
    }   
    
    /** Antaa kaikille tetriminossa oleville palikoille tietyn värin.
     * @param varivalinta Haluttu väri.
     */
    public void varita(Vari varivalinta)
    {
        for(Palikka palikka : tetrimino.palikkakokoelma().palikat())
            ((TetrisPalikka)palikka).vari().aseta(varivalinta);
    }
    
    private TetriminoPalikka uusiPalikka(Sijainti sijainti)
    {
        return new TetriminoPalikka(sijainti, tetrimino);
    }
    
    private TetriminoPalikka uusiPalikka(int x, int y)
    {
        return new TetriminoPalikka(new Sijainti(x, y), tetrimino);
    }    
    
    private void tungeEnsimmainenPalikka()
    {
        tetrimino.palikkakokoelma().tungePalikka(uusiPalikka(new Sijainti(0, 0)));
    }

    private boolean tungeUusiPalikkaSattumanvaraisesti()
    {
        if(tetrimino.palikkakokoelma().lisattyja() > 0)
            return tungeUusiPalikkaJonkinEdellisenViereen();
        
        tungeEnsimmainenPalikka();
        return true;
    }
    
    private boolean tungeUusiPalikkaJonkinEdellisenViereen()
    {
        ArrayList<Palikka> palikat = tetrimino.palikkakokoelma().palikat();
        int indeksi = satunnaisgeneraattori.nextInt(palikat.size());

        return tungeUusiPalikkaSattumanvaraisesti(((TetriminoPalikka)palikat.get( indeksi )).viereisetSijainnit());
    }
    
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
        for(Palikka palikka : toinen.palikkakokoelma().palikat())
        {
            TetriminoPalikka uusi = new TetriminoPalikka(((TetriminoPalikka)palikka).alkuperainenSijainti(), tetrimino);
            tetrimino.palikkakokoelma().tungePalikka(uusi);
        }
        
        tetrimino.asetaSijainniksi(toinen.siirtyma());
    }
}
