//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Pumpe_Kolben_W {
	int					mitteX				= 200;
	int					hoehe				= 100;
	int					links				= 300;
	int					breite				= 140;
	int					zylinderRestbreite	= 50;
	int					wandstaerke			= 14;
	
	Behaelter			bZylinder;
	Rechteck			zylinder;
	Rechteck			innen;
	Rechteck			inhalt;
	
	Behaelter			kolben;
	Pumpe_Pleuel	achse;
	int					pBreite				= 26;
	Rechteck			kolbenQ;
	
	Pumpe_Ventil	oben;
	Pumpe_Ventil	unten;
	
	public Pumpe_Kolben_W() {
		bZylinder = new Behaelter(0, 0, 2 * breite + wandstaerke + pBreite
				+ zylinderRestbreite, hoehe + 3 * wandstaerke);
		zylinder = new Rechteck(bZylinder, breite, wandstaerke, breite
				+ wandstaerke + pBreite + zylinderRestbreite, hoehe + 2
				* wandstaerke);
		innen = new Rechteck(bZylinder, breite, 2 * wandstaerke, breite
				+ pBreite + zylinderRestbreite, hoehe);
		innen.setzeFarbe("weiss");
		inhalt = new Rechteck(bZylinder, breite, 2 * wandstaerke, breite
				+ pBreite + zylinderRestbreite, hoehe);
		inhalt.setzeFarbe("blau");
		
		kolben = new Behaelter(bZylinder, 0, 2 * wandstaerke, breite + pBreite,
				hoehe);
		achse = new Pumpe_Pleuel(kolben, pBreite / 2,
				hoehe / 2, breite + pBreite / 2, hoehe / 2);
		achse.setzeBreite(pBreite);
		kolbenQ = new Rechteck(kolben, breite, 1, pBreite, hoehe - 3);
		
		oben = new Pumpe_Ventil(bZylinder);
		
		oben.setzePosition(2 * breite + pBreite + 5, 0);
		
		unten = new Pumpe_Ventil(bZylinder);
		unten.setzePosition(2 * breite + pBreite + 5, wandstaerke + hoehe - 1);
		
	}
	
	public void setzePosition(int startX, int mitte) {
		mitteX = mitte;
		bZylinder.setzePosition(startX - pBreite / 2, mitteX - hoehe / 2 - 2
				* wandstaerke);
	}
	
	public void positioniereZylinder(int pos) {
		kolben.setzePosition(pos, 2 * wandstaerke);
		inhalt.setzeDimensionen(pos + breite + pBreite, 2 * wandstaerke, -pos
				+ breite + zylinderRestbreite, hoehe);
	}
	
	/*
	 * @param args
	 */

	public static void main(String[] args) {
		Pumpe_Kolben_W s = new Pumpe_Kolben_W();
		Zeichnung.setzeFenstergroesse(1000, 600);
		Zeichnung.setzeRasterEin();
		s.positioniereZylinder(s.breite - 1);
		
	}
}
