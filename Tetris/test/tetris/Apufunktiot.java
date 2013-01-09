
package tetris;

import java.util.ArrayList;
import java.util.Arrays;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.tetrimino.TetriminonRakentaja;
import tetris.sovelluslogiikka.tormaystarkastelu.Tormays;
import tetris.sovelluslogiikka.sekalaiset.Suunta;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;

/** Joukko apufunktioita testaamisen helpottamiseksi.
 * @author grandi
 */
public class Apufunktiot
{
    /** Wrapperi-funktio. Luo uuden Tetris-palikan.
     * @param x Palikan x-koordinaatti.
     * @param y Palikan y-koordinaatti.
     * @return Palauttaa luodun TetrisPalikan.
     */
    public static Palikka uusiPalikka(int x, int y)
    {
        return new TetrisPalikka(new Sijainti(x, y));
    }
    
    /** Kertoo, sisältääkö törmäys jotkut tietyt suunnat.
     * @param tormays Törmäys, jonka suuntia tarkastellaan.
     * @param suunnat ArrayList, jossa olevat suunnat tulisi löytyä.
     * @return True, jos kaikki suunnat sisältyvät törmäykseen. Muutoin false.
     */
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
    
    /* Sama kuin sisaltaaSuunnat(Tormays, ArrayList<>), mutta taulukolla ArrayListin asemesta.
     */
    public static boolean sisaltaaSuunnat(Tormays tormays, Suunta[] suunnat)
    {
        return sisaltaaSuunnat(tormays, new ArrayList<Suunta>(Arrays.asList(suunnat)));
    }
    
    /** Luo neljäpalikkaisen tetriminon testaamista varten.
     * @param sijainti Tetriminon sijainti.
     * @return Palauttaa rakennetun tetriminon.
     */
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
