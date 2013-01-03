
package tetris.sovelluslogiikka.tormaystarkastelu;

import tetris.Apufunktiot;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.pelialue.TetrisPelialue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetrisTormaysTest
{
    private TetrisPelialue pelialue;
    private Tetrimino tetrimino;
    private TetrisTormays tormays;
    
    @Before public void setUp()
    {
        pelialue = new TetrisPelialue(new Sijainti(0, 0), 10, 20);
        
        tetrimino = Apufunktiot.luoTetriminoTestaamistaVarten(new Sijainti(5, 20));
        pelialue.tungePalikka(Apufunktiot.uusiPalikka(5, 19));
        
        tormays = new TetrisTormays(tetrimino, pelialue);
    }
    
    @Test public void tormayksienMaaraOnOikein()
    {
        assertEquals(3, tormays.tormaykset().size());
    }
    
    @Test public void tormayksienSuunnatOvatOikein()
    {
        Tormayssuunta[] suunnat = {
            Tormayssuunta.ALHAALLA,
            Tormayssuunta.YLHAALLA
        };
        
        assertTrue(Apufunktiot.sisaltaaSuunnat(tormays, suunnat));
    }
}
