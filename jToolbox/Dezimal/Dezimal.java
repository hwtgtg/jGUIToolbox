//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * <h1>Klasse <b>Dezimalzahl</b> <br/>
 * Eine Klasse ohne Konvertierungsprobleme bei Nachkommastellen<br/>
 * Diese Klasse kapselt die Klasse BigDecimal.</h1>
 * 
 * <h2>Methoden:</h2>
 * 
 * addiere, subtrahiere, multipliziere, diviediereExcakt, dividiereGerundet<br/>
 * <br/>
 * 
 * Das Ergebnis hat so viele Stellen, wie man braucht. </BR>
 * 
 * Bei dividiereExact so viele Stellen, wie man braucht. Die Division muss
 * aufgehen!<br/>
 * <br/>
 * 
 * Die Methode dividiereGerundet bestimmt das Basisobjekt die Anzahl der
 * Nachkommastellen<BR>
 * Beispiel: 12,1000 / 1,4 = 8.6429; 12.10 / 1.4 = 8.64<br/>
 * <br/>
 * 
 * istGleich , istGroesser , istKleiner , </br>
 * istGleichNull, istGroesserNull , istKleinerNull , </br>
 * <br/>
 * 
 * negieren , abs, hoch (ganze Zahl)<br/>
 * 
 * setzeNachkommastellen, leseNachkommastellen<br/>
 * <br/>
 * 
 * toDouble, toLong, toString<br/>
 * <br/>
 * 
 * toDouble toString<br/>
 * <br/>
 * 
 * Statische Methoden: public static Dezimal erzeugeAusDouble(double dwert)<br/>
 * <br/>
 * <br/>
 * 
 * Fuer die Ausgabe toString wird als Dezimaltrennzeichen das Komma verwendet.
 * Beim Lesen aus einem String wird diese Dezimaltrennzeichen vor der Umwandlung
 * automatisch in einen Punkt gewandelt. durch setzeDezimaltrennzeichen kann
 * jedes Zeichen als Dezimaltrennzeichen verwendet werden<br/>
 * 
 * <hr>
 * 
 * @author Hans Witt
 * @version 2.0 (29.12.2010)
 * 
 */

public class Dezimal {

	static private char dezimaltrennzeichen = ',';

	public static void setDezimaltrennzeichen(char dezimaltrennzeichen) {
		Dezimal.dezimaltrennzeichen = dezimaltrennzeichen;
	}

	public enum Art {
		exakt, gerundet
	}

	private BigDecimal dezimalzahl;

	private BigDecimal getBigDezimal() {
		return dezimalzahl;
	}

	private boolean NotANumber = false;

	public static final String strNAN = "##########";

	/**
	 * Erzeugt die Null
	 */
	public Dezimal() {
		dezimalzahl = BigDecimal.ZERO;
	}

	/**
	 * Erzeugt die Dezimalzahl NotANumber Wird als Basis fuer Rueckgabe
	 * verwendet
	 * 
	 * @param NaN
	 */
	private static boolean NaN = true;

	private Dezimal(boolean NaN) {
		NotANumber = NaN;
		dezimalzahl = BigDecimal.ZERO;
	}

	/**
	 * Erzeuge Dezimalzahl aus String
	 * 
	 */
	public Dezimal(String zahl) {
		String neu = zahl.replace(dezimaltrennzeichen, '.');
		neu = neu.trim(); // Leerzeichen am Anfang uind Ende entfernt
		if (neu.length() == 0)
			NotANumber = true; // leerer String

		try {
			dezimalzahl = new BigDecimal(neu);
		} catch (NumberFormatException e) {
			NotANumber = true;
		}

	}

	/**
	 * Erzeuge Dezimalzahl
	 * 
	 * Ein Feld aus 2 Long-Werten erzeugt eine Dezimalzahl
	 * 
	 * die erste Komponente ist die Ziffernfolge, die zweite Komponente die
	 * Nachkommastellen
	 * 
	 */
	public Dezimal(long[] zahl) {
		try {
			dezimalzahl = (BigDecimal.valueOf(zahl[0])).movePointRight((int) zahl[1]);
		} catch (Exception e) {
			NotANumber = true;
		}

	}

	/**
	 * Erzeuge Dezimalzahl
	 * 
	 * in den lwert wird das Komma eingefuegt
	 * 
	 * @param lwert
	 * @param nachkomma
	 */
	public Dezimal(long lwert, int nachkomma) {
		dezimalzahl = (BigDecimal.valueOf(lwert)).movePointLeft(nachkomma);
	}

	/**
	 * Erzeuge Dezimalzahl aus einem double-Wert
	 * 
	 */
	public Dezimal(double dWert) {
		try {
			dezimalzahl = BigDecimal.valueOf(dWert);
		} catch (Exception e) {
			NotANumber = true;
		}
	}

	/**
	 * Copy-Konstruktor
	 * 
	 * @param dez
	 */
	public Dezimal(Dezimal dez) {
		// Ueber toString uebertragen
		try {
			dezimalzahl = new BigDecimal(dez.getBigDezimal().toString());
		} catch (Exception e) {
			NotANumber = true;
		}
	}

	/**
	 * Erzeugen von Dezimal zu BigDezimal-Wert nur interne Verwendung
	 * 
	 * @param bigDez
	 */
	private Dezimal(BigDecimal bigDez) {
		NotANumber = false;
		dezimalzahl = bigDez;
	}

	public Dezimal addiere(Dezimal dez) {
		if (NotANumber || dez.NotANumber) {
			return new Dezimal(NaN);
		}
		return new Dezimal(dezimalzahl.add(dez.getBigDezimal()));
	}

	public Dezimal subtrahiere(Dezimal dez) {
		if (NotANumber || dez.NotANumber) {
			return new Dezimal(NaN);
		}
		return new Dezimal(dezimalzahl.subtract(dez.getBigDezimal()));
	}

	public Dezimal multipliziere(Dezimal dez) {
		if (NotANumber || dez.NotANumber) {
			return new Dezimal(NaN);
		}
		return new Dezimal(dezimalzahl.multiply(dez.getBigDezimal()));
	}

	public Dezimal dividiereExact(Dezimal dez) {
		if (NotANumber || dez.NotANumber) {
			return new Dezimal(NaN);
		}
		try {
			return new Dezimal(dezimalzahl.divide(dez.getBigDezimal()));
		} catch (ArithmeticException e) {
			return new Dezimal(NaN);
		}
	}

	public Dezimal dividiereGerundet(Dezimal dez) {
		if (NotANumber || dez.NotANumber) {
			return new Dezimal(NaN);
		}
		try {
			return new Dezimal(dezimalzahl.divide(dez.getBigDezimal(), RoundingMode.HALF_EVEN));
		} catch (ArithmeticException e) {
			return new Dezimal(NaN);
		}
	}

	public Dezimal dividiere(Dezimal dez, Art art) {
		if (art == Art.gerundet) {
			return dividiereGerundet(dez);
		} else {
			return dividiereExact(dez);
		}
	}

	public Dezimal negieren() {
		if (NotANumber) {
			return new Dezimal(NaN);
		}
		return new Dezimal(dezimalzahl.negate());
	}

	public Dezimal abs() {
		if (NotANumber) {
			return new Dezimal(NaN);
		}
		return new Dezimal(dezimalzahl.abs());
	}

	public Dezimal hoch(int n) {
		return new Dezimal(dezimalzahl.pow(n));
	}

	public Dezimal Wurzel() {
		return new Dezimal(Math.sqrt(dezimalzahl.doubleValue()));
	}

	public Dezimal Abs() {
		return new Dezimal(dezimalzahl.abs());
	}

	public Dezimal max(Dezimal dezimal) {
		return new Dezimal(dezimalzahl.max(dezimal.dezimalzahl));
	}

	public Dezimal min(Dezimal dezimal) {
		return new Dezimal(dezimalzahl.min(dezimal.dezimalzahl));
	}

	public boolean istGleichNull() {
		if (NotANumber) {
			return false;
		}
		return (dezimalzahl.compareTo(BigDecimal.ZERO)) == 0;
	}

	public boolean istGroesserNull() {
		if (NotANumber) {
			return false;
		}
		return (dezimalzahl.compareTo(BigDecimal.ZERO)) > 0;
	}

	public boolean istKleinerNull() {
		if (NotANumber) {
			return false;
		}
		return (dezimalzahl.compareTo(BigDecimal.ZERO)) < 0;
	}

	public boolean istGroesser(Dezimal dez) {
		if (NotANumber || dez.NotANumber) {
			return false;
		}
		return (dezimalzahl.compareTo(dez.getBigDezimal())) > 0;
	}

	public boolean istKleiner(Dezimal dez) {
		if (NotANumber || dez.NotANumber) {
			return false;
		}
		return (dezimalzahl.compareTo(dez.getBigDezimal())) < 0;
	}

	public boolean istGleich(Dezimal dez) {
		if (NotANumber || dez.NotANumber) {
			return false;
		}
		return (dezimalzahl.compareTo(dez.getBigDezimal())) == 0;
	}

	public double toDouble() {
		if (NotANumber) {
			return Double.NaN;
		}
		return dezimalzahl.doubleValue();
	}

	public long toLong() {
		if (NotANumber) {
			return Long.MAX_VALUE;
		}
		BigDecimal erg = dezimalzahl.round(new MathContext(0, RoundingMode.HALF_UP));

		return erg.longValue();
	}

	public long trunc() {
		if (NotANumber) {
			return Long.MAX_VALUE;
		}
		BigDecimal erg = dezimalzahl.round(new MathContext(0, RoundingMode.DOWN));

		return erg.longValue();
	}

	public long[] toLongFeld() {
		long[] erg = new long[2];

		if (!NotANumber) {
			erg[0] = dezimalzahl.unscaledValue().longValue();
			erg[1] = dezimalzahl.scale();

		}
		return erg;
	}

	public int leseNachkommastellen() {
		return dezimalzahl.scale();
	}

	public void setzeNachkommastellen(int nachkommastellen) {
		dezimalzahl = dezimalzahl.setScale(nachkommastellen, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		if (NotANumber) {
			return strNAN;
		}
		String ergStr = dezimalzahl.toPlainString();
		return ergStr.replace('.', dezimaltrennzeichen);
	}

	public String toString(int stellen) {
		if (NotANumber) {
			return strNAN;
		}
		String ergStr = dezimalzahl.toPlainString();
		int len = ergStr.length();
		if (len <= stellen) {
			ergStr = ergStr.replace('.', dezimaltrennzeichen);
		} else {
			int ipoint = ergStr.indexOf('.');
			if (ipoint < 0) {
				// ganze Zahl
				BigDecimal btemp = dezimalzahl.setScale(-1, RoundingMode.HALF_UP);
				ergStr = btemp.toString();
				len = ergStr.length();
				if (len > stellen) {
					btemp = dezimalzahl.setScale(-1 - (ergStr.length() - stellen), RoundingMode.HALF_UP);
					ergStr = btemp.toString();
				}
			} else if (stellen > ipoint) {
				ergStr = ergStr.substring(0, stellen);
			} else {
				int scale = ipoint - stellen;
				BigDecimal btemp = dezimalzahl.setScale(-(scale), RoundingMode.HALF_UP);
				ergStr = btemp.toString();
				len = ergStr.length();
				int edef = len - stellen;
				do {
					if (edef > 0) {
						btemp = dezimalzahl.setScale(-(scale + edef), RoundingMode.HALF_UP);
						ergStr = btemp.toString();
						len = ergStr.length();
						edef = len - stellen;
					}
				} while ((edef > 0) && (ergStr.indexOf('E') > 2));

			}
		}
		ergStr = ergStr.replace('.', dezimaltrennzeichen);
		
		if(ergStr.endsWith(",")){
			ergStr=ergStr.substring(0, ergStr.length()-1);
		}
		return ergStr;

	}

	public boolean isValid() {
		return !NotANumber;
	}

}
