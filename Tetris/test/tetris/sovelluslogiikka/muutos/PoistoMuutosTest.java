
package tetris.sovelluslogiikka.muutos;

import org.junit.Test;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import org.junit.Before;
import static org.junit.Assert.*;

public class PoistoMuutosTest
{    
    private PoistoMuutos muutos;
    private Pelialue pelialue;
    private TetrisPalikka palikka;
    
    @Before public void setUp()
    {
        Sijainti sijainti = new Sijainti(1, 2);
        
        pelialue = new Pelialue(new Sijainti(0, 0), 2, 2);
        
        pelialue.tungePalikka(new TetrisPalikka(sijainti));
        muutos = new PoistoMuutos(sijainti, pelialue);
        
        palikka = (TetrisPalikka)pelialue.haePalikka(sijainti);
    }
    
    @Test public void palikanPeittavyysVaheneePaivittaessa()
    {
        int edellinen = palikka.vari().peittavyys();
        
        for(int i = 0; i <= 3; i++)
        {
            muutos.paivita();
            assertTrue( edellinen > palikka.vari().peittavyys() );
            edellinen = palikka.vari().peittavyys();
        }
    }
    
    @Test public void valmistuessaanPalikkaOnPoistunutPelialueelta()
    {
        assertTrue(pelialue.haePalikka(palikka.sijainti()) != null);
        
        while(!muutos.onValmis())
            muutos.paivita();
        
        assertTrue(pelialue.haePalikka(palikka.sijainti()) == null);
    }
}
