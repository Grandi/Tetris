
package tetris.sovelluslogiikka.tormaystarkastelu;

import tetris.sovelluslogiikka.sekalaiset.Suunta;
import tetris.Apufunktiot;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetrisTormaysTest
{
    private Pelialue pelialue;
    private Tetrimino tetrimino;
    private TetrisTormays tormays;
    
    @Before public void setUp()
    {
        pelialue = new Pelialue(new Sijainti(0, 0), 10, 20);
        
        tetrimino = Apufunktiot.luoTetriminoTestaamistaVarten(new Sijainti(5, 20));
        pelialue.tungePalikka(Apufunktiot.uusiPalikka(6, 20));
        
        tormays = new TetrisTormays(tetrimino, pelialue);
    }
    
    @Test public void tormayksienMaaraOnOikein()
    {
        assertEquals(4, tormays.tormaykset().size());
    }
    
    @Test public void tormayksienSuunnatOvatOikein()
    {
        Suunta[] suunnat = {
            Suunta.ALAS,
            Suunta.YLOS,
            Suunta.VASEN
        };
        
        assertTrue(Apufunktiot.sisaltaaSuunnat(tormays, suunnat));
    }
}
