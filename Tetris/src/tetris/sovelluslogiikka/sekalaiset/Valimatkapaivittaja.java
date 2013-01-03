
package tetris.sovelluslogiikka.sekalaiset;

/*
 * Tämä osoittautuikin melko turhaksi luokaksi. Tod.näk. poistan tulevaisuudessa.
 */
public class Valimatkapaivittaja
{
    private Sijainti pienin, suurin;
    
    public Valimatkapaivittaja() {}

    public Valimatkapaivittaja(Valimatkapaivittaja kopioitava)
    {
        this.pienin = new Sijainti(kopioitava.pienin);
        this.suurin = new Sijainti(kopioitava.suurin);
    }
    
    private void alusta(Sijainti sijainti)
    {
        pienin = new Sijainti(sijainti);
        suurin = new Sijainti(sijainti);
    }
    
    private void paivita(Sijainti sijainti)
    {
        if(pienin.x() > sijainti.x()) pienin.asetaX(sijainti.x());
        if(pienin.y() > sijainti.y()) pienin.asetaY(sijainti.y());

        if(suurin.x() < sijainti.x()) suurin.asetaX(sijainti.x());
        if(suurin.y() < sijainti.y()) suurin.asetaY(sijainti.y());
    }
    
    public void huomioi(Sijainti sijainti)
    {
        if(pienin == null)
            alusta(sijainti);
        else
            paivita(sijainti);
    }
    
    public int suurinXValimatka() { return pienin == null ? 0 : suurin.x() - pienin.x(); }
    public int suurinYValimatka() { return pienin == null ? 0 : suurin.y() - pienin.y(); }
    
    public int suurinValimatka()
    {
        return Math.max(suurinXValimatka(), suurinYValimatka());
    }
    
    public int suurinValimatka(Sijainti sijainti)
    {
        if(pienin == null)
            return 0;
        
        int xValimatka = Math.max(Math.abs(suurin.x() - sijainti.x()), Math.abs(sijainti.x() - pienin.x()));
        int yValimatka = Math.max(Math.abs(suurin.y() - sijainti.y()), Math.abs(sijainti.y() - pienin.y()));
        
        return Math.max(xValimatka, yValimatka);
    }
    
    public static double valimatka(Sijainti eka, Sijainti toka)
    {
        double xd = eka.x()-toka.x();
        double yd = eka.y()-toka.y();
        
        return Math.sqrt( xd*xd + yd*yd );
    }
}
