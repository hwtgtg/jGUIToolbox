//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Graphische Darstellung eines Fussgaengerueberwegs
 * 
 * Die Strasse mit Ueberweg
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (25.8.2008)
 */

public class Kreuzung_Strasse_Strasse implements ITuWas {
	
	final int					breit						= 1000;
	final int					hoch						= 700;
	final int					strassenbreite				= 200;
	final int					haltestreifenbreiteAuto		= 20;
	
	final int					radius						= 25;
	
	Rechteck					strasseOW;
	Rechteck					strasseNS;
	
	Rechteck					haltelinieW;
	Rechteck					haltelinieO;
	
	Rechteck					haltelinieN;
	Rechteck					haltelinieS;
	
	
	AutoAmpel		aaW;
	AutoAmpel		aaO;

	AutoAmpel		aaN;
	AutoAmpel		aaS;
	
	
	public Kreuzung_Strasse_Strasse() {
		Zeichnung.setzeFenstergroesse(breit, hoch);
		Zeichnung.setzeRasterEin();
		
		strasseNS = new Rechteck((breit - strassenbreite) / 2, 0,
				strassenbreite, hoch);
		strasseOW = new Rechteck(0, (hoch - strassenbreite) / 2, breit,
				strassenbreite);
		
		haltelinieW = new Rechteck((breit - strassenbreite) / 2
				- haltestreifenbreiteAuto, hoch / 2, haltestreifenbreiteAuto,
				strassenbreite / 2);
		haltelinieW.setzeFarbe("weiss");
		
		haltelinieO = new Rechteck((breit + strassenbreite) / 2,
				(hoch - strassenbreite) / 2, haltestreifenbreiteAuto,
				strassenbreite / 2);
		haltelinieO.setzeFarbe("weiss");
		
		haltelinieN = new Rechteck((breit - strassenbreite) / 2,
				(hoch - strassenbreite) / 2 - haltestreifenbreiteAuto,
				strassenbreite/2, haltestreifenbreiteAuto);
		haltelinieN.setzeFarbe("weiss");
		
		haltelinieS = new Rechteck(breit / 2,
				(hoch + strassenbreite) / 2, strassenbreite/2,
				haltestreifenbreiteAuto);
		haltelinieS.setzeFarbe("weiss");
		
		aaW = new AutoAmpel(radius, "W");
		aaW.setzePosition((breit - strassenbreite) / 2 - 6 * radius,
				(hoch + strassenbreite) / 2);
		aaO = new AutoAmpel(radius, "O");
		aaO.setzePosition((breit + strassenbreite) / 2, (hoch - strassenbreite)
				/ 2 - 2 * radius);
		
		aaN = new AutoAmpel(radius, "N");
		aaN.setzePosition((breit - strassenbreite) / 2 - 2 * radius,
				(hoch - strassenbreite) / 2 - 6 * radius);
		
		aaS = new AutoAmpel(radius, "S");
		aaS.setzePosition((breit + strassenbreite) / 2,
				(hoch + strassenbreite) / 2);
	}
	

	public void tuWas(int ID) {
	}
	

	public static void main(String[] args) {
		 new Kreuzung_Strasse_Strasse();
	}
}
