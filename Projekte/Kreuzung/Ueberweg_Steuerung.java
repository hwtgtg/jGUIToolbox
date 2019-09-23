//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Graphische Darstellung eines Fussgaengerueberwegs
 * 
 * Steuerungsklasse
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (25.8.2008)
 */


public class Ueberweg_Steuerung implements ITuWas {

	Fussgaenger_Ueberweg strasse ;
	
	Taktgeber takt;
	

	/**
	 * Konstruktor fuer Objekte der Klasse Ueberweg
	 */
	public Ueberweg_Steuerung() {
		strasse = new Fussgaenger_Ueberweg();
		takt = new Taktgeber(this,0);	
		takt.einmal(1000);
	}

	public void tuWas(int ID) {
		aendereZustand();
	}
	
	enum Zustaende {
		AausFaus, AausFrot, AgruenFrot, AgelbFrot, ArotFrotA, ArotFgruen, ArotFrotE, ArotgelbFrot
	};

	Zustaende status = Zustaende.AausFaus;

	public void aendereZustand( ) {
		switch (status) {
		case AausFaus:
			strasse.aaO.ampelAus();
			strasse.aaW.ampelAus();
			strasse.faN.ampelAus();
			strasse.faS.ampelAus();
			status = Zustaende.AausFrot;
			takt.einmal(1000);
			break;
		case AausFrot:
			strasse.aaO.ampelAus();
			strasse.aaW.ampelAus();
			strasse.faN.rot();
			strasse.faS.rot();
			if (strasse.tasteN.istGedrueckt()) {
				status = Zustaende.AgruenFrot;
			}
			takt.einmal(1000);
			break;
		case AgruenFrot:
			strasse.aaO.gruen();
			strasse.aaW.gruen();
			strasse.faN.rot();
			strasse.faS.rot();
			status = Zustaende.AgelbFrot;
			takt.einmal(1000);
			break;
		case AgelbFrot:
			strasse.aaO.gelb();
			strasse.aaW.gelb();
			strasse.faN.rot();
			strasse.faS.rot();
			status = Zustaende.ArotFrotA;
			takt.einmal(1000);
			break;
		case ArotFrotA:
			strasse.aaO.rot();
			strasse.aaW.rot();
			strasse.faN.rot();
			strasse.faS.rot();
			status = Zustaende.ArotFgruen;
			takt.einmal(1000);
			break;
		case ArotFgruen:
			strasse.aaO.rot();
			strasse.aaW.rot();
			strasse.faN.gruen();
			strasse.faS.gruen();
			status = Zustaende.ArotFrotE;
			takt.einmal(3000);
			break;
		case ArotFrotE:
			strasse.aaO.rot();
			strasse.aaW.rot();
			strasse.faN.rot();
			strasse.faS.rot();
			strasse.tasteN.setzeNichtGewaehlt();
			strasse.tasteS.setzeNichtGewaehlt();
			status = Zustaende.ArotgelbFrot;
			takt.einmal(1000);
			break;
		case ArotgelbFrot:
			strasse.aaO.rotGelb();
			strasse.aaW.rotGelb();
			strasse.faN.rot();
			strasse.faS.rot();

			if (strasse.tasteN.istGedrueckt()) {
				status = Zustaende.AgruenFrot;
			} else {
				status = Zustaende.AausFrot;
			}
			takt.einmal(1000);
			break;
		default:

		}
	}


	public static void main(String[] args) {
		 new Ueberweg_Steuerung();
	}

}
