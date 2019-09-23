

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JComponent;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
//%$JGUIToolbox$%//ID fuer Toolboxdateien



/**
 * <h1>Zeichnung</h1>: Die Klasse Zeichnung ist ein JFrame zur Aufnahme von
 * Graphikobjekten<br />
 * 
 * Normalerweise braucht man sich um die Klasse Zeichnung nicht zu k&uuml;mmern. <br />
 * Sie wird von den Komponenten automatisch erzeugt.<br />
 * <br />
 * 
 * Wichtig: <b>Die statische Methode setzeFenstergroesse(...)</b> aendert die
 * Breite und Hoehe des Fensters<br />
 * Wenn es das Objekt noch nicht gibt, wird es erzeugt.<br />
 * <br />
 * Hilfreich: <b>Die statische Methode setzeRasterEin() bzw.
 * setzeRasterAus()</b> erzeugt/l&ouml;scht ein Raster auf der
 * Zeichenfl&auml;che.<br />
 * setzeDeltaX/setzeDeltaY setzen das Rastermass<br />
 * Diese Methoden sind hilfreich beim Designen von Oberflaechen.<br/>
 * <br />
 * 
 * Die sichbaren Attribute und Methoden wurden den BlueJ-Beispiel Shapes
 * nachempfunden.<br />
 * <br />
 * 
 * Die statische Methoden gibJFrame und gibPanel geben Referenzen auf das JPanel
 * in dem JFrame zurueck<br />
 * Wenn es den JFrame und das JPanel noch nicht gibt, werden sie erzeugt.<br />
 * <hr>
 * 
 * @author Hans Witt
 * @version Version 1.2: Raster bei der Zeichenflaeche eingefuehrt<br />
 * 
 *          Interface ITuWas geaendert s.u.<br />
 * 
 *          Version 2.0: <br />
 *          Zoom fuer Behaelter eingefuehrt<br />
 *          Anpassung bei BasisComponente fuer Wechseln zwischen Behaeltern<br />
 *          Interface IComponente. Alle Klassen, die zum einem Behaelter
 *          nachtraeglich hinzugefuegt werden koennen,<br />
 *          muessen das Interface IComponente haben <br />
 * 
 *          Version 3.0: ( 01.03.2009) Zeichenfenster in Scrollpane eingebettet
 * 
 *          Version 4.0: Experimentel: Destruktor fuer Graphikelemente
 *          eingefuehrt
 * 
 */
@SuppressWarnings("serial")
public class Zeichnung extends JFrame {

	public static Zeichenflaeche panel;
	public JPanel parentPannel = null;
	public static JScrollPane parentPane = null;

	/**
	 * Experimentell: Bei true werden mit dem GarbageCollector wird auch
	 * GUIElemente entfernt
	 */
	public static boolean verweistesGUIElementEntfernen = false;

	private static float startFontFroesse = 20;

	public static float getStartFontGroesse() {
		return startFontFroesse;
	}

	public static void setStartFontGroesse(int startfontgroesse) {
		Zeichnung.startFontFroesse = startfontgroesse;
	}

	public static JFrame single;

	private static IExitModul exit = null;

	/**
	 * Exitmodul setzen
	 * 
	 * @param exit
	 */
	public static void setExitmodul(IExitModul exit) {
		Zeichnung.exit = exit;
	}

	public static Zeichenflaeche gibZeichenflaeche() {
		if (panel == null) {
			single = new Zeichnung("BlueJ Graphik-Fenster");
		}
		return panel;
	}

	public static JFrame gibJFrame() {
		if (single == null) {
			single = new Zeichnung("BlueJ Graphik-Fenster");
		}
		return single;
	}

	public Zeichnung() {
		// Frame-Initialisierung
		super();
		setTitle("BlueJ-JGUIToolbox");

		addWindowListener(new WindowAdapter() {
			@Override
			@SuppressWarnings("synthetic-access")
			public void windowClosing(WindowEvent evt) {
				boolean schliessen = true;
				if (exit != null) {
					schliessen = exit.exitoptions();
				}
				if (schliessen)
					System.exit(0);
			}
		});

		int frameWidth = 600;
		int frameHeight = 600;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		// Zeichenflaeche zentriert
		// int x = (d.width - getSize().width) / 2;
		// int y = (d.height - getSize().height) / 2;

		// Zeichenflaeche rechtsbuendig
		int x = d.width - getSize().width;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		erzeugeFenster();
		System.gc();
	}

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
		 */
		panel = new Zeichenflaeche();

		parentPannel = new JPanel();
		parentPannel.setLayout(new BorderLayout());
		parentPane = new JScrollPane();

		parentPannel.add(parentPane, BorderLayout.CENTER);
		parentPane.setViewportView(panel);
		this.getContentPane().add(parentPannel);

		setExtendedState(Frame.NORMAL);
		setResizable(true);
		setVisible(true);
		// Damit immer der gleiche Frame angesprochen wird, unabhaengig vom der
		// Erzeugung ueber new oder gibJFrame
		single = this;
		setzeScrollbar(true);
	}

	public static void setzeScrollbar(boolean scrollbar) {

		if (single == null)
			Zeichnung.gibJFrame();
	
		if (scrollbar) {
			parentPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			parentPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		} else {
			parentPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			parentPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		}
		
		panel.setzeScrollbar(scrollbar);
	}

	public static void setzeToolbar(BasisComponente componente) {
		parentPane.setColumnHeaderView(componente);
	}
	
	public static void setzeStatusbar(BasisComponente componente) {
		Zeichnung.gibZeichenflaeche().parentPannel.add(componente, BorderLayout.SOUTH);
	}

	public Zeichnung(String title) {
		this();
		setTitle(title);
	}

	public Zeichnung(String title, boolean mitRaster) {
		this();
		setTitle(title);
		if (mitRaster) {
			setzeRasterEin();
		} else {
			setzeRasterAus();
		}

	}

	public static void setzeFenstergroesse(int breite, int hoehe) {

		JFrame frame = gibJFrame();
		frame.setSize(breite, hoehe);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		// Zeichenflaeche zentriert
		// int x = (d.width - getSize().width) / 2;
		// int y = (d.height - getSize().height) / 2;

		// Zeichenflaeche rechtsbuendig
		int x = d.width - frame.getSize().width;
		int y = (d.height - frame.getSize().height) / 2;
		frame.setLocation(x, y);
	}

	public static void setzeFenstergroesseZentriert(int breite, int hoehe) {

		JFrame frame = gibJFrame();
		frame.setSize(breite, hoehe);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		// Zeichenflaeche zentriert
		int x = (d.width - frame.getSize().width) / 2;
		int y = (d.height - frame.getSize().height) / 2;

		frame.setLocation(x, y);
	}

	public static void setzeTitel(String title) {
		gibJFrame().setTitle(title);
	}

	public static void setzeIcon(BilddateiAbstrakt bild) {
		gibJFrame().setIconImage(bild.leseBild());
	}

	public static void maximiere() {

		gibJFrame().setExtendedState(Frame.MAXIMIZED_BOTH);
	}

	public static void setzeRasterEin() {
		Zeichenflaeche.mitRaster = true;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	public static void setzeRasterAus() {
		Zeichenflaeche.mitRaster = false;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	public static void expand() {
		Zeichnung.gibZeichenflaeche().expand();
	}

	public static void xy() {
		Zeichnung.gibZeichenflaeche().xy();
	}

	/**
	 * Setze Rasterweite horizontal
	 * 
	 * @param deltaX
	 */
	public static void setzeDeltaX(int deltaX) {
		Zeichenflaeche.deltaX = deltaX;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	/**
	 * Setze Rasterweite vertikal
	 * 
	 * @param deltaY
	 */
	public static void setzeDeltaY(int deltaY) {
		Zeichenflaeche.deltaY = deltaY;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

}

//%$JGUIToolbox$%//ID fuer Toolboxdateien



/**
 * Die Zeichenflaeche ist BasisContainer fuer zeichnung bzw. davon abgeleitete
 * Klassen
 * 
 * @author Witt
 * 
 */
@SuppressWarnings("serial")
class Zeichenflaeche extends JPanel implements IContainer,
MouseListener, MouseMotionListener, MouseWheelListener {

	public static final int GROESSEANDERN = 0;
	public static final int BEWEGEN = 1;
	public static final int ANZEIGEN = 2;
	public static final int VERBERGEN = 3;

	public int breite = 100;
	public int hoehe = 100;
	public boolean scrollable = false;
	public Zeichenflaeche parent = null; // Wenn scrollbar
	// eigentliche
	// Zeichenflaeche, die
	// als Kind eine
	// Zeichenflaeche enthaelt
	public JScrollPane parentPane = null;
	public JPanel parentPannel = null;

	public static boolean mitRaster = false;
	public static int deltaX = 100;
	public static int deltaY = 100;

	public static double zoomFaktor = 1.0;

	public static void setzeMitRaster(boolean mitRaster) {
		Zeichenflaeche.mitRaster = mitRaster;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	public static void setzeDeltaX(int deltaX) {
		Zeichenflaeche.deltaX = deltaX;
	}

	public static void setzeDeltaY(int deltaY) {
		Zeichenflaeche.deltaY = deltaY;
	}

	// zum Verschieben bei Borderlayout
	Zeichenflaeche tp = null;

	public Zeichenflaeche() {
		this.setLayout(null);
	}

	public void expand() {
		this.setLayout(new BorderLayout());
	}

	public void xy() {
		this.setLayout(null);
	}

	public void verschiebeNord(IComponente comp) {
		if (tp == null)
			tp = new Zeichenflaeche();
		comp.getBasisComponente().ausContainerEntfernen();
		tp.add(comp.getBasisComponente(), 0);
		validate();
		add(comp.getBasisComponente(), BorderLayout.NORTH);
	}

	public void verschiebeOst(IComponente comp) {
		if (tp == null)
			tp = new Zeichenflaeche();
		comp.getBasisComponente().ausContainerEntfernen();
		tp.add(comp.getBasisComponente(), 0);
		validate();
		add(comp.getBasisComponente(), BorderLayout.EAST);
	}

	public void verschiebeSued(IComponente comp) {
		if (tp == null)
			tp = new Zeichenflaeche();
		comp.getBasisComponente().ausContainerEntfernen();
		tp.add(comp.getBasisComponente(), 0);
		validate();
		add(comp.getBasisComponente(), BorderLayout.SOUTH);
	}

	public void verschiebeWest(IComponente comp) {
		if (tp == null)
			tp = new Zeichenflaeche();
		comp.getBasisComponente().ausContainerEntfernen();
		tp.add(comp.getBasisComponente(), 0);
		validate();
		add(comp.getBasisComponente(), BorderLayout.WEST);
	}

	public enum Richtung {
		NORD, OST, SUED, WEST, ZENTRAL
	}

	Richtung ausrichtung = Richtung.ZENTRAL;

	public void Zentral() {
		ausrichtung = Richtung.ZENTRAL;
	}

	public void Nord() {
		ausrichtung = Richtung.NORD;
	}

	public void Ost() {
		ausrichtung = Richtung.OST;
	}

	public void Sued() {
		ausrichtung = Richtung.SUED;
	}

	public void West() {
		ausrichtung = Richtung.WEST;
	}

	/**
	 * Fuer Interface IContainer
	 */
	@Override
	public Component add(Component comp, int index) {
		switch (ausrichtung) {
		case ZENTRAL:
			return super.add(comp, index);
		case NORD:
			super.add(comp, BorderLayout.NORTH);
			return comp;
		case OST:
			super.add(comp, BorderLayout.EAST);
			return comp;
		case SUED:
			super.add(comp, BorderLayout.SOUTH);
			return comp;
		case WEST:
			super.add(comp, BorderLayout.WEST);
			return comp;
		default:
			return super.add(comp, index);
		}
	}

	public void scrollenAnpassen(int x, int y, int width, int height) {
		boolean anpassen = false;
		if ((x + width) > breite) {
			breite = x + width;
			anpassen = true;
		}
		if ((y + height) > hoehe) {
			hoehe = y + height;
			anpassen = true;
		}
		if (anpassen) {
			setPreferredSize(new Dimension(breite, hoehe));
			((JScrollPane) this.getParent().getParent()).validate();
		}
	}

	public void setzeScrollbar(boolean scrollbar) {
		scrollable = scrollbar;
	}

	public void setzeSichtbarkeit(boolean sichtbar) {
		this.getParent().setVisible(sichtbar);
	}

	@Override
	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
			int width, int height) {
		obj.setBounds(x, y, width, height);
		if (scrollable)
			scrollenAnpassen(x, y, width, height);
		repaint();
		// Zeichnung.gibJFrame().validate();
//		Zeichnung.gibZeichenflaeche().validate();
	}

	@Override
	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		obj.setSize(width, height);
		if (scrollable)
			scrollenAnpassen(obj.getX(), obj.getY(), width, height);
		obj.repaint();
		repaint();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	@Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		if (scrollable)
			scrollenAnpassen(x, y, obj.getWidth(), obj.getHeight());
		obj.setLocation(x, y);
		obj.repaint();
		repaint();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */

	public void paintComponentSpezial(Graphics g) {
		if (mitRaster) {
			Graphics2D g2 = (Graphics2D) g;
			// Graphik-Abmessungen
			int breite = getSize().width - 1;
			int hoehe = getSize().height - 1;
			Color farbe = StaticTools.getColor("schwarz");
			g.setColor(farbe);

			int hor = deltaX;
			while (hor < breite) {
				g2.drawLine(hor, 0, hor, hoehe);
				hor += deltaX;
			}

			int ver = deltaY;
			while (ver < hoehe) {
				g2.drawLine(0, ver, breite, ver);
				ver += deltaY;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintComponentSpezial(g);
	}

	// Zoom fuer Zeichenflaeche
	public double getBehaelterZoom() {
		return zoomFaktor;
	}

	// Zoom fuer Zeichenflaeche
	@Override
	public double setzeZoomfaktor(double zf) {
		zoomFaktor = zf;
		return zoomFaktor;
	}

	// Zur Kommunikation zwischen Objekten
	ITuWas linkObjBasis; // Link auf das zu benachrichtigende Objekt
	int idBasis = 0; // ID der Komponente. Fuer Callback wichtig

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param linkObjBasis
	 * @param IDBasis
	 */
	public void setzeLinkBasis(ITuWas linkObjBasis, int IDBasis) {
		setzeIDBasis(IDBasis);
		setzeLinkBasis(linkObjBasis);
	}

	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param IDBasis
	 */
	public void setzeIDBasis(int IDBasis) {
		idBasis = IDBasis;
	}

	ComponentListener cl = null;

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * 
	 * @param newlinkObjBasis
	 */
	public void setzeLinkBasis(ITuWas newlinkObjBasis) {
		if (cl != null) {
			removeComponentListener(cl);
		}

		if (newlinkObjBasis != null) {
			cl = new ComponentListener() {

				@Override
				public void componentShown(ComponentEvent e) {
					if (linkObjBasis != null) {
						{
							new Thread(new Runnable() {
								@Override
								public void run() {
									linkObjBasis.tuWas(idBasis
											+ Zeichenflaeche.ANZEIGEN);
								}
							}).start();
						}
					}
				}

				@Override
				public void componentResized(ComponentEvent e) {
					if (linkObjBasis != null) {
						{
							new Thread(new Runnable() {
								@Override
								public void run() {
									linkObjBasis.tuWas(idBasis
											+ Zeichenflaeche.GROESSEANDERN);
								}
							}).start();
						}
					}
				}

				@Override
				public void componentMoved(ComponentEvent e) {
					if (linkObjBasis != null) {
						{
							new Thread(new Runnable() {
								@Override
								public void run() {
									linkObjBasis.tuWas(idBasis
											+ Zeichenflaeche.BEWEGEN);
								}
							}).start();
						}
					}
				}

				@Override
				public void componentHidden(ComponentEvent e) {
					if (linkObjBasis != null) {
						{
							new Thread(new Runnable() {
								@Override
								public void run() {
									linkObjBasis.tuWas(idBasis
											+ Zeichenflaeche.VERBERGEN);
								}
							}).start();
						}
					}
				}
			};
			addComponentListener(cl);
		}

		this.linkObjBasis = newlinkObjBasis;
	}

	//*************************************** Mausereignisse *************************************
	 ITuWas linkObj;
	 int id = 0; // Basis-ID der Komponente fuer Callback

	
	public void setzeLink(ITuWas linkObj, int ID) {
		this.linkObj = linkObj;
		id = ID;
	}

	public static final int CLICK = 0; // ID = 0
	public static final int PRESS = 1; // ID = 1
	public static final int RELEASE = 2; // ID = 2
	public static final int ENTER = 3; // ID = 3
	public static final int EXIT = 4; // ID = 4
	public static final int DRAGGED = 5; // ID = 5
	public static final int MOVED = 6; // ID = 6
	public static final int WHEEL = 7; // ID = 7
	
	private int mouseAction = 0;

	Thread thread = null;
	private boolean action = false;
	private int mX = 0;
	private int mY = 0;
	private int click = 0;
	private int button = 0;
	private boolean shift = false;
	private boolean ctrl = false;
	private boolean alt = false;
	private int rotation = 0;

	synchronized public void tuWas(int ID, MouseEvent e) {
		if ((ID == DRAGGED) || (ID == MOVED)
				|| (ID == WHEEL)) {
			if ((thread != null) && thread.isAlive()) {
				return;
			}
		}

		final int IDrunnable = ID;
		action = true;
		mX = e.getX();
		mY = e.getY();
		click = e.getClickCount();
		button = e.getButton();
		shift = e.isShiftDown();
		ctrl = e.isControlDown();
		alt = e.isAltDown();
		if (linkObj != null) {
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					linkObj.tuWas(id + IDrunnable);
				}
			});
			thread.start();
		}
	}

	public void setMouseAction(int action) {
		mouseAction = action;
		if ((mouseAction & 0x1F) != 0) {
			addMouseListener(this);
		} else {
			removeMouseListener(this);
		}
		if ((mouseAction & 0x30) != 0) {
			addMouseMotionListener(this);
		} else {
			removeMouseMotionListener(this);
		}
		if ((mouseAction & (1 << WHEEL)) != 0) {
			addMouseWheelListener(this);
		} else {
			removeMouseWheelListener(this);
		}
	}
	
	public int getMouseAction() {
		return mouseAction;
	}

	public void mouseClicked(MouseEvent e) {
		if ((mouseAction & (1 << CLICK)) != 0) {
			tuWas(CLICK, e);
		}
	}

	public void mousePressed(MouseEvent e) {
		if ((mouseAction & (1 << PRESS)) != 0) {
			tuWas(PRESS, e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if ((mouseAction & (1 << RELEASE)) != 0) {
			tuWas(RELEASE, e);
		}

	}

	public void mouseEntered(MouseEvent e) {
		if ((mouseAction & (1 << ENTER)) != 0) {
			tuWas(ENTER, e);
		}
	}

	public void mouseExited(MouseEvent e) {
		if ((mouseAction & (1 << EXIT)) != 0) {
			tuWas(EXIT, e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		if ((mouseAction & (1 << DRAGGED)) != 0) {
			tuWas(DRAGGED, e);
		}
	}

	public void mouseMoved(MouseEvent e) {
		if ((mouseAction & (1 << MOVED)) != 0) {
			tuWas(MOVED, e);
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if ((mouseAction & (1 << WHEEL)) != 0) {
			rotation = e.getWheelRotation();
			tuWas(WHEEL, e);
		}
	}

	public int getMX() {
		return mX;
	}

	public int getMY() {
		return mY;
	}

	public int getClickCount() {
		return click;
	}

	public int getButton() {
		return button;
	}

	public boolean getShift() {
		return shift;
	}

	public boolean getCtrl() {
		return ctrl;
	}

	public boolean getAlt() {
		return alt;
	}

	public int getRotation() {
		return rotation;
	}

	public boolean isAction() {
		boolean wert = action;
		action = false;
		return wert;
	}

		
}

//%$JGUIToolbox$%//ID fuer Toolboxdateien



/**
 * Basisklasse, von der alle Komponenten im Framework abgeleitet sind. </br>
 * wird normalerweise vom Anwender nicht direkt verwendet.</br> In der Methode
 * paintComponent(Graphics g) wird die Methode paintComponentSpezial(Graphics g)
 * (abstrakt!) aufgerufen. Die von der Basiskomponente abgeleiteten Komponenten
 * koennen darin ihre Zeichnungen unterbringen.
 */

/**
 * Meldungen fuer folgende Ereignisse
 * 
 * GROESSEANDERN, BEWEGEN, ANZEIGEN, VERBERGEN
 * 
 * Zur Kommunikation zwischen Objekten wird im Unterschied zu den Meldungen
 * spezieller Klassen die Methoden werden fuer alle Objekte die Methoden
 * 
 * setzeLinkBasis(ITuWas newlinkObjBasis)<br/>
 * setzeIDBasis(int IDBasis)<br/>
 * setzeLinkBasis(ITuWas linkObjBasis, int IDBasis)<br/>
 * 
 * eingefuehrt. Rueckmeldung wie ueber den normalen Weg "tuWas(ID)".
 * 
 * 
 */

@SuppressWarnings("serial")
abstract class BasisComponente extends JPanel {
	// Zustand der Komponente

	public Color farbe = StaticTools.leseNormalZeichenfarbe();
	public int breite;
	public int hoehe;
	public int xPos = 0;
	public int yPos = 0;
	public boolean gefuellt = true;
	public boolean sichtbar = true;
	public float fontGroesse = 20;
	public int fontstyle = Font.PLAIN;

	public boolean inViewport = false;

	protected int originalXPos = 0;
	protected int originalYPos = 0;
	protected int originalBreite = 100;
	protected int originalHoehe = 100;
	protected float originalFontGroesse = 20;

	protected int originalVX = 0;
	protected int originalVY = 0;

	protected double zoomFaktor = 1.0;
	protected double bzf = 1; // Behaelterzoomfaktor

	protected Font f;

	/**
	 * Meldungen fuer folgende Ereignisse
	 */
	public static final int GROESSEANDERN = 0;
	public static final int BEWEGEN = 1;
	public static final int ANZEIGEN = 2;
	public static final int VERBERGEN = 3;

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public BasisComponente() {
		setOpaque(false); // Komponenten sind durchsichtig !
		fontGroesse = Zeichnung.getStartFontGroesse();
		f = new Font("Dialog", Font.PLAIN, Math.round(fontGroesse));
	}

	public void expand() {
		this.setLayout(new BorderLayout());
	}

	public void addCenter(JComponent panel) {
		add(panel, BorderLayout.CENTER);

	}

	public void xy() {
		this.setLayout(null);
	}

	protected void setFontsize(float x) {
		originalFontGroesse = x;
		zoomen();
		f = f.deriveFont(fontGroesse);
		// f = new Font("Dialog", Font.PLAIN, fontGroesse);
		setFont(f);
	}

	protected void setFontstyle(int fontstyle) {
		this.fontstyle = fontstyle;
		f = f.deriveFont(fontstyle);
	}

	protected void setFontName(String name) {
		f = new Font(name, fontstyle, Math.round(fontGroesse));
	}

	protected void setzeFontName(String name) {
		setFontName(name);
	}

	// Zur Kommunikation zwischen Objekten
	protected ITuWas linkObj; // Link auf das zu benachrichtigende Objekt
	protected int id = 0; // ID der Komponente. Fuer Callback wichtig

	public void setzeID(int ID) {
		id = ID;
	}

	public void setzeLink(ITuWas linkObj) {
		this.linkObj = linkObj;
	}

	public void setzeLink(ITuWas linkObj, int ID) {
		this.linkObj = linkObj;
		id = ID;
	}

	// zum Ueberschreiben in abgeleiteten Komponenten
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		repaint();
	}

	// zum Ueberschreiben in abgeleiteten Komponenten
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		repaint();
	}

	// zum Ueberschreiben in abgeleiteten Komponenten
	public void setzeSchriftTyp(String fontName) {
		f = new Font(fontName, Font.PLAIN, (int) fontGroesse);
		repaint();
	}

	/**
	 * Komponenten aus Behaelter entfernen
	 */
	public void ausContainerEntfernen() {
		JPanel p = (JPanel) this.getParent();
		if (p == null)
			return;
		p.remove(this);
		p.repaint();
		p.validate();
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	public abstract void paintComponentSpezial(Graphics g);

	public double setzeZoomfaktor(double zf) {
		zoomFaktor = zf;
		bzf = ((IContainer) this.getParent()).getBehaelterZoom();
		fontGroesse = (int) Math.round(originalFontGroesse * zoomFaktor * bzf);
		zoomen();
		setzeSchriftgroesse(fontGroesse);
		if (sichtbar) {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this, xPos, yPos, breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this, xPos, yPos, 0, 0);
		}
		return zoomFaktor;

	}

	public void zommfaktorAnpassen() {
		setzeZoomfaktor(zoomFaktor);
	}

	protected void zoomen() {
		breite = (int) Math.round(originalBreite * zoomFaktor * bzf);
		hoehe = (int) Math.round(originalHoehe * zoomFaktor * bzf);
		xPos = (int) Math.round((originalXPos + originalVX) * zoomFaktor * bzf);
		yPos = (int) Math.round((originalYPos + originalVY) * zoomFaktor * bzf);
		fontGroesse = (float) Math.round(originalFontGroesse * zoomFaktor * bzf);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintComponentSpezial(g);
	}

	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		repaint();
	}

	/**
	 * Mache sichtbar. Wenn es bereits sichtbar ist, tue nichts.
	 */
	public void sichtbarMachen() {
		sichtbar = true;
		((IContainer) this.getParent()).setzeKomponentenGroesse(this, breite, hoehe);
		repaint();
	}

	/**
	 * Mache diesen Kreis unsichtbar. Wenn es bereits unsichtbar ist, tue
	 * nichts.
	 */
	public void unsichtbarMachen() {
		if (sichtbar) {
			sichtbar = false;
			((IContainer) this.getParent()).setzeKomponentenGroesse(this, 0, 0);
			repaint();
		}
	}

	public void fuellen() {
		gefuellt = true;
		repaint();
	}

	public void rand() {
		gefuellt = false;
		repaint();
	}

	public void setzeGroesse(int width, int height) {
		originalBreite = width;
		originalHoehe = height;
		zoomen();
		if (sichtbar) {
			// Fuer Flowlayout eingefuegt
			setPreferredSize(new Dimension(width, height));
			((IContainer) this.getParent()).setzeKomponentenGroesse(this, breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenGroesse(this, 0, 0);
		}
		// setPreferredSize(getSize());
		// Zeichnung.gibZeichenflaeche().setzeGroesse(this, width, height);
		// Zeichnung.gibJFrame().validate();
	}

	public void setzePosition(int x, int y) {
		originalXPos = x;
		originalYPos = y;
		zoomen();
		((IContainer) this.getParent()).setzeKomponentenPosition(this, xPos, yPos);
		// Zeichnung.gibZeichenflaeche().setzePosition(this, x, y);
		// Zeichnung.gibJFrame().validate();
	}

	public void verschieben(int dx, int dy) {
		originalVX = dx;
		originalVY = dy;
		zoomen();
		setzePosition(originalXPos, originalYPos);
	}

	public void setzeDimensionen(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		originalXPos = neuesX;
		originalYPos = neuesY;
		originalBreite = neueBreite;
		originalHoehe = neueHoehe;
		zoomen();
		if (sichtbar) {
			setPreferredSize(new Dimension(neueBreite, neueHoehe));
			if (!inViewport) {
				((IContainer) this.getParent()).setzeKomponentenKoordinaten(this, xPos, yPos, breite, hoehe);
			}
		} else {
			if (!inViewport) {
				((IContainer) this.getParent()).setzeKomponentenKoordinaten(this, xPos, yPos, 0, 0);
			}
		}
		setPreferredSize(getSize());
		repaint();
	}

	/**
	 * Meldungen fuer folgende Ereignisse
	 * 
	 * GROESSEANDERN, BEWEGEN, ANZEIGEN, VERBERGEN
	 * 
	 * Zur Kommunikation zwischen Objekten wird im Unterschied zu den Meldungen
	 * spezieller Klassen die Methoden werden fuer alle Objekte die Methoden
	 * 
	 * setzeLinkBasis(ITuWas newlinkObjBasis)<br/>
	 * setzeIDBasis(int IDBasis)<br/>
	 * setzeLinkBasis(ITuWas linkObjBasis, int IDBasis)<br/>
	 * 
	 * eingefuehrt. Rueckmeldung wie ueber den normalen Weg "tuWas(ID)".
	 * 
	 * 
	 */
	ITuWas linkObjBasis; // Link auf das zu benachrichtigende Objekt
	int idBasis = 0; // ID der Komponente. Fuer Callback wichtig

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param linkObjBasis
	 * @param IDBasis
	 */
	public void setzeLinkBasis(ITuWas linkObjBasis, int IDBasis) {
		setzeIDBasis(IDBasis);
		setzeLinkBasis(linkObjBasis);
	}

	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param IDBasis
	 */
	public void setzeIDBasis(int IDBasis) {
		idBasis = IDBasis;
	}

	ComponentListener cl = null;

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * 
	 * @param newlinkObjBasis
	 */
	public void setzeLinkBasis(ITuWas newlinkObjBasis) {
		if (cl != null) {
			removeComponentListener(cl);
		}

		if (newlinkObjBasis != null) {
			cl = new ComponentListener() {

				@Override
				public void componentShown(ComponentEvent e) {
					if (linkObjBasis != null) {
						{
							new Thread(new Runnable() {
								@Override
								public void run() {
									linkObjBasis.tuWas(idBasis + BasisComponente.ANZEIGEN);
								}
							}).start();
						}
					}
				}

				@Override
				public void componentResized(ComponentEvent e) {
					if (linkObjBasis != null) {
						{
							new Thread(new Runnable() {
								@Override
								public void run() {
									linkObjBasis.tuWas(idBasis + BasisComponente.GROESSEANDERN);
								}
							}).start();
						}
					}
				}

				@Override
				public void componentMoved(ComponentEvent e) {
					if (linkObjBasis != null) {
						{
							new Thread(new Runnable() {
								@Override
								public void run() {
									linkObjBasis.tuWas(idBasis + BasisComponente.BEWEGEN);
								}
							}).start();
						}
					}
				}

				@Override
				public void componentHidden(ComponentEvent e) {
					if (linkObjBasis != null) {
						{
							new Thread(new Runnable() {
								@Override
								public void run() {
									linkObjBasis.tuWas(idBasis + BasisComponente.VERBERGEN);
								}
							}).start();
						}
					}
				}
			};
			addComponentListener(cl);
		}

		this.linkObjBasis = newlinkObjBasis;
	}

	/**
	 * Fuer DragAndDrop Ueber Toolboxobjekt kann eine Verbindung zur
	 * Toolbox-Klasse aufgebaut werden.
	 */
	Object toolboxobjekt = null;

	public Object getToolboxobject() {
		return toolboxobjekt;
	}

	public void setToolboxobject(Object toolboxobjekt) {
		this.toolboxobjekt = toolboxobjekt;
	}

}
//%$JGUIToolbox$%//ID fuer Toolboxdateien

abstract class BilddateiAbstrakt {
	BufferedImage bild;

	public BufferedImage leseBild() {
		return bild;
	}
}

//%$JGUIToolbox$%//ID fuer Toolboxdateien

/**
 * <h1>Interface f&uuml;r die Toolbox-Kommunikation</h1>.<br />
 * Das Interface legt die Callback-Methode fest<br/>
 * <br/>
 * Klassen die Ziel der Meldungen aktiver Komponenten (z.B. Taste) sind,
 * m&uuml;ssen die<br/>
 * <b>Methode public void setzeLink( ITuWas linkObj ) </b><br/>
 * implementieren.<br/>
 * 
 * Mit setzeLink(...) wird der aktiven Komponente das Zielobjekt uebergeben
 * (h&auml;ufig <b>this</b> ).<br/>
 * 
 * Diese ruft dann die Funktion tuWas( int ID) des Zielobjekts auf.<br/>
 * <br/>
 * Jede aktive Komponente besitzt auch die Methode setzeID(int ID). Beim Aufruf
 * von TuWas wird dann die ID &uuml;bertragen, <br/>
 * bei verschiedenen Ereignissen ID + Nr. Nr ist die interne ID der Komponente.
 * <hr>
 * 
 * @author Witt
 * @version : 2 ( 3.8.2008 )
 * 
 */
interface ITuWas {
	/**
	 * <h1>Callback-Methode</h1> Diese Methode wird von der aktiven Komponete
	 * aufgerufen.
	 * 
	 * @param ID
	 *            Parameter, der die Quelle identifiziert. <br/>
	 *            Die BasisID wird der Komponente mit setzeID( int ID)
	 *            uebergeben<br/>
	 *            Die Komponente kann mehrer Ereignisse melden. Die EreignisID
	 *            wird dabei zur BasisID adiert<br/>
	 *            Daher sollte man BasisIDs nicht fortlaufend vergeben, sondern
	 *            z.B. in 10er-Abstaenden
	 */
	public void tuWas(int ID);
}

//%$JGUIToolbox$%//ID fuer Toolboxdateien

/**
 * Das Interface IComponente fordert eine Methode die eine BasisComponente
 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen Container
 * hinzuzufuegen
 */
interface IComponente {
	public BasisComponente getBasisComponente();
}

//%$JGUIToolbox$%//ID fuer Toolboxdateien



/**
 * Das Interface IContainer fordert Methoden zum Aendern der Groesse und
 * Position eingebetteter Komponenten.
 * 
 * @version : 3 ( 4.8.2008 ):
 */
interface IContainer {

	public Component add(Component comp, int index);

	/**
	 * die folgenden Methoden weden von der Basiskomponente aufgerufen obj ist
	 * immer (IContainer) this.getParent() von der Basidkomponente von hier aus
	 * wird die Basiskomponente veraendert
	 */

	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
			int width, int height);

	public void setzeKomponentenGroesse(JComponent obj, int width, int height);

	public void setzeKomponentenPosition(JComponent obj, int x, int y);

	public void validate();

	/**
	 * liefert den Zoomfaktor fuer den Behaelter
	 * 
	 * @return Zoomfaktor
	 */
	public double getBehaelterZoom();

	public double setzeZoomfaktor(double zf);

}
//%$JGUIToolbox$%//ID fuer Toolboxdateien


interface IGraphik {
	public Rectangle getBounds() ;
	public Area getArea();
	public boolean kollisionMit(IGraphik grObject);
	public Rectangle kollisionsgebiet(IGraphik grObject) ;
	public boolean enthaeltPunkt(int x, int y);
	public boolean enthaeltPunkt(Point punkt);
	
}

interface ICGraphik {
	public Area getArea();
}

//%$JGUIToolbox$%//ID fuer Toolboxdateien
/**
 * Zur Bearbeitung von Optinen beim Exit-Befehl
 * 
 * Die Methode exitoptions liefert true, wenn die Anwendung nach Behandlung der
 * Optionen geschlossen wird
 * 
 */

interface IExitModul {
	boolean exitoptions();
}
