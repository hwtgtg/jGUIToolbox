//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_Ball_MitNichtModalemDialog implements ITuWas {
	
	Taktgeber			takt;
	B_Ball				ball;
	Rechteck			feld;
	
	static int			breit	= 550;
	static int			hoch	= 400;
	
	B_Ball_DialogNichtModal	dialogModal;
	Taste				taste;
	
	public B_Ball_MitNichtModalemDialog() {
		feld = new Rechteck();
		feld.setzeDimensionen(0, 0, breit, hoch);
		feld.setzeFarbe("gruen");
		
		ball = new B_Ball();
		ball.setzeGeschwindigkeit(15);
		ball.setzeFarbe("rot");
		zufallsPosition(ball);
		zufallsRichtung(ball);
		ball.rBreit = breit ;
		ball.rHoch = hoch ;

		ball.setzeRadius(30);
		
		takt = new Taktgeber();
		takt.setzeAnfangsZeitverzoegerung(100);
		takt.setzeZeitZwischenAktionen(50);
		takt.setzeLink(this, 0);
		takt.endlos();
		
		// Dialogobjekt erzeugen
		dialogModal = new B_Ball_DialogNichtModal();
		
		// DIe Taste startet den Dialog
		taste = new Taste("Einstellungen", 100, 450, 200, 100);
		taste.setzeLink(this, 1);		
	}
	
	@SuppressWarnings("static-method")
	public void zufallsPosition(B_Ball ball) {
		ball.setzeMittelpunkt(StaticTools.gibZufall(breit - 2
				* ball.leseRadius()), StaticTools.gibZufall(hoch - 2
				* ball.leseRadius()));
	}
	
	@SuppressWarnings("static-method")
	public void zufallsRichtung(B_Ball ball) {
		ball.setzeRichtung((int) (StaticTools.gibZufall() * 360));
	}

	/**
	 * Setzen des neuen Radius z.B. aus dem Dialog
	 * @param neuerRadius
	 */
	public void setzeRichtung( int neueRichtung){
		ball.setzeRichtung( neueRichtung );
	}
	
	public void tuWas(int ID) {
		switch (ID) {
		case 0:
			ball.bewegung();
			break;
		case 1: // Die Taste startet den Dialog 
			// Vorbelegen der Dialogwerte
			dialogModal.setzeRichtung( 200);// ball.leseRichtung());
			// Start des Dialogs
			dialogModal.setzeSichtbar(this);
			// Eine Uebernehmen geaenderter Werde ist hier nicht moeglich! 
			// Das Programm geht sofort nach dem oeffnen des Dialogs weiter!
			break ;
		default:

		}
	}
	
	public static void main(String[] args) {
		 new B_Ball_MitNichtModalemDialog();
	}
	
}
