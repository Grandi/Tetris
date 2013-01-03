
package tetris.sovelluslogiikka.pelialue;

import tetris.Apufunktiot;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalikkariviTest
{
    private Palikkarivi palikkarivi;

    @Before public void setUp()
    {
        palikkarivi = new Palikkarivi(2);
    }
    
    @Test public void onAluksiTyhja()
    {
        assertEquals(0, palikkarivi.palikat().size());
        assertFalse(palikkarivi.onTaysi());
    }
    
    @Test public void palikoidenTunkeminenToimii()
    {
        Palikka tungettu = Apufunktiot.uusiPalikka(2, 5);
        
        assertTrue( palikkarivi.tungePalikka(tungettu) );
        assertEquals(1, palikkarivi.palikat().size());
        assertTrue( tungettu == palikkarivi.palikat().get(0) );
        assertTrue( tungettu == palikkarivi.sisaltaaPalikan(tungettu.sijainti()) );
    }
    
    @Test public void palikoitaEiVoiTunkeaRivinUlkopuolelle()
    {
        palikkarivi.tungePalikka(Apufunktiot.uusiPalikka(2, 5));
        
        /* Y-koordinaatti on väärä: Ei ole linjassa. */
        assertFalse( palikkarivi.tungePalikka(Apufunktiot.uusiPalikka(2, 6)) );
        
        /* Liian kaukana ensimmäisestä palikasta. */
        assertFalse( palikkarivi.tungePalikka(Apufunktiot.uusiPalikka(-1, 5)) );
        assertFalse( palikkarivi.tungePalikka(Apufunktiot.uusiPalikka(5, 5)) );
        
        assertEquals(1, palikkarivi.palikat().size());
    }
    
    @Test public void kahtaPalikkaaEiVoiTunkeaSamaanPaikkaan()
    {
        palikkarivi.tungePalikka(Apufunktiot.uusiPalikka(2, 5));
        
        assertFalse( palikkarivi.tungePalikka(Apufunktiot.uusiPalikka(2, 5)) );
        assertEquals(1, palikkarivi.palikat().size());
    }
    
    @Test public void voiTayttya()
    {
        palikkarivi.tungePalikka(Apufunktiot.uusiPalikka(2, 5));
        palikkarivi.tungePalikka(Apufunktiot.uusiPalikka(3, 5));
        
        assertEquals( 2, palikkarivi.palikat().size());
        assertTrue( palikkarivi.onTaysi() );
    }
}
