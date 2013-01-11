
package tetris.sovelluslogiikka.pelimekaniikka;

import tetris.sovelluslogiikka.sekalaiset.Vari;

/** Pitää sisällään joukon pelin asetuksia.
 * @author grandi
 */
public class Asetukset
{
    /** Määrittää esitäytettävien palikkarivien määrän. */
    private int esitaytettavat;
    
    /** Määrittää tetriminossa olevien palikoiden määrän. */
    private int palikoidenMaara;
    
    /** Käytössä olevan väripaletin värit. */
    private Vari[] varipaletti;
    
    /** Määrittää mikä vaikeustaso pelin alussa on. */
    private int aloitusvaikeustaso;
    
    /**
     * Alustaa oletusasetukset.
     */
    public Asetukset()
    {
        alustaOletusasetukset();
    }
    
    /**
     * Alustaa oletusasetukset.
     */
    final public void alustaOletusasetukset()
    {
        esitaytettavat = 0;
        aloitusvaikeustaso = 4;
        palikoidenMaara = 4;
        
        varipaletti = new Vari[]
        {
            new Vari(255, 38, 0, 255),
            new Vari(0, 38, 255, 255),
            new Vari(38, 127, 0, 255),
            new Vari(178, 0, 255, 255)
        };
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
    
    /** Asettaa kuinka monta esitäytettävää riviä luodaan pelin alkaessa.
     * @param rivimaara Haluttu rivimäärä. Jos ei ole väliltä 0-7, arvoa ei hyväksytä.
     */
    public void asetaEsitaytettavatRivit(int rivimaara)
    {
        if(rivimaara >= 0 && rivimaara <= 12)
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
    
    /** Asettaa nykyisen väripaletin.
     * @param paletti Uusi paletti, joka tahdotaan käyttöönottaa.
     */
    public void asetaVaripaletti(Vari[] paletti)
    {
        this.varipaletti = paletti;
    }
    
    /** Palauttaa nykyisen väripaletin.
     * @return Väripaletti, joka on tällä hetkellä käytössä.
     */
    public Vari[] varipaletti()
    {
        return varipaletti;
    }
    
    /** Asettaa pelin aloitusvaikeustason.
     * @param vaikeustaso Vaikeustaso, jolla tahdotaan aloittaa. Voi olla negatiivinen.
     */
    public void asetaAloitusvaikeustaso(int vaikeustaso)
    {
        aloitusvaikeustaso = vaikeustaso;
    }
    
    /** Kertoo pelin aloitusvaikeustason.
     * @return Vaikeustaso, jolta peli alkaa.
     */
    public int aloitusvaikeustaso()
    {
        return aloitusvaikeustaso;
    }
}
