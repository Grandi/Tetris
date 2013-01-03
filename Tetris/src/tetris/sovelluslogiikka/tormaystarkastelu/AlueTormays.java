
package tetris.sovelluslogiikka.tormaystarkastelu;

import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Alue;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

public class AlueTormays implements Tormays
{
    private Palikka palikka;
    private Alue alue;
    
    public AlueTormays(Palikka palikka, Alue alue)
    {
        this.palikka = palikka;
        this.alue = alue;
    }
    
    @Override public ArrayList<Tormayssuunta> suunnat()
    {
        if(alue.onSisalla(palikka.sijainti()))
            return new ArrayList<Tormayssuunta>();

        return paatteleSuunnat();
    }
    
    private ArrayList<Tormayssuunta> paatteleSuunnat()
    {
        ArrayList<Tormayssuunta> suunnat = new ArrayList<Tormayssuunta>();

        if(vaakasuunnassa() != null)
            suunnat.add(vaakasuunnassa());

        if(pystysuunnassa() != null)
            suunnat.add(pystysuunnassa());
        
        return suunnat;
    }
    
    private Tormayssuunta vaakasuunnassa()
    {
        if(palikka.sijainti().x() < alue.alkupiste().x())
            return Tormayssuunta.VASEMMALLA;
        else if(palikka.sijainti().x() > alue.paatepiste().x())
            return Tormayssuunta.OIKEALLA;
        else
            return null;
    }
    
    private Tormayssuunta pystysuunnassa()
    {
        if(palikka.sijainti().y() > alue.paatepiste().y())
            return Tormayssuunta.ALHAALLA;
        else if(palikka.sijainti().y() < alue.alkupiste().y())
            return Tormayssuunta.YLHAALLA;
        else
            return null;
    }

    /*
    @Override public ArrayList<Sijainti> sijainnit()
    {
        ArrayList<Sijainti> sijainnit = new ArrayList<Sijainti>();
        sijainnit.add( palikka.sijainti() );
        return sijainnit;
    }
    */
}
