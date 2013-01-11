
package tetris.sovelluslogiikka.pelimekaniikka;

import java.util.Arrays;
import tetris.sovelluslogiikka.sekalaiset.Vari;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AsetuksetTest
{
    private Asetukset asetukset;
    
    @Before public void setUp()
    {
        asetukset = new Asetukset();
    }
    
    private void kokeileEttaOvatOletusasetuksissaan()
    {
        assertEquals(4, asetukset.palikoidenMaaraTetriminossa());
        assertEquals(0, asetukset.esitaytettavatRivit());
        assertEquals(4, asetukset.aloitusvaikeustaso());
    }
    
    @Test public void ovatAluksiOletusasetuksissaan()
    {
        kokeileEttaOvatOletusasetuksissaan();
        asetukset.asetaEsitaytettavatRivit(3);
        
        asetukset.alustaOletusasetukset();
        kokeileEttaOvatOletusasetuksissaan();
    }
    
    @Test public void asetuksetVoiNollata()
    {
        asetukset.asetaPalikoidenMaaraTetriminossa(5);
        
    }
    
    @Test public void voiAsettaaPalikoidenMaaran()
    {
        asetukset.asetaPalikoidenMaaraTetriminossa(5);
        assertEquals( 5, asetukset.palikoidenMaaraTetriminossa() );
    }
    
    @Test public void eiAnnaAsettaaHupsujaArvojaPalikoidenMaaraksi()
    {
        asetukset.asetaPalikoidenMaaraTetriminossa(2);
        assertEquals( 4, asetukset.palikoidenMaaraTetriminossa() );
        
        asetukset.asetaPalikoidenMaaraTetriminossa(7);
        assertEquals( 4, asetukset.palikoidenMaaraTetriminossa() );
    }
    
    @Test public void voiAsettaaEsitaytettavienRivienMaaran()
    {
        asetukset.asetaEsitaytettavatRivit(3);
        assertEquals(3, asetukset.esitaytettavatRivit());
    }
    
    @Test public void eiAnnaAsettaaHupsujaArvojaEsitaytettaviksiRiveiksi()
    {
        asetukset.asetaEsitaytettavatRivit(-1);
        assertEquals(0, asetukset.esitaytettavatRivit());
        
        asetukset.asetaEsitaytettavatRivit(13);
        assertEquals(0, asetukset.esitaytettavatRivit());
    }
    
    @Test public void voiAsettaaVaripaletin()
    {
        Vari[] varipaletti = new Vari[]
        {
            new Vari(1, 3, 3, 7),
            new Vari(71, 5, 5, 17),
        };
        
        asetukset.asetaVaripaletti(varipaletti);
        assertTrue(Arrays.equals(varipaletti, asetukset.varipaletti()));
    }
    
    @Test public void voiAsettaaAloitusvaikeustason()
    {
        asetukset.asetaAloitusvaikeustaso(-8);
        assertEquals(-8, asetukset.aloitusvaikeustaso());
    }
}
