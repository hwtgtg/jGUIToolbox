//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.Color;
import java.awt.SystemColor;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

import javax.swing.JColorChooser;

/**
 * Statische Methoden fuer Framework<br/>
 * Methoden fuer Warten, Zufallszahl, Farben, Zeit, ...
 * 
 * <hr>
 * <b>Bekannten Farbnamen</b> sind in der Methode leseBekannteFarben angegeben.
 * <br/>
 * Diese Farbnamen k&ouml;nnen erg&auml;nzt werden. Die Farbe braun ist dafuer
 * ein Muster.
 * <hr>
 * 
 * @author Hans Witt
 * @version Version: 1.2 (19.7.2008) Uebersetzung NOSW in ENUM Richtung NOSW
 *          <br />
 *          Version: 1.3 (12.12.2008) gibZufall(int zahl) verbessert<br />
 *          Version: 1.4 (21.09.2010) Fehler bei Richtung behoben<br />
 * 
 *          Version: 2 (1.1.2011) Farbmanagement ge&auml;ndert.<br/>
 *          Es sind jetzt Farben mit beliebigem Namen moeglich. <br />
 *          Gibt es eine Farbe nicht, so wird der Farbdialog aufgerufen un die
 *          Farbe definiert.<br/>
 *          Link zu einer Farbtabelle:
 *          http://www.uni-magdeburg.de/counter/rgb.txt.shtml (6.1.2011)<br/>
 * 
 * 
 */
public class StaticTools {
	/**
	 * Statische Methoden fuer alle
	 */
	private static String normalFarbe = "hellgrau";

	private static boolean unbekannteFarbeNachfragen = true;

	/**
	 * Bei Parameter false wird der Farbdialog nicht automatisch gestartet
	 * 
	 * @param unbekannteFarbeNachfragen
	 */
	public static void setUnbekannteFarbeNachfragen(boolean unbekannteFarbeNachfragen) {
		StaticTools.unbekannteFarbeNachfragen = unbekannteFarbeNachfragen;
	}

	static Farben farben;

	/**
	 * Rueckgabe einer Standardfarbe
	 * 
	 * @return Farbstring
	 */
	public static String leseNormalfarbe() {
		return normalFarbe;
	}

	/**
	 * Rueckgabe einer Standardfarbe
	 * 
	 * @return Farbstring
	 */
	public static String leseNormalHintergrundFarbe() {
		return "normalHintergrundFarbe";
	}

	private static Color leseBekannteFarben(String farbname) {
		Color tColor = null;
		// Suche nach bekannte Farbnamen, uebernehmen

		if (farbname.equals("rot")) {
			tColor = Color.red;
		} else if (farbname.equals("schwarz")) {
			tColor = Color.black;
		} else if (farbname.equals("blau")) {
			tColor = Color.blue;
		} else if (farbname.equals("gelb")) {
			tColor = Color.yellow;
		} else if (farbname.equals("gruen")) {
			tColor = Color.green;
		} else if (farbname.equals("lila")) {
			tColor = Color.magenta;
		} else if (farbname.equals("weiss")) {
			tColor = Color.white;
		} else if (farbname.equals("grau")) {
			tColor = Color.gray;
		
		} else if (farbname.equals("red")) {
			tColor = Color.red;
		} else if (farbname.equals("black")) {
			tColor = Color.black;
		} else if (farbname.equals("blue")) {
			tColor = Color.blue;
		} else if (farbname.equals("yellow")) {
			tColor = Color.yellow;
		} else if (farbname.equals("green")) {
			tColor = Color.green;
		} else if (farbname.equals("white")) {
			tColor = Color.white;
		} else if (farbname.equals("gray")) {
			tColor = Color.gray;

		} else if (farbname.equals("pink")) {
			tColor = Color.pink;
		} else if (farbname.equals("magenta")) {
			tColor = Color.magenta;
		} else if (farbname.equals("orange")) {
			tColor = new Color(255, 102, 51);
		} else if (farbname.equals("cyan")) {
			tColor = Color.cyan;
		} else if (farbname.equals("hellgrau")) {
			// tColor = Color.lightGray;
			tColor = new Color(250, 250, 250);
		} else if (farbname.equals("dunkelgrau")) {
			tColor = Color.darkGray;
		} else if (farbname.equals("braun")) {
			tColor = new Color(101, 66, 34);
		} else if (farbname.equals("hellrot")) {
			tColor = new Color(255, 0, 0);
		} else if (farbname.equals("normalHintergrundFarbe")) {
			tColor = SystemColor.menu;
		}

		return tColor;
	}

	public static boolean istFarbeVorhanden(String farbname) {
		Color tColor = null;
		if (farben != null) {
			tColor = farben.getFarbe(farbname);
			if (tColor != null)
				return true;
		}
		tColor = leseBekannteFarben(farbname);
		return (tColor != null);
	}

	/**
	 * Gibt den Farbwert (Color) fuer Java zurueck.<br/>
	 * Bekannte Farbnamen sind:<br/>
	 * "rot", "gelb", "blau", "gruen", "lila" , "schwarz" , "weiss" ,
	 * "grau","pink","magenta","orange","cyan","hellgrau", "braun"
	 */
	public static Color getColor(String farbname) {
		Color tColor = null;
		// Wenn unter den selbstdefinierten Farben gefunden, wird diese
		// uebernommen
		if (farben != null) {
			tColor = farben.getFarbe(farbname);
			if (tColor != null)
				return tColor;
		}
		// Bekannte Farbnamen uebernehmen
		tColor = leseBekannteFarben(farbname);
		if (tColor != null) {
			setzeFarbe(farbname, tColor);
			tColor = farben.getFarbe(farbname);
		} else {
			tColor = leseBekannteFarben(normalFarbe);
			if (unbekannteFarbeNachfragen) {
				Color cErgebnis = JColorChooser.showDialog(Zeichnung.gibZeichenflaeche(),
						"Farbe " + farbname + " aussuchen", tColor);
				if (cErgebnis != null)
					tColor = cErgebnis;
				setzeFarbe(farbname, tColor);
			}
		}
		return tColor;
	}

	public static Color leseNormalZeichenfarbe() {
		return getColor(normalFarbe);
	}

	/**
	 * Ergaenzt/Aendert die Farben der Toolbox
	 * 
	 * @param farbname
	 * @param farbe
	 */
	public static void setzeFarbe(String farbname, Color farbe) {
		if (farben == null) {
			farben = new Farben();
		}
		farben.setFarbe(farbname, farbe);
	}

	/**
	 * Ergaenzt/Aendert die Farben der Toolbox
	 * 
	 * 
	 * @param farbname
	 * @param r
	 * @param g
	 * @param b
	 */
	public static void setzeFarbe(String farbname, int r, int g, int b) {
		if ((r > 255) || (r < 0))
			r = 0;
		if ((g > 255) || (g < 0))
			g = 0;
		if ((b > 255) || (b < 0))
			b = 0;
		setzeFarbe(farbname, new Color(r, g, b));
	}

	/**
	 * Ergaenzt/Aendert die Farben der Toolbox
	 * 
	 * @param farbname
	 * @param rgb
	 *            String der Form "100,200,6"
	 */
	public static void setzeFarbe(String farbname, String rgb) {
		if ((farbname == null) || (rgb == null))
			return;
		int r = 0;
		int g = 0;
		int b = 0;

		String sR;
		String sG;
		String sB;

		rgb = rgb.trim();
		if (!rgb.equals("")) {
			int i = rgb.indexOf(",");
			if (i > 0) {
				sR = (rgb.substring(0, i)).trim();
				rgb = (rgb.substring(i + 1)).trim();
				try {
					r = Integer.parseInt(sR);
				} catch (NumberFormatException e) {
					return;
				}
			}
			i = rgb.indexOf(",");
			if (i > 0) {
				sG = (rgb.substring(0, i)).trim();
				sB = (rgb.substring(i + 1)).trim();
				try {
					g = Integer.parseInt(sG);
				} catch (NumberFormatException e) {
					return;
				}
				try {
					b = Integer.parseInt(sB);
				} catch (NumberFormatException e) {
					return;
				}
			}
			setzeFarbe(farbname, r, g, b);
		}
	}

	/**
	 * 
	 * @return Liste von Kombinationen Farbname , RGB
	 */
	public static Vector<String[]> leseFarben() {
		if (farben == null) {
			farben = new Farben();
		}
		return farben.leseFarben();
	}

	/**
	 * Setzt Farben aus einem Vektorfeld von String[]<br />
	 * 1.String: Farbname, 2.String: farbe als r,b,g
	 * 
	 * @param vFarben
	 */
	public static void setzeFarben(Vector<String[]> vFarben) {
		if (farben == null) {
			farben = new Farben();
		}
		for (String[] str : vFarben) {
			setzeFarbe(str[0], str[1]);
		}
	}

	/**
	 * Warte fuer die angegebenen Millisekunden. Mit dieser Operation wird eine
	 * Verzoegerung definiert, die fuer animierte Zeichnungen benutzt werden
	 * kann.
	 * 
	 * @param millisekunden
	 *            die zu wartenden Millisekunden
	 */
	public static void warte(int millisekunden) {
		try {
			Thread.sleep(millisekunden);
		} catch (Exception e) {
			// Exception ignorieren
		}
	}

	public static Richtung getRichtung(String r) {
		if (r == "N") {
			return Richtung.N;
		} else if (r.equals("O")) {
			return Richtung.O;
		} else if (r.equals("S")) {
			return Richtung.S;
		} else if (r.equals("W")) {
			return Richtung.W;
		} else if (r.equals("NO")) {
			return Richtung.NO;
		} else if (r.equals("SO")) {
			return Richtung.SO;
		} else if (r.equals("SW")) {
			return Richtung.SW;
		} else if (r.equals("NW")) {
			return Richtung.NW;
		} else {
			return Richtung.N;
		}
	}

	/**
	 * liefert die aktuelle Tageszeit
	 * 
	 * @return Tageszeit in Sekunden
	 */
	public static long jetzt() {
		long zeit = 500;
		Calendar cal = Calendar.getInstance();
		zeit = cal.get(Calendar.SECOND) + (60 * cal.get(Calendar.MINUTE)) + (3600 * cal.get(Calendar.HOUR_OF_DAY));

		return zeit;
	}

	/**
	 * liefert die aktuelle Zeit
	 * 
	 * @return Stunde
	 */
	public static int jetzt_Stunde() {
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * liefert die aktuelle Zeit
	 * 
	 * @return Minute in der aktuellen Stunde
	 */
	public static int jetzt_Minute() {
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.MINUTE);
	}

	/**
	 * liefert die aktuelle Zeit
	 * 
	 * @return Sekunde in der aktuellen Minute
	 */
	public static int jetzt_Sekunde() {
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.SECOND);
	}

	/**
	 * liefert das aktuelle Datum
	 * 
	 * @return Tag im Monat
	 */
	public static int jetzt_Tag() {
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * liefert das aktuelle Datum
	 * 
	 * @return Tag im Monat
	 */
	public static int jetzt_WochenTag() {
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * liefert das aktuelle Datum
	 * 
	 * @return Monat im Jahr. Januar = 1
	 */
	public static int jetzt_Monat() {
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.MONTH) + 1; // Erster Monat ist 1 !
	}

	/**
	 * liefert das aktuelle Datum
	 * 
	 * @return Jahr
	 */
	public static int jetzt_Jahr() {
		Calendar cal = Calendar.getInstance();

		return cal.get(Calendar.YEAR);
	}

	private static Random zufall;

	/**
	 * Bestimmt eine zufaellige Zahl zwischen 0 und 1.
	 * 
	 * @return Zufallszahl zwischen 0 und 1.
	 */
	public static double gibZufall() {
		if (zufall == null) {
			zufall = new Random();
		}

		return zufall.nextDouble();
	}

	/**
	 * Bestimmt eine symmetrisch zu 0 verteilte zufaellige Zahl.
	 * 
	 * @param radius
	 *            gibt den Bereich fuer die erzeugten Zufallszahlen an.
	 * @return Zufallszahl zwischen -radius und radius.
	 */
	public static double gibSymZufall(double radius) {
		if (zufall == null) {
			zufall = new Random();
		}

		return ((2 * zufall.nextDouble()) - 1) * radius;
	}

	/**
	 * Bestimmt eine zufaellige ganze Zahl zwischen 0 und zahl.
	 * 
	 * @return Zufallszahl zwischen 0 und zahl (einschliesslich).
	 */
	public static int gibZufall(int zahl) {
		if (zufall == null) {
			zufall = new Random();
		}

		return zufall.nextInt(zahl + 1);
	}

	/**
	 * Ausrichtung
	 * 
	 * @author Witt
	 */
	public enum Richtung {
		N, O, S, W, NO, SO, SW, NW;
	}
}

class Farben {
	class Farbe {
		String fName = "";
		Color color;

		public Farbe(String fName, Color color) {
			this.fName = fName;
			this.color = color;
		}

		public boolean gleich(String fName) {
			if (this.fName.equalsIgnoreCase(fName)) {
				return true;
			} else {
				return false;
			}
		}

		public Color getFarbe() {
			return color;
		}

		public void setFarbe(Color color) {
			this.color = color;
		}

	}

	Vector<Farbe> farben = null;

	public Farben() {
		farben = new Vector<Farben.Farbe>();
	}

	public Color getFarbe(String fName) {
		if ((farben == null) || (fName == null) || (fName == "")) {
			return null;
		} else {
			for (Farbe farbe : farben) {
				if (farbe.gleich(fName)) {
					return farbe.getFarbe();
				}
			}
			return null;
		}
	}

	public void setFarbe(String fName, Color color) {
		if ((fName == null) || (fName == "")) {
			return;
		}
		// Farbe entfernen
		if (color == null) {
			Farbe tFarbe = null;
			for (Farbe farbe : farben) {
				if (farbe.gleich(fName)) {
					tFarbe = farbe;
				}
			}
			if (tFarbe != null) {
				farben.remove(tFarbe);
			}
			return;
		}

		// Vorhandene Farbe aendern
		for (Farbe farbe : farben) {
			if (farbe.gleich(fName)) {
				farbe.setFarbe(color);
				return;
			}
		}
		// Neue Farbe hinzufuegen
		farben.add(new Farbe(fName, color));
	}

	public Vector<String[]> leseFarben() {
		Vector<String[]> gesetzteFarben;
		gesetzteFarben = new Vector<String[]>();

		for (Farbe farbe : farben) {
			String[] txtFarbe = new String[2];
			txtFarbe[0] = farbe.fName;
			txtFarbe[1] = "" + farbe.getFarbe().getRed() + "," + farbe.getFarbe().getGreen() + ","
					+ farbe.getFarbe().getBlue();
			gesetzteFarben.add(txtFarbe);
		}
		return gesetzteFarben;
	}

}
