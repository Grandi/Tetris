
package tetris.sovelluslogiikka.pelimekaniikka;

import java.util.HashMap;
import tetris.sovelluslogiikka.sekalaiset.Ajastin;

/** Kokoelma erilaisia pelin tilaan liittyviä muuttujia.
 * @author grandi
 */
public class Pelitilanne
{
    /** Tunnisteet muuttujille, jotka vaikuttavat pelin tilaan.
     */
    public static enum Tunniste
    {
        PISTEET, VAIKEUSTASO, RIVIT
    }
    
    /** Hajautustaulu, joka sisältää erilaisia pelin kannalta oleellisia kokonaislukuja. */
    private HashMap<Tunniste, Integer> arvot;
    
    /** Määrittää, onko peli päättynyt. */
    private boolean peliOhi;
    
    /** Määrittää, onko peli tauotettu. */
    private boolean tauotettu;
    
    /** Ajastaa tetriminon tippumisnopeuden. */
    private Ajastin tiputusAjastin;
    
    
    /** Alustaa muuttujat.
     */
    public Pelitilanne()
    {
        arvot = new HashMap<Tunniste, Integer>();
        
        this.peliOhi = false;
        this.tiputusAjastin = new Ajastin();
    }
    
    /** Asettaa pelitilanteeksi alkutilanteen.
     */
    public void alusta()
    {
        arvot.clear();
        peliOhi = false;
        tauotettu = false;
        tiputusAjastin.paivita();
    }
    
    /* Asettaa pelin aloitetuksi. */
    public void aloitaPeli()
    {
        peliOhi = false;
    }
    
    /** Asettaa pelin päättyneeksi. */
    public void paataPeli()
    {
        peliOhi = true;
    }
    
    /** Asettaa pelin tauotetuksi. */
    public void tauota()
    {
        tauotettu = true;
    }
    
    /** Kertoo, onko peli tauolla. */
    public boolean onTauolla()
    {
        return tauotettu;
    }
    
    /** Jatkaa peliä. */
    public void jatkaPelia()
    {
        tauotettu = false;
    }
    
    /** Kertoo, onko peli päättynyt.
     * @return True, jos peli on päättynyt. Muutoin false.
     */
    public boolean peliOnPaattynyt()
    {
        return peliOhi;
    }
    
    /** Asettaa tunnisteen (esimerkiksi pisteet) jonkin arvoiseksi.
     * @param tunniste Tunniste, jonka arvoa tahdotaan muuttaa.
     * @param arvo Arvo, joka tunnisteelle tahdotaan asettaa.
     */
    public void aseta(Tunniste tunniste, int arvo)
    {
        arvot.put(tunniste, arvo);
    }
    
    /** Palauttaa tunnisteen (esimerkiksi pisteet)
     * @param tunniste Tunniste, jonka arvo tahdotaan tietää.
     * @return Tunnisteen arvo. Nolla, jos tunnisteelle ei ole vielä astettu arvoa.
     */
    public int arvo(Tunniste tunniste)
    {
        return arvot == null || !arvot.containsKey(tunniste) ? 0 : arvot.get(tunniste);
    }
    
    /** Palauttaa tiputusajastimen, joka säätelee tetriminojen putoamisviivettä.
     * @return Tiputusajastin.
     */
    public Ajastin tiputusAjastin()
    {
        return tiputusAjastin;
    }
    
    /** Kertoo, voiko pelaaja liikuttaa tai kääntää tetriminoa.
     * @return True, jos pelaaja voi liikuttaa tai kääntää tetriminoa, muutoin false.
     */
    public boolean voiLiikuttaa()
    {
        return !peliOhi && !tauotettu;
    }
}
