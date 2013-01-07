
package tetris.sovelluslogiikka.tetrimino;

import tetris.Apufunktiot;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetriminoPalikkakokoelmaTest
{
    private TetriminoPalikkakokoelma kokoelma;
    private Tetrimino tetrimino;
    
    @Before public void setUp()
    {
        tetrimino = new Tetrimino();
        kokoelma = new TetriminoPalikkakokoelma(tetrimino);
    }
    
    @Test public void onAluksiTyhja()
    {
        assertEquals(0, kokoelma.lisattyja());
    }
    
    @Test public void ensimmaisenPalikanLisaysToimii()
    {
        assertTrue(kokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 2)));
        
        assertEquals(1, kokoelma.lisattyja());
        
        TetriminoPalikka tetriminoPalikka = (TetriminoPalikka)kokoelma.palikat().get(0);
        assertTrue(new Sijainti(1, 2).equals(tetriminoPalikka.alkuperainenSijainti()));
    }
    
    @Test public void palikoitaEiVoiTunkeaJosNeEivatOlisiVieressa()
    {
        kokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 2));
        
        assertFalse(kokoelma.tungePalikka(Apufunktiot.uusiPalikka(2, 3)));
        assertFalse(kokoelma.tungePalikka(Apufunktiot.uusiPalikka(0, 1)));
        
        assertEquals(1, kokoelma.lisattyja());
    }
    
    @Test public void palikoitaVoiLisataTetriminosaannonMukaisesti()
    {
        kokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 2));
        
        assertTrue(kokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 3)));
        assertTrue(kokoelma.tungePalikka(Apufunktiot.uusiPalikka(0, 2)));
        
        assertEquals(3, kokoelma.lisattyja());
    }
    
    private void alustele()
    {
        kokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 2));
        kokoelma.tungePalikka(Apufunktiot.uusiPalikka(2, 2));
        kokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 1));
        kokoelma.tungePalikka(Apufunktiot.uusiPalikka(2, 1));
    }

    @Test public void suhteellinenKeskipisteAsettuuOikein()
    {
        alustele();
        
        assertTrue(new Sijainti((float)1.5,(float)1.5).equals(kokoelma.suhteellinenKeskipiste()));
    }
    
    @Test public void palikoitaVoiPoistaa()
    {
        alustele();
        Sijainti sijainti = new Sijainti(2, 1);
        
        assertFalse( kokoelma.haePalikka(sijainti) == null );
        assertTrue(kokoelma.poistaPalikka(sijainti));
        assertTrue( kokoelma.haePalikka(sijainti) == null );
    }
    
    @Test public void palikoitaEiVoiPoistaaMikaliNiidenPoistaminenRikkooTetriminosaannon()
    {
        kokoelma.tungePalikka(Apufunktiot.uusiPalikka(1, 1));
        kokoelma.tungePalikka(Apufunktiot.uusiPalikka(2, 1));
        kokoelma.tungePalikka(Apufunktiot.uusiPalikka(3, 1));
        
        assertFalse(kokoelma.poistaPalikka(new Sijainti(2, 1)));
        
        assertEquals(3, kokoelma.lisattyja());
    }
    
    @Test public void palikoitaEiVoiPoistaaSieltaMissaNiitaEiOle()
    {
        alustele();
        
        assertFalse(kokoelma.poistaPalikka(new Sijainti(4, 8)));
        assertEquals(4, kokoelma.lisattyja());
    }
    
    @Test public void palikatVoiTyhjentaa()
    {
        alustele();
        
        kokoelma.tyhjenna();
        assertEquals(0, kokoelma.lisattyja());
    }
}
