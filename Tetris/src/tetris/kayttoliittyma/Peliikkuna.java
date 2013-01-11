
package tetris.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import tetris.sovelluslogiikka.pelimekaniikka.Komento;
import tetris.sovelluslogiikka.sekalaiset.Alue;

/** Ikkuna, jossa peli pyörii.
 * @author grandi
 */
public class Peliikkuna extends JFrame
{
    /** Pelitilanne piirretään peli-ikkunaan. */
    private PelitilanteenPiirtajaSwing pelitilanteenPiirtaja;
    
    /** Ikkunan yläosassa oleva valikko. */
    private JMenuBar valikko;
    
    /** Pelin ohjaus-olio. */
    private Ohjaus ohjaus;
    
    /** Asetusikkuna käynnistetään peli-ikkunan valikosta. */
    private Asetusikkuna asetusikkuna;
    
    /**
     * @param ohjaus Ohjaus-olio, joka kontrolloi peliä.
     */
    public Peliikkuna(Ohjaus ohjaus, Asetusikkuna asetusikkuna)
    {
        this.ohjaus = ohjaus;
        this.asetusikkuna = asetusikkuna;

        lisaaKomponentteja();
        pack();
        
        setTitle("Tetris");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    /** Lisää käyttöliittymäkomponentteja ikkunaan.
     */
    private void lisaaKomponentteja()
    {
        addKeyListener(new NappaimistonLukija(ohjaus));
        
        luoValikko();
        
        pelitilanteenPiirtaja = new PelitilanteenPiirtajaSwing(new Alue(0, 0, 10, 20), ohjaus.pelitilanne());
        add(pelitilanteenPiirtaja);

        ohjaus.asetaPelitilanteenPiirtaja(pelitilanteenPiirtaja);
    }
    
    /** Luo valikon peli-ikkunan yläosaan. Tein tapahtumakuuntelijat anonyymeillä luokilla, koska
     *  niiden actionPerformedit ovat vain pari riviä. Ei jaksa tehdä luokkia turhaan sellaisille.
     */
    private void luoValikko()
    {
        valikko = new JMenuBar();
        
        JMenu valikkokohta1 = new JMenu("Peli");
        JMenu valikkokohta2 = new JMenu("Asetukset");
        
        lisaaValikkoonToiminto("Uusi peli (N)", valikkokohta1,
           new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) {
                    ohjaus.annaKomento(Komento.UUSI_PELI);
                }
           }  
        );
        
        lisaaValikkoonToiminto("Tauko (P)", valikkokohta1,
           new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) {
                    ohjaus.annaKomento(Komento.TAUOTTAMINEN);
                }
           }  
        );        
        
        lisaaValikkoonToiminto("Lopeta peli", valikkokohta1,
             new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) {
                    System.exit(0);
                }
             }   
        );
        
        lisaaValikkoonToiminto("Muuta asetuksia", valikkokohta2,
            new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae)
                {    
                    if(asetusikkuna == null)
                        asetusikkuna = new Asetusikkuna(ohjaus.asetukset());
                    else if(!asetusikkuna.isVisible())
                        asetusikkuna.setVisible(true);
                }
            });
        
        lisaaValikkoonToiminto("Palauta oletusasetukset", valikkokohta2,
            new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) {
                    asetusikkuna.asetukset().alustaOletusasetukset();
                }
            });        
        
        valikko.add(valikkokohta1);
        valikko.add(valikkokohta2);
        
        this.setJMenuBar(valikko);
    }
    
    /** Lisää valikkoon uuden toiminnon.
     * @param teksti Teksti, joka valikkopainikkeessa on.
     * @param valikkokohta Kohta valikolla (JMenu), johon painike tungetaan.
     * @param tapahtumakuuntelija Tapahtuma, joka painikkeen painamisesta seuraa.
     */
    private void lisaaValikkoonToiminto(String teksti, JMenu valikkokohta, ActionListener tapahtumakuuntelija)
    {
        JMenuItem valikkopainike = new JMenuItem(teksti);
        valikkopainike.addActionListener(tapahtumakuuntelija);
        valikkokohta.add(valikkopainike);
    }
}
