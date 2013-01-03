
package tetris.sovelluslogiikka.tetrimino;

import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Valimatkapaivittaja;

public class TetriminoPalikkakokoelma implements Palikkakokoelma
{
    private ArrayList<Palikka> palikat;
    private Tetrimino tetrimino;
    private Sijainti keskipiste;
    
    public TetriminoPalikkakokoelma(Tetrimino tetrimino)
    {
        palikat = new ArrayList<Palikka>();
        this.tetrimino = tetrimino;
        this.keskipiste = new Sijainti(0, 0);
    }
    
    public TetriminoPalikkakokoelma(TetriminoPalikkakokoelma kopioitava)
    {
        this.palikat = new ArrayList<Palikka>(kopioitava.palikat);
        this.tetrimino = kopioitava.tetrimino;
        //this.keskipiste = kopioitava.suhteellinenKeskipiste();
    }
    
    private boolean xor(boolean a, boolean b)
    {
        return (!a && b) || (a && !b);
    }
    
    private boolean eiOleLiianKaukanaEnsimmaisesta(Sijainti sijainti)
    {
        return palikat.isEmpty() || Valimatkapaivittaja.valimatka(sijainti, ((TetriminoPalikka)palikat.get(0)).alkuperainenSijainti()) <= 2;
    }
    
    private boolean onJonkinMuunPalikanVieressa(Sijainti sijainti)
    {
        if(palikat.isEmpty())
            return true;

        for(Palikka palikka : palikat)
            if(xor(Math.abs(sijainti.x()-((TetriminoPalikka)palikka).alkuperainenSijainti().x()) == 1, Math.abs(sijainti.y()-((TetriminoPalikka)palikka).alkuperainenSijainti().y()) == 1))
                return true;
        
        return false;
    }
    
    private boolean onHyvassaSijainnissa(Sijainti tungettava)
    {
        return eiOleLiianKaukanaEnsimmaisesta(tungettava) &&
               sisaltaaPalikan(tungettava) == null &&
               onJonkinMuunPalikanVieressa(tungettava);
    }

    private void lisaaPalikka(TetriminoPalikka palikka)
    {
        palikat.add(palikka);
        paivitaKeskipiste(palikka.alkuperainenSijainti());
    }
    
    private TetriminoPalikka luoTetriminoPalikka(Palikka palikka)
    {
        return new TetriminoPalikka(palikka, tetrimino);
    }
    
    public void paivitaKeskipiste(Sijainti sijainti)
    {
        keskipiste.aseta(keskipiste.x() + sijainti.x(), keskipiste.y() + sijainti.y());
    }
    
    @Override public boolean tungePalikka(Palikka palikka)
    {
        TetriminoPalikka tetriminoPalikka;
        if(!(palikka instanceof TetriminoPalikka) || ((TetriminoPalikka)palikka).omistajaTetrimino() != tetrimino)
            tetriminoPalikka = luoTetriminoPalikka(palikka);
        else
            tetriminoPalikka = (TetriminoPalikka)palikka;
        
        if(!onHyvassaSijainnissa(tetriminoPalikka.alkuperainenSijainti()))
            return false;
        
        lisaaPalikka(tetriminoPalikka);
        return true;
    }

    @Override public Palikka sisaltaaPalikan(Sijainti sijainti)
    {
        for(Palikka palikka : palikat)
            if(((TetriminoPalikka)palikka).alkuperainenSijainti().onSamaKuin(sijainti))
                return palikka;
        
        return null;
    }

    @Override public ArrayList<Palikka> palikat()
    {
        return palikat;
    }
    
    public Sijainti suhteellinenKeskipiste()
    {
        if(lisattyja() == 0)
            return null;
        else
            return new Sijainti(keskipiste.x() / lisattyja(), keskipiste.y() / lisattyja());
    }

    @Override public int lisattyja()
    {
        return palikat.size();
    }
}
