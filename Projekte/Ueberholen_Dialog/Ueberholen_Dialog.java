//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Ueberholen_Dialog {
	// Dialog
	private Dlg_JGUIDialog	dialog;
	// Container der Dialog-Komponente
	private IContainer		behaelter;
	
	Schieberegler			startA;
	Schieberegler			vA;
	Schieberegler			aA;
	
	Ausgabe					labelA;
	Ausgabe					lSTartA;
	Ausgabe					lVA;
	Ausgabe					lAA;
	
	Schieberegler			startB;
	Schieberegler			vB;
	Schieberegler			aB;
	
	Ausgabe					labelB;
	Ausgabe					lSTartB;
	Ausgabe					lVB;
	Ausgabe					lAB;
	
	Taste					neueWerte;
	Taste					halt;
	Taste					weiter;
	Taste					spurLoeschen;
	
	double					laenge	= 600;
	double					vmax	= 200;
	double					amax	= 100;
	
	public Ueberholen_Dialog(ITuWas aktion) {
		
		// Dialog anlegen
		// Ein nichtModaler Dialog blockiert andere Fenster des Programms NICHT
		// ! </br>
		// Das Programm laeuft einfach weiter
		
		dialog = new Dlg_JGUIDialog("Einstellungen", Dlg_JGUIDialog.NICHTMODAL);
		// Behaelter fuer Dialogkomponenten lesen
		behaelter = dialog.leseContainer();
		// DIe Schliessen-Ereignisse des Dialogs senden eine Meldung
		dialog.setzeLink(aktion, 0);
		
		// Elemente in den Dialog-Behaelter einfuegen
		
		labelA = new Ausgabe(behaelter, "A", 20, 0, 50, 50);
		startA = new Schieberegler(behaelter, 'H', 50, 0, 400, 40, 0, laenge,
				laenge / 2);
		startA.setzeFarbe("rot");
		startA.setzeLink(aktion, 0);
		lSTartA = new Ausgabe(behaelter, "Start", 470, 0, 100, 50);
		
		vA = new Schieberegler(behaelter, 'H', 50, 50, 400, 40, 0, vmax,
				vmax / 3);
		vA.setzeFarbe("rot");
		vA.setzeLink(aktion, 1);
		lVA = new Ausgabe(behaelter, "-v+", 470, 50, 100, 50);
		
		aA = new Schieberegler(behaelter, 'H', 50, 100, 400, 40, 0, amax,
				amax / 10);
		aA.setzeFarbe("rot");
		aA.setzeLink(aktion, 2);
		lAA = new Ausgabe(behaelter, "-a+", 470, 100, 100, 50);
		
		labelB = new Ausgabe(behaelter, "B", 20, 150, 50, 50);
		startB = new Schieberegler(behaelter, 'H', 50, 150, 400, 40, 0, laenge,
				laenge / 2);
		startB.setzeFarbe("blau");
		startB.setzeLink(aktion, 10);
		lSTartB = new Ausgabe(behaelter, "Start", 470, 150, 100, 50);
		
		vB = new Schieberegler(behaelter, 'H', 50, 200, 400, 40, 0, vmax,
				vmax / 3);
		vB.setzeFarbe("blau");
		vB.setzeLink(aktion, 11);
		lVB = new Ausgabe(behaelter, "-v+", 470, 200, 100, 50);
		
		aB = new Schieberegler(behaelter, 'H', 50, 250, 400, 40, 0, amax,
				amax / 10);
		aB.setzeFarbe("blau");
		aB.setzeLink(aktion, 12);
		lAB = new Ausgabe(behaelter, "-a+", 470, 250, 100, 50);
		
		neueWerte = new Taste(behaelter, "Uebernehmen", 0, 300, 170, 50);
		neueWerte.setzeLink(aktion, 20);
		halt = new Taste(behaelter, "halt", 170, 300, 100, 50);
		halt.setzeLink(aktion, 21);
		weiter = new Taste(behaelter, "Start", 270, 300, 100, 50);
		weiter.setzeLink(aktion, 22);
		spurLoeschen = new Taste(behaelter, "Spuren loeschen", 370, 300, 180, 50);
		spurLoeschen.setzeLink(aktion, 23);
		
		// Groesse des Dialogfensters einstellen
		dialog.setSize(570, 400);
	}
	
	public void setVisible(){
		dialog.setVisible(true);
	}
	
	public static void main(String[] args) {
		Ueberholen_Dialog t = new Ueberholen_Dialog(null);
		t.setVisible();
		
	}
	
}
