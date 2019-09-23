/**
 * Write a description of class Lager here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lager {

	public static int bLagerHoch = 500;
	public static int bLagerBreit = 100;
	public static int bLagerX = (GUI.guiBreit-bLagerBreit)/2;
	public static int bLagerY = 50;

	Linie aufzugL ;
	Linie aufzugUL ;
	Linie aufzugR ;
	Linie aufzugUR ;
	int xAufzugL ;
	int xAufzugR ;
	int yAufzugOben ;
	int yAufzugUnten ;
	int xAufzugLL ;
	int xAufzugLR ;
	int xAufzugRL ;
	int xAufzugRR ;
	int yUAufzugUnten ;
	
	
	Kiste[] kistenplatz;
	int maxKisten = 6 ;
	int kistenAnzahl = 0; // 0 = leer
	Rechteck rLager;

	/**
	 * Constructor for objects of class Packstation
	 */
	public Lager() {
		rLager = new Rechteck(GUI.bLager, bLagerX, bLagerY, bLagerBreit,
				bLagerHoch);
		rLager.setzeFarbe("gelb");
		xAufzugL=bLagerX-Kiste.breit/2;
		xAufzugR=bLagerX+bLagerBreit+Kiste.breit/2;
		yAufzugOben = bLagerY - 50 ;
		yAufzugUnten = bLagerY+bLagerHoch-Kiste.hoch;
		
		xAufzugLL=bLagerX-Kiste.breit;
		xAufzugLR=bLagerX;
		xAufzugRL=bLagerX+bLagerBreit;
		xAufzugRR=bLagerX+bLagerBreit+Kiste.breit;
		yUAufzugUnten=bLagerY+bLagerHoch;
		
		aufzugL = new Linie(GUI.bLager, xAufzugL, yAufzugOben, xAufzugL, yAufzugUnten);
		aufzugL.setzeFarbe("blau");
		aufzugUL=new Linie(GUI.bLager, xAufzugLL, yUAufzugUnten, xAufzugLR, yUAufzugUnten);
		aufzugUL.setzeFarbe("blau");
		aufzugR = new Linie(GUI.bLager, xAufzugR, yAufzugOben, xAufzugR, yAufzugUnten);
		aufzugR.setzeFarbe("blau");
		aufzugUR=new Linie(GUI.bLager, xAufzugRL, yUAufzugUnten, xAufzugRR, yUAufzugUnten);
		aufzugUR.setzeFarbe("blau");

		kistenplatz = new Kiste[maxKisten];
		kistenAnzahl = 0;
	}

	public synchronized void ablegen(Kiste kiste) {
		while (kistenAnzahl > maxKisten - 1) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
			;
		}

		// Bei Erzeuger:  zumLager(kiste);
		insLager(kiste);

		kistenAnzahl++;
		kistenplatz[kistenAnzahl - 1] = kiste;
		System.out.println("Lager -- abgelegt: " + kiste.leseInfo());
		notifyAll();
	}
	
	public synchronized Kiste abholen(Verbraucher verbraucher) {
		while (kistenAnzahl <= 0) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
			;
		}

		Kiste kiste = kistenplatz[kistenAnzahl - 1];

		ausDemLager(kiste);
		// Beim Verbraucher: zumVerbraucher(kiste,verbraucher);

		kistenplatz[kistenAnzahl - 1] = null;
		kistenAnzahl--;

		System.out.println("Lager -- abgeholt: " + kiste.leseInfo());
		notifyAll();
		return kiste;
	}

	private void insLager(Kiste kiste) {
		int lagerX = bLagerX - Kiste.breit;
		int lagerY = bLagerY + bLagerHoch - Kiste.hoch;

		int dx = 0;
		int dy = 5;
		int rauf = kistenAnzahl * Kiste.hoch;
		int i = 0;
		while (i < rauf) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// Exception ignorieren
			}
			i += dy;
			kiste.verschieben(dx, -dy);
			aufzugL.setzeEndpunkte(xAufzugL, yAufzugOben, xAufzugL, yAufzugUnten-i);
			aufzugUL.setzeEndpunkte( xAufzugLL, yUAufzugUnten-i, xAufzugLR, yUAufzugUnten-i);
		}
		kiste.setzePosition(lagerX, lagerY - rauf);
		dx = 5;
		dy = 0;

		int rechts = Kiste.breit + (bLagerBreit - Kiste.breit) / 2;
		i = 0;
		while (i < rechts) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// Exception ignorieren
			}
			i += dx;
			kiste.verschieben(dx, dy);
		}
		kiste.setzePosition(lagerX + rechts, lagerY - rauf);
		aufzugL.setzeEndpunkte(xAufzugL, yAufzugOben, xAufzugL, yAufzugUnten);
		aufzugUL.setzeEndpunkte( xAufzugLL, yUAufzugUnten, xAufzugLR, yUAufzugUnten);

	}

	public void zumLager(Kiste kiste) {
		int lagerX = bLagerX - Kiste.breit;
		int lagerY = bLagerY + bLagerHoch - Kiste.hoch;

		int dx = (lagerX - kiste.posX) / 10;
		int dy = (lagerY - kiste.posY) / 10;

		int i = 0;
		while (i < 9) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// Exception ignorieren
			}
			i++;
			kiste.verschieben(dx, dy);
		}
		kiste.setzePosition(lagerX, lagerY);

	}

	private void ausDemLager(Kiste kiste) {
		int lagerY = bLagerY + bLagerHoch - Kiste.hoch;

		int dx = 5;
		int dy = 0;

		int rechts = bLagerX + bLagerBreit - kiste.posX;

		aufzugR.setzeEndpunkte(xAufzugR, yAufzugOben, xAufzugR, kiste.posY);
		aufzugUR.setzeEndpunkte( xAufzugRL, kiste.posY+Kiste.hoch, xAufzugRR, kiste.posY+Kiste.hoch);
		int i = 0;
		while (i < rechts) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// Exception ignorieren
			}
			i += dx;
			kiste.verschieben(dx, dy);
		}
		kiste.setzePosition(bLagerX + bLagerBreit, kiste.posY);

		dx = 0;
		dy = 5;
		int runter = (kistenAnzahl-1) * Kiste.hoch;
		i = 0;
		while (i < runter) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// Exception ignorieren
			}
			i += dy;
			kiste.verschieben(dx, dy);
			aufzugR.setzeEndpunkte(xAufzugR, yAufzugOben, xAufzugR, kiste.posY);
			aufzugUR.setzeEndpunkte( xAufzugRL, kiste.posY+Kiste.hoch, xAufzugRR, kiste.posY+Kiste.hoch);
		}
		kiste.setzePosition(bLagerX + bLagerBreit, lagerY);
		aufzugR.setzeEndpunkte(xAufzugR, yAufzugOben, xAufzugR, yAufzugUnten);
		aufzugUR.setzeEndpunkte( xAufzugRL, yUAufzugUnten, xAufzugRR, yUAufzugUnten);

	}
	
	public void zumVerbraucher(Kiste kiste,Verbraucher verbraucher) {
		int verbraucherX = verbraucher.posX  - Kiste.breit;
		int verbraucherY = verbraucher.posY+Verbraucher.dy ;

		int dx = (verbraucherX - kiste.posX) / 10;
		int dy = (verbraucherY - kiste.posY) / 10;

		int i = 0;
		while (i < 9) {
			try {
				Thread.sleep(70);
			} catch (Exception e) {
				// Exception ignorieren
			}
			i++;
			kiste.verschieben(dx, dy);
		}
		kiste.setzePosition(verbraucherX, verbraucherY);

	}

}
