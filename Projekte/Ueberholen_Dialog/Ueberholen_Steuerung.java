//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Ueberholen zweier Fahrzeuge.
 * 
 * Uebernehmen setzt auf Zeit 0 zurueck
 * 
 * 
 * 
 * @author Witt
 *
 */
public class Ueberholen_Steuerung implements ITuWas {
	
	Ueberholen_Panel	panel;
	Ueberholen_Dialog	dialog;
	Taktgeber			takt;
	int					zeit	= 0;
	
	public Ueberholen_Steuerung() {
		
		panel = new Ueberholen_Panel(this);
		dialog = new Ueberholen_Dialog(this);
		dialog.setVisible();
		
		takt = new Taktgeber(this, 99);
		takt.setzeZeitZwischenAktionen(50);
		
		starteinstellungen();
	}
	
	double	startA				= 1;
	double	yA					= 0;
	double	vA					= 5;
	double	aA					= 0;
	
	double	startB				= 10;
	double	yB					= 0;
	double	vB					= 2;
	double	aB					= 0;
	
	double	beaoachtungslaenge	= 100;
	
	double	dt					= 0.05;
	
	/**
	 * rechne y-Wert in bildschirmkoordinaten
	 */
	public int getYWert(double y) {
		int wert = Ueberholen_Panel.abstand
				+ Ueberholen_Panel.laenge
				- (int) Math.round(y / beaoachtungslaenge
						* Ueberholen_Panel.laenge);
		return wert;
	}
	
	public void starteinstellungen() {
		yA = startA;
		yB = startB;
		
		dialog.startA.setzeBereich(0,beaoachtungslaenge,startA);
		dialog.vA.setzeBereich(-50,50, vA);
		dialog.aA.setzeBereich(-25,25, aA);
	
		dialog.startB.setzeBereich(0,beaoachtungslaenge,startB);
		dialog.vB.setzeBereich(-50,50, vB);
		dialog.aB.setzeBereich(-25,25, aB);
		
		positionenSetzen();
	}

	public void neueWerteUebernehmen() {
		startA = dialog.startA.leseDoubleWert();
		yA=startA;
		vA=dialog.vA.leseDoubleWert();
		aA= dialog.aA.leseDoubleWert();

		startB = dialog.startB.leseDoubleWert();
		yB=startB;
		vB=dialog.vB.leseDoubleWert();
		aB= dialog.aB.leseDoubleWert();
		
		zeit = 0 ;
		positionenSetzen();
	}
	
	public void positionsberechnung() {
		yA += vA*dt;
		yB += vB*dt;
		vA += aA*dt;
		vB += aB*dt;
	}


	void positionenSetzen(){
		panel.autoA.setzePosition(Ueberholen_Panel.bewegungX
				- Ueberholen_Panel.radiusA, getYWert(yA)-Ueberholen_Panel.radiusA);
		panel.autoB.setzePosition(Ueberholen_Panel.bewegungX
				- Ueberholen_Panel.radiusB, getYWert(yB)-Ueberholen_Panel.radiusB);
		// Spur eintragen
		panel.spur.hinzufuegen(0, zeit, getYWert(yA)
				- Ueberholen_Panel.abstand );
		panel.spur.hinzufuegen(1, zeit, getYWert(yB)
				- Ueberholen_Panel.abstand );
		panel.spur
				.setzeDimensionen(Ueberholen_Panel.bewegungX - zeit,
						Ueberholen_Panel.abstand, Ueberholen_Panel.bewegungX - Ueberholen_Panel.abstand,
						Ueberholen_Panel.laenge);
	
	}
	
	
	
	public void tuWas(int ID) {
		
		if (ID == 99) { // Timer
		
			zeit += 5;
			
			positionsberechnung ();
			
			// Bewegen
			positionenSetzen();
			
			if ((yA > beaoachtungslaenge) && (yB > beaoachtungslaenge))
				takt.stop();
			if ((Ueberholen_Panel.bewegungX - zeit) < Ueberholen_Panel.abstand)
				takt.stop();
			
		} else if (ID == 20) { // Werte uebernehmen
			neueWerteUebernehmen();
		} else if (ID == 21) { // Halt
			takt.setzeZeitZwischenAktionen(50);
			takt.stop();
		} else if (ID == 22) { // Start/weiter-Taste
			dialog.weiter.setzeAusgabetext("weiter");
			takt.setzeZeitZwischenAktionen(50);
			takt.endlos();
		} else if (ID == 23) { // Spuren loeschen
			takt.stop();
			dialog.weiter.setzeAusgabetext("Start");
			panel.spur.loescheSpuren();
			zeit = 0;
			positionenSetzen();
		} else if (ID == (100 + MausBehaelter.PRESS)) { // Mauspress auf A
			dialog.setVisible();
		} else if (ID == (110 + MausBehaelter.PRESS)) { // Mauspress auf B
			dialog.setVisible();
		}
	}
	
	public static void main(String[] args) {
		 new Ueberholen_Steuerung();
	}
	
}
