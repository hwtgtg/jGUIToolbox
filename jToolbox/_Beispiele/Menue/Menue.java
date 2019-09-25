
//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.Font;

import javax.swing.JMenu;

/**
 * <h1>Menue</h1> Die Menue-Komponente. Sie nimmt weitere MenueeEintraege auf.<BR/>
 * Die Menue-Komponente ist von MenueEintrag abgeleitet. Die Methoden von
 * MenueEintrag gelten auch fuer Menue.<BR/>
 * Die Schnellzugriffstaste ist fuer Menues sinnlos. Sie hat keine Auswirkung. <BR/>
 * 
 * @author Witt
 * 
 */
public class Menue extends MenueEintrag {

	protected Menue() {

	}

	public Menue(String text) {
		menueEintrag = new JMenu(text);
	}

	public Menue(String text, Font font) {
		menueEintrag = new JMenu(text);
		menueEintrag.setFont(font);
	}

	@Override
	public void setzeAktivierung(String text) {
		// Nicht moeglich
	}

	public void adSeparator() {
		((JMenu) menueEintrag).addSeparator();
	}

	public void entfernePosition(int pos) {
		((JMenu) menueEintrag).remove(pos);
	}

	public void menueEintragHinzufuegen(MenueEintrag eintrag) {
		((JMenu) menueEintrag).add(eintrag.getMenueItem());
	}

	public void menueEintragEinfuegen(MenueEintrag eintrag, int pos) {
		((JMenu) menueEintrag).insert(eintrag.getMenueItem(), pos);
	}

}
