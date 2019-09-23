
//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class BehaelterTablayout extends BasisComponente implements IContainer,
		IComponente, ChangeListener {

	public Zeichenflaeche panel;
	public JTabbedPane parentPane = null;

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public BehaelterTablayout() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterTablayout(int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterTablayout(int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public BehaelterTablayout(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neuesX 
	 * @param neuesY 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterTablayout(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this(behaelter, 0, 0, 100, 50, "neuer Tab", new Zeichenflaeche());
	}

	/**
	 * allgemeiner Konstuktor
	 * @param neuesX 
	 * @param neuesY 
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterTablayout(int neuesX, int neuesY, int neueBreite,
			int neueHoehe, String tabText, Zeichenflaeche inhalt) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, 100, 50, tabText, inhalt);

	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterTablayout(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe, String tabText, Zeichenflaeche inhalt) {

		this.setLayout(new BorderLayout());
		parentPane = new JTabbedPane(JTabbedPane.TOP,
				JTabbedPane.WRAP_TAB_LAYOUT);
		this.add(parentPane, BorderLayout.CENTER);
		behaelter.add(this, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);

		behaelter.validate();

		parentPane.addChangeListener(this);

		addStdTab(tabText, inhalt);
		behaelter.validate();

	}

	@Override
	public BasisComponente getBasisComponente() {
		return this;
	}

	@Override
	public void paintComponentSpezial(Graphics g) {
	}

	/**
	 * Tab-Position Oben
	 */
	public void setzeTabsOben() {
		parentPane.setTabPlacement(JTabbedPane.TOP);
	}

	/**
	 * Tab-Position Unten
	 */
	public void setzeTabsUnten() {
		parentPane.setTabPlacement(JTabbedPane.BOTTOM);
	}

	/**
	 * Tab-Position Links
	 */
	public void setzeTabsLinks() {
		parentPane.setTabPlacement(JTabbedPane.LEFT);
	}

	/**
	 * Tab-Position Rechts
	 */
	public void setzeTabsRechts() {
		parentPane.setTabPlacement(JTabbedPane.RIGHT);
	}

	/**
	 * Zeichnenflaeche als neuen Tab anhaengen</br>
	 * 
	 * @param tabText
	 *            Tab-Beschreibung
	 * @param inhalt
	 *            Zeichenflaeche
	 */
	public void addTab(String tabText, Zeichenflaeche inhalt) {
		parentPane.addTab(tabText, inhalt);
	}

	/**
	 * Tab-Beschreibung auslesen
	 * 
	 * @param index
	 *            Nummer des Tabs (Beginn 0)
	 * @return Tab-Beschreibung
	 */
	public String getTabString(int index) {
		return parentPane.getTitleAt(index);
	}

	/**
	 * Tab-ID zum Beschreibungstext suchen
	 * 
	 * @param title
	 *            Beschreibungstext
	 * @return ID
	 */
	public int getTabID(String title) {
		return parentPane.indexOfTab(title);
	}

	/**
	 * ANzahl der Tabs
	 * 
	 * @return Anzahl
	 */
	public int getTabCount() {
		return parentPane.getTabCount();
	}

	/**
	 * Zeichenflaeche an Stelle index
	 * 
	 * @param index
	 * @return Referenz auf Zeichenflaeche
	 */
	public Zeichenflaeche getZeichenflaeche(int index) {
		if ((parentPane.getTabCount() > 0)
				&& (parentPane.getTabCount() > index)) {
			return (Zeichenflaeche) parentPane.getComponentAt(index);
		} else {
			return null;
		}
	}

	/**
	 * Zeichnenflaeche als neuen Tab einfuegen</br>
	 * 
	 * @param index
	 *            Position
	 * @param tabText
	 *            Tab-Beschreibung
	 * @param inhalt
	 *            Zeichenflaeche
	 */
	public void insertTab(int index, String tabText, Zeichenflaeche inhalt) {
		parentPane.insertTab(tabText, null, inhalt, "", index);
	}

	/**
	 * Tab entfernen
	 * 
	 * @param inhalt
	 *            Zeichenflaeche
	 */
	public void removeTab(Zeichenflaeche inhalt) {
		if (parentPane.getTabCount() > 0)
			parentPane.remove(inhalt);
	}

	/**
	 * Tab an Stelle Index entfernen
	 * 
	 * @param index
	 */
	public void removeTabAtIndex(int index) {
		if ((parentPane.getTabCount() > 0)
				&& (parentPane.getTabCount() > index))
			parentPane.removeTabAt(index);
	}

	public void addStdTab(String tabText, Zeichenflaeche inhalt) {
		parentPane.remove(panel);
		parentPane.addTab(tabText, inhalt);
		panel = inhalt;
	}

	public void aktiviereTab(int index) {
		parentPane.setSelectedIndex(index);
	}

	public void renameTabTitle(int index, String title) {
		parentPane.setTitleAt(index, title);
	}

	// Aufrufe werden auf wird auf Standartflaeche umgeleitet
	@Override
	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
			int width, int height) {
		if (panel != null)
			panel.setzeKomponentenKoordinaten(obj, x, y, width, height);
	}

	@Override
	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		if (panel != null)
			panel.setzeKomponentenGroesse(obj, width, height);
	}

	@Override
	public Component add(Component comp, int index) {
		return panel.add(comp, index);
	}

	@Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		if (panel != null)
			panel.setzeKomponentenPosition(obj, x, y);
	}

	@Override
	public double getBehaelterZoom() {
		return (panel != null) ? panel.getBehaelterZoom() : 1.0;
	}

	public Zeichenflaeche getStdZeichenflaeche() {
		return panel;
	}

	@Override
	public void stateChanged(ChangeEvent changeEvent) {

		JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		int index = sourceTabbedPane.getSelectedIndex();
		if (linkObj != null) {
			linkObj.tuWas(id + index);
		}
	}

}
