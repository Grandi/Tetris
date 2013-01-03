
package tetris.sovelluslogiikka.tetrimino;

import tetris.sovelluslogiikka.tetrimino.TetriminoPalikka;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.tetrimino.TetriminoPalikkakokoelma;
import tetris.Apufunktiot;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetriminoPalikkakokoelmaTest
{
    private TetriminoPalikkakokoelma tetriminoPalikkakokoelma;
    private Tetrimino tetrimino;
    
    @Before public void setUp()
    {
        tetrimino = new Tetrimino();
        tetriminoPalikkakokoelma = new TetriminoPalikkakokoelma(tetrimino);
    }
    
    @Test public void onAluksiTyhja()
    {
        assertEquals(0, tetriminoPalikkakokoelma.lisattyja());
    }
    
    @Test public void ensimmaisenPalikanLisaysToimii()
    {
        assertTrue(tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 2)));
        
        assertEquals(1, tetriminoPalikkakokoelma.lisattyja());
        
        TetriminoPalikka tetriminoPalikka = (TetriminoPalikka)tetriminoPalikkakokoelma.palikat().get(0);
        assertTrue(new Sijainti(1, 2).onSamaKuin(tetriminoPalikka.alkuperainenSijainti()));
    }
    
    @Test public void palikoitaEiVoiTunkeaJosNeEivatOlisiVieressa()
    {
        tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 2));
        
        assertFalse(tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(2, 3)));
        assertFalse(tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(0, 1)));
        
        assertEquals(1, tetriminoPalikkakokoelma.lisattyja());
    }
    
    @Test public void palikoitaEiVoiTunkeaLiianKauaksiEnsimmaisestaPalikasta()
    {
        tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 2));
        tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(2, 2));
        tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(3, 2));
        
        assertFalse(tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(4, 2)));
        assertFalse(tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(3, 3)));
    }
    
    @Test public void palikoitaVoiLisataTetriminosaannonMukaisesti()
    {
        tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 2));
        
        assertTrue(tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 3)));
        assertTrue(tetriminoPalikkakokoelma.tungePalikka(Apufunktiot.uusiPalikka(0, 2)));
        
        assertEquals(3, tetriminoPalikkakokoelma.lisattyja());
    }
}
