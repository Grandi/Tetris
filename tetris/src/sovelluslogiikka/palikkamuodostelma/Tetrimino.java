//
//package sovelluslogiikka.palikkamuodostelma;
//
//import java.util.ArrayList;
//import sovelluslogiikka.Satunnaisuus;
//import sovelluslogiikka.Sijainti;
//
//public class Tetrimino implements Palikkamuodostelma
//{
//    private int sivunPituus, asento;
//    private Sijainti sijainti;
//    private ArrayList<Palikka> palikat;
//    
//    public Tetrimino(Sijainti sijainti)
//    {
//        this.asento = Satunnaisuus.jokinLuku(3);
//        this.sijainti = sijainti;
//        this.palikat = new ArrayList<Palikka>();
//    }
//    
//    public void muodostaSatunnaisesti()
//    {
//        tungePalikka(new Sijainti(0, 0));
//        
//        for(int i = 1; i <= 3; i++)
//            tungeViereen((TetrisPalikka)palikat.get(Satunnaisuus.jokinLuku(i)));
//    }
//    
//    public void tungeViereen(TetrisPalikka tetrisPalikka)
//    {
//        Sijainti sijainnit[] = tetrisPalikka.viereisetSijainnit();
//        Satunnaisuus.sekoita(sijainnit);
//        
//        for(Sijainti kokeiluSijainti : sijainnit)
//            if(tungePalikka(kokeiluSijainti))
//                return;
//    }
//    
//    public boolean sisaltaaPalikan(Sijainti sijainti)
//    {
//        for(Palikka palikka : palikat)
//            if(palikka.sijainti() == sijainti)
//                return true;
//
//        return false;
//    }
//    
//    public boolean tungePalikka(Sijainti sijainti)
//    {
//        if(sisaltaaPalikan(sijainti))
//            return false;
//        
//        palikat.add(new TetrisPalikka(sijainti, this));
//        return true;
//    }
//
//    @Override public Palikka[] palikat()
//    {
//        return palikat.toArray(new Palikka[palikat.size()]);
//    }
//    
//    public Sijainti sijainti()
//    {
//        return sijainti;
//    }
//    
//    public int asento()
//    {
//        return asento;
//    }
//    
//    public void kaannaMyotapaivaan()
//    {
//        asento++;
//        if(asento > 3)
//            asento = 0;
//    }
//    
//    public void kaannaVastapaivaan()
//    {
//        asento--;
//        if(asento < 0)
//            asento = 3;
//    }
//    
//    public int sivunPituus()
//    {
//        return sivunPituus;
//    }
//}
