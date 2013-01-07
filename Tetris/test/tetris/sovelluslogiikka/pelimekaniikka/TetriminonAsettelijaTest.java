/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.sovelluslogiikka.pelimekaniikka;

import tetris.sovelluslogiikka.pelimekaniikka.TetriminonAsettelija;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.sovelluslogiikka.tetrimino.Tetrimino;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tetris.Apufunktiot;
import tetris.sovelluslogiikka.sekalaiset.Suunta;
import static org.junit.Assert.*;

public class TetriminonAsettelijaTest
{
    private Tetrimino tetrimino;
    private Pelialue pelialue;
    private TetriminonAsettelija asettelija;
    
    @Before public void setUp()
    {
        this.tetrimino = Apufunktiot.luoTetriminoTestaamistaVarten(new Sijainti(8, 5));
        this.pelialue = new Pelialue(new Sijainti(0, 0), 10, 20);
        
        this.asettelija = new TetriminonAsettelija(tetrimino, pelialue);
    }
    
    @Test public void tietaaKunEiTormaa()
    {
        assertFalse(asettelija.tormaa());
    }
    
    @Test public void antaaSiirtaaHyviinPaikkoihin()
    {
        assertTrue(new Sijainti(8, 5).equals(asettelija.aseteltuTetrimino().siirtyma()));
        
        assertTrue(asettelija.kokeileSiirtaa(Suunta.VASEN));
        assertTrue(new Sijainti(7, 5).equals(asettelija.aseteltuTetrimino().siirtyma()));
        
        assertTrue(asettelija.kokeileSiirtaa(Suunta.ALAS));
        assertTrue(new Sijainti(7, 6).equals(asettelija.aseteltuTetrimino().siirtyma()));
    }
    
    @Test public void eiAnnaSiirtaaHuonoihinPaikkoihin()
    {
        assertFalse(asettelija.kokeileSiirtaa(Suunta.OIKEA));
        assertTrue(new Sijainti(8, 5).equals(asettelija.aseteltuTetrimino().siirtyma()));
    }
    
    @Test public void antaaKaannellaHyviinAsentoihin()
    {
        assertTrue(asettelija.kokeileKaantaa(Suunta.OIKEA));
        assertEquals(1, asettelija.aseteltuTetrimino().asento());
    }
    
    @Test public void osaaKaantaessaSiirtaaTarvittaessaParempaanPaikkaan()
    {
        Sijainti siirtyma = new Sijainti(tetrimino.siirtyma());
        
        pelialue.tungePalikka(Apufunktiot.uusiPalikka(10, 7));
        assertTrue(asettelija.kokeileKaantaa(Suunta.OIKEA));
        
        siirtyma.asetaX(siirtyma.x()-1);
        assertTrue(asettelija.aseteltuTetrimino().siirtyma().equals(siirtyma));
    }
    
    @Test public void eiAnnaKaannellaHuonoihinAsentoihinJoissaSijaintiaEiVoisiEdesKorjata()
    {
        pelialue.tungePalikka(Apufunktiot.uusiPalikka(8, 6));
        assertFalse(asettelija.kokeileKaantaa(Suunta.OIKEA));
    }
    
    private boolean tetriminoOnPelialueenLattialla()
    {
        for(Palikka palikka : tetrimino.palikkakokoelma().palikat())
            if(palikka.sijainti().y() == pelialue.alue().paatepiste().y())
                return true;
        
        return false;
    }
    
    @Test public void tetriminonVoiPudottaaAlas()
    {
        asettelija.pudotaAlas();
        assertTrue(tetriminoOnPelialueenLattialla());
    }
    
    @Test public void tetriminonPudottamisessaHuomioidaanAllaolevatPalikatkin()
    {
        pelialue.tungePalikka(Apufunktiot.uusiPalikka(8, 20));
        assertFalse(tetriminoOnPelialueenLattialla());
    }
}
