//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * <h1>Listbox</h1>:
 * Die Listbox ist fuer Textobjekte (String) entworfen.<br/>
 * 
 * <h2>Methoden:</h2
 * setzeEditierbar()<br/>
 * setzeNichtEditierbar()<br/>
 * leseAuswahl()<br/>
 * setzeAuswahl(Object anObject)<br/>
 * leseAuswahlIndex()<br/>
 * setzeAuswahlIndex(int Index)<br/>
 * textHinzufuegen(String text)<br/>
 * textHinzufuegenAnPosition(String text, int index)<br/>
 * objektEntfernenAnPosition(int anIndex)<br/>
 * alleObjekteEntfernen()<br/>
 * <br/>
 * bei editierbarer Combobox:<br/>
 * leseAuswahlIndex() liefer -1, wenn der Eintrag nicht in der Auswahlliste ist<br/>
 * Die Auswahlliste wird NICHT automatisch ergaenzt <br/>
 *  <br/> <br/>
 * Ist der Kommunikationslink gesetzt, meldet das Objekt eine <b><i>&Auml;nderung der Auswahl</i></b>.<br/>
 * <hr>
 * @author Hans Witt
 * 
 * @version
 * Version 1.0 (30.3.2009)<br/>
 * Version 5.0: (4.9.2010
 *  	   Entfernen fuer Graphikkomponente eingefuehrt<br/>
 *  
 *   	   Destruktor entfernt Graphikkomponente automatisch bei gc()<br/>
 */
public class Listbox implements IComponente {
	
	private CListbox	obj;
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
	public Listbox() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Listbox(int neueBreite, int neueHoehe) {
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
	public Listbox(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
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
	public Listbox(String[] texte, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), texte, neuesX, neuesY, neueBreite,
				neueHoehe);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Listbox(IContainer behaelter) {
		this(behaelter, 0, 0, 150, 80);
	}
	
	public Listbox(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		obj = new CListbox();
		behaelter.add(obj, 0);
		setzeSchriftfarbe(schriftFarbe);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}
	
	public Listbox(IContainer behaelter, String[] texte, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		obj = new CListbox(texte);
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
	 * 
	 * @param ID
	 *            Parameter, der als ID an die Callback-Funktion uebergeben
	 *            wird. In der Callbackfunktion kann dann durch die ID das
	 *            aufrufende Objekt identifiziert werden.
	 * 
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
	
	
	public String leseAuswahl() {
		return (String) obj.getSelectedValue();
	}
	
	/**
	 * Mehrfachauswahl
	 * @return eine String-Liste der Auswahlen
	 */
	public String[] leseAuswahlen() {
		return (String[]) obj.getSelectedValues();
	}
	
	public void setzeAuswahl(Object anObject) {
		obj.setSelectedValue(anObject);
	}
	
	public int leseAuswahlIndex() {
		return obj.getSelectedIndex();
	}
	
	public void setzeAuswahlIndex(int Index) {
		obj.setSelectedIndex(Index);
	}
	
	public int[] leseAuswahlIndices() {
		return obj.getSelectedIndices();
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
	
	public void setzeEinfachauswahl() {
		obj.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public void setzeMehrfachauswahl() {
		obj.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	public void auswahlAufheben() {
		obj.clearSelection();
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
class CListbox extends BasisComponente implements ListSelectionListener {
	private JList		listbox;
	// Create a list that allows adds and removes
	DefaultListModel	model	= new DefaultListModel();
	
	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	@SuppressWarnings({ })
	public CListbox() {
		this.setLayout(new BorderLayout());
		listbox = new JList(model);
		setzeBasisfarbe("weiss");
		listbox.setFont(f);
		listbox.addListSelectionListener(this);
		this.add(listbox);
	}
	
	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	@SuppressWarnings({ })
	public CListbox(String[] Objects) {
		this.setLayout(new BorderLayout());
		for (int i = 0; i < Objects.length; i++) {
			model.add(i, Objects[i]);
		}
		listbox = new JList(model);
		setzeBasisfarbe("weiss");
		listbox.setFont(f);
		listbox.addListSelectionListener(this);
		this.add(listbox);
	}
	
	/**
	 */
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
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
		listbox.setFont(f);
		repaint();
	}
	
	@Override
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		listbox.setFont(f);
		repaint();
	}
	
	protected Color	schriftFarbe	= StaticTools.getColor("schwarz");
	
	public void setzeSchriftfarbe(String farbname) {
		schriftFarbe = StaticTools.getColor(farbname);
		listbox.setForeground(schriftFarbe);
		repaint();
	}
	
	public void setzeHintergrundfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		listbox.setBackground(farbe);
		repaint();
	}
	
	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
	}
	
	public String getSelectedValue() {
		return (String) listbox.getSelectedValue();
	}
	
	public String[] getSelectedValues() {
		return (String[]) listbox.getSelectedValues();
	}
	
	public void setSelectedValue(Object anObject) {
		listbox.setSelectedValue(anObject, true);
	}
	
	public int getSelectedIndex() {
		return listbox.getSelectedIndex();
	}
	
	public int[] getSelectedIndices() {
		return listbox.getSelectedIndices();
	}
	
	public void setSelectedIndex(int anIndex) {
		listbox.setSelectedIndex(anIndex);
	}
	
	public void addItem(String anObject) {
		// Append an item
		int pos = listbox.getModel().getSize();
		model.add(pos, anObject);
	}
	
	public void insertItemAt(String anObject, int index) {
		model.add(index, anObject);
	}
	
	public void removeItemAt(int anIndex) {
		if (anIndex < model.getSize()) model.remove(anIndex);
	}
	
	public void removeAllItems() {
		model.clear();
	}
	
	public void setSelectionMode(int selectionMode) {
		listbox.setSelectionMode(selectionMode);
	}
	
	public void clearSelection() {
		listbox.clearSelection();
	}
	
}
