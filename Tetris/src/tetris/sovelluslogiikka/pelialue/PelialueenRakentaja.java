
package tetris.sovelluslogiikka.pelialue;

import java.util.ArrayList;
import java.util.Random;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;

/** Rakentaa pelialuetta. Kokoaa yhteen joitain pelialueen manipuloimiseen tarvittavia toimintoja.
 * @author grandi
 */
public class PelialueenRakentaja
{
    private Pelialue pelialue;
    private Random satunnaisgeneraattori;
    
    /**
     * @param pelialue Pelialue, johon muutoksia tahdotaan suorittaa.
     * @param satunnaisgeneraattori Käyttäjän valitsema satunnaisgeneraattori.
     */
    public PelialueenRakentaja(Pelialue pelialue, Random satunnaisgeneraattori)
    {
        this.pelialue = pelialue;
        this.satunnaisgeneraattori = satunnaisgeneraattori;
    }
    
    /**
     * @param pelialue Pelialue, johon muutoksia tahdotaan suorittaa.
     */
    public PelialueenRakentaja(Pelialue pelialue)
    {
        this.pelialue = pelialue;
        this.satunnaisgeneraattori = new Random();
    }    
    
    /** Tunkee pelialueelle annetut palikat.
     * @param kokoelma Palikkakokoelma, joka sisältää lisättävät palikat.
     * @return Palauttaa ArrayListin palikoista, joiden lisääminen epäonnistui.
     */
    public ArrayList<Palikka> tungePalikat(Palikkakokoelma kokoelma)
    {
        ArrayList<Palikka> epaonnistuneet = new ArrayList<Palikka>();
        
        for(Palikka palikka : kokoelma.palikat())
            if(!pelialue.tungePalikka(palikka))
                epaonnistuneet.add(palikka);
        
        return epaonnistuneet;
    }
    
    /** Tunkee tetriminossa olevat palikat pelialueelle.
     * @param tetrimino Tetrimino, josta palikat otetaan.
     * @return Palauttaa ArrayListin palikoista, joiden lisääminen epäonnistui.
     */
    public ArrayList<Palikka> tungePalikat(Tetrimino tetrimino)
    {
        return tungePalikat(tetrimino.palikkakokoelma());
    }
    
    private void esitaytaRivi(int rivinumero, int tyhjaksiJatettava)
    {
        for(float x = pelialue.alue().alkupiste().x(); x <= pelialue.alue().paatepiste().x(); x++)
        {
            Sijainti nykyinen = new Sijainti(x, pelialue.alue().paatepiste().y() - rivinumero );

            if((int)x != tyhjaksiJatettava)
                pelialue.tungePalikka(new TetrisPalikka(nykyinen));
            else
                pelialue.poistaPalikka(nykyinen);
        }
        
        float QQQQQQ = pelialue.alue().paatepiste().x() - pelialue.alue().alkupiste().x();
        float b = 0;
    }
    
    private void esitaytaRivi(int rivinumero)
    {
        int tyhjaksiJatettava = satunnaisgeneraattori.nextInt((int)pelialue.alue().leveys()) + (int)pelialue.alue().alkupiste().x();
        esitaytaRivi(rivinumero, tyhjaksiJatettava);
    }
    
    /** Esitäyttää pelialuetta valmiilla, yhdellä palikalla vajailla palikkariveillä.
     * @param rivimaara Kuinka monta palikkariviä pelialueella tahdotaan olla.
     */
    public void esitaytaRivit(int rivimaara)
    {
        for(int i = 0; i < rivimaara; i++)
            esitaytaRivi(i);
    }
    
    /** Palauttaa sen pelialueen, jolle tämä luokka teki muutoksia.
     * @return Rakennettu pelialue.
     */
    public Pelialue rakennettuPelialue()
    {
        return pelialue;
    }
}
