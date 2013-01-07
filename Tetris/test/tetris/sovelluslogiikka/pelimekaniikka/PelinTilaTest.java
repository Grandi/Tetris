
package tetris.sovelluslogiikka.pelimekaniikka;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelinTilaTest
{
    private PelinTila tila;
    
    @Before public void setUp()
    {
        tila = new PelinTila();
    }
    
    @Test public void alussaTunnisteetOvatNollissa()
    {
        assertEquals(0, tila.arvo(PelinTila.Tunniste.PISTEET));
    }
    
    @Test public void arvojenAsettaminenToimii()
    {
        tila.aseta(PelinTila.Tunniste.PISTEET, 8);
        assertEquals(8, tila.arvo(PelinTila.Tunniste.PISTEET));
    }
}
