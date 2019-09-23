//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Hanoi_Hauptfenster {
	
	// Anfang Attribute
	Hanoi_Turm[]		turm;
	Hanoi_Scheibe[]	scheibe;
	
	Behaelter			alles;
	
	int					scheibenhoehe	= 35;
	
	// Ende Attribute
	
	public Hanoi_Hauptfenster() {
		Zeichnung.setzeFenstergroesse(850, 800);
		Zeichnung.setzeRasterEin();
		
		scheibe = new Hanoi_Scheibe[10];
		
		turm = new Hanoi_Turm[3];
		turm[0] = new Hanoi_Turm(200, 500, 200, 400);
		turm[1] = new Hanoi_Turm(400, 500, 200, 400);
		turm[2] = new Hanoi_Turm(600, 500, 200, 400);
		
		for (int i = 9; i >= 0; i--) {
			scheibe[i] = new Hanoi_Scheibe(i, (i + 1) * 20, scheibenhoehe);
			turm[0].scheibeHinzufuegen(scheibe[i]);
		}
		
		alles = new Behaelter(800, 600);
		alles.setzePosition(100, 100);
		alles.hinzufuegen((turm[0].turm));
		alles.hinzufuegen(turm[1].turm);
		alles.hinzufuegen(turm[2].turm);
		
		alles.setzeZoomfaktor(1);
		
	}
	
	// Anfang Methoden
	
	public void bewegeScheibe(int quelle, int ziel) {
		turm[ziel].scheibeHinzufuegen(scheibe[turm[quelle].scheibeEntfernen()]);
	}
	
	public static void main(String[] args) {
		 new Hanoi_Hauptfenster();
	}
	// Ende Methoden
}
