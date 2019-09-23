//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispiel fuer die Anwendung eines selbst gestalteten nicht modalen Dialoghs
 * 
 * @author Witt
 * 
 */
public class B_Dialog_NichtModal_Ausgabe implements ITuWas {
	
	B_Dialog_NichtModal	dialogNichtModal;
	
	AusgabePanel		anzeige;
	Taste				taste;
	
	/**
	 * Konstruktor
	 */
	public B_Dialog_NichtModal_Ausgabe() {
		
		dialogNichtModal = new B_Dialog_NichtModal(this);
		
		// Die Referenz im Dialog wird auf das Hauptfenster gesetzt
		
		anzeige = new AusgabePanel("Ausgabe", 100, 100, 300, 100);
		taste = new Taste("Dialog", 200, 250, 100, 50);
		taste.setzeLink(this, 0);
	}
	
	
	public void tuWas(int ID) {
		// Die Methode beendet sofort nach dem Start des Dialogs!
		dialogNichtModal.setzeSichtbar();
		// nichts weiter zu tun!
	}
	
	public static void main(String[] args) {
		 new B_Dialog_NichtModal_Ausgabe();
	}
}
