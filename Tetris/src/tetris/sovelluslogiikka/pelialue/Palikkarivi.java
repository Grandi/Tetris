
package tetris.sovelluslogiikka.pelialue;

import java.util.ArrayList;
import java.util.HashMap;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Valimatkapaivittaja;

public class Palikkarivi implements Palikkakokoelma
{
    private HashMap<Integer, Palikka> palikat;
    private int pituus;
    private Valimatkapaivittaja valimatkapaivittaja;
    
    public Palikkarivi(int pituus)
    {
        this.pituus = pituus;
        this.palikat = new HashMap<Integer, Palikka>();
        this.valimatkapaivittaja = new Valimatkapaivittaja();
    }
    
    private boolean onSamassaLinjassa(Sijainti sijainti)
    {
        if(palikat.isEmpty())
            return true;

        return palikat().get(0).sijainti().y() == sijainti.y();
    }
    
    public boolean onRivinSisalla(Sijainti sijainti)
    { 
        return valimatkapaivittaja.suurinValimatka(sijainti) <= pituus;
    }

    private void lisaaPalikka(Palikka palikka)
    {
        palikat.put(palikka.sijainti().x(), palikka);
        valimatkapaivittaja.huomioi(palikka.sijainti());
    }
    
    public boolean onTaysi()
    {
        return palikat.size() == pituus;
    }

    @Override public ArrayList<Palikka> palikat()
    {
        return new ArrayList<Palikka>(palikat.values());
    }
    
    @Override public boolean tungePalikka(Palikka palikka)
    {
        if(!onSamassaLinjassa(palikka.sijainti()) || !onRivinSisalla(palikka.sijainti()) || palikat.containsKey(palikka.sijainti().x()))
            return false;
        
        lisaaPalikka(palikka);
        return true;
    }
    
    @Override public Palikka sisaltaaPalikan(Sijainti sijainti)
    {
        if(!onSamassaLinjassa(sijainti) || !onRivinSisalla(sijainti))
            return null;
        
        return palikat.get(sijainti.x());
    }
    
    @Override public int lisattyja()
    {
        return palikat.size();
    }
}
