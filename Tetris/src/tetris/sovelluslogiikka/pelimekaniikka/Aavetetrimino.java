
package tetris.sovelluslogiikka.pelimekaniikka;

import java.util.Random;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Vari;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.tetrimino.TetriminonRakentaja;

/** Luo ja ylläpitää aavetetriminoa, eli himmennettyä kopiotetriminoa, joka näyttää alkuperäisen
 * tetriminon putoamiskohdan pelialueella.
 * @author grandi
 */
public class Aavetetrimino
{
    /** Tetrimino, joka toimii alkuperäisen tetriminon alas pudotettuna kopiona. */
    private Tetrimino aave;
            
    /** Alkuperäinen tetrimino, jota "seurataan" ja kopioidaan. */
    private Tetrimino alkuperainen;
    
    /** Pelialue, jolla törmäyksiä tutkitaan. */
    private Pelialue pelialue;
    
    public Aavetetrimino(Tetrimino seurattava, Pelialue pelialue)
    {
        this.alkuperainen = seurattava;
        this.pelialue = pelialue;
        this.aave = new Tetrimino();
        
        paivita();
    }
    
    /** Päivittää aavetetriminon sijainnin ja asennon.
     */
    final public void paivita()
    {
        if(alkuperainen == null || alkuperainen.palikkakokoelma().lisattyja() == 0)
            return;
        
        kopioiAlkuperaisesta();
        new TetriminonAsettelija(aave, pelialue).pudotaAlas();
    }
    
    /** Kopioi aavetetriminon alkuperäisestä tetriminosta, ja asettaa sille himmeämmän värin.
     */
    private void kopioiAlkuperaisesta()
    {
        Vari vari = ((TetrisPalikka)alkuperainen.palikkakokoelma().palikat().get(0)).vari();
        
        TetriminonRakentaja rakentaja = new TetriminonRakentaja(aave, new Random());
  
        rakentaja.rakennaToisenTetriminonPohjalta(alkuperainen);
        rakentaja.varita(new Vari(vari.punainen(), vari.vihrea(), vari.sininen(), 20));
    }
    
    /** Palauttaa aavetetriminon.
     * @return Alkuperäisen tetriminon pohjalta kopioitu aavetetrimino.
     */
    public Tetrimino tetrimino()
    {
        return aave;
    }
}
