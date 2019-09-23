//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Digitaluhr_Dual implements ITuWas {
	
	Behaelter				uhr;
	
	Dezimalziffer_Dual	h10;
	Dezimalziffer_Dual	h01;
	Dezimalziffer_Dual	m10;
	Dezimalziffer_Dual	m01;
	Dezimalziffer_Dual	s10;
	Dezimalziffer_Dual	s01;
	
	Ausgabe					aH;
	Ausgabe					aMin;
	Ausgabe					aSec;
	
	int						sec	= 0;
	int						min	= 0;
	int						h	= 0;
	
	Taktgeber				takt;
	
	public Digitaluhr_Dual() {
		
		uhr = new Behaelter(0, 0, 600, 300);
		
		aH = new Ausgabe(uhr, "h", 30, 0, 100, 60);
		aH.setzeSchriftgroesse(30);
		
		aMin = new Ausgabe(uhr, "min", 170, 0, 100, 60);
		aMin.setzeSchriftgroesse(30);
		
		aSec = new Ausgabe(uhr, "sec", 320, 0, 100, 60);
		aSec.setzeSchriftgroesse(30);
		
		h10 = new Dezimalziffer_Dual(uhr);
		h10.setzePosition(0, 60);
		
		h01 = new Dezimalziffer_Dual(uhr);
		h01.setzePosition(50, 60);
		
		m10 = new Dezimalziffer_Dual(uhr);
		m10.setzePosition(150, 60);
		
		m01 = new Dezimalziffer_Dual(uhr);
		m01.setzePosition(200, 60);
		
		s10 = new Dezimalziffer_Dual(uhr);
		s10.setzePosition(300, 60);
		
		s01 = new Dezimalziffer_Dual(uhr);
		s01.setzePosition(350, 60);
		
		setzeSekunde(StaticTools.jetzt_Sekunde());
		setzeMinute(StaticTools.jetzt_Minute());
		setzeStunde(StaticTools.jetzt_Stunde());
		
		takt = new Taktgeber(this, 0);
		takt.endlos();
	}
	
	public void setzeSekunde(int neueSec) {
		sec = neueSec;
		s10.setzeZiffer(sec / 10);
		s01.setzeZiffer(sec % 10);
	}
	
	public void setzeMinute(int neueMin) {
		min = neueMin;
		m10.setzeZiffer(min / 10);
		m01.setzeZiffer(min % 10);
	}
	
	public void setzeStunde(int neueH) {
		h = neueH;
		h10.setzeZiffer(h / 10);
		h01.setzeZiffer(h % 10);
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
		}
		setzeSekunde(sec);
		setzeMinute(min);
		setzeStunde(h);
		
	}
	
	public void setzePosition(int neuesX, int neuesY) {
		uhr.setzePosition(neuesX, neuesY);
	}
	
	public static void main(String[] args) {
		Digitaluhr_Dual u = new Digitaluhr_Dual();
		u.setzePosition(0, 0);
		// Zeichnung.setzeRasterEin();
	}
	
}
