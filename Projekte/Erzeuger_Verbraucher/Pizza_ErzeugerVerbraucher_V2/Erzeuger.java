import java.util.Random;

/**
 * Write a description of class Erzeuger here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Erzeuger extends Thread
{
    Bild pizzaofen ;

    String name = "";
    int lfdNr = 1 ;
    Lager lager =null;

    Behaelter eB ;
    Rechteck eR ;
    Ausgabe eA ;

    static int hoch = 100 ;
    static int breit = 100 ;
    static int dy = (hoch - Kiste.hoch)/2;

    int posX ;
    int posY ;

    /**
     * Constructor for objects of class Erzeuger
     */
    public Erzeuger(int i , String name , Lager lager )
    {
        this.name = name ;
        this.lager = lager ;

        posX = 50 ;
        posY = 50+i*hoch; ;

        eB = new Behaelter(GUI.bFabriken, posX,posY,breit,hoch);
        eR = new Rechteck(eB,0,0,breit,hoch);
        eR.setzeFarbe("blau");

        pizzaofen = new Bild(eB,new Bilddatei("pizzaofen.jpg"));
        pizzaofen.setzePosition(0,10);

        //		eA = new Ausgabe(eB,getFirma(), 0, 5, breit, hoch);
        //		eA.setzeAusrichtung(1);

        this.start();
    }

    public String getFirma(){
        return name ;
    }

    private static Random zufall;
    public  int gibZufall(int zahl) {
        if (zufall == null) {
            zufall = new Random();
        }
        return zufall.nextInt(zahl + 1);
    }

    public void run ()
    {
        while (true){
            Kiste kiste = new Kiste( this , lfdNr);
            lfdNr++;
            int i = 0 ;
            int dT=gibZufall(GUI.eRegler.leseIntWert())+ 90;
            while ( i < Kiste.breit){
                try {
                    Thread.sleep(dT);
                } catch (Exception e) {
                    // Exception ignorieren
                }	
                i = i + 2 ;

                kiste.verschieben(i);
            }
            kiste.endpositionErzeuger();
            //            System.out.println("Erzeuger -- erzeugt: "+kiste.leseInfo());
            kiste.setzeFrabe("gruen");

            ablegen(kiste,lager);
        }

    }

    synchronized public static  void  ablegen  (Kiste kiste , Lager lager ){
        lager.zumLager(kiste);
        lager.ablegen(kiste);
    }

}

