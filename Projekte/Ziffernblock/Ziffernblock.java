//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 * 
 * Ziffernblock
 * 
 * Beispiel fuer Umschalttaste
 * 
 * @author Hans Witt
 * 
 * @version: 1.1 Ueberarbeitet (19.7.2008)
 * @version: 2 (3.8.2008) angepasst an geaendertes ITuWas Umschalttaste durch
 *           taste ersetzt
 * @version: 3 (22.7.2019) Behaelter ergaenzt 
 */

public class Ziffernblock implements ITuWas {

    Behaelter beh_zb ;
    private Taste z0;
    private Taste z1;
    private Taste z2;
    private Taste z3;
    private Taste z4;
    private Taste z5;
    private Taste z6;
    private Taste z7;
    private Taste z8;
    private Taste z9;
    private Taste dp;
    private Taste clr;

    private int breite = 80;

    private long zahl = 0;
    private static int keinDP = -1;
    private int dpStelle = -1; // -1 = ganze Zahl n = n-te Stelle von rechts

    ITuWas linkObj;
    int id = 0; // ID der Komponente fuer Callback

    public void setLink(ITuWas linkObj, int ID) {
        this.linkObj = linkObj;
        id = ID;
    }

    public void clear() {
        zahl = 0;
        dpStelle = 0;
    }

    public long wert() {
        return zahl;
    }

    public int dpS() {
        return dpStelle;
    }

    public void setze(long wert) {
        zahl = wert;
    }

    public void setzeDP(int pos) {
        dpStelle = pos;
    }

    private boolean active = false;

    public void aktiviere() {
        active = true;
    }

    public void deaktiviere() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public Ziffernblock() {
        z0 = new Taste("0", breite - 2, breite - 2);
        beh_zb = new Behaelter(3*breite,4*breite);
        beh_zb.hinzufuegen(z0);
        z0.setzeLink(this, 0);

        z1 = new Taste("1", breite - 2, breite - 2);
        beh_zb.hinzufuegen(z1);
        z1.setzeLink(this, 1);

        z2 = new Taste("2", breite - 2, breite - 2);
        beh_zb.hinzufuegen(z2);
        z2.setzeLink(this, 2);

        z3 = new Taste("3", breite - 2, breite - 2);
        beh_zb.hinzufuegen(z3);
        z3.setzeLink(this, 3);

        z4 = new Taste("4", breite - 2, breite - 2);
        beh_zb.hinzufuegen(z4);
        z4.setzeLink(this, 4);

        z5 = new Taste("5", breite - 2, breite - 2);
        beh_zb.hinzufuegen(z5);
        z5.setzeLink(this, 5);

        z6 = new Taste("6", breite - 2, breite - 2);
        beh_zb.hinzufuegen(z6);
        z6.setzeLink(this, 6);

        z7 = new Taste("7", breite - 2, breite - 2);
        beh_zb.hinzufuegen(z7);
        z7.setzeLink(this, 7);

        z8 = new Taste("8", breite - 2, breite - 2);
        beh_zb.hinzufuegen(z8);
        z8.setzeLink(this, 8);

        z9 = new Taste("9", breite - 2, breite - 2);
        beh_zb.hinzufuegen(z9);
        z9.setzeLink(this, 9);

        dp = new Taste(",", breite - 2, breite - 2);
        beh_zb.hinzufuegen(dp);
        dp.setzeLink(this, 10);

        clr = new Taste("C", breite - 2, breite - 2);
        beh_zb.hinzufuegen(clr);
        clr.setzeLink(this, 11);

        setzePositionInBeh();

    }

    public void setzePositionInBeh() {
        z0.setzePosition( 1 + breite,  3 * breite);
        z1.setzePosition(1, 2 * breite);
        z2.setzePosition( 1 + breite,  2 * breite);
        z3.setzePosition(1 + 2 * breite, 2 * breite);
        z4.setzePosition( 1, breite);
        z5.setzePosition( 1 + breite, breite);
        z6.setzePosition(1 + 2 * breite, breite);
        z7.setzePosition( 1, 1);
        z8.setzePosition( 1 + breite,  1);
        z9.setzePosition( 1 + 2 * breite, 1);
        dp.setzePosition( 1 + 2 * breite,  3 * breite);
        clr.setzePosition( 1,  3 * breite);
    }

    public void setzePosition(int x, int y) {
        beh_zb.setzePosition(x, y);
    }

    public void setzeGroesse(int breite) {
        this.breite = breite;
        beh_zb.setzeGroesse(3*breite,4*breite);
        z7.setzeGroesse(breite - 2, breite - 2);
        z8.setzeGroesse(breite - 2, breite - 2);
        z9.setzeGroesse(breite - 2, breite - 2);
        z4.setzeGroesse(breite - 2, breite - 2);
        z5.setzeGroesse(breite - 2, breite - 2);
        z6.setzeGroesse(breite - 2, breite - 2);
        z1.setzeGroesse(breite - 2, breite - 2);
        z2.setzeGroesse(breite - 2, breite - 2);
        z3.setzeGroesse(breite - 2, breite - 2);
        clr.setzeGroesse(breite - 2, breite - 2);
        z0.setzeGroesse(breite - 2, breite - 2);
        dp.setzeGroesse(breite - 2, breite - 2);
        setzePositionInBeh();
    }

    public void setzeHintergrundfarbe(String neueFarbe) {
        z0.setzeHintergrundfarbe(neueFarbe);
        z1.setzeHintergrundfarbe(neueFarbe);
        z2.setzeHintergrundfarbe(neueFarbe);
        z3.setzeHintergrundfarbe(neueFarbe);
        z4.setzeHintergrundfarbe(neueFarbe);
        z5.setzeHintergrundfarbe(neueFarbe);
        z6.setzeHintergrundfarbe(neueFarbe);
        z7.setzeHintergrundfarbe(neueFarbe);
        z8.setzeHintergrundfarbe(neueFarbe);
        z9.setzeHintergrundfarbe(neueFarbe);
        dp.setzeHintergrundfarbe(neueFarbe);
        clr.setzeHintergrundfarbe(neueFarbe);
    }

    public void tuWas(int ID) {
        // Verarbeiten
        if (active) {
            switch (ID) {
                case 0:
                zahl = zahl * 10;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 1:
                zahl = zahl * 10 + 1;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 2:
                zahl = zahl * 10 + 2;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 3:
                zahl = zahl * 10 + 3;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 4:
                zahl = zahl * 10 + 4;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 5:
                zahl = zahl * 10 + 5;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 6:
                zahl = zahl * 10 + 6;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 7:
                zahl = zahl * 10 + 7;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 8:
                zahl = zahl * 10 + 8;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 9:
                zahl = zahl * 10 + 9;
                if (dpStelle != keinDP)
                    dpStelle++;
                break;
                case 10:
                if (dpStelle == keinDP)
                    dpStelle = keinDP + 1;
                break;
                case 11:
                zahl = 0;
                dpStelle = keinDP;
                break;
                default:
            }
        }

        if (linkObj != null) {
            linkObj.tuWas(id);
        }
    }

}
