
package tetris.sovelluslogiikka.pelimekaniikka;

import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;

/** Määrittää sen, joka ottaa tehtäväkseen pelitilanteen piirtämisen ja päivittämisen.
 * @author grandi
 */
public interface PelitilanteenPiirtaja
{
    /** Päivittää tilanteen.
     */
    public void paivitaTilanne();
    
    /** Lisää uuden palikkakokoelman, jota aletaan seuraamaan ja piirtämään.
     * @param kokoelma Piirrettävä palikkakokoelma.
     */
    public void lisaaSeurattavaPalikkakokoelma(Palikkakokoelma kokoelma);
}
