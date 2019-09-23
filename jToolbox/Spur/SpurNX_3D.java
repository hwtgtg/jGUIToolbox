//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Vector;

/**
 * <h1>SpurNX</H1>: Mehrere Spuren von Punkten<br />
 * Jede Spur hat eine eigene Farbe und einen eigenen Durchmesser.<br/>
 * Maximalanzahl von Punkten pro Spur: MAXPUNKTEPROSPUR Dann werden die ersten
 * geloescht.
 * <hr>
 * 
 * @author Hans Witt
 * 
 */
public class SpurNX_3D implements IComponente {
	public static int MAXPUNKTEPROSPUR = 10000;

	private CSpurNX_3D obj;
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
	public SpurNX_3D(int anzahl) {
		this(Zeichnung.gibZeichenflaeche(), anzahl);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public SpurNX_3D(int anzahl, int neueBreite, int neueHoehe) {
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
	public SpurNX_3D(int anzahl, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), anzahl, neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public SpurNX_3D(IContainer behaelter, int anzahl) {
		this(behaelter, anzahl, 0, 0, 50, 50);
	}

	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public SpurNX_3D(IContainer behaelter, int anzahl, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this.anzahl = anzahl;
		obj = new CSpurNX_3D(anzahl);
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

	public void setzeWinkelAlpha(double grad) {
		obj.setzeWinkelAlpha(grad);
	}

	public void setze3D() {
		obj.setze3D();
	}

	public void setzeXY() {
		obj.setzeXY();
	}

	public void setzeEinheit(double ausdehnung) {
		obj.setzeEinheit(ausdehnung);
	}

	public void hinzufuegen(int nrSpur, double x, double y, double z) {
		obj.addPoint(nrSpur, x, y, z);
	}

	public void mitKoordinatenachsen() {
		obj.mitKoordinatenachsen();
	}

	public void ohneKoordinatenachsen() {
		obj.ohneKoordinatenachsen();
	}

	public void setzeMittelpunkt(double x, double y, double z) {
		obj.setzeMittelpunkt(x, y, z);
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

	public void zentrieren() {
		obj.zentrieren();
	}
	
}

@SuppressWarnings({ "unchecked", "serial" })
class CSpurNX_3D extends BasisComponente {

	double skala = 1.0;
	final double w2 = Math.sqrt(2) / 2;

	double winkel = 0.0;
	double sinAlpha = 0;
	double cosAlpha = 1;

	public CSpurNX_3D() {
		super();
		setzeEinheit(1);
	}

	public void setzeWinkelAlpha(double grad) {
		winkel = grad * Math.PI / 180;
		sinAlpha = Math.sin(winkel);
		cosAlpha = Math.cos(winkel);
		repaint();
	}

	public void setzeEinheit(double ausdehnung) {
		skala = breite / ausdehnung;
	}

	boolean mitKoordinatenachsen = false;

	public void mitKoordinatenachsen() {
		mitKoordinatenachsen = true;
		repaint();
		validate();
	}

	public void ohneKoordinatenachsen() {
		mitKoordinatenachsen = false;
		repaint();
		validate();
	}

	boolean _3D = false;
	boolean _XY = true;

	public void setzeXY() {
		_3D = false;
		_XY = true;
		repaint();
	}

	public void setze3D() {
		_3D = true;
		_XY = false;
		repaint();
	}

	Point3D mitteZeichenflaeche = new Point3D(0, 0, 0);

	public void setzeMittelpunkt(double x, double y, double z) {
		mitteZeichenflaeche.setzePunkt(x, y, z);
		repaint();
	}

	public void zentrieren() {
		mitteZeichenflaeche.setzePunkt(breite / (2 * skala), hoehe
				/ (2 * skala), 0);
	}

	class Point3D {
		double x;
		double y;
		double z;

		Point3D(double x, double y, double z) {
			setzePunkt(x, y, z);
		}

		public void setzePunkt(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public int getX() {
			// Drehung um z-Achse
			double xW = cosAlpha * x - sinAlpha * y;
			double yW = sinAlpha * x + cosAlpha * y;

			if (_XY) {
				return (int) ((xW + mitteZeichenflaeche.x) * skala);
			} else if (_3D) {
				return (int) ((-(xW * w2) + yW + mitteZeichenflaeche.x) * skala);
			} else {
				return 0;
			}
		}

		public int getY() {
			// Drehung um z-Achse
			double yW = sinAlpha * x + cosAlpha * y;
			double xW = cosAlpha * x - sinAlpha * y;
			if (_XY) {
				return (int) ((-yW + mitteZeichenflaeche.y) * skala);
			} else if (_3D) {
				return (int) (((xW * w2) + (-z + mitteZeichenflaeche.y)) * skala);
			} else {
				return 0;
			}
		}
	}

	int anzahl = 20;
	Vector<Point3D>[] spuren;
	Color[] farben;
	int[] durchmesser;
	boolean mitRahmen = true;

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CSpurNX_3D(int anzahl) {
		this.anzahl = anzahl;
		spuren = new Vector[anzahl];
		farben = new Color[anzahl];
		durchmesser = new int[anzahl];

		for (int i = 0; i < anzahl; i++) {
			spuren[i] = new Vector<Point3D>();
			farben[i] = StaticTools.getColor("hellgrau");
			durchmesser[i] = 3;
		}
	}

	public void resetAllPoints() {
		for (int i = 0; i < anzahl; i++) {
			spuren[i].clear();
		}
		repaint();
	}

	public void addPoint(int nrSpur, double x, double y, double z) {

		if (nrSpur >= anzahl) {
			return;
		}

		if (nrSpur < 0) {
			return;
		}

		Point3D neu = new Point3D(x, y, z);

		if (this.getParent().isShowing()) {

			Graphics2D g2 = (Graphics2D) this.getGraphics();
			g2.setColor(farben[nrSpur]);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_MITER));

			if (gefuellt) {
				g2.fillOval(neu.getX() - (durchmesser[nrSpur] / 2), neu.getY()
						- (durchmesser[nrSpur] / 2), durchmesser[nrSpur],
						durchmesser[nrSpur]);
			} else {
				g2.drawOval(neu.getX() - (durchmesser[nrSpur] / 2), neu.getY()
						- (durchmesser[nrSpur] / 2), durchmesser[nrSpur],
						durchmesser[nrSpur]);
			}
		}

		if (spuren[nrSpur].size() >= SpurNX_3D.MAXPUNKTEPROSPUR) {
			spuren[nrSpur].remove(0);
		}
		spuren[nrSpur].add(neu);

	}

	public void resetPoints(int nrSpur) {
		if (nrSpur >= anzahl) {
			return;
		}

		if (nrSpur < 0) {
			return;
		}

		spuren[nrSpur].clear();
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

		if (mitKoordinatenachsen) {

		}

		if (mitRahmen) {
			g2.draw3DRect(0, 0, breite, hoehe, true);
		}

		Point3D temp;
		if (gefuellt) {
			for (int y = 0; y < anzahl; y++) {
				g2.setColor(farben[y]);

				for (int i = 0; i < spuren[y].size(); ++i) {
					temp = spuren[y].elementAt(i);
					g2.fillOval(temp.getX() - (durchmesser[y] / 2), temp.getY()
							- (durchmesser[y] / 2), durchmesser[y],
							durchmesser[y]);
				}
			}
		} else {
			for (int y = 0; y < anzahl; y++) {
				g2.setColor(farben[y]);
				for (int i = 0; i < spuren[y].size(); ++i) {
					temp = spuren[y].elementAt(i);
					g2.drawOval(temp.getX() - (durchmesser[y] / 2), temp.getY()
							- (durchmesser[y] / 2), durchmesser[y],
							durchmesser[y]);
				}
			}
		}
	}
}
