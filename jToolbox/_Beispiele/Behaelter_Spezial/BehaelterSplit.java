//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.Graphics;

import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class BehaelterSplit extends BasisComponente implements IComponente {

	public JSplitPane splitPane = null;
	public Zeichenflaeche panelLO; // Links oder oben
	public Zeichenflaeche panelRU; // Rechts oder unten

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public BehaelterSplit() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterSplit(int neueBreite, int neueHoehe) {
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
	public BehaelterSplit(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public BehaelterSplit(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterSplit(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {

		expand();

		panelLO = new Zeichenflaeche();
		panelRU = new Zeichenflaeche();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
		splitPane.setOneTouchExpandable(true);
		splitPane.setLeftComponent(panelLO);
		splitPane.setRightComponent(panelRU);

		addCenter(splitPane);

		behaelter.add(this, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);

		behaelter.validate();
		splitPane.setDividerLocation(0.3);

	}

	@Override
	public BasisComponente getBasisComponente() {
		return this;
	}

	@Override
	public void paintComponentSpezial(Graphics g) {
	}

	public IContainer getBehaelterLO() {
		return panelLO;
	}

	public IContainer getBehaelterRU() {
		return panelRU;
	}

	public void hinzufuegenLO(IComponente comp) {
		panelLO.add(comp.getBasisComponente(), 0);
	}

	public void hinzufuegenRU(IComponente comp) {
		panelRU.add(comp.getBasisComponente(), 0);
	}

	public void expandLO() {
		panelLO.expand();
	}

	public void expandRU() {
		panelRU.expand();
	}

	public void xyLO() {
		panelLO.xy();
	}

	public void xyRU() {
		panelRU.xy();
	}

	public void splitHorizontal() {
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	}

	public void splitVertikal() {
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	}

	public void setDividerLocation(double proportionalLocation) {
		splitPane.setDividerLocation(proportionalLocation);
	}

	public void setDividerLocation(int location) {
		splitPane.setDividerLocation(location);
	}

	public int getDividerLocation() {
		return splitPane.getDividerLocation();
	}

	public void setDividerSize(int newSize) {
		splitPane.setDividerSize(newSize);
	}

	public int getDividerSize() {
		return splitPane.getDividerSize();
	}

	public void setResizeWeight(double value) {
		splitPane.setResizeWeight(value);
	}

	public void setOneTouchExpandable(boolean newValue) {
		splitPane.setOneTouchExpandable(newValue);
	}

	// Eine Moeglichkeit, bei Ableitungen eine Bearbeitungsmehtode zu Ueberschreiben
	// Dazu wird als linkziel auftrag angegeben.
	// Die Methode heist auch auftrag;
	protected ITuWas auftrag = new ITuWas() {
		@Override
		public void tuWas(int ID) {
			final int linkID = ID ;
			new Thread(new Runnable() {
				public void run() {
					ausfuehren(linkID);
				}
			}).start();
		}

	};

	protected void ausfuehren(int ID) {

	}

	
}
