
package tetris.sovelluslogiikka.pelimekaniikka;

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
    
    @Test public void ovatAluksiOletusasetuksissaan()
    {
        assertEquals(4, asetukset.palikoidenMaaraTetriminossa());
        assertEquals(0, asetukset.esitaytettavatRivit());
        assertTrue( asetukset.kayttaaVareja() );
        assertTrue( asetukset.nayttaaTetriminonPutoamiskohdan() );
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
        
        asetukset.asetaEsitaytettavatRivit(8);
        assertEquals(0, asetukset.esitaytettavatRivit());
    }
    
    @Test public void antaaAsettaaNaytetaankoPalikanPutoamiskohta()
    {
        asetukset.naytaPutoamiskohta(false);
        assertFalse(asetukset.nayttaaTetriminonPutoamiskohdan());
    }
    
    @Test public void antaaAsettaaKaytetaankoVareja()
    {
        asetukset.kaytaVareja(false);
        assertFalse( asetukset.kayttaaVareja() );
    }
}
