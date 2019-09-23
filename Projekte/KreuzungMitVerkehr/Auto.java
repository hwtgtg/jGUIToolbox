//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Auto implements ITuWas {
	Behaelter	auto;
	Rechteck	umriss;
	Dreieck		richtung;
	Ausgabe		nummer;
	
	int			laenge				= 60;
	int			breite				= 40;
	
	double		geschwindigkeitMax	= 3;
	double		geschwinndigkeitMin	= 5;
	double		geschwindigkeit		= 3;
	double		posX				= 0;
	double		posY				= 100;
	String		orientierung		= "O";
	
	Umgebung	u					= null;
	
	Taktgeber	takt;
	
	public Auto(Umgebung u, String orientierung, int nr) {
		this.u = u;
		if (orientierung.equals("O")) {
			erzeugen(0, u.strasseMitte + 7, "O", nr);
		} else {
			erzeugen(u.breite - laenge, u.strasseOben + 2, "W", nr);
		}
	}
	
	void erzeugen(int posX, int posY, String orientierung, int nr) {
		geschwindigkeitMax = StaticTools.gibZufall() * 15 + geschwinndigkeitMin;
		geschwindigkeit = geschwindigkeitMax;
		this.posX = posX;
		this.posY = posY;
		this.orientierung = orientierung;
		auto = new Behaelter(laenge, breite);
		umriss = new Rechteck(auto, 2, 2, laenge - 4, breite - 4);
		richtung = new Dreieck(auto, laenge / 6, 4, laenge * 2 / 3, breite - 8);
		richtung.setzeAusrichtung(orientierung);
		richtung.setzeFarbe("rot");
		nummer = new Ausgabe(auto, "" + nr, laenge / 6, 4, laenge * 2 / 3,
				breite - 8);
		setzePosition(posX, posY);
		if (orientierung.equals("W")) {
			geschwindigkeit = -geschwindigkeit;
		}
		
		takt = new Taktgeber();
		takt.setzeZeitZwischenAktionen(70);
		takt.endlos();
		takt.setzeLink(this);
	}
	
	void setzePosition(int x, int y) {
		auto.setzePosition(x, y);
	}
	
	void setzePositionX(double x) {
		posX = x;
		auto.setzePosition((int) posX, (int) posY);
	}
	
	double vorn() {
		if (orientierung.equals("O")) {
			return posX + laenge;
		} else {
			return posX;
		}
	}
	
	double hinten() {
		if (orientierung.equals("O")) {
			return posX;
		} else {
			return posX + laenge;
		}
	}
	
	double neuePosX() {
		if (orientierung.equals("W")) {
			if ((posX < u.gehwegRechts + 45) && (posX > u.gehwegRechts + 15)
					&& (u.aaWest.halt)) {
				// nichts
			} else {
				posX += geschwindigkeit;
			}
		} else {
			if ((posX > u.gehwegLinks - laenge - 45)
					&& (posX < u.gehwegLinks - laenge - 15) && (u.aaOst.halt)) {
				// nichts
			} else {
				posX += geschwindigkeit;
			}
		}
		
		return posX;
	}
	
	public void tuWas(int ID) {
		neuePosX();
		if (orientierung.equals("W")) {
			if (posX <= 0) {
				posX = u.breite - laenge;
			}
		} else {
			if (posX >= (u.breite - laenge)) {
				posX = 60;
			}
		}
		
		setzePosition((int) posX, (int) posY);
	}
}
