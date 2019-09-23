//%$JGUIToolbox$%//ID fuer Toolboxdateien

import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;

/**
 * <h1>MenueRadioBehaelter</h1> Damit die Radio-Eigenschaften aktiviert werden,
 * muss die MenueRadioTaste einem MenueRadioBehaelter hinzugefuegt werden.<BR/>
 * Alle Komponenten eines MenueRadioBehaelters deaktivieren bei Selektion alle
 * anderen Komponenten des MenueRadioBehaelters. <BR/>
 * 
 * @author Witt
 * 
 */
public class MenueRadioBehaelter {
	ButtonGroup group;

	public MenueRadioBehaelter() {
		group = new ButtonGroup();
	}

	public void addMenueRadioTaste(MenueRadioTaste rt) {
		group.add((JRadioButtonMenuItem) (rt.getMenueItem()));
	}

}
