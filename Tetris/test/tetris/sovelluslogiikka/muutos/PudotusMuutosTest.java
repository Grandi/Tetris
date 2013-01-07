
package tetris.sovelluslogiikka.muutos;

import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.tetrimino.TetriminoPalikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PudotusMuutosTest
{
    private Pelialue pelialue;
    
    @Before public void setUp()
    {
        pelialue = new Pelialue(new Sijainti(1, 0), 2, 2);
        pelialue.tungePalikka(new TetrisPalikka(new Sijainti(1, 0)));
    }
    
    @Test public void palikkaPaatyyLopultaOikeaanPaikkaan()
    {
        PudotusMuutos muutos = new PudotusMuutos(new Sijainti(1, 0), new Sijainti(1, 2), pelialue);
        TetrisPalikka palikka = (TetrisPalikka)pelialue.haePalikka(new Sijainti(1, 0));
        
        assertFalse(pelialue.haePalikka(new Sijainti(1, 2)) != null);
        assertFalse(pelialue.haePalikka(new Sijainti(1, 0)) == null);
        
        while(!muutos.onValmis())
            muutos.paivita();
        
        assertTrue(pelialue.haePalikka(new Sijainti(1, 2)) != null);
        assertTrue(pelialue.haePalikka(new Sijainti(1, 0)) == null);
    }
    
    @Test public void pudottaminenToimiiVaikkaLahtoJaMaaliOlisivatSamat()
    {
        PudotusMuutos muutos = new PudotusMuutos(new Sijainti(1, 0), new Sijainti(1, 0), pelialue);
        
        muutos.paivita();
        
        assertTrue(muutos.onValmis());
        assertTrue(pelialue.haePalikka(new Sijainti(1, 0)) != null);
    }
}
