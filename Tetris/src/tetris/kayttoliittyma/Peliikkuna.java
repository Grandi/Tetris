
package tetris.kayttoliittyma;

import javax.swing.JFrame;
import tetris.sovelluslogiikka.sekalaiset.Alue;

/** Ikkuna, jossa peli pyörii.
 * @author grandi
 */
public class Peliikkuna extends JFrame
{
    private PelitilanteenPiirtajaSwing pelitilanteenPiirtaja;
    private Ohjaus ohjaus;
    
    /**
     * @param ohjaus Ohjaus-olio, joka kontrolloi peliä.
     */
    public Peliikkuna(Ohjaus ohjaus)
    {
        this.ohjaus = ohjaus;
        
        lisaaKomponentteja();
        
        setTitle("Tetris");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(320 + 32, 640 + 64);
        setResizable(false);
    }
    
    private void lisaaKomponentteja()
    {
        addKeyListener(new NappaimistonLukija(ohjaus));
        
        pelitilanteenPiirtaja = new PelitilanteenPiirtajaSwing(new Alue(0, 0, 11, 20));
        add(pelitilanteenPiirtaja);

        ohjaus.asetaPelitilanteenPiirtaja(pelitilanteenPiirtaja);
    }
}
