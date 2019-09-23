//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 * <h1>D_Farbe</h1> kapselt den Farbdialog.<br/>
 * <br/>
 * <hr>
 * Es gibt drei Konstruktoren:
 * <ul> 
 * <li> Der Standard-Konstruktor new D_Farbe() <br />
 *      Dialog erzeugen: dlg = new D_Farbe();<br />
 *      Nach Ende des Dialogs muss die Farbe expizit uebernommen werden: dlg.setzeFarbname("braun");</li>
 * <li> Der Konstruktor D_Farbe(String farbe)</li>
 * <li> Der Konstruktor D_Farbe(String titel, String farbe)</li>
 * </ul> 
 * Beim Standardkonstruktor muss nach Ende des Dialogs die Farbe expizit uebernommen werden: <br /> 
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; dlg.setzeFarbname("braun");</li>
 * Beim <b>Abbruch</b> wird beim Standardkonstruktor null zurueckgeliefert, sonst wird dem Farbwert die Standardardfarbe "helles grau" zugeordnet <br />
 * <hr>
 * 
 * @version 1.0 (1.1.2011)
 */
public class Dlg_Farbe {
	public String titel = "Farbauswahl";
	protected int meldungsTyp = JOptionPane.PLAIN_MESSAGE;
	public Color cErgebnis = null;
	public Color cStart = null;
	public String farbname = null;
	protected String ergebnis = "";

	/**
	 * Konstruktor
	 */
	public Dlg_Farbe() {
		cStart = Zeichnung.gibZeichenflaeche().getBackground();
		zeigeFarbDialog();
	}

	/**
	 * Konstruktor <br />
	 * 
	 * Der Farbname farbe wird gesetzt
	 */
	public Dlg_Farbe(String farbe) {
		if (StaticTools.istFarbeVorhanden(farbe)) {
			cStart = StaticTools.getColor(farbe);
		} else {
			cStart = Zeichnung.gibZeichenflaeche().getBackground();
		}
		zeigeFarbDialog();
		setzeAlsFarbe(farbe);
	}

	/**
	 * Konstruktor <br />
	 * 
	 * Der Farbname farbe wird gesetzt
	 */
	public Dlg_Farbe(String titel, String farbe) {
		if (StaticTools.istFarbeVorhanden(farbe)) {
			cStart = StaticTools.getColor(farbe);
		} else {
			cStart = Zeichnung.gibZeichenflaeche().getBackground();
		}
		this.titel = titel;
		zeigeFarbDialog();
		setzeAlsFarbe(farbe);
	}

	/**
	 * rufe Meldungsfenster auf
	 */
	private void zeigeFarbDialog() {
		cErgebnis = JColorChooser.showDialog(Zeichnung.gibZeichenflaeche(),
				titel, cStart);
	}

	/**
	 * Lese das Ergebnis des Farbdialogs
	 * 
	 * @return Ergebnis als Color-Wert
	 */
	public Color leseFarbe() {
		return cErgebnis;
	}

	/**
	 * Setzen der Farbe als farbname
	 * @param farbname
	 */
	public void setzeAlsFarbe(String farbname) {
		if (cErgebnis != null)
			StaticTools.setzeFarbe(farbname, cErgebnis);
	}
}
