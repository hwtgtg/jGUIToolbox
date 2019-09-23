
//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 * <h1>Umschalttaste</h1>.
 * 
 * Ueber die Methode istGewaehlt() kann der Status abgefragt werden.<br />
 * <br/>
 * 
 * Ist der Kommunikationslink gesetzt, meldet das Objekt eine
 * <b><i>&Auml;nderung</i></b>.<br/>
 * Bei einer Aenderung zu <b>Select</b> wird tuWas(id) <br />
 * bei einer Aenderung zu <b>DesSelect</b> tuWas(<b>id + 1</b> ) aufgerufen.
 * <br />
 * Lokalen IDs als Konstanten. <br />
 * 
 * 
 * @author Hans Witt
 * 
 * @version Version 1.1 (14.7.2008) Hinzufuegen von Statusvariablen fuer
 *          Position ...<br />
 *          Version: 1.1.1 (17.7.2008) Neue Komponenten werden von Unten nach
 *          Oben aufgebaut, d.h.vor die alten gesetzt<br />
 *          Version: 2 (3.8.2008) angepasst an geaendertes ITuWas<br />
 *          Version: 3 (9.8.2008) ergaenzt fuer Behaelter fuer GUI-Elemente
 *          <br />
 *          Version: 3.1 (14.8.2008) Konstruktor auf int neuesX, int neuesY ,
 *          int neueBreite, int neueHoehe angepasst<br />
 *          Version: 3.2 (18.8.2008) Zustandsvariable auf protected gesetzt
 *          <br />
 *          Version 5.0: (4.9.2010 Entfernen fuer Graphikkomponente eingefuehrt
 *          <br />
 *          Destruktor entfernt Graphikkomponente automatisch bei gc()
 * 
 */
public class UmschaltTaste implements IComponente {

	public static final int SELECT = 0; // ID = 0
	public static final int UNSELECT = 1; // ID = 1

	private CUmschaltTaste obj;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;
	public String anzeigeText = "Drueckmich";
	public int fontGroesse = -1;
	public String schriftFarbe = "schwarz";
	public String hintergrundFarbe = "weiss";
	public boolean gedrueckt = false;

	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public UmschaltTaste() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public UmschaltTaste(String neuerText, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, 0, 0, neueBreite, neueHoehe);
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
	public UmschaltTaste(String neuerText, int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public UmschaltTaste(IContainer behaelter) {
		this(behaelter, "Drueckmich", 0, 0, 100, 50);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param neuerText
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public UmschaltTaste(IContainer behaelter, String neuerText, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		anzeigeText = neuerText;
		obj = new CUmschaltTaste(anzeigeText);
		behaelter.add(obj, 0);
		setzeSchriftfarbe(schriftFarbe);
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

	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param ID
	 */
	public void setzeID(int ID) {
		obj.setzeID(ID);
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * 
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj) {
		obj.setzeLink(linkObj);
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param linkObj
	 * @param ID
	 */
	public void setzeLink(ITuWas linkObj, int ID) {
		obj.setzeLink(linkObj, ID);
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

	public void setzeAusgabetext(String neuerText) {
		anzeigeText = neuerText;
		obj.setText(anzeigeText);
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
	public void setzeDimensionen(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		xPos = neuesX;
		yPos = neuesY;
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeDimensionen(xPos, yPos, breite, hoehe);
	}

	/**
	 * Status der Komponente abrufen
	 */
	public boolean istGewaehlt() {
		gedrueckt = obj.gewaehlt();

		return gedrueckt;
	}

	/**
	 * Status der Komponente auf >>gewaehlt<< setzen
	 */
	public void setzeGewaehlt() {
		gedrueckt = true;
		obj.setGewaehlt(gedrueckt);
	}

	/**
	 * Status der Komponente auf >>Nicht gewaehlt<< setzen
	 */
	public void setzeNichtGewaehlt() {
		gedrueckt = false;
		obj.setGewaehlt(gedrueckt);
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

	public void setzeIcon(Bilddatei bild) {
		Icon icon = new ImageIcon(bild.leseBild());
		obj.setzeIcon(icon);
	}

}

@SuppressWarnings("serial")
class CUmschaltTaste extends BasisComponente implements ItemListener {
	private JToggleButton knopf;

	// bei manuellem Setzten KEINE Meldung
	boolean manuell = false;

	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	public CUmschaltTaste(String text) {
		this.setLayout(new BorderLayout());
		knopf = new JToggleButton(text, false);
		knopf.setBackground(farbe);
		knopf.setMargin(new Insets(0,0,0,0));
		knopf.setFont(f);
		knopf.addItemListener(this);
		knopf.updateUI();
		this.add(knopf);
	}

	public void setzeIcon(Icon icon) {
		knopf.setIcon(icon);
	}

	@Override
	public void setzeFontName(String name) {
		super.setzeFontName(name);
		knopf.setFont(f);
		repaint();
	}

	@Override
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		knopf.setFont(f);
		repaint();
	}

	@Override
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		knopf.setFont(f);
		repaint();
	}

	public void setText(String s) {
		knopf.setText(s);
	}

	public void itemStateChanged(ItemEvent e) {
		// JToggleButton cb = (JToggleButton) e.getSource();
		if (manuell) {
			manuell = false;

			return;
		}

		int change = e.getStateChange();

		if (change == ItemEvent.SELECTED) {
			// TuWas Selected );
			if (linkObj != null) {
				{
					new Thread(new Runnable() {
						@Override
						public void run() {
							linkObj.tuWas(id + UmschaltTaste.SELECT);
						}
					}).start();
				}
			}
		} else if (change == ItemEvent.DESELECTED) {
			if (linkObj != null) {
				{
					new Thread(new Runnable() {
						@Override
						public void run() {
							linkObj.tuWas(id + UmschaltTaste.UNSELECT);
						}
					}).start();
				}
			}
		}
	}

	/**
	 * @return gibt zurueck, ob der Knopf gedrueckt ist
	 */
	public boolean gewaehlt() {
		return knopf.isSelected();
	}

	public void setGewaehlt(boolean checked) {
		manuell = true;
		knopf.setSelected(checked);
		manuell = false;
	}

	@Override
	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		knopf.setBackground(farbe);
		repaint();
	}

	public void setzeSchriftfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		knopf.setForeground(farbe);
		repaint();
	}

	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
	}
}
