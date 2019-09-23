//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

import java.awt.geom.Rectangle2D;

public class Person {
	Behaelter			person;
	Kreis				umriss;
	Ausgabe				nummer;
	
	static int			nr				= 1;
	Rectangle2D.Double	rect;
	
	int					hoehe			= 20;
	int					breite			= 20;
	
	double				geschwindigkeit	= 2;
	String				orientierung	= "O";
	
	Umgebung			u				= null;
	
	Taktgeber			takt;
	
	public Person(Umgebung u, String orientierung) {
		this.u = u;
		if (orientierung.equals("S")) {
			erzeugen(u.gehwegLinks
					+ StaticTools.gibZufall(u.gehwegbreite - breite), 0, "S");
		} else {
			erzeugen(u.gehwegLinks
					+ StaticTools.gibZufall(u.gehwegbreite - breite), u.hoehe
					- breite, "N");
		}
	}
	
	public Person(Umgebung u, String orientierung, char p) {
		this.u = u;
		if (orientierung.equals("S")) {
			if (p == 'r') {
				erzeugen(u.gehwegLinks, 0,
						"S");
			}
		} else {
			if (p == 'r') {
				erzeugen(u.gehwegRechts - breite, u.hoehe -2* breite, "N");
			}
		}
	}
	
	public Person(Umgebung u, int posX, int posY, String orientierung) {
		this.u = u;
		erzeugen(posX, posY, orientierung);
	}
	
	void erzeugen(int posX, int posY, String orientierung) {
		
		rect = new Rectangle2D.Double(posX, posY, breite, hoehe);
		
		this.orientierung = orientierung;
		geschwindigkeit = 1.4 + StaticTools.gibZufall() * 2;
		person = new Behaelter(breite, hoehe);
		umriss = new Kreis(person, 2, 2, (breite - 4) / 2);
		umriss.setzeFarbe("blau");
		nummer = new Ausgabe(person, "" + nr, 2, 1, breite, hoehe);
		nummer.setzeSchriftgroesse(10);
		nr++;
		setzePosition(posX, posY);
		if (orientierung.equals("N")) {
			geschwindigkeit = -geschwindigkeit;
			umriss.setzeFarbe("gelb");
		}
	}
	
	void setzePosition(double x, double y) {
		rect.x = x;
		rect.y = y;
		person.setzePosition((int) x, (int) y);
	}
	
	void setzePosition() {
		setzePosition(rect.x, rect.y);
	}
	
	double oben() {
		return rect.y;
	}
	
	double unten() {
		return rect.y + hoehe;
	}
	
	double neuePosY() {
		if (orientierung.equals("N")) {
			if ((rect.y > u.strasseUnten + 6) && (rect.y < u.strasseUnten + 12)
					&& (u.faSued.halt)) {
				u.faSued.taste.setzeGewaehlt();
				u.faNord.taste.setzeGewaehlt();
			} else {
				rect.y += geschwindigkeit;
			}
		} else {
			if ((rect.y < u.strasseOben - hoehe - 6)
					&& (rect.y > u.strasseOben - hoehe - 12) && (u.faSued.halt)) {
				u.faSued.taste.setzeGewaehlt();
				u.faNord.taste.setzeGewaehlt();
			} else {
				rect.y += geschwindigkeit;
			}
		}
		
		return rect.y;
	}
}
