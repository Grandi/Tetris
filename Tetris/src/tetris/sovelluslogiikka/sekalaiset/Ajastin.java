
package tetris.sovelluslogiikka.sekalaiset;

/** Laskee yksinkertaisesti kulunutta aikaa, jotta voitaisiin tehdä ajastettuja toimintoja.
 * @author grandi
 */
public class Ajastin
{
    private long aloitus;
    
    /** Päivittää ajastimen tällä hetkellä olevaan aikaan.
     */
    public Ajastin()
    {
        paivita();
    }
    
    /** Päivittää ajastimen tällä hetkellä olevaan aikaan.
     */
    final public void paivita()
    {
        aloitus = System.currentTimeMillis();
    }
    
    /** Kertoo kuinka kauan edellisestä päivityksestä on kulunut.
     * @return Aikaero millisekunteina.
     */
    public long kulunut()
    {
        return System.currentTimeMillis()-aloitus;
    }
    
    /** Kertoo, onko edellisestä päivityksestä kulunut tietty aika.
     * @param aika Aika millisekunteina.
     * @return Palauttaa true, jos edellisestä päivityksestä on kulunut
     * vähintään parametrissa määritelty aika. Muutoin false.
     */
    public boolean onKulunut(long aika)
    {
        return kulunut() >= aika;
    }
}
