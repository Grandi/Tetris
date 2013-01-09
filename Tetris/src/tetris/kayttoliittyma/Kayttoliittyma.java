
package tetris.kayttoliittyma;

import tetris.sovelluslogiikka.pelimekaniikka.Asetukset;

/** Pelin käyttöliittymä.
 * @author grandi
 */
public class Kayttoliittyma implements Runnable
{
    //private Peliikkuna peliikkuna;
    private Asetukset asetukset;
    private Ohjaus ohjaus;
    
    public Kayttoliittyma()
    {
        asetukset = new Asetukset();
        ohjaus = new Ohjaus(asetukset);
    }
    
    @Override public void run()
    {
        new Peliikkuna(ohjaus);
        ohjaus.pelaa();
    }
}
