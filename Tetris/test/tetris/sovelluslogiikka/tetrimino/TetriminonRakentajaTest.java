
package tetris.sovelluslogiikka.tetrimino;

import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetriminonRakentajaTest
{
    private TetriminonRakentaja rakentaja;
    
    @Before public void setUp()
    {
        rakentaja = new TetriminonRakentaja();
    }
    
    @Test public void tetriminojenSatunnaisgenerointiToimii()
    {
        rakentaja.luoTyypillinenTetrisPalikka();

        assertTrue( rakentaja.rakennettuTetrimino().palikkakokoelma() instanceof TetriminoPalikkakokoelma );
        assertEquals(4, rakentaja.rakennettuTetrimino().palikkakokoelma().lisattyja());
    }
    
    @Test public void mallinkinPohjaltaRakentaminenToimii()
    {
        assertTrue(
          rakentaja.rakennaMallista(new Sijainti(1, 1),
             new boolean[][]
             {
                 { false, true, false, false },
                 { false, true, false, false },
                 { false, true, false, false },
                 { false, true, false, false }
             })
        );
        
        assertTrue( rakentaja.rakennettuTetrimino().palikkakokoelma() instanceof TetriminoPalikkakokoelma );
        assertEquals(4, rakentaja.rakennettuTetrimino().palikkakokoelma().lisattyja());
    }
    
    @Test public void viallinenMalliEiKelpaa()
    {
        assertFalse(
            rakentaja.rakennaMallista(new Sijainti(5, 3),
                new boolean[][]
                {
                    { false, true, false, false },
                    { false, true, false, true },
                    { false, false, true, false }
                })
        );
    }
}
