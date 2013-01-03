
package tetris.sovelluslogiikka.tetrimino;

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
        Palikka ensimmainen = tetrimino.palikkakokoelma().palikat().get(palikanNumero);        
        
        assertTrue(sijainti.onSamaKuin(ensimmainen.sijainti()));
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
        
        assertTrue(((TetriminoPalikka)tetrimino.palikkakokoelma().palikat().get(0)).alkuperainenSijainti().onSamaKuin(new Sijainti(3, 2)));
        assertTrue(((TetriminoPalikka)tetrimino.palikkakokoelma().palikat().get(1)).alkuperainenSijainti().onSamaKuin(new Sijainti(2, 2)));
    }
    
    @Test public void kaantelyToimiiAskeleenMyotapaivaan()
    {
        alustele();
        
        tetrimino.kaannaMyotapaivaan();
        
        kokeileOnkoSijainnissa(0, new Sijainti(2, 3));
        kokeileOnkoSijainnissa(1, new Sijainti(2, 2));
    }
    
    @Test public void kaantelyToimiiAskeleenVastapaivaan()
    {
        alustele();
        
        tetrimino.kaannaVastapaivaan();
        
        kokeileOnkoSijainnissa(0, new Sijainti(2, 1));
        kokeileOnkoSijainnissa(1, new Sijainti(2, 2));
    }
    
    @Test public void kaantelyToimiiKaksikinAskelta()
    {
        alustele();
        
        tetrimino.kaannaMyotapaivaan();
        tetrimino.kaannaMyotapaivaan();
        
        kokeileOnkoSijainnissa(0, new Sijainti(1, 2));
        kokeileOnkoSijainnissa(1, new Sijainti(2, 2));
    }
    
    @Test public void toStringToimii()
    {
        TetriminoPalikka palikka = new TetriminoPalikka(new Sijainti(2, 5), tetrimino);
        
        assertEquals("TetriminoPalikka @ (2,5)", palikka.toString());
    }
}
