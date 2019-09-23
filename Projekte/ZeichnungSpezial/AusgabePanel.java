//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * <h1>AusgabePanel zur Ausgabe von Text </h1>.<br />
 * 
 * @author Hans Witt<br />
 * 
 * @version
 * Version 1.1 (14.7.2008) 
 *        Hinzufuegen von Statusvariablen fuer Position ...<br />
 * Version: 1.1.1 (17.7.2008) 
 *        Neue Komponenten werden von Unten nach Oben aufgebaut, d.h.vor die alten gesetzt<br />
 * Version: 3 (9.8.2008) 
 *        ergaenzt fuer Behaelter fuer GUI-Elemente<br />
 * Version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst<br />		
 * Version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt <br />
 * Version 5.0: (4.9.2010
 *  	   Entfernen fuer Graphikkomponente eingefuehrt<br />
 *  
 *   	   Destruktor entfernt Graphikkomponente automatisch bei gc()<br />
 * 
 */
public class AusgabePanel implements IComponente{

	private CAusgabePanel obj;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;
	public String anzeigeText = "Anzeige";
	public int fontGroesse = -1;
	public String hintergrundFarbe = StaticTools.leseNormalfarbe();
	public String schriftFarbe = "schwarz";

	private int ausrichtung = 1; // 0 = links , 1 = mitte , 2 =rechts

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public AusgabePanel() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public AusgabePanel(String neuerText, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, 0, 0, neueBreite,
				neueHoehe);
	}
	
	/**
	 * 
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuerText
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public AusgabePanel(String neuerText, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
				neueBreite, neueHoehe);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public AusgabePanel(IContainer behaelter) {
		this(behaelter, "Anzeige", 0, 0, 100, 50);
	}
	
	public AusgabePanel(IContainer behaelter, String neuerText, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		anzeigeText = neuerText;
		obj = new CAusgabePanel(anzeigeText, ausrichtung);
		behaelter.add(obj, 0);
		setzeHintergrundfarbe(hintergrundFarbe);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
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
	
	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeSchriftfarbe(String neueFarbe) {
		schriftFarbe = neueFarbe;
		obj.setzeSchriftfarbe(schriftFarbe);
	}
	
	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeHintergrundfarbe(String neueFarbe) {
		hintergrundFarbe = neueFarbe;
		obj.setzeBasisfarbe(hintergrundFarbe);
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
	
	public void setzeTooltip(String toolstring) {
		obj.setzeTooltip(toolstring);		
	}

	
}

@SuppressWarnings("serial")
class CAusgabePanel extends BasisComponente {
	
	private JPanel	panel;
	private JLabel	label;
	
	public void setzeTooltip(String toolstring) {
		label.setToolTipText(toolstring);
		panel.setToolTipText(toolstring);
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
		panel.setFont(f);
		repaint();
	}
	
	@Override
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		label.setFont(f);
		panel.setFont(f);
		repaint();
	}
	
	public void setzeAusrichtung(int ausrichtung) {
		switch (ausrichtung) {
		case 0:
			label.setHorizontalAlignment(SwingConstants.LEFT);
			break;
		case 1:
			label.setHorizontalAlignment(SwingConstants.CENTER);
			break;
		case 2:
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		default:
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
	
	/**
	 * Konstruktor
	 */
	public CAusgabePanel(String text, int ausrichtung /*
													 * 0 = links , 1 = mitte , 2
													 * =rechts
													 */) {
		this.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(farbe);
		
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		
		label = new JLabel(text);
		
		switch (ausrichtung) {
		case 0:
			label.setHorizontalAlignment(SwingConstants.LEFT);
			break;
		case 1:
			label.setHorizontalAlignment(SwingConstants.CENTER);
			break;
		case 2:
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		default:
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		label.setVerticalAlignment(SwingConstants.CENTER);
		
		label.setFont(f);
		panel.add(label, BorderLayout.CENTER);
		this.add(panel);
		repaint();
	}
	
	public void setText(String s) {
		label.setText(s);
	}
	
	@Override
	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		panel.setBackground(farbe);
		repaint();
	}
	
	public void setzeSchriftfarbe(String farbname) {
		label.setForeground(StaticTools.getColor(farbname));
		repaint();
	}
	
	@Override
	public void paintComponentSpezial(Graphics g) {
		// nichts zu tun
	}
}
