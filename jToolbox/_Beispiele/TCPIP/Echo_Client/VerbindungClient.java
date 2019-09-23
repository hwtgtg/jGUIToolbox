//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class VerbindungClient {

	public static final int ERROR = -1;

	/**
	 * Soket fuer die Uebertragung
	 */
	protected Socket aufruf = null;

	// Die IOStreams
	protected InputStream inStream = null;
	protected OutputStream outStream = null;

	/** Datenstrom raus */
	private PrintWriter outString = null;
	/** Datenstrom rein */
	private BufferedReader inString = null;
	/** Datenstrom raus */
	private DataOutputStream outData = null;
	/** Datenstrom rein */
	private DataInputStream inData = null;

	/**
	 * Normaes IO-Modul
	 */
	public VerbindungClient() {
		aufruf = null;
		inStream = null;
		outStream = null;
	}

	/**
	 * Client-Modul
	 * 
	 * baut Verbindung zu host/port auf
	 * 
	 * @param host
	 * @param port
	 */

	public VerbindungClient(String host, int port) {
		this();
		verbindungAufbauen(host, port);
	}

	/**
	 * Baut eine Verbindung zum Server host/port auf
	 * 
	 * @param host
	 * @param port
	 * @return Erfolg
	 */
	public boolean verbindungAufbauen(String host, int port) {
		try {
			Socket socket = new Socket(host, port);
			verbindungAufbauen(socket);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Baut eine Verbindung ueber das gegebene Socket auf
	 * 
	 * @param socket
	 * @return Erfolg
	 */
	private boolean verbindungAufbauen(Socket socket) {
		try {
			if (aufruf != null) {
				aufruf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		aufruf = socket;
		try {
			inStream = aufruf.getInputStream();
			outStream = aufruf.getOutputStream();

			outString = new PrintWriter(outStream, true);
			inString = new BufferedReader(new InputStreamReader(inStream));

			outData = new DataOutputStream(outStream);
			inData = new DataInputStream(inStream);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Fuer Client:
	 * 
	 * gibt den Port des angerufenen Servers zurueck
	 * 
	 * @return serverport
	 */
	public int getPortServer() {
		return aufruf.getPort();
	}

	/**
	 * gibt den eigenen Port zurueck
	 * 
	 * @return port
	 */
	public int getLocalPort() {
		return aufruf.getLocalPort();
	}

	/**
	 * Schliesst die Verbindung
	 * 
	 */
	public void close() {
		try {
			aufruf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Liest einen String
	 * 
	 * @return als String
	 */
	public String readLine() {
		try {
			return inString.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Schreibt einen String
	 * 
	 */
	public void writeLine(String zeile) {
		outString.println(zeile);
	}

	/**
	 * Lesen eines Integer-Werts
	 * 
	 * @return als int
	 */
	public int readInt() {
		
		try {
			return inData.readInt();
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * Schreiben eines Integer-Werts
	 * 
	 */
	public void writeInt(int number) {
		try {
			outData.writeInt(number);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lesen eines Double-Werts
	 * 
	 * @return als double
	 */
	public double readDouble() {
		
		try {
			return inData.readDouble();
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * Schreiben eines Double-Werts
	 * 
	 */
	public void writeDouble(double number) {
		try {
			outData.writeDouble(number);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
