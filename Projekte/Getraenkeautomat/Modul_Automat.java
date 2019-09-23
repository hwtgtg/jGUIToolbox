//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beschreiben Sie hier die Klasse B_Automat.
 * 
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Modul_Automat implements ITuWas {
	AusgabePanel	titel;
	Rechteck		gehauese;
	Segment8x		anzeige;
	
	Modul_Auswahl		auswahl;
	Modul_Eingabe		eingabe;
	Modul_Ausgabe		ausgabe;
	
	Taktgeber		takt;
	
	Euro			wert		;
	Euro			einzahlung	;
	String			flasche;
	
	/**
	 * Konstruktor fuer Objekte der Klasse B_Automat
	 */
	public Modul_Automat() {
		
		wert = new Euro();
		einzahlung = new Euro() ;
		Zeichnung.setzeFenstergroesse(650, 700);
		gehauese = new Rechteck(630, 650);
		gehauese.setzeFarbe("gelb");
		
		titel = new AusgabePanel("Getraenkeautomat", 100, 10, 400, 80);
		titel.setzeHintergrundfarbe("blau");
		titel.setzeSchriftgroesse(30);
		titel.setzeSchriftfarbe("rot");
		
		anzeige = new Segment8x(350, 100, 3, 120);
		anzeige.anzeige(0, 2);
		
		auswahl = new Modul_Auswahl(this, 10, 135);
		eingabe = new Modul_Eingabe(this, 280, 230);
		ausgabe = new Modul_Ausgabe(100, 420);
		
		takt = new Taktgeber();
		takt.setzeLink(this);
		
		auswahl.aktivieren();
	}
	
	public void gewaehlt(String flasche, Euro wert) {
		auswahl.deaktivieren();
		this.flasche = flasche;
		this.wert = wert;
		anzeige.anzeige(this.wert.wert ,2);
		eingabe.aktivieren();
	}
	
	public void tuWas(int ID) {
		if (ID == 0) {
			ausgabe.setzeAusgabe("Rueckgabe: " + einzahlung );
			einzahlung = new Euro();
			takt.setzeID(10);
			takt.einmal(2000);
		} else {
			eingabe.deaktivieren();
			eingabe.reset();
			wert = new Euro();
			anzeige.anzeige(wert.wert ,2);
			auswahl.aktivieren();
			ausgabe.setzeAusgabe("");
		}
	}
	
	public void eingabe(Euro muenze) {
		this.einzahlung.addiere( muenze);
		Euro t = new Euro(wert);
		t.subtrahiere(einzahlung);
		
		anzeige.anzeige(t.wert,2);
		if (wert.istKleiner( einzahlung)||wert.istGleich(einzahlung)) {
			eingabe.deaktivieren();
			ausgabe.setzeAusgabe(flasche);
			einzahlung.subtrahiere( wert);
			neustart();
		}
	}
	
	public void neustart() {
		// 2 s Zeit zum Ansehen der Ausgabe 
		if (einzahlung.istGroesserNull()) { // Ausgabe Rueckgabe 
			takt.setzeID(0);
			takt.einmal(2000);   
		} else {				            // Nur Reset
			takt.setzeID(10);
			takt.einmal(2000);
		}
	}
	
	public void reset() {
		// Unmittelbar zur Rueckgabe, wenn noetig
		if (einzahlung.istGroesserNull()) { // Ausgabe Rueckgabe 
			takt.setzeID(0);
			takt.einmal(10);
		} else {				// Nur Reset
			takt.setzeID(10);
			takt.einmal(10);
		}
	}
	
	public static void main(String[] args) {
		new Modul_Automat();
	}
	
}
