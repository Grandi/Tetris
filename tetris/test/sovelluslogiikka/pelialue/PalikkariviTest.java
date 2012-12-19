
package sovelluslogiikka.pelialue;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalikkariviTest
{
    Palikkarivi palikkarivi;

    @Before public void setUp()
    {
        palikkarivi = new Palikkarivi(2);
    }
    
    @Test public void onAluksiTyhja()
    {
        assertEquals(0, palikkarivi.lisattyjaPalikoita());
        assertFalse(palikkarivi.onTaysi());
        
        assertFalse(palikkarivi.sisaltaaPalikan(0));
        assertFalse(palikkarivi.sisaltaaPalikan(1));
    }
    
    @Test public void onnistunutPalikanTunkeminenPalauttaaTruen()
    {
        assertTrue(palikkarivi.tungePalikka(0));
    }
    
    @Test public void palikkaLoytyySieltaMihinSeOnLaitettu()
    {
        palikkarivi.tungePalikka(0);
        assertTrue(palikkarivi.sisaltaaPalikan(0));
    }
    
    @Test public void palikoidenLukumaaraLisaantyyAsianmukaisesti()
    {
        palikkarivi.tungePalikka(0);
        assertEquals(1, palikkarivi.lisattyjaPalikoita());
    }
    
    @Test public void palikoitaEiLoydyMihinNiitaEiOleLaitettu()
    {
        palikkarivi.tungePalikka(0);
        assertFalse(palikkarivi.sisaltaaPalikan(1));
    }
    
    @Test public void palikoitaEiVoiTunkeaRivinUlkopuolelle()
    {
        assertFalse(palikkarivi.tungePalikka(-1));
        assertFalse(palikkarivi.tungePalikka(2));
        
        assertFalse(palikkarivi.sisaltaaPalikan(-1));
        assertFalse(palikkarivi.sisaltaaPalikan(2));
        
        assertFalse(palikkarivi.onTaysi());
        assertEquals(0, palikkarivi.lisattyjaPalikoita());
    }
    
    @Test public void useampaaPalikkaaEiVoiLisataSamaanKohtaan()
    {
        palikkarivi.tungePalikka(0);
        assertFalse(palikkarivi.tungePalikka(0));
        
        assertEquals(1, palikkarivi.lisattyjaPalikoita());
        assertFalse(palikkarivi.onTaysi());
    }
    
    @Test public void palikkariviVoiTayttya()
    {
        palikkarivi.tungePalikka(0);
        palikkarivi.tungePalikka(1);
        
        assertTrue(palikkarivi.onTaysi());
        assertEquals(2, palikkarivi.lisattyjaPalikoita());
    }
}
