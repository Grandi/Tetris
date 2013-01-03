
package tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.tetrimino.TetriminonRakentaja;
import tetris.sovelluslogiikka.tormaystarkastelu.Tormays;
import tetris.sovelluslogiikka.tormaystarkastelu.Tormayssuunta;

public class Apufunktiot
{
    public static Palikka uusiPalikka(int x, int y)
    {
        return new Palikka(new Sijainti(x, y));
    }
    
    public static boolean sisaltaaSuunnat(Tormays tormays, ArrayList<Tormayssuunta> suunnat)
    {
        ArrayList<Tormayssuunta> tunnistetut = tormays.suunnat();
        
        for(Tormayssuunta tormayssuunta : tunnistetut)
            if(suunnat.contains(tormayssuunta))
                suunnat.remove(tormayssuunta);
            else
                return false;
        
        return suunnat.isEmpty();
    }
    
    public static boolean sisaltaaSuunnat(Tormays tormays, Tormayssuunta[] suunnat)
    {
        return sisaltaaSuunnat(tormays, new ArrayList<Tormayssuunta>(Arrays.asList(suunnat)));
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
