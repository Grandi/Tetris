
package tetris.sovelluslogiikka.pelimekaniikka;

import java.util.HashMap;
import tetris.sovelluslogiikka.sekalaiset.Ajastin;

/** Kokoelma erilaisia pelin tilaan liittyviä muuttujia.
 * @author grandi
 */
public class PelinTila
{
    /** Tunnisteet muuttujille, jotka vaikuttavat pelin tilaan.
     */
    public static enum Tunniste
    {
        PISTEET
    }
    
    private HashMap<Tunniste, Integer> arvot;
    private boolean peliOhi;
    private Ajastin nappainAjastin, tiputusAjastin;
    
    /** Alustaa muuttujat.
     */
    public PelinTila()
    {
        arvot = new HashMap<Tunniste, Integer>();
        arvot.put(Tunniste.PISTEET, 0);
        
        this.peliOhi = false;
        
        this.nappainAjastin = new Ajastin();
        this.tiputusAjastin = new Ajastin();
    }
    
    /** Asettaa pelin päättyneeksi.
     */
    public void paataPeli()
    {
        peliOhi = true;
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
     * @return Tunnisteen arvo. Nolla, jos tunnisteelle ei ole vielä astettu
     * arvoa, mutta moista tapausta ei pitäisi olla mahdollista tulla.
     */
    public int arvo(Tunniste tunniste)
    {
        if(arvot.containsKey(tunniste))
            return arvot.get(tunniste);
        else
            return 0;
    }
    
    /** Palauttaa näppäinajastimen, joka säätelee kontrollien nopeutta/hitautta.
     * @return Näppäinajastin.
     */
    public Ajastin nappainAjastin()
    {
        return nappainAjastin;
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
        return nappainAjastin.onKulunut(25) && !peliOhi;
    }
}
