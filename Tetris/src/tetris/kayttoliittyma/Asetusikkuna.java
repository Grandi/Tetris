
package tetris.kayttoliittyma;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import tetris.sovelluslogiikka.pelimekaniikka.Asetukset;

/** Luo asetusikkunan, joka tarjoaa asetusten muuttamista. Erittäin keskeneräinen.
 * @author grandi
 */
public class Asetusikkuna extends JFrame implements ActionListener
{
    private JSlider esitaytettavienSaataja;
    private JComboBox palikoidenMaaranSaataja;
    private JCheckBox aaveTetrimino, varienKaytto, sivupalkinNaytto;
    private JButton painike;
    
    private Asetukset asetukset;
    
    public Asetusikkuna(Asetukset asetukset)
    {
        this.asetukset = asetukset;

        lisaaKomponentit();
        
        setTitle("Asetuksien säätäminen");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //setSize(480, 260);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
    
    private void lisaaKomponentit()
    {
        setLayout(new GridBagLayout());

        lisaaLomakkeetSisaltavaPaneeli();
        
        painike = new JButton("OK");
        painike.addActionListener(this);
        
        add(painike);
        add( Box.createHorizontalStrut(20) );
    }
    
    private void lisaaLomakkeetSisaltavaPaneeli()
    {
        JPanel paneeli = new JPanel();
        
        paneeli.setBorder(new EmptyBorder(20, 20, 20, 20) );
        paneeli.setLayout(new GridLayout(0,2));

        lisaaEsitaytettavienRivienKyselija(paneeli);
        lisaaPutoamiskohdanNayttamisenKyselija(paneeli);
        lisaaVarienKaytonKyselija(paneeli);
        //lisaaSivupalkinNaytonKyselija(paneeli);
        lisaaPalikoidenMaaranKyselija(paneeli);
        
        add(paneeli);
    }
    
    private void lisaaPalikoidenMaaranKyselija(JPanel paneeli)
    {
        palikoidenMaaranSaataja = new JComboBox(new String[] { "4", "5" });
        
        paneeli.add(new JLabel(" Tetriminojen koko:     "));
        paneeli.add(palikoidenMaaranSaataja);
    }
    
    private void lisaaEsitaytettavienRivienKyselija(JPanel paneeli)
    {
        esitaytettavienSaataja = new JSlider(JSlider.HORIZONTAL, 0, 7, 0);
        
        esitaytettavienSaataja.setMajorTickSpacing(10);
        esitaytettavienSaataja.setPaintTicks(true);
        esitaytettavienSaataja.setValue(asetukset.esitaytettavatRivit());
        
        paneeli.add(new JLabel(" Esitäytetyt rivit: "));
        paneeli.add(esitaytettavienSaataja);
    }
    
    private void lisaaPutoamiskohdanNayttamisenKyselija(JPanel paneeli)
    {
        paneeli.add(new JLabel(" Näytä putoamiskohta:  "));
        aaveTetrimino = new JCheckBox();
        aaveTetrimino.setSelected(asetukset.nayttaaTetriminonPutoamiskohdan());
        paneeli.add(aaveTetrimino);
    }
    
    private void lisaaVarienKaytonKyselija(JPanel paneeli)
    {
        paneeli.add(new JLabel(" Käytä värejä:  "));
        varienKaytto = new JCheckBox();
        varienKaytto.setSelected(asetukset.kayttaaVareja());
        paneeli.add(varienKaytto);
    }

    @Override public void actionPerformed(ActionEvent ae)
    {
        asetukset.kaytaVareja( varienKaytto.isSelected() );
        asetukset.naytaPutoamiskohta( aaveTetrimino.isSelected() );
//        asetukset.naytaSivupalkki( sivupalkinNaytto.isSelected() );
        
        asetukset.asetaEsitaytettavatRivit( esitaytettavienSaataja.getValue() );
        
        String palikat = (String)palikoidenMaaranSaataja.getSelectedItem();
        asetukset.asetaPalikoidenMaaraTetriminossa( Integer.parseInt(palikat) );

        setVisible(false);
    }
}
