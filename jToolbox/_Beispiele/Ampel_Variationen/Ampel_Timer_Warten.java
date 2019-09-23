//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 * 
 * Die Klasse Ampelschaltung besteht aus der Autoampel die Ampel wird durch
 * Abfrage der Taktgeberkomponente weitergeschaltet
 * 
 * @author Hans Witt
 * 
 * Version: 1.1 Zustaende als enum
 * @version: 1.3 Klasse Taste ueberarbeitet
 * @version: 1.4 jetzt mit einem exten Timer (Klasse Taktgeber)
 * 
 */

public class Ampel_Timer_Warten {

    Ampel ampel;

    Taktgeber takt;

    public Ampel_Timer_Warten() {
        ampel = new Ampel();
        ampel.setzePosition(100, 100);
        takt = new Taktgeber();
        takt.endlos();
    }

    enum Zustaende {
        aus, gruen, gelb, rot, gelbgruen
    };

    public void aendereZustand() {
        Zustaende zustand = Zustaende.aus;
        while (true) {
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
            takt.warteBisTaktsignal(); // Warten auf Timer-Signal
        }
    }

    public static void main(String[] args) {
        Ampel_Timer_Warten a = new Ampel_Timer_Warten();
        a.aendereZustand();
    }

}
