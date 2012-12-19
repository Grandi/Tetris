//
//package sovelluslogiikka.palikkamuodostelma;
//
//import sovelluslogiikka.Sijainti;
//
//public class TetrisPalikka implements Palikka
//{
//    private Tetrimino tetrimino;
//    private Sijainti sijainti;
//    
//    public TetrisPalikka(Sijainti sijainti, Tetrimino tetrimino)
//    {
//        this.tetrimino = tetrimino;
//        this.sijainti = sijainti;
//    }
//    
//    Sijainti[] viereisetSijainnit()
//    {
//        return new Sijainti[]
//        {
//            new Sijainti(sijainti().x() - 1, sijainti().y()),
//            new Sijainti(sijainti().x() + 1, sijainti().y()),
//            new Sijainti(sijainti().x(), sijainti().y() - 1),
//            new Sijainti(sijainti().x(), sijainti().y() + 1),
//        };
//    }
//    
//    @Override public Sijainti sijainti()
//    {
//        return sijainti;
//    }
//}
