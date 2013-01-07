
package tetris.sovelluslogiikka.tormaystarkastelu;

import tetris.sovelluslogiikka.sekalaiset.Suunta;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Alue;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import org.junit.Before;
import org.junit.Test;
import tetris.Apufunktiot;
import static org.junit.Assert.*;

public class AlueTormaysTest
{
    private Palikka ulkonaOleva, ulkonaOleva2, sisallaOleva;
    private Alue alue;
    
    @Before public void setUp()
    {
        ulkonaOleva  = Apufunktiot.uusiPalikka(1, 2);
        ulkonaOleva2 = Apufunktiot.uusiPalikka(10, 9);
        sisallaOleva = Apufunktiot.uusiPalikka(4, 5);

        alue = new Alue(new Sijainti(2, 3), new Sijainti(8, 6));
    }
    
    @Test public void ulkonaOlevatTunnistetaanUlkonaoleviksi()
    {
        AlueTormays aluetormays = new AlueTormays(ulkonaOleva, alue);
        Suunta[] suunnat = new Suunta[] { Suunta.VASEN, Suunta.YLOS };

        assertTrue(Apufunktiot.sisaltaaSuunnat(aluetormays, suunnat));
        
        aluetormays = new AlueTormays(ulkonaOleva2, alue);
        suunnat = new Suunta[] { Suunta.OIKEA, Suunta.ALAS };
        
        assertTrue(Apufunktiot.sisaltaaSuunnat(aluetormays, suunnat));
    }
    
    @Test public void sisallaOlevaTunnistetaanSisallaOlevaksi()
    {
        AlueTormays aluetormays = new AlueTormays(sisallaOleva, alue);
        assertEquals(0, aluetormays.suunnat().size());
    }
}
