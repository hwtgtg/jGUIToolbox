//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * Aufzug mit Steuerung
 * 
 * Die Steuerung
 * 
 * 
 * IDs des Schachts : 2. Stock: 2 -- 1. Stock: 1 -- Erdgeschoss: 0
 * 
 * IDs der Kabine: 2. Stock: 12 -- 1. Stock: 11 -- Erdgeschoss: 10
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (23.8.2008)
 */
public class Aufzug_Steuerung_Optimiert implements ITuWas {

    private Aufzug_Schacht schacht;
    private Aufzug_Kabine kabine;
    private Person person;
    private Taktgeber takt;

    public Aufzug_Steuerung_Optimiert() {
        schacht = new Aufzug_Schacht();
        schacht.setzePosition(25, 25);

        kabine = new Aufzug_Kabine();
        kabine.setzePosition(13 + 25, 43 + 25);

        person = new Person();
        person.setzePosition(300 + 60, 450 + 25);

        verknuepfeTaster();

        takt = new Taktgeber();
        takt.setzeLink(this, 0);
        takt.setzeZeitZwischenAktionen(1500);
        takt.endlos();

        Zeichnung.setzeFenstergroesse(450, 700);

    }

    public void kabineNach2() {
        kabine.bewege(43 + 25);
    }

    public void kabineNach1() {
        kabine.bewege(180 + 43 + 25);
    }

    public void kabineNachE() {
        kabine.bewege(360 + 43 + 25);
    }

    /*
     * Die Taster in der Kabine werden mit den Tastern aussen verbunden
     * Ein Tastendruck aussen setzt auch den Taster innen und umgekehrt
     */
    public void verknuepfeTaster() {
        kabine.tE.setzeLink(this, 50);
        kabine.t1.setzeLink(this, 51);
        kabine.t2.setzeLink(this, 52);

        schacht.tE.setzeLink(this, 60);
        schacht.t1.setzeLink(this, 61);
        schacht.t2.setzeLink(this, 62);
    }
    int zustand = 0;

    public void aendereZustand() {
        if (kabine.amZiel()) {
            if (zustand == 0) { // Anfangsbewegng auf E
                zustand = 1;
            } else if (zustand == 1) { // Erdgeschoss
                kabine.tE.setzeNichtGewaehlt();
                schacht.tE.setzeNichtGewaehlt();
                if (kabine.t1.istGedrueckt()) {
                    kabineNach1();
                    zustand = 2;
                } else if (kabine.t2.istGedrueckt()) {
                    kabineNach1();
                    zustand = 2;
                }

            } else if (zustand == 2) { // 1. Stock
                kabine.t1.setzeNichtGewaehlt();
                schacht.t1.setzeNichtGewaehlt();
                if (kabine.t2.istGedrueckt()) {
                    kabineNach2();
                    zustand = 3;
                } else if (kabine.tE.istGedrueckt()) {
                    kabineNachE();
                    zustand = 1;
                }
            } else if (zustand == 3) { // 2. Stock
                kabine.t2.setzeNichtGewaehlt();
                schacht.t2.setzeNichtGewaehlt();
                if (kabine.t1.istGedrueckt()) {
                    kabineNach1();
                    zustand = 2;
                } else if (kabine.tE.istGedrueckt()) {
                    kabineNach1();
                    kabine.t2.setzeNichtGewaehlt();
                    schacht.t2.setzeNichtGewaehlt();
                    zustand = 2;
                }
            }
        }
    }

    public void aendereZustandVerbessert() {
        if (kabine.amZiel()) {
            if (zustand == 0) { // Anfangsbewegng auf E
                zustand = 1;
            } else if (zustand == 1) { // Erdgeschoss
                kabine.tE.setzeNichtGewaehlt();
                schacht.tE.setzeNichtGewaehlt();
                if (kabine.t1.istGedrueckt()) {
                    kabineNach1();
                    zustand = 2;
                } else if (kabine.t2.istGedrueckt()) {
                    kabineNach1();
                    zustand = 4;
                }

            } else if (zustand == 2) { // 1. Stock
                kabine.t1.setzeNichtGewaehlt();
                schacht.t1.setzeNichtGewaehlt();
                if (kabine.t2.istGedrueckt()) {
                    kabineNach2();
                    zustand = 3;
                } else if (kabine.tE.istGedrueckt()) {
                    kabineNachE();
                    zustand = 1;
                }
            } else if (zustand == 3) { // 2. Stock
                kabine.t2.setzeNichtGewaehlt();
                schacht.t2.setzeNichtGewaehlt();
                if (kabine.t1.istGedrueckt()) {
                    kabineNach1();
                    zustand = 2;
                } else if (kabine.tE.istGedrueckt()) {
                    kabineNach1();
                    kabine.t2.setzeNichtGewaehlt();
                    schacht.t2.setzeNichtGewaehlt();
                    zustand = 5;
                }
            } else if (zustand == 4) { // 1. Stock auf dem Weg zum 2. Stock
                kabineNach2();
                kabine.t1.setzeNichtGewaehlt();
                schacht.t1.setzeNichtGewaehlt();
                zustand = 3;
            } else if (zustand == 5) { // 1. Stock auf dem Weg zum Erdgeschoss
                kabineNachE();
                kabine.t1.setzeNichtGewaehlt();
                schacht.t1.setzeNichtGewaehlt();
                zustand = 1;
            }
        }
    }

    public void tuWas(int ID) {

        switch (ID) {
            case 0: // Taktgeber
                aendereZustandVerbessert();
                break;
            case 50:
                schacht.tE.setzeGewaehlt();
                break;
            case 51:
                schacht.t1.setzeGewaehlt();
                break;
            case 52:
                schacht.t2.setzeGewaehlt();
                break;
            case 60:
                kabine.tE.setzeGewaehlt();
                break;
            case 61:
                kabine.t1.setzeGewaehlt();
                break;
            case 62:
                kabine.t2.setzeGewaehlt();
                break;
            default:
        }
    }

    public static void main(String[] args) {
        Aufzug_Steuerung_Optimiert k = new Aufzug_Steuerung_Optimiert();
        k.kabineNachE();
    }


}
