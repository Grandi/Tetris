
package tetris.sovelluslogiikka.pelialue;

import tetris.sovelluslogiikka.pelialue.TetrisPelialue;
import tetris.Apufunktiot;
import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetrisPelialueTest
{
    TetrisPelialue tetrisPelialue;
    
    @Before public void setUp()
    {
        tetrisPelialue = new TetrisPelialue(new Sijainti(0, 0), 3, 3);
    }
    
    @Test public void palikoitaEiVoiTunkeaPelialueenAlapuolelle()
    {
        assertFalse( tetrisPelialue.tungePalikka(Apufunktiot.uusiPalikka(4, -1)) );
    }
    
    @Test public void palikoitaVoiTunkea()
    {
        assertTrue( tetrisPelialue.tungePalikka(Apufunktiot.uusiPalikka(2, 0)) );
        assertTrue( tetrisPelialue.tungePalikka(Apufunktiot.uusiPalikka(3, 0)) );
        assertTrue( tetrisPelialue.tungePalikka(Apufunktiot.uusiPalikka(2, 1)) );
        
        assertEquals(3, tetrisPelialue.lisattyja());
    }
    
    @Test public void palikoidenHakeminenToimii()
    {
        Palikka tungetut[] = new Palikka[]
        {
            Apufunktiot.uusiPalikka(2, 0),
            Apufunktiot.uusiPalikka(3, 0),
            Apufunktiot.uusiPalikka(2, 1)
        };
        
        for(Palikka palikka : tungetut)
            tetrisPelialue.tungePalikka(palikka);
        
        ArrayList<Palikka> palikat = tetrisPelialue.palikat();
        
        for(Palikka palikka : tungetut)
        {
            assertTrue( palikat.contains(palikka) );
            assertTrue( tetrisPelialue.sisaltaaPalikan(palikka.sijainti()) == palikka );
        }
    }
}
