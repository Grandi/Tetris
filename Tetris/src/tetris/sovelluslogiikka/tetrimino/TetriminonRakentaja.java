
package tetris.sovelluslogiikka.tetrimino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;

public class TetriminonRakentaja
{
    private Tetrimino tetrimino;
    private Random satunnaisgeneraattori;
    
    public TetriminonRakentaja(Tetrimino tetrimino, Random satunnaisgeneraattori)
    {
        this.tetrimino = tetrimino;
        this.satunnaisgeneraattori = satunnaisgeneraattori;
    }
    
    public TetriminonRakentaja()
    {
        this.tetrimino = new Tetrimino();
        this.satunnaisgeneraattori = new Random();
    }
    
    public void tungeUusiaSatunnaisiaPalikoita(int haluttuMaara)
    {
        while(tetrimino.palikkakokoelma().lisattyja() < haluttuMaara)
            tungeUusiPalikkaSattumanvaraisesti();
    }
    
    public void luoTyypillinenTetrisPalikka()
    {
        tungeUusiaSatunnaisiaPalikoita(4);
    }    
    
    public TetriminoPalikka uusiPalikka(Sijainti sijainti)
    {
        return new TetriminoPalikka(sijainti, tetrimino);
    }
    
    private void tungeEnsimmainenPalikka()
    {
        tetrimino.palikkakokoelma().tungePalikka(uusiPalikka(new Sijainti(0, 0)));
    }
    
    public boolean tungeUusiPalikkaSattumanvaraisesti()
    {
        if(tetrimino.palikkakokoelma().lisattyja() > 0)
            return tungeUusiPalikkaJonkinEdellisenViereen();
        
        tungeEnsimmainenPalikka();
        return true;
    }
    
    private boolean tungeUusiPalikkaJonkinEdellisenViereen()
    {
        ArrayList<Palikka> palikat = tetrimino.palikkakokoelma().palikat();
        int indeksi = satunnaisgeneraattori.nextInt(palikat.size());

        return tungeUusiPalikkaSattumanvaraisesti(((TetriminoPalikka)palikat.get( indeksi )).viereisetSijainnit());
    }
    
    private boolean tungeUusiPalikkaSattumanvaraisesti(ArrayList<Sijainti> sijainnit)
    {
        Collections.shuffle(sijainnit);
        
        for(Sijainti viereinen : sijainnit)
            if(tetrimino.palikkakokoelma().tungePalikka(uusiPalikka(viereinen)))
                return true;
        
        return false;
    }
    
    public Tetrimino rakennettuTetrimino()
    {
        return tetrimino;
    }
    
    private int leveys(boolean[][] array)
    {
        int leveys = 0;
        for(boolean[] sub : array)
            leveys = Math.max(leveys, sub.length);
        return leveys;
    }
    
    private boolean kokeileTunkeaUusi(Sijainti sijainti)
    {
        Sijainti kohta = new Sijainti(sijainti.x(), sijainti.y());
        return tetrimino.palikkakokoelma().tungePalikka(uusiPalikka(kohta));
    }
    
    public boolean rakennaMallista(Sijainti sijainti, boolean[][] malli)
    {
        int leveys = leveys(malli);

        rakenna(malli, new Sijainti(1, 1), sijainti);        
        return tetrimino.palikkakokoelma().lisattyja() == 4;
    }
    
    private boolean onUlkopuolella(boolean[][] malli, Sijainti sijainti)
    {
        return sijainti.y() < 0 || sijainti.y() >= malli.length || sijainti.x() < 0 || sijainti.x() >= malli[sijainti.y()].length;
    }
    
    private void rakenna(boolean[][] malli, Sijainti haku, Sijainti sijainti)
    {
        if(onUlkopuolella(malli, haku) || !malli[haku.y()][haku.x()])
            return;
        
        malli[haku.y()][haku.x()] = false;
        Sijainti oikeaSijainti = new Sijainti(sijainti.x() + haku.x() - 1, sijainti.y() + haku.y() - 1);
        tetrimino.palikkakokoelma().tungePalikka(uusiPalikka(oikeaSijainti));
        
        rakenna(malli, new Sijainti(haku.x() - 1, haku.y()), sijainti);
        rakenna(malli, new Sijainti(haku.x() + 1, haku.y()), sijainti);
        rakenna(malli, new Sijainti(haku.x(), haku.y() - 1), sijainti);
        rakenna(malli, new Sijainti(haku.x(), haku.y() + 1), sijainti);
    }
}
