
package tetris.sovelluslogiikka.tetrimino;

import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Vari;

/** Määrittää tetriminopalikan, eli palikan, joka on osa Tetris-palikkamuodostelmaa.
 * @author grandi
 */
public class TetriminoPalikka extends TetrisPalikka
{
    private Tetrimino tetrimino;
    
    /**
     * @param sijainti Palikan alkuperäinen sijainti.
     * @param tetrimino Tetrimino, joka "omistaa" kyseisen palikan.
     */
    public TetriminoPalikka(Sijainti sijainti, Tetrimino tetrimino)
    {
        super(sijainti);
        this.tetrimino = tetrimino;
    }
    
    /** Kopioi palikan.
     * @param palikka Palikka, jonka sijainti kopioidaan.
     * @param tetrimino Tetrimino, jonka osana palikka on.
     */
    public TetriminoPalikka(Palikka palikka, Tetrimino tetrimino)
    {
        this(palikka.sijainti(), tetrimino);
    }
    
    /** Kopioi tetriminopalikan, eli sen sijainnin ja tetriminon.
     * @param kopioitava Kopioitava tetriminopalikka.
     */
    public TetriminoPalikka(TetriminoPalikka kopioitava)
    {
        this(kopioitava.alkuperainenSijainti(), kopioitava.tetrimino);
    }
    
    /** Laskee tetriminopalikan suhteellisen sijainnin, eli missä suhteessa sen sijainti on
     * ympäröivän tetriminon keskipisteeseen.
     * @return Tetriminon suhteellinen sijainti. Palauttaa null, jos sitä ei kyetä laskemaan.
     */
    private Sijainti suhteellinenSijainti()
    {
        Sijainti keskipiste = ((TetriminoPalikkakokoelma)tetrimino.palikkakokoelma()).suhteellinenKeskipiste();
        
        if(keskipiste == null)
            return null;
        else
            return new Sijainti(sijainti.x() - keskipiste.x(), sijainti.y() - keskipiste.y());
    }

    /** Laskee tetriminopalikan todellisen sijainnin riippuen tetriminon sijainnista ja asennosta.
     * @return Tetriminopalikan todellinen sijainti.
     */
    @Override public Sijainti sijainti()
    {
        if(suhteellinenSijainti() == null)
            return tetrimino.sijainti();
        
        switch(tetrimino.asento())
        {
            case 0: return new Sijainti( (int)Math.round(tetrimino.sijainti().x() + suhteellinenSijainti().x()),  (int)Math.round(tetrimino.sijainti().y() + suhteellinenSijainti().y()));
            case 1: return new Sijainti( (int)Math.round(tetrimino.sijainti().x() - suhteellinenSijainti().y()),  (int)Math.round(tetrimino.sijainti().y() + suhteellinenSijainti().x())); 
            case 2: return new Sijainti( (int)Math.round(tetrimino.sijainti().x() - suhteellinenSijainti().x()),  (int)Math.round(tetrimino.sijainti().y() - suhteellinenSijainti().y()));
            case 3: return new Sijainti( (int)Math.round(tetrimino.sijainti().x() + suhteellinenSijainti().y()),  (int)Math.round(tetrimino.sijainti().y() - suhteellinenSijainti().x()));
            default: return null;
        }
    }
    
    /** Palauttaa tetriminon merkkijonoesityksen.
     * @return Tetriminon merkkijonoesitys, joka sisältää sen sijainnin.
     */
    @Override public String toString()
    {
        return "TetriminoPalikka @ " + sijainti();
    }
    
    /** Kertoo tetriminopalikan vieressä olevat sijainnit.
     * @return ArrayList, joka sisältää viereiset sijainnit.
     */
    public ArrayList<Sijainti> viereisetSijainnit()
    {
        Sijainti keski = sijainti();
        ArrayList<Sijainti> viereiset = new ArrayList<Sijainti>();
        
        for(int y = -1; y <= 1; y++)
        for(int x = -1; x <= 1; x++)
            if(x != 0 || y != 0)
                viereiset.add(new Sijainti(keski.x() + x, keski.y() + y));
        
        return viereiset;
    }
    
    /** Kertoo, mikä tetriminopalikan sijainti oli alunperin, kun sitä oltiin lisäämässä tetriminoon.
     * @return Tetriminopalikan alkuperäinen sijainti.
     */
    public Sijainti alkuperainenSijainti()
    {
        return sijainti;
    }
    
    /** Palauttaa tetriminon, jona osana tetriminopalikka on.
     * @return Tetrimino, joka sisältää tämän tetriminopalikan.
     */
    public Tetrimino omistajaTetrimino()
    {
        return tetrimino;
    }
}
