//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Dual_DatumUndUhrzeit implements ITuWas {
	
	Behaelter		datum;
	
	Dual_Ziffern	zf_h;
	Dual_Ziffern	zf_min;
	Dual_Ziffern	zf_sec;
	
	Ausgabe			a_h;
	Ausgabe			a_min;
	Ausgabe			a_sec;
	
	Dual_Ziffern	zf_tg;
	Dual_Ziffern	zf_monat;
	Dual_Ziffern	zf_jahr;
	
	Ausgabe			a_tg;
	Ausgabe			a_monat;
	Ausgabe			a_jahr;
	
	int				sec	= 0;
	int				min	= 0;
	int				h	= 0;
	
	// Kreis ring;
	
	Taktgeber		takt;
	
	public Dual_DatumUndUhrzeit() {
		
		datum = new Behaelter(0, 0, 600, 500);
		// Datum
		a_tg = new Ausgabe(datum, "Tag", 0, 0, 100, 50);
		a_tg.setzeSchriftgroesse(30);
		
		a_monat = new Ausgabe(datum, "Monat", 0, 60, 100, 50);
		a_monat.setzeSchriftgroesse(30);
		
		a_jahr = new Ausgabe(datum, "Jahr", 0, 120, 100, 50);
		a_jahr.setzeSchriftgroesse(30);
		
		zf_tg = new Dual_Ziffern(datum, 7);
		zf_tg.setzePosition(100, 5);
		
		zf_monat = new Dual_Ziffern(datum, 7);
		zf_monat.setzePosition(100, 65);
		
		zf_jahr = new Dual_Ziffern(datum, 7);
		zf_jahr.setzePosition(100, 125);
		// Uhrzeit
		a_h = new Ausgabe(datum, "Std.", 0, 250, 100, 50);
		a_h.setzeSchriftgroesse(30);
		
		a_min = new Ausgabe(datum, "min", 0, 310, 100, 50);
		a_min.setzeSchriftgroesse(30);
		
		a_sec = new Ausgabe(datum, "sec", 0, 370, 100, 50);
		a_sec.setzeSchriftgroesse(30);
		
		zf_h = new Dual_Ziffern(datum, 6);
		zf_h.setzePosition(100, 255);
		
		zf_min = new Dual_Ziffern(datum, 6);
		zf_min.setzePosition(100, 315);
		
		zf_sec = new Dual_Ziffern(datum, 6);
		zf_sec.setzePosition(100, 375);
		
		setzeTag(StaticTools.jetzt_Tag());
		setzeMonat(StaticTools.jetzt_Monat());
		setzeJahr(StaticTools.jetzt_Jahr());
		
		setzeSekunde(StaticTools.jetzt_Sekunde());
		setzeMinute(StaticTools.jetzt_Minute());
		setzeStunde(StaticTools.jetzt_Stunde());
		
		takt = new Taktgeber(this, 0);
		takt.endlos();
	}
	
	public void setzeTag(int neuerTag) {
		zf_tg.setzeZiffer(neuerTag);
	}
	
	public void setzeMonat(int neuerMonat) {
		zf_monat.setzeZiffer(neuerMonat);
	}
	
	public void setzeJahr(int neuesJahr) {
		neuesJahr = neuesJahr % 100;
		zf_jahr.setzeZiffer(neuesJahr);
	}
	
	public void setzeSekunde(int neueSec) {
		sec = neueSec;
		zf_sec.setzeZiffer(sec);
	}
	
	public void setzeMinute(int neueMin) {
		min = neueMin;
		zf_min.setzeZiffer(min);
	}
	
	public void setzeStunde(int neueH) {
		h = neueH;
		zf_h.setzeZiffer(h);
	}
	
	public void tuWas(int ID) {
		sec++;
		if (sec >= 60) {
			min++;
			sec = 0;
		}
		
		if (min >= 60) {
			min = 0;
			h++;
		}
		if (h >= 24) {
			h = 0;
			setzeTag(StaticTools.jetzt_Tag());
			setzeMonat(StaticTools.jetzt_Monat());
			setzeJahr(StaticTools.jetzt_Jahr());
		}
		setzeSekunde(sec);
		setzeMinute(min);
		setzeStunde(h);
	}
	
	public void setzePosition(int neuesX, int neuesY) {
		datum.setzePosition(neuesX, neuesY);
	}
	
	public static void main(String[] args) {
		Dual_DatumUndUhrzeit u = new Dual_DatumUndUhrzeit();
		u.setzePosition(20, 20);
		// Zeichnung.setzeRasterEin();
	}
	
}
