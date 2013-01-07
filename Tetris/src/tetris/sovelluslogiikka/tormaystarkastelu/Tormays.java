
package tetris.sovelluslogiikka.tormaystarkastelu;

import tetris.sovelluslogiikka.sekalaiset.Suunta;
import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

/** Määrittää törmäyksen, ja osaa kertoa missä suunnissa se tapahtui.
 * @author grandi
 */
public interface Tormays
{
    /** Palauttaa luettelon törmäyksen suunnista.
     * @return ArrayList, joka sisältää törmäyksen suunnat.
     */
    public ArrayList<Suunta> suunnat();
    //public ArrayList<Sijainti> sijainnit();
}
