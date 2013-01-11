
package tetris.kayttoliittyma;

import tetris.sovelluslogiikka.pelimekaniikka.Asetukset;

/** Pelin käyttöliittymä.
 * @author grandi
 */
public class Kayttoliittyma
{
    public Kayttoliittyma()
    {
        Asetukset asetukset = new Asetukset();

        Asetusikkuna asetusikkuna = new Asetusikkuna(asetukset);
        
        while(asetusikkuna.isVisible())
            System.out.print("");
        
        Ohjaus ohjaus = new Ohjaus(asetukset);
        new Peliikkuna(ohjaus, asetusikkuna);
        ohjaus.run();
    }
}
