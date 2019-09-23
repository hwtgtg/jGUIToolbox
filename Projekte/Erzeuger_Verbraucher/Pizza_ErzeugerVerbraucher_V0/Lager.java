/**
 * Write a description of class Lager here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lager {

    public static int bLagerHoch = 500;
    public static int bLagerBreit = 100;
    public static int bLagerX = (GUI.guiBreit - bLagerBreit) / 2;
    public static int bLagerY = 50;

    Linie aufzugUL;
    Linie aufzugUR;
    int xAufzugL;
    int xAufzugR;
    int yAufzugOben;
    int yAufzugUnten;
    int xAufzugLL;
    int xAufzugLR;
    int xAufzugRL;
    int xAufzugRR;
    int yUAufzugUntenL;
    int yUAufzugUntenR;

    Kiste[] kistenplatz;
    int maxKisten = 6;
    int kistenAnzahl = 0; // 0 = leer
    Rechteck rLager;

    /**
     * Constructor for objects of class Packstation
     */
    public Lager() {
        rLager = new Rechteck(GUI.bLager, bLagerX, bLagerY, bLagerBreit,
            bLagerHoch);
        rLager.setzeFarbe("gelb");
        xAufzugL = bLagerX - Kiste.breit / 2;
        xAufzugR = bLagerX + bLagerBreit + Kiste.breit / 2;
        yAufzugOben = bLagerY - 50;
        yAufzugUnten = bLagerY + bLagerHoch - Kiste.hoch;

        xAufzugLL = bLagerX - Kiste.breit;
        xAufzugLR = bLagerX;
        xAufzugRL = bLagerX + bLagerBreit;
        xAufzugRR = bLagerX + bLagerBreit + Kiste.breit;
        yUAufzugUntenR = bLagerY + bLagerHoch;
        yUAufzugUntenL = bLagerY + bLagerHoch - (maxKisten - 1) * Kiste.hoch;
        aufzugUL = new Linie(GUI.bLager, xAufzugLL, yUAufzugUntenL, xAufzugLR,
            yUAufzugUntenL);
        aufzugUL.setzeFarbe("blau");
        aufzugUR = new Linie(GUI.bLager, xAufzugRL, yUAufzugUntenR, xAufzugRR,
            yUAufzugUntenR);
        aufzugUR.setzeFarbe("blau");

        kistenplatz = new Kiste[maxKisten];
        kistenAnzahl = 0;
    }

    public  void ablegen(Kiste kiste) {

        while (kistenAnzahl >= maxKisten ) { // voll
            try {
                wait();
            } catch (InterruptedException ex) {
            }
            ;
        }

        zumLager(kiste);
        insLager(kiste);

        kistenAnzahl++;
        kistenplatz[kistenAnzahl - 1] = kiste;
        // System.out.println("Lager -- abgelegt: " + kiste.leseInfo());
        notifyAll();
    }

    public  Kiste abholen(Verbraucher verbraucher) {
        while (kistenAnzahl <= 0) { // Leer
            try {
                wait();
            } catch (InterruptedException ex) {
            }
            ;
        }

        Kiste kiste = kistenplatz[0];

        ausDemLager(kiste);

        for (int i = 1; i < kistenAnzahl ; i++) {
            kistenplatz[i-1]=kistenplatz[i];
        }
        kistenplatz[kistenAnzahl-1] = null;
        kistenAnzahl--;

        verschieben();

        zumVerbraucher(kiste, verbraucher);

        // System.out.println("Lager -- abgeholt: " + kiste.leseInfo());
        notifyAll();
        return kiste;
    }

    private void insLager(Kiste kiste) {
        int lagerX = bLagerX - Kiste.breit;
        int lagerY = bLagerY + bLagerHoch - Kiste.hoch;

        // nach rechts
        int dx = 5;
        int dy = 0;

        int rechts = Kiste.breit + (bLagerBreit - Kiste.breit) / 2;
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
        kiste.setzePosition(lagerX + rechts, yUAufzugUntenL - Kiste.hoch);

        // runter

        dx = 0;
        dy = 5;
        int runter = (maxKisten - kistenAnzahl - 1) * Kiste.hoch;
        i = 0;
        while (i < runter) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                // Exception ignorieren
            }
            i += dy;
            kiste.verschieben(dx, dy);
        }
        kiste.setzePosition(lagerX+ rechts, lagerY - kistenAnzahl*Kiste.hoch );

    }

    private void zumLager(Kiste kiste) {
        int lagerX = bLagerX - Kiste.breit;
        int lagerY = bLagerY + bLagerHoch - maxKisten * Kiste.hoch;

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
        kiste.setzePosition(bLagerX + bLagerBreit, lagerY);

    }

    void verschieben(){
        if (kistenAnzahl==0)return; // nichts zu tun
        int lagerX = bLagerX - Kiste.breit;
        int lagerY = bLagerY + bLagerHoch - Kiste.hoch;
        int rechts = Kiste.breit + (bLagerBreit - Kiste.breit) / 2;

        // runter
        int dx = 0;
        int dy = 2;
        int runter = Kiste.hoch;
        int i = 0;
        while (i < runter) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                // Exception ignorieren
            }
            i += dy;
            for (int j = 0; j < kistenAnzahl ; j++) {
                kistenplatz[j].verschieben(dx, dy);
            }
        }
        for (int j = 0; j < kistenAnzahl ; j++) {
            kistenplatz[j].setzePosition(lagerX+ rechts, lagerY - j*Kiste.hoch );
        }

    }

	
    private void zumVerbraucher(Kiste kiste, Verbraucher verbraucher) {
        int verbraucherX = verbraucher.posX - Kiste.breit;
        int verbraucherY = verbraucher.posY + Verbraucher.dy;

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
