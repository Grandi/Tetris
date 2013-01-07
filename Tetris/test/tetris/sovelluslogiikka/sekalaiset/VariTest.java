
package tetris.sovelluslogiikka.sekalaiset;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VariTest
{
    private Vari vari;
    
    @Before public void setUp()
    {
        vari = new Vari(255, 0, 255, 50);
    }
    
    @Test public void asettaminenJaArvojenHakeminenToimii()
    {
        assertEquals(255, vari.punainen());
        assertEquals(0, vari.vihrea());
        assertEquals(255, vari.sininen());
        assertEquals(50, vari.peittavyys());
    }
    
    @Test public void varejaVoiKopioida()
    {
        Vari kopioitu = new Vari(vari);
        
        assertTrue(kopioitu.equals(vari));
    }
}
