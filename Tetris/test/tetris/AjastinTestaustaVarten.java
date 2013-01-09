
package tetris;

import tetris.sovelluslogiikka.sekalaiset.Ajastin;

/** Pieni apuluokka ajastusta vaativien toimenpiteiden testaamiseksi. 
 * @author grandi
 */
public class AjastinTestaustaVarten extends Ajastin
{
    @Override public boolean onKulunut(long maara)
    {
        return true;
    }
    
    @Override public long kulunut()
    {
        return Long.MAX_VALUE;
    }
}
