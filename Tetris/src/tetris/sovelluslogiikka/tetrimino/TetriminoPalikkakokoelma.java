
package tetris.sovelluslogiikka.tetrimino;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

/** Pitää kirjaa tetriminosääntöä noudattavista tetriminopalikoista. Toisin sanoen siis jokaisen
 * kokoelmassa olevan palikan on oltava jonkin toisen kokoelmassa olevan palikan vieressä.
 * @author grandi
 */
public class TetriminoPalikkakokoelma implements Palikkakokoelma
{
    /** ArrayList, joka sisältää palikkakokoelmassa olevat palikat.
     * Taataan olevan TetriminoPalikka-luokan jäseniä. */
    private ArrayList<Palikka> palikat;
    
    /** Tetrimino, joka omistaa tämän palikkakokoelman ja nämä palikat. */
    private Tetrimino tetrimino;
    
    /** Sijoitettujen palikoiden alkuperäisten sijaintien summa. Tämän perusteella voidaan
     *  laskea palikoille keskiarvo, jonka perusteella taas voi laskea suhteellisia sijainteja. */
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

    /** Kertoo, onko sijainti toisen vieressä.
     * 
     * @param a Ensimmäinen vertailtava sijainti.
     * @param b Toinen vertailtava sijainti;
     * @return True, jos sijainnit ovat vierekkäin. Muutoin false.
     */
    private boolean onVieressa(Sijainti a, Sijainti b)
    {
        return (a.x() == b.x() && Math.abs(a.y() - b.y()) == 1) ||
               (a.y() == b.y() && Math.abs(a.x() - b.x()) == 1);
    }
    
    /** Kertoo, olisiko sijainti jonkin kokoelmassa olevan palikan vieressä.
     * 
     * @param sijainti Sijainti, josta tahdotaan tietää sen vieruus kokoelman palikoihin.
     * @return True, mikäli edes jokin vieressä oleva palikka löytyy. Muutoin false.
     */
    private boolean onJonkinMuunPalikanVieressa(Sijainti sijainti)
    {
        if(palikat.isEmpty())
            return true;

        for(Palikka palikka : palikat)
            if(palikka != null && onVieressa(((TetriminoPalikka)palikka).sijainti(), sijainti))
                return true;
        
        return false;
    }

    /** Kertoo, sisältääkö kokoelma palikan tässä sijainnissa.
     * @param sijainti Sijainti, josta palikan löytyminen tahdotaan selvittää.
     * @return True, mikäli sijainnissa ei ole tämän kokoelman palikkaa. Muutoin false.
     */
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

    /** Lisää palikkakokoelmaan uuden TetriminoPalikka-olion.
     * @param palikka Lisättävä TetriminoPalikka.
     */
    private void lisaaPalikka(TetriminoPalikka palikka)
    {
        palikat.add(palikka);
        paivitaSumma(palikka.alkuperainenSijainti());
    }

    /** Päivittää sijoitettujen palikoiden alkuperäisten sijaintien summan.
     * @param sijainti Sijainti, joka tahdotaan laskea mukaan summaan. 
     */
    private void paivitaSumma(Sijainti sijainti)
    {
        summa.aseta(summa.x() + sijainti.x(), summa.y() + sijainti.y());
    }
    
    /** Varmistaa, että Palikka-olio on TetriminoPalikka-olio, ja sen omistaja on sama tetrimino.
     * Mikäli näin ei ole, uusi TetriminoPalikka-olio luodaan.
     * @param palikka TetriminoPalikka, joka vastaa annettua palikka-oliota.
     * @return Palauttaa annettua Palikka-oliota vastaavan TetriminoPalikka-olion.
     */
    private TetriminoPalikka varmistaTetriminoPalikkaus(Palikka palikka)
    {
        if(!(palikka instanceof TetriminoPalikka) || ((TetriminoPalikka)palikka).omistajaTetrimino() != tetrimino)
            return new TetriminoPalikka(palikka, tetrimino);
        
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
        try {
            for(Palikka palikka : palikat)
                if(/*palikka != null &&*/ ((TetriminoPalikka)palikka).sijainti().equals(sijainti))
                    return palikka;
        } catch(ConcurrentModificationException e) {}
        
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
        if(palikat.isEmpty())
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
    
    /** Laskee uudelleen sijoitettujen palikoiden sijaintien summan.
     */
    private void uudelleenlaskeSumma()
    {
        summa = new Sijainti(0, 0);
        
        for(Palikka palikka : palikat)
            paivitaSumma(palikka.sijainti());
    }
    
    /** Kertoo, toteuttavatko nykyiset palikat tetriminosäännön. Eli ovat toistensa vieressä.
     * @return True, jos palikat toteuttavat tetriminosäännön. Muutoin false.
     */
    public boolean toteuttaaTetriminosaannon()
    {
        for(Palikka palikka : palikat)
            if(!onJonkinMuunPalikanVieressa(palikka.sijainti()))
                return false;
        
        return true;
    }
    
    /** Poistaa kokoelman palikan annetusta sijainnista.
     * @param sijainti Sijainti, josta palikka tahdotaan poistaa.
     * @return Poistettu palikka, tai jos palikkaa ei ole olemassa, null.
     */
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
        
        uudelleenlaskeSumma();
        return true;
    }
}
