//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
import java.io.*;



/**
 * Clientimplementierung - Echo
 * 
 * @author Hans Witt
 * @version 1.0
 * 
 */
public class Echo_CLIENT {

    /** bidirektionale Schnittstelle zur Netzwerkprotokoll-Implementierung*/
    private VerbindungClient verbindung = null;

    /** Datenstrom von der Tastatur*/
    private TBConsole console = null ;
    /** Eingabe von Client an Server*/
    private String clientEingabe="";

    /** Botschaft vom Server*/ 
    private String serverBotschaft = "";

    /**
     * Konstruktor
     * @exception IOException eine Ausnahme tritt m&ouml;glicherweise auf falls:<br/>
     * - die Clientverbindung nicht hergestellt werden konnte (beispielsweise bei falscher IP-Adresse oder
     * falschem Port)<br/>
     * - die Verbindung zum Server gest&ouml;rt bzw. unterbrochen wurde.
     */
    public Echo_CLIENT() {

        VerbindungHerstellen();

        console.println("Echo Start: Daten eingeben. Beenden mit ENDE");

        //Tastatureingabe, Senden und Empfangen
        do {
            clientEingabe = console.readLine();
            //auf die Clientkonsole ausgeben
            console.println("Client: " + clientEingabe);
            //und zum Server schicken
            verbindung.writeLine(clientEingabe);

            //Serverbotschaft anzeigen.
            console.println("Server: " + serverBotschaft);

            //Eingabe vom Client lesen
        } while (((serverBotschaft = verbindung.readLine()) != null) && (!serverBotschaft.equals("ENDE")));

        VerbindungBeenden();
    }

    /**
     * stellt die Serververbindung her und erzeugt die n&ouml;tigen Lese- und Schreibojekte
     * @exception IOException tritt auf, falls die Verbindung nicht korrekt erstellt werden kann.
     */
    private void VerbindungHerstellen()  {
        //Ipadresse und Port ermitteln
        console  = new TBConsole();

        console.println("IP-Adresse des Servers eingeben:");
        String ipadresse = console.readLine();

        console.println("Port eingeben:");
        int port = console.readInt();

        //Verbindung herstellen
        verbindung = new VerbindungClient(ipadresse, port);
        console.println("Verbindung hergestellt");
     
        
    }

    /**
     * beendet alle Lese- und Schreibkan&auml;le und die Verbindung zum Server
     * @exception IOException tritt auf, falls eine Verbindung oder ein Stream nicht beendet werden kann.
     */
    private void VerbindungBeenden() {
        verbindung.close();
        console.close();
    }
}
