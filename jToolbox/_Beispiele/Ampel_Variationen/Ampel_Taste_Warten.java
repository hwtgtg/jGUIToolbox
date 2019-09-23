//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 * 
 * Die Klasse Ampelschaltung besteht aus der Autoampel die Ampel wird durch
 * Abfrage der Taste (Warten auf einen Tastendruck) weitergeschaltet
 * 
 * @author Hans Witt
 * 
 * Version: 1.1 Zustaende als enum
 * @version: 1.3 Klasse Taste ueberarbeitet
 */

public class Ampel_Taste_Warten {

    Ampel ampel;

    Taste taste;

    public Ampel_Taste_Warten() {
        ampel = new Ampel();
        ampel.setzePosition(100, 100);

        taste = new Taste("Bitte druecken",200, 50);
        taste.setzePosition(50, 450);

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
            taste.warteBisGedrueckt();
        }
    }

    public static void main(String[] args) {
        Ampel_Taste_Warten a = new Ampel_Taste_Warten();
        a.aendereZustand();
    }

}
