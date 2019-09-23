//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
import java.awt.Rectangle;


public class B_TestKollision2 {

	 Dreieck dreieck1 ;
	Dreieck dreieck2;

	Linie linie1;

	public B_TestKollision2() {
		Zeichnung.setzeRasterEin();
		Zeichnung.setzeFenstergroesse(800, 600);

		 dreieck1 = new Dreieck(300,250,250,100);
		 dreieck1 .setzeAusrichtung("NO");
		 dreieck1.setzeFarbe("blau");

		dreieck2 = new Dreieck(100, 300, 250, 100);
		dreieck2.setzeAusrichtung("NW");
		dreieck2.setzeFarbe("blau");

		linie1 = new Linie(250, 100, 270, 500);

		 Rechteck reDr1Dr2 ;

		Rechteck reDr1Li1;

		Rectangle reKoll = null;

		 if (dreieck1.kollisionMit(dreieck2)) {
		 reKoll = dreieck2.kollisionsgebiet(dreieck1);
		 reDr1Dr2 = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
		 reKoll.height);
		 reDr1Dr2.setzeFarbe("rot");
		 reDr1Dr2.rand();
		 }

//		// if (dreieck2.kollisionMit(linie1)) {
//		if (dreieck2.kollisionsgebiet(linie1) != null) {
//			reKoll = dreieck2.kollisionsgebiet(linie1);
//			reDr1Li1 = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
//					reKoll.height);
//			reDr1Li1.setzeFarbe("rot");
//			reDr1Li1.rand();
//		}

	}

	public static void main(String[] args) {
		new B_TestKollision2();
	}

}
