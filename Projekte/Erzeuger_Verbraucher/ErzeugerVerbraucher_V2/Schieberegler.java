//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * <h1> Schieberegler </h1>.
 * 
 * <h2>Einstellungen:</h2>
 * setzeBereich(double neuesMin, double neuesMax, double neuerWert)<br />
 * und <br />
 * setzeWert(double neuerWert)<br />
 *
 * <h2>Lesen:</h2>
 * leseDoubleWert ()<br />
 * und leseIntWert( ) <br />
 * 
 * <h2>Status:</h2>
 *  Ueber die Methode hatSichGeaendert() kann der Status abgefragt werden.
 *  Statusabfrage, lesen und warteBisAenderung setzen den Status automatisch zurueck
 *
 * Ist der Kommunikationslink gesetzt, meldet das Objekt das <b><i>&Auml;nderungen</i></b>. <br/><br/>
 * 
 * <hr>
 * @author Hans Witt
 * @version
 * Version 5.0: (4.9.2010
 *  	   Entfernen fuer Graphikkomponente eingefuehrt<br/>
 *   	   Destruktor entfernt Graphikkomponente automatisch bei gc()<br/>
 */
public class Schieberegler implements IComponente {
	
	private CSchieberegler	obj;
	public int			breite				= 300;
	public int			hoehe				= 30;
	public int			xPos				= 0;
	public int			yPos				= 0;
	public String		hintergrundFarbe	= "dunkelgrau";
	
	public double		min					= 0;
	public double		max					= 100;
	public double		wert				= 50;
	
	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public Schieberegler() {
		this(Zeichnung.gibZeichenflaeche(), 'H');
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param ausrichtung
	 *            horizontal 'H' oder senkrecht 'S'
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Schieberegler(char ausrichtung, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), ausrichtung, 0, 0, neueBreite,
				neueHoehe);
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param ausrichtung
	 *            horizontal 'H' oder senkrecht 'S'
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Schieberegler(char ausrichtung, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), ausrichtung, neuesX, neuesY,
				neueBreite, neueHoehe);
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param ausrichtung
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param min
	 * @param max
	 * @param wert
	 */
	public Schieberegler(char ausrichtung, int neuesX, int neuesY,
			int neueBreite, int neueHoehe, double min, double max, double wert) {
		this(Zeichnung.gibZeichenflaeche(), ausrichtung, neuesX, neuesY,
				neueBreite, neueHoehe, min, max, wert);
		
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param ausrichtung
	 *            horizontal 'H' oder senkrecht 'S'
	 */
	public Schieberegler(IContainer behaelter, char ausrichtung) {
		this(behaelter, ausrichtung, 0, 0, 300, 30);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param ausrichtung
	 *            horizontal 'H' oder senkrecht 'S'
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Schieberegler(IContainer behaelter, char ausrichtung, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		this(behaelter, ausrichtung, neuesX, neuesY, 300, 30, 0, 100, 50);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param ausrichtung
	 *            horizontal 'H' oder senkrecht 'S'
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param min
	 * @param max
	 * @param wert
	 */
	public Schieberegler(IContainer behaelter, char ausrichtung, int neuesX,
			int neuesY, int neueBreite, int neueHoehe, double min, double max,
			double wert) {
		obj = new CSchieberegler(ausrichtung);
		
		behaelter.add(obj, 0);
		setzeFarbe(hintergrundFarbe);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		setzeBereich(min, max, wert);
		behaelter.validate();
		this.obj.repaint();
	}
	
	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen
	 * Behaelter hinzuzufuegen
	 */
	public BasisComponente getBasisComponente() {
		return obj;
	}
	
	public void setzeBereich(double neuesMin, double neuesMax, double neuerWert) {
		min = neuesMin;
		max = neuesMax;
		wert = neuerWert;
		obj.setzeBereich(neuesMin, neuesMax, neuerWert);
	}
	
	public void setzeWert(double neuerWert) {
		wert = neuerWert;
		obj.setzeWert(neuerWert);
	}
	
	public void setzeTeilung(int teilung){
		obj.setzeTeilung(teilung);
	}
	
	public double leseDoubleWert() {
		wert = obj.leseWert();
		ruecksetzenAenderung();
		return wert;
	}
	
	public int leseIntWert() {
		wert = obj.leseWert();
		ruecksetzenAenderung();
		return (int) wert;// Math.round(wert);
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
	
	public void setzeOrientierungHorizontal() {
		obj.setzeOrientierungHorizontal();
	}
	
	public void setzeOrientierungSenkrecht() {
		obj.setzeOrientierungSenkrecht();
	}
	
	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(String neueFarbe) {
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
	public boolean hatSichGeaendert() {
		boolean wert = obj.hatSichGeaendert();
		ruecksetzenAenderung();
		return wert;
	}
	
	public void ruecksetzenAenderung() {
		obj.resetAenderung();
	}
	
	public void warteBisAenderung() {
		while (!hatSichGeaendert()) {
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
class CSchieberegler extends BasisComponente implements ChangeListener {
	private JSlider		balken;
	
	// Zustand der Komponente
	protected double	min			= 0;
	protected double	max			= 100;
	protected double	wert		= 50;
	protected double	deltaWert	= 1;
	protected int		teilung		= 200;
	
	private boolean		aenderung	= false;
	
	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	public CSchieberegler(char ausrichtung) {
		this.setLayout(new BorderLayout());
		if (ausrichtung == 'H') {
			balken = new JSlider(Adjustable.HORIZONTAL, 0, teilung, teilung / 2);
		} else {
			balken = new JSlider(Adjustable.VERTICAL, 0, teilung, teilung / 2);
		}
		balken.setPaintTicks(true);
		balken.setMinorTickSpacing(5);
		balken.setMajorTickSpacing(10);
		
		balken.setBackground(farbe);
		balken.addChangeListener(this);
		balken.updateUI();
		this.add(balken);
	}
	
	public void setzeOrientierungHorizontal() {
		balken.setOrientation(Adjustable.HORIZONTAL);
	}
	
	public void setzeOrientierungSenkrecht() {
		balken.setOrientation(Adjustable.VERTICAL);
	}
	
	public void setzeBereich(double neuesMin, double neuesMax, double neuerWert) {
		min = neuesMin;
		max = neuesMax;
		deltaWert = (max - min) / teilung;
		setzeWert(neuerWert);
	}
	
	public void setzeWert(double neuerWert) {
		wert = neuerWert;
		balken.setValue((int) ((wert - min) / deltaWert));
	}
	
	public double leseWert() {
		wert = balken.getValue() * deltaWert + min;
		return wert;
	}
	
	
	public void setzeTeilung ( int teilung ){
		this.teilung = teilung ;
		balken.setMaximum(teilung);
		deltaWert = (max - min) / teilung;
		setzeWert(wert);
	}
	
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		aenderung = true;
		if (linkObj != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					linkObj.tuWas(id);
				}
			}).start();
		}
	}
	
	/**
	 * @return gibt zurueck, ob Sich der status geaendert hat
	 */
	public boolean hatSichGeaendert() {
		return aenderung;
	}
	
	public void resetAenderung() {
		this.aenderung = false;
	}
	
	@Override
	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		balken.setBackground(farbe);
		repaint();
	}
	
	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
		
	}
	
}
