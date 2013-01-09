
package tetris.sovelluslogiikka.sekalaiset;

/** Määrittää Tetris-palikan.
 * @author grandi
 */
public class TetrisPalikka implements Palikka
{
    /** TetrisPalikan sijainti. */
    protected Sijainti sijainti;
    
    /** TetrisPalikan väri. */
    protected Vari vari;
    
    /**
     * @param sijainti Palikan sijainti.
     * @param vari Palikan väri.
     */
    public TetrisPalikka(Sijainti sijainti, Vari vari)
    {
        this.sijainti = sijainti;
        this.vari = vari;
    }
    
    /** Asettaa palikan sijainnin.
     * @param sijainti Sijainti, johon palikka tahdotaan.
     */
    public void asetaSijainniksi(Sijainti sijainti)
    {
        this.sijainti = sijainti;
    }
    
    /**
     * @param sijainti Palikan sijainti.
     */
    public TetrisPalikka(Sijainti sijainti)
    {
        this(sijainti, new Vari(50, 50, 50, 255));
    }
    
    /** Kopioi palikan sijiannin.
     * @param palikka Palikka, josta sijainti kopioidaan.
     */
    public TetrisPalikka(TetrisPalikka palikka)
    {
        this(palikka.sijainti(), palikka.vari());
    }
    
    /** Palauttaa Tetris-palikan värin.
     * @return Palikan väri.
     */
    public Vari vari()
    {
        return vari;
    }

    @Override public Sijainti sijainti()
    {
        return sijainti;
    }
}
