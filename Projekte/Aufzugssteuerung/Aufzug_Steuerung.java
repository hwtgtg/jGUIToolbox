//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * Aufzug mit Steuerung
 * 
 * Die Steuerung
 * 
 * 
 * IDs des Schachts : 2. Stock: 2 -- 1. Stock: 1 -- Erdgeschoss: 0
 * 
 * IDs der Kabine: 2. Stock: 12 -- 1. Stock: 11 -- Erdgeschoss: 10
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (23.8.2008)
 */

public class Aufzug_Steuerung implements ITuWas {
	
	private Aufzug_Schacht	schacht;
	private Aufzug_Kabine		kabine;
	private Person		person;
	
	public Aufzug_Steuerung() {
		Zeichnung.setzeScrollbar(true);

		schacht = new Aufzug_Schacht();
		schacht.setzePosition(25, 25);
		
		kabine = new Aufzug_Kabine();
		kabine.setzePosition(13 + 25, 43 + 25);
		
		person = new Person();
		person.setzePosition(300 + 60 , 450 + 25);

		Zeichnung.setzeFenstergroesse(450, 700);
		
	}
	
	public void tuWas(int ID) {
		
	}
	
	public void kabineNach2() {
		kabine.bewege(43 + 25);
	}
	
	public void kabineNach1() {
		kabine.bewege(180 + 43 + 25);
	}
	
	public void kabineNachE() {
		kabine.bewege(360 + 43 + 25);
	}
	
	public static void main(String[] args) {
		Aufzug_Steuerung k = new Aufzug_Steuerung();
		k.kabineNachE();
		// Zeichnung.setzeRasterEin();
	}
	
}
