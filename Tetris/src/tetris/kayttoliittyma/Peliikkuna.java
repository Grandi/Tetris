
package tetris.kayttoliittyma;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import tetris.sovelluslogiikka.sekalaiset.Alue;

/** Ikkuna, jossa peli pyörii.
 * @author grandi
 */
public class Peliikkuna extends JFrame
{
    private PelitilanteenPiirtajaSwing pelitilanteenPiirtaja;
    private JMenuBar valikko;
    private Ohjaus ohjaus;
    
    /**
     * @param ohjaus Ohjaus-olio, joka kontrolloi peliä.
     */
    public Peliikkuna(Ohjaus ohjaus)
    {
        this.ohjaus = ohjaus;
        
        lisaaKomponentteja();
        pack();
        
        //setSize(320 + 32, 640 + 64);
        
        setTitle("Tetris");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //
        setResizable(false);
    }
    
    private void lisaaKomponentteja()
    {
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addKeyListener(new NappaimistonLukija(ohjaus));
        
        luoValikko();
        
        pelitilanteenPiirtaja = new PelitilanteenPiirtajaSwing(new Alue(0, 0, 10, 20), ohjaus.pelitilanne(), ohjaus.asetukset());
        add(pelitilanteenPiirtaja);

        ohjaus.asetaPelitilanteenPiirtaja(pelitilanteenPiirtaja);
    }
    
    private void luoValikko()
    {
        valikko = new JMenuBar();
        
        JMenu valikkokohta1 = new JMenu("Peli");
        JMenu valikkokohta2 = new JMenu("Asetukset");
        
        lisaaValikkoonToiminto('u',"Uusi peli", valikkokohta1,
           new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) {
                    ohjaus.alustaUusiPeli();
                }
           }  
        );
        
        lisaaValikkoonToiminto('l',"Lopeta peli", valikkokohta1,
             new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) {
                    System.exit(0);
                }
             }   
        );
        
        lisaaValikkoonToiminto('a',"Muuta asetuksia", valikkokohta2,
            new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) {
                    new Asetusikkuna(ohjaus.asetukset());
                    ohjaus.pelitilanne().tauota();
                }
            });
        
        valikko.add(valikkokohta1);
        valikko.add(valikkokohta2);
        
        this.setJMenuBar(valikko);
    }
    
    private void lisaaValikkoonToiminto(Character pikanappain, String teksti, JMenu valikkokohta, ActionListener tapahtumakuuntelija)
    {
        JMenuItem valikkopainike = new JMenuItem(teksti);
        valikkopainike.addActionListener(tapahtumakuuntelija);
        valikkokohta.add(valikkopainike);
        
        if(pikanappain != 0)
            valikkokohta.setMnemonic(pikanappain);
    }
}
