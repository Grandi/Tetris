
package tetris.sovelluslogiikka.pelialue;

import java.util.ArrayList;
import java.util.Random;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Palikkakokoelma;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;
import tetris.sovelluslogiikka.sekalaiset.Vari;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;

/** Rakentaa pelialuetta. Kokoaa yhteen joitain pelialueen manipuloimiseen tarvittavia toimintoja.
 * @author grandi
 */
public class PelialueenRakentaja
{
    /** Pelialue, jolle muutoksia suoritetaan. */
    private Pelialue pelialue;
    
    /** Satunnaisgeneraattori, joka hoitaa rakentamisen satunnaisuudesta. */
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
        this(pelialue, new Random());
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
    
    /** Värittää pelialueen palikat käyttäjän valitsemalla värillä.
     * @param varivalinta Väri, jolla palikat tahdotaan värjätä.
     */
    public void varita(Vari varivalinta)
    {
        for(Palikka palikka : pelialue.palikat())
            ((TetrisPalikka)palikka).vari().aseta(varivalinta);
    }
    
    /** Himmentää pelialueen palikoita, eli vähentää niiden peittävyyttä. 
     * @param maara Kuinka paljon palikoita tahdotaan himmentää.
     */
    public void himmenna(int maara)
    {
        for(Palikka palikka : pelialue.palikat())
            ((TetrisPalikka)palikka).vari().asetaPeittavyys( (int)( ((TetrisPalikka)palikka).vari().peittavyys() - maara ) );
    }
    
    /** Värittää pelialueen palikat sattumanvaraisella värillä.
     */
    public void varita(Vari[] paletti)
    {
        for(Palikka palikka : pelialue.palikat())
            ((TetrisPalikka)palikka).vari().aseta(paletti[satunnaisgeneraattori.nextInt(paletti.length)]);
    }
    
    /** Esitäyttää halutun rivin. Jättää yhden sattumanvaraisen palikan vajaaksi.
     * @param rivinumero Rivi, joka tahdotaan täyttää.
     * @param eiSaaJaadaTyhjaksi Kohta riviltä, jota ei saa jättää tyhjäksi.
     * @return Se palikka, joka jätettiin tyhjäksi halutulla rivillä.
     */
    private int esitaytaRivi(int rivinumero, int eiSaaJaadaTyhjaksi)
    {
        int tyhjaksiJatettava = -1;
        
        do
            tyhjaksiJatettava = satunnaisgeneraattori.nextInt((int)pelialue.alue().leveys() + 1) + (int)pelialue.alue().alkupiste().x();
        while(tyhjaksiJatettava == eiSaaJaadaTyhjaksi);
        
        for(float x = pelialue.alue().alkupiste().x(); x <= pelialue.alue().paatepiste().x(); x++)
        {
            Sijainti nykyinen = new Sijainti(x, pelialue.alue().paatepiste().y() - rivinumero );

            if((int)x != tyhjaksiJatettava)
                pelialue.tungePalikka(new TetrisPalikka(nykyinen));
            else
                pelialue.poistaPalikka(nykyinen);
        }
        
        return tyhjaksiJatettava;
    }
    
    /** Esitäyttää pelialuetta valmiilla, yhdellä palikalla vajailla palikkariveillä.
     * @param rivimaara Kuinka monta palikkariviä pelialueella tahdotaan olla.
     */
    public void esitaytaRivit(int rivimaara)
    {
        int edellinenTyhjaksiJatetty = -1;
        for(int i = 0; i < rivimaara; i++)
        {
            edellinenTyhjaksiJatetty = esitaytaRivi(i, edellinenTyhjaksiJatetty);
        }
    }
    
    /** Palauttaa sen pelialueen, jolle tämä luokka teki muutoksia.
     * @return Rakennettu pelialue.
     */
    public Pelialue rakennettuPelialue()
    {
        return pelialue;
    }
}
