
package tetris.sovelluslogiikka.muutos;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RyhmaMuutosTest
{    
    private RyhmaMuutos ryhmaMuutos;

    @Before public void setUp()
    {
        ryhmaMuutos = new RyhmaMuutos();
    }
    
    @Test public void onAluksiTyhja()
    {
        assertEquals(0, ryhmaMuutos.muutokset().size());
    }
    
    @Test public void onValmisOllessaanTyhja()
    {
        assertEquals(0, ryhmaMuutos.muutokset().size());
        assertTrue(ryhmaMuutos.onValmis());
    }
    
    @Test public void ryhmaMuutokseenVoiLisataMuutoksia()
    {
        ryhmaMuutos.lisaaMuutos(new MuutosTestaustaVarten());
        assertEquals(1, ryhmaMuutos.muutokset().size());
        
        ryhmaMuutos.lisaaMuutos(new MuutosTestaustaVarten());
        assertEquals(2, ryhmaMuutos.muutokset().size());
    }
    
    @Test public void ryhmaMuutosOnValmisVainKunSenJasenetOvatValmiita()
    {
        Muutos muutos1 = new MuutosTestaustaVarten();
        Muutos muutos2 = new MuutosTestaustaVarten();
        
        assertFalse(muutos1.onValmis());
        assertFalse(muutos2.onValmis());
        
        ryhmaMuutos.lisaaMuutos(muutos1);
        ryhmaMuutos.lisaaMuutos(muutos2);
        
        assertFalse(ryhmaMuutos.onValmis());
        
        while(!ryhmaMuutos.onValmis())
            ryhmaMuutos.paivita();
        
        assertTrue(muutos1.onValmis());
        assertTrue(muutos2.onValmis());    
        
        assertTrue(ryhmaMuutos.onValmis());
    }
    
    @Test public void muutostenMaaraVaheneeNiidenValmistuessa()
    {
        Muutos muutos1 = new MuutosTestaustaVarten();
        Muutos muutos2 = new MuutosTestaustaVarten();
        muutos2.asetaLaukaisijaksi(muutos1);
        
        ryhmaMuutos.lisaaMuutos(muutos1);
        ryhmaMuutos.lisaaMuutos(muutos2);
        
        assertEquals(2, ryhmaMuutos.muutokset().size());
        ryhmaMuutos.paivita();
        assertEquals(1, ryhmaMuutos.muutokset().size());
        ryhmaMuutos.paivita();
        assertEquals(0, ryhmaMuutos.muutokset().size());
    }
    
    @Test public void muutostenHakeminenToimii()
    {
        Muutos muutos = new MuutosTestaustaVarten();
        ryhmaMuutos.lisaaMuutos(muutos);
        
        assertTrue( muutos == ryhmaMuutos.muutokset().get(0) );
    }
}
