
//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.TransferHandler;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * <h1>Mehrzeiliges Textfeld</h1>: Es steht z.B. der Toolbox als einfacher
 * Editor oder als Console zur Verfuegung. </br>
 * 
 * setzeNurAnzeige() macht das Textfeld schreibgeschuetzt</br>
 * setzeEditierbar() laesst direktes Editieren zu <br/>
 * 
 * @author Hans Witt
 * 
 * @version Version 1.0(31.1.2011)
 * 
 */
public class TextfensterFarbe implements IComponente {

	// public static final int SELECT = 0; // ID = 0
	// public static final int UNSELECT = 1; // ID = 1

	private CConsolenfensterFarbe obj;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;

	// protected String anzeigeText = "Drueckmich";

	public int fontGroesse = -1;
	public String schriftFarbe = "schwarz";
	public String hintergrundFarbe = "weiss";
	public boolean gedrueckt = false;

	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public TextfensterFarbe() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public TextfensterFarbe(int neueBreite, int neueHoehe) {
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
	public TextfensterFarbe(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public TextfensterFarbe(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * Allgemeiner Konstruktor
	 * 
	 * Wird von allen anderen Konstruktoren aufgerufen
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public TextfensterFarbe(IContainer behaelter, int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		obj = new CConsolenfensterFarbe();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}

	public void setzeScrollbarDimension(int breite) {
		obj.setzeScrollbarDimension(breite);
	}

	
	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen
	 * Behaelter hinzuzufuegen
	 */
	public BasisComponente getBasisComponente() {
		return obj;
	}

	public void setzeSchriftName(String name) {
		obj.setzeFontName(name);
	}

	/**
	 * Setze Schriftgroesse des Fensters
	 * 
	 * Eine Schriftgroesse fuer das genze Fenster!
	 * 
	 * @param neueFontgroesse
	 */
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
	 * Nur Ausgabe
	 */
	public void setzeNurAnzeige() {
		obj.setEditable(false);
	}

	/*
	 * Das Fenster kann auch beschrieben werden
	 */
	public void setzeEditierbar() {
		obj.setEditable(true);
	}

	/**
	 * Textausgabe als Fehlermeldung -> rot
	 * 
	 * @param text
	 */
	public void printlnFehler(String text) {
		print("rot", text + "\n");
	}

	/**
	 * Textausgabe als Warnmeldung -> gelb
	 * 
	 * @param text
	 */
	public void printlnWarnung(String text) {
		print("gelb", text + "\n");
	}

	/**
	 * Textausgabe als Hinweis -> gruen
	 * 
	 * @param text
	 */
	public void printlnHinweis(String text) {
		print("gruen", text + "\n");
	}

	/**
	 * Textausgabe als Info -> schwarz
	 * 
	 * @param text
	 */
	public void printlnInfo(String text) {
		print("schwarz", text + "\n");
	}

	/**
	 * printLine in der uebergebenen Farbe
	 * 
	 * @param farbe
	 * @param text
	 */
	public void println(String farbe, String text) {
		print(farbe, text + "\n");
	}

	/**
	 * printLine in schwarz
	 * 
	 * @param text
	 */
	public void println(String text) {
		print(text + "\n");
	}

	/**
	 * print in der uebergebenen Farbe
	 * 
	 * @param farbe
	 * @param text
	 */
	public void print(String farbe, String text) {
		Color color = StaticTools.getColor(farbe);
		obj.append(color, text);
	}

	/**
	 * print in schwarz
	 * 
	 * @param text
	 */
	public void print(String text) {
		obj.append(text);
	}

	/**
	 * Lese den Inhalt des Textfelds
	 * 
	 * @return Feld von Strings
	 */
	public String[] leseZeilen() {
		return obj.leseZeilen();
	}

	public int anzahlZeilen() {
		return obj.getAnzahlZeilen();
	}

	/**
	 * Loeschen des Textfelds
	 */
	public void clear() {
		obj.clear();
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau","braun"
	 */
	public void setzeSchriftfarbe(String neueFarbe) {
		schriftFarbe = neueFarbe;
		obj.setzeSchriftfarbe(schriftFarbe);
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau","braun"
	 */
	public void setzeHintergrundfarbe(String neueFarbe) {
		hintergrundFarbe = neueFarbe;
		obj.setzeHintergrundfarbe(hintergrundFarbe);
	}

	/**
	 * neue Groesse
	 * 
	 * @param neueBreite
	 * @param neueHoehe
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

	public void setzeDragEnabled(boolean enable) {
		obj.setzeDragEnabled(enable);
	}

	public void setTransferHandler(TransferHandler transferHandler) {
		obj.setTransferHandler(transferHandler);
	}

	public void setzeFont(Font font) {
		obj.setzeFont(font);		
	}
	
	public void setzeTooltip(String toolstring) {
		obj.setzeTooltip(toolstring);		
	}

}

@SuppressWarnings("serial")
class CConsolenfensterFarbe extends BasisComponente {

	// private JToggleButton knopf;
	JTextPane text;
	boolean editable = false;
	JScrollPane scroller ;
	
	Color schriftfarbe = StaticTools.getColor("schwarz");
	Color hintergrundfarbe = StaticTools.getColor("weiss");

	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	public CConsolenfensterFarbe() {
		this.setLayout(new BorderLayout());
		text = new JTextPane();
		text.setBackground(hintergrundfarbe);
		text.setForeground(schriftfarbe);
		text.setEditable(editable);
		setzeSchriftgroesse(12);
		text.setFont(f);
		setJTextPaneFont(f,Color.BLACK);
		text.updateUI();
		scroller = new JScrollPane(text);
		this.add(scroller);
	}
	
	public void setzeScrollbarDimension(int breite) {
		// Get the scroll-bar and make it a bit wider.
		JScrollBar sb = scroller.getVerticalScrollBar();
		sb.setPreferredSize(new Dimension(breite, 0));
		sb = scroller.getHorizontalScrollBar();
		sb.setPreferredSize(new Dimension(0,breite));
	}


	public void setzeDragEnabled(boolean enable) {
		text.setDragEnabled(enable);
	}

	public void setTransferHandler(TransferHandler transferHandler) {
		text.setTransferHandler(transferHandler);
	}

	public void setzeTooltip(String toolstring) {
		text.setToolTipText(toolstring);
	}

	public void clear() {
		text.setText("");
	}

	public String[] leseZeilen() {
		String zeilen = text.getText();
		int anzahlZeilen = getAnzahlZeilen(zeilen);
		String[] ergebnisfeld = new String[anzahlZeilen];

		String aktuelleZeile = "";
		int zeile = 0;
		for (int i = 0; i < zeilen.length(); i++) {
			char c = zeilen.charAt(i);
			if (c == '\r') {
				// Nichts
			} else if (c == '\n') {
				ergebnisfeld[zeile] = aktuelleZeile;
				aktuelleZeile = "";
				zeile++;
			} else {
				aktuelleZeile += c;
			}
		}
		if (zeile < anzahlZeilen) {
			ergebnisfeld[zeile] = aktuelleZeile;
		}

		return ergebnisfeld;
	}

	public int getAnzahlZeilen() {
		String zeilen = text.getText();
		int zeilenzahl = 0;
		int len = zeilen.length();
		if (len > 0)
			zeilenzahl = 1;
		for (int i = 0; i < len; i++) {
			char c = zeilen.charAt(i);
			if (c == '\n')
				zeilenzahl++;
		}
		return zeilenzahl;
	}

	private int getAnzahlZeilen(String zeilen) {
		int zeilenzahl = 0;
		int len = zeilen.length();
		if (len > 0)
			zeilenzahl = 1;
		for (int i = 0; i < len; i++) {
			char c = zeilen.charAt(i);
			if (c == '\n')
				zeilenzahl++;
		}
		return zeilenzahl;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		text.setEditable(editable);
	}
	
	public void setzeFont(Font font) {
		text.setFont(f);
		repaint();
	}


	@Override
	public void setzeFontName(String name) {
		super.setzeFontName(name);
		text.selectAll();
		text.setFont(f);
		setJTextPaneFont(f,schriftfarbe);
		text.repaint();
		repaint();
	}

	public void setJTextPaneFont(Font font, Color c) {
		// Start with the current input attributes for the JTextPane. This
		// should ensure that we do not wipe out any existing attributes
		// (such as alignment or other paragraph attributes) currently
		// set on the text area.
		MutableAttributeSet attrs = text.getInputAttributes();

		// Set the font family, size, and style, based on properties of
		// the Font object. Note that JTextPane supports a number of
		// character attributes beyond those supported by the Font class.
		// For example, underline, strike-through, super- and sub-script.
		StyleConstants.setFontFamily(attrs, font.getFamily());
		StyleConstants.setFontSize(attrs, font.getSize());
		StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
		StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);

		// Set the font color
		StyleConstants.setForeground(attrs, c);

		// Retrieve the pane's document object
		StyledDocument doc = text.getStyledDocument();

		// Replace the style for the entire document. We exceed the length
		// of the document by 1 so that text entered at the end of the
		// document uses the attributes.
		doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
	}

	@Override
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		text.setFont(f);
		setJTextPaneFont(f,schriftfarbe);
		repaint();
		validate();
	}

	@Override
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		text.setFont(f);
		setJTextPaneFont(f,schriftfarbe);
		repaint();
	}

	public void setText(String s) {
		text.setEditable(true);
		text.setText(s);
		text.setEditable(editable);
	}

	public void append(String text) {
		append(schriftfarbe, text);
	}

	/**
	 * append text s with color c
	 * 
	 * @param cs
	 * @param s
	 */
	public void append(Color c, String s) { // better implementation--uses
		// StyleContext
		StyleContext sc = StyleContext.getDefaultStyleContext();

		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		int len = text.getDocument().getLength(); // same value as
		// getText().length();
		text.setEditable(true);
		text.setCaretPosition(len); // place caret at the end (with no
									// selection)
		text.setCharacterAttributes(aset, false);

		aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.FontSize, Math.round(originalFontGroesse));
		text.setCharacterAttributes(aset, false);

		text.replaceSelection(s); // there is no selection, so inserts at caret
		text.setEditable(editable);
	}

	@Override
	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		// knopf.setBackground(farbe);
		repaint();
	}

	public void setzeSchriftfarbe(String farbname) {
		schriftfarbe = StaticTools.getColor(farbname);
		text.setForeground(schriftfarbe);
		setJTextPaneFont(f,schriftfarbe);
		repaint();
	}

	public void setzeHintergrundfarbe(String farbname) {
		hintergrundfarbe = StaticTools.getColor(farbname);
		text.setBackground(hintergrundfarbe);
		repaint();
	}

	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
	}
}
