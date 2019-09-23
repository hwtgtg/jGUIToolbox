//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * Aufzug mit Steuerung
 * 
 * Der Schacht
 * 
 * IDs: 2. Stock: 2 -- 1. Stock: 1 -- Erdgeschoss: 0
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (23.8.2008)
 */

public class Aufzug_Schacht implements ITuWas {
	
	private Behaelter		schacht;
	private Rechteck		schachtAussen;
	private Rechteck		schachtInnen;
	
	private Ausgabe			a_geschoss_2;
	private Rechteck		geschoss_2;
	FeststellTaste	t2;
	
	private Ausgabe			a_geschoss_1;
	private Rechteck		geschoss_1;
	FeststellTaste	t1;
	
	private Ausgabe			a_geschoss_E;
	private Rechteck		geschoss_E;
	FeststellTaste	tE;
	
	public Aufzug_Schacht() {
		schacht = new Behaelter(10, 0, 400, 600);
		// schacht.setzeMitRaster(true);
		
		schachtAussen = new Rechteck(schacht, 0, 0, 200, 600);
		schachtAussen.setzeFarbe("hellgrau");
		
		a_geschoss_2 = new Ausgabe( schacht , "2", 250 , 50 , 150,150);
		a_geschoss_2.setzeSchriftgroesse(100);
		geschoss_2 = new Rechteck(schacht, 0, 200, 400, 20);
		geschoss_2.setzeFarbe("hellgrau");
		t2 = new FeststellTaste(schacht, "", "", 220, 130, 20, 20);
		t2.setzeFarbeNichtGedrueckt("gruen");
		t2.setzeFarbeGedrueckt("gelb");
		t2.setzeLink(this, 2);
		
		a_geschoss_1 = new Ausgabe( schacht , "1", 250 , 230 , 150,150);
		a_geschoss_1.setzeSchriftgroesse(100);
		geschoss_1 = new Rechteck(schacht, 0, 380, 400, 20);
		geschoss_1.setzeFarbe("hellgrau");
		t1 = new FeststellTaste(schacht, "", "", 220, 310, 20, 20);
		t1.setzeFarbeNichtGedrueckt("gruen");
		t1.setzeFarbeGedrueckt("gelb");
		t1.setzeLink(this, 1);
		
		a_geschoss_E = new Ausgabe( schacht , "E", 250 , 410 , 150,150);
		a_geschoss_E.setzeSchriftgroesse(100);
		geschoss_E = new Rechteck(schacht, 0, 560, 400, 20);
		geschoss_E.setzeFarbe("hellgrau");
		tE = new FeststellTaste(schacht, "", "", 220, 490, 20, 20);
		tE.setzeFarbeNichtGedrueckt("gruen");
		tE.setzeFarbeGedrueckt("gelb");
		tE.setzeLink(this, 0);
		
		schachtInnen = new Rechteck(schacht, 10, 40, 180, 540);
		schachtInnen.setzeFarbe("dunkelgrau");
	}
	
	public void setzePosition(int neuesX, int neuesY) {
		schacht.setzePosition(neuesX, neuesY);
	}
	
	private ITuWas	linkObj;
	@SuppressWarnings("unused")
	private int		id	= 0;	// ID der Komponente fuer Callback
								
	public void setzeLink(ITuWas linkObj, int ID) {
		this.linkObj = linkObj;
		id = ID;
	}
	
	public void tuWas(int ID) {
		if (linkObj != null) tuWas(ID);
	}
	
	public static void main(String[] args) {
		new Aufzug_Schacht();
		Zeichnung.setzeFenstergroesse(500, 700);
		// Zeichnung.setzeRasterEin();
	}
	
}
