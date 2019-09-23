import java.util.Random;

/**
 * Write a description of class Verbraucher here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Verbraucher extends Thread {
	String name;
	Lager lager = null;

	Behaelter vB;
	Rechteck vR;
	Ausgabe vA;

	static int hoch =100;
	static int breit=100;
	static int dy = (hoch - Kiste.hoch) / 2;

	int posX;
	int posY;

	public Verbraucher(int i, String name, Lager lager) {
		this.name = name;
		this.lager = lager;

		posX = GUI.guiBreit - breit - 50;
		posY = 50 + i * hoch;

		vB = new Behaelter(GUI.bFabriken, posX, posY, breit, hoch);
		vR = new Rechteck(vB, 0, 0, breit, hoch);
		vR.setzeFarbe("blau");

		vA = new Ausgabe(vB, name, 0, 5, breit, hoch);
		vA.setzeAusrichtung(1);

		this.start();
	}

	private static Random zufall;

	public int gibZufall(int zahl) {
		if (zufall == null) {
			zufall = new Random();
		}
		return zufall.nextInt(zahl + 1);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(gibZufall(2000) + 1000);
			} catch (Exception e) {
				// Exception ignorieren
			}

			System.out.println("Verbraucher -- abgeholen von: " + name);

			Kiste kiste = abholen(this,lager);
            kiste.setzeFrabe("orange");
			
            int i = 0 ;
            int dT=gibZufall(GUI.vRegler.leseIntWert())+ 10;
            while ( i < Kiste.breit){
            	try {
                    Thread.sleep(dT);
                } catch (Exception e) {
                // Exception ignorieren
                }	
                i = i + 2 ;
                
                kiste.verschieben(i);
            }
			
			
			System.out.println("Verbraucher -- abgeholt: " + kiste.leseInfo()
					+ " von " + name);
		}
	}
	
    synchronized public static  Kiste  abholen  ( Verbraucher verbraucher , Lager lager ){
    	
    	Kiste kiste = lager.abholen(verbraucher);
    	lager.zumVerbraucher(kiste,verbraucher);
    	
    	return kiste ;
    }


}
