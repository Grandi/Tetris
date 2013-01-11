
package tetris.sovelluslogiikka.tormaystarkastelu;

import tetris.sovelluslogiikka.sekalaiset.Suunta;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;

/** Määrittää Tetris-törmäyksen, eli tetriminon ja Tetris-pelialueen välisen törmäyksen.
 * @author grandi
 */
public class TetrisTormays implements Tormays
{
    /** Kokoelma tapahtuneita törmäyksiä. */
    private ArrayList<Tormays> tormaykset;
    
    /** Tarkistaa törmäykset tetriminon ja pelialueen välillä.
     * @param tetrimino Tetrimino, jonka törmäämistä tutkitaan.
     * @param pelialue Pelialue, jonka törmäämistä tutkitaan.
     */
    public TetrisTormays(Tetrimino tetrimino, Pelialue pelialue)
    {
        tormaykset = new ArrayList<Tormays>();
        
        if(tetrimino.palikkakokoelma().lisattyja() <= 0)
            return;
        
        try {
            for(Palikka palikka : tetrimino.palikkakokoelma().palikat())
            {
                if(palikka == null)
                    continue;
                
                if(pelialue.alue().onSisalla(palikka.sijainti()))
                    tarkistaTormaakoPalikkaPelialueeseen(palikka, tetrimino, pelialue);
                else
                    tormaykset.add(new AlueTormays(palikka, pelialue.alue())); 
            }
        } catch(ConcurrentModificationException e) {}
    }

    /** Tarkistaa törmääkö tetriminon palikka pelialueeseen.
     * 
     * @param palikka Palikka, jonka törmäämisestä olemme kiinnostuneita.
     * @param tetrimino Tetrimino, johon palikka kuuluu.
     * @param pelialue Pelialue, johon palikan oletetaan voivan törmätä.
     */
    private void tarkistaTormaakoPalikkaPelialueeseen(Palikka palikka, Tetrimino tetrimino, Pelialue pelialue)
    {
        Palikka pelialueelta = pelialue.haePalikka(palikka.sijainti());

        if(pelialueelta != null)
            tormaykset.add(new PalikoidenValinenTormays(palikka, pelialueelta, tetrimino.sijainti()));
    }
    
    @Override public ArrayList<Suunta> suunnat()
    {
        HashMap<Suunta, Integer> suunnat = new HashMap<Suunta, Integer>();
        
        for(Tormays tormays : tormaykset)
            for(Suunta tormayssuunta : tormays.suunnat())
                suunnat.put(tormayssuunta, 1);
        
        return new ArrayList<Suunta>(suunnat.keySet());
    }
    
    /** Palauttaa listan tapahtuneista törmäyksistä. Sisältää AlueTormays- ja/tai PalikoidenValinenTormays-olioita.
     * @return ArrayList, joka sisältää tapahtuneet törmäykset. Mikäli tyhjä, törmäyksiä ei tapahtunut.
     */
    public ArrayList<Tormays> tormaykset()
    {
        return tormaykset;
    }
}
