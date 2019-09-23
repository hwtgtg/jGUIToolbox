//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
import java.awt.Rectangle;


public class B_TestKollision3 {

	Behaelter behaelter1;
	Dreieck dreieck1;
	Dreieck dreieck2;

	Kreis kreis1;

	public B_TestKollision3() {
		Zeichnung.setzeRasterEin();
		Zeichnung.setzeFenstergroesse(800, 600);

		behaelter1 = new Behaelter(250, 230, 300, 200);
		behaelter1.setzeDeltaX(50);
		behaelter1.setzeDeltaY(50);
		behaelter1.setzeMitRaster(true);

		dreieck1 = new Dreieck(behaelter1, 50, 10, 100, 50);
		dreieck1.setzeAusrichtung("NO");
		dreieck1.setzeFarbe("blau");

		kreis1 = new Kreis(behaelter1, 0, 50, 60);

		dreieck2 = new Dreieck(100, 250, 250, 100);
		dreieck2.setzeAusrichtung("NW");
		dreieck2.setzeFarbe("gelb");

		Rechteck reBeh1Dr2;

		Rectangle reKoll = null;

		if (behaelter1.kollisionMit(dreieck2)) {
			// if (behaelter1.kollisionsgebiet(dreieck2) != null) {
			reKoll = behaelter1.kollisionsgebiet(dreieck2);
			reBeh1Dr2 = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reBeh1Dr2.setzeFarbe("rot");
			reBeh1Dr2.rand();
		}

	}

	public static void main(String[] args) {
		new B_TestKollision3();
	}

}
