
package tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.tetrimino.TetriminonRakentaja;
import tetris.sovelluslogiikka.tormaystarkastelu.Tormays;
import tetris.sovelluslogiikka.sekalaiset.Suunta;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.tetrimino.TetriminoPalikka;

public class Apufunktiot
{
    public static Palikka uusiPalikka(int x, int y)
    {
        return new TetrisPalikka(new Sijainti(x, y));
    }
    
    public static boolean sisaltaaSuunnat(Tormays tormays, ArrayList<Suunta> suunnat)
    {
        ArrayList<Suunta> tunnistetut = tormays.suunnat();
        
        for(Suunta tormayssuunta : tunnistetut)
            if(suunnat.contains(tormayssuunta))
                suunnat.remove(tormayssuunta);
            else
                return false;
        
        return suunnat.isEmpty();
    }
    
    public static boolean sisaltaaSuunnat(Tormays tormays, Suunta[] suunnat)
    {
        return sisaltaaSuunnat(tormays, new ArrayList<Suunta>(Arrays.asList(suunnat)));
    }
    
    public static Tetrimino luoTetriminoTestaamistaVarten(Sijainti sijainti)
    {
        TetriminonRakentaja rakentaja = new TetriminonRakentaja();
        
        rakentaja.rakennaMallista(
                sijainti,
                new boolean[][]
                {
                     { false, true, false, false },
                     { false, true, false, false },
                     { false, true, true, false },
                     { false, false, false, false }
                }
        );
        
        return rakentaja.rakennettuTetrimino();
    }
}
