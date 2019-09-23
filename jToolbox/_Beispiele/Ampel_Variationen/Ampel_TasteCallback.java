//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 * 
 * Die Klasse Ampelschaltung besteht aus der Autoampel die Ampel wird durch durch eine
 * Taste ueber Callback weitergeschaltet
 *  
 * @author Hans Witt
 * 
 * @version: 1.0
 * @version: 2 (3.8.2008)
 *       angepasst an geaendertes ITuWas
 * 
 */

public class Ampel_TasteCallback implements ITuWas {

    Ampel ampel;

    Taste taste;
    Zustaende zustand = Zustaende.aus;

    public Ampel_TasteCallback() {
        ampel = new Ampel();
        ampel.setzePosition(100, 100);

        taste = new Taste("Bitte druecken", 200, 50);
        taste.setzePosition(50, 450);
        taste.setzeLink(this, 0);
    }

    public void tuWas(int ID) {
        aendereZustand();
    }

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

    enum Zustaende {aus,
        gruen,
        gelb,
        rot,
        gelbgruen;
    }

    public static void main(String[] args) {
        new Ampel_TasteCallback();
    }

}
