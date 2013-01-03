
package tetris.sovelluslogiikka.tetrimino;

import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

public class TetriminoPalikka extends Palikka
{
    private Tetrimino tetrimino;
    
    public TetriminoPalikka(Sijainti sijainti, Tetrimino tetrimino)
    {
        super(sijainti);
        this.tetrimino = tetrimino;
    }
    
    public TetriminoPalikka(Palikka palikka, Tetrimino tetrimino)
    {
        this(palikka.sijainti(), tetrimino);
    }
    
    public TetriminoPalikka(TetriminoPalikka kopioitava)
    {
        this(kopioitava.sijainti(), kopioitava.tetrimino);
    }
    
    public Sijainti suhteellinenSijainti()
    {
        Sijainti keskipiste = ((TetriminoPalikkakokoelma)tetrimino.palikkakokoelma()).suhteellinenKeskipiste();
        return new Sijainti(sijainti.x() - keskipiste.x(), sijainti.y() - keskipiste.y());
    }
    
    @Override public Sijainti sijainti()
    {
        switch(tetrimino.asento())
        {
            case 0: return new Sijainti(tetrimino.sijainti().x() + suhteellinenSijainti().x(), tetrimino.sijainti().y() + suhteellinenSijainti().y());
            case 1: return new Sijainti(tetrimino.sijainti().x() + suhteellinenSijainti().y(), tetrimino.sijainti().y() + suhteellinenSijainti().x()); 
            case 2: return new Sijainti(tetrimino.sijainti().x() - suhteellinenSijainti().x(), tetrimino.sijainti().y() + suhteellinenSijainti().y());
            case 3: return new Sijainti(tetrimino.sijainti().x() + suhteellinenSijainti().y(), tetrimino.sijainti().y() - suhteellinenSijainti().x());
            default: return null;
        }
    }
    
    @Override public String toString()
    {
        return "TetriminoPalikka @ " + sijainti;
    }
    
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
    
    public Sijainti alkuperainenSijainti()
    {
        return sijainti;
    }
    
    public Tetrimino omistajaTetrimino()
    {
        return tetrimino;
    }
}
