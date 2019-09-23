//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

/**
 * <h1Pfeil</h1>.
 * <hr>
 * Liniendicke setzen mit setzeBreite(int neueBreite) <br/>
 * End-Spitzen festlegen mit setzeEnden(boolean start, boolean ende) <br>
 * <hr>
 * @author Hans Witt
 * @version
 * Version: 3.2 (30.8.2008) <br />
 * Version: 5.0: (4.9.2010
 *  	   Entfernen fuer Graphikkomponente eingefuehrt <br />
 *  
 *   	   Destruktor entfernt Graphikkomponente automatisch bei gc() <br />
 */
public class Pfeil implements IComponente {
	
	protected CPfeil	obj;
	public int		x1			= 0;
	public int		y1			= 0;
	public int		x2			= 100;
	public int		y2			= 50;
	public boolean	sichtbar	= true;
	public String	farbe		= StaticTools.leseNormalfarbe();
	public int		breite		= 4;
	
	public boolean	spitzeStart	= false;
	public boolean	spitzeEnde	= true;
	
	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public Pfeil() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Pfeil(int x1, int y1, int x2, int y2) {
		this(Zeichnung.gibZeichenflaeche(), x1, y1, x2, y2);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Pfeil(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}
	
	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param x2
	 * @param y2
	 */
	public Pfeil(IContainer behaelter, int x1, int y1, int x2, int y2) {
		cObjectSetzen();
		if (behaelter != null) {
			behaelter.add(obj, 0);
			setzeEndpunkte(x1, y1, x2, y2);
			behaelter.validate();
		}
	}
	
	public void cObjectSetzen() {
		obj = new CPfeil();
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
	
	public boolean isSichtbar(){
		return sichtbar ;
	}
	
	/**
	 * 
	 * @param neuesX1
	 * @param neuesY1
	 * @param neuesX2
	 * @param neuesY2
	 */
	public void setzeEndpunkte(int neuesX1, int neuesY1, int neuesX2,
			int neuesY2) {
		x1 = neuesX1;
		y1 = neuesY1;
		x2 = neuesX2;
		y2 = neuesY2;
		obj.setzeEndpunkte(x1, y1, x2, y2);
	}
	
	// Methode noetig zum Hinzufuegen mit Anpassung beim Behaelter
	// Die Enden werden relativ zur aktuellen Position verschoben
	public void verschieben(int dx, int dy) {
		setzeEndpunkte(x1 + dx, y1 + dy, x2 + dx, y2 + dy);
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
	 * Liniendicke setzen
	 * 
	 * @param neueBreite
	 */
	public void setzeBreite(int neueBreite) {
		breite = neueBreite;
		obj.setzeBreite(breite);
	}
	
	/**
	 * End-Spitzen festlegen
	 * 
	 * @param start
	 * @param ende
	 */
	public void setzeEnden(boolean start, boolean ende) {
		spitzeStart = start;
		spitzeEnde = ende;
		obj.setzeEnde(spitzeStart, spitzeEnde);
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
	if (!Zeichnung.verweistesGUIElementEntfernen) return;
		if (obj != null) entfernen();
	}
	
}

@SuppressWarnings("serial")
class CPfeil extends BasisComponente {
	
	protected int		pfeilBreite			= 50;
	private int			originalPfeilBreite	= 50;
	private int			spitze				= 3;
	
	protected boolean	spitzeStart			= false;
	protected boolean	spitzeEnde			= true;
	
	int[]				xpoints				= new int[10];
	int[]				ypoints				= new int[10];
	
	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CPfeil() {
		
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
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));
		g2.setColor(farbe);
		if (gefuellt) {
			g2.fillPolygon(new Polygon(xpoints, ypoints, 10));
		} else {
			g2.drawPolygon(new Polygon(xpoints, ypoints, 10));
		}
	}
	
	int	originalStartX	= 0;
	int	originalStartY	= 0;
	int	originalEndeX	= 0;
	int	originalEndeY	= 0;
	
	int	startX			= 0;
	int	startY			= 0;
	int	endeX			= 0;
	int	endeY			= 0;
	
	int	relStartX		= 10;
	int	relStartY		= 10;
	int	relEndeX		= 100;
	int	relEndY			= 100;
	
	@Override
	protected void zoomen() {
		pfeilBreite = (int) Math.round(originalPfeilBreite * zoomFaktor * bzf);
		startX = (int) Math.round((originalStartX + originalVX) * zoomFaktor
				* bzf);
		startY = (int) Math.round((originalStartY + originalVY) * zoomFaktor
				* bzf);
		endeX = (int) Math.round((originalEndeX + originalVX) * zoomFaktor
				* bzf);
		endeY = (int) Math.round((originalEndeY + originalVY) * zoomFaktor
				* bzf);
		
		if (startX < endeX) {
			xPos = startX - pfeilBreite * spitze;
			breite = endeX - startX + 2 * spitze * pfeilBreite;
		} else {
			xPos = endeX - pfeilBreite * 2;
			breite = startX - endeX + 2 * spitze * pfeilBreite;
		}
		if (startY < endeY) {
			yPos = startY - pfeilBreite * spitze;
			hoehe = endeY - startY + 2 * spitze * pfeilBreite;
		} else {
			hoehe = startY - endeY + 2 * spitze * pfeilBreite;
			yPos = endeY - pfeilBreite * spitze;
		}
		
		relStartX = startX - xPos;
		relStartY = startY - yPos;
		relEndeX = endeX - xPos;
		relEndY = endeY - yPos;
		setzeKoordinaten();
	}
	
	// Das Rechteck um die Verbindungslinie hat doppelte Pfeilbreite
	public void setzeEndpunkte(int neuesX1, int neuesY1, int neuesX2,
			int neuesY2) {
		originalStartX = neuesX1;
		originalStartY = neuesY1;
		originalEndeX = neuesX2;
		originalEndeY = neuesY2;
		zoomen();
		
		super.setzeDimensionen(xPos, yPos, breite, hoehe);
	}
	
	public void setzeBreite(int neueBreite) {
		originalPfeilBreite = neueBreite;
		setzeEndpunkte(originalStartX, originalStartY, originalEndeX,
				originalEndeY);
	}
	
	/**
	 * Enden setzen
	 * 
	 * @param start
	 * @param ende
	 */
	public void setzeEnde(boolean start, boolean ende) {
		spitzeStart = start;
		spitzeEnde = ende;
		setzeKoordinaten();
	}
	
	public void setzeKoordinaten() {
		
		int dx = relEndeX - relStartX;
		int dy = relEndY - relStartY;
		
		double f = ((double) 0.5 * pfeilBreite)
				/ (Math.sqrt(dx * dx + dy * dy));
		
		double bx = dy * f;
		double by = dx * f;
		
		xpoints[0] = relStartX;
		ypoints[0] = relStartY;
		
		if (spitzeStart) {
			xpoints[1] = relStartX + (int) (-spitze * bx + spitze * by);
			ypoints[1] = relStartY + (int) (+spitze * by + spitze * bx);
		} else {
			xpoints[1] = relStartX + (int) (-bx);
			ypoints[1] = relStartY + (int) (by);
		}
		
		xpoints[2] = relStartX + (int) (-bx + spitze * by);
		ypoints[2] = relStartY + (int) (by + spitze * bx);
		
		xpoints[3] = relEndeX + (int) (-bx - spitze * by);
		ypoints[3] = relEndY + (int) (by - spitze * bx);
		
		if (spitzeEnde) {
			xpoints[4] = relEndeX + (int) (-spitze * bx - spitze * by);
			ypoints[4] = relEndY + (int) (spitze * by - spitze * bx);
		} else {
			xpoints[4] = relEndeX + (int) (-bx);
			ypoints[4] = relEndY + (int) (by);
		}
		
		xpoints[5] = relEndeX;
		ypoints[5] = relEndY;
		
		if (spitzeEnde) {
			xpoints[6] = relEndeX + (int) (+spitze * bx - spitze * by);
			ypoints[6] = relEndY + (int) (-spitze * by - spitze * bx);
		} else {
			xpoints[6] = relEndeX + (int) (+bx);
			ypoints[6] = relEndY + (int) (-by);
		}
		
		xpoints[7] = relEndeX + (int) (+bx - spitze * by);
		ypoints[7] = relEndY + (int) (-by - spitze * bx);
		
		xpoints[8] = relStartX + (int) (+bx + spitze * by);
		ypoints[8] = relStartY + (int) (-by + spitze * bx);
		
		if (spitzeStart) {
			xpoints[9] = relStartX + (int) (+spitze * bx + spitze * by);
			ypoints[9] = relStartY + (int) (-spitze * by + spitze * bx);
		} else {
			xpoints[9] = relStartX + (int) (+bx);
			ypoints[9] = relStartY + (int) (-by);
		}
		repaint();
	}
	
}
