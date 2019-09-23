//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * <h1>Taste</h1>.
 *  
 *  <h2>Methoden</h2>
 *  Status: wurdeGedrueckt()</br>  
 *  Es wird gemeldet, ob seit der letzten Abfrage die Taste gedrueckt wurde.</br> 
 *  </br>
 *  Status zur&uuml;cksetzen:  ruecksetzenGedrueckt()</br>
 *  Warten auf Tastendruck: warteBisGedrueckt() <br/>
 *  </br>    
 *  Ist der Kommunikationslink gesetzt, meldet das Objekt das <b><i>Zeitsignal</i></b>. <br/><br/>
 *  <hr>
 * @author Hans Witt
 * 
 * @version
 * Version: 1.3 (21.7.2008)</br> 
 *       Ueber die Methode wurdeGedrueckt() kann der Status abgefragt werden.</br> 
 *       Es wird gemeldet, ob seit der letzten Abfrage die Taste gedrueckt wurde.</br> 
 *       ruecksetzenGedrueckt() setzt den Status zurueck</br> 
 *       warteBisGedrueckt() wartet bis gedrueckt wird</br> 
 * Version: 2 (3.8.2008)</br> 
 *       angepasst an geaendertes ITuWas</br> 
 * Version: 3 (9.8.2008) 
 *        ergaenzt fuer Behaelter fuer GUI-Elemente</br> 
 * Version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst</br> 		
 * Version: 4 (5.2.2012) Icon ergaenzt       
 */
public class Taste implements IComponente {
	
	private CTaste		obj;
	public int		breite				= 0;
	public int		hoehe				= 0;
	public int		xPos				= 0;
	public int		yPos				= 0;
	public String	anzeigeText			= "Drueckmich";
	public int		fontGroesse			= -1;
	public String	schriftFarbe		= "schwarz";
	public String	hintergrundFarbe	= "weiss";
	
	public boolean	gedrueckt			= false;
	
	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public Taste() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Taste(String neuerText, int neueBreite, int neueHoehe) {
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
	public Taste(String neuerText, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
				neueBreite, neueHoehe);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Taste(IContainer behaelter) {
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
	public Taste(IContainer behaelter, String neuerText, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		anzeigeText = neuerText;
		obj = new CTaste(anzeigeText);
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
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
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
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param linkObj
	 * @param ID
	 * 
	 */
	public void setzeLink(ITuWas linkObj, int ID) {
		obj.setzeLink(linkObj, ID);
	}
	
	
	public void setzeIcon(BilddateiAbstrakt bild){
		Icon icon = new ImageIcon(bild.leseBild()); 
		obj.setIcon(icon);
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
	 * Status der Komponente abrufen und ruecksetzen
	 */
	public boolean wurdeGedrueckt() {
		boolean wert = obj.istGedrueckt();
		ruecksetzenGedrueckt();
		return wert;
	}
	
	public void ruecksetzenGedrueckt() {
		gedrueckt = false;
		obj.resetGedrueckt();
	}
	
	public void warteBisGedrueckt() {
		while (!wurdeGedrueckt()) {
			StaticTools.warte(20); // warte 20ms
		}
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
class CTaste extends BasisComponente implements ActionListener {
	private JButton	taste;
	
	// Zustand der Komponente
	
	private boolean	gedrueckt	= false;
	
	// Font f = new Font("Dialog", Font.PLAIN, 18);
	
	@Override
	public void setzeFontName(String name) {
		super.setzeFontName(name);
		taste.setFont(f);
		repaint();
	}
	
	@Override
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		taste.setFont(f);
		repaint();
	}
	
	@Override
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		taste.setFont(f);
		repaint();
	}
	
	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	public CTaste(String text) {
		this.setLayout(new BorderLayout());
		taste = new JButton(text);
		taste.setMargin(new Insets(0,0,0,0));
		taste.setBackground(farbe);
		taste.setFont(f);
		taste.addActionListener(this);
		taste.updateUI();
		this.add(taste);
	}
	
	Thread thread = null ;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		gedrueckt = true;
		if (linkObj != null) {
			thread =  new Thread(new Runnable() {
				@Override
				public void run() {
					linkObj.tuWas(id);
				}
			});
			thread.start();
		}
	}
	
	/**
	 * @return gibt zurueck, ob der Knopf gedrueckt ist
	 */
	public boolean istGedrueckt() {
		return gedrueckt;
	}
	
	public void resetGedrueckt() {
		this.gedrueckt = false;
	}
	
	@Override
	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		taste.setBackground(farbe);
		repaint();
	}
	
	public void setzeSchriftfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		taste.setForeground(farbe);
		repaint();
	}
	
	public void setText(String s) {
		taste.setText(s);
	}
	
	public void setIcon(Icon icon){
		taste.setIcon(icon);
	}

	
	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
		
	}
}
