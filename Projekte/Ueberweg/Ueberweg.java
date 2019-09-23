//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 * 
 * Ueberweg
 * Beispiel fuer Graphikobjekte und Schalter
 * 
 * @author Hans Witt
 * 
 * Version: 1.0
 * @version: 2.0
 * Taste mit Warten eingebaut
 */

public class Ueberweg  {
    private AutoAmpel aa;
    private FussgaengerAmpel fa;
    private FeststellTaste knopf;
    private Taste start;

    private AusgabePanel marke;
    private AusgabePanel marke2;

    boolean bstart = false;

    /**
     * Konstruktor fuer Objekte der Klasse Ueberweg
     */
    public Ueberweg() {
        marke = new AusgabePanel("Fussgaengerueberweg", 150,20, 400, 100);
        marke.setzeSchriftgroesse(30);
        marke.setzeSchriftfarbe("blau");
        marke.setzeHintergrundfarbe("gruen");

        marke2 = new AusgabePanel("Start mit Taste", 250,150,200, 50);
        marke2.setzeSchriftgroesse(20);

        start = new Taste("Start", 100,500, 200, 50 );
        start.setzeHintergrundfarbe("orange");
        start.setzeSchriftfarbe("gruen");

        knopf = new FeststellTaste("Bitte druecken","Signal kommt", 350, 440,200, 50);
        knopf.setzeFarbeNichtGedrueckt("rot");
        knopf.setzeFarbeGedrueckt("gelb");
        knopf.setzeSchriftgroesse(20);
        knopf.setzeSchriftfarbe("magenta");

        aa = new AutoAmpel();
        aa.vertikalVerschieben(100);
        fa = new FussgaengerAmpel();
        fa.horizontalVerschieben(100);
        fa.vertikalVerschieben(200);
    }

    enum Zustaende {
        AausFaus, AausFrot, AgruenFrot, AgelbFrot, ArotFrotA, ArotFgruen, ArotFrotE, ArotgelbFrot
    };

    Zustaende status = Zustaende.AausFaus;

    public void aendereZustand() {
        switch (status) {
            case AausFaus:
            aa.ampelAus();
            fa.ampelAus();
            StaticTools.warte(500);
            status = Zustaende.AausFrot;
            break;
            case AausFrot:
            aa.ampelAus();
            fa.rot();
            StaticTools.warte(800);
            if (knopf.istGedrueckt()) {
                status = Zustaende.AgruenFrot;
            }
            break;
            case AgruenFrot:
            aa.gruen();
            fa.rot();
            StaticTools.warte(800);
            status = Zustaende.AgelbFrot;
            break;
            case AgelbFrot:
            aa.gelb();
            fa.rot();
            StaticTools.warte(800);
            status = Zustaende.ArotFrotA;
            break;
            case ArotFrotA:
            aa.rot();
            fa.rot();
            StaticTools.warte(800);
            status = Zustaende.ArotFgruen;
            break;
            case ArotFgruen:
            aa.rot();
            fa.gruen();
            StaticTools.warte(800);
            status = Zustaende.ArotFrotE;
            break;
            case ArotFrotE:
            aa.rot();
            fa.rot();
            knopf.setzeNichtGewaehlt();
            StaticTools.warte(800);
            status = Zustaende.ArotgelbFrot;
            break;
            case ArotgelbFrot:
            aa.rotGelb();
            fa.rot();
            StaticTools.warte(800);
            if (knopf.istGedrueckt()) {
                status = Zustaende.AgruenFrot;
            } else {
                status = Zustaende.AausFrot;
            }
            break;
            default:

        }
    }

    public void starten() {
        start.warteBisGedrueckt();
        while (true) {
            StaticTools.warte(200);

            aendereZustand();
        }
    }

    public static void main(String[] args) {
        Ueberweg t = new Ueberweg();
        t.starten();
    }

}
