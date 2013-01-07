
package tetris.kayttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import tetris.sovelluslogiikka.pelimekaniikka.PelitilanteenPiirtaja;
import tetris.sovelluslogiikka.sekalaiset.Alue;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Vari;
import tetris.sovelluslogiikka.tetrimino.TetriminoPalikka;

/** Javan Swingillä toteutettu ratkaisu piirtää pelitilanne.
 * @author grandi
 */
public class PelitilanteenPiirtajaSwing extends JPanel implements PelitilanteenPiirtaja
{
    private ArrayList<Palikkakokoelma> palikkakokoelmat;
    private Alue piirtoalue;
    private int palikanLeveys, palikanKorkeus;
    
    /**
     * @param piirtoalue Alue, jolle piirretään. 
     */
    public PelitilanteenPiirtajaSwing(Alue piirtoalue)
    {
        this.palikkakokoelmat = new ArrayList<Palikkakokoelma>();
        this.piirtoalue = piirtoalue;
        setPreferredSize(new Dimension((int)piirtoalue.leveys(), (int)piirtoalue.korkeus()));
        
        this.palikanLeveys = 32;
        this.palikanKorkeus = this.palikanLeveys;
    }
    
    @Override public void paivitaTilanne()
    {
        super.repaint();
    }
    
    @Override public void lisaaSeurattavaPalikkakokoelma(Palikkakokoelma kokoelma)
    {
        palikkakokoelmat.add(kokoelma);
    }   
    
    @Override protected void paintComponent(Graphics grafiikat)
    {
        super.paintComponent(grafiikat);
        
        piirraTausta(grafiikat);
        piirraPalikat(grafiikat);
    }
    
    /** Piirtää taustan. Hieman muutettu väri + Ääriviivat.
     * @param grafiikat Graphics-olio.
     */
    public void piirraTausta(Graphics grafiikat)
    {
        grafiikat.setColor(new Color(50, 50, 50));
        grafiikat.fillRect((int)piirtoalue.alkupiste().x(), (int)piirtoalue.alkupiste().y(),
                (int)piirtoalue.leveys() * palikanLeveys, ((int)piirtoalue.korkeus() + 1) * palikanKorkeus);
        
        grafiikat.setColor(new Color(200, 200, 200));
        grafiikat.fillRect((int)piirtoalue.alkupiste().x() + 1, (int)piirtoalue.alkupiste().y(),
                (int)piirtoalue.leveys() * palikanLeveys - 2, ((int)piirtoalue.korkeus() + 1) * palikanKorkeus - 2);
    }
    
    private void piirraPalikat(Graphics grafiikat)
    {
        for(Palikkakokoelma kokoelma : palikkakokoelmat)
            piirraPalikat(grafiikat, kokoelma);
    }
    
    private void piirraPalikat(Graphics grafiikat, Palikkakokoelma kokoelma)
    {
        for(Palikka palikka : kokoelma.palikat())
            piirra(grafiikat, (TetrisPalikka)palikka);
    }
    
    private void piirraPyoristettyPalikka(Graphics grafiikat, float x, float y, float leveys, float korkeus)
    {
        grafiikat.fillRect((int)x + 1, (int)y, (int)leveys - 2, (int)korkeus);
        grafiikat.fillRect((int)x, (int)y + 1, (int)leveys, (int)korkeus - 2);
    }
    
    private void piirra(Graphics grafiikat, TetrisPalikka palikka)
    {
        Vari vari = palikka.vari();
        Sijainti sijainti = palikka.sijainti();

        grafiikat.setColor(new Color(vari.punainen(), vari.vihrea(), vari.sininen()));
        //grafiikat.setColor(new Color(50, 50, 50));
        
        piirraPyoristettyPalikka(grafiikat, palikka.sijainti().x() * palikanLeveys + 1, palikka.sijainti().y() * palikanKorkeus + 1, palikanLeveys - 2, palikanKorkeus - 2);
         
        // Debuggausta, ei tule lopulliseen työhön:
        grafiikat.setColor(new Color(200, 100, 100));
        if(palikka instanceof TetriminoPalikka)
        {
            Sijainti k = ((TetriminoPalikka)palikka).omistajaTetrimino().sijainti();
            grafiikat.fillRect((int)k.x() * palikanLeveys -1, (int)k.y() * palikanKorkeus -1, 2, 2);
        }
    }
}
