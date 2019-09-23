
//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;

/**
 * <h1>RadioTaste</h1>: Die Radiotaste muss in einen Radiobehaelter gesetzt
 * werden.<br/>
 * 
 * Der Radiobehaelter sorgt dafuer, dass nur eine Taste ausgewaehlt ist.<br/>
 * <a href="RadioBehaelter.html">Siehe Klasse RadioBehaelter</a> <br/>
 * <br/>
 * 
 * RadioTasten signalisieren zuerst dem Behaelter. Die ID der Taste wird
 * zusammen mit der ID des Behaelters weitergeleitet. <br />
 * 
 * 
 * <h2>Anwendung:</h2>
 * <ul>
 * <li>Radiobehaelter erzeugen: radiobehaelter = new
 * RadioBehaelter(0,152,200,47);</li>
 * <li>RadioTaste in den Behaelter setzen: r1 = new RadioTaste(radiobehaelter,
 * "ro", 7, 15, 60, 30);</li>
 * <li>ID der Taste setzen: r1.setzeID(3);</li>
 * <li>Anfangs gesetzte Taste festlegen: r1.setzeGewaehlt();</li>
 * <li>Optional: Kommunikations-Link vom Radiobehaelter auf ein anderes Ziel
 * setzen. Es wird dann statt dem Radiobehaelter das andere Objekt informiert.
 * Die Radiobehaelter-Funktionalit&auml;t bleibt erhalten</li>
 * </ul>
 * 
 * <hr>
 * 
 * @author Hans Witt
 * 
 * @version
 * 
 *          Version 5.0: (4.9.2010) Entfernen fuer Graphikkomponente eingefuehrt <br />
 * 
 *          Destruktor entfernt Graphikkomponente automatisch bei gc() <br />
 */
public class RadioTaste implements IComponente {

	public static final int NICHTGEWAEHLT = 1;
	public static final int GEWAEHLT = 0;

	private CRadioTaste obj;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;
	public String anzeigeText = "Check";
	public int fontGroesse = -1;
	public String hintergrundFarbe = StaticTools.leseNormalfarbe();
	public String schriftFarbe = "schwarz";

	private boolean gewaehlt = false;

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public RadioTaste(RadioBehaelter behaelter) {
		this(behaelter, "Radio", 0, 0, 50, 50);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param neuerText
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public RadioTaste(RadioBehaelter behaelter, String neuerText, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		anzeigeText = neuerText;
		obj = new CRadioTaste(anzeigeText);
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

	public void setzeAusgabetext(String neuerText) {
		anzeigeText = neuerText;
		obj.setText(anzeigeText);
	}

	/**
	 * Status der Komponente abrufen
	 */
	public boolean istGewaehlt() {
		gewaehlt = obj.gewaehlt();
		return gewaehlt;
	}

	/**
	 * Status der Komponente auf >>gewaehlt<< setzen
	 */
	public void setzeGewaehlt() {
		gewaehlt = true;
		obj.setGewaehlt(gewaehlt);
	}

	/**
	 * Status der Komponente auf >>Nicht gewaehlt<< setzen
	 */
	public void setzeNichtGewaehlt() {
		gewaehlt = false;
		obj.setGewaehlt(gewaehlt);
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
		if (!Zeichnung.verweistesGUIElementEntfernen)
			return;
		if (obj != null)
			entfernen();
	}
	
	public void setzeTooltip(String toolstring) {
		obj.setzeTooltip(toolstring);		
	}


	// Eine Moeglichkeit, bei Ableitungen eine Bearbeitungsmehtode zu Ueberschreiben
	// Dazu wird als linkziel auftrag angegeben.
	// Die Methode heist auch auftrag;
	protected ITuWas auftrag = new ITuWas() {
		@Override
		public void tuWas(int ID) {
			final int linkID = ID ;
			new Thread(new Runnable() {
				public void run() {
					ausfuehren(linkID);
				}
			}).start();
		}

	};

	protected void ausfuehren(int ID) {

	}

}

@SuppressWarnings("serial")
class CRadioTaste extends BasisComponente implements ItemListener {
	private JRadioButton knopf;

	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	public CRadioTaste(String text) {
		this.setLayout(new BorderLayout());
		knopf = new JRadioButton(text, false);
		knopf.setFont(f);
		knopf.addItemListener(this);
		this.add(knopf);
		repaint();
	}

	public void setzeTooltip(String toolstring) {
		setToolTipText(toolstring);
	}

	public void itemStateChanged(ItemEvent e) {
		// JCheckBox cb = (JCheckBox) e.getSource();
		int change = e.getStateChange();
		if (change == ItemEvent.SELECTED) {
			// TuWas Selected );
			if (linkObj != null) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						linkObj.tuWas(id + RadioTaste.GEWAEHLT);
					}
				}).start();
			}
		} else if (change == ItemEvent.DESELECTED) {
			// TuWas DeSelected );
			if (linkObj != null) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						linkObj.tuWas(id + RadioTaste.NICHTGEWAEHLT);
					}
				}).start();
			}
		}
	}

	@Override
	public void setzeFontName(String name) {
		super.setzeFontName(name);
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

	/**
	 * @return gibt zurueck, ob der Knopf gedrueckt ist
	 */
	public boolean gewaehlt() {
		return knopf.isSelected();
	}

	public void setGewaehlt(boolean checked) {
		knopf.setSelected(checked);
	}

	public void setText(String s) {
		knopf.setText(s);
	}

	@Override
	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		knopf.setBackground(farbe);
		repaint();
	}

	public void setzeSchriftfarbe(String farbname) {
		knopf.setForeground(StaticTools.getColor(farbname));
		repaint();
	}

	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
	}

	public JRadioButton getButton() {
		return knopf;
	}
}
