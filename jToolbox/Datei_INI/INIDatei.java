
//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 * Die Klasse <b>INIDatei</b> dient dem Lesen und Schreiben von INI-Dateien<br/>
 * Die Klasse erstellt eine Struktur, in die Inidatensaetze eingelesen bzw. zum
 * Speichern bereitgestellt werden</br> Die Daten koennen aus der Klasse
 * strukturiert gelesen und geschrieben werden.<br/>
 * 
 * setzeXX(String Sektion, String Attribut, XX wert) erzeugt/aendert einen
 * Eintrag <br/>
 * leseXX(String Sektion, String Attribut) liest einen Eintrag als XX<br/>
 * Die Daten liegen im Hauptspeicher. Erst durch <b>schreibeINIDatei</b> werden
 * die Daten auf den Datentraeger geschrieben. <br/>
 * <br/>
 * 
 * Der Konstruktor der Klasse erstellt eine Liste von INI-Datensaetzen mit den
 * Attributen Sektion ([...], Attribut , wert<br/>
 * <br/>
 * Eintraege der Inidatei sind<br/>
 * [ABC]<br/>
 * &nbsp;&nbsp;&nbsp; wert=5<br/>
 * &nbsp;&nbsp;&nbsp;breite=4<br/>
 * [EINS]<br/>
 * &nbsp;&nbsp;&nbsp;f=5<br/>
 * [ENDE]<br/>
 * &nbsp;&nbsp;&nbsp;wichtig=578<br/>
 * &nbsp;&nbsp;&nbsp;zahl=2010.02<br/>
 * &nbsp;&nbsp;&nbsp;tag=17<br/>
 * <br/>
 * <br/>
 * lesenINIDatei(String dateiname) importiert die Datei,wenn sie existiert.<br/>
 * <br/>
 * schreibeINIDatei(String dateiname, boolean ersetzen) schreibt die Datei<br/>
 * ersetzen=true ersetzt eine bestehende Datei<br/>
 * <br/>
 * 
 * schreibeINIDatei() ersetzt die letzte (gelesene oder geschriebene) Datei<br/>
 * <br/>
 * setzeString(String Sektion, String Attribut, String wert) erzeugt/aendert
 * einen INIDatei-Eintrag <br/>
 * 
 * genauso setzeInteger und setzeDouble<br/>
 * <br/>
 * existiert(String Sektion, String Attribut) ueberprueft die Existenz eines
 * Eintrags<br/>
 * <br/>
 * 
 * leseString(String Sektion, String Attribut) liest einen Eintrag als String<br/>
 * <br/>
 * leseInteger(String Sektion, String Attribut, double error)<br/>
 * und leseDouble(String Sektion, String Attribut, double error)<br/>
 * lesen eine INTEGER bzw DOUBLE-Zahl.<br/>
 * errror erhaelt man, falls dabei ein Fehler auftritt<br/>
 * <hr>
 * autor: Witt
 * 
 */
@SuppressWarnings("serial")
public class INIDatei extends LinkedList<INIDatensatz> {

	String dateiname = null;
	public boolean geoeffnet = false ;
	
	final static int INI_ENDE = -1;
	// Es wird keine Fehlermeldung bei Schreibfehler ausgegeben
	public static final boolean KeinFEHLER = true;

	/**
	 * Konstruktor
	 * 
	 * erzeugt leere LinkedList
	 */
	public INIDatei() {
	}

	/**
	 * Konstruktor
	 * 
	 * erzeugt LinkedList und liest Datei "dateiname" in die LinkedList
	 */
	public INIDatei(String dateiname) {
		lesenINIDatei(dateiname);
	}

	/**
	 * zum internen Gebrauch. muss leider public sein
	 */
	@Override
	public boolean add(INIDatensatz ini) {
		setzeString(ini.Sektion, ini.Attribut, ini.wert);
		return true;
	}

	/**
	 * Eingabe/Veraenderung eines Werts als String
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param wert
	 */
	public void setzeString(String Sektion, String Attribut, String wert) {
		if (!existiert(Sektion, Attribut)) {
			INIDatensatz tmp = new INIDatensatz();
			// Eintraege nur mit Attribut
			if (Attribut.equals(""))
				return;
			tmp.Sektion = Sektion;
			tmp.Attribut = Attribut;
			tmp.wert = wert;
			super.add(tmp);
			// Liste sortieren
			Collections.sort(this);
		} else { // Aendern
			for (INIDatensatz ds : this) {
				if (ds.Sektion.equals(Sektion) && ds.Attribut.equals(Attribut)) {
					ds.wert = wert;
					return;
				}
			}
		}
	}

	/**
	 * Eingabe/Veraenderung eines Werts als Integer
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param wert
	 */
	public void setzeInteger(String Sektion, String Attribut, int wert) {
		setzeString(Sektion, Attribut, "" + wert);
	}

	/**
	 * Eingabe/Veraenderung eines Werts als long
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param wert
	 */
	public void setzeLong(String Sektion, String Attribut, long wert) {
		setzeString(Sektion, Attribut, "" + wert);
	}

	/**
	 * Eingabe/Veraenderung eines Werts als boolean
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param wert
	 */
	public void setzeBoolean(String Sektion, String Attribut, boolean wert) {
		setzeString(Sektion, Attribut, "" + wert);
	}

	/**
	 * Eingabe/Veraenderung eines Werts als float
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param wert
	 */
	public void setzeFloat(String Sektion, String Attribut, float wert) {
		setzeString(Sektion, Attribut, "" + wert);
	}

	/**
	 * Eingabe/Veraenderung eines Werts als double
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param wert
	 */
	public void setzeDouble(String Sektion, String Attribut, double wert) {
		setzeString(Sektion, Attribut, "" + wert);
	}

	/**
	 * Testet, ob Attribut in der Sektion existiert
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @return existiert
	 */
	public boolean existiert(String Sektion, String Attribut) {
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion) && ds.Attribut.equals(Attribut)) {
				return true;
			}
			if (ds.Sektion.compareTo(Sektion) > 0)
				return false;
		}
		return false;
	}

	/**
	 * gibt der Wert des Attributs als String zurueck
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @return als String
	 */
	public String leseString(String Sektion, String Attribut) {
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion) && ds.Attribut.equals(Attribut)) {
				return ds.wert;
			}
			if (ds.Sektion.compareTo(Sektion) > 0)
				return "";
		}
		return "";
	}

	/**
	 * gibt den Wert des Attributs als ganze Zahl zurueck
	 * 
	 * <br/>
	 * Falls das nicht geht, wird der error-Wert zurueckgegeben
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param error
	 * @return als int
	 */
	public int leseInteger(String Sektion, String Attribut, int error) {
		if (!existiert(Sektion, Attribut))
			return error;
		String erg = leseString(Sektion, Attribut);
		int ret = error;
		try {
			ret = Integer.parseInt(erg);
		} catch (Exception e) {
		}

		return ret;
	}

	/**
	 * gibt den Wert des Attributs als ganze Zahl (long) zurueck
	 * 
	 * <br/>
	 * Falls das nicht geht, wird der error-Wert zurueckgegeben
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param error
	 * @return als int
	 */
	public long leseLong(String Sektion, String Attribut, long error) {
		if (!existiert(Sektion, Attribut))
			return error;
		String erg = leseString(Sektion, Attribut);
		long ret = error;
		try {
			ret = Long.parseLong(erg);
		} catch (Exception e) {
		}

		return ret;
	}
	
	
	/**
	 * gibt den Wert des Attributs als ganze Zahl zurueck
	 * 
	 * <br/>
	 * Falls das nicht geht, wird der error-Wert zurueckgegeben
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param error
	 * @return als int
	 */
	public boolean leseBoolean(String Sektion, String Attribut, boolean error) {
		if (!existiert(Sektion, Attribut))
			return error;
		String erg = leseString(Sektion, Attribut);
		boolean ret = error;
		try {
			ret = Boolean.parseBoolean(erg);
		} catch (Exception e) {
		}

		return ret;
	}

	
	/**
	 * gibt den Wert des Attributs als float-Zahl zurueck
	 * 
	 * <br/>
	 * Falls das nicht geht, wird der error-Wert zurueckgegeben
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param error
	 * @return als Double
	 */
	public float leseFloat(String Sektion, String Attribut, float error) {
		if (!existiert(Sektion, Attribut))
			return error;
		String erg = leseString(Sektion, Attribut);
		erg = erg.replaceAll(",", ".");
		float ret = error;
		try {
			ret = Float.parseFloat(erg);
		} catch (Exception e) {
		}

		return ret;
	}
	
	
	/**
	 * gibt den Wert des Attributs als double-Zahl zurueck
	 * 
	 * <br/>
	 * Falls das nicht geht, wird der error-Wert zurueckgegeben
	 * 
	 * @param Sektion
	 * @param Attribut
	 * @param error
	 * @return als Double
	 */
	public double leseDouble(String Sektion, String Attribut, double error) {
		if (!existiert(Sektion, Attribut))
			return error;
		String erg = leseString(Sektion, Attribut);
		erg = erg.replaceAll(",", ".");
		double ret = error;
		try {
			ret = Double.parseDouble(erg);
		} catch (Exception e) {
		}

		return ret;
	}

	int indexAll = 0;

	/**
	 * <strong>Auslesen der Liste, erster Eintrag</strong>
	 * 
	 * Rueckgabe in der Form
	 * 
	 * <strong>[Sektion]attribut=wert</strong>
	 * 
	 * <br/>
	 * Leere Liste -> Rueckgabe: leerer String
	 */
	public String leseFirst() {
		Collections.sort(this);
		indexAll = 0;
		if (this.isEmpty()) {
			return "";
		} else {
			return "[" + this.getFirst().Sektion + "]"
					+ this.getFirst().Attribut + "=" + this.getFirst().wert;
		}
	}

	/**
	 * <strong>Auslesen der Liste, Folge-Eintrag</strong>
	 * 
	 * Rueckgabe in der Form
	 * 
	 * <strong>[Sektion]attribut=wert</strong>
	 * 
	 * <br/>
	 * Ende der Liste -> Rueckgabe: leerer String
	 */
	public String leseNext() {
		if (indexAll == INI_ENDE)
			return "";
		indexAll++;
		int i = 0;
		for (INIDatensatz ds : this) {
			if (i == indexAll) {
				return "[" + ds.Sektion + "]" + ds.Attribut + "=" + ds.wert;
			} else {
				i++;
			}
		}
		indexAll = INI_ENDE; // Ende
		return "";
	}

	int indexSektion = 0;

	/**
	 * <strong>Auslesen der Eintraege einer Sektion, erster Eintrag</strong>
	 * 
	 * Rueckgabe in der Form
	 * 
	 * <strong>attribut=wert</strong>
	 * 
	 * <br/>
	 * Kein Eintrag -> Rueckgabe: leerer String
	 */
	public String leseFirstAusSektion(String Sektion) {
		indexSektion = 0;
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion)) {
				return ds.Attribut + "=" + ds.wert;
			}
		}
		return "";
	}

	/**
	 * <strong>Auslesen der Eintraege einer Sektion, Folge-Eintrag</strong>
	 * 
	 * Rueckgabe in der Form
	 * 
	 * <strong>attribut=wert</strong>
	 * 
	 * <br/>
	 * Ende -> Rueckgabe: leerer String
	 */
	public String leseNextAusSektion(String Sektion) {
		if (indexSektion == INI_ENDE)
			return "";
		indexSektion++;
		int i = 0;
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion)) {
				if (i == indexSektion) {
					return ds.Attribut + "=" + ds.wert;
				} else {
					i++;
				}
			}
		}
		indexSektion = INI_ENDE;
		return "";
	}

	/**
	 * <strong>Auslesen der Eintraege einer Sektion, erster Eintrag</strong><br />
	 * 
	 * Rueckgabe als Stringfeld. <br />
	 * 1.String: Arrtibut, 2.String: Wert
	 * 
	 * <br/>
	 * Kein Eintrag -> Rueckgabe: null
	 */
	public String[] leseFirstAusSektionFeld(String Sektion) {
		indexSektion = 0;
		String[] sErg = null;
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion)) {
				sErg = new String[2];
				sErg[0] = ds.Attribut;
				sErg[1] = ds.wert;
				return sErg;
			}
		}
		return sErg;
	}

	/**
	 * <strong>Auslesen der Eintraege einer Sektion, Folge-Eintrag</strong><br />
	 * 
	 * Rueckgabe als Stringfeld. <br />
	 * 1.String: Arrtibut, 2.String: Wert
	 * 
	 * <br/>
	 * Ende -> Rueckgabe: null
	 */
	public String[] leseNextAusSektionFeld(String Sektion) {
		String[] sErg = null;
		if (indexSektion == INI_ENDE) {
			return sErg;
		}
		indexSektion++;
		int i = 0;
		for (INIDatensatz ds : this) {
			if (ds.Sektion.equals(Sektion)) {
				if (i == indexSektion) {
					sErg = new String[2];
					sErg[0] = ds.Attribut;
					sErg[1] = ds.wert;
					return sErg;
				} else {
					i++;
				}
			}
		}
		indexSektion = INI_ENDE;
		return sErg;
	}

	/**
	 * <strong>Auslesen der Eintraege einer Sektion</strong><br />
	 * 
	 * Ergebnis: Vektor von String[]<br />
	 * 1.String: Arrtibut, 2.String: Wert
	 * 
	 * @param Sektion
	 * @return Vektor aus {"Attribut","Wert"}
	 */
	public Vector<String[]> leseSketion(String Sektion) {
		Vector<String[]> gesetzteFarben;
		gesetzteFarben = new Vector<String[]>();
		String[] sErg = leseFirstAusSektionFeld(Sektion);
		while (sErg != null) {
			gesetzteFarben.add(sErg);
			sErg = leseNextAusSektionFeld(Sektion);
		}
		return gesetzteFarben;
	}

	/**
	 * Eingabe/Veraenderung einer Sektion
	 * 
	 * @param Sektion
	 * @param werte
	 * <br />
	 *            Vektor von String[]<br />
	 *            1.String: Arrtibut, 2.String: Wert
	 */
	public void setzeSektion(String Sektion, Vector<String[]> werte) {
		for (String[] str : werte) {
			setzeString(Sektion, str[0], str[1]);
		}
	}

	/**
	 * Loeschen aller Eintraege
	 */
	@Override
	public void clear() {
		super.clear();
	}

	/**
	 * <strong>Schreiben der INI-Datei</strong> Dateiname bekannt, z.B. durch
	 * vorheriges lesen
	 */
	public void schreibeINIDatei() {
		if (dateiname != "")
			schreibeINIDatei(dateiname, true, !KeinFEHLER);
	}

	public boolean schreibeINIDatei(boolean keinfehler) {
		if (dateiname != "") {
			return schreibeINIDatei(dateiname, true, !KeinFEHLER);
		} else {
			return true ;
		}
	}
	
	
	/**
	 * <strong>Schreiben der INI-Datei Dateiname wird fuer spaetere Verwendung
	 * gespeichert. </strong>
	 * 
	 * Eine bestehende Datei wird bei ersetzen = true ueberschrieben Bei
	 * ersetzen = false wird mit fehlermeldung abgebrochen
	 */
	public boolean schreibeINIDatei(String dateiname, boolean ersetzen , boolean keinFEHLER) {
		this.dateiname = dateiname;
		File datei = new File(dateiname);
		if (datei.exists()) {
			if (ersetzen) {
				datei.delete();
				try {
					datei.createNewFile();
				} catch (IOException e) {
					if(!keinFEHLER){
					JOptionPane.showConfirmDialog(null,
							"Probleme beim Speichern !", "Speichern",
							JOptionPane.OK_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					}
					return false;
				}
			} else {
				return false;
			}
		}

		// Liste sortieren
		Collections.sort(this);

		try {
			FileWriter fw = new FileWriter(dateiname);
			BufferedWriter bfw = new BufferedWriter(fw);
			String Sektion = "";
			for (INIDatensatz ds : this) {
				if (!Sektion.equals(ds.Sektion)) {
					bfw.write("[" + ds.Sektion + "]");
					bfw.newLine();
					Sektion = ds.Sektion;
				}
				bfw.write("    " + ds.Attribut + "=" + ds.wert);
				bfw.newLine();
			}
			bfw.close();
			fw.close();
		} catch (IOException e) {
			if(!keinFEHLER){
				JOptionPane.showConfirmDialog(null, e.toString(),
						"Probleme beim Speichern", JOptionPane.OK_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
			}
			return false;
		}
		return true;
	}

	/**
	 * <strong>Lesen der Datei in die INI-Liste</strong>
	 * 
	 * @param dateiname
	 */
	public void lesenINIDatei(String dateiname) {
		this.dateiname = dateiname;
		File datei = new File(dateiname);
		if (!datei.exists()) {
			geoeffnet = false ;
			return;
		} else {
			FileReader reader = null;
			try {
				String pfad = datei.getPath();
				reader = new FileReader(pfad);
				BufferedReader br = new BufferedReader(reader);
				geoeffnet = true ;

				String zeile;

				while ((zeile = br.readLine()) != null) {
					INIDatensatz erg = INIDatensatz.zeilenAnalyse(zeile);
					if (erg != null) {
						this.add(erg);
					}
				}
				if(br!=null){ 
					br.close();
				}
			} catch (IOException e) {
				JOptionPane.showConfirmDialog(null, e.toString(),
						"Probleme beim Lesen", JOptionPane.OK_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

			} finally {
				try {
					if (reader != null)
						reader.close();
				} catch (Exception e) {
				}
			}
			// Liste sortieren
			Collections.sort(this);
		}
	}


}

class INIDatensatz implements Comparable<INIDatensatz> {

	public static String letzeSektion = "";

	String Sektion;
	String Attribut;
	String wert;

	/**
	 * @param daten
	 */
	protected INIDatensatz() {
	}

	public static INIDatensatz zeilenAnalyse(String daten) {
		daten = daten.trim();
		if (!daten.equals("") && daten.startsWith("[") && daten.endsWith("]")) {
			// Neue Kathegorie
			letzeSektion = (daten.substring(1, daten.length() - 1)).trim();
			return null;
		} else {
			int i = daten.indexOf("=");
			INIDatensatz erg = new INIDatensatz();
			erg.Sektion = letzeSektion;
			if (i > 0) {
				erg.Attribut = (daten.substring(0, i)).trim();
				erg.wert = (daten.substring(i + 1)).trim();
				return erg;
			} else {
				return null;
			}
		}
	}

	@Override
	public String toString() {
		return Sektion;
	}

	// Fuer das Sortieren
	// Vergleich nur fuer Sektion
	@Override
	public int compareTo(INIDatensatz arg) {
		return -1 * arg.Sektion.compareTo(Sektion);
	}
}
