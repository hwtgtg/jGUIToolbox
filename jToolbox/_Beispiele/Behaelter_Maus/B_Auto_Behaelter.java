//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * Landschaft mit Auto
 * 
 * Auto und Baum werden in separate Behaelter verpackt
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (6.8.2008)
 */

public class B_Auto_Behaelter implements ITuWas {

    private MausBehaelter       baum;
    private Rechteck        stamm;
    private Kreis           krone;

    private MausBehaelter   auto;
    private Rechteck        reUnten;
    private Rechteck        reOben;
    private Rechteck        tuer;
    private Rechteck        fenster;

    private Kreis           radLi;
    private Kreis           radRe;

    @SuppressWarnings("unused")
    private Kreis           lichtVorn;
    @SuppressWarnings("unused")
    private Kreis           lichtHinten;

    private Taktgeber       takt;

    public B_Auto_Behaelter() {

        baum = new MausBehaelter(50, 50, 200, 350);
        // Die stamm- und krone-Koordinaten werden relativ zum Behaelter baum
        // gesetzt
        stamm = new Rechteck(baum, 55, 150, 90, 200);
        stamm.setzeFarbe("dunkelgrau");

        krone = new Kreis(baum, 0, 0, 100);
        krone.setzeFarbe("gruen");

        auto = new MausBehaelter(300, 150, 300, 250);
        auto.setzeMausereignisse((1 << MausBehaelter.PRESS)
            | (1 << MausBehaelter.RELEASE) | (1 << MausBehaelter.DRAGGED));
        auto.setzeLink(this, 0);
        auto.unsichtbarMachen();

        reUnten = new Rechteck(auto, 0, 120, 300, 100);
        reUnten.setzeFarbe("orange");

        reOben = new Rechteck(auto, 750, 20, 150, 100);
        reOben.setzeFarbe("orange");

        tuer = new Rechteck(auto, 90, 30, 120, 180);
        tuer.setzeFarbe("blau");

        fenster = new Rechteck(auto, 100, 40, 100, 80);
        fenster.setzeFarbe("gelb");

        radLi = new Kreis(auto, 20, 190, 30);
        radLi.setzeFarbe("schwarz");

        radRe = new Kreis(auto, 220, 190, 30);
        radRe.setzeFarbe("schwarz");

        auto.sichtbarMachen();
        auto.setzeFarbe("schwarz");
        auto.setzeSchriftgroesse(20);
        auto.setzeBeschreibungstext("Auto mit der Maus bewegen!");

        takt = new Taktgeber(this, 10);
        takt.setzeAnfangsZeitverzoegerung(50);
        takt.setzeZeitZwischenAktionen(50);
    }

    // Startposition der Komponente beim Druecken der Taste
    int     mPposx      = 0;
    int     mPposy      = 0;
    // fuer Taktgeber
    int     anfangsX    = 300;
    int     anfangsY    = 150;
    int     dx          = 10;
    int     dy          = 10;

    boolean zurueck     = false;

    public void tuWas(int ID) {
        if (ID == (MausBehaelter.PRESS)) {
            mPposx = auto.getMX();
            mPposy = auto.getMY();
            zurueck = false;
        } else if (ID == (MausBehaelter.RELEASE)) {
            zurueck = true;
            dx = (anfangsX - auto.getXPos()) / 20;
            dy = (anfangsY - auto.getYPos()) / 20;
            takt.mehrfach(20);
        } else if (ID == (MausBehaelter.DRAGGED)) {
            auto.setzePosition(auto.getXPos() + auto.getMX() - mPposx, auto
                .getYPos()
                + auto.getMY() - mPposy);
        } else if (ID == 10) { // Taktgeber
            if (!zurueck) {
                takt.stop();
            } else {
                auto.setzePosition(auto.getXPos() + dx, auto.getYPos() + dy);
            }
        }
    }

}
