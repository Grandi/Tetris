
package sovelluslogiikka;

public class Sijainti
{
    public int x, y;
    
    public int x() { return x; }
    public int y() { return y; }
    
    public Sijainti(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Sijainti(Sijainti toinen)
    {
        this(toinen.x(), toinen.y());
    }
    
    public void aseta(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
