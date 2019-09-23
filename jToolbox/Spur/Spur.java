//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Vector;

/**
 * <h1>Spur</h1>: 
 * Spur von Punkten im Fenster.<br />
 *
 * @author Hans Witt
 *
 */
public class Spur implements IComponente {
	private CSpur					obj;
	public int					breite		= 0;
	public int					hoehe		= 0;
	public int					xPos		= 0;
	public int					yPos		= 0;
	public boolean				sichtbar	= true;
	public boolean				gefuellt	= true;
	public String				farbe		= StaticTools.leseNormalfarbe();
	
	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public Spur() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Spur(int neueBreite, int neueHoehe) {
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
	public Spur(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Spur(IContainer behaelter) {
		this(behaelter, 0, 0, 50, 50);
	}
	
	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Spur(IContainer behaelter, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		obj = new CSpur();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}
	
	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen
	 * Behaelter hinzuzufuegen
	 */
	public BasisComponente getBasisComponente() {
		return obj;
	}
	
	public void hinzufuegen(int x, int y) {
		obj.addPoint(x, y);
	}
	
	public void loescheSpur() {
		obj.resetPoints();
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
	if (!Zeichnung.verweistesGUIElementEntfernen) return;
		if (obj != null) entfernen();
	}
	
}

@SuppressWarnings("serial")
class CSpur extends BasisComponente {
	Vector<Point>	punkte	= new Vector<Point>();
	
	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CSpur() {
	}
	
	public void addPoint(int x, int y) {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		
		if (gefuellt) {
			g2.fillOval(x - 1, y - 1, 3, 3);
		} else {
			g2.drawOval(x - 1, y - 1, 3, 3);
		}
		
		punkte.add(new Point(x, y));
	}
	
	public void resetPoints() {
		punkte.clear();
		repaint();
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
		g2.setColor(farbe);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));
		
		if (gefuellt) {
			for (int i = 0; i < punkte.size(); ++i) {
				g2.fillOval(punkte.elementAt(i).x - 1,
						punkte.elementAt(i).y - 1, 3, 3);
			}
		} else {
			for (int i = 0; i < punkte.size(); ++i) {
				g2.drawOval(punkte.elementAt(i).x - 1,
						punkte.elementAt(i).y - 1, 3, 3);
			}
		}
	}
}
