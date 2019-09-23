//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * Landschaft mit Auto
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (6.9.2019)
 */


public class Auto {
	
	private Rechteck reUnten;
	private Rechteck reOben;
	private Rechteck tuer;
	private Rechteck fenster;
	
	private Kreis radLinks;
	private Kreis radRechts;


	public Auto() {

		reUnten = new Rechteck(100, 200,300, 100);
		reUnten.setzeFarbe("orange");

		reOben = new Rechteck(175, 100,150, 100);
		reOben.setzeFarbe("orange");

		tuer = new Rechteck(190, 110,120, 180);
		tuer.setzeFarbe("blau");

		fenster = new Rechteck(200, 120,100, 80);
		fenster.setzeFarbe("gelb");
		
		radLinks = new Kreis( 120, 270 , 30 ) ;
		radLinks.setzeFarbe("schwarz");
		
		radRechts = new Kreis( 320, 270 , 30 ) ;
		radRechts.setzeFarbe("schwarz");
	}

	public static void main(String[] args) {
		new Auto();
		Zeichnung.setzeRasterEin();
	}
}
