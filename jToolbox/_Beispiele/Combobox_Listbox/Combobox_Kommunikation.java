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

public class Combobox_Kommunikation implements ITuWas {
	/**
	 * Attribute
	 */
	
	Ausgabe			label;	// Titel
	Combobox		ein;	// Zahleneingabe
	AusgabePanel	aus;	// Ergebnispanel
	Taste			clr;	// Loeschen der Eingabe und Ausgabe
							
	/**
	 * Konstruktor
	 */
	public Combobox_Kommunikation() {
		label = new Ausgabe("Beispiel fuer Kommunikation", 10, 20, 400, 50);
		String [] texte = {
				"zeile 1", "zeile 2", "zeile3","zeile 4"
		} ;
		ein = new Combobox( texte , 10, 80, 250, 40);
		ein.setzeSchriftgroesse(12);
		ein.setzeHintergrundfarbe("gruen");
		ein.textHinzufuegen("hinzugefueegt");  // Letze Zeile in der Combobox
		ein.setzeEditierbar();
		ein.setzeAuswahlIndex(2);
		
		// Die ID des Eingabefeld ist 100
		ein.setzeID(100);
		// Dem Eingabefeld wird dieses Objekt als Callback-Objekt uebergeben.
		ein.setzeLink(this);
		
		aus = new AusgabePanel("Ausgabe", 10, 170, 300, 80);
		aus.setzeHintergrundfarbe("gelb");
		
		clr = new Taste("Alles loeschen", 10, 260, 250, 80);
		clr.setzeHintergrundfarbe("gelb");
		// Die ID der Taste ist 200
		clr.setzeID(200);
		// Der Taste wird dieses Objekt als Callback-Objekt uebergeben. 
		clr.setzeLink(this);
		
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
		case 100: // Signal vom Eingabefeld mit der ID 100 ein. Es wird bei ENTER ausgeloest
			int auswahl = ein.leseAuswahlIndex() ;
			aus.setzeAusgabetext("Combo-Eintrag: " + auswahl +" "+ein.leseAuswahl());
			if ( auswahl == -1 ){
				ein.textHinzufuegen(ein.leseAuswahl());
			}
			break;
		
		case 200: // Signal von der Taste clr mit der ID 200 
			ein.setzeAuswahlIndex(0);
			ein.setzeEditierbar();
			aus.setzeAusgabetext("");
			break;
		
		default:
			break;
		}
		
	}
	
}
