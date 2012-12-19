
package sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SijaintiTest
{    
    private Sijainti sijainti;
    
    @Before public void setUp()
    {
        sijainti = new Sijainti(2, 5);
    }
    
    @Test public void koordinaatitOvatOikeat()
    {
        assertEquals(sijainti.x(), 2);
        assertEquals(sijainti.y(), 5);
    }
    
    @Test public void sijaintiAsettuuOikeaksi()
    {
        sijainti.aseta(7, 9);
        assertEquals(sijainti.x(), 7);
        assertEquals(sijainti.y(), 9);
    }
    
    @Test public void myosNegatiivisetArvotToimivat()
    {
        sijainti.aseta(-3, -10);
        assertEquals(sijainti.x(), -3);
        assertEquals(sijainti.y(), -10);
    }
    
    @Test public void sijaintienKopiointiToimii()
    {
        Sijainti uusi = new Sijainti(sijainti);
        
        assertEquals(uusi.x(), sijainti.x());
        assertEquals(uusi.y(), sijainti.y());
    }
}
