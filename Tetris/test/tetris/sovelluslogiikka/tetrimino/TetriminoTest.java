
package tetris.sovelluslogiikka.tetrimino;

import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetriminoTest
{
    private Tetrimino tetrimino;
    
    @Before public void setUp()
    {
        tetrimino = new Tetrimino();
    }
    
    private void onHyvassaAsennossa()
    {
        assertTrue(0 <= tetrimino.asento() && tetrimino.asento() <= 3);
    }
    
    @Test public void alussaOnHyvassaAsennossa()
    {
        onHyvassaAsennossa();
    }
    
    @Test public void kaantyyMyotapaivaan()
    {
        tetrimino.kaannaMyotapaivaan();
        assertEquals(1, tetrimino.asento());
    }
    
    @Test public void kaantyyVastapaivaan()
    {
        tetrimino.kaannaVastapaivaan();
        assertEquals(3, tetrimino.asento());
    }
    
    @Test public void kaantyyOikeinMyotapaivaan()
    {
        for(int i = 0; i < 4; i++)
        {
            tetrimino.kaannaMyotapaivaan();
            onHyvassaAsennossa();
        }
    }
    
    @Test public void kaantyyOikeinVastapaivaan()
    {
        for(int i = 0; i < 4; i++)
        {
            tetrimino.kaannaVastapaivaan();
            onHyvassaAsennossa();
        }
    }
    
    @Test public void asennonVoiUudelleenasettaa()
    {
        tetrimino.laitaAsentoon(2);
        assertEquals(2, tetrimino.asento());
    }
    
    @Test public void asennonUudelleenasettaminenHuolehtiiRajaarvoista()
    {
        tetrimino.laitaAsentoon(4);
        assertEquals(0, tetrimino.asento());
        
        tetrimino.laitaAsentoon(-1);
        assertEquals(3, tetrimino.asento());
    }
}
