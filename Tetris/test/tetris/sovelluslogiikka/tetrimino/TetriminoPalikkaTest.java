
package tetris.sovelluslogiikka.tetrimino;

import tetris.sovelluslogiikka.sekalaiset.Suunta;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import org.junit.Before;
import org.junit.Test;
import tetris.Apufunktiot;
import static org.junit.Assert.*;

public class TetriminoPalikkaTest
{
    private Tetrimino tetrimino;
    
    @Before public void setUp()
    {
        tetrimino = new Tetrimino();
    }
    
    private void kokeileOnkoSijainnissa(int palikanNumero, Sijainti sijainti)
    {
        Sijainti oikea = tetrimino.palikkakokoelma().palikat().get(palikanNumero).sijainti();        
        
        oikea.asetaX((int)oikea.x());
        oikea.asetaY((int)oikea.y());
        
        assertTrue(sijainti.equals(oikea));
    }

    @Test public void palikatOvatOikeinAlkusijainneissaan()
    {
        tetrimino.palikkakokoelma().tungePalikka(Apufunktiot.uusiPalikka(3, 2));
        tetrimino.palikkakokoelma().tungePalikka(Apufunktiot.uusiPalikka(3, 3));
        tetrimino.palikkakokoelma().tungePalikka(Apufunktiot.uusiPalikka(4, 2));
        
        kokeileOnkoSijainnissa(0, new Sijainti(3, 2));
        kokeileOnkoSijainnissa(1, new Sijainti(3, 3));
        kokeileOnkoSijainnissa(2, new Sijainti(4, 2));
    }
    
    private void alustele()
    {
        tetrimino.palikkakokoelma().tungePalikka(Apufunktiot.uusiPalikka(3, 2));
        tetrimino.palikkakokoelma().tungePalikka(Apufunktiot.uusiPalikka(2, 2));
    }
    
    @Test public void alkuperainenSijaintiAsettuuOikein()
    {
        alustele();
        
        assertTrue(((TetriminoPalikka)tetrimino.palikkakokoelma().palikat().get(0)).alkuperainenSijainti().equals(new Sijainti(3, 2)));
        assertTrue(((TetriminoPalikka)tetrimino.palikkakokoelma().palikat().get(1)).alkuperainenSijainti().equals(new Sijainti(2, 2)));
    }  
    
    @Test public void kaantelyToimiiAskeleenMyotapaivaan()
    {
        alustele();
        
        tetrimino.kaanna(Suunta.OIKEA);
        
        kokeileOnkoSijainnissa(0, new Sijainti(3, 3));
        kokeileOnkoSijainnissa(1, new Sijainti(3, 2));
    }
    
    @Test public void kaantelyToimiiAskeleenVastapaivaan()
    {
        alustele();
        
        tetrimino.kaanna(Suunta.VASEN);
        
        kokeileOnkoSijainnissa(0, new Sijainti(3, 2));
        kokeileOnkoSijainnissa(1, new Sijainti(3, 3));
    }
    
    @Test public void kaantelyToimiiKaksikinAskelta()
    {
        alustele();
        
        tetrimino.kaanna(Suunta.OIKEA);
        tetrimino.kaanna(Suunta.OIKEA);
        
        kokeileOnkoSijainnissa(0, new Sijainti(2, 2));
        kokeileOnkoSijainnissa(1, new Sijainti(3, 2));
    }
    
    @Test public void toStringToimii()
    {
        alustele();
        
        assertEquals("TetriminoPalikka @ (2.0,2.0)", tetrimino.palikkakokoelma().palikat().get(1).toString());
    }
}
