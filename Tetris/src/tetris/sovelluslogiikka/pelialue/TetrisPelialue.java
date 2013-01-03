
package tetris.sovelluslogiikka.pelialue;

import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Alue;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

public class TetrisPelialue extends Alue implements Palikkakokoelma
{
    private ArrayList<Palikkarivi> palikkarivit;
    
    public TetrisPelialue(Sijainti sijainti, int leveys, int korkeus)
    {
        super(sijainti, leveys, korkeus);
        this.palikkarivit = new ArrayList<Palikkarivi>();
    }
    
    private void varmistaRivienRiittavyys(int y)
    {
        while(palikkarivit.size() <= y)
            palikkarivit.add(new Palikkarivi(leveys()));
    }

    @Override public boolean tungePalikka(Palikka palikka)
    {
        if(!onSisalla(palikka.sijainti()))
            return false;
        
        varmistaRivienRiittavyys(palikka.sijainti().y());
        return palikkarivit.get(palikka.sijainti().y()).tungePalikka(palikka);
    }

    @Override public Palikka sisaltaaPalikan(Sijainti sijainti)
    {
        if(!onSisalla(sijainti) || sijainti.y() >= palikkarivit.size())
            return null;
        
        return palikkarivit.get(sijainti.y()).sisaltaaPalikan(sijainti);
    }

    @Override public ArrayList<Palikka> palikat()
    {
        ArrayList<Palikka> palikat = new ArrayList<Palikka>();
        
        for(Palikkarivi palikkarivi : palikkarivit)
            palikat.addAll(palikkarivi.palikat());
        
        return palikat;
    }

    @Override public int lisattyja()
    {
        int lisattyja = 0;
        
        for(Palikkarivi palikkarivi : palikkarivit)
            lisattyja += palikkarivi.lisattyja();
        
        return lisattyja;
    }
}
