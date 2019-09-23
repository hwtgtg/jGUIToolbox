//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
import java.awt.geom.Point2D;


public class B_RotAuto {

	BehaelterBewegt autobehaelter;

	int breite = 40;
	int laenge = 60;

	int rx = 8;
	int rb = 6;

	int dx = 15;
	int dy = breite / 2 - rb / 2;

	int ax = laenge - 2 * dx;

	int lv = 25;

	int lh = 25;

	int zugachsenbreite = 6;

	int behaelterlaenge = laenge - 2 * dx + lh + lv;
	int behaelterbreite = breite;

	Rechteck karosse;
	Rechteck radlh;
	Rechteck radrh;

	Rechteck achseHinten;

	RVorderachse vorderachse;

	public B_RotAuto(int neuesX, int neuesY) {

		autobehaelter = new BehaelterBewegt(neuesX, neuesY, behaelterlaenge,
				behaelterbreite);

		karosse = new Rechteck(autobehaelter, lh - dx, rb / 2, laenge, breite
				- rb);
		radlh = new Rechteck(autobehaelter, lh - rx, 0, 2 * rx, rb);
		radlh.setzeFarbe("schwarz");

		radrh = new Rechteck(autobehaelter, lh - rx, breite - rb, 2 * rx, rb);
		radrh.setzeFarbe("schwarz");

		achseHinten = new Rechteck(autobehaelter, lh - zugachsenbreite/2, rb , zugachsenbreite,2* dy-rb);

		vorderachse = new RVorderachse(this);

		autobehaelter.setzeRotationszentrumRelativ(lh, breite / 2);

		vorderachse.setzeachswinkel(0);

		autobehaelter.setzePositionRotationszentrum(neuesX, neuesY);

	}

	public void bewegenParallelversetzen(float parallel , float radius,
			float geschwindigkeit){
		
		radius = Math.abs(radius);
		float winkel = (float) Math.acos(1.0-Math.abs(parallel)/(2*radius));
		
		autobehaelter.bewegen_KREIS_Bogen(Math.signum(parallel)*radius, winkel, 100);
		autobehaelter.bewegen_KREIS_Bogen(-Math.signum(parallel)*radius, winkel, 100);

	}
	
	public void bewegen(){
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
		karosse.setzeFarbe("rot");

		autobehaelter.bewegen_KREIS_Grad(-100, -45, 100);
		autobehaelter.bewegen_Delay(500);
		
		autobehaelter.bewegen_DREHEN_Grad(135, 1000);
		autobehaelter.bewegen_Delay(500);
		autobehaelter.bewegen_DREHEN_Grad(-180, 500);

		autobehaelter.bewegen_WartenAufEndeBewegen();
		karosse.setzeFarbe("gelb");
		autobehaelter.bewegen_DREHEN_Grad(-180, 500);
		
		autobehaelter.bewegen_WartenAufEndeBewegen();
		autobehaelter.bewegen_LINEAR(autobehaelter.leseRotationspunkt_X() - 100, 100);

	}

}

class RVorderachse {

	BehaelterBewegt vorderachse;

	Rechteck achse;
	int achsenbreite = 6;
	Rechteck radLinksVorn;
	Rechteck radRechtsVorn;

	Rechteck zugachse;

	Point2D.Float ziehen = new Point2D.Float(0, 0);
	float winkel = 0;

	public RVorderachse(B_RotAuto auto) {

		vorderachse = new BehaelterBewegt(auto.autobehaelter, auto.lh + auto.ax
				- auto.rx, 0, auto.lv + auto.rx, auto.breite);
		vorderachse.setzeRotationszentrumRelativ(auto.rx, auto.breite / 2);

		achse = new Rechteck(vorderachse, auto.rx - achsenbreite / 2,
				auto.rb / 2, achsenbreite, auto.dy * 2);
		radLinksVorn = new Rechteck(vorderachse, 0, 0, auto.rx * 2, auto.rb);
		radLinksVorn.setzeFarbe("schwarz");
		radRechtsVorn = new Rechteck(vorderachse, 0, auto.dy * 2, auto.rx * 2,
				auto.rb);
		radRechtsVorn.setzeFarbe("schwarz");
		zugachse = new Rechteck(vorderachse, auto.rx - achsenbreite / 2,
				auto.dy + auto.rb / 2 - auto.zugachsenbreite / 2, auto.lv,
				auto.zugachsenbreite);

	}

	void setzeachswinkel(float winkel) {
		this.winkel = winkel;
		vorderachse.setzeBewegungsrichtungGradmass(winkel);
	}

	void setzeZiehpunkt() {

	}

	public Point2D.Float getZiehpunkt() {
		return ziehen;
	}

}
