
package tetris.sovelluslogiikka.tetrimino;

import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Suunta;

/** Määrittää tetriminon, eli käänneltävän ja siirreltävän palikkamuodostelman.
 * @author grandi
 */
public class Tetrimino
{
    private TetriminoPalikkakokoelma palikkakokoelma;
    private Sijainti siirtyma;
    private int asento;
    
    public Tetrimino()
    {
        this.palikkakokoelma = new TetriminoPalikkakokoelma(this);
        alusta();
    }
    
    /** Kopioi tetriminon.
     * @param Tetrimino, joka tahdotaan kopioida.
     */
    public Tetrimino(Tetrimino kopioitava)
    {
        this.palikkakokoelma = new TetriminoPalikkakokoelma(kopioitava.palikkakokoelma);
        this.asento = kopioitava.asento();
        this.siirtyma = new Sijainti(kopioitava.siirtyma());
    }
    
    /** Alustaa tetriminon sellaiseksi, että se voidaan generoida uudestaan.
     */
    final public void alusta()
    {
        this.palikkakokoelma.tyhjenna();
        this.asento = 0;
        this.siirtyma = new Sijainti(0, 0);
    }
    
    /** Asettaa tetriminon siirtymän, eli sen mihin suuntaan muodostelmaa on siirretty.
     * @param sijainti Tetriminon sijainti.
     */
    public void asetaSijainniksi(Sijainti sijainti)
    {
        this.siirtyma = new Sijainti(sijainti);
    }
    
    /** Kertoo tetriminon tämänhetkisen asennon.
     * @return Asento, lukuarvo väliltä 0-3.
     */
    public int asento()
    {
        return asento;
    }
    
    /** Laittaa tetriminon haluttuun asentoon.
     * @param asento Asento, johon tetrimino tahdotaan. Lukuarvo väliltä 0-3.s
     */
    public void laitaAsentoon(int asento)
    {
        this.asento = asento;
        if(this.asento > 3)
            this.asento = 0;
        else if(this.asento < 0)
            this.asento = 3;
    }
    
    /** Kääntää tetriminoa tahdottuun suuntaan.
     * @param suunta Joko Suunta.VASEN tai Suunta.OIKEA.
     */
    public void kaanna(Suunta suunta)
    {
        switch(suunta)
        {
            case OIKEA: laitaAsentoon(asento + 1); break;
            case VASEN: laitaAsentoon(asento - 1); break;
        }
    }
    
    /** Siirtää tetriminoa tahdottuun suuntaan.
     * @param suunta Suunta, johon tetriminoa tahdotaan siirtää.
     */
    public void siirra(Suunta suunta)
    {
        float x = siirtyma.x();
        float y = siirtyma.y();
        
        switch(suunta)
        {
            case OIKEA: siirtyma.aseta(x + 1, y); break;
            case VASEN: siirtyma.aseta(x - 1, y); break;
            case ALAS:  siirtyma.aseta(x, y + 1); break;
            case YLOS:  siirtyma.aseta(x, y - 1); break;
        }
    }
    
    /** Kertoo tetriminon todellisen keskipisteen, johon on otettu huomioon siirtymä ja
     * palikoiden suhteellinen sijainti toisiinsa nähden.
     * @return Tetriminon palikoiden tämänhetkinen keskipiste.
     */
    public Sijainti sijainti()
    {          
        Sijainti keskipiste = palikkakokoelma.suhteellinenKeskipiste();
        
        if(keskipiste == null)
        {
            if(siirtyma != null)
                return siirtyma;
            else
                return null;
        }
        else
            return new Sijainti(siirtyma.x() + keskipiste.x(), siirtyma.y() + keskipiste.y());
    }
    
    /** Kertoo mihin tetriminoa on siirretty.
     * @return Tetriminon siirtymä.
     */
    public Sijainti siirtyma()
    {
        return siirtyma;
    }
    
    /** Palauttaa sen palikkakokoelman, johon tetriminon palikat on laitettu.
     * @return Tetriminon palikkakokoelma.
     */
    public Palikkakokoelma palikkakokoelma()
    {
        return palikkakokoelma;
    }
}
