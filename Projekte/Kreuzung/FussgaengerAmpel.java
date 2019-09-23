//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Graphische Darstellung eines Fussgaengerueberwegs
 * 
 * Die Klasse Fussgaengerampel
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (25.8.2008)
 */
public class FussgaengerAmpel {
	
	private Behaelter		fAampel;
	private Kreis			rot;
	private Kreis			gruen;
	private Rechteck		hinten;
	
	int						breite		= 50;
	int						hoehe		= 100;
	int radius = 50 ;
	
	StaticTools.Richtung	richtung	= StaticTools.Richtung.S;
	
	/**
	 * Erzeuge ein Exemplar der Klasse Ampel
	 */
	public FussgaengerAmpel(int radius, String richtung) {
		this.radius = radius ;
		this.richtung = StaticTools.getRichtung(richtung);
		fAampel = new Behaelter(0, 0, 100, 500);
		
		hinten = new Rechteck(fAampel);
		hinten.setzeFarbe("hellgrau");
		hinten.fuellen();
		hinten.sichtbarMachen();
		
		rot = new Kreis(fAampel);
		rot.setzeFarbe("rot");
		rot.sichtbarMachen();
		
		gruen = new Kreis(fAampel);
		gruen.setzeFarbe("gruen");
		gruen.sichtbarMachen();
		ausrichtung();
	}
	
	public void ausrichtung() {
		switch (richtung) {
		case S:
			breite = radius * 2 ;
			hoehe = radius * 4 ;
			rot.setzeGroesse(radius-2);
			gruen.setzeGroesse(radius-2);
			rot.setzePosition(1, 1);
			gruen.setzePosition(1, breite+1);
			hinten.setzeDimensionen(0, 0, breite, hoehe);
			fAampel.setzeGroesse(breite, hoehe);
			break;
		case N:
			breite = radius * 2 ;
			hoehe = radius * 4 ;
			rot.setzeGroesse(radius-2);
			gruen.setzeGroesse(radius-2);
			rot.setzePosition(1, breite+1);
			gruen.setzePosition(1, 1);
			hinten.setzeDimensionen(0, 0, breite, hoehe);
			fAampel.setzeGroesse(breite, hoehe);
			break;
		case O:
			breite = radius * 4 ;
			hoehe = radius * 2 ;
			rot.setzeGroesse(radius-2);
			gruen.setzeGroesse(radius-2);
			rot.setzePosition(1, 1);
			gruen.setzePosition(1+hoehe, 1);
			hinten.setzeDimensionen(0, 0, breite, hoehe);
			fAampel.setzeGroesse(breite, hoehe);
			break;
		case W:
			breite = radius * 4 ;
			hoehe = radius * 2 ;
			rot.setzeGroesse(radius-2);
			gruen.setzeGroesse(radius-2);
			rot.setzePosition(1+hoehe, 1);
			gruen.setzePosition(1, 1);
			hinten.setzeDimensionen(0, 0, breite, hoehe);
			fAampel.setzeGroesse(breite, hoehe);
			break;
		default:

		}
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
	
	public void rot() {
		rotAn();
		gruenAus();
	}
	
	public void gruen() {
		rotAus();
		gruenAn();
	}
	
	public void ampelAus() {
		rotAus();
		gruenAus();
	}
	
	public void setzePosition( int neuesX , int neuesY){
		fAampel.setzePosition(neuesX, neuesY);
	}
	
	
	
	public static void main(String[] args) {
		 new FussgaengerAmpel(20,"W");
	}

}
