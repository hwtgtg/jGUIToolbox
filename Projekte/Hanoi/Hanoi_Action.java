//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Hanoi_Action implements Runnable {
    
    int delta = 300 ;
    
    Hanoi_Hauptfenster  buehne;
    
    Taktgeber   takt;
    
    public Hanoi_Action() {
        buehne = new Hanoi_Hauptfenster();
        takt = new Taktgeber();
        takt.setzeZeitZwischenAktionen(delta);
        takt.endlos();
    }
    
    private int hilfsturm(int quelle, int ziel) {
        if ((quelle + ziel) == 3) {
            return 0;
        } else if ((quelle + ziel) == 2) {
            return 1;
        } else
            return 2;
    }
    
    public void bewege(int quelle, int ziel) {
        bewege(quelle, ziel, 10);
    }
    
    public void bewege(int quelle, int ziel, int anzahl) {
        if (anzahl == 1) {
            takt.warteBisTaktsignal();
            buehne.bewegeScheibe(quelle, ziel);
        } else {
            bewege(quelle, hilfsturm(quelle,ziel), anzahl - 1);
            bewege(quelle, ziel, 1);
            bewege(hilfsturm(quelle,ziel), ziel, anzahl - 1);
        }
    }
    
    public void run() {
        this.bewege(0, 2);
    }

    public void setzetakt(int ms ) {
        takt.setzeZeitZwischenAktionen(ms);
    }

    public static void main(String[] args) {
        Hanoi_Action t = new Hanoi_Action();
//        t.buehne.alles.setzeZoomfaktor(0.5);
        t.setzetakt(500 );
        t.run();
    }

}
