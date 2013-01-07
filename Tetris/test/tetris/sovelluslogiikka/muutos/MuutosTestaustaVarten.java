
package tetris.sovelluslogiikka.muutos;

/** Tämä on pieni apuluokka, jolla on nopea testata Muutoksien toimivuutta.
 * @author grandi
 */
public class MuutosTestaustaVarten extends Muutos
{
    boolean valmis;
    
    public MuutosTestaustaVarten()
    {
        valmis = false;
    }
    
    @Override public void paivita()
    {
        valmis = true;
    }

    @Override public boolean onValmis()
    {
        return valmis;
    }
}
