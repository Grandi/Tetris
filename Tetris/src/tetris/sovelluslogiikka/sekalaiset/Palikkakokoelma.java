
package tetris.sovelluslogiikka.sekalaiset;

import java.util.ArrayList;

public interface Palikkakokoelma
{
    public boolean tungePalikka(Palikka palikka);
    public Palikka sisaltaaPalikan(Sijainti sijainti);
    public ArrayList<Palikka> palikat();
    public int lisattyja();
}
