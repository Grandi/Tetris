
package tetris.sovelluslogiikka.tormaystarkastelu;

import tetris.sovelluslogiikka.sekalaiset.Suunta;
import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Alue;
import tetris.sovelluslogiikka.sekalaiset.Palikka;

/** Määrittää törmäyksen palikan ja alueen kanssa. Ideana on, että palikan tulisi olla
 * alueen sisäpuolella, ja törmääminen on näin ollen alueen rajojen ulkopuolelle päätymistä.
 * @author grandi
 */
public class AlueTormays implements Tormays
{
    /** Palikka, jonka törmäämistä tarkastellaan. */
    private Palikka palikka;
    
    /** Alue, johon törmäämistä tarkastellaan. */
    private Alue alue;
    
    /**
     * @param palikka Palikka, jonka törmäämistä tutkitaan.
     * @param alue Alue, jonka sisäpuolella palikan kuuluisi olla.
     */
    public AlueTormays(Palikka palikka, Alue alue)
    {
        this.palikka = palikka;
        this.alue = alue;
    }
    
    @Override public ArrayList<Suunta> suunnat()
    {
        if(alue.onSisalla(palikka.sijainti()))
            return new ArrayList<Suunta>();

        return paatteleSuunnat();
    }
    
    /** Päättelee suunnat, joissa törmäykset tapahtuivat.
     * @return ArrayList, joka sisältää 0-2 suuntaa.
     */
    private ArrayList<Suunta> paatteleSuunnat()
    {
        ArrayList<Suunta> suunnat = new ArrayList<Suunta>();

        if(vaakasuunnassa() != null)
            suunnat.add(vaakasuunnassa());

        if(pystysuunnassa() != null)
            suunnat.add(pystysuunnassa());
        
        return suunnat;
    }
    
    /** Kertoo törmäyksen suunnan vaakasuunnassa.
     * @return Törmäyksen suunta, tai null, jos törmäystä ei tapahtunut vaakasuunnassa.
     */
    private Suunta vaakasuunnassa()
    {
        if(palikka.sijainti().x() < alue.alkupiste().x())
            return Suunta.VASEN;
        else if(palikka.sijainti().x() > alue.paatepiste().x())
            return Suunta.OIKEA;
        else
            return null;
    }
    
    /** Kertoo törmäyksen suunnan pystysuunnassa.
     * @return Törmäyksen suunta, tai null, jos törmäystä ei tapahtunut pystysuunnassa.
     */
    private Suunta pystysuunnassa()
    {
        if(palikka.sijainti().y() > alue.paatepiste().y())
            return Suunta.ALAS;
        else if(palikka.sijainti().y() < alue.alkupiste().y())
            return Suunta.YLOS;
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
