//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Vector;

/**
 * <h1>SpurNX</H1>: Mehrere Spuren von Punkten<br />
 * Jede Spur hat eine eigene Farbe und einen eigenen Durchmesser.<br/>
 * <hr>
 * 
 * @author Hans Witt
 * 
 */
public class SpurNX implements IComponente {
	private CSpurNX obj;
	public int anzahl = 4;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;
	public boolean sichtbar = true;
	public boolean gefuellt = true;
	public String farbe = StaticTools.leseNormalfarbe();

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public SpurNX(int anzahl) {
		this(Zeichnung.gibZeichenflaeche(), anzahl);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public SpurNX(int anzahl, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), anzahl, 0, 0, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public SpurNX(int anzahl, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), anzahl, neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public SpurNX(IContainer behaelter, int anzahl) {
		this(behaelter, anzahl, 0, 0, 50, 50);
	}

	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public SpurNX(IContainer behaelter, int anzahl, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this.anzahl = anzahl;
		obj = new CSpurNX(anzahl);
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

	public void hinzufuegen(int nrSpur, int x, int y) {
		obj.addPoint(nrSpur, x, y);
	}

	public void loescheSpur(int nrSpur) {
		obj.resetPoints(nrSpur);
	}

	public void loescheSpuren() {
		obj.resetAllPoints();
	}

	public void mitRahmen() {
		obj.mitRahmen = true;
		obj.repaint();
	}

	public void ohneRahmen() {
		obj.mitRahmen = false;
		obj.repaint();
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(int nrSpur, String neueFarbe) {
		farbe = neueFarbe;
		obj.setzeFarbe(nrSpur, neueFarbe);
	}

	public void setzeDurchmesser(int nrSpur, int durchmesser) {
		obj.setzeDurchmesser(nrSpur, durchmesser);
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
	
}

@SuppressWarnings({ "unchecked", "serial" })
class CSpurNX extends BasisComponente {
	int anzahl = 20;
	Vector<Point>[] spuren;
	Color[] farben;
	int[] durchmesser;
	boolean mitRahmen = true;

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CSpurNX(int anzahl) {
		this.anzahl = anzahl;
		spuren = new Vector[anzahl];
		farben = new Color[anzahl];
		durchmesser = new int[anzahl];

		for (int i = 0; i < anzahl; i++) {
			spuren[i] = new Vector<Point>();
			farben[i] = StaticTools.getColor("hellgrau");
			durchmesser[i] = 3;
		}
	}

	public void resetAllPoints() {
		for (int i = 0; i < anzahl; i++) {
			((Vector<Point>) spuren[i]).clear();
		}

		repaint();
	}

	public void addPoint(int nrSpur, int x, int y) {
		if (nrSpur >= anzahl) {
			return;
		}

		if (nrSpur < 0) {
			return;
		}

		if (this.getParent().isShowing()) {

			Graphics2D g2 = (Graphics2D) this.getGraphics();
			g2.setColor(farben[nrSpur]);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_MITER));

			if (gefuellt) {
				g2.fillOval(x - (durchmesser[nrSpur] / 2), y
						- (durchmesser[nrSpur] / 2), durchmesser[nrSpur],
						durchmesser[nrSpur]);
			} else {
				g2.drawOval(x - (durchmesser[nrSpur] / 2), y
						- (durchmesser[nrSpur] / 2), durchmesser[nrSpur],
						durchmesser[nrSpur]);
			}
		}
		((Vector<Point>) spuren[nrSpur]).add(new Point(x, y));
	}

	public void resetPoints(int nrSpur) {
		if (nrSpur >= anzahl) {
			return;
		}

		if (nrSpur < 0) {
			return;
		}

		((Vector<Point>) spuren[nrSpur]).clear();
		repaint();
	}

	public void setzeFarbe(int nrSpur, String neueFarbe) {
		if (nrSpur >= anzahl) {
			return;
		}

		if (nrSpur < 0) {
			return;
		}

		farben[nrSpur] = StaticTools.getColor(neueFarbe);
		repaint();
	}

	public void setzeDurchmesser(int nrSpur, int durchmesser) {
		if (nrSpur >= anzahl) {
			return;
		}

		if (nrSpur < 0) {
			return;
		}

		this.durchmesser[nrSpur] = durchmesser;
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
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));

		if (mitRahmen) {
			g2.draw3DRect(0, 0, breite, hoehe, true);
		}

		if (gefuellt) {
			for (int y = 0; y < anzahl; y++) {
				g2.setColor(farben[y]);

				for (int i = 0; i < ((Vector<Point>) spuren[y]).size(); ++i) {
					g2.fillOval(((Vector<Point>) spuren[y]).elementAt(i).x
							- (durchmesser[y] / 2),
							((Vector<Point>) spuren[y]).elementAt(i).y
									- (durchmesser[y] / 2), durchmesser[y],
							durchmesser[y]);
				}
			}
		} else {
			for (int y = 0; y < anzahl; y++) {
				g2.setColor(farben[y]);

				for (int i = 0; i < ((Vector<Point>) spuren[y]).size(); ++i) {
					g2.drawOval(((Vector<Point>) spuren[y]).elementAt(i).x
							- (durchmesser[y] / 2),
							((Vector<Point>) spuren[y]).elementAt(i).y
									- (durchmesser[y] / 2), durchmesser[y],
							durchmesser[y]);
				}
			}
		}
	}
}
