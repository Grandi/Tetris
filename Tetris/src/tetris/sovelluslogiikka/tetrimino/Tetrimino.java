
package tetris.sovelluslogiikka.tetrimino;

import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

public class Tetrimino
{
    private TetriminoPalikkakokoelma palikkakokoelma;
    private Sijainti siirtyma;
    private int asento;
    
    public Tetrimino()
    {
        this.palikkakokoelma = new TetriminoPalikkakokoelma(this);
        this.asento = 0;
        this.siirtyma = new Sijainti(0, 0);
    }
    
    public Tetrimino(Tetrimino kopioitava)
    {
        this.palikkakokoelma = new TetriminoPalikkakokoelma(kopioitava.palikkakokoelma);
        this.asento = kopioitava.asento();
        this.siirtyma = new Sijainti(kopioitava.sijainti());
    }
    
    public void asetaSijainniksi(Sijainti sijainti)
    {
        this.siirtyma = new Sijainti(sijainti);
    }
    
    public int asento()
    {
        return asento;
    }
    
    public void laitaAsentoon(int asento)
    {
        this.asento = asento;
        if(this.asento > 3)
            this.asento = 0;
        else if(this.asento < 0)
            this.asento = 3;
    }

    public void kaannaMyotapaivaan()
    {
        laitaAsentoon(asento + 1);
    }
    
    public void kaannaVastapaivaan()
    {
        laitaAsentoon(asento - 1);
    }
    
    public Sijainti sijainti()
    {          
        Sijainti keskipiste = palikkakokoelma.suhteellinenKeskipiste();
        
        if(keskipiste == null || siirtyma == null)
            return null;
        else
            return new Sijainti(siirtyma.x() + keskipiste.x(), siirtyma.y() + keskipiste.y());
    }
    
    public Palikkakokoelma palikkakokoelma()
    {
        return palikkakokoelma;
    }
}
