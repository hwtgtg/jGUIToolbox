//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * 
 */

/**
 * @author Witt
 * 
 */
public class B_BehaelterBorderLayout {

	/**
	 * 
	 */
	public B_BehaelterBorderLayout() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Zeichnung.setzeFenstergroesse(1000, 750);

	
		BehaelterBorderlayout bb = new BehaelterBorderlayout(200, 200, 600, 400);
		bb.setzeBeschreibungstext("bbText");

		AusgabePanel nord = new AusgabePanel( "Nord ",0, 0, 100, 50);
		nord.setzeAusrichtung(Ausgabe.LINKSBUENDIG);
		bb.verschiebeNord(nord);

		AusgabePanel zentral = new AusgabePanel(bb, "Zentral ", 50, 50, 100,
				100);
		zentral.setzeAusrichtung(Ausgabe.ZENTRIERT);

		Behaelter bsued = new Behaelter( 0, 0, 500, 50);
		bb.verschiebeSued(bsued);

		AusgabePanel sText = new AusgabePanel(bsued, "Sued: ", 0, 0, 200, 50);
		sText.setzeAusrichtung(Ausgabe.RECHTSBUENDIG);

		Eingabefeld sEin = new Eingabefeld(bsued, "Eingabe:", 200, 0, 250, 50);
		sEin.setzeAusrichtung(Ausgabe.LINKSBUENDIG);

	}
}
