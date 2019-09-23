//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Die Autoampel
 * 
 * Teil des Projekts Kreuzung
 * 
 */
public class AutoAmpel {
	
	Behaelter	aampel;
	Kreis		rot;
	Kreis		gelb;
	Kreis		gruen;
	Rechteck	hinten;
	
	boolean halt = false ;
	
	static int	breite	= 60;
	static int	hoehe	= 20;
	
	/**
	 * Erzeuge ein Exemplar der Klasse Ampel
	 */
	public AutoAmpel() {
		erzeuge('O');
	}
	
	public AutoAmpel(int x, int y, char ausrichtung) {
		erzeuge(ausrichtung);
		setzePosition(x, y);
	}
	
	/**
	 * Zeichne die Ampel.
	 */
	void erzeuge(char c) {
		if (c == 'W') {
			aampel = new Behaelter(61, 21);
			rot = new Kreis(aampel, 42, 2, 8);
			rot.setzeFarbe("rot");
			rot.sichtbarMachen();
			
			gelb = new Kreis(aampel, 22, 2, 8);
			gelb.setzeFarbe("gelb");
			gelb.sichtbarMachen();
			
			gruen = new Kreis(aampel, 2, 2, 8);
			gruen.setzeFarbe("gruen");
			gruen.sichtbarMachen();
			
			hinten = new Rechteck(aampel, 0, 0, 60, 20);
			hinten.setzeFarbe("gruen");
			hinten.rand();
			hinten.sichtbarMachen();
		} else if (c == 'O') {
			aampel = new Behaelter(61, 21);
			rot = new Kreis(aampel, 2, 2, 8);
			rot.setzeFarbe("rot");
			rot.sichtbarMachen();
			
			gelb = new Kreis(aampel, 22, 2, 8);
			gelb.setzeFarbe("gelb");
			gelb.sichtbarMachen();
			
			gruen = new Kreis(aampel, 42, 2, 8);
			gruen.setzeFarbe("gruen");
			gruen.sichtbarMachen();
			
			hinten = new Rechteck(aampel, 0, 0, 60, 20);
			hinten.setzeFarbe("gruen");
			hinten.rand();
			hinten.sichtbarMachen();
		}
		
	}
	
	// Horizontal verschieben
	void setzePosition(int x, int y) {
		aampel.setzePosition(x, y);
	}
	
	private void rotAn() {
		rot.fuellen();
	}
	
	private void rotAus() {
		rot.rand();
	}
	
	private void gelbAn() {
		gelb.fuellen();
	}
	
	private void gelbAus() {
		gelb.rand();
	}
	
	private void gruenAn() {
		gruen.fuellen();
	}
	
	private void gruenAus() {
		gruen.rand();
	}
	
	void rot() {
		rotAn();
		gelbAus();
		gruenAus();
		halt = true ;
	}
	
	void rotGelb() {
		rotAn();
		gelbAn();
		gruenAus();
	}
	
	public void gelb() {
		rotAus();
		gelbAn();
		gruenAus();
		halt = true ;
	}
	
	void gruen() {
		rotAus();
		gelbAus();
		gruenAn();
		halt = false ;
	}
	
	void ampelAus() {
		rotAus();
		gelbAus();
		gruenAus();
		halt = false ;
	}
	
	public static void main(String[] args) {
		new AutoAmpel(100, 100, 'W');
		new AutoAmpel(300, 200, 'O');
	}
}
