
package tetris.sovelluslogiikka.tormaystarkastelu;

import java.util.ArrayList;
import java.util.HashMap;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.pelialue.TetrisPelialue;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;

public class TetrisTormays implements Tormays
{
    private ArrayList<Tormays> tormaykset;
    
    public TetrisTormays(Tetrimino tetrimino, TetrisPelialue pelialue)
    {
        tormaykset = new ArrayList<Tormays>();
        
        for(Palikka palikka : tetrimino.palikkakokoelma().palikat())
            if(pelialue.onSisalla(palikka.sijainti()))
                lisaaPalikoidenValinenTormays(palikka, tetrimino, pelialue);
            else
                tormaykset.add(new AlueTormays(palikka, pelialue)); 
    }
    
    private void lisaaPalikoidenValinenTormays(Palikka palikka, Tetrimino tetrimino, TetrisPelialue pelialue)
    {
        Palikka pelialueelta = pelialue.sisaltaaPalikan(palikka.sijainti());

        if(pelialueelta != null)
            tormaykset.add(new PalikoidenValinenTormays(palikka, pelialueelta, tetrimino.sijainti()));
    }
    
    @Override public ArrayList<Tormayssuunta> suunnat()
    {
        HashMap<Tormayssuunta, Integer> suunnat = new HashMap<Tormayssuunta, Integer>();
        
        for(Tormays tormays : tormaykset)
            for(Tormayssuunta tormayssuunta : tormays.suunnat())
                suunnat.put(tormayssuunta, 1);
        
        return new ArrayList<Tormayssuunta>(suunnat.keySet());
    }
    
    public ArrayList<Tormays> tormaykset()
    {
        return tormaykset;
    }
}
