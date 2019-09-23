//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class B_Dialog_NichtModal implements ITuWas {
	
	// Dialog
	public Dlg_JGUIDialog	dialog;
	// Container der Dialog-Komponente
	private IContainer		behaelter;
	
	Eingabefeld				eingabe ;
	private Taste			ende ;

	B_Dialog_NichtModal_Ausgabe hauptfenster ;

	
	public B_Dialog_NichtModal(B_Dialog_NichtModal_Ausgabe hauptfenster){
		this.hauptfenster=hauptfenster;
		// Dialog anlegen
		// Ein nicht modaler Dialog blockiert nicht ! 
		// Dialoge werden verborgen, nicht geschlossen!
		dialog = new Dlg_JGUIDialog("Einstellungen", Dlg_JGUIDialog.NICHTMODAL );
		// Behaelter fuer Dialogkomponenten lesen
		behaelter = dialog.leseContainer();
		
		eingabe = new Eingabefeld(behaelter,"....",0,0,400,80);
		eingabe.setzeLink(this, 0);
		
		// Elemente in den Dialog-Behaelter einfuegen
		ende = new Taste(behaelter, "Verbergen", 50, 100, 150, 50);
		// Die Taste erhaelt als Link die Dialog-Komponente schliesst den Dialog
		ende.setzeLink(dialog, 1);
		
		// Groesse des Dialogfensters einstellen
		dialog.setSize(400, 300);
	}
	
	/**
	 * Der Dialog wird sichtbar.
	 */
	public void setzeSichtbar() {
		// Dialog anzeigen
		dialog.setVisible(true);
	}

	public void tuWas(int ID) {
		if ( hauptfenster != null) // Sicher ist sicher
		{
			hauptfenster.anzeige.setzeAusgabetext(eingabe.leseText());
		}
		
		
	}
}
