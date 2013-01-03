
package tetris.sovelluslogiikka.sekalaiset;

import tetris.sovelluslogiikka.sekalaiset.Alue;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
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
        assertTrue(new Sijainti(1, 3).onSamaKuin(alue.alkupiste()));
        assertTrue(new Sijainti(4, 5).onSamaKuin(alue.paatepiste()));
    }
    
    @Test public void leveysJaKorkeusToimivat()
    {
        assertEquals(3, alue.leveys());
        assertEquals(2, alue.korkeus());
    }
    
    @Test public void myosVaihtoehtoinenKonstruktoriToimii()
    {
        Alue uusi = new Alue(new Sijainti(1, 3), 3, 2);
        
        assertTrue(uusi.alkupiste().onSamaKuin(alue.alkupiste()));
        assertTrue(uusi.paatepiste().onSamaKuin(alue.paatepiste()));
    }
    
    @Test public void sisallaOleminenToimii()
    {
        assertTrue(alue.onSisalla(new Sijainti(2, 4)));
        assertFalse(alue.onSisalla(new Sijainti(0, 4)));
    }
    
    @Test public void toStringToimii()
    {
        assertEquals("Alue [(1,3),(4,5)]",alue.toString());
    }
}
