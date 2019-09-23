
//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * <h1>Ausgabe von Text</h1>. <br />
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
public class Ausgabe implements IComponente {
	private CAusgabe obj;
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
	public Ausgabe() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Ausgabe(String neuerText, int neueBreite, int neueHoehe) {
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
	public Ausgabe(String neuerText, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
				neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Ausgabe(IContainer behaelter) {
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
	public Ausgabe(IContainer behaelter, String neuerText, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		anzeigeText = neuerText;
		obj = new CAusgabe(anzeigeText, ausrichtung);
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
		obj.setText(anzeigeText);
	}

	public void setzeSchriftgroesse(int neueFontgroesse) {
		fontGroesse = neueFontgroesse;
		obj.setzeSchriftgroesse(fontGroesse);
	}

	public void setzeSchriftName(String name) {
		obj.setzeFontName(name);
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
	

	public void setzeTooltip(String toolstring) {
		obj.setzeTooltip(toolstring);		
	}
	

}

@SuppressWarnings("serial")
class CAusgabe extends BasisComponente {
	private JLabel label;

	/**
	 * Konstruktor
	 */
	public CAusgabe(String text, int ausrichtung /**
	 * 0 = links , 1 = mitte , 2
	 * =rechts
	 */
	) {
		this.setLayout(new BorderLayout());
		label = new JLabel(text);

		setzeAusrichtung(ausrichtung);

		label.setForeground(farbe);
		label.setFont(f);
		this.add(label);
		repaint();
	}
	
	public void setzeTooltip(String toolstring) {
		label.setToolTipText(toolstring);
	}



	@Override
	public void setzeFontName(String name) {
		super.setzeFontName(name);
		label.setFont(f);
		repaint();
	}

	@Override
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		label.setFont(f);
		repaint();
	}

	@Override
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		label.setFont(f);
		repaint();
	}

	public void setzeAusrichtung(int ausrichtung) {
		switch (ausrichtung) {
		case Ausgabe.LINKSBUENDIG:
			label.setHorizontalAlignment(SwingConstants.LEFT);
			break;
		case Ausgabe.ZENTRIERT:
			label.setHorizontalAlignment(SwingConstants.CENTER);
			break;
		case Ausgabe.RECHTSBUENDIG:
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		default:
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		label.setVerticalAlignment(SwingConstants.CENTER);
	}

	public void setText(String s) {
		label.setText(s);
	}

	public void setzeSchriftFarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		label.setForeground(farbe);
		repaint();
	}

	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
	}
}
