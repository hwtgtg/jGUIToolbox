//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * Graphische Darstellung eines Fussgaengerueberwegs
 * 
 * Die Klasse Autoampel
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (25.8.2008)
 */
public class AutoAmpel {

	private Behaelter fAampel;
	private Kreis rot;
	private Kreis gelb;
	private Kreis gruen;
	private Rechteck hinten;

	int breite = 50;
	int hoehe = 150;
	int radius = 50;

	StaticTools.Richtung richtung = StaticTools.Richtung.S;

	/**
	 * Erzeuge ein Exemplar der Klasse Ampel
	 */
	public AutoAmpel(int radius, String richtung) {
		this.radius = radius;
		this.richtung = StaticTools.getRichtung(richtung);
		fAampel = new Behaelter(0, 0, 100, 500);

		hinten = new Rechteck(fAampel);
		hinten.setzeFarbe("hellgrau");
		hinten.fuellen();
		hinten.sichtbarMachen();

		rot = new Kreis(fAampel);
		rot.setzeFarbe("rot");
		rot.sichtbarMachen();

		gelb = new Kreis(fAampel);
		gelb.setzeFarbe("gelb");
		gelb.sichtbarMachen();

		gruen = new Kreis(fAampel);
		gruen.setzeFarbe("gruen");
		gruen.sichtbarMachen();
		ausrichtung();
	}

	public void ausrichtung() {
		switch (richtung) {
		case S:
			breite = radius * 2;
			hoehe = radius * 6;
			rot.setzeGroesse(radius - 2);
			gelb.setzeGroesse(radius - 2);
			gruen.setzeGroesse(radius - 2);
			rot.setzePosition(1, 1);
			gelb.setzePosition(1, breite + 1);
			gruen.setzePosition(1, 2 * breite + 1);
			hinten.setzeDimensionen(0, 0, breite, hoehe);
			fAampel.setzeGroesse(breite, hoehe);
			break;
		case N:
			breite = radius * 2;
			hoehe = radius * 6;
			rot.setzeGroesse(radius - 2);
			gelb.setzeGroesse(radius - 2);
			gruen.setzeGroesse(radius - 2);
			rot.setzePosition(1, 2 * breite + 1);
			gelb.setzePosition(1, breite + 1);
			gruen.setzePosition(1, 1);
			hinten.setzeDimensionen(0, 0, breite, hoehe);
			fAampel.setzeGroesse(breite, hoehe);
			break;
		case O:
			breite = radius * 6;
			hoehe = radius * 2;
			rot.setzeGroesse(radius - 2);
			gelb.setzeGroesse(radius - 2);
			gelb.setzePosition(1, breite + 1);
			gruen.setzeGroesse(radius - 2);
			rot.setzePosition(1, 1);
			gelb.setzePosition(1 + hoehe, 1);
			gruen.setzePosition(1 + 2 * hoehe, 1);
			hinten.setzeDimensionen(0, 0, breite, hoehe);
			fAampel.setzeGroesse(breite, hoehe);
			break;
		case W:
			breite = radius * 6;
			hoehe = radius * 2;
			rot.setzeGroesse(radius - 2);
			gelb.setzeGroesse(radius - 2);
			gruen.setzeGroesse(radius - 2);
			rot.setzePosition(1 + 2 * hoehe, 1);
			gelb.setzePosition(1 + hoehe, 1);
			gruen.setzePosition(1, 1);
			hinten.setzeDimensionen(0, 0, breite, hoehe);
			fAampel.setzeGroesse(breite, hoehe);
			break;
		default:

		}
	}

	public void setzePosition(int neuesX, int neuesY) {
		fAampel.setzePosition(neuesX, neuesY);
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

	public void rot() {
		rotAn();
		gelbAus();
		gruenAus();
	}

	public void rotGelb() {
		rotAn();
		gelbAn();
		gruenAus();
	}

	public void gelb() {
		rotAus();
		gelbAn();
		gruenAus();
	}

	public void gruen() {
		rotAus();
		gelbAus();
		gruenAn();
	}

	public void ampelAus() {
		rotAus();
		gelbAus();
		gruenAus();
	}

	
	public static void main(String[] args) {
		 new AutoAmpel(20,"W");
	}

}
