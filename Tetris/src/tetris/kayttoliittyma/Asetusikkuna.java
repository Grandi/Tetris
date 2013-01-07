
package tetris.kayttoliittyma;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/** Luo asetusikkunan, joka tarjoaa asetusten muuttamista. Erittäin keskeneräinen.
 * @author grandi
 */
public class Asetusikkuna extends JFrame
{
    private JComboBox palikoidenMaaranSaataja, esitaytettavienSaataja;
    private JCheckBox aaveTetrimino, varienKaytto;
    private JButton painike;
    
    public Asetusikkuna()
    {
        lisaaKomponentit();
        
        setTitle("Asetuksien säätäminen");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(480, 260);
        setResizable(false);
    }
    
    private void lisaaKomponentit()
    {
        palikoidenMaaranSaataja = new JComboBox();
        lisaaNumeroarvoja(palikoidenMaaranSaataja, 4, 5);
        palikoidenMaaranSaataja.setSelectedIndex(1);
        
        setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        
        panel.setBorder(new EmptyBorder(35, 50, 30, 50) );
        panel.setLayout(new GridLayout(0,2));
        
        panel.add(new JLabel(" Palikoiden määrä:     "));
        panel.add(palikoidenMaaranSaataja);
        
        esitaytettavienSaataja = new JComboBox();
        lisaaNumeroarvoja(esitaytettavienSaataja, 0, 7);
        
        panel.add(new JLabel(" Esitäytetyt rivit: "));
        panel.add(esitaytettavienSaataja);
        
        panel.add(new JLabel(" Näytä putoamiskohta:  "));
        aaveTetrimino = new JCheckBox();
        aaveTetrimino.setSelected(true);
        panel.add(aaveTetrimino);
        
        panel.add(new JLabel(" Käytä värejä:  "));
        varienKaytto = new JCheckBox();
        varienKaytto.setSelected(true);
        panel.add(varienKaytto);
        
        add(panel);
        
        painike = new JButton("Aloita peli!");
        add(painike);
    }
    
    private void lisaaNumeroarvoja(JComboBox kombolaatikko, int alku, int loppu)
    {
        for(int i = alku; i <= loppu; i++)
            kombolaatikko.addItem(i);
    }
}
