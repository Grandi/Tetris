
package tetris.sovelluslogiikka.pelimekaniikka;

import java.util.ArrayList;
import java.util.Iterator;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tetris.Apufunktiot;
import tetris.sovelluslogiikka.sekalaiset.Suunta;
import static org.junit.Assert.*;

public class AavetetriminoTest
{
    private Aavetetrimino aavetetrimino;
    private Tetrimino tetrimino;
    private Pelialue pelialue;
    
    @Before public void setUp()
    {
        tetrimino = Apufunktiot.luoTetriminoTestaamistaVarten(new Sijainti(20, 20));
        pelialue = new Pelialue(new Sijainti(0, 0), 40, 40);
        aavetetrimino = new Aavetetrimino(tetrimino, pelialue);
    }
    
    /** Palikat ovat samassa linjassa kuin alkuperäisellä palikalla, mutta alempana.
     * @return True, jos ovat. Muutoin false.
     */
    private boolean vaikuttaaOlevanOikeassaMestassa()
    {
        Tetrimino aave = aavetetrimino.tetrimino();
        
        ArrayList<Palikka> palikat = new ArrayList<Palikka>( tetrimino.palikkakokoelma().palikat() );
        Iterator<Palikka> alkuperaisesta = palikat.iterator();
        
        for(Palikka aaveesta : aave.palikkakokoelma().palikat())
            while(alkuperaisesta.hasNext())
            {
                Palikka alkuperainen = alkuperaisesta.next();
                if(alkuperainen.sijainti().x() == aaveesta.sijainti().x() && alkuperainen.sijainti().y() <= aaveesta.sijainti().y())
                {
                    alkuperaisesta.remove();
                    break;
                }
            }
        
        return palikat.isEmpty();
    }
    
    @Test public void toimiiIlmanKaantelyita()
    {
        assertTrue( vaikuttaaOlevanOikeassaMestassa() );
    }
    
    @Test public void toimiiKaantaessaEriSuuntiin()
    {
        tetrimino.kaanna(Suunta.OIKEA);
        
        aavetetrimino.paivita();
        assertTrue( vaikuttaaOlevanOikeassaMestassa() );
        
        tetrimino.kaanna(Suunta.VASEN);
        tetrimino.kaanna(Suunta.VASEN);
        
        aavetetrimino.paivita();
        assertTrue( vaikuttaaOlevanOikeassaMestassa() );
    }
    
    @Test public void toimiiSiirrellessakin()
    {
        tetrimino.siirra(Suunta.VASEN);
        tetrimino.siirra(Suunta.ALAS);
        
        aavetetrimino.paivita();
        assertTrue( vaikuttaaOlevanOikeassaMestassa() );
        
        tetrimino.siirra(Suunta.OIKEA);
        tetrimino.siirra(Suunta.OIKEA);
        tetrimino.siirra(Suunta.ALAS);
        
        aavetetrimino.paivita();
        assertTrue( vaikuttaaOlevanOikeassaMestassa() );        
    }
}
