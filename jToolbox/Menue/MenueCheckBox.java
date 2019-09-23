//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBoxMenuItem;

/**
 * <h1>MenueCheckBox</h1> Die Menue-Komponente mit Checkbox. <BR/>
 * MenueCheckBox ist von MenueEintrag abgeleitet. Die Methoden von MenueEintrag
 * gelten auch fuer MenueCheckBox. <BR/>
 * <BR/>
 * Mit der Methode istGewaehlt() kann der Status der Checkbox abgefragt werde.
 * Es ist kein Kommunikationslink noetig, um den Status abzufragen.
 * 
 * @author Witt
 * 
 */
public class MenueCheckBox extends MenueEintrag implements ItemListener {
	/**
	 * AUSGEWAEHLT: 0;
	 * 
	 */
	public static final int AUSGEWAEHLT = 0;
	/**
	 * NICHTGEWAEHLT: 1;
	 * 
	 */
	public static final int NICHTGEWAEHLT = 1;

	protected MenueCheckBox() {

	}

	public MenueCheckBox(String text) {
		menueEintrag = new JCheckBoxMenuItem(text);
	}

	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param ID
	 */
	@Override
	public void setzeID(int ID) {
		this.id = ID;
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * 
	 * @param linkObj
	 */
	@Override
	public void setzeLink(ITuWas linkObj) {
		if ((this.linkObj == null) && (linkObj != null)) {
			menueEintrag.addItemListener(this);
		}
		if (linkObj == null) {
			menueEintrag.removeItemListener(this);
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
	@Override
	public void setzeLink(ITuWas linkObj, int ID) {
		this.id = ID;
		setzeLink(linkObj);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (linkObj != null) {
			if (((JCheckBoxMenuItem) menueEintrag).isSelected()) {

				new Thread(new Runnable() {
					@Override
					public void run() {
						linkObj.tuWas(id + AUSGEWAEHLT);
					}
				}).start();
			} else {

				new Thread(new Runnable() {
					@Override
					public void run() {
						linkObj.tuWas(id + NICHTGEWAEHLT);
					}
				}).start();
			}
		}
	}

	/**
	 * Status der Checkbox
	 * 
	 * @return Status
	 */
	public boolean istGewaehlt() {
		return ((JCheckBoxMenuItem) menueEintrag).isSelected();
	}

	/**
	 * Status der Komponente auf >>gewaehlt<< setzen
	 */
	public void setzeGewaehlt() {
		((JCheckBoxMenuItem) menueEintrag).setSelected(true);
	}

	/**
	 * Status der Komponente auf >>Nicht gewaehlt<< setzen
	 */
	public void setzeDeSelect() {
		((JCheckBoxMenuItem) menueEintrag).setSelected(false);
	}

}
