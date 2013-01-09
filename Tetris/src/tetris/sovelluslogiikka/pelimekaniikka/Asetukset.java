
package tetris.sovelluslogiikka.pelimekaniikka;

/** Pitää sisällään joukon pelin asetuksia.
 * @author grandi
 */
public class Asetukset
{
    /** Määrittää käytetäänkö ohjelmassa värejä. */
    private boolean kaytaVareja;
    
    /** Määrittää näytetäänkö tetriminon putoamiskohta (eli "aavetetrimino"). */
    private boolean naytaTetriminonPutoamiskohta;
    
    /** Määrittää esitäytettävien palikkarivien määrän. */
    private int esitaytettavat;
    
    /** Määrittää tetriminossa olevien palikoiden määrän. */
    private int palikoidenMaara;
    
    /** Alustaa oletusasetukset.
     */
    public Asetukset()
    {
        kaytaVareja = true;
        esitaytettavat = 0;
        naytaTetriminonPutoamiskohta = true;
        palikoidenMaara = 4;
    }
    
    /** Kertoo, käytetäänkö pelissä värejä. */
    public boolean kayttaaVareja()
    {
        return kaytaVareja;
    }
        
    /** Kertoo, näytetäänkö tetriminon putoamiskohta. */
    public boolean nayttaaTetriminonPutoamiskohdan()
    {
        return naytaTetriminonPutoamiskohta;
    }
    
    /** Kertoo kuinka monta esitäytettyä riviä luodaan pelin alussa. */
    public int esitaytettavatRivit()
    {
        return esitaytettavat;
    }
    
    /** Kertoo kuinka monta palikkaa tetriminossa on. */
    public int palikoidenMaaraTetriminossa()
    {
        return palikoidenMaara;
    }
    
    /** Asettaa sen, käytetäänkö pelissä värejä.
     * @param jokoTai Jos true, käytetään. Jos false, ei käytetä.
     */
    public void kaytaVareja(boolean jokoTai)
    {
        kaytaVareja = jokoTai;
    }
    
    /** Asettaa sen, näytetäänkö tetriminon putoamiskohta eli "aavetetrimino".
     * @param jokoTai Jos True, näytetään. Jos false, ei näytetä.
     */
    public void naytaPutoamiskohta(boolean jokoTai)
    {
        naytaTetriminonPutoamiskohta = jokoTai;
    }
    
    /** Asettaa kuinka monta esitäytettävää riviä luodaan pelin alkaessa.
     * @param rivimaara Haluttu rivimäärä. Jos ei ole väliltä 0-7, arvoa ei hyväksytä.
     */
    public void asetaEsitaytettavatRivit(int rivimaara)
    {
        if(rivimaara >= 0 && rivimaara <= 7)
            esitaytettavat = rivimaara;
    }
    
    /** Asettaa kuinka monta palikkaa tetriminoissa on.
     * @param palikkamaara Haluttu palikkamäärä. Jos ei ole väliltä 3-6, arvoa ei hyväksytä.
     */
    public void asetaPalikoidenMaaraTetriminossa(int palikkamaara)
    {
        if(palikkamaara >= 3 && palikkamaara <= 6)
            palikoidenMaara = palikkamaara;
    }
}
