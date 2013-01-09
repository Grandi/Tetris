
package tetris.sovelluslogiikka.muutos;

import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;

/** Käsittelee sitä muutosta, kun palikka poistetaan pelialueelta.
 * @author grandi
 */
public class PoistoMuutos extends Muutos
{
    /** Palikka, joka poistetaan. */
    private TetrisPalikka palikka;
    
    /** Pelialue, jolta palikka poistetaan. */
    private Pelialue pelialue;
    
    /** Määrittää onko poistaminen valmistunut. */
    private boolean valmis;

    /**
     * @param sijainti Poistettavan palikan sijainti.
     * @param pelialue Pelialue, jolta palikka poistetaan.
     */
    public PoistoMuutos(Sijainti sijainti, Pelialue pelialue)
    {
        this.palikka = (TetrisPalikka)pelialue.haePalikka(sijainti);
        this.pelialue = pelialue;
        this.valmis = false;
    }    

    @Override public void paivita()
    {
        if(valmis)
            return;
        
        if(palikka.vari().peittavyys() < 5)
        {
            pelialue.poistaPalikka(palikka.sijainti());
            valmis = true;
        }
        else
            palikka.vari().asetaPeittavyys( (int)((float)palikka.vari().peittavyys() / 1.2) );
    }

    @Override public boolean onValmis()
    {
        return valmis;
    }
}
