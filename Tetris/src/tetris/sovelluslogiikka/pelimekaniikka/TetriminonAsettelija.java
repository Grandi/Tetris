
package tetris.sovelluslogiikka.pelimekaniikka;

import java.util.ArrayList;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Suunta;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import tetris.sovelluslogiikka.tormaystarkastelu.TetrisTormays;

/** Työkalu, jolla voidaan siirrellä ja käännellä tetriminoa pelialueella niin, että
 * mahdolliset törmäykset otetaan samalla huomioon.
 * @author grandi
 */
public class TetriminonAsettelija
{
    private Tetrimino tetrimino;
    private Pelialue pelialue;
    private TetrisTormays tormays;
    
    /**
     * @param tetrimino Tetrimino, jota tahdotaan siirrellä ja/tai käännellä.
     * @param pelialue Pelialue, johon tetriminon oletetaan voivan törmätä.
     */
    public TetriminonAsettelija(Tetrimino tetrimino, Pelialue pelialue)
    {
        this.tetrimino = tetrimino;
        this.pelialue = pelialue;
    }
    
    private TetrisTormays paivitaTormaystarkistus()
    {
        tormays = new TetrisTormays(tetrimino, pelialue);
        return tormays;
    }
    
    /** Kertoo törmääkö tetrimino nykyisessä sijainnissaan ja asennossaan pelialueeseen.
     * @return Palauttaa true, jos tetrimino törmää pelialueeseen, muutoin false.
     */
    public boolean tormaa()
    {
        return !paivitaTormaystarkistus().tormaykset().isEmpty();
    }
    
    /** Siirtää tetriminoa alimpaan pisteeseen, jossa se ei vielä törmää pelialueeseen.
     */
    public void pudotaAlas()
    {
        Sijainti edellinenSijainti = tetrimino.siirtyma();
        
        while(!tormaa())
        {
            edellinenSijainti = new Sijainti(tetrimino.siirtyma());
            tetrimino.siirra(Suunta.ALAS);
        }
        
        tetrimino.siirtyma().aseta(edellinenSijainti);
    }
    
    /** Jos tiedetään, että tetrimino törmää tällä hetkellä pelialueeseen, tämä metodi pyrkii siirtämään
     * tetriminoa tiettyyn suuntaan niin kauan kunnes se ei enää törmää.
     * 
     * @param korjaussuunta Suunta, johon päin tetriminoa siirretään korjausliikkeenä.
     * @return Palauttaa false, jos tilannetta ei voi korjata annetulla korjassuunnalla. Näin käy, jos
     * korjaussuunnassakin on törmäys. Jos korjaaminen onnistuu, palautetaan true.
     */
    public boolean yritaPoistuaTormaamasta(Suunta korjaussuunta)
    {
        while(tormaa())
        {
            if(tormays.suunnat().contains(korjaussuunta))
                return false;
            
            tetrimino.siirra(korjaussuunta);
        }
        
        return true;
    }

    private boolean korjaaSijainti()
    {
        ArrayList<Suunta> suunnat = paivitaTormaystarkistus().suunnat();

        if(suunnat.isEmpty())
            return true;
        else if(suunnat.contains(Suunta.OIKEA))
            return yritaPoistuaTormaamasta(Suunta.VASEN);
        else if(suunnat.contains(Suunta.VASEN))
            return yritaPoistuaTormaamasta(Suunta.OIKEA);
        else
            return false;
    }
    
    /** Kokeilee siirtää tetriminoa askeleen annettuun suuntaan. Mikäli se ei törmäyksen takia
     * onnistuisi, tetrimino säilyttää alkuperäisen sijaintinsa.
     * 
     * @param suunta Suunta, johon tetriminoa tahdotaan siirtää.
     * @return Palauttaa true, jos siirtäminen onnistui. Muutoin false.
     */
    public boolean kokeileSiirtaa(Suunta suunta)
    {
        Sijainti alkuperainenSiirtyma = new Sijainti(tetrimino.siirtyma());
        
        tetrimino.siirra(suunta);
        if(!tormaa())
            return true;
        
        tetrimino.siirtyma().aseta(alkuperainenSiirtyma);
        return false;
    }
    
    /** Kokeilee kääntää tetriminoa 95 astetta annettuun suuntaan. Jos käännöksestä
     * aiheutuisi törmäys, yritetään tehdä vielä korjaava siirtoliike.
     * 
     * @param suunta Suunta, johon tetriminoa tahdotaan kääntää. Vasen/Oikea
     * @return Palauttaa false, jos tetrimino jäisi kääntyessään niin huonoon paikkaan,
     * ettei tilannetta voisi korjata. Muutoin true.
     */
    public boolean kokeileKaantaa(Suunta suunta)
    {
        int alkuperainenAsento = tetrimino.asento();
        
        tetrimino.kaanna(suunta);
        if(!tormaa() || korjaaSijainti())
            return true;
        
        tetrimino.laitaAsentoon(alkuperainenAsento);
        return false;
    }
    
    /** Palauttaa tetriminon, jota on siirrelty/käännelty tämän luokan avulla.
     * @return Tetrimino, johon muutoksia kohdistettiin.
     */
    public Tetrimino aseteltuTetrimino()
    {
        return tetrimino;
    }
}
