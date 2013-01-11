
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
import tetris.sovelluslogiikka.sekalaiset.Vari;

/** Luo asetusikkunan, joka tarjoaa asetusten muuttamista. Erittäin keskeneräinen.
 * @author grandi
 */
public class Asetusikkuna extends JFrame implements ActionListener
{
    private JSlider esitaytettavienSaataja;
    private JComboBox varipaletinValitsija, vaikeustasonValitsija;
    private JCheckBox viisipalikkaisenValitsija;
    private JButton painike;
    
    /** Asetukset, jotka säädetään. */
    private Asetukset asetukset;
    
    public Asetusikkuna(Asetukset asetukset)
    {
        this.asetukset = asetukset;

        lisaaKomponentit();
        
        setTitle("Asetuksien säätäminen");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /** Lisää ikkunaan asetuskomponentit.
     */
    private void lisaaKomponentit()
    {
        setLayout(new GridBagLayout());

        lisaaLomakkeetSisaltavaPaneeli();
        
        painike = new JButton("OK");
        painike.addActionListener(this);
        
        add(painike);
        add( Box.createHorizontalStrut(20) );
    }
    
    /** Lisää ikkunaan paneelin, johon lomakkeet tungetaan.
     */
    private void lisaaLomakkeetSisaltavaPaneeli()
    {
        JPanel paneeli = new JPanel();
        
        paneeli.setBorder(new EmptyBorder(20, 20, 20, 20) );
        paneeli.setLayout(new GridLayout(0,2));

        lisaaEsitaytettavienRivienKyselija(paneeli);
        lisaaVaikeustasonKyselija(paneeli);
        lisaaVaripaletinKyselija(paneeli);
        lisaaPalikoidenMaaranKyselija(paneeli);
        
        add(paneeli);
    }
    
    /** Lisää checkboxin, jolla säädetään, tahdotaanko tetriminoista 5-palikkaisia.
     * @param paneeli Paneeli, johon lisätään.
     */
    private void lisaaPalikoidenMaaranKyselija(JPanel paneeli)
    {
        viisipalikkaisenValitsija = new JCheckBox();
        viisipalikkaisenValitsija.setSelected(asetukset.palikoidenMaaraTetriminossa() == 5);
        
        paneeli.add(new JLabel(" Viisipalikkaiset tetriminot:     "));
        paneeli.add(viisipalikkaisenValitsija);
    }
    
    /** Lisää JSliderin, jolla säädetään, kuinka monta riviä esitäytetään.
     * @param paneeli Paneeli, johon lisätään. 
     */
    private void lisaaEsitaytettavienRivienKyselija(JPanel paneeli)
    {
        esitaytettavienSaataja = new JSlider(JSlider.HORIZONTAL, 0, 12, 0);
        
        esitaytettavienSaataja.setMajorTickSpacing(4);
        esitaytettavienSaataja.setMinorTickSpacing(1);
        esitaytettavienSaataja.setSnapToTicks(true);
        
        esitaytettavienSaataja.setPaintTicks(true);
        esitaytettavienSaataja.setValue(asetukset.esitaytettavatRivit());
        
        paneeli.add(new JLabel(" Esitäytetyt rivit: "));
        paneeli.add(esitaytettavienSaataja);
    }
    
    /** Lisää comboboxin, jolla säädetään aloitusvaikeustasoa.
     * @param paneeli Paneeli, johon lisätään.
     */
    private void lisaaVaikeustasonKyselija(JPanel paneeli)
    {
        vaikeustasonValitsija = new JComboBox(new String[] { "Aloittelija", "Keskiverto", "Mestari" });
        vaikeustasonValitsija.setSelectedIndex(1);
        
        paneeli.add(new JLabel(" Pelin aloitusvaikeustaso:  "));
        paneeli.add(vaikeustasonValitsija);
    }
    
    /** Lisää comboboxin, jolla säädetään mitä väripalettia käytetään.
     * @param paneeli Paneeli, johon lisätään.
     */
    private void lisaaVaripaletinKyselija(JPanel paneeli)
    {
        this.varipaletinValitsija = new JComboBox(new String[] { "Värikäs", "Mustavalkoinen", "Vaaleanpunainen" });
         
        paneeli.add(new JLabel(" Palikoiden väripaletti:     "));
        paneeli.add(varipaletinValitsija);
    }

    /** Kun on OK-painiketta painettu:
     */
    @Override public void actionPerformed(ActionEvent ae)
    {
        asetaVaripalettiValinnanPerusteella();
        asetaAloitusvaikeustasoValinnanPerusteella();
        
        asetukset.asetaEsitaytettavatRivit( esitaytettavienSaataja.getValue() );
        asetukset.asetaPalikoidenMaaraTetriminossa( viisipalikkaisenValitsija.isSelected() ? 5 : 4 );

        setVisible(false);
    }
    
    /** Asettaa asetuksiin äsken säädetyn aloitusvaikeustason.
     */
    private void asetaAloitusvaikeustasoValinnanPerusteella()
    {
        String valinta = ((String)vaikeustasonValitsija.getSelectedItem());
        
        if(valinta.equals("Aloittelija"))
            asetukset.asetaAloitusvaikeustaso(0);
        else if(valinta.equals("Keskiverto"))
            asetukset.asetaAloitusvaikeustaso(4);
        else
            asetukset.asetaAloitusvaikeustaso(8);
    }
    
    /** Asettaa pelin väripaletin siksi miksi se valittiin.
     */
    private void asetaVaripalettiValinnanPerusteella()
    {
        Vari[] paletti = null;
        
        String valinta = ((String)varipaletinValitsija.getSelectedItem());
        if(valinta.equals("Mustavalkoinen"))
            paletti = new Vari[] { new Vari(50, 50, 50, 255) };
        else if(valinta.equals("Vaaleanpunainen"))
            paletti = new Vari[] { new Vari(255, 96, 176, 255) };
        else
            paletti = new Vari[]
            {
                new Vari(255, 38, 0, 255),
                new Vari(0, 38, 255, 255),
                new Vari(38, 127, 0, 255),
                new Vari(178, 0, 255, 255)
            };
        
        asetukset.asetaVaripaletti(paletti);
    }
    
    /** Palauttaa asetukset, joita säädetään/ollaan säädetty.
     * @return Säädettävänä olleet asetukset.
     */
    public Asetukset asetukset()
    {
        return asetukset;
    }
}
