
package tetris.sovelluslogiikka.muutos;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MuutosTest
{
    private MuutosTestaustaVarten muutos1, muutos2;

    @Before public void setUp()
    {
        muutos1 = new MuutosTestaustaVarten();
        muutos2 = new MuutosTestaustaVarten();
    }
    
    @Test public void onAluksiLaukaistuJaEiOleValmis()
    {
        assertTrue(!muutos1.onValmis());
        assertTrue(muutos1.onLaukaistu());
    }
    
    @Test public void laukaisijaksiAsettaminenToimii()
    {
        assertTrue(muutos2.onLaukaistu());
        
        muutos2.asetaLaukaisijaksi(muutos1);
        assertFalse(muutos2.onLaukaistu());
        
        muutos1.paivita();
        assertTrue(muutos2.onLaukaistu());
    }
}
