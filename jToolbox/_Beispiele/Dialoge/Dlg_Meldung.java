//%$JGUIToolbox$%//ID fuer Toolboxdateien
import javax.swing.JOptionPane;

/**
 * <h1>Dlg_Meldung</h1> kapselt den Meldungsdialog.<br/><br/>
 * <hr>
 * <b>Anwendung:</b> <br/>
 * <ul>
 * <li>Dialog erzeugen:  dlg = new Dlg_Meldung();</li>
 * <li>Titel setzen: dlg.setzeTitel("Titel der Dialogbox");</li>
 * <li>Meldungstext setzen: dlg.setzeMeldungstext("Meldung");</li>
 * <li>Art des Icons setzen: dlg.icon_frage();</li>
 * <li>Dialogbox zeigen:dlg.zeigeMeldung();</li>
 * </ul>
 * <hr>
 * @author Hans Witt
 * @version
 * Version: 3 (13.8.2008)<br />
 * @version  3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt <br />
 */
public class Dlg_Meldung {
	public String titel = "";
	public String meldung = "...";
	protected int typ = JOptionPane.PLAIN_MESSAGE;

	public Dlg_Meldung() {

	}

	public void zeigeMeldung() {
		JOptionPane.showMessageDialog(Zeichnung.gibJFrame(), meldung, titel,
				typ);
	}

	public void setzeTitel(String titel) {
		this.titel = titel;
	}

	public void setzeMeldungstext(String meldung) {
		this.meldung = meldung;
	}

	public void icon_fehlermeldung() {
		typ = JOptionPane.ERROR_MESSAGE;
	}

	public void icon_hinweis() {
		typ = JOptionPane.INFORMATION_MESSAGE;
	}

	public void icon_warnug() {
		typ = JOptionPane.WARNING_MESSAGE;
	}

	public void icon_frage() {
		typ = JOptionPane.QUESTION_MESSAGE;
	}

	public void icon_ohneIcon() {
		typ = JOptionPane.PLAIN_MESSAGE;
	}
}
