//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Hanoi_Turm {
	Behaelter turm ;
	Rechteck	stange;
	int			mitteX;
	int			untenY;
	int			breite;
	int			hoehe;
	int	scheibenhoehe  = 35 ;
	
	int anzahl = 0 ; // Anzahlk der Scheiben auf der Stange
	
	int [] inhalt ;
	
	public Hanoi_Turm(int mitteX, int unten, int breite, int hoehe) {
		this.mitteX = mitteX;
		this.untenY = unten;
		this.breite=breite ;
		this.hoehe=hoehe;
		inhalt = new int[11];
		turm = new Behaelter(mitteX-breite/2, untenY-hoehe, breite, hoehe);
		stange = new Rechteck(turm , (breite*19/40) , 0 , 10, hoehe);
	}

	public void scheibeHinzufuegen(Hanoi_Scheibe scheibe) {
		turm.hinzufuegen(scheibe.obj);
		anzahl++;
		inhalt[anzahl]=scheibe.nr;
		positioniereScheibe(scheibe);
	}
	
	public int scheibeEntfernen() {
		return 	inhalt[anzahl--];
	}

	private void positioniereScheibe(Hanoi_Scheibe scheibe){
		scheibe.positioniere( hoehe - anzahl*scheibenhoehe - 20);
	}
}
