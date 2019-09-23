//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_Ball_DialogNichtModal implements ITuWas {
	
	// Dialog
	private Dlg_JGUIDialog	dialog;
	// Container der Dialog-Komponente
	private IContainer		behaelter;
	
	Schieberegler			balken;
	Eingabefeld				wert;
	
	private Taste			test;
	
	/**
	 * Konstruktor </br>
	 * 
	 * Ein nicht modaler Dialog blockiert andere Fenster des Programms nicht! </br>
	 * 
	 * Die Elemente des Dialogs werden angelegt. Die Taste erhaelt als Link die
	 * Dialog-Komponente. Die Taste schliesst/Verbirgt den Dialog 
	 * 
	 */
	
	public B_Ball_DialogNichtModal() {
		// Dialog anlegen
		// Ein nichtModaler Dialog blockiert andere Fenster des Programms NICHT
		// ! </br>
		// Das Programm laeuft einfach weiter
		
		dialog = new Dlg_JGUIDialog("Richtung", Dlg_JGUIDialog.NICHTMODAL);
		// Behaelter fuer Dialogkomponenten lesen
		behaelter = dialog.leseContainer();
		// DIe Schliessen-Ereignisse des Dialogs senden eine Meldung
		dialog.setzeLink(this,0);
		
		// Elemente in den Dialog-Behaelter einfuegen
		balken = new Schieberegler(behaelter, 'H', 100, 100, 400, 40, 0 , 360,
				10);
		balken.setzeLink(this, 2);
		
		wert = new Eingabefeld(behaelter, "wert", 250, 200, 100, 50);
		wert.setzeLink(this, 5);
		
		test = new Taste(behaelter, "Ende", 250, 300, 100, 50);
		// Die Taste erhaelt als Link die Dialog-Komponente schliesst den Dialog
		test.setzeLink(dialog, 1);
		
		// Groesse des Dialogfensters einstellen
		dialog.setSize(600, 400);
	}
	
	/**
	 * Anfangswerte der Dialog-Komponenten werden vom Programm gesetzt
	 * 
	 * @param neuesMin
	 * @param neuesMax
	 * @param neuerWert
	 */
	public void setzeRichtung(int neuerWert) {
		balken.setzeBereich(0, 360 , neuerWert );
		wert.setzeDouble(neuerWert);
	}
	
	B_Ball_MitNichtModalemDialog uebergeordnet;
	
	/**
	 * Der Dialog wird sichtbar. Der modale Dialog blokiert die Eingabe des
	 * Hauptprogramms
	 */
	public void setzeSichtbar(B_Ball_MitNichtModalemDialog link) {
		// Dialog anzeigen
		uebergeordnet = link;
		dialog.setVisible(true);
	}
	
	public void tuWas(int ID) {
		switch (ID) {
		case 1:
			break ;
		case 2: // Schieberegler
			wert.setzeInteger(balken.leseIntWert());
			break;
		case 5: // wert
			balken.setzeBereich(0, 360, wert.leseInteger(0));
			break;
		default:
		}
		
		if (uebergeordnet != null)
		uebergeordnet.setzeRichtung(wert.leseIntegerGerundet(0));		
	}
}
