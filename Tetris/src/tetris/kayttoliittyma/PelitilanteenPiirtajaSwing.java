
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
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.pelimekaniikka.Asetukset;
import tetris.sovelluslogiikka.sekalaiset.Vari;

/** Javan Swingillä toteutettu ratkaisu piirtää pelitilanne.
 * @author grandi
 */
public class PelitilanteenPiirtajaSwing extends JPanel implements PelitilanteenPiirtaja
{
    /** Palikkakokoelmat, joita aiotaan piirtää. */
    private ArrayList<Palikkakokoelma> palikkakokoelmat;
    
    /** Pelitilanne, koska on hyvä tietää esim. koska peli on päättynyt. */
    private Pelitilanne pelitilanne;
    
    /** Alue, jolle piirretään. */
    private Alue piirtoalue;
    
    /**
     * @param piirtoalue Alue, jolle piirretään.
     * @param pelitilanne Pelitilanne, jonka mukaan toimitaan.
     */
    public PelitilanteenPiirtajaSwing(Alue piirtoalue, Pelitilanne pelitilanne)
    {
        this.palikkakokoelmat = new ArrayList<Palikkakokoelma>();
        this.piirtoalue = piirtoalue;
        
        setPreferredSize(new Dimension(((int)piirtoalue.leveys() + 1) * 32, ((int)piirtoalue.korkeus() + 1) * 32));
        this.pelitilanne = pelitilanne;
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
        
        ((Graphics2D)grafiikat).setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        piirraPalikat(grafiikat);

        if(pelitilanne.peliOnPaattynyt())
            piirraGameOverRuutu(grafiikat);
        else if(pelitilanne.onTauolla())
            piirraTaukoRuutu(grafiikat);
    }
    
    /** Piirtää vaakasuunnassa keskitettyä tekstiä.
     * @param grafiikat Graphics-olio; piirtämisen kohde.
     * @param x Tekstin keskiosan x-koordinaatti.
     * @param y Tekstin y-koordinaatti.
     * @param teksti Teksti, joka piirretään.
     */
    private void piirraKeskitettyaTekstia(Graphics grafiikat, int x, int y, String teksti)
    {
        grafiikat.drawString(teksti, x - (teksti.length() * ((Graphics2D)grafiikat).getFont().getSize()) / 4, y);
    }
    
    /** Piirtää ruudun siitä, että peli on tauolla.
     * @param grafiikat 
     */
    private void piirraTaukoRuutu(Graphics grafiikat)
    {
        grafiikat.setColor(Color.BLACK);
        grafiikat.setFont(new Font("Arial", Font.BOLD, 48));

        piirraKeskitettyaTekstia(grafiikat, ((int)piirtoalue.leveys() + 1) * 16, 200, "Tauotettu" );

        grafiikat.setFont(new Font("Verdana", Font.BOLD, 18));
        piirraKeskitettyaTekstia(grafiikat, ((int)piirtoalue.leveys()) * 16, 240, "Paina \"P\" Jatkaaksesi." );
    }
    
    /** Tunkee alkunollia luvun eteen, koska se näyttää kivalta. Tulee game over -ruutuun.
     * @param luku Luku, jolle alkunollia tahdotaan.
     * @param pituus Pituus, joka luvun merkkijonoesitykselle tahdotaan.
     * @return Palauttaa alkunollitetun luvun merkkijonoesityksen.
     */
    private String tungeAlkunollia(int luku, int pituus)
    {
        String merkkijono = new Integer(luku).toString();
        StringBuilder rakentaja = new StringBuilder(merkkijono);
        
        while(rakentaja.toString().length() < pituus)
            rakentaja.insert(0, "0");
        
        return rakentaja.toString();
    }
    
    /** Piirtää ruudun merkkinä pelin päättymisestä.
     * @param grafiikat Graphics-olio; piirtämisen kohde.
     */
    private void piirraGameOverRuutu(Graphics grafiikat)
    {
        grafiikat.setColor(Color.BLACK);
        grafiikat.setFont(new Font("Arial", Font.BOLD, 48));
        
        piirraKeskitettyaTekstia(grafiikat, ((int)piirtoalue.leveys() + 1) * 16 + 20, 200, "Peli ohi!" );
        
        grafiikat.setFont(new Font("Verdana", Font.BOLD, 24));
        
        grafiikat.drawString("Pisteet: ", 90, 280);
        grafiikat.drawString("Taso:    ", 90, 310);
        grafiikat.drawString("Rivejä:  ", 90, 340);
        
        int rivit = pelitilanne.arvo(Pelitilanne.Tunniste.RIVIT);
        int ihannepistemaara = rivit/4 * 9 + (rivit % 4 == 0 ? 0 : 2 * (rivit % 4 - 1) + 1);
        
        grafiikat.drawString(
                tungeAlkunollia(pelitilanne.arvo(Pelitilanne.Tunniste.PISTEET), 2) + "/" + tungeAlkunollia(ihannepistemaara, 2),
                220, 280);
        
        grafiikat.drawString(tungeAlkunollia(( pelitilanne.arvo(Pelitilanne.Tunniste.VAIKEUSTASO) + 1), 2), 220, 310);
        grafiikat.drawString(tungeAlkunollia(rivit, 2),              220, 340);        
    }
    
    /** Piirtää kaikki pelin palikat.
     * @param grafiikat Graphics-olio; piirtämisen kohde.
     */
    private void piirraPalikat(Graphics grafiikat)
    {
        for(Palikkakokoelma kokoelma : palikkakokoelmat)
            piirraPalikat(grafiikat, kokoelma);
    }
    
    /** Piirtää kaikki palikkakokoelman palikat.
     * @param grafiikat Graphics-olio; piirtämisen kohde.
     * @param kokoelma Palikkakokoelma, jonka palikat tahdotaan piirtää.
     */
    private void piirraPalikat(Graphics grafiikat, Palikkakokoelma kokoelma)
    {
        try {
            for(Palikka palikka : kokoelma.palikat())
                if(palikka != null)
                    piirra(grafiikat, (TetrisPalikka)palikka);
        } catch(ConcurrentModificationException e) {}
    }
    
    /** Piirtää ruudulle pyöristetyn palikan.
     * @param grafiikat Graphics-olio; piirron kohde.
     * @param x Palikan x-koordinaatti.
     * @param y Palikan y-koordinaatti.
     * @param leveys Palikan leveys.
     * @param korkeus Palikan korkeus.
     */
    private void piirraPyoristettyPalikka(Graphics grafiikat, float x, float y, float leveys, float korkeus)
    {
        grafiikat.fillRect((int)x + 1, (int)y, (int)leveys - 2, (int)korkeus);
        grafiikat.fillRect((int)x, (int)y + 1, (int)leveys, (int)korkeus - 2);
    }
    
    /** Piirtää TetrisPalikan ruudulle.
     * @param grafiikat Graphics-olio; piirron kohde.
     * @param palikka TetrisPalikka, joka piirretään.
     */
    private void piirra(Graphics grafiikat, TetrisPalikka palikka)
    {
        Vari vari = palikka.vari();        
        int peittavyys = !pelitilanne.peliOnPaattynyt() && pelitilanne.onTauolla() ? 20 : vari.peittavyys();

        grafiikat.setColor(new Color(vari.punainen(), vari.vihrea(), vari.sininen(), peittavyys));
        piirraPyoristettyPalikka(grafiikat, palikka.sijainti().x() * 32 + 1, palikka.sijainti().y() * 32 + 1, 30, 30);
    }
}
