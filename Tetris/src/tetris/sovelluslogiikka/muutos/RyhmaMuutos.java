
package tetris.sovelluslogiikka.muutos;

import java.util.ArrayList;
import java.util.Iterator;

/** Määrittää kokoelman muutoksia, ja hoitaa niiden yhteisestä päivittämisestä.
 * @author grandi
 */
public class RyhmaMuutos extends Muutos
{
    /** Lista muutoksia. */
    private ArrayList<Muutos> muutokset;
    
    public RyhmaMuutos()
    {
        muutokset = new ArrayList<Muutos>();
    }
    
    /** Lisää ryhmän muutoksiin uuden muutoksen.
     * @param muutos Muutos, joka tahdotaan lisätä.
     */
    public void lisaaMuutos(Muutos muutos)
    {
        muutokset.add(muutos);
    }
    
    /** Palauttaa ryhmämuutoksessa olevat muutokset.
     * @return Kokoelmaan lisätyt muutokset.
     */
    public ArrayList<Muutos> muutokset()
    {
        return muutokset;
    }
    
    /** Poistaa kaikki ne muutokset, jotka ovat jo valmiita.
     */
    private void poistaValmistuneet()
    {
        Iterator<Muutos> iteraattori = muutokset.iterator();
        
        while(iteraattori.hasNext())
            if(iteraattori.next().onValmis())
                iteraattori.remove();
    }
    
    /** Antaa ArrayListin kaikista niistä muutoksista, joita tulisi nyt päivittää.
     * @return ArrayList, jossa on muutoksia.
     */
    private ArrayList<Muutos> paivitettavat()
    {
        ArrayList<Muutos> paivitettavat = new ArrayList<Muutos>();
        
        for(Muutos muutos : muutokset)
            if(muutos.onLaukaistu())
                paivitettavat.add(muutos);
        
        return paivitettavat;
    }
    
    @Override public void paivita()
    {
        for(Muutos muutos : paivitettavat())
            muutos.paivita();
        
        poistaValmistuneet();
    }

    /** @return True, jos kaikki kokoelman muutokset ovat valmiita.
     */
    @Override public boolean onValmis()
    {
        return muutokset.isEmpty();
    }
}
