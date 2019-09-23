//%$JGUIToolbox$%//ID fuer Toolboxdateien
import javax.swing.JOptionPane;

/**
 * <h1>Dlg_Eingabe</h1> kapselt den Eingabedialog.<br/><br/>
 * <hr>
 * <b>Anwendung:</b> <br/>
 * <ul>
 * <li>Dialog erzeugen:  dlg = new Dlg_Eingabe();</li>
 * <li>Titel setzen: dlg.setzeTitel("Titel der Dialogbox");</li>
 * <li>Meldungstext setzen: dlg.setzeMeldungstext("Meldung");</li>
 * <li>Art des Icons setzen: dlg.icon_frage();</li>
 * <li>Dialogbox zeigen:dlg.zeigeMeldung();</li>
 * <li>Ergebnis des Dialogs abfragen: <br/>
 *     Das Ergebnis ist grundsaetzlich ein String - eine Zeichenkette<br/>
 *     Um die Weiterverwendung als Zahl zu erleichtern sind Umwandelfunktionen bereitgestellt. <br />
 *     Der Parameter ist der Wert, der bei einem Umwandlungsfehler geliefert wird    
 *     <ul>
 *        <li>Ergebnis als Zeichenkette: String txt = dlg.leseErgebnis();</li>
 *        <li>Ergebnis als ganze Zahl: int iErg = dlg.leseInteger(-1); // Eine Dezimalzahl liefert den Fehlerwert !</li>
 *        <li>Ergebnis als gerundete ganze Zahl: int iErg = dlg.leseIntegerGerundet(-1); // Hier wird eine Dezimalzahl akzeptiert!</li>
 *        <li>Ergebnis als double-Zahl: double dErg = dlg.leseDouble(0,99); <br />
 *        // Hinweis: Dezimalkommas werden vor der Konversion in den Dezimalpunkt umgewandelt.<br />
 *        // Exponentialeingaben werden akzeptiert: z.B. 1,45e12 </li>
 *     </ul>
 * </li>
 * </ul>
 * Beim <b>Abbruch</b> wird der leere String "" zurueckgeliefert. <br />
 * <b>Nach dem Lesen</b> wird der Ergebnisstring geleert !
 * 
 * <hr>
 * @version
 * Version 3 (13.8.2008) <br />
 * 
 * Version  3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt <br /> 
 */
public class Dlg_Eingabe {
	public String titel = "";
	public String meldung = "...";
	protected int meldungsTyp = JOptionPane.PLAIN_MESSAGE;

	protected String ergebnis = "";

	/**
	 * Konstruktor
	 */
	public Dlg_Eingabe() {

	}

	/**
	 * zeigt den Dialog an
	 */
	public void zeigeMeldung() {
		ergebnis = JOptionPane.showInputDialog(Zeichnung.gibJFrame(), meldung,
				titel, meldungsTyp);
		if(ergebnis==null){
			ergebnis = "";
		}
	}

	/**
	 * Lese das Ergebnis des Meldungsfensters
	 * 
	 * @return Ergebnis <br/> 
	 * nach dem Lesen wird der Ergebnisstring geleert
	 */
	public String leseErgebnis() {
		String temp = ergebnis;
		ergebnis = "";
		return temp;
	}

	public int leseIntegerGerundet(int def) {
		int value = 0;
		value = (int) Math.round(leseDouble(def));
		return value;
	}
	
	public int leseInteger(int def) {
		int value = 0;
		String neu = leseErgebnis().trim();
		try {
			value = Integer.parseInt(neu);
		} catch (Exception e) {
			value = def;
		}
		return value;
	}

	public double leseDouble(double def) {
		double value = 0;
		String neu = leseErgebnis().replace(',', '.');
		try {
			value = Double.parseDouble(neu);
		} catch (Exception e) {
			value = def;
		}
		return value;
	}

	public void setzeTitel(String titel) {
		this.titel = titel;
	}

	public void setzeMeldungstext(String meldung) {
		this.meldung = meldung;
	}

	public void icon_fehlermeldung() {
		meldungsTyp = JOptionPane.ERROR_MESSAGE;
	}

	public void icon_hinweis() {
		meldungsTyp = JOptionPane.INFORMATION_MESSAGE;
	}

	public void icon_warnug() {
		meldungsTyp = JOptionPane.WARNING_MESSAGE;
	}

	public void icon_frage() {
		meldungsTyp = JOptionPane.QUESTION_MESSAGE;
	}

	public void icon_ohneIcon() {
		meldungsTyp = JOptionPane.PLAIN_MESSAGE;
	}
}
