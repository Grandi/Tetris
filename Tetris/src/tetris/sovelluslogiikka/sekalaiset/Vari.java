
package tetris.sovelluslogiikka.sekalaiset;

/** Määrittää asian värin. Tämä on melko surkuhupaisa luokka, koska:
 * a) kommenttia on enemmän kuin itse koodia.
 * b) Javasta löytyisi jo valmis Color-luokka.
 * @author grandi
 */
public class Vari
{
    private int punainen, vihrea, sininen, peittavyys;

    /** Asettaa värin.
     * @param r Punaisen määrä, lukuarvo väliltä 0-255.
     * @param g Vihreän määrä, lukuarvo väliltä 0-255.
     * @param b Sinisen määrä, lukuarvo väliltä 0-255.
     * @param a Läpinäkyvyys, lukuarvo väliltä 0-255.s
     */
    final public void aseta(int r, int g, int b, int a)
    {
        asetaPunainen(r);
        asetaVihrea(g);
        asetaSininen(b);
        asetaPeittavyys(a);
    }    
    
    /** Asettaa värin samaksi kuin toinen väri-olio.
     * @param vari Väri, josta värisävyn lukuarvot kopioidaan.
     */
    final public void aseta(Vari vari)
    {
        aseta(vari.punainen(), vari.vihrea(), vari.sininen(), vari.peittavyys());
    }
    
    /**
     * @param r Värin punaisuus, lukuarvo väliltä 0-255.
     * @param g Värin vihreys, lukuarvo väliltä 0-255.
     * @param b Värin sinisyys, lukuarvo väliltä 0-255.
     * @param a Värin peittävyys, lukuarvo väliltä 0-255.
     */
    public Vari(int r, int g, int b, int a)
    {
        aseta(r, g, b, a);
    }
    
    /** Kopioi värin toisesta väri-oliosta.
     * @param Kopioitava väri.
     */
    public Vari(Vari kopioitava)
    {
        aseta(kopioitava);
    }
    
    /** Varmistaa, että väriarvo on väliltä 0-255. Korvaa liian suuret ja pienet arvot toimivilla raja-arvoilla.
     * @param arvo Arvo, jonka oikeellisuus tarkistetaan.
     * @return Lukuarvo väliltä 0-255.
     */
    public int varmistaArvonOikeallisuus(int arvo)
    {
        if(arvo > 255)
            return 255;
        else if(arvo < 0)
            return 0;
        else
            return arvo;
    }
    
    /** Asettaa värin punaisuuden.
     * @param r Lukuarvo väliltä 0-255.
     */
    public void asetaPunainen(int r)
    {
        this.punainen = varmistaArvonOikeallisuus(r);
    }
    
    /** Asettaa värin vihreyden.
     * @param g Lukuarvo väliltä 0-255.
     */
    public void asetaVihrea(int g)
    {
        this.vihrea = varmistaArvonOikeallisuus(g);
    }
    
    /** Asettaa värin sinisyyden.
     * @param b Lukuarvo väliltä 0-255.
     */
    public void asetaSininen(int b)
    {
        this.sininen = varmistaArvonOikeallisuus(b);
    }
    
    /* Asettaa värin peittävyyden.
     * @param a Lukuarvo väliltä 0-255.
     */
    public void asetaPeittavyys(int a)
    {
        this.peittavyys = varmistaArvonOikeallisuus(a);
    }
    
    /** Palauttaa värin punaisuuden.
     * @return Värin punaisuus, lukuarvo väliltä 0-255.
     */
    public int punainen()
    {
        return this.punainen;
    }
    
    /** Palauttaa värin vihreyden.
     * @return Värin vihreys, lukuarvo väliltä 0-255.
     */
    public int vihrea()
    {
        return this.vihrea;
    }
    
    /** Palauttaa värin sinisyyden.
     * @return Värin sinisyys, lukuarvo väliltä 0-255.
     */
    public int sininen()
    {
        return this.sininen;
    }
    
    /** Palauttaa värin läpinäkyvyyden.
     * @return Värin peittävyys, lukuarvo väliltä 0-255.
     */
    public int peittavyys()
    {
        return peittavyys;
    }
    
    /** Kertoo onko väri sama kuin vertailtava väri.
     * @param vertailtava Vertailtava väri.
     * @return Palauttaa true, mikäli väriarvot ovat samat kuin vertailtavassa värissä.
     */
    public boolean equals(Vari vertailtava)
    {
        return punainen == vertailtava.punainen() &&
               vihrea == vertailtava.vihrea() &&
               sininen == vertailtava.sininen();
    }
}
