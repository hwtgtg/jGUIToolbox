//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * <h1>Taktgeber</h1>
 * ist eine Klasse, die in gleichen Zeitabstaenden ein Taktsignal gibt.<br/><br/>
 * 
 *  Ueber die Methode <b>wurdeSignalisiert()</b> kann der Status abgefragt werden.<br/>
 *  Es wird gemeldet, ob sich seit der letzten Abfrage die ein Taktsignal ereignet hat.<br/>
 *  <br/>
 *  Status zurueck: ruecksetzenTimer() <br/>
 *  <br />     
 *  Mit warteBisTaktsignal() kann die Ausf&uuml;hrung angehalten werden bis zum Taktsignal.<br/>
 *      
 *  Ist der Kommunikationslink gesetzt, meldet das Objekt das <b><i>Zeitsignal</i></b>. <br/><br/>
 *  
 * <hr> 
 * @author Hans Witt
 * 
 * @version
 * Version 3 (21.8.2008) <br/>
 * 	     Methode mehrfach( int anzahl ) implementiert <br/>
 * 		 Methode start() entfernt. Durch Setzen der Anzahl startet der Timer <br/>  	
 */
public class Taktgeber {
	private Timer		t;
	protected int		delay			= 1000;	// milliseconds
	protected int		startDelay		= 1000;
	
	protected boolean	timerSignal		= false;
	
	protected int		anzahl;
	protected boolean	begrenzteAnzahl	= false;
	
	public Taktgeber() {
		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tuWas();
			}
		};
		t = new Timer(delay, taskPerformer);
		setzeZeitZwischenAktionen(1000);
		setzeAnfangsZeitverzoegerung(1000);
	}
	
	/**
	 * Konstruktor mit gleichzeitigem Setzen des tuWas-Objekts
	 * 
	 * @param linkObj
	 * @param ID
	 */
	public Taktgeber(ITuWas linkObj, int ID) {
		this();
		setzeLink(linkObj, ID);
	}
	
	void tuWas() {
		timerSignal = true;
		if (begrenzteAnzahl) { // Bei Methode eimal stopt der Timer automatisch
			anzahl--;
			if (anzahl == 0) t.stop();
		}
		if (linkObj != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					linkObj.tuWas(id);
				}
			}).start();
		}
		
	}
	
	ITuWas	linkObj;
	int		id	= 0;	// ID der Komponente fuer Callback
								
	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param ID
	 */
	public void setzeID(int ID) {
		this.id = ID;
	}
	
	/**
	 * Setzen des Kommunikationslinks<br/>
	 * 
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj) {
		this.linkObj = linkObj;
	}
	
	/**
	 * Setzen des Kommunikationslinks<br/>
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param linkObj
	 * @param basisID
	 * 
	 */
	public void setzeLink(ITuWas linkObj, int basisID) {
		this.linkObj = linkObj;
		id = basisID;
	}
	
	/**
	 * Es wird abgefragt, ob seit der letzten Nachfrage der Timer sihnalisiert
	 * hat
	 * 
	 * @return Ergebnis
	 */
	public boolean wurdeSignalisiert() {
		boolean wert = timerSignal;
		timerSignal = false;
		return wert;
	}
	
	/**
	 * Unterbricht das Programm bis zum n&auml;chsten Taktsignal 
	 */
	public void warteBisTaktsignal() {
		while (!wurdeSignalisiert()) {
			StaticTools.warte(20); // warte 20ms
		}
	}
	
	/**
	 * SetzeWiederholungsverzoegerung
	 */
	public void setzeZeitZwischenAktionen(int delay) {
		t.setDelay(delay);
	}
	
	/**
	 * SetzeAnfangsverzoegerung
	 */
	public void setzeAnfangsZeitverzoegerung(int delay) {
		t.setInitialDelay(delay);
	}
	
	/**
	 * Timer mit Wiederholung automatischer Stop nach anzahl Intervallen
	 */
	public void mehrfach(int anzahl) {
		t.stop();
		this.anzahl = anzahl;
		begrenzteAnzahl = true;
		t.setRepeats(true);
		t.start();
	}
	
	/**
	 * einmaliges Signalisieren
	 */
	public void einmal() {
		t.stop();
		begrenzteAnzahl = false;
		t.setRepeats(false);
		t.start();
	}
	
	/**
	 * einmaliges Signalisieren
	 */
	public void einmal(int delay) {
		t.stop();
		setzeAnfangsZeitverzoegerung(delay);
		setzeZeitZwischenAktionen(delay);
		begrenzteAnzahl = false;
		t.setRepeats(false);
		t.start();
	}
	
	/**
	 * Timer mit Endlos-Wiederholung
	 */
	public void endlos() {
		t.stop();
		begrenzteAnzahl = false;
		t.setRepeats(true);
		t.start();
	}
	
	/**
	 * Timer mit Endlos-Wiederholung
	 *
	 *  
	 */
	public void endlos(int anfangsDelay,int zwischenDelay) {
		t.stop();
		setzeAnfangsZeitverzoegerung(anfangsDelay);
		setzeZeitZwischenAktionen(zwischenDelay);
		begrenzteAnzahl = false;
		t.setRepeats(true);
		t.start();
	}

	public void stop() {
		t.stop();
	}
	
	/**
	 * Status des Timers
	 * 
	 * @return Timer l&auml;uft?
	 */
	public boolean laufend() {
		return t.isRunning();
	}
	
}
