//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispiel fuer die Anwendung eines selbst gestalteten modalen Dialoghs
 * 
 * @author Witt
 *
 */
public class B_Dialog_Modal_Ausgabe implements ITuWas{
	
	B_Dialog_Modal	dialogModal;

	AusgabePanel anzeige ;
	Taste				taste;

	/**
	 * Konstruktor
	 */
	public B_Dialog_Modal_Ausgabe () {
		
		dialogModal = new B_Dialog_Modal();
		anzeige = new AusgabePanel("Ausgabe",100,100,300,100);
		taste = new Taste("Dialog", 200 , 250 , 100 , 50 );
		taste.setzeLink(this, 0);
	}

	public void tuWas(int ID) {
		dialogModal.setzeSichtbar();
		if (dialogModal.dialog.leseCloseID()== 1 ){
			anzeige.setzeAusgabetext(dialogModal.eingabe.leseText());
		}
	}
	
	public static void main(String[] args) {
		 new B_Dialog_Modal_Ausgabe();
	}
}
