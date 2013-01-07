
package tetris.sovelluslogiikka.pelialue;

import java.util.ArrayList;
import java.util.HashMap;
import tetris.sovelluslogiikka.sekalaiset.Alue;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;

/** Palikkakokoelma, josta pelialueella olevissa palikkariveissä olevia palikoita
 * voidaan hakea ja manipuloida.
 * @author grandi
 */
public class Pelialue implements Palikkakokoelma
{
    private HashMap<Sijainti, TetrisPalikka> palikat;
    private HashMap<Float, Integer> lisattyja;
    private Alue alue;
    
    private float korjattuY(float y)
    {
        return alue.paatepiste().y()-y;
    }

    /**
     * @param sijainti Sijainti, jossa pelialue sijaitsee. Yleensä (0,0)
     * @param leveys Pelialueen leveys.
     * @param korkeus Pelialueen korkeus.
     */
    public Pelialue(Sijainti sijainti, int leveys, int korkeus)
    {
        alue = new Alue(sijainti, leveys, korkeus);
        palikat = new HashMap<Sijainti, TetrisPalikka>();
        lisattyja = new HashMap<Float, Integer>();
    }
    
    private void paivitaLisatyt(float rivi, int muutos)
    {
        if(!lisattyja.containsKey(rivi))
            lisattyja.put(rivi, muutos);
        else
        {
            if(lisattyja.get(rivi) + muutos == 0)
                lisattyja.remove(rivi);
            else
                lisattyja.put(rivi, lisattyja.get(rivi) + muutos);
        }
    }

    @Override public boolean tungePalikka(Palikka palikka)
    {
        if(!alue.onSisalla(palikka.sijainti()) || haePalikka(palikka.sijainti()) != null)
            return false;

        palikat.put(palikka.sijainti(), new TetrisPalikka(palikka));
        paivitaLisatyt(korjattuY(palikka.sijainti().y()), 1);
        return true;
    }

    @Override public Palikka haePalikka(Sijainti sijainti)
    {
        if(!alue.onSisalla(sijainti) || !palikat.containsKey(sijainti))
            return null;
        
        return palikat.get(sijainti);
    }

    @Override public ArrayList<Palikka> palikat()
    {
        return new ArrayList<Palikka>(palikat.values());
    }

    @Override public int lisattyja()
    {
        return palikat.size();
    }

    @Override public void tyhjenna()
    {
        palikat.clear();
    }

    @Override public boolean poistaPalikka(Sijainti sijainti)
    {
        if(haePalikka(sijainti) == null)
            return false;
        
        palikat.remove(sijainti);
        paivitaLisatyt(korjattuY(sijainti.y()), -1);
        return true;
    }
    
    /** Palauttaa pelialueen alueen, josta voidaan laskea sen rajat.
     * @return Pelialueen alue.
     */
    public Alue alue()    
    {
        return alue;
    }
    
    /** Kertoo kuinka monta palikkariviä pelialueella on.
     * @return Palikkarivien määrä.
     */
    public int palikkarivienMaara()
    {
        return lisattyja.size();
    }
    
    /** Kertoo kuinka monta palikkaa rivillä on.
     * @param rivinumero Rivi, jonka palikkamäärä tahdotaan tietää.
     * @return Palikoiden määrä rivillä.
     */
    public int palikoitaRivilla(float rivinumero)
    {
        rivinumero = korjattuY(rivinumero);
        return !lisattyja.containsKey(rivinumero) ? 0 : lisattyja.get(rivinumero);
    }
    
    public boolean riviOnTaysi(float rivinumero)
    {
        return lisattyja.get(korjattuY(rivinumero)) == alue.leveys();
    }
}
