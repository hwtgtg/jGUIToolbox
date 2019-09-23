//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * <h1>MenueEintrag</h1>
 * 
 * Komponente in Menues.<BR/>
 * u.a.:<BR/>
 * Erzeugung: new MenueEintrag(Anzeigetext)<BR/>
 * Mnemonik setzen:setzeMnemonik(zeichen)<BR/>
 * Schnellzugriffstaste setzen: setzeAktivierung(tastenbeschreibung);<BR/>
 * 
 * 
 * @author Witt
 * 
 */
public class MenueEintrag implements ActionListener {

	public JMenuItem menueEintrag;

	protected MenueEintrag() {

	}

	public MenueEintrag(String text) {
		menueEintrag = new JMenuItem(text);
	}

	public MenueEintrag(String text, Font font) {
		menueEintrag = new JMenuItem(text);
		menueEintrag.setFont(font);
	}

	public MenueEintrag(String text, int schriftgroesse) {
		menueEintrag = new JMenuItem(text);
		// menueEintrag.setFont(menueEintrag.getFont().deriveFont(schriftgroesse));
		menueEintrag.setFont(new Font("ARIAL", Font.PLAIN, schriftgroesse));
	}

	public JMenuItem getMenueItem() {
		return menueEintrag;
	}

	/**
	 * Setzt die Schnellzugriffs-Tastenkombination, die den Menueeintrag sofort
	 * aktiviert.<BR/>
	 * Siehe auch Klasse Tastatur.<BR/>
	 * 
	 * Beispiele: "alt G" fuer ALT+g<BR/>
	 * "ctrl F1" fuer die Funktionstaste F1<BR/>
	 * "alt ctrl released Q" fuer alt+ctrl+q loslassen<BR/>
	 * Tip: alt , ctrl , shift klein schreiben<BR/>
	 * Den Buchstaben gross! Modifikatoren klein
	 * 
	 * @param text
	 */
	public void setzeAktivierung(String text) {
		menueEintrag.setAccelerator(KeyStroke.getKeyStroke(text));
	}

	/**
	 * Setzt Mnemonik beim Menueeintrag (den unterstrichenen Buchstaben)<BR/>
	 * 
	 * @param c
	 */
	public void setzeMnemonik(char c) {
		menueEintrag.setMnemonic(c);
	}

	/**
	 * Setzt den Anzeigetext bei der Menuekomponente
	 * 
	 * @param text
	 */
	public void setzeBeschreibung(String text) {
		menueEintrag.getAccessibleContext().setAccessibleDescription(text);
	}

	/**
	 * Setzt den Anzeigetext bei der Menuekomponente
	 * 
	 * @param text
	 */
	public void setzeBeschreibung(String text, int schriftgroesse, boolean hervorgehoben) {
		menueEintrag.getAccessibleContext().setAccessibleDescription(text);
		if (hervorgehoben) {
			menueEintrag.setFont(menueEintrag.getFont().deriveFont(Font.BOLD, schriftgroesse));
		} else {
			menueEintrag.setFont(menueEintrag.getFont().deriveFont(Font.PLAIN, schriftgroesse));
		}
	}

	/**
	 * Setzt den Anzeigetext bei der Menuekomponente
	 * 
	 * @param text
	 */
	public void setzeBeschreibung(String text, int schriftgroesse) {
		menueEintrag.getAccessibleContext().setAccessibleDescription(text);
		menueEintrag.setFont(menueEintrag.getFont().deriveFont(schriftgroesse));
	}

	// Zur Kommunikation zwischen Objekten
	ITuWas linkObj; // Link auf das zu benachrichtigende Objekt
	int id = 0; // ID der Komponente. Fuer Callback wichtig

	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
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
		if ((this.linkObj == null) && (linkObj != null)) {
			menueEintrag.addActionListener(this);
		}
		if (linkObj == null) {
			menueEintrag.removeActionListener(this);
		}
		this.linkObj = linkObj;
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param linkObj
	 * @param ID
	 * 
	 */
	public void setzeLink(ITuWas linkObj, int ID) {
		this.id = ID;
		setzeLink(linkObj);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (linkObj != null)
			new Thread(new Runnable() {
				@Override
				public void run() {
					linkObj.tuWas(id);
				}
			}).start();
	}

	/**
	 * Aktiviert den Menueeintrag
	 */
	public void setzeAktiviert() {
		menueEintrag.setEnabled(true);
	}

	/**
	 * Deaktiviert den Menueeintrag. Der Menueeintrag wird ausgegraut
	 * dargestellt.
	 */
	public void setzeDeaktiviert() {
		menueEintrag.setEnabled(false);
	}

	/**
	 * Startus des Menueeintrags
	 * 
	 * @return Status
	 */
	public boolean istAktiviert() {
		return menueEintrag.isEnabled();
	}

}
