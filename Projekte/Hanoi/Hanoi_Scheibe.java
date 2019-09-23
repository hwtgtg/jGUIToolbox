//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Hanoi_Scheibe {
	// Anfang Variablen
	Behaelter obj ;
	Rechteck	scheibe;
	int			posX	= 0;
	int			posY	= 0;
	int			breite	= 0;
	int			hoehe	= 0;
	int			nr		= -1;
	int			mitteX	= 100;
	
	// Ende Variablen
	
	public Hanoi_Scheibe(int nr, int breite, int hoehe) {
		obj =  new Behaelter( 200 , hoehe);
		this.nr = nr;
		this.breite = breite;
		this.hoehe = hoehe;
		scheibe = new Rechteck(obj, mitteX - breite/2 ,0,breite, hoehe);
		scheibe.setzeFarbe("gelb");
	}
	
	// Anfang Ereignisprozeduren
	
	public void positioniere(int posY) {
		this.posX = mitteX - breite / 2;
		this.posY = posY;
		obj.setzePosition(0, posY);
	}
	
	// Ende Ereignisprozeduren
}
