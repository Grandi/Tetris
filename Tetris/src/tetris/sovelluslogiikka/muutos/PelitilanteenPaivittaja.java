
package tetris.sovelluslogiikka.muutos;

import java.util.HashMap;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.pelimekaniikka.Pelitilanne;
import tetris.sovelluslogiikka.sekalaiset.Ajastin;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.TetrisPalikka;

/** Päivittää pelialueen/-tilanteen. Tarkistaa siis esimerkiksi onko pelialueella poistettavia
 * palikkarivejä, ja muokkaa pisteitä/vaikeustasoja poistojen yhteydessä
 * @author grandi
 */
public class PelitilanteenPaivittaja extends Muutos
{
    /** Pelialueella tapahtuvat muutokset. */
    private RyhmaMuutos poistot, siirrot;
    
    /** Pelitilanne, johon päivitetään pisteet ja vaikeustasot jne. */
    private Pelitilanne pelinTila;
    
    /** Ajastin, jolla ajastetaan muutosten tapahtumisnopeutta. */
    private Ajastin muutostenAjastaja;
    
    /** Y-koordinaatti, johon seuraava putoava palikkarivi tipahtaa. */
    private float putoamiskohta;
    
    /** Pelialue, jota päivitetään. */
    private Pelialue pelialue;
    
    /**
     * @param pelialue Pelialue, jota päivitetään.
     * @param pelinTila Pelitilanne, jota päivitetään.
     */
    public PelitilanteenPaivittaja(Pelialue pelialue, Pelitilanne pelinTila)
    {
        this(pelialue, pelinTila, new Ajastin());
    }
    
    /**
     * @param pelialue Pelialue, jota päivitetään.
     * @param pelinTila Pelitilanne, jota päivitetään.
     * @param ajastin Käyttäjän valitsema ajastin muutosten tapahtumanopeuksia säätämään.
     */
    public PelitilanteenPaivittaja(Pelialue pelialue, Pelitilanne pelinTila, Ajastin ajastin)
    {
        this.pelialue = pelialue;
        this.pelinTila = pelinTila;
        
        this.poistot = new RyhmaMuutos();
        this.siirrot = new RyhmaMuutos();
        this.siirrot.asetaLaukaisijaksi(poistot);
        
        this.muutostenAjastaja = ajastin;
    }    
    
    /** Poistaa parametrina annetun palikan pelialueelta.
     * @param palikka Poistettava palikka.
     */
    private void poistaPalikka(TetrisPalikka palikka)
    {
        if(palikka == null)
            return;
        
        if(putoamiskohta == 0)
            putoamiskohta = palikka.sijainti().y();
        poistot.lisaaMuutos(new PoistoMuutos(palikka.sijainti(), pelialue));
    }
    
    /** Pudottaa parametrina annetun palikan pelialueella.
     * @param palikka Pudotettava palikka;
     */
    private void pudotaPalikka(TetrisPalikka palikka)
    {
        if(palikka == null)
            return;
        
        Sijainti kohta = new Sijainti(palikka.sijainti().x(), putoamiskohta);
        siirrot.lisaaMuutos(new PudotusMuutos(palikka.sijainti(), kohta, pelialue));
    }
    
    /** Päättää mitä tekee parametrina annetun rivinumeron riville. Poistaa, jos on täysi,
     * pudottaa, jos alempia pudotettiin, tai sitten ei tee mitään.
     * @param rivi Sen rivin rivinumero, jota halutaan käsittelemän.
     * @return True, jos rivi poistettiin. Muutoin false.
     */
    private boolean kasitteleRivi(float rivi)
    {
        for(float x = pelialue.alue().alkupiste().x(); x <= pelialue.alue().paatepiste().x(); x++)
        {
            TetrisPalikka palikka = (TetrisPalikka)pelialue.haePalikka(new Sijainti(x, pelialue.alue().paatepiste().y()-rivi));
            
            if(pelialue.riviOnTaysi(rivi))
                poistaPalikka(palikka);
            else if(!poistot.onValmis())
                pudotaPalikka(palikka);
        }
        
        if(!pelialue.riviOnTaysi(rivi) && putoamiskohta != 0)
            putoamiskohta--;
        
        return pelialue.riviOnTaysi(rivi);
    }
    
    /** Vaikuttaa pelitilanteeseen parametrina annetulla rivimäärällä.
     * @param poistetutRivit Poistettujen rivien määrä.
     */
    public void vaikutaTilanteeseen(int poistetutRivit)
    {
        if(poistetutRivit > 0)
        {
            int edellisetPisteet = pelinTila.arvo(Pelitilanne.Tunniste.PISTEET);
            int seuraavatPisteet = edellisetPisteet + 2 * (poistetutRivit - 1) + 1;

            pelinTila.aseta(Pelitilanne.Tunniste.PISTEET, seuraavatPisteet);
            pelinTila.aseta(Pelitilanne.Tunniste.RIVIT, pelinTila.arvo(Pelitilanne.Tunniste.RIVIT) + poistetutRivit);

            if(poistetutRivit >= 8 || seuraavatPisteet % 8 <= edellisetPisteet % 8)
                pelinTila.aseta(Pelitilanne.Tunniste.VAIKEUSTASO, pelinTila.arvo(Pelitilanne.Tunniste.VAIKEUSTASO) + 1);
        }
    }
    
    /** Tutkii, onko pelialueella jotain päivitettävää.
     */
    private void tutkiPaivityksiaPelialueelta()
    {
        int poistetutRivit = 0;
        
        for(float rivi = 0; pelialue.palikoitaRivilla(rivi) > 0; rivi++)
            if(kasitteleRivi(rivi))
                poistetutRivit++;
        
        vaikutaTilanteeseen(poistetutRivit);
    }
    
    /** Tyhjentää kaikki pelialueella olevat muutokset.
     */
    public void tyhjennaMuutokset()
    {
        poistot.muutokset().clear();
        siirrot.muutokset().clear();
    }

    @Override public void paivita()
    {
        if(onValmis())
        {
            putoamiskohta = 0;
            tutkiPaivityksiaPelialueelta();
        }
        else if(muutostenAjastaja.onKulunut(20))
        {
            poistot.paivita();

            if(siirrot.onLaukaistu())
                siirrot.paivita();
            
            muutostenAjastaja.paivita();
        }
    }

    @Override public boolean onValmis()
    {
        return poistot.onValmis() && siirrot.onValmis();
    }
}
