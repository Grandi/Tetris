
package tetris.sovelluslogiikka.sekalaiset;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlueTest
{    
    private Alue alue;
    
    @Before public void setUp()
    {
        alue = new Alue(new Sijainti(1, 3), new Sijainti(4, 5));
    }
    
    @Test public void pisteetAsettuivatKonstruktorissaOikeiksi()
    {
        assertTrue(new Sijainti(1, 3).equals(alue.alkupiste()));
        assertTrue(new Sijainti(4, 5).equals(alue.paatepiste()));
    }
    
    @Test public void leveysJaKorkeusToimivat()
    {
        assertEquals(3, alue.leveys(), 1.0);
        assertEquals(2, alue.korkeus(), 1.0);
    }
    
    @Test public void myosVaihtoehtoinenKonstruktoriToimii()
    {
        Alue uusi = new Alue(new Sijainti(1, 3), 3, 2);
        
        assertTrue(uusi.alkupiste().equals(alue.alkupiste()));
        assertTrue(uusi.paatepiste().equals(alue.paatepiste()));
    }
    
    @Test public void sisallaOleminenToimii()
    {
        assertTrue(alue.onSisalla(new Sijainti(2, 4)));
        assertFalse(alue.onSisalla(new Sijainti(0, 4)));
    }
    
    @Test public void toStringToimii()
    {
        assertEquals("Alue [(1.0,3.0),(4.0,5.0)]",alue.toString());
    }
}
