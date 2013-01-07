
package tetris.sovelluslogiikka.sekalaiset;

/** Pitää kirjaa alkupisteestä ja päätepisteestä, jotka muodostavat yhdessä suorakulmionmuotoisen alueen.
 * @author grandi
 */
public class Alue
{
    private Sijainti alkupiste, paatepiste;
    
    /**
     * @param alku Alueen vasen yläkulma.
     * @param loppu Alueen oikea alakulma.
     */
    public Alue(Sijainti alku, Sijainti loppu)
    {
        this.alkupiste = alku;
        this.paatepiste = loppu;
    }
    
    /**
     * @param x Alueen vasemman yläkulman x-koordinaatti.
     * @param y Alueen oikean yläkulman y-koordinaatti.
     * @param leveys Alueen leveys.
     * @param korkeus Alueen korkeus.
     */
    public Alue(float x, float y, float leveys, float korkeus)
    {
        this(new Sijainti(x, y), (int)leveys, (int)korkeus);
    }
    
    /**
     * @param alku Alueen vasen yläkulma.
     * @param leveys Alueen leveys.
     * @param korkeus Alueen korkeus.
     */
    public Alue(Sijainti alku, int leveys, int korkeus)
    {
        this(alku, new Sijainti(alku.x() + leveys, alku.y() + korkeus));
    }
    
    /** Kertoo onko piste alueen sisällä.
     * @param sijainti Piste, jonka sisälläolemista tarkastellaan.
     * @return Palauttaa true, mikäli piste on alueen sisäpuolella. Muutoin false.
     */
    public boolean onSisalla(Sijainti sijainti)
    {
        return sijainti.x() >= alkupiste.x() && sijainti.y() >= alkupiste.y() && sijainti.x() <= paatepiste.x() && sijainti.y() <= paatepiste.y();
    }
    
    /** Kertoo missä pisteessä on alueen vasen yläkulma.
     * @return Alueen vasen yläkulma.
     */
    public Sijainti alkupiste()
    {
        return alkupiste;
    }
    
    /** Kertoo missä pisteessä on alueen oikea alakulma.
     * @return Alueen oikea alakulma.
     */
    public Sijainti paatepiste()
    {
        return paatepiste;
    }
    
    /** Kertoo alueen leveyden.
     * @return Alueen leveys.
     */
    public float leveys()
    {
        return Math.abs(paatepiste.x() - alkupiste.x());
    }
    
    /** Kertoo alueen korkeuden.
     * @return Alueen korkeus.
     */
    public float korkeus()
    {
        return Math.abs(paatepiste.y() - alkupiste.y());
    }
    
    /** 
     * Palauttaa alueen alkupisteen ja päätepisteen sijainnit merkkijonona.
     * @return Alueen merkkijonomuotoinen esitystapa.
     */
    @Override public String toString()
    {
        return "Alue [" + alkupiste + "," + paatepiste + "]";
    }
}
