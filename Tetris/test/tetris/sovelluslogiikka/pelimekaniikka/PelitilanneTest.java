
package tetris.sovelluslogiikka.pelimekaniikka;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelitilanneTest
{
    private Pelitilanne tila;
    
    @Before public void setUp()
    {
        tila = new Pelitilanne();
    }
    
    @Test public void alussaTunnisteetOvatNollissa()
    {
        assertEquals(0, tila.arvo(Pelitilanne.Tunniste.PISTEET));
    }
    
    @Test public void arvojenAsettaminenToimii()
    {
        tila.aseta(Pelitilanne.Tunniste.PISTEET, 8);
        assertEquals(8, tila.arvo(Pelitilanne.Tunniste.PISTEET));
    }
}
