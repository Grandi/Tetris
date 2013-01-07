
package tetris.sovelluslogiikka.muutos;

/** Määrittelee muutoksen. Jokin asia muuttuu joksikin jonkin toisen asian seurauksena.
 * @author grandi
 */
public abstract class Muutos
{
    protected Muutos laukaisija;
    
    /** Asettaa laukaisijan, eli muutoksen, jonka valmistuminen laukaisee
     * tässä muutoksessa olevat tapahtumat.
     * @param laukaisija Muutos, jonka valmistumista odotetaan.
     */
    public void asetaLaukaisijaksi(Muutos laukaisija)
    {
        this.laukaisija = laukaisija;
    }
    
    /** Kertoo, jos muutos on laukaistu (käynnistetty), eli sen laukaisija on valmistunut.
     * @return True, jos muutos on laukaistu. Muutoin false.
     */
    public boolean onLaukaistu()
    {
        return laukaisija == null || laukaisija.onValmis();
    }
    
    /** Päivittää muutoksen tapahtumat.
     */
    public abstract void paivita();
    
    /** Kertoo, onko muutos valmistunut.
     * @return True, jos on valmistunut. Muutoin false.
     */
    public abstract boolean onValmis();
}
