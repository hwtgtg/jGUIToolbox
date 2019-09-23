//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Witt
 * 
 */
@SuppressWarnings("serial")
public class ZeichnungTab extends Zeichnung {

	public static JTabbedPane parentPane = null;

	public ZeichnungTab() {
	}

	public ZeichnungTab(String title) {
		super(title);
	}

	public ZeichnungTab(String title, boolean mitRaster) {
		super(title, mitRaster);
	}

	@Override
	public void erzeugeFenster() {

		panel = new Zeichenflaeche();

		parentPannel = new JPanel();
		parentPannel.setLayout(new BorderLayout());

		parentPane = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.WRAP_TAB_LAYOUT);

		// parentPane.addTab("Tab", panel);

		parentPannel.add(parentPane, BorderLayout.CENTER);

		this.getContentPane().add(parentPannel);

		setExtendedState(Frame.NORMAL);
		setResizable(true);
		setVisible(true);
		// Damit immer der gleiche Frame angesprochen wird, unabhaengig vom der
		// Erzeugung ueber new oder gibJFrame
		single = this;
		parentPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (linkObj != null) {
					linkObj.tuWas(id + parentPane.getSelectedIndex());
				}
			}
		});

		Zeichenflaeche z = new Zeichenflaeche();
		addStdTab("neuer Tab", z);
		validate();
	}

	// Zur Kommunikation zwischen Objekten
	ITuWas linkObj; // Link auf das zu benachrichtigende Objekt
	int id = 0; // ID der Komponente. Fuer Callback wichtig

	public static void setzeID(int ID) {
		if (single != null) {
			((ZeichnungTab) single).id = ID;
		}
	}

	public static void setzeLink(ITuWas linkObj) {
		if (single != null) {
			((ZeichnungTab) single).linkObj = linkObj;
		}

	}

	public static void setzeLink(ITuWas linkObj, int ID) {
		if (single != null) {
			((ZeichnungTab) single).linkObj = linkObj;
			((ZeichnungTab) single).id = ID;
		}
	}

	public static void setzeTabsOben() {
		parentPane.setTabPlacement(JTabbedPane.TOP);
	}

	public static void setzeTabsUnten() {
		parentPane.setTabPlacement(JTabbedPane.BOTTOM);
	}

	public static void setzeTabsLinks() {
		parentPane.setTabPlacement(JTabbedPane.LEFT);
	}

	public static void setzeTabsRechts() {
		parentPane.setTabPlacement(JTabbedPane.RIGHT);
	}

	public static String getTabString(int index) {
		return parentPane.getTitleAt(index);
	}

	public static int getTabID(String title) {
		return parentPane.indexOfTab(title);
	}

	public static void addTab(String tabText, Zeichenflaeche inhalt) {
		parentPane.addTab(tabText, inhalt);
	}

	public static void insertTab(int index, String tabText,
			Zeichenflaeche inhalt) {
		parentPane.insertTab(tabText, null, inhalt, "", index);
	}

	public static void removeTab(Zeichenflaeche inhalt) {
		if (parentPane.getTabCount() > 0)
			parentPane.remove(inhalt);
	}

	public static void addStdTab(String tabText, Zeichenflaeche inhalt) {
		parentPane.remove(panel);
		parentPane.addTab(tabText, inhalt);
		panel = inhalt;
	}

	public static void aktiviereTab(int index) {
		parentPane.setSelectedIndex(index);
	}

	public static void renameTabTitle(int index, String title) {
		parentPane.setTitleAt(index, title);
	}

	public static Zeichenflaeche getStdZeichenflaeche() {
		return panel;
	}

	public static Zeichenflaeche getZeichenflaeche(int index) {
		if (parentPane.getTabCount() > 0) {
			return (Zeichenflaeche) parentPane.getTabComponentAt(index);
		} else {
			return null;
		}
	}

}
