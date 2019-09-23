//%$JGUIToolbox$%//ID fuer Toolboxdateien
import javax.swing.JOptionPane;

/**
 * <h1>Dlg_Bestaetigung</h1> kapselt den Bestaetigungsdialog.<br/>
 * <hr>
 * <b>Anwendung:</b> <br/>
 * <ul>
 * <li>Dialog erzeugen:  dlg = new Dlg_Bestaetigung();</li>
 * <li>Titel setzen: dlg.setzeTitel("Titel der Dialogbox");</li>
 * <li>Meldungstext setzen: dlg.setzeMeldungstext("Meldung");</li>
 * <li>Art des Icons setzen: dlg.icon_frage();</li>
 * <li>Art der Dialogbox setzen: dlg.typ_JaNeinAbbruch();</li>
 * <li>Dialogbox zeigen:dlg.zeigeMeldung();</li>
 * <li>Ergebnis des Dialogs abfragen: char erg = dlg.leseErgebnis();</li>
 * </ul>
 * 
 * <hr>
 * Ergebniswerte sind Zeichen: <br />
 * Ja -> 'J' <br />
 * NEIN -> 'N' <br />
 * ABBRUCH oder Fenster schliesen: 'A' <br />
 * 
 * Dialog noch nicht aufgerufen: ' ' (Leerzeichen) <br />
 * <hr>
 * @version Version: 3 (13.8.2008) <br />
 * 
 *          Version 3.2 (18.8.2008) Zustandsvariable auf protected gesetzt <br />
 * 
 */
public class Dlg_Bestaetigung {
	public String titel = "";
	public String meldung = "...";
	protected int optionsType = JOptionPane.YES_NO_OPTION;
	protected int meldungsTyp = JOptionPane.PLAIN_MESSAGE;

	protected char ergebnis = ' ';

	/**
	 * Konstruktor
	 */
	public Dlg_Bestaetigung() {

	}

	/**
	 * rufe Anzeige von Dialog auf
	 */
	public void zeigeMeldung() {

		int result = JOptionPane.showConfirmDialog(Zeichnung.gibJFrame(),
				meldung, titel, optionsType, meldungsTyp);

		switch (result) {
		case JOptionPane.YES_OPTION:
			ergebnis = 'J';
			break;
		case JOptionPane.NO_OPTION:
			ergebnis = 'N';
			break;
		case JOptionPane.CANCEL_OPTION:
			ergebnis = 'A';
			break;
		case JOptionPane.CLOSED_OPTION:
			ergebnis = 'A';
			break;
		default:
			ergebnis = ' ';
			break;
		}
	}

	/**
	 * Lese das Ergebnis des Meldungsfensters
	 * 
	 * @return Ergebnis <br />
	 *         Ergebniswerte sind Zeichen: <br />
	 *         Ja -> 'J' <br />
	 *         NEIN -> 'N' <br />
	 *         ABBRUCH oder Fenster schliesen: 'A' <br />
	 */
	public char leseErgebnis() {
		return ergebnis;
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

	public void typ_typJaNein() {
		optionsType = JOptionPane.YES_NO_OPTION;
	}

	public void typ_typOKAbbruch() {
		optionsType = JOptionPane.OK_CANCEL_OPTION;
	}

	public void typ_JaNeinAbbruch() {
		optionsType = JOptionPane.YES_NO_CANCEL_OPTION;
	}

}
