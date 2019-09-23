/**
 * Serverimplementierung - Echo
 * 
 * @author Hans Witt
 * @version 1.0
 * 
 */
public class Echo_SERVER {

	/**
	 * bidirektionale Schnittstelle zur Netzwerkprotokoll-Implementierung des
	 * Servers
	 */
	private VerbindungServer verbindung = null;

	private TBConsole console = null;

	/** Botschaft von Client zum Server */
	private String clientBotschaft = null;
	/** Botschaft vom Server zum Client */
	private String serverAntwort = null;

	/**
	 * Konstruktor des Servers
	 * 
	 */
	public Echo_SERVER() {

		ServerStarten();

		ClientVerbindungStarten(); // auf Client warten und verbinden

		Serverprozess();
	}

	/**
	 * fragt den Port ab und erzeugt den Serversocket
	 */
	private void ServerStarten() {

		console = new TBConsole();

		console.println("Port eingeben: ");

		// Serverport einlesen
		int port = console.readInt();

		// Servermodul erzeugen
		// Wenn Verbindung zum Port nicht möglich wird null zurückgegeben
		// Mit new ... wäre das nicht möglich
		verbindung = VerbindungServer.getVerbindungServerPort(port);

		console.println("Server gestartet...");

	}

	/**
	 * wartet auf eine Clientverbindung warten und erzeugt die n&ouml;tigen
	 * Lese- und Schreibobjekte nach dem eine Verbindung hergestellt wurde
	 */
	private void ClientVerbindungStarten() {

		// Wartet auf Verbindungswunsch vom Client
		verbindung.warteAufClient();

		// Begrue&szlig;ung
		
		verbindung
				.writeLine("Server verbunden. Ende durch Eingabe von 'ENDE'.");
		console.println("Client verbunden");
	}

	public void Serverprozess() {
		do {// lesen und antworten

			clientBotschaft = verbindung.readLine();

			// Beim Spiegelserver ist die Antwort gleich der Frage
			serverAntwort = clientBotschaft;

			verbindung.writeLine(serverAntwort);

		} while (!serverAntwort.equals("ENDE"));

		VerbindungBeenden();
	}

	/**
	 * schlie&szlig;t den Serversocket
	 */
	private void VerbindungBeenden() {
		verbindung.close();
		console.println("Server gestoppt...");
		console.close();
	}

}
