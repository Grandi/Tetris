
package tetris.sovelluslogiikka.sekalaiset;

import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SijaintiTest
{
    Sijainti sijainti;
    
    @Before public void setUp()
    {
        sijainti = new Sijainti(5, -2);
    }
    
    @Test public void koordinaatitOvatOikein()
    {
        assertEquals(5, sijainti.x(), 1.0);
        assertEquals(-2, sijainti.y(), 1.0);
    }
    
    @Test public void vertailuToimii()
    {
        assertTrue(new Sijainti(5, -2).equals(sijainti));
        assertFalse(new Sijainti(3, -2).equals(sijainti));
    }
    
    @Test public void sijaintejaVoiKopioida()
    {
        Sijainti toinen = new Sijainti(sijainti);
        
        assertTrue(toinen.equals(sijainti));
        assertFalse(toinen == sijainti);
    }
    
    @Test public void yksittaisetAsettamisetToimivat()
    {
        sijainti.asetaX(-1);
        sijainti.asetaY(4);
        
        assertTrue(new Sijainti(-1, 4).equals(sijainti));
    }
    
    @Test public void toStringToimii()
    {
        assertEquals("(5.0,-2.0)", sijainti.toString());
    }
}
