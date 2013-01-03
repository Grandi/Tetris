
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
        assertEquals(5, sijainti.x());
        assertEquals(-2, sijainti.y());
    }
    
    @Test public void vertailuToimii()
    {
        assertTrue(new Sijainti(5, -2).onSamaKuin(sijainti));
        assertFalse(new Sijainti(3, -2).onSamaKuin(sijainti));
    }
    
    @Test public void sijaintejaVoiKopioida()
    {
        Sijainti toinen = new Sijainti(sijainti);
        
        assertTrue(toinen.onSamaKuin(sijainti));
        assertFalse(toinen == sijainti);
    }
    
    @Test public void yksittaisetAsettamisetToimivat()
    {
        sijainti.asetaX(-1);
        sijainti.asetaY(4);
        
        assertTrue(new Sijainti(-1, 4).onSamaKuin(sijainti));
    }
    
    @Test public void toStringToimii()
    {
        assertEquals("(5,-2)", sijainti.toString());
    }
}
