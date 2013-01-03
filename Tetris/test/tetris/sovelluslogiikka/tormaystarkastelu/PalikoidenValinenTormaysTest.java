
package tetris.sovelluslogiikka.tormaystarkastelu;

import tetris.sovelluslogiikka.tormaystarkastelu.Tormayssuunta;
import tetris.sovelluslogiikka.tormaystarkastelu.PalikoidenValinenTormays;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import org.junit.Before;
import org.junit.Test;
import tetris.Apufunktiot;
import static org.junit.Assert.*;

public class PalikoidenValinenTormaysTest
{   
    @Before public void setUp() {}
    
    private void testaaAntaakoOikeatSuunnat(Sijainti tormayksenSijainti, Sijainti vertailupiste, Tormayssuunta[] vaaditut)
    {
        Palikka palikka = new Palikka(tormayksenSijainti);
        PalikoidenValinenTormays tormays = new PalikoidenValinenTormays(palikka, palikka, vertailupiste);
        
        assertTrue(Apufunktiot.sisaltaaSuunnat(tormays, vaaditut));
    }
    
    @Test public void antaaOikeatSuunnat()
    {
        Tormayssuunta[] vaaditut = new Tormayssuunta[] { Tormayssuunta.VASEMMALLA, Tormayssuunta.YLHAALLA };
        testaaAntaakoOikeatSuunnat(new Sijainti(1, 1), new Sijainti(2, 2), vaaditut);
        
        vaaditut = new Tormayssuunta[] { Tormayssuunta.OIKEALLA, Tormayssuunta.ALHAALLA };
        testaaAntaakoOikeatSuunnat(new Sijainti(3, 3), new Sijainti(2, 2), vaaditut);
    }
    
    @Test public void eiAnnaSuuntaaMikaliVertailupisteOnSamassaKohtaaTormayksenKanssa()
    {
        testaaAntaakoOikeatSuunnat(new Sijainti(1, 1), new Sijainti(1, 1), new Tormayssuunta[] {});
    }
    
    @Test public void tormayksenOsapuoletOvatOikein()
    {
        Palikka ensimmainen = Apufunktiot.uusiPalikka(1, 4);
        Palikka toinen = Apufunktiot.uusiPalikka(1, 4);
        
        PalikoidenValinenTormays tormays = new PalikoidenValinenTormays(ensimmainen, toinen, new Sijainti(1, 4));
       
        assertEquals(2, tormays.palikat().length);
        assertTrue(ensimmainen == tormays.palikat()[0]);
        assertTrue(toinen == tormays.palikat()[1]);
    }
}
