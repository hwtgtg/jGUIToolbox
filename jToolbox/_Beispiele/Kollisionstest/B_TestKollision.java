//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
import java.awt.Rectangle;


public class B_TestKollision {
	
	Behaelter aereaBehaelter ;

	Rechteck rechteck1;
	Rechteck reLiKr;
	Rechteck reReKr;
	
	Ellipse ellipse1;
	Dreieck dreieckRechts ;
	Dreieck dreieckLinks ;
	Rechteck reRe1Dr1 ;
	Rechteck reRe1Ell1;
	Rechteck reElDr1 ;
	
	Quadrat quadrat1 ;
	Rechteck reQaEll ;
	
	
	Kreis kreis1;
	Kreis kreis2;
	Rechteck reKrKr;
	Rechteck reKrQua;
	Rechteck reKr2Dr1;
	Rechteck reKr2Dr2;
	Rechteck reDr1Dr2;
	
	

	public B_TestKollision() {
		Zeichnung.setzeRasterEin();
		Zeichnung.setzeFenstergroesse(800, 600);
		
		rechteck1 = new Rechteck(470, 190, 140, 200);
		rechteck1.setzeFarbe("gruen");


		kreis1 = new Kreis(50, 50, 100);
		kreis1.setzeFarbe("gelb");

		kreis2 = new Kreis(200, 200, 50);
		kreis2.setzeFarbe("orange");

		
		ellipse1 = new Ellipse(250, 100, 250, 100);
		ellipse1.setzeFarbe("magenta");

		dreieckRechts = new Dreieck(292,275,250,110);
		dreieckRechts .setzeAusrichtung("NO");
		dreieckRechts.setzeFarbe("blau");
		
		dreieckLinks = new Dreieck(100,290,124,100);
		dreieckLinks .setzeAusrichtung("NW");
		dreieckLinks.setzeFarbe("blau");

		
		quadrat1 = new Quadrat(200, 0, 120);
		
		
		aereaBehaelter = new Behaelter(100,100,800,800);

		
		Rectangle reKoll = null;
		
		if (ellipse1.kollisionMit(rechteck1)) {
			reKoll = ellipse1.kollisionsgebiet(rechteck1);
			reRe1Ell1 = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reRe1Ell1.setzeFarbe("rot");
			reRe1Ell1.rand();
		}

		if (dreieckRechts.kollisionMit(rechteck1)) {
			reKoll = dreieckRechts.kollisionsgebiet(rechteck1);
			reRe1Dr1 = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reRe1Dr1.setzeFarbe("rot");
			reRe1Dr1.rand();

			aereaBehaelter.setzeAusgabeAerea(rechteck1.getArea());

		
		}
		
		if (dreieckRechts.kollisionMit(kreis2)) {
			reKoll = dreieckRechts.kollisionsgebiet(kreis2);
			reKr2Dr1 = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reKr2Dr1.setzeFarbe("rot");
			reKr2Dr1.rand();
		}
		
		if (dreieckLinks.kollisionMit(kreis2)) {
			reKoll = dreieckLinks.kollisionsgebiet(kreis2);
			reKr2Dr2 = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reKr2Dr2.setzeFarbe("rot");
			reKr2Dr2.rand();
		}
		
		if (dreieckRechts.kollisionMit(dreieckLinks)) {
			reKoll = dreieckLinks.kollisionsgebiet(dreieckRechts);
			reDr1Dr2 = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reDr1Dr2.setzeFarbe("rot");
			reDr1Dr2.rand();
		}

		if (kreis1.kollisionMit(kreis2)) {
			reKoll = kreis1.kollisionsgebiet(kreis2);
			reKrKr = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reKrKr.setzeFarbe("rot");
			reKrKr.rand();
			
		}

		if (ellipse1.kollisionMit(dreieckRechts)) {
			reKoll = ellipse1.kollisionsgebiet(dreieckRechts);
			reElDr1 = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reElDr1.setzeFarbe("rot");
			reElDr1.rand();
		}
		
		

		if (ellipse1.kollisionMit(quadrat1)) {
			reKoll = quadrat1.kollisionsgebiet(ellipse1);
			reQaEll = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reQaEll.setzeFarbe("rot");
			reQaEll.rand();
		}

		if (ellipse1.kollisionMit(quadrat1)) {
			reKoll = quadrat1.kollisionsgebiet(ellipse1);
			reQaEll = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reQaEll.setzeFarbe("rot");
			reQaEll.rand();
		}

		if (ellipse1.kollisionMit(quadrat1)) {
			reKoll = kreis1.kollisionsgebiet(quadrat1);
			reKrQua = new Rechteck(reKoll.x, reKoll.y, reKoll.width,
					reKoll.height);
			reKrQua.setzeFarbe("rot");
			reKrQua.rand();
		}
	
	
	
	}

	public static void main(String[] args) {
		new B_TestKollision();
	}

}
