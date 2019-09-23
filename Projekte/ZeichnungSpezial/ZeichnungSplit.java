//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * @author Witt
 * 
 */
@SuppressWarnings("serial")
public class ZeichnungSplit extends Zeichnung {

	public static JSplitPane parentPane = null;
	public static Zeichenflaeche panelLO; // Links oder oben
	public static Zeichenflaeche panelRU; // Rechts oder unten

	public ZeichnungSplit() {
	}

	public ZeichnungSplit(String title) {
		super(title);
	}

	public ZeichnungSplit(String title, boolean mitRaster) {
		super(title, mitRaster);
	}
	
	public ZeichnungSplit(String title,  int breite , int hoehe ) {
		super(title );
		setzeFenstergroesse(breite, hoehe);
	}

	@Override
	public void erzeugeFenster() {
		// this.getContentPane().add(panel);

		panelLO = new Zeichenflaeche();
		panelRU = new Zeichenflaeche();

		parentPannel = new JPanel();
		parentPannel.setLayout(new BorderLayout());

		parentPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);

		parentPane.setDividerLocation(150);
		parentPane.setOneTouchExpandable(true);
		parentPane.setLeftComponent(panelLO);
		parentPane.setRightComponent(panelRU);

		parentPannel.add(parentPane, BorderLayout.CENTER);

		this.getContentPane().add(parentPannel);

		setExtendedState(Frame.NORMAL);
		setResizable(true);
		setVisible(true);
		// Damit immer der gleiche Frame angesprochen wird, unabhaengig vom der
		// Erzeugung ueber new oder gibJFrame
		panel = panelRU;
		single = this;
	}

	public static void setzeToolbar(BasisComponente componente) {
		((Zeichnung)Zeichnung.gibJFrame()).parentPannel.add(componente,BorderLayout.NORTH);	
	}

	
	public static IContainer getBehaelterLO() {
		return panelLO;
	}

	public static IContainer getBehaelterRU() {
		return panelRU;
	}

	public static void hinzufuegenLO(IComponente comp) {
		panelLO.add(comp.getBasisComponente(), 0);
	}

	public static void hinzufuegenRU(IComponente comp) {
		panelRU.add(comp.getBasisComponente(), 0);
	}

	public static void expandLO() {
		panelLO.expand();
	}

	public static void expandRU() {
		panelRU.expand();
	}

	public static void xyLO() {
		panelLO.xy();
	}

	public static void xyRU() {
		panelRU.xy();
	}

	public static void splitHorizontal() {
		parentPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	}

	public static void splitVertikal() {
		parentPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	}

	public static void setDividerLocation(double proportionalLocation) {
		parentPane.setDividerLocation(proportionalLocation);
	}

	public static void setDividerLocation(int location) {
		parentPane.setDividerLocation(location);

	}

	public static void setDividerSize(int newSize) {
		parentPane.setDividerSize(newSize);
	}

	public static void setResizeWeight(double value) {
		parentPane.setResizeWeight(value);
	}

	public static void setOneTouchExpandable(boolean newValue) {
		parentPane.setOneTouchExpandable(newValue);
	}

}
