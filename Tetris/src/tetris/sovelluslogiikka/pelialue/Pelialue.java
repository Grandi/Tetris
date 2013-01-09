
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
    /** Hajautustaulu; sijainti => sijainnissa oleva palikka. */
    private HashMap<Sijainti, TetrisPalikka> palikat;
    
    /** Hajautustaulu, joka kertoo, kuinka monta palikkaa rivillä on. */
    private HashMap<Float, Integer> lisattyja;
    
    /** Alue, jonka tämä pelialue muodostaa. */
    private Alue alue;
    
    /** Korjaa rivin y-akselin pelialueenmukaisesti. Rivit kasvavat ylöspäin, pelialue alaspäin.
     * @param y Y-akseli, joka tahdotaan kääntää oikeaksi.
     * @return Käännetty y-akseli.
     */
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
    
    /** Päivittää sen kuinka monta palikkaa tietyllä rivillä on.
     * @param rivi Rivi, jonka palikkamäärää tahdotaan muokata.
     * @param muutos Palikoiden määrän muutos, positiivinen tai negatiivinen.
     */
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

        palikat.put(palikka.sijainti(), new TetrisPalikka((TetrisPalikka)palikka));
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
        lisattyja.clear();
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
        return !lisattyja.containsKey(rivinumero) ? 0 : lisattyja.get(rivinumero);
    }
    
    /** Kertoo onko rivi täysi.
     * @param rivinumero Rivi, jonka täytenäoleminen tahdotaan tietää.
     * @return True, jos rivi on täysi. Muuten false.
     */
    public boolean riviOnTaysi(float rivinumero)
    {
        return lisattyja.containsKey(rivinumero) && lisattyja.get(rivinumero) > alue.leveys();
    }
}
