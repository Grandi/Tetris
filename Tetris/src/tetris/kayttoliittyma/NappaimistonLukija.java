
package tetris.kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
                ohjaus.siirraTetriminoa(Suunta.VASEN); 
                break;
                
            case KeyEvent.VK_RIGHT:
                ohjaus.siirraTetriminoa(Suunta.OIKEA);
                break;
                
            case KeyEvent.VK_DOWN:
                ohjaus.pudotaTetrimino();
                break;
                
            case KeyEvent.VK_UP:
                ohjaus.kaannaTetriminoa(Suunta.OIKEA);
                break;
                
            case KeyEvent.VK_P:
                if(ohjaus.pelitilanne().onTauolla())
                    ohjaus.pelitilanne().jatkaPelia();
                else
                    ohjaus.pelitilanne().tauota();
                break;
        }
    }

    @Override public void keyReleased(KeyEvent ke) {}
    @Override public void keyTyped(KeyEvent ke) {}
}
