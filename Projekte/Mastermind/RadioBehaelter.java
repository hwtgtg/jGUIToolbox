
//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * <h1>RadioBehaelter</h1>:
 * 
 * Der RadioBehaelter ist besonders zur Aufnahme von RadioTasten vorgesehen. <br />
 * Er ist abgeleitet von der Behaelterklasse.<br />
 * 
 * Der Radiobehaelter sorgt dafuer, dass nur eine Taste ausgewaehlt ist.<br/>
 * <a href="RadioTaste.html">Siehe Klasse Radiotaste</a> <br/>
 * <br/>
 * 
 * RadioTasten signalisieren zuerst dem Behaelter. Die ID der Taste wird
 * zusammen mit der ID des Behaelters weitergeleitet. <br />
 * 
 * 
 * 
 * <h2>Anwendung:</h2>
 * <ul>
 * <li>Radiobehaelter erzeugen: radiobehaelter = new
 * RadioBehaelter(0,152,200,47);</li>
 * <li>RadioTaste in den Behaelter setzen: r1 = new RadioTaste(radiobehaelter,
 * "ro", 7, 15, 60, 30);</li>
 * <li>ID der Taste setzen: r1.setzeID(3);</li>
 * <li>Anfangs gesetzte Taste festlegen: r1.setzeGewaehlt();</li>
 * <li>Optional: Kommunikations-Link vom Radiobehaelter auf ein anderes Ziel
 * setzen. Es wird dann statt dem Radiobehaelter das andere Objekt informiert.
 * Die Radiobehaelter-Funktionalit&auml;t bleibt erhalten</li>
 * </ul>
 * 
 * <hr>
 * 
 * @author Hans Witt
 * 
 * @version
 *
 *          Version 5.0: (4.9.2010) Entfernen fuer Graphikkomponente eingefuehrt <br />
 * 
 *          Destruktor entfernt Graphikkomponente automatisch bei gc() <br />
 */
public class RadioBehaelter implements IContainer, IComponente {
	private CRadioBehaelter obj;
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
	public RadioBehaelter() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public RadioBehaelter(int neueBreite, int neueHoehe) {
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
	public RadioBehaelter(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public RadioBehaelter(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public RadioBehaelter(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		obj = new CRadioBehaelter();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}

	@Override
	public double setzeZoomfaktor(double zf) {
		zoomInhalt = obj.setzeZoomfaktor(zf);
		return zoomInhalt;
	}

	public double getBehaelterZoom() {
		return obj.getBehaelterZoom();
	}

	public void hinzufuegen(RadioTaste rTaste) {
		rTaste.getBasisComponente().ausContainerEntfernen();
		obj.add(rTaste.getBasisComponente(), 0);
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
	 */
	public void setzeLink(ITuWas linkObj, int ID) {
		obj.setzeLink(linkObj, ID);
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
	 * Groesse des Anzeigefelds aendern *
	 */
	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeGroesse((int) (breite / zoomInhalt),
				(int) (hoehe / zoomInhalt));
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
	public void setzeDimensionen(int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		xPos = neuesX;
		yPos = neuesY;
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeDimensionen((int) (xPos / zoomInhalt),
				(int) (yPos / zoomInhalt), (int) (breite / zoomInhalt),
				(int) (hoehe / zoomInhalt));
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

	/**
	 * Fuer Interface IContainer
	 */
	@Override
	public Component add(Component comp, int index) {
		return this.obj.add(comp, index);
	}

	@Override
	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
			int width, int height) {
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

}

@SuppressWarnings("serial")
class CRadioBehaelter extends BasisComponente implements IContainer, ITuWas {
	private ButtonGroup group = new ButtonGroup();
	private Vector<BasisComponente> unterComponenten = new Vector<BasisComponente>();
	private String anzeige = null;
	private boolean mitRand = false;
	int anzeigenbreite = 0;
	int anzeigenhoehe = 0;
	int ascend = 0;
	private String hintergrundFarbe = null;
	Color hfarbe;

	public CRadioBehaelter() {
		this.setLayout(null);
	}

	/**
	 * ID Der RadioButtonGroup
	 * 
	 * @param ID
	 */
	@Override
	public void setzeID(int ID) {
		id = ID;
	}

	/**
	 * 
	 * @param linkObj
	 */
	@Override
	public void setzeLink(ITuWas linkObj) {
		this.linkObj = linkObj;
	}

	@Override
	public void setzeLink(ITuWas linkObj, int ID) {
		this.linkObj = linkObj;
		id = ID;
	}

	/**
	 * RadioTasten signalisieren default-maessig dem Behaelter Der leitet die ID
	 * zusammen mit der BehaelterID weiter
	 */
	@Override
	public void tuWas(int ID) {
		final int IDrunnable = ID;
		if (linkObj != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					linkObj.tuWas(id + IDrunnable);
				}
			}).start();
		}
	}

	@Override
	public Component add(Component comp, int index) {
		Component erg = super.add(comp, index);
		unterComponenten.addElement((BasisComponente) comp);
		group.add(((CRadioTaste) comp).getButton());

		// Eine RadioTaste signalisiert zuerst dem Behaelter. Kann
		// ueberschreieben
		// werden
		((BasisComponente) comp).setzeLink(this);

		return erg;
	}

	/**
	 * liefert den Zoomfaktor fuer den Behaelter
	 * 
	 * @return
	 */
	public double getBehaelterZoom() {
		return zoomFaktor * ((IContainer) this.getParent()).getBehaelterZoom();
	}

	@Override
	public double setzeZoomfaktor(double zf) {
		zoomFaktor = zf;
		bzf = ((IContainer) this.getParent()).getBehaelterZoom();
		fontGroesse = (int) Math.round(originalFontGroesse * zoomFaktor * bzf);
		setzeSchriftgroesse(fontGroesse);

		originalXPos = (int) (originalXPos / zoomFaktor);
		originalYPos = (int) (originalYPos / zoomFaktor);

		for (int i = 0; i < unterComponenten.size(); i++) {
			unterComponenten.get(i).zommfaktorAnpassen();
		}

		zoomen();

		if (sichtbar) {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, 0, 0);
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

		if (hintergrundFarbe != null) {
			hfarbe = StaticTools.getColor(hintergrundFarbe);
		}
	}

	public void setzeSichtbarkeit(boolean sichtbar) {
		this.getParent().setVisible(sichtbar);
	}

	// Wird von der Graaphikkomponente zum Positionieren im Behaelter aufgerufen
	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
			int width, int height) {
		obj.setBounds(x, y, width, height);
		repaint();
		validate();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		obj.setSize(width, height);
		obj.repaint();
		repaint();
		validate();
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	@Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		obj.setLocation(x, y);
		obj.repaint();
		repaint();
		validate();
		Zeichnung.gibZeichenflaeche().validate();

		// Zeichnung.gibJFrame().validate();
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	@Override
	public void paintComponentSpezial(Graphics g) {
		if (mitRand) {
			Graphics2D g2 = (Graphics2D) g;
			// Graphik-Abmessungen
			breite = getSize().width - 1;
			hoehe = getSize().height - 1;

			if (hintergrundFarbe != null) {
				g2.setColor(hfarbe);
				g2.fill3DRect(0, 0, breite, hoehe, true);
			}

			g2.setColor(farbe);

			if (anzeige != null) {
				Rectangle2D rec = (g2.getFontMetrics()).getStringBounds(" "
						+ anzeige + " ", g2);
				anzeigenbreite = (int) rec.getWidth();
				anzeigenhoehe = (int) rec.getHeight();
				ascend = g2.getFontMetrics().getMaxAscent();
			}

			if (anzeige != null) {
				g2.drawString(anzeige, 6, ascend);
				g2.drawLine(0, anzeigenhoehe / 2, 4, anzeigenhoehe / 2);
				g2.drawLine(anzeigenbreite + 1, anzeigenhoehe / 2, breite,
						anzeigenhoehe / 2);
				g2.drawLine(0, anzeigenhoehe / 2, 0, hoehe);
				g2.drawLine(breite, anzeigenhoehe / 2, breite, hoehe);
				g2.drawLine(0, hoehe, breite, hoehe);
			} else {
				g2.draw3DRect(0, 0, breite, hoehe, true);
			}
		}
	}
}
