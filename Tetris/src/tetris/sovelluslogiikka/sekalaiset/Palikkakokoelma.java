
package tetris.sovelluslogiikka.sekalaiset;

import java.util.ArrayList;

/** Määrittää palikkakokoelman, eli tietyllä säännönmukaisuudella yhteenniputetun joukon palikoita.
 * @author grandi
 */
public interface Palikkakokoelma
{
    /** Poista kaikki kokoelmassa olevat palikat.
     */
    public void tyhjenna();
    
    /** Poistaa palikan kokoelmasta.
     * @param sijainti Sijainti, josta palikka tahdotaan poistaa.
     * @return False, jos palikan poistaminen ei käy päinsä. Muutoin true.
     */
    public boolean poistaPalikka(Sijainti sijainti);
    
    /** Tunkee palikan kokoelmaan.
     * @param palikka Palikka, joka tahdotaan tunkea.
     * @return False, jos palikan tunkeminen ei onnistu (esim. paikka jo varattu). Muutoin true.
     */
    public boolean tungePalikka(Palikka palikka);
    
    /** Kertoo mikä palikka sijainnissa sijaitsee.
     * @param sijainti Sijainti, jossa sijaitsevasta palikasta ollaan kiinnostuneita.
     * @return Palauttaa viitteen kyseessä olevaan palikkaan. Jos sijainnissa ei ole palikkaa, palautetaan null.
     */
    public Palikka haePalikka(Sijainti sijainti);
    
    /** Palauttaa kokoelmassa olevat palikat ArrayListinä.
     * @return ArrayList, joka sisältää kokoelman palikat.
     */
    public ArrayList<Palikka> palikat();
    
    /** Kertoo montako palikkaa kokoelmassa on.
     * @return Kokoelman palikoiden määrä.
     */
    public int lisattyja();
}
