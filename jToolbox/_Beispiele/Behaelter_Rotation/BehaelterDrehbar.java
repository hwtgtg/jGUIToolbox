
//%$JGUIToolbox$%//

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * <h1>BehaelterDrehbar als Container-Klasse</h1>.<br />
 * Der Behaelter nimmt GUI-Elemente auf <br />
 * GUI-Elemente koennen rotieren <br />
 * 
 * Diese werden relativ zum Container positioniert.<br />
 * Ein Verschieben des Behaelters verschiebt <b>alle Elemente</b> im Behaelter
 * mit.<br />
 * <br />
 * Die Klasse kapselt die GUI-Darstellung der Elemente. <br />
 * Sie ist in einer "normalen" Java-Klasse die Erg&auml;nzung, in der die
 * <b>Graphische Darstellung</b> der Objekte gekapselt ist.
 * 
 * @author Hans Witt
 * 
 * @version Version: 4.0 Zoom fuer Behaelter eingefuehrt<br />
 *          Anpassung bei BasisComponente fuer Wechseln zwischen Behaeltern<br />
 *          Interface IComponente. Alle Klassen, die zum einem Behaelter
 *          nachtraeglich hinzugefuegt werden koennen, muessen das Interface
 *          Componente haben<br />
 * 
 */
public class BehaelterDrehbar implements IContainer, IComponente  {

	protected CBehaelterDrehbar cbDrehbar_obj;
	public float breite = 0;
	public float hoehe = 0;
	public float xPos = 0;
	public float yPos = 0;

	protected Behaelter innererBehaelter;
	boolean innererBehaelterErzeugt = false;
	protected float rotationXRel;
	protected float rotationYRel;

	protected float innereBreite = 0;
	protected float innereHoehe = 0;
	protected float inneresXPos = 0;
	protected float inneresYPos = 0;

	protected float xPosRelativ = 0;
	protected float yPosRelativ = 0;

	protected boolean sichtbar = true;
	protected double zoomInhalt = 1;
	protected String anzeigeText = "Anzeige";
	protected int fontGroesse = -1;
	protected String farbe = "schwarz";
	protected String hintergrundFarbe = null;
	
	private static float PI = (float) Math.PI;

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public BehaelterDrehbar() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterDrehbar(int neueBreite, int neueHoehe) {
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
	public BehaelterDrehbar(int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public BehaelterDrehbar(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterDrehbar(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {

		rotationXRel = neueBreite / 2;
		rotationYRel = neueHoehe / 2;

		inneresXPos = neuesX;
		inneresYPos = neuesY;
		innereBreite = neueBreite;
		innereHoehe = neueHoehe;

		legeDimensionenFest();

		cbDrehbar_obj = new CBehaelterDrehbar();
		behaelter.add(cbDrehbar_obj, 0);

		// Inneren Behaelter einfuegen
		// Rotationszentrum Mitte

		innererBehaelter = new Behaelter(this, (int)xPosRelativ, (int)yPosRelativ,
				(int)innereBreite, (int)innereHoehe);

		setzeDimensionenAussen();

//		setzeMitRaster(true, true);
		innererBehaelter.setzeMitRand(true);

	}

	private void legeDimensionenFest() {
		// Nach links oben
		float d1 = Math.round(Math.sqrt((rotationXRel) * (rotationXRel)
				+ (rotationYRel) * (rotationYRel)));

		// Nach links unten
		float d2 = Math.round(Math.sqrt((rotationXRel) * (rotationXRel)
				+ (innereHoehe - rotationYRel) * (innereHoehe - rotationYRel)));

		// Nach rechts oben
		float d3 =  Math.round(Math.sqrt((innereBreite - rotationXRel)
				* (innereBreite - rotationXRel) + (rotationYRel) * (rotationYRel)));

		// Nach rechts unten
		float d4 =  Math.round(Math.sqrt((innereBreite - rotationXRel)
				* (innereBreite - rotationXRel) + (innereHoehe - rotationYRel)
				* (innereHoehe - rotationYRel)));

		breite = Math.max(d1, Math.max(d2, Math.max(d3, d4))) * 2;
		hoehe = breite;
		
		xPos = inneresXPos - (breite / 2 - rotationXRel);
		xPosRelativ = breite / 2 - rotationXRel;

		yPos = inneresYPos - (hoehe / 2 - rotationYRel);
		yPosRelativ = hoehe / 2 - rotationYRel;

	}

	public float leseWinkelBogenmass() {
		return cbDrehbar_obj.getWinkelBogenmass();
	}

	public float leseWinkelGradmass() {
		return leseWinkelBogenmass() / PI * 180;
	}

	public void setzeWinkelGradmass(float winkel) {
		setzeWinkelBogenmass(winkel * PI / 180);
	}

	public void setzeWinkelBogenmass(float winkel) {
		while (winkel > 2 * Math.PI) {
			winkel -= 2 * Math.PI;
		}

		while (winkel < -2 * Math.PI) {
			winkel += 2 * Math.PI;
		}

		cbDrehbar_obj.setWinkelBogenmass(winkel);
	}

	/**
	 * Setze Rotationszentrum relativ zu Anfangsposition
	 * 
	 * @param rotXRel
	 * @param rotYRel
	 */
	public void setzeRotationszentrumRelativ(float rotXRel, float rotYRel) {
		rotationXRel = rotXRel;
		rotationYRel = rotYRel;
		legeDimensionenFest();
		setzeDimensionenAussen();
	}


	/**
	 * Groesse des Anzeigefelds aendern *
	 */
	public void setzeGroesse(int neueBreite, int neueHoehe) {
		innererBehaelter.setzeGroesse(neueBreite, neueHoehe);

		innereBreite = neueBreite;
		innereHoehe = neueHoehe;

		legeDimensionenFest();

		setzeDimensionenAussen();
	}

	/**
	 * neue Position
	 * 
	 * @param posRotX
	 * @param posRotY
	 */
	public void setzePositionRotationszentrum(float posRotX, float posRotY) {
		inneresXPos = posRotX - rotationXRel;
		inneresYPos = posRotY - rotationYRel;
		xPos = inneresXPos - xPosRelativ;
		yPos = inneresYPos - yPosRelativ;
		cbDrehbar_obj.setzePosition((int) (xPos / zoomInhalt), (int) (yPos / zoomInhalt));
		innererBehaelter.setzePosition((int)xPosRelativ, (int)yPosRelativ);
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
		inneresXPos = neuesX;
		inneresYPos = neuesY;
		innereBreite = neueBreite;
		innereHoehe = neueHoehe;

		legeDimensionenFest();
		setzeDimensionenAussen();
	}

	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	private void setzeDimensionenAussen() {
		cbDrehbar_obj.setzeDimensionen((int) (xPos / zoomInhalt),
				(int) (yPos / zoomInhalt), (int) (breite / zoomInhalt),
				(int) (hoehe / zoomInhalt));
		cbDrehbar_obj.rZentrumAnpassen();
		innererBehaelter.setzePosition((int)xPosRelativ, (int)yPosRelativ);
	}

	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen
	 * Behaelter hinzuzufuegen
	 */
	@Override
	public BasisComponente getBasisComponente() {
		return innererBehaelter.getBasisComponente();
	}

	/**
	 * Fuer Interface IContainer
	 */
	@Override
	public Component add(Component comp, int index) {
		if (!innererBehaelterErzeugt) {
			innererBehaelterErzeugt = true;
			return this.cbDrehbar_obj.add(comp, index);
		} else
			return this.innererBehaelter.getBasisComponente().add(comp, index);
	}

	public void setzeMitRaster(boolean innenMitRaster, boolean mitRaster) {
		cbDrehbar_obj.setzeMitRaster(mitRaster);
		innererBehaelter.setzeMitRand(innenMitRaster);
	}

	public void setzeDeltaX(int innenDeltaX, int deltaX) {
		innererBehaelter.setzeDeltaX(innenDeltaX);
		cbDrehbar_obj.setzeDeltaX(deltaX);
	}

	public void setzeDeltaY(int innenDeltaY, int deltaY) {
		innererBehaelter.setzeDeltaY(innenDeltaY);
		cbDrehbar_obj.setzeDeltaY(deltaY);
	}

	/* *********************************************************************************************************************** */

	@Override
	public double setzeZoomfaktor(double zf) {
		zoomInhalt = cbDrehbar_obj.setzeZoomfaktor(zf);
		return zoomInhalt;
	}

	public double getBehaelterZoom() {
		return cbDrehbar_obj.getBehaelterZoom();
	}

	public void hinzufuegen(IComponente comp) {
		comp.getBasisComponente().ausContainerEntfernen();
		cbDrehbar_obj.add(comp.getBasisComponente(), 0);
	}

	public void hinzufuegenUndAnpassen(IComponente comp) {
		comp.getBasisComponente().ausContainerEntfernen();
		cbDrehbar_obj.add(comp.getBasisComponente(), 0);
		comp.getBasisComponente().verschieben((int)(-xPos), (int)(-yPos));
	}

	public void setzeBeschreibungstext(String neuerText) {
		anzeigeText = neuerText;
		cbDrehbar_obj.setText(anzeigeText);
	}

	public void setzeSchriftName(String name) {
		cbDrehbar_obj.setzeFontName(name);
	}

	public void setzeSchriftgroesse(int neueFontgroesse) {
		fontGroesse = neueFontgroesse;
		cbDrehbar_obj.setzeSchriftgroesse(fontGroesse);
	}

	public void setzeSchriftStilNormal() {
		cbDrehbar_obj.setzeSchriftStil(Font.PLAIN);
	}

	public void setzeSchriftStilFett() {
		cbDrehbar_obj.setzeSchriftStil(Font.BOLD);
	}

	public void setzeSchriftStilKursiv() {
		cbDrehbar_obj.setzeSchriftStil(Font.ITALIC);
	}

	public void setzeFarbe(String neueFarbe) {
		farbe = neueFarbe;
		cbDrehbar_obj.setzeSchriftFarbe(farbe);
	}

	public void setzeHintergrundfarbe(String neueFarbe) {
		hintergrundFarbe = neueFarbe;
		cbDrehbar_obj.setzeHintergrundfarbe(hintergrundFarbe);
	}

	public void setzeMitRand(boolean mitRand) {
		cbDrehbar_obj.setzeMitRand(mitRand);
	}

	/**
	 * Mache sichtbar.
	 */
	public void sichtbarMachen() {
		sichtbar = true;
		cbDrehbar_obj.sichtbarMachen();
	}

	/**
	 * Mache unsichtbar.
	 */
	public void unsichtbarMachen() {
		sichtbar = false;
		cbDrehbar_obj.unsichtbarMachen();
	}


	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
			int width, int height) {
		this.cbDrehbar_obj.setzeKomponentenKoordinaten(obj, x, y, width, height);
	}

	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		this.cbDrehbar_obj.setzeKomponentenGroesse(obj, width, height);
	}

	@Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		this.cbDrehbar_obj.setzeKomponentenPosition(obj, x, y);
	}

	@Override
	public void validate() {
		cbDrehbar_obj.validate();
	}

	/*
	 * Ende Interface IContainer
	 */

	/**
	 * gibt z.B. fuer den Dialog das JPanel-Objekt zurueck
	 */
	public JPanel getPanel() {
		return cbDrehbar_obj;
	}

	/**
	 * Entfernen des Graphikobjekts
	 */
	public void entfernen() {
		if (cbDrehbar_obj != null) {
			cbDrehbar_obj.ausContainerEntfernen();
			cbDrehbar_obj = null;
		}
	}

	/**
	 * Destruktor
	 */
	@Override
	protected void finalize() {
		if (!Zeichnung.verweistesGUIElementEntfernen)
			return;
		if (cbDrehbar_obj != null)
			entfernen();
	}

	public void expand() {
		cbDrehbar_obj.expand();
	}

	public void xy() {
		cbDrehbar_obj.xy();
	}
	
	
	// Kollision

//	@Override
//	public Rectangle getBounds() {
//		return cbDrehbar_obj.getBounds();
//	}
//
//	@Override
//	public Area getArea() {
//		return cbDrehbar_obj.getArea();
//	}
//
//	@Override
//	public boolean kollisionMit(IGraphik grObject) {
//		Area arThis = getArea();
//		Area arGrObject = grObject.getArea();
//		boolean erg = true;
//		try {
//			arThis.intersect(arGrObject);
//			if (arThis.isEmpty()) {
//				erg = false;
//			}
//		} catch (Exception e) {
//			erg = false;
//		}
//		return erg;
//	}
//
//	@Override
//	public Rectangle kollisionsgebiet(IGraphik grObject) {
//		return getBounds().intersection(grObject.getBounds());
//	}
//
//	@Override
//	public boolean enthaeltPunkt(int x, int y) {
//		return enthaeltPunkt(new Point(x, y));
//	}
//
//	@Override
//	public boolean enthaeltPunkt(Point punkt) {
//		return getArea().contains(punkt);
//	}

	
	protected void ausfuehren(int ID) {

	}


}

@SuppressWarnings("serial")
class CBehaelterDrehbar extends BasisComponente implements IContainer {
	private boolean mitRaster = false;
	private int deltaX = 100;
	private int deltaY = 100;
	private Vector<BasisComponente> unterComponenten = new Vector<BasisComponente>();
	private String anzeige = "";
	private boolean mitRand = false;
	int anzeigenbreite = 0;
	int anzeigenhoehe = 0;
	int ascend = 0;
	private String hintergrundFarbe = null;
	Color hfarbe;

	private float winkel = 0;
	private float rZentrumX = 0;
	private float rZentrumY = 0;
	private boolean rOriginSet = false;
	
	
//	public Area getArea() {
//		Area summe = new Area();
//		for (int i = 0; i < unterComponenten.size(); i++) {
//			BasisComponente untercomonente = unterComponenten.get(i);
//			if (untercomonente instanceof ICGraphik) {
//				summe.add(((ICGraphik) untercomonente).getArea());
//			}
//		}
//		summe.transform(AffineTransform.getTranslateInstance(xPos, yPos));
//		summe.transform(AffineTransform.getRotateInstance(winkel, rZentrumX,
//				rZentrumY));
//		return summe ;
//	}

	public CBehaelterDrehbar() {
		this.setLayout(null);
	}

	@Override
	public Component add(Component comp, int index) {
		Component erg = super.add(comp, index);
		unterComponenten.addElement((BasisComponente) comp);
		return erg;
	}

	@Override
	public void add(Component comp, Object constraints) {
		super.add(comp, constraints);
		unterComponenten.addElement((BasisComponente) comp);
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

	public void setzeSichtbarkeit(boolean sichtbar) {
		this.getParent().setVisible(sichtbar);
	}

	public float getWinkelBogenmass() {
		return winkel;
	}

	public void rZentrumAnpassen() {
		rZentrumX = breite / 2;
		rZentrumY = hoehe / 2;
		rOriginSet = true;
	}

	public void setWinkelBogenmass(float winkel) {
		if (!rOriginSet) {
			rZentrumAnpassen();
		}
		this.winkel = winkel;
		JPanel p = (JPanel) this.getParent();
		if (p == null)
			return;
		p.repaint();
		p.validate();
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
		Graphics2D g2 = (Graphics2D) g;

		// Graphik-Abmessungen
		int breite = getSize().width - 1;
		int hoehe = getSize().height - 1;

		// Raster wird nicht rotiert
		if (mitRaster) {
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

			g2.draw3DRect(0, 0, breite, hoehe, true);
			g2.draw3DRect(1, 1, breite - 2, hoehe - 2, true);
		}

		g2.rotate(winkel, rZentrumX, rZentrumY);

		if (mitRand) {
			if (hintergrundFarbe != null) {
				g2.setColor(hfarbe);
				g2.fill3DRect(0, 0, breite, hoehe, true);
			}

			g2.setColor(farbe);

			if (anzeige != "") {
				Rectangle2D rec = (g2.getFontMetrics()).getStringBounds(" "
						+ anzeige + " ", g2);
				anzeigenbreite = (int) rec.getWidth();
				anzeigenhoehe = (int) rec.getHeight();
				ascend = g2.getFontMetrics().getMaxAscent();
			}

			if (anzeige != "") {
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
