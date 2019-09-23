//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.event.ItemEvent;

import javax.swing.JRadioButtonMenuItem;

/**
 * <h1>MenueRadiotaste</h1> Die Menue-Komponente mit Radiotaste. <BR/>
 * Damit die Radio-Eigenschaften aktiviert werden, muss die Komponente einem
 * MenueRadioBehaelter hinzugefuegt werden.<BR/>
 * Alle Komponenten eines MenueRadioBehaelters deaktivieren bei Selektion alle
 * anderen Komponenten des MenueRadioBehaelters. <BR/>
 * MenueRadiotaste ist von MenueEintrag abgeleitet. Die Methoden von
 * MenueEintrag gelten auch fuer MenueRadiotaste. <BR/>
 * <BR/>
 * Mit der Methode istGewaehlt() kann der Status der Radiotaste abgefragt werde.
 * Es ist kein Kommunikationslink noetig, um den Status abzufragen.
 * 
 * @author Witt
 * 
 */
public class MenueRadioTaste extends MenueCheckBox {
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

	public MenueRadioTaste(String text) {
		menueEintrag = new JRadioButtonMenuItem(text);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (linkObj != null) {
			if (((JRadioButtonMenuItem) menueEintrag).isSelected()) {

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

	@Override
	public boolean istGewaehlt() {
		return ((JRadioButtonMenuItem) menueEintrag).isSelected();
	}

	/**
	 * Status der Komponente auf >>gewaehlt<< setzen
	 */
	@Override
	public void setzeGewaehlt() {
		((JRadioButtonMenuItem) menueEintrag).setSelected(true);
	}

	/**
	 * Status der Komponente auf >>Nicht gewaehlt<< setzen
	 */
	@Override
	public void setzeDeSelect() {
		((JRadioButtonMenuItem) menueEintrag).setSelected(false);
	}

}
