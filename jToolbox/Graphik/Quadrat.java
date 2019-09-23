
//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * <h1>Quadrat</h1>
 * 
 * @author Hans Witt
 * @version Version 1.1 (14.7.2008) Hinzufuegen von Statusvariablen fuer
 *          Position ... <br />
 *          Version: 1.1.1 (17.7.2008) Neue Komponenten werden von Unten nach
 *          Oben aufgebaut, d.h.vor die alten gesetzt <br />
 *          Version: 3 (9.8.2008) Containerklasse fuer GUI-Elemente <br />
 *          Version: 3.1 (14.8.2008) Konstruktor auf int neuesX, int neuesY ,
 *          int neueBreite, int neueHoehe angepasst <br />
 *          Version: 3.2 (18.8.2008) Zustandsvariable auf protected gesetzt <br />
 *          Version 5.0: (4.9.2010 Entfernen fuer Graphikkomponente eingefuehrt <br />
 *          Destruktor entfernt Graphikkomponente automatisch bei gc() <br />
 */
public class Quadrat implements IComponente, IGraphik {

	private CQuadrat obj;
	public int seite = 0;
	public int xPos = 0;
	public int yPos = 0;
	public boolean sichtbar = true;
	public boolean gefuellt = true;
	public String farbe = StaticTools.leseNormalfarbe();

	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public Quadrat() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueSeite
	 */
	public Quadrat(int neueSeite) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueSeite);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueSeite
	 */
	public Quadrat(int neuesX, int neuesY, int neueSeite) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueSeite);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Quadrat(IContainer behaelter) {
		this(behaelter, 0, 0, 50);
	}

	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neuesX
	 * @param neuesY
	 * @param neueSeite
	 */
	public Quadrat(IContainer behaelter, int neuesX, int neuesY, int neueSeite) {
		obj = new CQuadrat();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueSeite);
		behaelter.validate();
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

	public void sichtbarMachen() {
		sichtbar = true;
		obj.sichtbarMachen();
	}

	/**
	 * Mache unsichtbar. Wenn es bereits unsichtbar ist, tue nichts.
	 */
	public void unsichtbarMachen() {
		sichtbar = false;
		obj.unsichtbarMachen();
	}

	/**
	 * Bewege horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void nachRechtsBewegen() {
		horizontalBewegen(20);
	}

	/**
	 * Bewege einige Bildschirmpunkte nach links.
	 */
	public void nachLinksBewegen() {
		horizontalBewegen(-20);
	}

	/**
	 * Bewege einige Bildschirmpunkte nach oben.
	 */
	public void nachObenBewegen() {
		vertikalBewegen(-20);
	}

	/**
	 * Bewege einige Bildschirmpunkte nach unten.
	 */
	public void nachUntenBewegen() {
		vertikalBewegen(20);
	}

	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamVertikalBewegen(int entfernung) {
		int delta;

		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}

		for (int i = 0; i < entfernung; i++) {
			vertikalBewegen(delta);
			StaticTools.warte(10);
		}
	}

	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamHorizontalBewegen(int entfernung) {
		int delta;

		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}

		for (int i = 0; i < entfernung; i++) {
			horizontalBewegen(delta);
			StaticTools.warte(10);
		}
	}

	/**
	 * 
	 * 
	 */
	public void setzeGroesse(int neueSeite) {
		seite = neueSeite;
		obj.setzeGroesse(seite, seite);
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
		obj.setzePosition(xPos, yPos);
	}

	// Methode noetig zum Hinzufuegen mit Anpassung beim Behaelter
	// Die Enden werden relativ zur aktuellen position verschoben
	public void verschieben(int dx, int dy) {
		setzePosition(xPos + dx, yPos + dy);
	}

	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueSeite
	 */
	public void setzeDimensionen(int neuesX, int neuesY, int neueSeite) {
		xPos = neuesX;
		yPos = neuesY;
		seite = neueSeite;
		obj.setzeDimensionen(xPos, yPos, seite, seite);
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(String neueFarbe) {
		farbe = neueFarbe;
		obj.setzeBasisfarbe(neueFarbe);
	}

	/**
	 * Bewege horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void horizontalBewegen(int entfernung) {
		xPos += entfernung;
		obj.setzePosition(xPos, yPos);
	}

	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void vertikalBewegen(int entfernung) {
		yPos += entfernung;
		obj.setzePosition(xPos, yPos);
	}

	public void fuellen() {
		gefuellt = true;
		obj.fuellen();
	}

	public void rand() {
		gefuellt = false;
		obj.rand();
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

	// Kollisionserkennung

	@Override
	public boolean enthaeltPunkt(int x, int y) {
		return enthaeltPunkt(new Point(x, y));
	}

	@Override
	public boolean enthaeltPunkt(Point punkt) {
		return getArea().contains(punkt);
	}

	@Override
	public Area getArea() {
		return obj.getArea();
	}

	@Override
	public boolean kollisionMit(IGraphik grObject) {
		Area arThis = getArea();
		Area arGrObject = grObject.getArea();
		boolean erg = true;
		try {
			arThis.intersect(arGrObject);
			if (arThis.isEmpty()) {
				erg = false;
			}
		} catch (Exception e) {
			erg = false;
		}
		return erg;
	}

	@Override
	public Rectangle kollisionsgebiet(IGraphik grObject) {
		return getBounds().intersection(grObject.getBounds());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, seite, seite);
	}

}

@SuppressWarnings("serial")
class CQuadrat extends BasisComponente implements ICGraphik {

	Shape sQuadrat;

	public Area getArea() {
		while (sQuadrat == null) {
			StaticTools.warte(5);
		}
		Area move = new Area(sQuadrat);
		move.transform(AffineTransform.getTranslateInstance(xPos, yPos));
		return move;
	}

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CQuadrat() {

	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */

	@Override
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width;
		hoehe = getSize().height;
		g.setColor(farbe);

		sQuadrat = new Rectangle2D.Float(0, 0, breite, hoehe);

		if (gefuellt) {
			g2.fill3DRect(0, 0, breite, hoehe, true);
		} else {

			g2.draw3DRect(0, 0, breite, hoehe, true);
			g2.draw3DRect(1, 1, breite - 2, hoehe - 2, true);
		}

	}
}
