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

public class Fussgaenger_Ueberweg implements ITuWas {
	
	final int					breit						= 1000;
	final int					hoch						= 800;
	final int					strassenbreite				= 200;
	final int					haltestreifenbreiteAuto		= 20;
	
	final int					gehsteigbreite				= 200;
	final int					radius						= 25;
	final int					haltestreifenbreitePerson	= 10;
	final int					anzahlZebrastreifen			= 4;
	
	final int					gehsteigLinks				= breit - 400;
	
	final int					halteWest					= gehsteigLinks - 50;
	
	Rechteck					strasse;
	Rechteck					gehsteig;
	
	Rechteck					haltelinieW;
	Rechteck					haltelinieO;
	
	Rechteck					haltelinieN;
	Rechteck					haltelinieS;
	
	Rechteck[]					zebrastreifen;
	
	AutoAmpel		aaW;
	AutoAmpel		aaO;
	
	FussgaengerAmpel	faN;
	FeststellTaste				tasteN;
	
	FussgaengerAmpel	faS;
	FeststellTaste				tasteS;
	
	public Fussgaenger_Ueberweg() {
		Zeichnung.setzeScrollbar(true);
		Zeichnung.setzeRasterEin();
		Zeichnung.setzeFenstergroesse(breit, hoch);
		gehsteig = new Rechteck(gehsteigLinks, 0, gehsteigbreite, hoch);
		gehsteig.setzeFarbe("orange");
		strasse = new Rechteck(0, (hoch - strassenbreite) / 2, breit,
				strassenbreite);
		
		haltelinieW = new Rechteck(gehsteigLinks - haltestreifenbreiteAuto,
				hoch / 2, haltestreifenbreiteAuto, strassenbreite / 2);
		haltelinieW.setzeFarbe("weiss");
		
		haltelinieO = new Rechteck(gehsteigLinks + gehsteigbreite,
				(hoch - strassenbreite) / 2, haltestreifenbreiteAuto,
				strassenbreite / 2);
		haltelinieO.setzeFarbe("weiss");
		
		haltelinieN = new Rechteck(gehsteigLinks, (hoch - strassenbreite) / 2
				- haltestreifenbreitePerson, gehsteigbreite,
				haltestreifenbreitePerson);
		haltelinieN.setzeFarbe("weiss");
		
		haltelinieS = new Rechteck(gehsteigLinks, (hoch + strassenbreite) / 2,
				gehsteigbreite, haltestreifenbreitePerson);
		haltelinieS.setzeFarbe("weiss");
		
		zebrastreifen = new Rechteck[anzahlZebrastreifen];
		int zebrastreifenbreite = strassenbreite
				/ (2 * anzahlZebrastreifen + 1);
		
		for (int i = 0; i < anzahlZebrastreifen; i++) {
			zebrastreifen[i] = new Rechteck(gehsteigLinks,
					(hoch - strassenbreite) / 2 + zebrastreifenbreite
							* (2 * i + 1), gehsteigbreite, zebrastreifenbreite);
			zebrastreifen[i].setzeFarbe("weiss");
		}
		
		aaW = new AutoAmpel(radius, "W");
		aaW.setzePosition(gehsteigLinks - 6 * radius,
				(hoch + strassenbreite) / 2);
		aaO = new AutoAmpel(radius, "O");
		aaO.setzePosition(gehsteigLinks + gehsteigbreite,
				(hoch - strassenbreite) / 2 - 2 * radius);
		
		faN = new FussgaengerAmpel(radius, "N");
		faN.setzePosition(gehsteigLinks - 2 * radius, (hoch - strassenbreite)
				/ 2 - 4 * radius);
		
		faS = new FussgaengerAmpel(radius, "S");
		faS.setzePosition(gehsteigLinks + gehsteigbreite,
				(hoch + strassenbreite) / 2);
		
		tasteN = new FeststellTaste("", "", 2 * radius, 2 * radius);
		tasteN.setzeFarbeGedrueckt("gelb");
		tasteN.setzeFarbeNichtGedrueckt("rot");
		tasteN.setzePosition(gehsteigLinks - 2 * radius,
				(hoch - strassenbreite) / 2 - 6 * radius);
		tasteN.setzeLink(this, 0);
		
		tasteS = new FeststellTaste("", "", 2 * radius, 2 * radius);
		tasteS.setzeFarbeGedrueckt("gelb");
		tasteS.setzeFarbeNichtGedrueckt("rot");
		tasteS.setzePosition(gehsteigLinks + gehsteigbreite,
				(hoch + strassenbreite) / 2 + 4 * radius);
		tasteS.setzeLink(this, 0);
	}
	
	public void tuWas(int ID) {
		tasteN.setzeGewaehlt();
		tasteS.setzeGewaehlt();
	}
	
	public static void main(String[] args) {
		 new Fussgaenger_Ueberweg();
	}
}
