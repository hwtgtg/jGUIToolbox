//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispiel fuer die Kommunikation zwischen Klassen der Toolbox
 * 
 * Im gruenen Eingabefeld wird eine Zahlen eingegeben.
 * 
 * Nach Druecken der Enter-Taste wird der Doppelte Wert der eingegebenen Zahl im
 * roten Ausgabefeld ausgegeben.
 * 
 * Die gelbe Taste loesche Eingabe und Ausgabefeld.
 * 
 * @author Witt
 */

public class B_Dezimalzahlen_Graphisch implements ITuWas {
	/**
	 * Attribute
	 */
	
	Ausgabe			label;			// Titel
	Eingabefeld		ein1;			// Zahleneingabe
	Dezimal			dEin1;
	Eingabefeld		ein2;			// Zahleneingabe
	Dezimal			dEin2;
	AusgabePanel	aus;			// Ergebnispanel
	Taste			clr;			// Loeschen der Eingabe und Ausgabe
									
	Dezimal			dErg;
	
	RadioBehaelter	rg_behaelter;
	RadioTaste		rg_n;
	// RadioTaste rg_x;
	RadioTaste		rg_o;
	
	RadioBehaelter	ra_behaelter;
	RadioTaste		ra_a;
	RadioTaste		ra_s;
	RadioTaste		ra_m;
	RadioTaste		ra_d;
	int				faktor	= 0;
	
	Dezimal.Art		art;
	
	/**
	 * Konstruktor
	 */
	public B_Dezimalzahlen_Graphisch() {
		label = new Ausgabe("Beispiel fuer Dezimalzahlen", 10, 00, 400, 50);
		
		rg_behaelter = new RadioBehaelter(10, 50, 400, 70);
		rg_behaelter.setzeSchriftgroesse(14);
		rg_behaelter.setzeFarbe("grau");
		rg_behaelter.setzeBeschreibungstext("Genauigkeit");
		
		rg_n = new RadioTaste(rg_behaelter, "gerundet", 7, 20, 90, 40);
		rg_n.setzeSchriftgroesse(10);
		rg_n.setzeHintergrundfarbe("rot");
		
		rg_o = new RadioTaste(rg_behaelter, "exakt", 204, 20, 90, 40);
		rg_o.setzeSchriftgroesse(10);
		rg_o.setzeHintergrundfarbe("gruen");
		
		rg_n.setzeGewaehlt();
		art = Dezimal.Art.gerundet;
		
		ra_behaelter = new RadioBehaelter(10, 120, 400, 70);
		ra_behaelter.setzeSchriftgroesse(14);
		ra_behaelter.setzeFarbe("grau");
		ra_behaelter.setzeBeschreibungstext("Rechenart");
		
		ra_a = new RadioTaste(ra_behaelter, "addieren", 7, 20, 90, 40);
		ra_a.setzeSchriftgroesse(10);
		ra_a.setzeHintergrundfarbe("rot");
		ra_s = new RadioTaste(ra_behaelter, "subtrahieren", 106, 20, 90, 40);
		ra_s.setzeSchriftgroesse(10);
		ra_s.setzeHintergrundfarbe("gelb");
		ra_m = new RadioTaste(ra_behaelter, "multiplizieren", 204, 20, 90, 40);
		ra_m.setzeSchriftgroesse(10);
		ra_m.setzeHintergrundfarbe("gruen");
		ra_d = new RadioTaste(ra_behaelter, "dividieren", 302, 20, 90, 40);
		ra_d.setzeSchriftgroesse(10);
		ra_d.setzeHintergrundfarbe("lila");
		
		ra_a.setzeGewaehlt();
		
		ein1 = new Eingabefeld("1,2345", 10, 190, 400, 80);
		ein1.setzeHintergrundfarbe("gruen");
		ein1.zentrieren();
		// Die ID des Eingabefeld ist 10
		ein1.setzeID(10);
		// Dem Eingabefeld wird dieses Objekt als Callback-Objekt uebergeben.
		ein1.setzeLink(this);
		
		ein2 = new Eingabefeld("1,25", 10, 280, 400, 80);
		ein2.setzeHintergrundfarbe("orange");
		ein2.zentrieren();
		// Die ID des Eingabefeld ist 10
		ein2.setzeID(10);
		// Dem Eingabefeld wird dieses Objekt als Callback-Objekt uebergeben.
		ein2.setzeLink(this);
		
		aus = new AusgabePanel("Ausgabe", 10, 370, 400, 80);
		aus.setzeHintergrundfarbe("rot");
		
		clr = new Taste("Alles loeschen", 10, 460, 400, 80);
		clr.setzeHintergrundfarbe("gelb");
		// Die ID der Taste ist 20
		clr.setzeID(20);
		// Der Taste wird dieses Objekt als Callback-Objekt uebergeben.
		clr.setzeLink(this);
		
		/**
		 * Eine Radiotaste signalisiert als Standard dem Behaelter Die naechsten
		 * beiden Zeilen leiten die Behaeltermeldungen auf diese Klasse
		 */
		ra_behaelter.setzeID(10);
		ra_behaelter.setzeLink(this); // Der Radio-Behaelter signalisiert
		
		rg_behaelter.setzeID(10);
		rg_behaelter.setzeLink(this); // Der Radio-Behaelter signalisiert
		tuWas(10);
		
	}
	
	/**
	 * Callback-Funktion
	 * 
	 * @param ID
	 */
	public void tuWas(int ID) {
		// Hier wird in Abhaengigkeit von der Signalquelle
		// eine Aktion ausgeloest
		switch (ID) {
		case 10: // Signal vom Eingabefeld mit der ID 10.
			if (rg_n.istGewaehlt()) {
				art = Dezimal.Art.gerundet;
			} else if (rg_o.istGewaehlt()) {
				art = Dezimal.Art.exakt;
			}
			
			if (ra_a.istGewaehlt()) {
				dEin1 = new Dezimal(ein1.toString());
				// ein1.setzeTextRechtsbuendig(dEin1.toString() + " ");
				dEin2 = new Dezimal(ein2.toString());
				// ein2.setzeTextRechtsbuendig(dEin2.toString() + " ");
				dErg = dEin1.addiere(dEin2);
				aus.setzeAusgabetext(dErg.toString());
			} else if (ra_s.istGewaehlt()) {
				dEin1 = new Dezimal(ein1.toString());
				// ein1.setzeTextRechtsbuendig(dEin1.toString() + " ");
				dEin2 = new Dezimal(ein2.toString());
				// ein2.setzeTextRechtsbuendig(dEin2.toString() + " ");
				dErg = dEin1.subtrahiere(dEin2);
				aus.setzeAusgabetext(dErg.toString());
			} else if (ra_m.istGewaehlt()) {
				dEin1 = new Dezimal(ein1.toString());
				// ein1.setzeTextRechtsbuendig(dEin1.toString() + " ");
				dEin2 = new Dezimal(ein2.toString());
				// ein2.setzeTextRechtsbuendig(dEin2.toString() + " ");
				dErg = dEin1.multipliziere(dEin2);
				aus.setzeAusgabetext(dErg.toString());
			} else if (ra_d.istGewaehlt()) {
				dEin1 = new Dezimal(ein1.toString());
				// ein1.setzeTextRechtsbuendig(dEin1.toString() + " ");
				dEin2 = new Dezimal(ein2.toString());
				// ein2.setzeTextRechtsbuendig(dEin2.toString() + " ");
				if (art == Dezimal.Art.gerundet) {
					dErg = dEin1.dividiereGerundet(dEin2);					
				} else {
					dErg = dEin1.dividiereExact(dEin2);
				}
				aus.setzeAusgabetext(dErg.toString());
			}
			
			break;
		
		case 20: // Signal von der Taste clr mit der ID 20
			ein1.setzeAusgabetext("neu");
			ein2.setzeAusgabetext("neu");
			aus.setzeAusgabetext("---");
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new B_Dezimalzahlen_Graphisch();
		
	}
	
}
