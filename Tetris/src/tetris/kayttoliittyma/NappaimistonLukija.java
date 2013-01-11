
package tetris.kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.sovelluslogiikka.pelimekaniikka.Komento;
import tetris.sovelluslogiikka.sekalaiset.Suunta;

/** Lukee näppäimistösyötettä.
 * @author grandi
 */
public class NappaimistonLukija implements KeyListener
{
    private Ohjaus ohjaus;
    
    /**
     * @param ohjaus Olio, joka hallitsee peliä.
     */
    public NappaimistonLukija(Ohjaus ohjaus)
    {
        this.ohjaus = ohjaus;
    }

    /** Välitetään näppäinpainallukset ohjaus-oliolle.
     * @param nappain Näppäin, joka oli painettu.
     */
    @Override public void keyPressed(KeyEvent nappain)
    {
        switch(nappain.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                ohjaus.annaKomento(Komento.SIIRTO_VASEMMALLE);
                break;
                
            case KeyEvent.VK_RIGHT:
                ohjaus.annaKomento(Komento.SIIRTO_OIKEALLE);
                break;
                
            case KeyEvent.VK_DOWN:
                ohjaus.annaKomento(Komento.PUDOTTAMINEN);
                break;
                
            case KeyEvent.VK_UP:
                ohjaus.annaKomento(Komento.KAANTAMINEN);
                break;
                
            case KeyEvent.VK_P:
                ohjaus.annaKomento(Komento.TAUOTTAMINEN);
                break;
                
            case KeyEvent.VK_N:
                ohjaus.annaKomento(Komento.UUSI_PELI);
                break;
        }
    }

    @Override public void keyReleased(KeyEvent ke) {}
    @Override public void keyTyped(KeyEvent ke) {}
}
