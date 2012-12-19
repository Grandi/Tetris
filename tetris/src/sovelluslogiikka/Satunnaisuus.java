//
//package sovelluslogiikka;
//
//import java.util.Random;
//
//public class Satunnaisuus
//{
//    private static Random generoija = new Random();
//    
//    public static void asetaSiemenluku(long siemenluku)
//    {
//        generoija.setSeed(siemenluku);
//    }
//    
//    public static int jokinLuku(int suurin)
//    {
//        if(suurin <= 0)
//            return 0;
//        else
//            return generoija.nextInt(suurin);
//    }
//    
//    public static int jokinLuku(int pienin, int suurin)
//    {
//        if(pienin == suurin)
//            return pienin;
//        else if(pienin > suurin)
//            return jokinLuku(suurin, pienin);
//        else
//            return generoija.nextInt(Math.abs(suurin - pienin)) + pienin;
//    }
//    
//    public static void sekoita(Object[] taulukko)
//    {
//        for(int i = 0; i < taulukko.length; i++)
//        {
//            int vaihdettava = jokinLuku(i);
//            
//            Object valiaikainen = taulukko[vaihdettava];
//            taulukko[vaihdettava] = taulukko[i];
//            taulukko[i] = valiaikainen;
//        }
//    }
//}
