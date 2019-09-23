//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Eine AutoSchlange speichert Autos
 * 
 * @author Witt
 * 
 */
public class PersonenSchlange {
	
	Person[]	elemente;
	int			maximalanzahl	= 10;
	int			aktuelleAnzahl	= 0;
	
	public PersonenSchlange(int anzahl) {
		this.maximalanzahl = anzahl;
		elemente = new Person[anzahl];
	}
	
	public PersonenSchlange(int anzahl, Umgebung u, String orientierung) {
		this(anzahl);
		for (int i = 0; i < anzahl; i++) {
			elemente[i] = new Person(u, orientierung);
		}
		aktuelleAnzahl = anzahl;
	}
	
	public void hinzufuegen(Person Person) {
		if (aktuelleAnzahl < maximalanzahl) {
			elemente[aktuelleAnzahl] = Person;
			aktuelleAnzahl++;
		}
	}
	
	public Person entferne(int nr) {
		if (nr < 0) return null; // unzulaessige Nummer
		if (nr < aktuelleAnzahl) {
			Person ret = elemente[nr];
			for (int i = nr+1; i < aktuelleAnzahl; i++) {
				elemente[i - 1] = elemente[i];
			}
			aktuelleAnzahl--;
			elemente[aktuelleAnzahl] = null;// letztes loeschen
			
			return ret;
		} else
			return null;
	}
	
	public Person gibPerson(int nr) {
		if (nr < 0) return null; // unzulaessige Nummer
		if (nr < aktuelleAnzahl) {
			return elemente[nr];
		} else
			return null;
	}	
	
	
}
