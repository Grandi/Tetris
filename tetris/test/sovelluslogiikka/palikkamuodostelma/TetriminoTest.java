//package sovelluslogiikka.palikkamuodostelma;
//
//import sovelluslogiikka.Sijainti;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class TetriminoTest
//{
//    private Tetrimino tetrimino;
//    
//    @Before public void setUp()
//    {
//        tetrimino = new Tetrimino(new Sijainti(2, 5));
//    }
//    
//    @Test public void onAlussaHyvassaAsennossa()
//    {
//        assertTrue(tetrimino.asento() >= 0 && tetrimino.asento() <= 3);
//    }
//    
//    @Test public void kaantyyOikeinMyotapaivaan()
//    {
//        int alkuAsento = tetrimino.asento();
//        
//        for(int i = 0; i < 5; i++)
//            tetrimino.kaannaMyotapaivaan();
//        
//        assertEquals((alkuAsento + 5) % 4, tetrimino.asento());
//    }
//    
//    @Test public void kaantyyOikeinVastapaivaan()
//    {
//        int asento = tetrimino.asento();
//        
//        for(int i = 0; i < 5; i++)
//        {
//            tetrimino.kaannaVastapaivaan();
//            
//            if(--asento < 0)
//                asento = 3;
//        }
//        
//        assertEquals(asento, tetrimino.asento());
//    }
//    
//    @Test public void palikanTunkeminenOnnistuu()
//    {
//        Sijainti sijainti = new Sijainti(5, 3);
//        
//        assertTrue(tetrimino.tungePalikka(sijainti));
//        assertTrue(tetrimino.sisaltaaPalikan(sijainti));
//    }
//    
//    @Test public void kahtaPalikkaaEiVoiTunkeaSamaanKohtaan()
//    {
//        Sijainti sijainti = new Sijainti(5, 3);
//        tetrimino.tungePalikka(sijainti);
//        
//        assertFalse(tetrimino.tungePalikka(sijainti));
//    }
//    
//    @Test public void palikoitaEiLoydyPaikoistaJoissaNiitaEiOle()
//    {
//        assertFalse(tetrimino.sisaltaaPalikan(new Sijainti(-3, 5)));
//    }
//    
//    @Test public void palikoidenHakeminenToimii()
//    {
//        Palikka palikat[] = new Palikka[]
//        {
//            new TetrisPalikka(new Sijainti(5, 3), tetrimino),
//            new TetrisPalikka(new Sijainti(1, 2), tetrimino),
//            new TetrisPalikka(new Sijainti(0, 9), tetrimino)
//        };
//        
//        for(Palikka palikka : palikat)
//            tetrimino.tungePalikka(palikka.sijainti());
//        
//        Palikka haetut[] = tetrimino.palikat();
//        assertEquals(palikat.length, haetut.length);
//        
//        for(int i = 0; i < palikat.length; i++)
//            assertEquals(palikat[i].sijainti(), haetut[i].sijainti());
//    }
//}
