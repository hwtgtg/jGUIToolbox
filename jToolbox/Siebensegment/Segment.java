//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * Segment als Teil einer Siebensegmentanzeige
 * @author Witt
 *
 */
public class Segment implements IComponente {
	
	private CSegment	obj;
	public int		breite		= 0;
	public int		hoehe		= 0;
	public int		xPos		= 0;
	public int		yPos		= 0;
	public boolean	sichtbar	= true;
	public boolean	gefuellt	= true;
	public String	farbe		= "rot";
	public String	farbeRand	= StaticTools.leseNormalfarbe();
	
	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public Segment() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Segment(int neueBreite, int neueHoehe) {
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
	public Segment(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Segment(IContainer behaelter) {
		this(behaelter, 0, 0, 50, 25);
	}
	
	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Segment(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		obj = new CSegment();
		behaelter.add(obj, 0);
		setzeFarbe("rot");
		setzeRandfarbe("grau");
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
	
	/**
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(String neueFarbe) {
		farbe = neueFarbe;
		obj.setzeBasisfarbe(neueFarbe);
	}
	
	/**
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeRandfarbe(String neueFarbe) {
		farbeRand = neueFarbe;
		obj.setzeRandfarbe(farbeRand);
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
class CSegment extends BasisComponente {
	// Zustand der Komponente
	protected Color	randFarbe	= StaticTools.getColor("hellgrau");
	
	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CSegment() {
		
	}
	
	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	
	@Override
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width - 2;
		hoehe = getSize().height - 2;
		g.setColor(farbe);
		
		if (hoehe > 2 * breite) {
			int[] xpoints = { breite / 2, breite, breite, breite / 2, 1, 1 };
			int[] ypoints = { 1, breite / 2, hoehe - breite / 2, hoehe,
					hoehe - breite / 2, breite / 2 };
			// int[] xpoints2 = { breite/2 , breite - 1 , breite -1 , breite /2
			// , 2 , 2 };
			// int[] ypoints2 = { 2 , breite /2 +1 , hoehe - breite / 2 -1 ,
			// hoehe-1 , hoehe - breite / 2 - 1 , breite / 2+1 };
			if (gefuellt) {
				g.setColor(farbe);
				g2.fillPolygon(new Polygon(xpoints, ypoints, 6));
			} else {
				g.setColor(randFarbe);
				g2.drawPolygon(new Polygon(xpoints, ypoints, 6));
				// g2.drawPolygon(new Polygon(xpoints2, ypoints2, 6));
			}
		} else if (breite > 2 * hoehe) {
			int[] xpoints = { 1, hoehe / 2, breite - hoehe / 2, breite,
					breite - hoehe / 2, hoehe / 2 };
			int[] ypoints = { hoehe / 2, 1, 1, (hoehe / 2), hoehe, hoehe };
			// int[] xpoints2 = { 2 , hoehe/2+1, breite - hoehe/2-1 , breite-1
			// ,breite - hoehe/2 - 1 , hoehe/2 +1 };
			// int[] ypoints2 = { hoehe/2, 2 , 2 , (hoehe/2) , hoehe -1 , hoehe
			// -1 };
			if (gefuellt) {
				g.setColor(farbe);
				g2.fillPolygon(new Polygon(xpoints, ypoints, 6));
			} else {
				g.setColor(randFarbe);
				g2.drawPolygon(new Polygon(xpoints, ypoints, 6));
				// g2.drawPolygon(new Polygon(xpoints2, ypoints2, 6));
			}
		} else {
			int[] xpoints = { 1, (breite / 3), 2 * (breite / 3), breite,
					2 * (breite / 3), (breite / 3) };
			int[] ypoints = { (hoehe) / 2, 1, 1, (hoehe / 2), hoehe, hoehe };
			// int[] xpoints2 = { 2 , (breite / 3)+1, 2*(breite / 3)-1, breite
			// -1 ,2*(breite / 3)-1 , (breite / 3) +1};
			// int[] ypoints2 = { (hoehe)/2, 2 , 2 , (hoehe/2) , hoehe -1 ,
			// hoehe -1 };
			if (gefuellt) {
				g2.fillPolygon(new Polygon(xpoints, ypoints, 6));
			} else {
				g2.drawPolygon(new Polygon(xpoints, ypoints, 6));
				// g2.drawPolygon(new Polygon(xpoints2, ypoints2, 6));
			}
		}
	}
	
	public void setzeRandfarbe(String farbname) {
		randFarbe = StaticTools.getColor(farbname);
		repaint();
	}
	
}
