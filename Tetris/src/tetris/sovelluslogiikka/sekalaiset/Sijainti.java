
package tetris.sovelluslogiikka.sekalaiset;

/** Pitää sisällään kaksi koordinaattia, jotka muodostavat pisteen koordinaatistossa.
 * @author grandi
 */
public class Sijainti
{
    /** Sijainnin koordinaatti. */
    protected float x, y;
    
    /** Asettaa pisteen sijainnin.
     * @param x Pisteen x-koordinaatti.
     * @param y Pisteen y-koordinaatti.
     */
    public final void aseta(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    /** Kopioi pisteen sijainnin.
     * @param sijainti Toinen sijainti-olio.
     */
    public final void aseta(Sijainti sijainti)
    {
        this.x = sijainti.x();
        this.y = sijainti.y();
    }
    
    /** Kertoo onko sijainti sama jonkin toisen sijainti-olion kanssa.
     * @param toinen Vertailtava sijainti-olio.
     * @return True, mikäli vertailtava sijainti on sama. Muutoin false.
     */
    @Override public boolean equals(Object toinen)
    {
        return x == ((Sijainti)toinen).x() && y == ((Sijainti)toinen).y();
    }

    @Override public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + Float.floatToIntBits(this.x);
        hash = 29 * hash + Float.floatToIntBits(this.y);
        return hash;
    }
    
    /** Palauttaa pisteen x-koordinaatin.
     * @return Pisteen x-koordinaatti.
     */
    public float x() { return x; }
    
    /** Palauttaa pisteen y-koordinaatin.
     * @return Pisteen y-koordinaatti.
     */
    public float y() { return y; }
    
    /** Asettaa pisteen x-koordinaatin.
     * @param x Pisteen x-koordinaatti.
     */
    public void asetaX(float x) { this.x = x; }
    
    /** Asettaa pisteen y-koordinaatin.
     * @param y Pisteen y-koordinaatti.
     */
    public void asetaY(float y) { this.y = y; }
    
    /**
     * @param x Pisteen x-koordinaatti.
     * @param y Pisteen y-koordinaatti.
     */
    public Sijainti(float x, float y)
    {
        aseta(x, y);
    }
    
    /** Kopioi sijainnin koordinaateiksi kopioitavan sijainnin koordinaatit.
     * @param kopioitava Kopioitava sijainti-olio.
     */
    public Sijainti(Sijainti kopioitava)
    {
        aseta(kopioitava);
    }
    
    /** Palauttaa sijainnin merkkijonomuotoisen esityksen koordinaatteineen.
     * @return Sijainnin merkkijonoesitys.
     */
    @Override public String toString()
    {
        return "(" + x + "," + y + ")";
    }
}
