
package tetris.sovelluslogiikka.sekalaiset;

public class Palikka
{
    protected Sijainti sijainti;
    
    public Palikka(Sijainti sijainti)
    {
        this.sijainti = new Sijainti(sijainti);
    }
    
    public Sijainti sijainti()
    {
        return sijainti;
    }
}
