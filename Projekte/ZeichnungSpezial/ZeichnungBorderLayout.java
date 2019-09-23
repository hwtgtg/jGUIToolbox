//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ZeichnungBorderLayout extends Zeichnung {

	public static JPanel parentPane = null;
	public static Zeichenflaeche panelBorderLayout; // Links oder oben

	public static boolean isInit() {
		return (single==null);
	}
	
	public ZeichnungBorderLayout() {
		super();
	}

	public ZeichnungBorderLayout(String title) {
		super(title);
	}

	public ZeichnungBorderLayout(String title, boolean mitRaster) {
		super(title, mitRaster);
	}

	public ZeichnungBorderLayout(String title, int breite, int hoehe) {
		super(title);
		setzeFenstergroesse(breite, hoehe);
	}

	@Override
	public void erzeugeFenster() {

		/*
		 * Aufbau:
		 * 
		 * Contenpane = JPanel parentPannel -> BorderLayout
		 * 
		 * in parentpanel JScrollPane parentPane
		 * 
		 * in parentPane Viewport Zeichenflaeche panel
		 * 
		 * setzeToolbar setzt parentPane.setColumnHeaderView(...);
		 * 
		 * 
		 * Standart-Zeichnen mit gibZeichenflaeche in panel
		 * 
		 * 
		 * Hier wird statt panel panelBorderLayout in parentPane angezeigt
		 */

		panelBorderLayout = new Zeichenflaeche();

		parentPannel = new JPanel();
		parentPannel.setLayout(new BorderLayout());

		parentPane = parentPannel;
		// parentPannel.add(panelBorderLayout, BorderLayout.CENTER);

//		System.out.println(this.getContentPane().getComponentCount());
		this.getContentPane().add(parentPannel);

		setExtendedState(Frame.NORMAL);
		setResizable(true);
		setVisible(true);

		// Damit immer der gleiche Frame angesprochen wird, unabhaengig vom der
		// Erzeugung ueber new oder gibJFrame
		// Dummy fuer Normale Zeichenflaeche.
		// wg. Boderlayout darf nur einmal geziehlt eine Komponente zentral
		// eingefuehrt werden.
		// Nachfolgende Aktionen ueberschreiben vorher gesetzte Objekte
		panel = new Zeichenflaeche();
		single = this;
	}

	public static void hinzufuegen(IComponente comp) {
		panelBorderLayout.add(comp.getBasisComponente(), 0);
	}

	public static void verschiebeNord(IComponente comp) {
		comp.getBasisComponente().ausContainerEntfernen();
		parentPane.add(comp.getBasisComponente(), BorderLayout.NORTH);
	}

	public static void verschiebeOst(IComponente comp) {
		comp.getBasisComponente().ausContainerEntfernen();
		parentPane.add(comp.getBasisComponente(), BorderLayout.EAST);
	}

	public static void verschiebeSued(IComponente comp) {
		comp.getBasisComponente().ausContainerEntfernen();
		parentPane.add(comp.getBasisComponente(), BorderLayout.SOUTH);
	}

	public static void verschiebeWest(IComponente comp) {
		comp.getBasisComponente().ausContainerEntfernen();
		parentPane.add(comp.getBasisComponente(), BorderLayout.WEST);
	}

	public static void setzeZentrum(IComponente comp) {
		comp.getBasisComponente().ausContainerEntfernen();
		parentPane.add(comp.getBasisComponente());
	}

}
