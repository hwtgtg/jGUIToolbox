//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class B_Dialog_Modal {
	
	// Dialog
	public Dlg_JGUIDialog	dialog;
	// Container der Dialog-Komponente
	private IContainer		behaelter;
	
	Eingabefeld				eingabe ;
	
	private Taste			ja ;
	private Taste			nein ;

	public B_Dialog_Modal(){
		// Dialog anlegen
		// Ein modaler Dialog blockiert andere Fenster des Programms ! </br>
		// Dialoge werden verborgen, nicht geschlossen!
		dialog = new Dlg_JGUIDialog("Einstellungen", Dlg_JGUIDialog.MODAL );
		// Behaelter fuer Dialogkomponenten lesen
		behaelter = dialog.leseContainer();
		
		eingabe = new Eingabefeld(behaelter,"....",0,0,400,80);
		
		// Elemente in den Dialog-Behaelter einfuegen
		ja = new Taste(behaelter, "Uebernehmen", 50, 100, 150, 50);
		// Die Taste erhaelt als Link die Dialog-Komponente schliesst den Dialog
		ja.setzeLink(dialog, 1);
		
		nein = new Taste(behaelter, "Abbruch", 200, 100, 150, 50);
		// Die Taste erhaelt als Link die Dialog-Komponente schliesst den Dialog
		nein.setzeLink(dialog, 0);
		
		// Groesse des Dialogfensters einstellen
		dialog.setSize(400, 300);
	}
	
	/**
	 * Der Dialog wird sichtbar.
	 * Der modale Dialog blockiert die Eingabe des Hauptprogramms
	 */
	public void setzeSichtbar() {
		// Dialog anzeigen
		dialog.setVisible(true);
	}
}
