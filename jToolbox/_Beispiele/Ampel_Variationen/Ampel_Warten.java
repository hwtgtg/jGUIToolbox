//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Die Klasse Ampelschaltung besteht aus der Autoampel 
 * die Ampel wird nach Ablauf einer bestimmten Zeit weitergeschaltet
 *  
 * @version: 1.1
 *    Zustaende als enum
 */

public class Ampel_Warten {

    Ampel ampel;

    public Ampel_Warten() {
        ampel = new Ampel();
        ampel.setzePosition(100, 100);
    }

    enum Zustaende { aus , gruen , gelb , rot , gelbgruen  };
    public void aendereZustand() {
        Zustaende zustand = Zustaende.aus ; 
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
            StaticTools.warte(1000); // warte 1s
        }
    }

    public static void main(String[] args) {
        Ampel_Warten a = new Ampel_Warten();
        a.aendereZustand();
    }

}
