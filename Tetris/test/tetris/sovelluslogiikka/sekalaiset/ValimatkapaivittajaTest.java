
package tetris.sovelluslogiikka.sekalaiset;

import tetris.sovelluslogiikka.sekalaiset.Valimatkapaivittaja;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValimatkapaivittajaTest
{
    private Valimatkapaivittaja valimatkapaivittaja;
    
    @Before public void setUp()
    {
        valimatkapaivittaja = new Valimatkapaivittaja();
    }
    
    @Test public void valimatkaOnNollaAluksi()
    {
        assertEquals(0, valimatkapaivittaja.suurinValimatka());
        assertEquals(0, valimatkapaivittaja.suurinValimatka(new Sijainti(5, 3)));
    }
    
    @Test public void valimatkaOnOikeinEnsimmaisenLisayksenJalkeen()
    {
        valimatkapaivittaja.huomioi(new Sijainti(4, 1));
        
        assertEquals(0, valimatkapaivittaja.suurinValimatka());
        assertEquals(2, valimatkapaivittaja.suurinValimatka(new Sijainti(5, 3)));
    }
    
    @Test public void valimatkaOnOikeinUseammanLisayksenJalkeen()
    {
        valimatkapaivittaja.huomioi(new Sijainti(4, 1));
        valimatkapaivittaja.huomioi(new Sijainti(5, 3));
        valimatkapaivittaja.huomioi(new Sijainti(3, 0));
        
        assertEquals(3, valimatkapaivittaja.suurinValimatka());
        assertEquals(2, valimatkapaivittaja.suurinValimatka(new Sijainti(5, 2)));
    }
    
    @Test public void suoraValimatkaLaskeentuuOikein()
    {
        assertEquals(2, Valimatkapaivittaja.valimatka(new Sijainti(1, 2), new Sijainti(3, 2)), 1.0);
    }
}
