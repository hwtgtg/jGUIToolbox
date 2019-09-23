//%$JGUIToolbox$%//ID fuer Toolboxdateien
public class Euro {
	// zwei Nachkommastellen!
	public long	wert;
	
	public Euro() {
		wert = 0;
	}
	
	public Euro(long euro, int cent) {
		this.wert = euro*100+cent;
	}
	
	public Euro(double dwert) {
		this.wert = Math.round(dwert*100);
	}

	public Euro(Euro eu) { // Copy-Konstruktor
		this.wert = eu.wert;
	}
	
	public void addiere(Euro eu) {
		this.wert += eu.wert;
	}
	
	public void subtrahiere(Euro eu) {
		this.wert -= eu.wert;
	}
	
	public boolean istNull() {
		return wert == 0;
	}
	
	public boolean istGroesserNull() {
		return wert > 0;
	}
	
	public boolean istKleinerNull() {
		return wert < 0;
	}
	
	public boolean istGroesser(Euro eu) {
		return wert > eu.wert;
	}
	
	public boolean istKleiner(Euro eu) {
		return wert < eu.wert;
	}

	public boolean istGleich(Euro eu) {
		return wert == eu.wert;
	}

	
	
	public double toDouble(){
		return wert/ 100.0 ;
	}
	@Override
	public String toString() {
		long aus = wert;
		String erg = "";
		if (aus < 0) {
			erg = "-";
			aus = -aus;
		}
		erg = erg + (wert / 100) + "," + (wert % 100)+"€";
		return erg;
	}
}
