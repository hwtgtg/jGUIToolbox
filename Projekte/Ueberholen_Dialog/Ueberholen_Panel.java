//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Ueberholen_Panel  {
	
	SpurNX			spur;
	Pfeil			pfeil;
	MausBehaelter	autoA;
	MausBehaelter	autoB;
	Kreis			a;
	Kreis			b;
	
	static int				bewegungX	= 700;
	static int				abstand		= 50;
	static int				laenge		= 600;
	static int				radiusA		= 20;
	static int				yA			= abstand + laenge;
	static int				radiusB		= 10;
	static int				yB			= abstand + laenge-100;
	
	public Ueberholen_Panel(ITuWas action ) {
		Zeichnung.setzeFenstergroesse(1000, 800);
		//Zeichnung.setzeRasterEin();
		
		spur = new SpurNX(2,bewegungX, abstand, 1, laenge);
		spur.mitRahmen();
		
		pfeil = new Pfeil(bewegungX, abstand + laenge, bewegungX, abstand);
		pfeil.setzeEnden(false, false);
		pfeil.setzeBreite(8);
		pfeil.setzeFarbe("gruen");
		
		autoA = new MausBehaelter(bewegungX - radiusA, yA, 2 * radiusA,
				2 * radiusA);
		a = new Kreis(autoA, 0, 0, radiusA);
		a.setzeFarbe("rot");
		autoA.setzeMausereignisse(1 << MausBehaelter.PRESS);
		autoB = new MausBehaelter(bewegungX - radiusB, yB, 2 * radiusB,
				2 * radiusB);
		b = new Kreis(autoB, 0, 0, radiusB);
		b.setzeFarbe("blau");
		autoB.setzeMausereignisse(1 << MausBehaelter.PRESS);
		
		spur.loescheSpuren();
		spur.setzeFarbe(0, "rot");
		spur.setzeFarbe(1, "blau");
		
		autoA.setzeLink(action, 100);
		autoB.setzeLink(action, 110);
	}
	
}
