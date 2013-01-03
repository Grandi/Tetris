
package tetris.sovelluslogiikka.sekalaiset;

public class Alue
{
    private Sijainti alkupiste, paatepiste;
    
    public Alue(Sijainti alku, Sijainti loppu)
    {
        this.alkupiste = alku;
        this.paatepiste = loppu;
    }
    
    public Alue(Sijainti alku, int leveys, int korkeus)
    {
        this(alku, new Sijainti(alku.x() + leveys, alku.y() + korkeus));
    }
    
    public boolean onSisalla(Sijainti sijainti)
    {
        return sijainti.x() >= alkupiste.x() && sijainti.y() >= alkupiste.y() && sijainti.x() <= paatepiste.x() && sijainti.y() <= paatepiste.y();
    }
    
    public Sijainti alkupiste() { return alkupiste; }
    public Sijainti paatepiste() { return paatepiste; }
    
    public int leveys() { return Math.abs(paatepiste.x() - alkupiste.x()); }
    public int korkeus() { return Math.abs(paatepiste.y() - alkupiste.y()); }
    
    @Override public String toString()
    {
        return "Alue [" + alkupiste + "," + paatepiste + "]";
    }
}
