//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Memmory implements ITuWas {
	
	int				anzahl	= 30;
	
	MemmoryKarte[]	feld;
	Bilddatei[]		bilder;
	Bilddatei[]		bildzuordnung;
	
	Taktgeber		takt;
	
	int				feld1;
	int				feld2;
	boolean			Versuch1;		// 1.Versuch oder 2. Versuch
	boolean			warten;			// warten nach zweiten Versuch. Keine weitere Auswahl
	
	int				duplikate;		// Anzahl gefundener Duplikate
									
	public Memmory() {
		Zeichnung.setzeFenstergroesse(700, 600);
		feld = new MemmoryKarte[anzahl];
		int x = 5;
		int y = 5;
		
		bilder = new Bilddatei[anzahl / 2 + 1];
		bilder[7] = new Bilddatei("img\\granatapfel.gif");
		bilder[1] = new Bilddatei("img\\apfel.gif");
		bilder[2] = new Bilddatei("img\\banane.gif");
		bilder[3] = new Bilddatei("img\\birne.gif");
		bilder[4] = new Bilddatei("img\\orange.gif");
		bilder[5] = new Bilddatei("img\\erdbeere.gif");
		bilder[6] = new Bilddatei("img\\grapefruit.gif");
		bilder[0] = new Bilddatei("img\\turkey.gif");
		bilder[8] = new Bilddatei("img\\lemmon.gif");
		bilder[9] = new Bilddatei("img\\bee.gif");
		bilder[10] = new Bilddatei("img\\aquila.gif");
		bilder[11] = new Bilddatei("img\\manager_mimooh.gif");
		bilder[12] = new Bilddatei("img\\penguin.gif");
		bilder[13] = new Bilddatei("img\\ragnetto.gif");
		bilder[14] = new Bilddatei("img\\puffin.gif");
		bilder[15] = new Bilddatei("img\\planets.gif");
		
		bildzuordnung = new Bilddatei[anzahl];
		for (int i = 0; i < anzahl; i++) {
			bildzuordnung[i] = bilder[i / 2];
		}
		
		// wuerfeln
		Bilddatei h = null;
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				if (StaticTools.gibZufall() < 0.5) {
					h = bildzuordnung[i];
					bildzuordnung[i] = bildzuordnung[j];
					bildzuordnung[j] = h;
				}
			}
		}
		
		for (int i = 0; i < anzahl; i++) {
			feld[i] = new MemmoryKarte(x, y, 100, 100);
			feld[i].setzeID(i-1); // Das Press-Ereignis liefert Basis-ID 1. Daher um 1 vermindert
			feld[i].setzeLink(this);
			feld[i].setzeBilder(bildzuordnung[i], bilder[anzahl / 2]);
			x += 105;
			if ((i % 6) == 5) {
				x = 5;
				y += 105;
			}
		}
		Versuch1 = true;
		takt = new Taktgeber();
		takt.setzeLink(this);
		
		takt.setzeID(1000);
		duplikate = 0;
		warten = false ;
	}
	
	public void reset() {
		for (int i = 0; i < anzahl; i++) {
			bildzuordnung[i] = bilder[i / 2];
		}
		
		// wuerfeln
		Bilddatei h = null;
		for (int i = 0; i < anzahl; i++) {
			for (int j = i + 1; j < anzahl; j++) {
				if (StaticTools.gibZufall() < 0.5) {
					h = bildzuordnung[i];
					bildzuordnung[i] = bildzuordnung[j];
					bildzuordnung[j] = h;
				}
			}
		}
		
		for (int i = 0; i < anzahl; i++) {
			feld[i].setzeID( i-1); // Das Press-Ereignis liefert Basis-ID 1. Daher um 1 vermindert
			feld[i].setzeLink(this);
			feld[i].setzeBilder(bildzuordnung[i], bilder[anzahl / 2]);
		}
		Versuch1 = true;
		duplikate = 0;
		warten = false ;
	}
	
	public void tuWas(int ID) {
		if (ID == 1000) { // Timer
			warten = false ;
			reset();
		} else if (ID == 1001) { // Timer // Bilderduplette zuruecksetzen
			feld[feld1].setzeRueckseite();
			feld[feld1].setzeLink(this);
			feld[feld2].setzeRueckseite();
			feld[feld2].setzeLink(this);
			warten = false ;
		} else
		if (warten) { // Keine neue Eingabe solange Warten nach 2. Auswahl
			return ;
		} else
		if (Versuch1) {
			feld1 = ID;
			feld[feld1].setzeVorderseite();
			feld[feld1].setzeLink(null);
			Versuch1 = false;
		} else {
			warten = true ;
			feld2 = ID;
			feld[feld2].setzeVorderseite();
			
			if (bildzuordnung[feld1] == bildzuordnung[feld2]) {
				feld[feld2].setzeLink(null);
				duplikate++;
				if (duplikate == (anzahl / 2)) { // Neuanfang 
					takt.setzeID(1000);
					takt.einmal(4000);
				} else {
					warten = false;
					Versuch1 = true;
				}
				
			} else { // Bilderduplette zuruecksetzen
				takt.setzeID(1001);
				takt.einmal(500);
				Versuch1 = true;
			}
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Memmory();
		
	}
}
