
package sovelluslogiikka.pelialue;

public class Palikkarivi
{
    private int leveys, lisattyja;
    private boolean palikat[];
    
    public Palikkarivi(int leveys)
    {
        this.leveys = leveys;
        this.palikat = new boolean[leveys];
        this.lisattyja = 0;
    }
    
    public int lisattyjaPalikoita()
    {
        return lisattyja;
    }
    
    public boolean onTaysi()
    {
        return lisattyja == leveys;
    }
    
    public boolean sisaltaaPalikan(int kohta)
    {
        return kohta >= 0 && kohta < leveys && palikat[kohta];
    }

    public boolean tungePalikka(int kohta)
    {
        if(kohta < 0 || kohta >= leveys || palikat[kohta])
            return false;
        
        palikat[kohta] = true;
        lisattyja++;
        return true;
    }
}
