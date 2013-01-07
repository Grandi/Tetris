
package tetris.sovelluslogiikka.tetrimino;

import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

/** Pitää kirjaa tetriminosääntöä noudattavista tetriminopalikoista. Toisin sanoen siis jokaisen
 * kokoelmassa olevan palikan on oltava jonkin toisen kokoelmassa olevan palikan vieressä.
 * @author grandi
 */
public class TetriminoPalikkakokoelma implements Palikkakokoelma
{
    private ArrayList<Palikka> palikat;
    private Tetrimino tetrimino;
    private Sijainti summa;
    
    /**
     * @param tetrimino Tetrimino, jonka osana kokoelman palikat ovat.
     */
    public TetriminoPalikkakokoelma(Tetrimino tetrimino)
    {
        palikat = new ArrayList<Palikka>();
        this.tetrimino = tetrimino;
        this.summa = new Sijainti(0, 0);
    }
    
    /** Kopioi tetriminopalikkakokoelman.
     * @param kopioitava Tetriminopalikkakokoelma, joka tahdotaan kopioida.
     */
    public TetriminoPalikkakokoelma(TetriminoPalikkakokoelma kopioitava)
    {
        this.palikat = new ArrayList<Palikka>(kopioitava.palikat);
        this.tetrimino = kopioitava.tetrimino;
        this.summa = kopioitava.summa;
    }

    private boolean onVieressa(Sijainti a, Sijainti b)
    {
        return (a.x() == b.x() && Math.abs(a.y() - b.y()) == 1) ||
               (a.y() == b.y() && Math.abs(a.x() - b.x()) == 1);
    }
    
    private boolean onJonkinMuunPalikanVieressa(Sijainti sijainti)
    {
        if(palikat.isEmpty())
            return true;

        for(Palikka palikka : palikat)
            if(onVieressa(((TetriminoPalikka)palikka).sijainti(), sijainti))
                return true;
        
        return false;
    }

    private boolean eiSisallaPalikkaa(Sijainti sijainti)
    {
        return haePalikka(sijainti) == null;
    }
    
    /** Kertoo, voisiko tähän kokoelmaan lisätä uuden palikan tiettyyn sijaintiin.
     * @param sijainti Sijainti, jonka hyvyydestä ollaan lisäämisen kannalta kiinnostuneita.
     * @return Palauttaa false, jos sijaintiin ei voi lisätä uutta palikkaa. Muutoin true.
     */
    public boolean onHyvassaSijainnissaLisaamisenKannalta(Sijainti sijainti)
    {
        return eiSisallaPalikkaa(sijainti) &&
               onJonkinMuunPalikanVieressa(sijainti);
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
    
    private void paivitaKeskipiste(Sijainti sijainti)
    {
        summa.aseta(summa.x() + sijainti.x(), summa.y() + sijainti.y());
    }
    
    private TetriminoPalikka varmistaTetriminoPalikkaus(Palikka palikka)
    {
        if(!(palikka instanceof TetriminoPalikka) || ((TetriminoPalikka)palikka).omistajaTetrimino() != tetrimino)
            return luoTetriminoPalikka(palikka);
        
        return (TetriminoPalikka)palikka;
    }
    
    @Override public boolean tungePalikka(Palikka palikka)
    {
        TetriminoPalikka tetriminoPalikka = varmistaTetriminoPalikkaus(palikka);
        
        if(!onHyvassaSijainnissaLisaamisenKannalta(tetriminoPalikka.alkuperainenSijainti()))
            return false;
        
        lisaaPalikka(tetriminoPalikka);
        return true;
    }

    @Override public Palikka haePalikka(Sijainti sijainti)
    {
        for(Palikka palikka : palikat)
            if(((TetriminoPalikka)palikka).sijainti().equals(sijainti))
                return palikka;
        
        return null;
    }

    @Override public ArrayList<Palikka> palikat()
    {
        return palikat;
    }
    
    /**
     * @return Lisättyjen palikoiden alkuperäisten sijaintien keskiarvo.
     */
    public Sijainti suhteellinenKeskipiste()
    {
        if(lisattyja() == 0)
            return new Sijainti(0, 0);
        else
            return new Sijainti(summa.x() / lisattyja(), summa.y() / lisattyja());
    }

    @Override public int lisattyja()
    {
        return palikat.size();
    }

    @Override public void tyhjenna()
    {
        palikat.clear();
        summa = new Sijainti(0, 0);
    }
    
    private void uudelleenlaskeKeskipiste()
    {
        summa = new Sijainti(0, 0);
        
        for(Palikka palikka : palikat)
            paivitaKeskipiste(palikka.sijainti());
    }
    
    private boolean toteuttaaTetriminosaannon()
    {
        for(Palikka palikka : palikat)
            if(!onJonkinMuunPalikanVieressa(palikka.sijainti()))
                return false;
        
        return true;
    }
    
    private Palikka poista(Sijainti sijainti)
    {
        for(int i = 0; i < palikat.size(); i++)
            if(palikat.get(i).sijainti().equals(sijainti))
            {
                Palikka poistettu = palikat.get(i);
                palikat.remove(i);
                return poistettu;
            }
        
        return null;
    }

    @Override public boolean poistaPalikka(Sijainti sijainti)
    {
        Palikka poistettu = poista(sijainti);
        if(poistettu == null)
            return false;
        
        if(!toteuttaaTetriminosaannon())
        {
            tungePalikka(poistettu);
            return false;
        }
        
        uudelleenlaskeKeskipiste();
        return true;
    }
}
