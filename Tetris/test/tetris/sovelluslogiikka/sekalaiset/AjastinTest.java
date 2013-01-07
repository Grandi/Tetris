
package tetris.sovelluslogiikka.sekalaiset;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AjastinTest
{
    private Ajastin ajastin;
    
    @Before public void setUp()
    {
        ajastin = new Ajastin();
    }
    
    @Test public void ajastinTietaaKunSeOnYlittanytJonkinAjan()
    {
        assertTrue(ajastin.onKulunut(0));
    }
    
    @Test public void ajastinEiLuuleYlittaneensaAikojaJoitaSeEiOleYlittanyt()
    {
        assertFalse(ajastin.onKulunut(1000 * 60 * 60));
    }
    
    @Test public void ajastimenPaivittaminenToimii()
    {
        long aika = ajastin.kulunut();
        
        for(int i = 0; i < 10; i++);
        
        ajastin.paivita();
        assertFalse(ajastin.kulunut() > aika);
    }
}
