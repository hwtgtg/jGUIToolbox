 

 
//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * Auto als Behaelter
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (22.10.2008)
 */
public class Behaelter_Rotationstest implements ITuWas {

	private BehaelterDrehbar rotationsbehaelter;
	
	private Rotationsflaeche re1 ;
	private Rotationsflaeche re2 ;
	

	private Taktgeber takt;

	public Behaelter_Rotationstest() {

		rotationsbehaelter = new BehaelterDrehbar(100, 100, 600, 600);

		re1 = new Rotationsflaeche(rotationsbehaelter,100, 100);

		re2 = new Rotationsflaeche(rotationsbehaelter,400, 400);
		re2.dwinkel=-20;
	
		
		takt = new Taktgeber();
		takt.setzeZeitZwischenAktionen(100);
		takt.endlos();
		takt.setzeLink(this);

	}
	
	int winkel = 0;

	@Override
	public void tuWas(int ID) {
		winkel -= 1;
		rotationsbehaelter.setzeWinkelGradmass(winkel);
	}
	
	
	public static void main(String[] args) {
		Zeichnung.setzeFenstergroesse(1000, 800);
		Zeichnung.setzeRasterEin();
		// Zeichnung.setzeRasterEin();
		// B_Auto_Rotationstest test =
		new Behaelter_Rotationstest();
		
		
	}


}
