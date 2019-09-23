//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * Aufzug mit Steuerung
 * 
 * Eine Person
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (23.8.2008)
 */

public class Person {
	
	private Behaelter	person;
	
	private Kreis		kopf;
	
	private Linie		rumpf;
	private Linie		handLi;
	private Linie		handRe;
	private Linie		fussLi;
	private Linie		fussRe;
	
	public Person() {
		
		person = new Behaelter(50, 50, 50, 100);
		
		kopf = new Kreis(person, 10, 0, 15);
		kopf.setzeFarbe("schwarz");
		kopf.fuellen();
		
		rumpf = new Linie(person, 25, 30, 25, 70);
		rumpf.setzeFarbe("schwarz");
		
		handLi = new Linie(person, 25, 50, 5, 30);
		handLi.setzeFarbe("schwarz");
		
		handRe = new Linie(person, 25, 50, 45, 30);
		handRe.setzeFarbe("schwarz");
		
		fussLi = new Linie(person, 25, 70, 5, 95);
		fussLi.setzeFarbe("schwarz");
		
		fussRe = new Linie(person, 25, 70, 45, 95);
		fussRe.setzeFarbe("schwarz");
	}
	
	public void setzePosition(int neuesX, int neuesY) {
		person.setzePosition(neuesX, neuesY);
	}
	
	public static void main(String[] args) {
		new Person();
		// Zeichnung.setzeRasterEin();
	}
}
