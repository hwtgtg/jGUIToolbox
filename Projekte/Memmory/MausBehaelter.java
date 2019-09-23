
//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * <h1>MausBehaelter</h1> erweitert den Behaelter.<br />
 * 
 * Ist der Kommunikationslink gesetzt, meldet das Objekt eine
 * <b><i>Mausereignisse</i></b>.<br/>
 * <br/>
 * Die zu signalisierenden Ereignisse m&uuml;ssen aktiviert werden<br/>
 * Siehe Konstanten und setzeMausXXX... <br/>
 * <br/>
 * 
 * Der MausBehaelter nimmt GUI-Elemente auf<br/>
 * Diese werden wie beim Behaelter relativ zum Container positioniert. Siehe
 * Behaelter<br/>
 * <hr>
 * 
 * @author Hans Witt
 * 
 * 
 * @version Version: 3 (9.8.2008) Containerklasse fuer GUI-Elemente<br/>
 *          Version: 3.1 (14.8.2008) Konstruktor auf int neuesX, int neuesY ,
 *          int neueBreite, int neueHoehe angepasst <br/>
 *          Version 5.0: (4.9.2010 Entfernen fuer Graphikkomponente eingefuehrt
 *          <br/>
 * 
 *          Destruktor entfernt Graphikkomponente automatisch bei gc()<br/>
 * 
 *          Version 6 : Bei MausBehaelter.DRAGGED, MausBehaelter.MOVED und
 *          MausBehaelter.WHEEL <br/>
 *          Verwerfen der Interrupts bis vorheriger Thread beendet ist.<br/>
 * 
 */
public class MausBehaelter implements IContainer, IComponente {

	public static final int CLICK = 0; // ID = 0
	public static final int PRESS = 1; // ID = 1
	public static final int RELEASE = 2; // ID = 2
	public static final int ENTER = 3; // ID = 3
	public static final int EXIT = 4; // ID = 4
	public static final int DRAGGED = 5; // ID = 5
	public static final int MOVED = 6; // ID = 6
	public static final int WHEEL = 7; // ID = 7

	protected CMausBehaelter obj;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;

	public boolean sichtbar = true;

	public double zoomInhalt = 1;

	public String anzeigeText = "Anzeige";
	public int fontGroesse = -1;
	public String farbe = "schwarz";
	public String hintergrundFarbe = null;

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public MausBehaelter() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public MausBehaelter(int neueBreite, int neueHoehe) {
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
	public MausBehaelter(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public MausBehaelter(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public MausBehaelter(IContainer behaelter, int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		cObjectSetzen();
		if (behaelter == null) {
			behaelter = Zeichnung.gibZeichenflaeche();
		}

		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}

	public void cObjectSetzen() {
		obj = new CMausBehaelter();
	}

	public void setzeBeschreibungstext(String neuerText) {
		anzeigeText = neuerText;
		obj.setText(anzeigeText);
	}

	public void setzeSchriftName(String name) {
		obj.setzeFontName(name);
	}

	public void setzeSchriftgroesse(int neueFontgroesse) {
		fontGroesse = neueFontgroesse;
		obj.setzeSchriftgroesse(fontGroesse);
	}

	public void setzeSchriftStilNormal() {
		obj.setzeSchriftStil(Font.PLAIN);
	}

	public void setzeSchriftStilFett() {
		obj.setzeSchriftStil(Font.BOLD);
	}

	public void setzeSchriftStilKursiv() {
		obj.setzeSchriftStil(Font.ITALIC);
	}

	public void setzeFarbe(String neueFarbe) {
		farbe = neueFarbe;
		obj.setzeSchriftFarbe(farbe);
	}

	public void setzeHintergrundfarbe(String neueFarbe) {
		hintergrundFarbe = neueFarbe;
		obj.setzeHintergrundfarbe(hintergrundFarbe);
	}

	public void setzeMitRand(boolean mitRand) {
		obj.setzeMitRand(mitRand);
	}

	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen
	 * Behaelter hinzuzufuegen
	 */
	@Override
	public BasisComponente getBasisComponente() {
		return obj;
	}

	/**
	 * fuegt eine Komponente der Toolbox zu Behaelter
	 * 
	 * @param comp
	 */
	public void hinzufuegen(IComponente comp) {
		comp.getBasisComponente().ausContainerEntfernen();
		obj.add(comp.getBasisComponente(), 0);
	}

	public void hinzufuegenUndAnpassen(IComponente comp) {
		comp.getBasisComponente().ausContainerEntfernen();
		obj.add(comp.getBasisComponente(), 0);
		comp.getBasisComponente().verschieben(-xPos, -yPos);
	}

	@Override
	public double setzeZoomfaktor(double zf) {
		zoomInhalt = obj.setzeZoomfaktor(zf);
		return zoomInhalt;
	}

	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param ID
	 */
	public void setzeID(int ID) {
		obj.setzeID(ID);
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * 
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj) {
		obj.setzeLink(linkObj);
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param linkObj
	 * @param ID
	 */
	public void setzeLink(ITuWas linkObj, int ID) {
		obj.setzeLink(linkObj, ID);
	}

	/**
	 * MauseEreignisse zuruecksetzen
	 */
	public void ruecksetzenMaus() {
		obj.setMouseAction(0);
	}

	/**
	 * Aktiviert MausClickEreignis
	 */
	public void setzeMausClick() {
		obj.setMouseAction(obj.getMouseAction() | (1 << CLICK));
	}

	/**
	 * Aktiviert MausPress- und -ReleaseEreignis
	 */
	public void setzeMausPressRelease() {
		obj.setMouseAction(obj.getMouseAction() | (1 << PRESS) | (1 << RELEASE));
	}

	/**
	 * Betreten und Verlassen des Behaelters: <br/>
	 * Aktiviert MausEnter und -ExitEreignis
	 */
	public void setzeMausEnterExit() {
		obj.setMouseAction(obj.getMouseAction() | (1 << ENTER) | (1 << EXIT));
	}

	/**
	 * Aktiviert MausBewegung ohne und mit gedr&uuml;ckter Maustaste
	 */
	public void setzeMausDraggedMoved() {
		obj.setMouseAction(obj.getMouseAction() | (1 << DRAGGED) | (1 << MOVED));
	}

	/**
	 * Aktiviert MausRadEreignis
	 */
	public void setzeMausRad() {
		obj.setMouseAction(obj.getMouseAction() | (1 << WHEEL));
	}

	/**
	 * Aktiviert MausEreignisse
	 * 
	 * @param ereignisse
	 *            Der Parametere ereignisse ist ein Mathematische oder "|" aus
	 * 
	 *            <pre>
	 *         1 << MausBehaelter.CLICK
	 *         1 << MausBehaelter.PRESS
	 *         1 << MausBehaelter.RELEASE
	 *         1 << MausBehaelter.ENTER
	 *         1 << MausBehaelter.EXIT
	 *         1 << MausBehaelter.DRAGGED
	 *         1 << MausBehaelter.MOVED
	 *         1 << MausBehaelter.WHEEL
	 *            </pre>
	 * 
	 *            Keine Kontrolle ueber zulaessige Werte !
	 */
	public void setzeMausereignisse(int ereignisse) {
		obj.setMouseAction(ereignisse);
	}

	/**
	 * Aktiviert alle Mausereignisse
	 */
	public void setzeAlleMausereignisse() {
		obj.setMouseAction((1 << MausBehaelter.CLICK) | (1 << MausBehaelter.PRESS) | (1 << MausBehaelter.RELEASE)
				| (1 << MausBehaelter.ENTER) | (1 << MausBehaelter.EXIT) | (1 << MausBehaelter.DRAGGED)
				| (1 << MausBehaelter.MOVED) | (1 << MausBehaelter.WHEEL));
	}

	/**
	 * Status der Komponente abrufen und ruecksetzen
	 */
	public boolean mausAktion() {
		return obj.isAction();
	}

	public int getMX() {
		return obj.getMX();
	}

	public int getMY() {
		return obj.getMY();
	}

	public int getClickCount() {
		return obj.getClickCount();
	}

	public int getButton() {
		return obj.getButton();
	}

	public boolean getShift() {
		return obj.getShift();
	}

	public boolean getCtrl() {
		return obj.getCtrl();
	}

	public boolean getAlt() {
		return obj.getAlt();
	}

	public int getRotation() {
		return obj.getRotation();
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	/**
	 * Groesse des Anzeigefelds aendern *
	 */
	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeGroesse((int) (breite / zoomInhalt), (int) (hoehe / zoomInhalt));
	}

	/**
	 * neue Position
	 * 
	 * @param neuesX
	 * @param neuesY
	 */
	public void setzePosition(int neuesX, int neuesY) {
		xPos = neuesX;
		yPos = neuesY;
		obj.setzePosition((int) (xPos / zoomInhalt), (int) (yPos / zoomInhalt));
	}

	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public void setzeDimensionen(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		xPos = neuesX;
		yPos = neuesY;
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeDimensionen((int) (xPos / zoomInhalt), (int) (yPos / zoomInhalt), (int) (breite / zoomInhalt),
				(int) (hoehe / zoomInhalt));
	}

	public void verschieben(int dx, int dy) {
		setzePosition(xPos + dx, yPos + dy);
	}

	/**
	 * Mache sichtbar.
	 */
	public void sichtbarMachen() {
		sichtbar = true;
		obj.sichtbarMachen();
	}

	/**
	 * Mache unsichtbar.
	 */
	public void unsichtbarMachen() {
		sichtbar = false;
		obj.unsichtbarMachen();
	}

	public void setzeMitRaster(boolean mitRaster) {
		obj.setzeMitRaster(mitRaster);
	}

	public void setzeDeltaX(int deltaX) {
		obj.setzeDeltaX(deltaX);
	}

	public void setzeDeltaY(int deltaY) {
		obj.setzeDeltaY(deltaY);
	}

	public void setzeRasterfarbe(String rasterfarbe) {
		obj.setzeRasterfarbe(rasterfarbe);
	}

	/**
	 * Fuer Interface IContainer
	 */
	@Override
	public Component add(Component comp, int index) {
		return this.obj.add(comp, index);
	}

	@Override
	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y, int width, int height) {
		this.obj.setzeKomponentenKoordinaten(obj, x, y, width, height);
	}

	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		this.obj.setzeKomponentenGroesse(obj, width, height);
	}

	@Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		this.obj.setzeKomponentenPosition(obj, x, y);
	}

	@Override
	public void validate() {
		obj.validate();
	}

	/*
	 * Ende Interface IContainer
	 */
	/**
	 * gibt z.B. fuer den Dialog das JPanel-Objekt zurueck
	 */
	public JPanel getPanel() {
		return obj;
	}

	public double getBehaelterZoom() {
		return obj.getBehaelterZoom();
	}

	/**
	 * Entfernen des Graphikobjekts
	 */
	public void entfernen() {
		if (obj != null) {
			obj.ausContainerEntfernen();
			obj = null;
		}
	}

	/**
	 * Destruktor
	 */
	@Override
	protected void finalize() {
		if (!Zeichnung.verweistesGUIElementEntfernen)
			return;
		if (obj != null)
			entfernen();
	}

	public void expand() {
		obj.expand();
	}

	public void xy() {
		obj.xy();
	}

	public void setzeFokusierbar(boolean wert) {
		obj.setFocusable(wert);
	}

	public void setzeFokus() {
		obj.requestFocus();
	}

}

@SuppressWarnings("serial")
class CMausBehaelter extends BasisComponente
		implements IContainer, MouseListener, MouseMotionListener, MouseWheelListener, Printable {

	private boolean mitRaster = false;
	private int deltaX = 50;
	private int deltaY = 50;

	private Vector<BasisComponente> unterComponenten = new Vector<BasisComponente>();
	private String anzeige = null;
	private boolean mitRand = false;

	int anzeigenbreite = 0;
	int anzeigenhoehe = 0;
	int ascend = 0;

	private String hintergrundFarbe = null;
	Color hfarbe;

	private String rasterfarbe = "schwarz";

	@Override
	public Component add(Component comp, int index) {
		Component erg = super.add(comp, index);
		unterComponenten.addElement((BasisComponente) comp);
		return erg;
	}

	/**
	 * liefert den Zoomfaktor fuer den Behaelter
	 * 
	 * @return
	 */
	public double getBehaelterZoom() {
		try {
			return zoomFaktor * ((IContainer) this.getParent()).getBehaelterZoom();
		} catch (Exception e) {
			return 1.0d;
		}

	}

	@Override
	public double setzeZoomfaktor(double zf) {
		zoomFaktor = zf;
		bzf = ((IContainer) this.getParent()).getBehaelterZoom();
		fontGroesse = (int) Math.round(originalFontGroesse * zoomFaktor * bzf);
		setzeSchriftgroesse(fontGroesse);

		originalXPos = (int) (originalXPos / zoomFaktor);
		originalYPos = (int) (originalYPos / zoomFaktor);

		zoomen();
		for (int i = 0; i < unterComponenten.size(); i++) {
			unterComponenten.get(i).zommfaktorAnpassen();
		}

		if (sichtbar) {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this, xPos, yPos, breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this, xPos, yPos, 0, 0);
		}
		return zoomFaktor;
	}

	public void setzeMitRand(boolean mitRand) {
		this.mitRand = mitRand;
		Zeichnung.gibZeichenflaeche().repaint();
	}

	@Override
	public void setzeFontName(String name) {
		super.setzeFontName(name);
		repaint();
	}

	@Override
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		repaint();
	}

	@Override
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		repaint();
	}

	public void setText(String s) {
		setzeMitRand(true);
		anzeige = s;
	}

	public void setzeSchriftFarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		repaint();
	}

	public void setzeHintergrundfarbe(String neueFarbe) {
		hintergrundFarbe = neueFarbe;
		if (hintergrundFarbe != null)
			hfarbe = StaticTools.getColor(hintergrundFarbe);
		repaint();
	}

	public void setzeMitRaster(boolean mitRaster) {
		this.mitRaster = mitRaster;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	public void setzeDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public void setzeDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	public void setzeRasterfarbe(String rasterfarbe) {
		this.rasterfarbe = rasterfarbe;
	}

	public CMausBehaelter() {
		/*
		 * this.setBorder(BorderFactory.createCompoundBorder(BorderFactory
		 * .createRaisedBevelBorder(), BorderFactory
		 * .createLoweredBevelBorder()));
		 */
		this.setLayout(null);
	}

	// Bereits in Basiscomponente definiert
	// ITuWas linkObj;
	// int id = 0; // Basis-ID der Komponente fuer Callback

	@Override
	public void setzeLink(ITuWas linkObj, int ID) {
		this.linkObj = linkObj;
		id = ID;
	}

	private int mX = 0;
	private int mY = 0;
	private int click = 0;
	private int button = 0;
	private boolean shift = false;
	private boolean ctrl = false;
	private boolean alt = false;
	private int rotation = 0;

	private boolean action = false;

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

	Thread thread = null;

	synchronized public void tuWas(int ID, MouseEvent e) {
		if ((ID == MausBehaelter.DRAGGED) || (ID == MausBehaelter.MOVED) || (ID == MausBehaelter.WHEEL)) {
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
		if ((mouseAction & (1 << MausBehaelter.WHEEL)) != 0) {
			addMouseWheelListener(this);
		} else {
			removeMouseWheelListener(this);
		}
	}

	public int getMouseAction() {
		return mouseAction;
	}

	private int mouseAction = 0;

	public void mouseClicked(MouseEvent e) {
		if ((mouseAction & (1 << MausBehaelter.CLICK)) != 0) {
			tuWas(MausBehaelter.CLICK, e);
		}
	}

	public void mousePressed(MouseEvent e) {
		if ((mouseAction & (1 << MausBehaelter.PRESS)) != 0) {
			tuWas(MausBehaelter.PRESS, e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if ((mouseAction & (1 << MausBehaelter.RELEASE)) != 0) {
			tuWas(MausBehaelter.RELEASE, e);
		}

	}

	public void mouseEntered(MouseEvent e) {
		if ((mouseAction & (1 << MausBehaelter.ENTER)) != 0) {
			tuWas(MausBehaelter.ENTER, e);
		}
	}

	public void mouseExited(MouseEvent e) {
		if ((mouseAction & (1 << MausBehaelter.EXIT)) != 0) {
			tuWas(MausBehaelter.EXIT, e);
		}
	}

	public void mouseDragged(MouseEvent e) {
		if ((mouseAction & (1 << MausBehaelter.DRAGGED)) != 0) {
			tuWas(MausBehaelter.DRAGGED, e);
		}
	}

	public void mouseMoved(MouseEvent e) {
		if ((mouseAction & (1 << MausBehaelter.MOVED)) != 0) {
			tuWas(MausBehaelter.MOVED, e);
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if ((mouseAction & (1 << MausBehaelter.WHEEL)) != 0) {
			rotation = e.getWheelRotation();
			tuWas(MausBehaelter.WHEEL, e);
		}
	}

	public void setzeSichtbarkeit(boolean sichtbar) {
		this.getParent().setVisible(sichtbar);
	}

	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y, int width, int height) {
		obj.setBounds(x, y, width, height);
		repaint();
		// validate();
		// Zeichnung.gibJFrame().validate();
		// Zeichnung.gibZeichenflaeche().validate();
	}

	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		obj.setSize(width, height);
		obj.repaint();
		repaint();
		validate();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	@Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		obj.setLocation(x, y);
		obj.repaint();
		repaint();
		validate();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	@Override
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		int breite = getSize().width - 1;
		int hoehe = getSize().height - 1;

		if (mitRand) {

			if (hintergrundFarbe != null) {
				g2.setColor(hfarbe);
				g2.fill3DRect(0, 0, breite, hoehe, true);
			}

			g2.setColor(farbe);
			if (anzeige != null) {
				Rectangle2D rec = (g2.getFontMetrics()).getStringBounds(" " + anzeige + " ", g2);
				anzeigenbreite = (int) rec.getWidth();
				anzeigenhoehe = (int) rec.getHeight();
				ascend = g2.getFontMetrics().getMaxAscent();
			}

			if (anzeige != null) {
				g2.drawString(anzeige, 6, ascend);
				g2.drawLine(0, anzeigenhoehe / 2, 4, anzeigenhoehe / 2);
				g2.drawLine(anzeigenbreite + 1, anzeigenhoehe / 2, breite, anzeigenhoehe / 2);
				g2.drawLine(0, anzeigenhoehe / 2, 0, hoehe);
				g2.drawLine(breite, anzeigenhoehe / 2, breite, hoehe);
				g2.drawLine(0, hoehe, breite, hoehe);

			} else {
				g2.draw3DRect(0, 0, breite, hoehe, true);
			}
		}

		if (mitRaster) {
			Color farbe = StaticTools.getColor(rasterfarbe);
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

	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		/* Now print the window and its visible contents */
		this.printAll(g);

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}

}
