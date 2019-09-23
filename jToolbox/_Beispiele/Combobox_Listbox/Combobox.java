//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

/**
 * <h1>Combobox</h1>.<br />
 * Sie ist fuer Textobjekte (String) entworfen <br />
 * 
 * Bei editierbarer Combobox:<br />
 * leseAuswahlIndex() liefer -1, wenn der Eintrag nicht in der Auswahlliste ist. <br />
 * Die Auswahlliste wird NICHT automatisch ergaenzt <br /> <br />
 *  
 * Ist der Kommunikationslink gesetzt, meldet das Objekt eine <b><i>&Auml;nderung der Auswahl</i></b>.<br/>
 * 
 * @author Hans Witt
 * 
 * @version
 * Version 1.0 (30.3.2009)<br />
 * Version 5.0: (4.9.2010
 *  	   Entfernen fuer Graphikkomponente eingefuehrt<br />
 */
public class Combobox implements IComponente {
	
	private CCombobox	obj;
	public int		breite				= 0;
	public int		hoehe				= 0;
	public int		xPos				= 0;
	public int		yPos				= 0;
	public int		fontGroesse			= -1;
	public String	schriftFarbe		= "schwarz";
	public String	hintergrundFarbe	= "weiss";
	
	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public Combobox() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Combobox(int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe);
	}
	
	/**
	 * Konstruktor fuer leere Combobox
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Combobox(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}
	
	/**
	 * Konstruktor fuer Combobox.
	 * 
	 * @param texte
	 *            // Textfeld
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Combobox(String[] texte, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), texte, neuesX, neuesY, neueBreite,
				neueHoehe);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Combobox(IContainer behaelter) {
		this(behaelter, 0, 0, 150, 80);
	}
	
	public Combobox(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		obj = new CCombobox();
		behaelter.add(obj, 0);
		setzeSchriftfarbe(schriftFarbe);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}
	
	public Combobox(IContainer behaelter, String[] texte, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		obj = new CCombobox(texte);
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
	@Override
	public BasisComponente getBasisComponente() {
		return obj;
	}
	
	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * @param ID
	 */
	public void setzeID(int ID) {
		obj.setzeID(ID);
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj) {
		obj.setzeLink(linkObj);
	}


	/**
	 * Setzen des Kommunikationslinks<br/>
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * @param ID
	 * @param linkObj
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
		obj.setzeHintergrundfarbe(hintergrundFarbe);
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
	// Die Enden werden relativ zur aktuellen Position verschoben
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
	
	public void setzeEditierbar() {
		obj.setEditable(true);
	}
	
	public void setzeNichtEditierbar() {
		obj.setEditable(false);
	}
	
	public String leseAuswahl() {
		return (String) obj.getSelectedItem();
	}
	
	public void setzeAuswahl(Object anObject) {
		obj.setSelectedItem(anObject);
	}
	
	public int leseAuswahlIndex() {
		return obj.getSelectedIndex();
	}
	
	public void setzeAuswahlIndex(int Index) {
		obj.setSelectedIndex(Index);
	}
	
	public void textHinzufuegen(String text) {
		obj.addItem(text);
	}
	
	public void textHinzufuegenAnPosition(String text, int index) {
		obj.insertItemAt(text, index);
	}
	
	public void objektEntfernenAnPosition(int anIndex) {
		obj.removeItemAt(anIndex);
	}
	
	public void alleObjekteEntfernen() {
		obj.removeAllItems();
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
class CCombobox extends BasisComponente implements ActionListener {
	private JComboBox	combobox;
	
	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	public CCombobox() {
		this.setLayout(new BorderLayout());
		combobox = new JComboBox();
		setzeBasisfarbe("weiss");
		combobox.setFont(f);
		combobox.addActionListener(this);
		this.add(combobox);
	}
	
	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	@SuppressWarnings({ })
	public CCombobox(String[] Objects) {
		this.setLayout(new BorderLayout());
		combobox = new JComboBox(Objects);
		setzeBasisfarbe("weiss");
		combobox.setFont(f);
		combobox.addActionListener(this);
		this.add(combobox);
	}
	
	/**
	 * Nach Abschluss der Selektion
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (linkObj != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					linkObj.tuWas(id);
				}
			}).start();
		}
	}
	
	@Override
	public void setzeFontName(String name) {
		super.setzeFontName(name);
		combobox.setFont(f);
		repaint();
	}
	
	@Override
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		combobox.setFont(f);
		repaint();
	}
	
	@Override
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		combobox.setFont(f);
		repaint();
	}

	
	protected Color	schriftFarbe	= StaticTools.getColor("schwarz");
	
	public void setzeSchriftfarbe(String farbname) {
		schriftFarbe = StaticTools.getColor(farbname);
		combobox.setForeground(schriftFarbe);
		repaint();
	}
	
	public void setzeHintergrundfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		combobox.setBackground(farbe);
		repaint();
	}
	
	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
	}
	
	public String getSelectedItem() {
		return (String) combobox.getSelectedItem();
	}
	
	public void setSelectedItem(Object anObject) {
		combobox.setSelectedItem(anObject);
	}
	
	public int getSelectedIndex() {
		return combobox.getSelectedIndex();
	}
	
	public void setSelectedIndex(int anIndex) {
		combobox.setSelectedIndex(anIndex);
	}
	
	public void addItem(String anObject) {
		combobox.addItem(anObject);
	}
	
	public void insertItemAt(String anObject, int index) {
		combobox.insertItemAt(anObject, index);
	}
	
	public void removeItem(String anObject) {
		combobox.removeItem(anObject);
		
	}
	
	public void removeItemAt(int anIndex) {
		combobox.removeItemAt(anIndex);
	}
	
	public void removeAllItems() {
		combobox.removeAll();
	}
	
	public void setEditable(boolean editable) {
		combobox.setEditable(editable);
	}
	
}
