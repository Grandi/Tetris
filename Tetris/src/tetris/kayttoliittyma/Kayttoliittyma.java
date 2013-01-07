
package tetris.kayttoliittyma;

/** Pelin käyttöliittymä.
 * @author grandi
 */
public class Kayttoliittyma implements Runnable
{
    private Asetusikkuna asetusikkuna;
    private Peliikkuna peliikkuna;
    private Ohjaus ohjaus;
    
    public Kayttoliittyma()
    {
        ohjaus = new Ohjaus();
    }
    
    @Override public void run()
    {
        /*
        peliikkuna = new Peliikkuna(ohjaus);
        ohjaus.pelaa();
        */
    }
}
