//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;

/**
 * <h1>Ellipse</h1>, auch als Bogen einsetzbar.<br/>
 * Startwinkel und Bogenlaenge im Gradmass
 * <hr>
 * 
 * @author Hans Witt
 * 
 * @version Version: 1.0 (19.7.2008)<br />
 * 
 *          Version: 3 (9.8.2008) Containerklasse fuer GUI-Elemente<br />
 *          Version: 3.1 (14.8.2008) Konstruktor auf int neuesX, int neuesY ,
 *          int neueBreite, int neueHoehe angepasst<br />
 * 
 *          Version: 3.2 (18.8.2008) Zustandsvariable auf protected gesetzt <br />
 *          Version 5.0: (4.9.2010 Entfernen fuer Graphikkomponente eingefuehrt<br />
 * 
 *          Destruktor entfernt Graphikkomponente automatisch bei gc()<br />
 * 
 */
public class Ellipse implements IComponente, IGraphik {

	private CEllipse obj;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;

	public int startwinkel = 0;
	public int bogenlaenge = 360;

	public boolean sichtbar = true;
	public boolean gefuellt = true;
	public String farbe = StaticTools.leseNormalfarbe();

	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public Ellipse() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Ellipse(int neueBreite, int neueHoehe) {
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
	public Ellipse(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Ellipse(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Ellipse(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this(behaelter, neuesX, neuesY, neueBreite, neueHoehe, 0, 360);
	}

	public Ellipse(int neuesX, int neuesY, int neueBreite, int neueHoehe,
			int neuerStartwinkel, int neueBogenlaenge) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe, neuerStartwinkel, neueBogenlaenge);

	}

	public Ellipse(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe, int neuerStartwinkel,
			int neueBogenlaenge) {
		obj = new CEllipse();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		setzeBogen(neuerStartwinkel, neueBogenlaenge);
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

	public void setzeBogen(int neuerStartwinkel, int neueBogenlaenge) {
		startwinkel = neuerStartwinkel;
		bogenlaenge = neueBogenlaenge;
		obj.setzeBogen(startwinkel, bogenlaenge);
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

	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeGroesse(breite, hoehe);
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
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public void setzeDimensionen(int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		xPos = neuesX;
		yPos = neuesY;
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeDimensionen(xPos, yPos, breite, hoehe);
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
	public boolean enthaeltPunkt( int x , int y ){
		return enthaeltPunkt(new Point(x, y) );
	}

	
	@Override
	public boolean enthaeltPunkt( Point punkt){
		return getArea().contains(punkt);
	}

	@Override
	public Area getArea(){
		return obj.getArea();
	}
	

	@Override
	public boolean kollisionMit(IGraphik grObject) {
		Area arThis = getArea();
		Area arGrObject = grObject.getArea();
		boolean erg = true ;
		try {
			arThis.intersect(arGrObject);
			if (arThis.isEmpty()){
				erg=false ;
			}
		} catch (Exception e) {
			erg = false ;
		}
		return erg ;
	}

	
	@Override
	public Rectangle kollisionsgebiet(IGraphik grObject) {
		return getBounds().intersection(grObject.getBounds());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, breite, hoehe);
	}

}

@SuppressWarnings("serial")
class CEllipse extends BasisComponente implements ICGraphik {

	private int startWinkel = 0;
	private int bogenLaenge = 360;
	
	Shape sEllipse ;
	
	public Area getArea() {
		while(sEllipse== null){
			StaticTools.warte(5);
		}
		Area move =new Area(sEllipse);
		move.transform(AffineTransform.getTranslateInstance(xPos, yPos));
		return move ;
	}


	public void setzeBogen(int neuerStartwinkel, int neueBogenlaenge) {
		startWinkel = neuerStartwinkel;
		bogenLaenge = neueBogenlaenge;
		repaint();
	}

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CEllipse() {

	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */

	@Override
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width - 1;
		hoehe = getSize().height - 1;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));

		g2.setColor(farbe);

		sEllipse = new Arc2D.Double( 0, 0, breite , hoehe ,   startWinkel,   bogenLaenge , Arc2D.PIE ) ;
		
		if (gefuellt) {
			sEllipse = new Arc2D.Double( 0, 0, breite , hoehe ,   startWinkel,   bogenLaenge , Arc2D.PIE ) ;
			g2.fill(sEllipse);
		} else {
			sEllipse = new Arc2D.Double( 0, 0, breite , hoehe ,   startWinkel,   bogenLaenge , Arc2D.OPEN ) ;
			g2.draw(sEllipse);
			g2.drawArc(1, 1, breite - 2, hoehe - 2, startWinkel, bogenLaenge);
		}
	}
}
