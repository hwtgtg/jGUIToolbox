//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Eine AutoSchlange speichert Autos
 * 
 * @author Witt
 * 
 */
public class AutoSchlange {
	
	Auto[]	elemente;
	int		maximalanzahl	= 10;
	int		aktuelleAnzahl	= 0;
	
	public AutoSchlange(int anzahl) {
		this.maximalanzahl = anzahl;
		elemente = new Auto[anzahl];
	}
	
	public AutoSchlange(int anzahl, Umgebung u, String orientierung) {
		this(anzahl);
		for (int i = 0; i < anzahl; i++) {
			elemente[i] = new Auto(u, orientierung,i);
		}
		aktuelleAnzahl = anzahl;
	}
	
	public void hinzufuegen(Auto auto) {
		if (aktuelleAnzahl < maximalanzahl) {
			elemente[aktuelleAnzahl] = auto;
			aktuelleAnzahl++;
		}
	}
	
	public Auto entferne(int nr) {
		if (nr < 0) return null; // unzulaessige Nummer
		if (nr < aktuelleAnzahl) {
			Auto ret = elemente[nr];
			for (int i = nr+1; i < aktuelleAnzahl; i++) {
				elemente[i - 1] = elemente[i];
			}
			aktuelleAnzahl--;
			elemente[aktuelleAnzahl] = null;// letztes loeschen
			return ret;
		} else
			return null;
	}
	
	public Auto gibAuto(int nr) {
		if (nr < 0) return null; // unzulaessige Nummer
		if (nr < aktuelleAnzahl) {
			return elemente[nr];
		} else
			return null;
	}	
}
