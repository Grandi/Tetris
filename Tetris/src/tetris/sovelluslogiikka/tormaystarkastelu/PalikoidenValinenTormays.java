
package tetris.sovelluslogiikka.tormaystarkastelu;

import tetris.sovelluslogiikka.sekalaiset.Suunta;
import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

/** Määrittää törmäyksen kahden palikan välillä. Törmäys tarkoittaa, että niiden sijainnit ovat samat.
 * @author grandi
 */
public class PalikoidenValinenTormays implements Tormays
{
    /** Palikat, joiden välistä törmäystä tarkastellaan. */
    private Palikka palikat[];
    
    /** Piste, johon törmäyksen sijaintia verrataan suunnan selvittämiseksi. */
    private Sijainti vertailupiste;
    
    /**
     * @param palikka Palikka, jonka törmäämistä tutkitaan.
     * @param vertailtava Toinen palikka, jonka törmäämistä tutkitaan.
     * @param vertailupiste Piste, josta katsotaan, missä suunnassa törmäys tapahtui.
     */
    public PalikoidenValinenTormays(Palikka palikka, Palikka vertailtava, Sijainti vertailupiste)
    {
        this.palikat = new Palikka[] { palikka, vertailtava };
        this.vertailupiste = vertailupiste;
    }
    
    @Override public ArrayList<Suunta> suunnat()
    {
        ArrayList<Suunta> suunnat = new ArrayList<Suunta>();
        
        if(palikat[0].sijainti().y() != vertailupiste.y())
            suunnat.add(palikat[0].sijainti().y() > vertailupiste.y() ? Suunta.ALAS : Suunta.YLOS); 
        
        if(palikat[0].sijainti().x() != vertailupiste.x())
            suunnat.add(palikat[0].sijainti().x() < vertailupiste.x() ? Suunta.VASEN : Suunta.OIKEA);
        
        return suunnat;
    }
    
    /** Palauttaa törmäyksen osapuolet.
     * @return Taulukko, jossa on kaksi palikkaa.
     */
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
