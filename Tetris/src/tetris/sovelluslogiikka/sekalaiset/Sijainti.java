
package tetris.sovelluslogiikka.sekalaiset;

public class Sijainti
{    
    protected int x, y;
    
    public final void aseta(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public boolean onSamaKuin(Sijainti toinen)
    {
        return x == toinen.x() && y == toinen.y();
    }
    
    public int x() { return x; }
    public int y() { return y; }
    
    public void asetaX(int x) { this.x = x; }
    public void asetaY(int y) { this.y = y; }
    
    public Sijainti(int x, int y)
    {
        aseta(x, y);
    }
    
    public Sijainti(Sijainti kopioitava)
    {
        aseta(kopioitava.x(), kopioitava.y());
    }
    
    @Override public String toString()
    {
        return "(" + x + "," + y + ")";
    }
}
