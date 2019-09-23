//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * <h1>Ausgabe von Text mit Richtung</h1>. <br />
 * 
 * @author Hans Witt <br />
 * 
 * @version Version 1.1 (14.7.2008) Hinzufuegen von Statusvariablen fuer
 *          Position ...<br />
 *          Version: 1.1.1 (17.7.2008) Neue Komponenten werden von Unten nach
 *          Oben aufgebaut, d.h.vor die alten gesetzt <br />
 *          Version: 3 (9.8.2008) ergaenzt fuer Behaelter fuer GUI-Elemente <br />
 *          Version: 3.1 (14.8.2008) Konstruktor auf int neuesX, int neuesY ,
 *          int neueBreite, int neueHoehe angepasst <br />
 *          Version : 3.2 (18.8.2008) Zustandsvariable auf protected gesetzt <br />
 *          Version 5.0: (4.9.2010 Entfernen fuer Graphikkomponente eingefuehrt <br />
 * 
 *          Destruktor entfernt Graphikkomponente automatisch bei gc() <br />
 * 
 */
public class AusgabeMitRichtung implements IComponente {
	private CAusgabeMitRichtung obj;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;
	public String anzeigeText = "Anzeige";
	public int fontGroesse = -1;
	public String farbe = "schwarz";

	private int ausrichtung = 1; // 0 = links , 1 = mitte , 2
									// =rechts
	public static final int LINKSBUENDIG = 0;
	public static final int ZENTRIERT = 1;
	public static final int RECHTSBUENDIG = 2;

	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public AusgabeMitRichtung() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public AusgabeMitRichtung(String neuerText, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, 0, 0, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuerText
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public AusgabeMitRichtung(String neuerText, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
				neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public AusgabeMitRichtung(IContainer behaelter) {
		this(behaelter, "Anzeige", 0, 0, 100, 50);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param neuerText
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public AusgabeMitRichtung(IContainer behaelter, String neuerText,
			int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		anzeigeText = neuerText;
		obj = new CAusgabeMitRichtung(anzeigeText, ausrichtung);
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		setzeFarbe(farbe);
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

	public void setzeAusgabetext(String neuerText) {
		anzeigeText = neuerText;
		obj.setzeText(anzeigeText);
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

	/**
	 * Ausrichtung des Texts setzen </br> Linksbuendig: 0 </br> Zentriert: 1
	 * </br> Rechtsbuendig: 2 </br>
	 */
	public void setzeAusrichtung(int i) {
		ausrichtung = i;
		obj.setzeAusrichtung(i);
	}

	public void setzeFarbe(String neueFarbe) {
		farbe = neueFarbe;
		obj.setzeSchriftFarbe(farbe);
	}

	/**
	 * Groesse des Anzeigefelds aendern *
	 */
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

	public void unsichtbarMachen() {
		obj.unsichtbarMachen();
	}

	public void sichtbarMachen() {
		obj.sichtbarMachen();
	}

	public double getWinkel() {
		return obj.getSchreibrichtung();
	}

	public void setzeSchreibrichtung(char richtung) {
		obj.setzeSchreibrichtung(richtung);
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
class CAusgabeMitRichtung extends BasisComponente {
	// private JLabel label;

	private int ausrichtung = SwingConstants.LEFT;
	private String text = "";
	private char richtung = 'O';

	// Fenstert

	public char getSchreibrichtung() {
		return richtung;
	}

	public void setzeSchreibrichtung(char richtung) {
		this.richtung = richtung;
		JPanel p = (JPanel) this.getParent();
		if (p == null)
			return;
		p.repaint();
		p.validate();
	}

	/**
	 * Konstruktor
	 */
	public CAusgabeMitRichtung(String text, int ausrichtung /**
	 * 0 = links , 1 =
	 * mitte , 2 =rechts
	 */
	) {
		this.setLayout(new BorderLayout());
		setzeText(text);
		// label = new JLabel(text);

		setzeAusrichtung(ausrichtung);

		// label.setForeground(farbe);
		// label.setFont(f);
		// this.add(label);
		repaint();
	}

	@Override
	protected void setzeFontName(String name) {
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


	public void setzeAusrichtung(int ausrichtung) {
		switch (ausrichtung) {
		case AusgabeMitRichtung.LINKSBUENDIG:
			this.ausrichtung = SwingConstants.LEFT;
			// label.setHorizontalAlignment(SwingConstants.LEFT);
			break;
		case AusgabeMitRichtung.ZENTRIERT:
			this.ausrichtung = SwingConstants.CENTER;
			// label.setHorizontalAlignment(SwingConstants.CENTER);
			break;
		case AusgabeMitRichtung.RECHTSBUENDIG:
			this.ausrichtung = SwingConstants.RIGHT;
			// label.setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		default:
			this.ausrichtung = SwingConstants.CENTER;
			// label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		repaint();
		// label.setVerticalAlignment(SwingConstants.CENTER);
	}

	public void setzeText(String s) {
		this.text = s;
		// label.setText(s);
		repaint();
	}

	public void setzeSchriftFarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		// label.setForeground(farbe);
		repaint();
	}

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

		FontRenderContext frc = g2.getFontRenderContext();
		TextLayout layout = new TextLayout(text, f, frc);
		Rectangle2D bounds = layout.getBounds();

		int strBreite = (int) bounds.getWidth();

		if (ausrichtung == SwingConstants.LEFT) {
			if (richtung == 'O') {
				g2.drawString(text, 0, hoehe / 2);
			} else if (richtung == 'S') {
				g2.rotate(Math.toRadians(90), 0, 0);
				g2.drawString(text, 0, -breite / 2);
			} else if (richtung == 'W') {
				g2.rotate(Math.toRadians(180), breite / 2, hoehe / 2);
				g2.drawString(text, 0, hoehe / 2);
			} else if (richtung == 'N') {
				g2.rotate(Math.toRadians(-90), 0, hoehe);
				g2.drawString(text, 0, hoehe + breite / 2);
			}
		} else if (ausrichtung == SwingConstants.CENTER) {
			if (richtung == 'O') {
				g2.drawString(text, (breite - strBreite) / 2, hoehe / 2);
			} else if (richtung == 'S') {
				g2.rotate(Math.toRadians(90), 0, 0);
				g2.drawString(text, (hoehe - strBreite) / 2, -breite / 2);
			} else if (richtung == 'W') {
				g2.rotate(Math.toRadians(180), breite / 2, hoehe / 2);
				g2.drawString(text, (breite - strBreite) / 2, hoehe / 2);
			} else if (richtung == 'N') {
				g2.rotate(Math.toRadians(-90), 0, hoehe);
				g2.drawString(text, (hoehe - strBreite) / 2, hoehe + breite / 2);
			}
		} else if (ausrichtung == SwingConstants.RIGHT) {
			if (richtung == 'O') {
				g2.drawString(text, breite - strBreite, hoehe / 2);
			} else if (richtung == 'S') {
				g2.rotate(Math.toRadians(90), 0, 0);
				g2.drawString(text, hoehe - strBreite, -breite / 2);
			} else if (richtung == 'W') {
				g2.rotate(Math.toRadians(180), breite / 2, hoehe / 2);
				g2.drawString(text, breite - strBreite, hoehe / 2);
			} else if (richtung == 'N') {
				g2.rotate(Math.toRadians(-90), 0, hoehe);
				g2.drawString(text, hoehe - strBreite-2, hoehe + breite / 2);
			}
		}

	}
}
