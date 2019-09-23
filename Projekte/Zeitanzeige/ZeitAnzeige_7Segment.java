//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 *
 * Zeitanzeige mit Siebensegmentanzeigen
 *
 * Beispiel fuer Graphikobjekte und StaticTools
 *
 * @author Hans Witt
 *
 * @version
 * Version 1.0
 *
 * Version 3: (12.8.2008) angepasst an Behaelter fuer GUI-Elemente
 */
public class ZeitAnzeige_7Segment {
    private SiebenSegment sH01;
    private SiebenSegment sH10;
    private Kreis hO;
    private Kreis hU;
    private SiebenSegment sM01;
    private SiebenSegment sM10;
    private Kreis mO;
    private Kreis mU;
    private SiebenSegment sS01;
    private SiebenSegment sS10;
    private int hoehe = 100;
    private long zeit = 0;
    private boolean punktStatus = false;
    private boolean laufend = false;

    public ZeitAnzeige_7Segment() {
        sS10 = new SiebenSegment(0, 0, hoehe);
        sS01 = new SiebenSegment(0, 0, hoehe);

        mO = new Kreis(0, 0, hoehe / 8);
        mU = new Kreis(0, 0, hoehe / 8);

        sM10 = new SiebenSegment(0, 0, hoehe);
        sM01 = new SiebenSegment(0, 0, hoehe);

        hO = new Kreis(0, 0, hoehe / 8);
        hU = new Kreis(0, 0, hoehe / 8);

        sH10 = new SiebenSegment(0, 0, hoehe);
        sH01 = new SiebenSegment(0, 0, hoehe);
        positionAendern(0, 0);
        farbeAendern("rot");
    }

    public void positionAendern(int x, int y) {
        sH10.setzePosition(x, y);
        sH01.setzePosition(x + (hoehe / 2), y);

        hO.setzePosition(x + ((hoehe * 9) / 8), y + ((hoehe * 3) / 20));
        hU.setzePosition(x + ((hoehe * 9) / 8), y + ((hoehe * 10) / 20));

        sM10.setzePosition(x + (hoehe / 2 * 3), y);
        sM01.setzePosition(x + (2 * hoehe), y);

        mO.setzePosition(x + ((hoehe * 21) / 8), y + ((hoehe * 3) / 20));
        mU.setzePosition(x + ((hoehe * 21) / 8), y + ((hoehe * 10) / 20));

        sS10.setzePosition(x + (3 * hoehe), y);
        sS01.setzePosition(x + (hoehe / 2 * 7), y);
    }

    /*
     * gueltige Farben:
     * "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
     * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
       */
    public void farbeAendern(String neueFarbe) {
        sH10.setzeFarbe(neueFarbe);
        sH01.setzeFarbe(neueFarbe);

        hO.setzeFarbe(neueFarbe);
        hU.setzeFarbe(neueFarbe);

        sM10.setzeFarbe(neueFarbe);
        sM01.setzeFarbe(neueFarbe);

        mO.setzeFarbe(neueFarbe);
        mU.setzeFarbe(neueFarbe);

        sS10.setzeFarbe(neueFarbe);
        sS01.setzeFarbe(neueFarbe);
    }

    public void punkteAn() {
        hO.fuellen();
        hU.fuellen();
        mO.fuellen();
        mU.fuellen();
    }

    public void punkteAus() {
        hO.rand();
        hU.rand();
        mO.rand();
        mU.rand();
    }

    public void anzeige(long neueZeit) {
        long anzeigeZeit = neueZeit;
        int s = (int) (anzeigeZeit % 60);
        sS01.anzeige(s % 10);
        sS10.anzeige(s / 10);
        anzeigeZeit = anzeigeZeit / 60;

        int m = (int) (anzeigeZeit % 60);
        sM01.anzeige(m % 10);
        sM10.anzeige(m / 10);
        anzeigeZeit = anzeigeZeit / 60;

        int h = (int) (anzeigeZeit % 60);
        sH01.anzeige(h % 10);
        sH10.anzeige(h / 10);
    }

    public void takt() {
        punktStatus = !punktStatus;

        if (punktStatus) {
            punkteAn();
        } else {
            punkteAus();
        }
    }

    public void laufen() {
        laufend = true;

        while (laufend) {
            anzeige(zeit);
            takt();
            zeit++;

            if (zeit > (60 * 24 * 3600)) {
                zeit = 0;
            }

            StaticTools.warte(1000);
        }
    }

    public void zeitSetzen(int h, int m, int s) {
        zeit = (h * 3600) + (m * 60) + s;
        anzeige(zeit);
    }

    public void zeitSetzen(long neueZeit) {
        zeit = neueZeit;
        anzeige(zeit);
    }

    public void systemZeitSetzen() {
        zeitSetzen(StaticTools.jetzt());
        laufen();
    }

    public static void main(String[] args) {
        ZeitAnzeige_7Segment a = new ZeitAnzeige_7Segment();
        a.systemZeitSetzen();
    }
}
