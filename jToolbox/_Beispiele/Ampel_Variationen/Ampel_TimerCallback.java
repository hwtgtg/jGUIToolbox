//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 * 
 * Die Klasse Ampelschaltung besteht aus der Autoampel die Ampel wird durch eine
 * Takgeberkomponente ueber Callback weitergeschaltet
 *  *
 * @author Hans Witt
 * 
 * Version: 1.1 Zustaende als enum
 * @version: 1.3 Klasse Taste ueberarbeitet
 * @version: 2 (3.8.2008)
 *       angepasst an geaendertes ITuWas
 * 
 */

public class Ampel_TimerCallback implements ITuWas {

    Ampel ampel;

    Taktgeber takt;

    public Ampel_TimerCallback() {
        ampel = new Ampel();
        ampel.setzePosition(100, 100);

        takt = new Taktgeber();
        takt.setzeLink(this, 0);
        takt.mehrfach(12);
    }

    public void tuWas(int ID) { // Timer startet ändern
        aendereZustand();
    }

    enum Zustaende {
        aus, gruen, gelb, rot, gelbgruen
    };

    Zustaende zustand = Zustaende.aus;

    public void aendereZustand() {
        switch (zustand) {
            case aus:
                ampel.ampelAus();
                zustand = Zustaende.gruen;
                break;
            case gruen:
                ampel.gruen();
                zustand = Zustaende.gelb;
                break;
            case gelb:
                ampel.gelb();
                zustand = Zustaende.rot;
                break;
            case rot:
                ampel.rot();
                zustand = Zustaende.gelbgruen;
                break;
            case gelbgruen: // rot -> rot-gelb
                ampel.rotGelb();
                zustand = Zustaende.gruen;
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new Ampel_TimerCallback();
    }
}
