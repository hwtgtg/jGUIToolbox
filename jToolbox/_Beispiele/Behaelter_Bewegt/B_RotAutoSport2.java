//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
// Sicherung ohne LichtAnUndAus



public class B_RotAutoSport2 {

	BehaelterBewegt autobehaelter;

	int breite = 40;
	int laenge = 60;

	int radRadius = 8;
	int radBreite = 8;

	int deltaXRand = 15;

	int lichtRadius=4;
	
	int ax = laenge - 2 * deltaXRand;

	int abstandAchseVorn = 25;

	int abstandAchseHinten = 25;

	int achsenbreite = 6;

	int behaelterlaenge = laenge - 2 * deltaXRand + abstandAchseHinten
			+ abstandAchseVorn;
	int behaelterbreite = breite;
	int deltaYRand = radBreite / 2;
	
	Rechteck karosse;

	Kreis karosseHinten;
	int einruecken = 4;
	int radiusHinten = breite / 2 - radBreite - einruecken ;
	Dreieck karosseVorn;
	int breiteVorn = radiusHinten *2 ;

	Rechteck karosseMitte;

	Rechteck radlh;
	Rechteck radrh;

	Rechteck radlv;
	Rechteck radrv;

	Rechteck achseHinten;
	Rechteck achseVorn;
	
	Kreis lichtLi ;
	Kreis lichtRe ;
	
	Kreis bremseLi ;
	Kreis bremseRe ;
	
	Kreis blinkenLiVorne;
	Kreis blinkenLiHinten;
	
	Kreis blinkenReVorne;
	Kreis blinkenReHinten;
	

	public B_RotAutoSport2(int neuesX, int neuesY) {

		autobehaelter = new BehaelterBewegt(neuesX, neuesY, behaelterlaenge,
				behaelterbreite);

		karosse = new Rechteck(autobehaelter, abstandAchseHinten - deltaXRand,
				radBreite / 2, laenge, breite - radBreite);

		karosseMitte = new Rechteck(autobehaelter, abstandAchseHinten,
				behaelterbreite / 2 - breiteVorn/2 , behaelterlaenge
						- abstandAchseHinten - abstandAchseVorn, breiteVorn);
		karosseMitte.setzeFarbe("gelb");

		karosseHinten = new Kreis(autobehaelter);
		karosseHinten.setzeMittelpunktUndRadius(abstandAchseHinten,
				behaelterbreite / 2, radiusHinten);
		karosseHinten.setzeFarbe("gelb");

		karosseVorn = new Dreieck(autobehaelter,behaelterlaenge
				- abstandAchseVorn, behaelterbreite / 2 -  breiteVorn/2, breiteVorn/2 ,breiteVorn);
		karosseVorn.setzeAusrichtung("O");
		karosseVorn.setzeFarbe("gelb");

		radlh = new Rechteck(autobehaelter, abstandAchseHinten - radRadius, 0,
				2 * radRadius, radBreite);
		radlh.setzeFarbe("schwarz");

		radrh = new Rechteck(autobehaelter, abstandAchseHinten - radRadius,
				breite - radBreite, 2 * radRadius, radBreite);
		radrh.setzeFarbe("schwarz");

		radlv = new Rechteck(autobehaelter, behaelterlaenge - abstandAchseVorn
				- radRadius, 0, 2 * radRadius, radBreite);
		radlv.setzeFarbe("schwarz");

		radrv = new Rechteck(autobehaelter, behaelterlaenge - abstandAchseVorn
				- radRadius, breite - radBreite, 2 * radRadius, radBreite);
		radrv.setzeFarbe("schwarz");

		achseHinten = new Rechteck(autobehaelter, abstandAchseHinten
				- achsenbreite / 2, radBreite, achsenbreite, behaelterbreite
				- 2*radBreite);
		achseVorn = new Rechteck(autobehaelter, behaelterlaenge
				- abstandAchseVorn - achsenbreite / 2, radBreite, achsenbreite,
				behaelterbreite - 2*radBreite);
		
		blinkenLiHinten = new Kreis(autobehaelter);
		blinkenLiHinten.setzeMittelpunktUndRadius(abstandAchseHinten - deltaXRand, deltaYRand , lichtRadius);
		blinkenLiHinten.setzeFarbe("orange");
		
		blinkenLiVorne = new Kreis(autobehaelter);
		blinkenLiVorne.setzeMittelpunktUndRadius(behaelterlaenge - abstandAchseVorn +deltaXRand, deltaYRand , lichtRadius);
		blinkenLiVorne.setzeFarbe("orange");
		
		blinkenReHinten = new Kreis(autobehaelter);
		blinkenReHinten.setzeMittelpunktUndRadius(abstandAchseHinten - deltaXRand, behaelterbreite-deltaYRand , lichtRadius);
		blinkenReHinten.setzeFarbe("orange");
		
		blinkenReVorne = new Kreis(autobehaelter);
		blinkenReVorne.setzeMittelpunktUndRadius(behaelterlaenge - abstandAchseVorn +deltaXRand, behaelterbreite-deltaYRand , lichtRadius);
		blinkenReVorne.setzeFarbe("orange");
	
		
		lichtLi = new Kreis(autobehaelter);
		lichtLi.setzeMittelpunktUndRadius(behaelterlaenge - abstandAchseVorn +deltaXRand, deltaYRand +2*lichtRadius, lichtRadius);
		lichtLi.setzeFarbe("cyan");
		
		lichtRe = new Kreis(autobehaelter);
		lichtRe.setzeMittelpunktUndRadius(behaelterlaenge - abstandAchseVorn +deltaXRand, behaelterbreite-deltaYRand-2*lichtRadius , lichtRadius);
		lichtRe.setzeFarbe("cyan");
	
		bremseLi = new Kreis(autobehaelter);
		bremseLi.setzeMittelpunktUndRadius(abstandAchseHinten - deltaXRand, deltaYRand +2*lichtRadius, lichtRadius);
		bremseLi.setzeFarbe("rot");
		
		bremseRe = new Kreis(autobehaelter);
		bremseRe.setzeMittelpunktUndRadius(abstandAchseHinten - deltaXRand, behaelterbreite-deltaYRand-2*lichtRadius , lichtRadius);
		bremseRe.setzeFarbe("rot");
		
		autobehaelter.setzeRotationszentrumRelativ(abstandAchseHinten,
				breite / 2);

		autobehaelter.setzePositionRotationszentrum(neuesX, neuesY);

	}

	public void bewegenParallelversetzen(float parallel, float radius,
			float geschwindigkeit) {

		radius = Math.abs(radius);
		float winkel = (float) Math.acos(1.0 - Math.abs(parallel)
				/ (2 * radius));

		autobehaelter.bewegen_KREIS_Bogen(Math.signum(parallel)
				* radius, winkel, 100);
		autobehaelter.bewegen_KREIS_Bogen(-Math.signum(parallel)
				* radius, winkel, 100);

	}

	public void bewegen() {
		autobehaelter.bewegen_NeueAusrichtungGradmass(0);
		autobehaelter.bewegen_Delay(500);

		bewegenParallelversetzen(50, 100, 100);

		 autobehaelter.bewegen_LINEAR(50, 100);
		 autobehaelter.bewegen_KREIS_Grad(-100, 90, 100);
		 autobehaelter.bewegen_KREIS_Grad(+100, 90, 100);
		 autobehaelter.bewegen_LINEAR(100, 100);
		
		 autobehaelter.bewegen_KREIS_Grad(+100, 45, 100);
		 autobehaelter.bewegen_LINEAR(80, 100);
		 autobehaelter.bewegen_KREIS_Grad(-100, 45, 100);
		 autobehaelter.bewegen_Delay(500);
		
		 autobehaelter.bewegen_WartenAufEndeBewegen();
//		 karosse.setzeFarbe("rot");
		
		 autobehaelter.bewegen_KREIS_Grad(-100, -45, 100);
		 autobehaelter.bewegen_Delay(500);
		
		 autobehaelter.bewegen_DREHEN_Grad(135, 1000);
		 autobehaelter.bewegen_Delay(500);
		 autobehaelter.bewegen_DREHEN_Grad(-180, 500);
		
		 autobehaelter.bewegen_WartenAufEndeBewegen();
//		 karosse.setzeFarbe("gruen");
		 autobehaelter.bewegen_DREHEN_Grad(-180, 500);
		
		 autobehaelter.bewegen_WartenAufEndeBewegen();
		 autobehaelter.bewegen_LINEAR(autobehaelter.leseRotationspunkt_X()
		 - 100, 100);

	}

}
