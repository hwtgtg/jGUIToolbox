//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class IOTools {

	public static void warten(int millisekunden) {
		try {
			Thread.sleep(millisekunden);
		} catch (Exception e) {
			// Exception ignorieren
		}
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

	private static void out(String text) {
		System.out.print(text);
	}

	private static void newline() {
		System.out.println();
	}

	public static void writeln() {
		newline();
	}

	public static void write(String text) {
		out(text);
	}

	public static void writeln(String text) {
		out(text);
		writeln();
	}

	public static void write(int nr) {
		out("" + nr);
	}

	public static void writeln(long nr) {
		out("" + nr);
		newline();
	}

	public static void write(long nr) {
		out("" + nr);
	}

	public static void write(double zahl) {
		out("" + zahl);
	}

	public static void writeln(double zahl) {
		out("" + zahl);
		newline();
	}

	// ******************************* Eingabe *********************************
	private static BufferedReader tastaturEingabe = new BufferedReader(
			new InputStreamReader(System.in));

	private static TextBuffer buffer = new TextBuffer(new ITextBuffer() {
		// Dem Buffer wird eine Eingabefunktion uebergeben
		@Override
		public String nextLine() {

			IOTools.write(">>");
			try {
				return tastaturEingabe.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	});

	/**
	 * Lesen der Eingabe. Buffer geloescht
	 * 
	 * @return
	 */
	public static String readTxt() {
		return buffer.readTxt();
	}

	/**
	 * Lesen der Eingabe ohne Loeschen des Buffers
	 * 
	 * @return
	 */
	public static String inspectTxt() {
		return buffer.inspectTxt();
	}

	/**
	 * @return
	 */
	public static boolean testeTxt() {
		return buffer.testeTxt();
	}

	/**
	 * Test, ob Eingabezeile aus Sting vergleich besteht. Buffer bleibt
	 * erhalten!
	 * 
	 * @param vergleich
	 * @return
	 */
	public static boolean vergleicheTxt(String vergleich) {
		return buffer.vergleicheTxt(vergleich);
	}

	/**
	 * Test, ob Eingabezeile aus Sting vergleich besteht. Buffer bleibt
	 * erhalten!
	 * 
	 * @param buffer
	 * @return
	 */

	/**
	 * Test, ob Eingabezeile aus Sting vergleich besteht. Buffer bleibt
	 * erhalten! Ignoriere Gross-/Kleinschreibung
	 * 
	 * @param vergleich
	 * @return
	 */
	public static boolean vergleicheTxtIgnoreCase(String vergleich) {
		return buffer.vergleicheTxtIgnoreCase(vergleich);
	}

	public static boolean testInt() {
		return buffer.testeInt();
	}

	public static int readInt(int fehler) {
		return buffer.readInt(fehler);
	}

	public static boolean testLong() {
		return buffer.testeLong();
	}

	public static long readLong(long fehler) {
		return buffer.readLong(fehler);
	}

	public static boolean testFloat() {
		return buffer.testeInt();
	}

	public static float readFloat(float fehler) {
		return buffer.readFloat(fehler);
	}

	public static boolean testDouble() {
		return buffer.testeDouble();
	}

	public static double readDouble(double fehler) {
		return buffer.readDouble(fehler);
	}

	public static boolean testBoolean() {
		return buffer.testeBoolean();
	}

	public static boolean readBoolean(boolean fehler) {
		return buffer.readBoolean(fehler);
	}

	public static void loescheEingabezeile() {
		buffer.clearInputLine();
		
	}

}

interface ITextBuffer {
	public String nextLine();
}

class TextBuffer implements ITextBuffer {
	String text = null;
	boolean leer = true;

	ITextBuffer leseQuelle = this;

	public TextBuffer(ITextBuffer leseQuelle) {
		if (leseQuelle != null) {
			this.leseQuelle = leseQuelle;
		}
	}

	public TextBuffer(String  text) {
		leseQuelle=this;
		this.text=text;
	}

	@Override
	public String nextLine() {
		// Aufruf, wenn Eingabezeile leer
		// Dummy, damit Buffer allein verwendet werden kann
		return null;
	}

	private String textLine() {
		if (leer) {
			text = leseQuelle.nextLine();
			leer = false;
		}
		return text;
	}

	/**
	 * fuer externes Setzen der Eingabezeile
	 * @param text
	 */
	public void setzeTextline(String text) {
		this.text = text;
	}

	/**
	 * Loeschen des EingaabeBuffers
	 */
	public void clearInputLine() {
		text = null;
		leer = true;
	}

	public String inspectTxt() {
		String ret = textLine();
		return ret;
	}

	public String readTxt() {
		String ret = inspectTxt();
		clearInputLine();
		return ret;
	}

	/**
	 * Eingabezeile wird als String ausgegeben. Buffer nicht geloescht.
	 * 
	 * @return
	 */
	public boolean testeTxt() {
		if (textLine() == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Test, ob Eingabezeile aus Sting txt besteht. Buffer bleibt erhalten!
	 * 
	 * @param txt
	 * @return
	 */
	public boolean vergleicheTxt(String vergleich) {
		return vergleich.equals(textLine());
	}

	/**
	 * Test, ob Eingabezeile aus Sting txt besteht. Buffer bleibt erhalten!
	 * 
	 * @param txt
	 * @return
	 */
	public boolean vergleicheTxtIgnoreCase(String vergleich) {
		return vergleich.equalsIgnoreCase(textLine());
	}

	public boolean testeInt() {
		if (textLine() == null)
			return false;
		try {
			Integer.parseInt(textLine());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public int readInt(int fehler) {
		if (textLine() == null)
			return fehler;
		try {
			int ret = Integer.parseInt(textLine());
			clearInputLine();
			return ret;
		} catch (NumberFormatException e) {
			clearInputLine();
			return fehler;
		}
	}

	public boolean testeLong() {
		if (textLine() == null)
			return false;
		try {
			Long.parseLong(textLine());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	public long readLong(long fehler) {
		if (textLine() == null)
			return fehler;
		try {
			long ret = Long.parseLong(textLine());
			clearInputLine();
			return ret;
		} catch (NumberFormatException e) {
			clearInputLine();
			return fehler;
		}
	}

	public boolean testeFloat() {
		if (textLine() == null)
			return false;
		try {
			Float.parseFloat(textLine());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public float readFloat(float fehler) {
		if (textLine() == null)
			return fehler;
		try {
			float ret = Float.parseFloat(textLine());
			clearInputLine();
			return ret;
		} catch (NumberFormatException e) {
			clearInputLine();
			return fehler;
		}
	}

	public boolean testeDouble() {
		if (textLine() == null)
			return false;
		try {
			Double.parseDouble(textLine());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public double readDouble(double fehler) {
		if (textLine() == null)
			return fehler;
		try {
			double ret = Double.parseDouble(textLine());
			clearInputLine();
			return ret;
		} catch (NumberFormatException e) {
			clearInputLine();
			return fehler;
		}
	}

	public boolean testeBoolean() {
		if (textLine() == null)
			return false;
		if (textLine().equalsIgnoreCase("true")
				|| textLine().equalsIgnoreCase("wahr")
				|| textLine().equalsIgnoreCase("false")
				|| textLine().equalsIgnoreCase("falsch")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean readBoolean(boolean fehler) {
		if (textLine() == null)
			return fehler;
		if (textLine().equalsIgnoreCase("true")
				|| textLine().equalsIgnoreCase("wahr")) {
			clearInputLine();
			return true;
		} else if (textLine().equalsIgnoreCase("false")
				|| textLine().equalsIgnoreCase("falsch")) {
			clearInputLine();
			return false;
		} else {
			return fehler;
		}
	}

}
