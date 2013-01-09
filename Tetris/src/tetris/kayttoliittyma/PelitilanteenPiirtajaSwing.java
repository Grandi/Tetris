
package tetris.kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.swing.JPanel;
import tetris.sovelluslogiikka.pelimekaniikka.Pelitilanne;
import tetris.sovelluslogiikka.pelimekaniikka.PelitilanteenPiirtaja;
import tetris.sovelluslogiikka.sekalaiset.Alue;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.*;
import tetris.sovelluslogiikka.pelimekaniikka.Asetukset;
import tetris.sovelluslogiikka.sekalaiset.Vari;

/** Javan Swingillä toteutettu ratkaisu piirtää pelitilanne.
 * @author grandi
 */
public class PelitilanteenPiirtajaSwing extends JPanel implements PelitilanteenPiirtaja
{
    private ArrayList<Palikkakokoelma> palikkakokoelmat;
    private Pelitilanne pelitilanne;
    private Asetukset asetukset;
    
    private Alue piirtoalue;
    private int palikanLeveys, palikanKorkeus;
    
    /**
     * @param piirtoalue Alue, jolle piirretään. 
     */
    public PelitilanteenPiirtajaSwing(Alue piirtoalue, Pelitilanne pelitilanne, Asetukset asetukset)
    {
        this.palikkakokoelmat = new ArrayList<Palikkakokoelma>();
        this.asetukset = asetukset;
        this.piirtoalue = piirtoalue;
        
        setPreferredSize(new Dimension(((int)piirtoalue.leveys() + 1) * 32, ((int)piirtoalue.korkeus() + 1) * 32));
        
        this.palikanLeveys = 32;
        this.palikanKorkeus = this.palikanLeveys;
        
        this.pelitilanne = pelitilanne;
    }
    
    @Override public void paivitaTilanne()
    {
        setPreferredSize(new Dimension(((int)piirtoalue.leveys() + 1) * 32, ((int)piirtoalue.korkeus() + 1) * 32));
        super.repaint();
    }
    
    @Override public void lisaaSeurattavaPalikkakokoelma(Palikkakokoelma kokoelma)
    {
        palikkakokoelmat.add(kokoelma);
    }   
    
    @Override protected void paintComponent(Graphics grafiikat)
    {
        super.paintComponent(grafiikat);
        
        ((Graphics2D)grafiikat).setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        piirraTausta(grafiikat);
        piirraPalikat(grafiikat);

        if(pelitilanne.peliOnPaattynyt())
            piirraGameOverRuutu(grafiikat);
        else if(pelitilanne.onTauolla())
            piirraTaukoRuutu(grafiikat);
    }
    
    private void piirraKeskitettyaTekstia(Graphics grafiikat, int x, int y, String teksti)
    {
        grafiikat.drawString(teksti, x - (teksti.length() * ((Graphics2D)grafiikat).getFont().getSize()) / 4, y);
    }
    
    private void piirraTaukoRuutu(Graphics grafiikat)
    {
        grafiikat.setColor(Color.BLACK);
        grafiikat.setFont(new Font("Arial", Font.BOLD, 48));

        piirraKeskitettyaTekstia(grafiikat, ((int)piirtoalue.leveys() + 1) * palikanLeveys /2, 200, "Tauotettu" );

        grafiikat.setFont(new Font("Verdana", Font.BOLD, 18));
        piirraKeskitettyaTekstia(grafiikat, ((int)piirtoalue.leveys()) * palikanLeveys /2, 240, "Paina \"P\" Jatkaaksesi." );
    }
    
    private String tungeAlkunollia(int luku, int pituus)
    {
        String merkkijono = new Integer(luku).toString();
        StringBuilder rakentaja = new StringBuilder(merkkijono);
        
        while(rakentaja.toString().length() < pituus)
            rakentaja.insert(0, "0");
        
        return rakentaja.toString();
    }
    
    private void piirraGameOverRuutu(Graphics grafiikat)
    {
        grafiikat.setColor(Color.BLACK);
        grafiikat.setFont(new Font("Arial", Font.BOLD, 48));
        
        piirraKeskitettyaTekstia(grafiikat, ((int)piirtoalue.leveys() + 1) * palikanLeveys /2 + 20, 200, "Peli ohi!" );
        
        grafiikat.setFont(new Font("Verdana", Font.BOLD, 24));
        
        grafiikat.drawString("Pisteet: ", 90, 280);
        grafiikat.drawString("Taso:    ", 90, 310);
        grafiikat.drawString("Rivejä:  ", 90, 340);
        
        grafiikat.drawString(tungeAlkunollia(pelitilanne.arvo(Pelitilanne.Tunniste.PISTEET), 2),            220, 280);
        grafiikat.drawString(tungeAlkunollia(( pelitilanne.arvo(Pelitilanne.Tunniste.VAIKEUSTASO) + 1), 2), 220, 310);
        grafiikat.drawString(tungeAlkunollia(pelitilanne.arvo(Pelitilanne.Tunniste.RIVIT), 2),              220, 340);        
    }

    private void piirraTausta(Graphics grafiikat)
    {
        /*grafiikat.setColor(new Color(50, 50, 50));
        grafiikat.fillRect((int)piirtoalue.alkupiste().x(), (int)piirtoalue.alkupiste().y(),
                ((int)piirtoalue.leveys() + 1) * palikanLeveys + 170, ((int)piirtoalue.korkeus() + 1) * palikanKorkeus);*/
        
        /*grafiikat.setColor(new Color(200, 200, 200));
        grafiikat.fillRect((int)piirtoalue.alkupiste().x(), (int)piirtoalue.alkupiste().y(),
                ((int)piirtoalue.leveys() + 1) * palikanLeveys, ((int)piirtoalue.korkeus() + 1) * palikanKorkeus );*/
    }
    
    private void piirraPalikat(Graphics grafiikat)
    {
        for(Palikkakokoelma kokoelma : palikkakokoelmat)
            piirraPalikat(grafiikat, kokoelma);
    }
    
    private void piirraPalikat(Graphics grafiikat, Palikkakokoelma kokoelma)
    {
        try
        {
            for(Palikka palikka : kokoelma.palikat())
                piirra(grafiikat, (TetrisPalikka)palikka);
        }
        catch(ConcurrentModificationException exception)
        {}
    }
    
    private void piirraPyoristettyPalikka(Graphics grafiikat, float x, float y, float leveys, float korkeus)
    {
        grafiikat.fillRect((int)x + 1, (int)y, (int)leveys - 2, (int)korkeus);
        grafiikat.fillRect((int)x, (int)y + 1, (int)leveys, (int)korkeus - 2);
    }
    
    private void piirra(Graphics grafiikat, TetrisPalikka palikka)
    {
        Vari vari = palikka.vari();        
        int peittavyys = /*pelitilanne.peliOnPaattynyt() ||*/ pelitilanne.onTauolla() ? 20 : vari.peittavyys();
            
        if(asetukset.kayttaaVareja())
            grafiikat.setColor(new Color(vari.punainen(), vari.vihrea(), vari.sininen(), peittavyys));            
        else
            grafiikat.setColor(new Color(50, 50, 50, peittavyys));

        piirraPyoristettyPalikka(grafiikat, palikka.sijainti().x() * palikanLeveys + 1, palikka.sijainti().y() * palikanKorkeus + 1, palikanLeveys - 2, palikanKorkeus - 2);
         
        // Debuggausta, ei tule lopulliseen työhön:
        /*grafiikat.setColor(new Color(200, 100, 100));
        if(palikka instanceof TetriminoPalikka)
        {
            Sijainti k = ((TetriminoPalikka)palikka).omistajaTetrimino().sijainti();
            grafiikat.fillRect((int)k.x() * palikanLeveys -1, (int)k.y() * palikanKorkeus -1, 2, 2);
        }
         */
    }
}
