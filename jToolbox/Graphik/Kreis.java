
//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * <h1>Kreis</h1>
 * <hr>
 * 
 * @author Hans Witt
 * @version Version 1.1 (14.7.2008) Hinzufuegen von Statusvariablen fuer
 *          Position ...<br/>
 *          Version: 1.1.1 (17.7.2008) Neue Komponenten werden von Unten nach
 *          Oben aufgebaut, d.h.vor die alten gesetzt<br/>
 *          Version: 1.1.2 (23.7.2008) setzeRadius verbessert ( Groesse = radius
 *          * >>2<< )<br/>
 * 
 *          Version: 3 (9.8.2008) Containerklasse fuer GUI-Elemente<br/>
 *          Version: 3.1 (14.8.2008) Konstruktor auf int neuesX, int neuesY ,
 *          int neueBreite, int neueHoehe angepasst<br/>
 *          Version: 3.2 (18.8.2008) Zustandsvariable auf protected gesetzt <br/>
 *          Version: 3.3 (29.8.2008) Zu den sonst uebliche Metheode setzeGroesse
 *          undd setzePosition kommen<br/>
 * 
 *          setzeRadius - behaelt den Mittelpunkt bei !<br/>
 * 
 *          setzeMittelpunkt<br/>
 * 
 *          setzeMittelpunktUndRadius<br/>
 * 
 *          Version 5.0: (4.9.2010 Entfernen fuer Graphikkomponente eingefuehrt<br/>
 * 
 *          Destruktor entfernt Graphikkomponente automatisch bei gc()<br/>
 */
public class Kreis implements IComponente, IGraphik {

	private CKreis obj;
	public int radius = 25;
	public int xPos = 0;
	public int yPos = 0;
	public boolean sichtbar = true;
	public boolean gefuellt = true;
	public String farbe = StaticTools.leseNormalfarbe();

	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public Kreis() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuerRadius
	 */
	public Kreis(int neuerRadius) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neuerRadius);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neuerRadius
	 */
	public Kreis(int neuesX, int neuesY, int neuerRadius) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neuerRadius);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Kreis(IContainer behaelter) {
		this(behaelter, 0, 0, 25);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param neuerRadius
	 */
	public Kreis(IContainer behaelter, int neuesX, int neuesY, int neuerRadius) {
		obj = new CKreis();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neuerRadius);
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
	 * neuer Mittelpunkt
	 * 
	 * @param neuesMX
	 * @param neuesMY
	 */
	public void setzeMittelpunkt(int neuesMX, int neuesMY) {
		xPos = neuesMX - radius;
		yPos = neuesMY - radius;
		obj.setzePosition(xPos, yPos);
	}

	/**
	 * neuer radius *
	 */
	public void setzeRadius(int neuerRadius) {
		int mx = xPos + radius;
		int my = yPos + radius;
		radius = neuerRadius;
		xPos = mx - radius;
		yPos = my - radius;
		obj.setzeDimensionen(xPos, yPos, 2 * radius, 2 * radius);
	}

	/**
	 * 
	 * @param neuesMX
	 * @param neuesMY
	 * @param neuerRadius
	 */

	public void setzeMittelpunktUndRadius(int neuesMX, int neuesMY,
			int neuerRadius) {
		radius = neuerRadius;
		xPos = neuesMX - radius;
		yPos = neuesMY - radius;
		obj.setzeDimensionen(xPos, yPos, 2 * radius, 2 * radius);
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
	 * neuer radius *
	 */
	public void setzeGroesse(int neuerRadius) {
		radius = neuerRadius;
		obj.setzeGroesse(2 * radius, 2 * radius);
	}

	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neuerRadius
	 */
	public void setzeDimensionen(int neuesX, int neuesY, int neuerRadius) {
		xPos = neuesX;
		yPos = neuesY;
		radius = neuerRadius;
		obj.setzeDimensionen(xPos, yPos, 2 * radius, 2 * radius);
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

	/**
	 * gefuellter Kreis
	 */
	public void fuellen() {
		gefuellt = true;
		obj.fuellen();
	}

	/**
	 * Kreislinie
	 */
	public void rand() {
		gefuellt = false;
		obj.rand();
	}

	/**
	 * Entfernen des Graphikobjekts
	 */
	public void entfernen() {
		getBasisComponente().ausContainerEntfernen();
		obj = null;
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
		return new Rectangle(xPos, yPos, 2 * radius, 2 * radius);
	}

}

@SuppressWarnings("serial")
class CKreis extends BasisComponente implements ICGraphik {

	Shape sKreis;

	@Override
	public Area getArea() {
		while (sKreis == null) {
			StaticTools.warte(5);
		}
		Area move = new Area(sKreis);
		move.transform(AffineTransform.getTranslateInstance(xPos, yPos));
		return move;
	}

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CKreis() {

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
		g2.setColor(farbe);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));

		sKreis = new Ellipse2D.Float(0, 0, breite, hoehe);

		if (gefuellt) {
			g2.fill(sKreis);
		} else {

			g2.drawOval(1, 1, breite - 3, hoehe - 3);
			// g2.drawOval(1, 1, breite - 3, hoehe - 3);
		}
	}
}
