//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Dezimalziffer_Dual {
	
	Behaelter	zeichen;
	Kreis		d8;
	Kreis		d4;
	Kreis		d2;
	Kreis		d1;
	
	public Dezimalziffer_Dual( IContainer behaelter) {
		zeichen = new Behaelter(behaelter,100, 100, 50, 200);
		d8 = new Kreis(zeichen, 0, 0, 20);
		d4 = new Kreis(zeichen, 0, 50, 20);
		d2 = new Kreis(zeichen, 0, 100, 20);
		d1 = new Kreis(zeichen, 0, 150, 20);
	}
	
	public void setzeZiffer(int i) {
		int a = i;
		if ((a / 8) != 0) {
			d8.setzeFarbe("rot");
		} else {
			d8.setzeFarbe("hellgrau");
		}
		a = a % 8;
		if ((a / 4) != 0) {
			d4.setzeFarbe("rot");
		} else {
			d4.setzeFarbe("hellgrau");
		}
		a = a % 4;
		if ((a / 2) != 0) {
			d2.setzeFarbe("rot");
		} else {
			d2.setzeFarbe("hellgrau");
		}
		a = a % 2;
		if (a != 0) {
			d1.setzeFarbe("rot");
		} else {
			d1.setzeFarbe("hellgrau");
		}
	}
	
	public void setzePosition(int neuesX, int neuesY) {
		zeichen.setzePosition(neuesX, neuesY);
	}
	
	public static void main(String[] args) {
		Dezimalziffer_Dual u = new Dezimalziffer_Dual(Zeichnung.gibZeichenflaeche()) ;
		
		for (int i = 0; i < 16; i++) {
			u.setzePosition(i*10, 0);
			u.setzeZiffer(i);
			StaticTools.warte(500);
		}
		
		// u.setzePosition(200,200);
		// Zeichnung.setzeRasterEin();
	}
	
}
