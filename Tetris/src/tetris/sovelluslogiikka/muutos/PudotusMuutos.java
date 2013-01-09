
package tetris.sovelluslogiikka.muutos;

import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;

/** Käsittelee sitä muutosta, kun palikka tippuu pelialueella.
 * @author grandi
 */
public class PudotusMuutos extends Muutos
{
    /** Palikka, joka pudotetaan. */
    private TetrisPalikka palikka;
    
    /** Sijainti, jota kohti palikkaa siirretään. */
    private Sijainti maaranpaa;
    
    /** Palikan alkuperainen sijainti. */     
    private Sijainti alkuperainenSijainti;
    
    /** Pelialue, jolla palikkaa liu'utetaan. */
    private Pelialue pelialue;
    
    /**
     * @param sijainti Sijainti, jossa pudotettava palikka on pelialueella.
     * @param maaranpaa Määränpää, johon palikan olisi kuljettava.
     * @param pelialue Pelialue, jolla pudotettava palikka on.
     */
    public PudotusMuutos(Sijainti sijainti, Sijainti maaranpaa, Pelialue pelialue)
    {
        this.palikka = (TetrisPalikka)pelialue.haePalikka(sijainti);
        this.maaranpaa = maaranpaa;
        this.pelialue = pelialue;
        this.alkuperainenSijainti = sijainti;
    }
    
    /** Kertoo, onko palikka niin lähellä määränpäätä, että se jo oikeastaan on jo siellä.
     * @return True, jos on tarpeeksi lähellä. Muutoin false.
     */
    private boolean onTarpeeksiLahella()
    {
        return Math.abs(palikka.sijainti().x() - maaranpaa.x()) < 0.1 && Math.abs(palikka.sijainti().y() - maaranpaa.y()) < 0.1;
    }

    @Override public void paivita()
    {
        if(onValmis())
            return;
        
        if(onTarpeeksiLahella())
        {
            palikka.asetaSijainniksi(maaranpaa);
            
            pelialue.poistaPalikka(alkuperainenSijainti);
            pelialue.tungePalikka(palikka);
        }
        else
            palikka.asetaSijainniksi(new Sijainti ((palikka.sijainti().x() + maaranpaa.x())/2, (palikka.sijainti().y() + maaranpaa.y())/2));
    }

    @Override public boolean onValmis()
    {
        return maaranpaa.equals(palikka.sijainti());
    }
}
