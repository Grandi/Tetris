
package tetris.sovelluslogiikka.pelialue;

import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Vari;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import org.junit.Before;
import org.junit.Test;
import tetris.Apufunktiot;
import static org.junit.Assert.*;

public class PelialueenRakentajaTest
{
    private PelialueenRakentaja rakentaja;
    private Pelialue pelialue;

    @Before public void setUp()
    {
        pelialue = new Pelialue(new Sijainti(0, 0), 10, 20);
        rakentaja = new PelialueenRakentaja(pelialue);
    }
    
    @Test public void tetriminoVoidaanTunkeaPelialueelle()
    {
        Tetrimino tetrimino = Apufunktiot.luoTetriminoTestaamistaVarten(new Sijainti(6, 7));

        rakentaja.tungePalikat(tetrimino);
        
        for(Palikka palikka : tetrimino.palikkakokoelma().palikat())
            assertTrue(rakentaja.rakennettuPelialue().haePalikka(palikka.sijainti()) != null);
    }
    
    @Test public void rivejaVoidaanEsitayttaa()
    {
        rakentaja.esitaytaRivit(3);
        Pelialue pelialue = rakentaja.rakennettuPelialue();
        
        assertEquals(3, pelialue.palikkarivienMaara());
        
        for(int i = 0; i < 3; i++)
            assertEquals((int)pelialue.alue().leveys(), pelialue.palikoitaRivilla( i));
    }
    
    @Test public void rakennettuPelialueOnOikea()
    {
        assertTrue(pelialue == rakentaja.rakennettuPelialue());
    }
    
    @Test public void pelialueenVoiVarittaa()
    {
        Vari vari = new Vari(255, 0, 0, 255);
        
        rakentaja.esitaytaRivit(1);
        rakentaja.varita(vari);
        
        TetrisPalikka palikka = (TetrisPalikka)pelialue.palikat().get(0);
        assertTrue(palikka.vari().equals(vari));
        
        rakentaja.varita();
    }
}
