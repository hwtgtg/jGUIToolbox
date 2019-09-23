//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Die Klasse <b>TBConsole</b> kapselt Ein- und Ausgabe von Text und Zahlen. <BR />
 * Eingabe von Text und Zahlen erfolgt immer zeilenweise. <BR /><BR />
 * 
 * Die Ausgabe kapselt (der Vollstaendigkeit halber) System.out.print.<br /><BR />
 *  
 * Die Eingabe kann als <b>int</b>, <b>long</b> oder <b>double</b> weitergegeben werden.  <BR />
 * Fehler (try/catch) werden abgefangen.<br />
 * 
 * @author Witt
 * 
 */
public class TBConsole {
	
	public static int	ERROR	= -1;
	
	public static void setERROR(int error) {
		ERROR = error;
	}
	
	/** Datenstrom von der Tastatur */
	private BufferedReader	tastatur	= null;
	
	public TBConsole() {
		tastatur = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public String readLine() {
		try {
			return tastatur.readLine();
		} catch (IOException e) {
		}
		return "";
	}
	
	public int readInt() {
		try {
			return Integer.parseInt(tastatur.readLine());
		} catch (Exception e) {
		}
		return ERROR;
	}
	
	public long readLong() {
		try {
			return Long.parseLong(tastatur.readLine());
		} catch (Exception e) {
		}
		return ERROR;
	}

	public double readDouble() {
		try {
			return Double.parseDouble(tastatur.readLine());
		} catch (Exception e) {
		}
		return ERROR;
	}
	
	@SuppressWarnings("static-method")
	public void println() {
		System.out.println();
	}
	
	public void println(String text) {
		print(text);
		println();
	}
	
	@SuppressWarnings("static-method")
	public void print(String text) {
		System.out.print(text);
	}
	
	public void printlnInt(int number) {
		println("" + number);
	}
	
	public void printInt(int number) {
		print("" + number);
	}

	public void printlnLong(long number) {
		println("" + number);
	}
	
	public void printlong(long number) {
		print("" + number);
	}
	
	public void printlnDouble(double number) {
		println("" + number);
	}
	
	public void printDouble(double number) {
		print("" + number);
	}
	
	public void close() {
		try {
			tastatur.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
