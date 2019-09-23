//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_Ball_DialogModal implements ITuWas {
	
	// Dialog
	private Dlg_JGUIDialog	dialog;
	// Container der Dialog-Komponente
	private IContainer		behaelter;
	
	Schieberegler			balken;
	Eingabefeld				min;
	Eingabefeld				max;
	Eingabefeld				wert;
	
	private Taste			test;
	
	/**
	 * Konstruktor </br>
	 * 
	 * Ein modaler Dialog blockiert andere Fenster des Programms ! </br>
	 * 
	 * Die Elemente des Dialogs werden angelegt. Die Taste erhaelt als Link
	 * die Dialog-Komponente. Die Taste schliesst/Verbirgt den Dialog und gibt
	 * die Kontrolle an das Programm zurueck
	 */
	
	public B_Ball_DialogModal() {
		// Dialog anlegen
		// Ein modaler Dialog blockiert andere Fenster des Programms ! </br>
		// Dialoge werden verborgen, nicht geschlossen!
		
		dialog = new Dlg_JGUIDialog("Einstellungen", Dlg_JGUIDialog.MODAL);
		// Behaelter fuer Dialogkomponenten lesen
		behaelter = dialog.leseContainer();
		
		// Elemente in den Dialog-Behaelter einfuegen
		balken = new Schieberegler(behaelter, 'H', 100, 100, 400, 40, 0, 100,
				50);
		balken.setzeLink(this, 2);
		
		min = new Eingabefeld(behaelter, "min", 100, 200, 100, 50);
		min.setzeLink(this, 3);
		max = new Eingabefeld(behaelter, "max", 400, 200, 100, 50);
		max.setzeLink(this, 4);
		wert = new Eingabefeld(behaelter, "wert", 250, 200, 100, 50);
		wert.setzeLink(this, 5);
		
		test = new Taste(behaelter, "Beenden", 250, 300, 100, 50);
		// Die Taste erhaelt als Link die Dialog-Komponente schliesst den Dialog
		test.setzeLink(dialog, 1);
		
		// Groesse des Dialogfensters einstellen
		dialog.setSize(600, 400);
	}

	/**
	 * Der Dialog wird sichtbar.
	 * Der modale Dialog blokiert die Eingabe des Hauptprogramms
	 */
	public void setzeSichtbar() {
		// Dialog anzeigen
		dialog.setVisible(true);
	}
	

	
	/**
	 * Anfangswerte der Dialog-Komponenten werden vom Programm gesetzt 
	 * 
	 * @param neuesMin
	 * @param neuesMax
	 * @param neuerWert
	 */
	public void setzeWerte(double neuesMin, double neuesMax, double neuerWert) {
		balken.setzeBereich(neuesMin, neuesMax, neuerWert);
		min.setzeDouble(neuesMin);
		max.setzeDouble(neuesMax);
		wert.setzeDouble(neuerWert);
	}
	
	public void tuWas(int ID) {
		switch (ID) {
		case 2: // Schieberegler
			wert.setzeDouble(balken.leseDoubleWert());
			break;
		case 3: // min
			balken.setzeBereich(min.leseDouble(0), max.leseDouble(0), wert
					.leseDouble(0));
			break;
		case 4: // max
			balken.setzeBereich(min.leseDouble(0), max.leseDouble(0), wert
					.leseDouble(0));
			break;
		case 5: // wert
			balken.setzeBereich(min.leseDouble(0), max.leseDouble(0), wert
					.leseDouble(0));
			break;
		default:
		}
	}
}
