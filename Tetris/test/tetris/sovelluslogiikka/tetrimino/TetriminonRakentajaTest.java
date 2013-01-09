
package tetris.sovelluslogiikka.tetrimino;

import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Vari;
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
        rakentaja.luoTyypillinenTetrisPalikka(new Sijainti(2, 5), 4);

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
    
    @Test public void tetriminojenVarjaaminenOnnistuu()
    {
        Vari vari = new Vari(255, 0, 0, 255);
        
        rakentaja.luoTyypillinenTetrisPalikka(new Sijainti(5, 3), 4);
        rakentaja.varita(vari);
        Tetrimino tetrimino = rakentaja.rakennettuTetrimino();
        
        assertTrue( ((TetrisPalikka)tetrimino.palikkakokoelma().palikat().get(0)).vari().equals(vari) );
    }
}
