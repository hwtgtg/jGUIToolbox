//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ServerVerbindungen {
	ServerSocket server = null;
	int verbunden = 0;

	ServerVerbindungen(ServerSocket server) {
		this.server = server;
		verbunden = 1;
	}

	public void incVerbindung() {
		verbunden++;
	}

	public void decVerbindung() {
		verbunden--;
	}

	public boolean verbindungenVorhanden() {
		return (verbunden > 0);
	}

}

public class VerbindungServer {

	public static final int ERROR = -1;

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

	private static ArrayList<ServerVerbindungen> socketlist = new ArrayList<ServerVerbindungen>();

	private static synchronized ServerSocket getServer(int serverPort) {
		for (ServerVerbindungen s : socketlist) {
			if (s.server.getLocalPort() == serverPort)
				s.incVerbindung();
			return s.server;
		}
		ServerSocket server = null;
		try {
			server = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		VerbindungServer.addServer(server);

		return server;
	}

	private static void addServer(ServerSocket server) {
		socketlist.add(new ServerVerbindungen(server));
	}

	private static synchronized void serverEnfernen(ServerSocket server) {
		ServerVerbindungen erg = null;
		for (ServerVerbindungen s : socketlist) {
			if (s.server == server) {
				s.decVerbindung();
				if (!s.verbindungenVorhanden()) {
					erg = s;
				}
			}
		}
		if (erg != null) {
			socketlist.remove(erg);
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Server zu Port " + server.getLocalPort()
					+ " geschlossen");

			server = null;
		}
	}

	/**
	 * Server bei einem Serversocket
	 */
	protected ServerSocket server;

	/**
	 * Soket fuer die Uebertragung
	 */
	protected Socket aufruf = null;

	public static VerbindungServer getVerbindungServerPort(int serverPort){
		VerbindungServer vbs = new VerbindungServer(serverPort);
		if (vbs.server== null ){
			return null;
		} else {
			return vbs ;
		}
		
	}
	
	/**
	 * Die Server-Portnummer
	 * 
	 * Wird bei neuerServerkanalAufbauen vom System vergeben
	 */
	protected int serverport = -1;

	/**
	 * Normaes IO-Modul
	 */
	private VerbindungServer() {
		aufruf = null;
		inStream = null;
		outStream = null;
	}

	// Server-IOModul horcht am Port serverPort
	public VerbindungServer(int serverPort) {
		this();
		// Kommunikation aufbauen
		server = VerbindungServer.getServer(serverPort);
	}

	/*
	 * serverkanalAufbauen()
	 * 
	 * wartet auf Soccket-Anfrage vom Client
	 */
	public boolean warteAufClient() {
		if (server == null) {
			return false;
		} else {
			// Kommunikation aufbauen
			Socket socket = null;
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

			// Kanal zum Socket aufbauen
			verbindungAufbauen(socket);
			return true;
		}
	}

	/**
	 * Erzeugt eine neue Serververbindung
	 * 
	 * Portnummer wird vom Betriebssystem zugeordnet
	 * 
	 * Sie kann mit getServerport abgefragt werden
	 * 
	 * @return serverport oder ERROR
	 */
	public int neuenServerportAufbauen() {
		try {
			server = new ServerSocket();
			server.bind(null);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}

		VerbindungServer.addServer(server);

		serverport = server.getLocalPort();

		if (warteAufClient()) {
			return serverport;
		} else {
			return ERROR;
		}
	}

	/**
	 * gibt die portnummer zurueck
	 * 
	 * @return serverport
	 */
	public int getServerport() {
		return serverport;
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
	 * Fuer Server:
	 * 
	 * gibt den Port des angerufenen Client zurueck
	 * 
	 * @return clientPort
	 */
	public int getClientPort() {
		return aufruf.getPort();
	}

	/**
	 * gibt den eigenen Port zurueck
	 * 
	 * @return serverport
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
		VerbindungServer.serverEnfernen(server);
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
