//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 *
 * Aufzug mit Steuerung
 *
 * Die Kabine
 *
 * IDs: 2. Stock: 12 -- 1. Stock: 11 -- Erdgeschoss: 10
 *
 * @author Hans Witt
 *
 * @version: 1.0 (23.8.2008)
 */
public class Aufzug_Kabine implements ITuWas {
    private Behaelter kabine;
    private Rechteck kabineAussen;
    private Rechteck kabineInnen;
    FeststellTaste t2;
    FeststellTaste t1;
    FeststellTaste tE;
    private Taktgeber takt;
    int yPos = 0;
    int xPos = 0;
    final int dySTD = 5;
    int dy = 5;
    private ITuWas linkObj;
    @SuppressWarnings("unused")
	private int id = 0; // ID der Komponente fuer Callback

    public Aufzug_Kabine() {
        kabine = new Behaelter(10, 0, 175, 175);
        kabine.setzeMitRaster(true);

        kabineAussen = new Rechteck(kabine, 0, 0, 175, 175);
        kabineAussen.setzeFarbe("orange");

        kabineInnen = new Rechteck(kabine, 51, 5, 116, 154);
        kabineInnen.setzeFarbe("gelb");

        t2 = new FeststellTaste(kabine, "2", "2", 2, 12, 47, 36);
        t2.setzeFarbeNichtGedrueckt("gruen");
        t2.setzeFarbeGedrueckt("gelb");
        t2.setzeLink(this, 12);

        t1 = new FeststellTaste(kabine, "1", "1", 2, 62, 47, 36);
        t1.setzeFarbeNichtGedrueckt("gruen");
        t1.setzeFarbeGedrueckt("gelb");
        t1.setzeLink(this, 11);

        tE = new FeststellTaste(kabine, "E", "E", 2, 112, 47, 36);
        tE.setzeFarbeNichtGedrueckt("gruen");
        tE.setzeFarbeGedrueckt("gelb");
        tE.setzeLink(this, 10);

        takt = new Taktgeber(this, 0);
        takt.setzeAnfangsZeitverzoegerung(50);
        takt.setzeZeitZwischenAktionen(50);
        takt.setzeLink(this, 99);
    }

    public void setzePosition(int neuesX, int neuesY) {
        xPos = neuesX;
        yPos = neuesY;
        kabine.setzePosition(neuesX, neuesY);
    }

    public void bewege(int yPosNeu) {
        if (yPosNeu == yPos) {
            return;
        }

        int dt = (yPosNeu - yPos) / dySTD;

        if (dt >= 0) {
            dy = dySTD;
        } else {
            dt = -dt;
            dy = -dySTD;
        }

        takt.mehrfach(dt);
    }

    public boolean amZiel() {
        return !takt.laufend();
    }
    public void setzeLink(ITuWas linkObj, int ID) {
        this.linkObj = linkObj;
        id = ID;
    }

    public void tuWas(int ID) {
        if (ID == 99) {
            setzePosition(xPos, yPos + dy);
        } else if (linkObj != null) {
            tuWas(ID);
        }
    }

    public static void main(String[] args) {
        new Aufzug_Kabine();
        Zeichnung.setzeRasterEin();
    }
}
