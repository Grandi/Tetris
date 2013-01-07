
package tetris.sovelluslogiikka.tormaystarkastelu;

import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Suunta;
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
    
    private void testaaAntaakoOikeatSuunnat(Sijainti tormayksenSijainti, Sijainti vertailupiste, Suunta[] vaaditut)
    {
        Palikka palikka = new TetrisPalikka(tormayksenSijainti);
        PalikoidenValinenTormays tormays = new PalikoidenValinenTormays(palikka, palikka, vertailupiste);
        
        assertTrue(Apufunktiot.sisaltaaSuunnat(tormays, vaaditut));
    }
    
    @Test public void antaaOikeatSuunnat()
    {
        Suunta[] vaaditut = new Suunta[] { Suunta.VASEN, Suunta.YLOS };
        testaaAntaakoOikeatSuunnat(new Sijainti(1, 1), new Sijainti(2, 2), vaaditut);
        
        vaaditut = new Suunta[] { Suunta.OIKEA, Suunta.ALAS };
        testaaAntaakoOikeatSuunnat(new Sijainti(3, 3), new Sijainti(2, 2), vaaditut);
    }
    
    @Test public void eiAnnaSuuntaaMikaliVertailupisteOnSamassaKohtaaTormayksenKanssa()
    {
        testaaAntaakoOikeatSuunnat(new Sijainti(1, 1), new Sijainti(1, 1), new Suunta[] {});
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
