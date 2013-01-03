
package tetris.sovelluslogiikka.tormaystarkastelu;

import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

public class PalikoidenValinenTormays implements Tormays
{
    private Palikka palikat[];
    private Sijainti vertailupiste;
    
    public PalikoidenValinenTormays(Palikka palikka, Palikka vertailtava, Sijainti vertailupiste)
    {
        this.palikat = new Palikka[] { palikka, vertailtava };
        this.vertailupiste = vertailupiste;
    }
    
    @Override public ArrayList<Tormayssuunta> suunnat()
    {
        ArrayList<Tormayssuunta> suunnat = new ArrayList<Tormayssuunta>();
        
        if(palikat[0].sijainti().y() != vertailupiste.y())
            suunnat.add(palikat[0].sijainti().y() > vertailupiste.y() ? Tormayssuunta.ALHAALLA : Tormayssuunta.YLHAALLA); 
        
        if(palikat[0].sijainti().x() != vertailupiste.x())
            suunnat.add(palikat[0].sijainti().x() < vertailupiste.x() ? Tormayssuunta.VASEMMALLA : Tormayssuunta.OIKEALLA);
        
        return suunnat;
    }
    
    public Palikka[] palikat()
    {
        return palikat;
    }

    /*
    @Override public ArrayList<Sijainti> sijainnit()
    {
        ArrayList<Sijainti> sijainnit = new ArrayList<Sijainti>();
        sijainnit.add(palikat[0].sijainti());
        return sijainnit;
    }
     */
}
