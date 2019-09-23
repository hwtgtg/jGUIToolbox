//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Ampelsteuerung implements ITuWas {
	Umgebung	kreuzung;
	
	Taktgeber	takt;
	
	public Ampelsteuerung(Umgebung kreuzung) {
		this.kreuzung = kreuzung;
		kreuzung.faNord.taste.setzeID(10);
		kreuzung.faSued.taste.setzeID(11);
		
		kreuzung.faNord.taste.setzeLink(this);
		kreuzung.faSued.taste.setzeLink(this);
		
		takt = new Taktgeber();
		takt.einmal(2000);
		takt.setzeID(1);
		takt.setzeLink(this);
	}
	
	enum Zustaende {
		AausFaus, AausFrot, AgruenFrot, AgelbFrot, ArotFrotA, ArotFgruen, ArotFrotE, ArotgelbFrot
	};
	
	Zustaende	status	= Zustaende.AausFrot;
	
	public void aendereZustand() {
		switch (status) {
		case AausFaus:
			kreuzung.aaOst.ampelAus();
			kreuzung.aaWest.ampelAus();
			kreuzung.faNord.ampelAus();
			kreuzung.faSued.ampelAus();
			// Weiter durch externes Ereignis
			status = Zustaende.AausFrot;
			break;
		case AausFrot:
			kreuzung.aaOst.ampelAus();
			kreuzung.aaWest.ampelAus();
			kreuzung.faNord.rot();
			kreuzung.faSued.rot();
			if (kreuzung.faNord.taste.istGedrueckt()) {
				status = Zustaende.AgruenFrot;
				takt.einmal(2000);
			} else {
				takt.einmal(100);
			}
			
			break;
		case AgruenFrot:
			kreuzung.aaOst.gruen();
			kreuzung.aaWest.gruen();
			kreuzung.faNord.rot();
			kreuzung.faSued.rot();
			takt.einmal(2500);
			status = Zustaende.AgelbFrot;
			break;
		case AgelbFrot:
			kreuzung.aaOst.gelb();
			kreuzung.aaWest.gelb();
			kreuzung.faNord.rot();
			kreuzung.faSued.rot();
			takt.einmal(1000);
			status = Zustaende.ArotFrotA;
			break;
		case ArotFrotA:
			kreuzung.aaOst.rot();
			kreuzung.aaWest.rot();
			kreuzung.faNord.rot();
			kreuzung.faSued.rot();
			takt.einmal(2000);
			status = Zustaende.ArotFgruen;
			break;
		case ArotFgruen:
			kreuzung.aaOst.rot();
			kreuzung.aaWest.rot();
			kreuzung.faNord.gruen();
			kreuzung.faSued.gruen();
			takt.einmal(3000);
			status = Zustaende.ArotFrotE;
			break;
		case ArotFrotE:
			kreuzung.aaOst.rot();
			kreuzung.aaWest.rot();
			kreuzung.faNord.rot();
			kreuzung.faSued.rot();
			takt.einmal(10000);
			kreuzung.faNord.taste.setzeNichtGewaehlt();
			kreuzung.faSued.taste.setzeNichtGewaehlt();
			status = Zustaende.ArotgelbFrot;
			break;
		case ArotgelbFrot:
			kreuzung.aaOst.rotGelb();
			kreuzung.aaWest.rotGelb();
			kreuzung.faNord.rot();
			kreuzung.faSued.rot();
			takt.einmal(1000);
			if (kreuzung.faNord.taste.istGedrueckt()) {
				status = Zustaende.AgruenFrot;
			} else {
				status = Zustaende.AausFrot;
			}
			break;
		default:

		}
	}
	
	public void tuWas(int ID) {
		switch (ID) {
		case 1: // faNord gedrueckt
			aendereZustand();
			break;
		case 10: // faNord gedrueckt
			kreuzung.faSued.taste.setzeGewaehlt();
			break;
		case 11: // faSued gedrueckt
			kreuzung.faNord.taste.setzeGewaehlt();
			break;
		default:
			break;
		}
	}
}
