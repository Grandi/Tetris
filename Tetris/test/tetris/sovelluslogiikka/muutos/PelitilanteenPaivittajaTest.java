
package tetris.sovelluslogiikka.muutos;

import tetris.AjastinTestaustaVarten;
import tetris.sovelluslogiikka.pelimekaniikka.Pelitilanne;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelitilanteenPaivittajaTest
{
    private Pelialue pelialue;
    private Pelitilanne pelitilanne;
    private PelitilanteenPaivittaja paivittaja;
    
    private void taytaRiveja(int maara)
    {
        for(float y = pelialue.alue().paatepiste().y(); y > pelialue.alue().paatepiste().y() - maara; y--)
            for(float x = pelialue.alue().alkupiste().x(); x <= pelialue.alue().paatepiste().x(); x++)
                pelialue.tungePalikka(new TetrisPalikka(new Sijainti(x, y)));
    }
    
    private void kokeileRivimaaranPoistoa(int rivimaara, int pisteoletus)
    {
        assertEquals(0, pelitilanne.arvo(Pelitilanne.Tunniste.RIVIT));
        assertEquals(0, pelitilanne.arvo(Pelitilanne.Tunniste.PISTEET));
        
        taytaRiveja(rivimaara);
        
        paivittaja.paivita();
        while(!paivittaja.onValmis())
            paivittaja.paivita();
        
        assertEquals(pisteoletus, pelitilanne.arvo(Pelitilanne.Tunniste.PISTEET));
        assertEquals(rivimaara, pelitilanne.arvo(Pelitilanne.Tunniste.RIVIT));
        
        assertEquals(0, pelialue.lisattyja());
        assertEquals(0, pelialue.palikoitaRivilla(0));
    }
    
    @Before public void setUp()
    {
        pelialue = new Pelialue(new Sijainti(0, 0), 2, 6);
        pelitilanne = new Pelitilanne();
        
        paivittaja = new PelitilanteenPaivittaja(pelialue, pelitilanne, new AjastinTestaustaVarten());
    }
    
    @Test public void osaaPoistaaYhdenRivin()
    {
        kokeileRivimaaranPoistoa(1, 1);
    }
    
    @Test public void osaaPoistaaKaksiRivia()
    {
        kokeileRivimaaranPoistoa(2, 3);
    }
    
    @Test public void osaaPoistaaKolmeRivia()
    {
        kokeileRivimaaranPoistoa(3, 5);
    }
    
    @Test public void osaaPoistaaNeljaRivia()
    {
        kokeileRivimaaranPoistoa(4, 7);
    }
    
    @Test public void osaaPoistaaViisiRivia()
    {
        kokeileRivimaaranPoistoa(5, 9);
    }
    
    @Test public void tasoEiVaihduJosEiTarpeeksiRiveja()
    {
        assertEquals(0, pelitilanne.arvo(Pelitilanne.Tunniste.VAIKEUSTASO));
        
        paivittaja.vaikutaTilanteeseen(7);
        assertEquals(0, pelitilanne.arvo(Pelitilanne.Tunniste.VAIKEUSTASO));
    }
    
    @Test public void tasoVaihtuuKunTarpeeksiRiveja()
    {
        assertEquals(0, pelitilanne.arvo(Pelitilanne.Tunniste.VAIKEUSTASO));
        
        paivittaja.vaikutaTilanteeseen(5);
        paivittaja.vaikutaTilanteeseen(4);
        
        assertEquals(1, pelitilanne.arvo(Pelitilanne.Tunniste.VAIKEUSTASO));
    }
    
    @Test public void toinenkinKonstruktoriToimii()
    {
        PelitilanteenPaivittaja paivittaja = new PelitilanteenPaivittaja(null, null, null);
    }
    
    @Test public void eiTeeMitaanOutoaTyhjallaPelialueella()
    {
        paivittaja.paivita();
        while(!paivittaja.onValmis())
            paivittaja.paivita();
        
        assertEquals(0, pelitilanne.arvo(Pelitilanne.Tunniste.RIVIT));
        assertEquals(0, pelitilanne.arvo(Pelitilanne.Tunniste.PISTEET));
        assertEquals(0, pelitilanne.arvo(Pelitilanne.Tunniste.VAIKEUSTASO));
    }
}
