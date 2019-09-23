//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * 
 */

/**
 * @author Witt
 * 
 */
public class AnwendungZeichnungBorderLayout {

    /**
     * 
     */
    public AnwendungZeichnungBorderLayout() {
        new ZeichnungBorderLayout();
        ZeichnungBorderLayout.setzeFenstergroesse(500, 500);

        AusgabePanel nord = new AusgabePanel( "Nord ",0, 0, 100, 50);
        nord.setzeAusrichtung(Ausgabe.ZENTRIERT);
        ZeichnungBorderLayout.verschiebeNord(nord);


        Behaelter bsued = new Behaelter( 0, 0, 500, 50);
        ZeichnungBorderLayout.verschiebeSued(bsued);

        AusgabePanel sued = new AusgabePanel(bsued, "Sued: ", 0, 0, 200, 50);
        sued.setzeAusrichtung(Ausgabe.RECHTSBUENDIG);

        Eingabefeld sEin = new Eingabefeld(bsued, "Eingabe:", 200, 0, 250, 50);
        sEin.setzeAusrichtung(Ausgabe.LINKSBUENDIG);

        AusgabePanel ost = new AusgabePanel( "Ost",0, 0, 100, 50);
        ost.setzeAusrichtung(Ausgabe.LINKSBUENDIG);
        ZeichnungBorderLayout.verschiebeOst(ost);

        AusgabePanel west = new AusgabePanel( "West",0, 0, 100, 50);
        west.setzeAusrichtung(Ausgabe.RECHTSBUENDIG);
        ZeichnungBorderLayout.verschiebeWest(west);

        ZeichnungBorderLayout.setzeTitel("Borderlayout-Test");

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new AnwendungZeichnungBorderLayout();
    }
}
