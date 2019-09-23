//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Fussgaengerampel
 * 
 * Teil des Projekts Kreuzung
 * 
 */
public class FussgaengerAmpel {
	
	Behaelter		fampel;
	Kreis			rot;
	Kreis			gruen;
	Rechteck		hinten;
	
	FeststellTaste	taste;
	
	static int		breite	= 20;
	static int		hoehe	= 60;
	
	boolean halt = false ;

	/**
	 * Erzeuge ein Exemplar der Klasse Ampel
	 */
	public FussgaengerAmpel() {
		erzeuge('S');
	}
	
	public FussgaengerAmpel(int x, int y, char ausrichtung) {
		erzeuge(ausrichtung);
		setzePosition(x, y);
	}
	
	/**
	 * Zeichne die Ampel.
	 */
	void erzeuge(char r) {
		if (r == 'S') {
			fampel = new Behaelter(21, 61);
			rot = new Kreis(fampel, 2, 2, 8);
			rot.setzeFarbe("rot");
			rot.sichtbarMachen();
			
			gruen = new Kreis(fampel, 2, 22, 8);
			gruen.setzeFarbe("gruen");
			gruen.sichtbarMachen();
			
			hinten = new Rechteck(fampel, 0, 0, 20, 40);
			hinten.setzeFarbe("gruen");
			hinten.rand();
			hinten.sichtbarMachen();
			
			taste = new FeststellTaste(fampel, "", "", 0, 40, 20, 20);
		} else if (r == 'N') {
			fampel = new Behaelter(21, 61);
			rot = new Kreis(fampel, 2, 42, 8);
			rot.setzeFarbe("rot");
			rot.sichtbarMachen();
			
			gruen = new Kreis(fampel, 2, 22, 8);
			gruen.setzeFarbe("gruen");
			gruen.sichtbarMachen();
			
			hinten = new Rechteck(fampel, 0, 20, 20, 40);
			hinten.setzeFarbe("gruen");
			hinten.rand();
			hinten.sichtbarMachen();
			
			taste = new FeststellTaste(fampel, "", "", 0, 0, 20, 20);
		}
		
		taste.setzeFarbeNichtGedrueckt("rot");
		taste.setzeFarbeGedrueckt("gelb");
		
	}
	
	// Horizontal verschieben
	void setzePosition(int x, int y) {
		fampel.setzePosition(x, y);
	}
	
	private void rotAn() {
		rot.fuellen();
	}
	
	private void rotAus() {
		rot.rand();
	}
	
	private void gruenAn() {
		gruen.fuellen();
	}
	
	private void gruenAus() {
		gruen.rand();
	}
	
	void rot() {
		rotAn();
		gruenAus();
		halt = true ;
	}
	
	void gruen() {
		rotAus();
		gruenAn();
		halt = false ;
	}
	
	void ampelAus() {
		rotAus();
		gruenAus();
		halt = false ;
	}
	
	public static void main(String[] args) {
		new FussgaengerAmpel();
		// new FussgaengerAmpel(300,200,'O');
	}
	
}
