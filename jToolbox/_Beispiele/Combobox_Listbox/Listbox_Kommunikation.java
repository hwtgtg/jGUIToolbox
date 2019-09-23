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

public class Listbox_Kommunikation implements ITuWas {
	/**
	 * Attribute
	 */
	
	Ausgabe			label;	// Titel
	Listbox		ein;	// Zahleneingabe
	AusgabePanel	aus;	// Ergebnispanel
	Taste			clr;	// Loeschen der Eingabe und Ausgabe
							
	/**
	 * Konstruktor
	 */
	public Listbox_Kommunikation() {
		label = new Ausgabe("Beispiel fuer Kommunikation", 10, 20, 300, 50);
		String [] texte = {
				"zeile 1", "zeile 2", "zeile 3","zeile 4","zeile 5","zeile 6","zeile 7","zeile 8"
		} ;
		ein = new Listbox( texte , 10, 80, 300, 200);
		ein.setzeHintergrundfarbe("gruen");
		ein.textHinzufuegen("Hallo");  // Letze Zeile in der Combobox
				
		ein.setzeAuswahlIndex(2);
		
		// Die ID des Eingabefeld ist 10
		ein.setzeID(10);
		// Dem Eingabefeld wird dieses Objekt als Callback-Objekt uebergeben.
		ein.setzeLink(this);
		
		aus = new AusgabePanel("Ausgabe", 10, 300, 300, 80);
		aus.setzeHintergrundfarbe("gelb");
		
		clr = new Taste("Alles loeschen", 10, 400, 300, 80);
		clr.setzeHintergrundfarbe("gelb");
		// Die ID der Taste ist 20
		clr.setzeID(20);
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
		case 10: // Signal vom Eingabefeld mit der ID 10 ein. Es wird bei ENTER ausgeloest
			int auswahl = ein.leseAuswahlIndex() ;
			aus.setzeAusgabetext("Liste-Eintrag: " + auswahl +" "+ein.leseAuswahl());
			if ( auswahl == -1 ){
				ein.textHinzufuegen(ein.leseAuswahl());
			}
			break;
		
		case 20: // Signal von der Taste clr mit der ID 20 
			ein.auswahlAufheben();
			aus.setzeAusgabetext("");
			break;
		
		default:
			break;
		}
		
	}

}

