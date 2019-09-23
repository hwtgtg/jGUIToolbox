
//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * <h1>Tabelle:</h1> Tabelle fuer Stringobjekte mit und ohne Checkbox.<br/>
 * 
 * <br/>
 * Ist der Kommunikationslink gesetzt, meldet das Objekt Select, Edit,
 * Aenderungen des Struktur
 * <hr>
 * 
 * @author Hans Witt
 * 
 * @version Version 1.1 (26.02.2012)<br/>
 * Automatische Edit-Ende bei Fokusverlusst<br/>
 * Version Version 1.0 (30.4.2010)
 * 
 */
public class Tabelle implements IComponente {

	public static final int ZELLENSELECT = 0; // tuWasID
	public static final int SPALTENSELECT = 1; // tuWasID
	public static final int ZEILENSELECT = 2; // tuWasID
	public static final int TABELLENSTRUKTURAENDERUNG = 4; // tuWasID
	public static final int EDITENDE = 5; // tuWasID

	// wird bei der getTextExtended gesetzt
	// Fuer setExtended: Eckige Klammern umschliessen CheckBox-Wert
	// interpretierte Werte:
	// [true][false][wahr][falsch][ja][nein][richtig][ok][yes][no]
	public static String CHEKBOXTRUE = "[ja]"; // wird bei der getTextExtended
												// gesetzt
	public static String CHEKBOXFALSE = "[nein]"; // wird bei der
													// getTextExtended
													// gesetzt

	private CTabelle obj;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;
	public int fontGroesse = -1;
	public String schriftFarbe = "schwarz";
	public String hintergrundFarbe = "weiss";

	public int zeilen = 4;
	public int spalten = 4;

	/**
	 * Erzeugt eine Tabelle durch Angabe der Zeilen und Spalten.
	 * 
	 * @param zeilen
	 *            Anzahl Zeilen
	 * @param spalten
	 *            Anzahl Spalten
	 * @param neuesX
	 *            Position und Dimension
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Tabelle(int zeilen, int spalten, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), zeilen, spalten, neuesX, neuesY,
				neueBreite, neueHoehe);
	}

	/**
	 * Erzeugt eine Tabelle aus einem Stringfeld fuer den Kopf und einem
	 * zweidimensionalen Stringfeld fuer die Zeilen.
	 * 
	 * Die Anzahl Spalten wird durch das Kopf-Stringfeld festgelegt. Ist das
	 * Kopf-Stringfeld <strong>null</strong>, so legt die erste Datenzeile die
	 * Anzahl der Spalten fest.</br> Ist das Kopf-Feld null, so werden die
	 * Kopfzeilen mit A ,B ,C ... (wie bei einem Tabellenkalkulator)
	 * bezeichnet.</br>
	 * 
	 * Bitte beachten: Der Konstruktor muss dann mit </br>
	 * 
	 * <strong>Tabelle( (String[]) null, ... )</strong>
	 * 
	 * aufgerufen werden, da die Zuordnung zu einem Konstruktoren sonst nicht
	 * eindeutig ist.
	 * 
	 * @param spaltenkopf
	 *            Bezeichnungen der Spaltenkoepfe
	 * @param zellen
	 *            Feld der Zelleninhalte
	 * @param neuesX
	 *            Dimensionen
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Tabelle(String[] spaltenkopf, String[][] zellen, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), spaltenkopf, zellen, neuesX,
				neuesY, neueBreite, neueHoehe);
	}

	/**
	 * Erzeugt eine Tabelle durch Angabe der Zeilen und Spalten.
	 * 
	 * @param behaelter
	 *            Behaelter fuer die Tabelle
	 * @param zeilen
	 * @param spalten
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Tabelle(IContainer behaelter, int zeilen, int spalten, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		obj = new CTabelle(zeilen, spalten);
		behaelter.add(obj, 0);
		setzeSchriftfarbe(schriftFarbe);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}

	/**
	 * Erzeugt Tabelle aus einem Stringfeld fuer den Kopf und einem
	 * zweidimensionalen Stringfeld fuer die Zeilen. Der erste Parameter ist der
	 * Behaelter, in dem die Tabelle erzeugt wird.
	 * 
	 * Die Anzahl Spalten wird durch das Kopf-Stringfeld festgelegt. Ist das
	 * Kopf-Stringfeld <strong>null</strong>, so legt die erste Datenzeile die
	 * Anzahl der Spalten fest.</br> Ist das Kopf-Feld null, so werden die
	 * Kopfzeilen mit A ,B ,C ... (wie bei einem Tabellenkalkulator)
	 * bezeichnet.</br>
	 * 
	 * @param behaelter
	 *            Behaelter fuer die Tabelle
	 * @param spaltenkopf
	 *            Bezeichnungen der Spaltenkoepfe
	 * @param zellen
	 *            Feld der Zelleninhalte
	 * @param neuesX
	 *            Dimensionen
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Tabelle(IContainer behaelter, String[] spaltenkopf,
			String[][] zellen, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		obj = new CTabelle(spaltenkopf, zellen);
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

	/**
	 * Farben fuer die Anzeige von Zellen</br>
	 * 
	 * Art: Siehe setzeAnzeigetypXXX
	 * 
	 * @param vordergrund1
	 *            Schriftfarbe1
	 * @param hintergrund1
	 *            Hintergrundfarbe1
	 * @param vordergrund2
	 *            Schriftfarbe2
	 * @param hintergrund2
	 *            Hintergrundfarbe2
	 */
	public void setzeFarben(String vordergrund1, String hintergrund1,
			String vordergrund2, String hintergrund2) {
		obj.setzeFarben(vordergrund1, hintergrund1, vordergrund2, hintergrund2);
	}

	/**
	 * Tabellengroesse setzen
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

	/**
	 * Position und Groesse setzen
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
	 * Anzeige der Zellen in gleicher Farbe
	 * 
	 * Die beiden ersten Farben von setzeFarben werden verwednet
	 */
	public void setzeAnzeigetypUni() {
		obj.setzeAnzeigtypUni();
	}

	/**
	 * Anzeige-Auswahl Zeile</br>
	 * 
	 * Benachbarte Zeilen haben unterschiedliche Hintergrund- und Schriftfarbe
	 */
	public void setzeAnzeigetypZeilen() {
		obj.setzeAnzeigetypZeilen();
	}

	/**
	 * Anzeige-Auswahl Zelle</br>
	 * 
	 * Benachbarte Zellen haben unterschiedliche Hintergrund- und Schriftfarbe
	 * 
	 */
	public void setzeAnzeigetypZellen() {
		obj.setzeAnzeigetypZellen();
	}

	public int leseAnzahlSpalten() {
		return obj.leseAnzahlSpalten();
	}

	public int leseAnzahlZeilen() {
		return obj.leseAnzahlZeilen();
	}

	/**
	 * leere Zeile hinzufuegen
	 */
	public void zeileHinzufuegen() {
		obj.zeileHinzufuegen();
	}

	public void zeileHinzufuegen(String[] zeile) {
		obj.zeileHinzufuegen(zeile);
	}

	public void zeileEinfuegen(int i, String[] zeile) {
		obj.zeileEinfuegen(i, zeile);
	}

	/**
	 * leere Zeile an Position i hinzufuegen
	 * 
	 * @param i
	 */
	public void zeileEinfuegen(int i) {
		obj.zeileEinfuegen(i);
	}

	public void zeileLoeschen(int i) {
		obj.zeileLoeschen(i);
	}

	/**
	 * Die Spalte 0 wird in Randfarben gesetzt. Spalte 0 ist nicht editierbar
	 * 
	 * @param fett
	 *            Gibt an, ob die Schriftart fett gesetzt wird
	 */
	public void spalteNullAlsRand(boolean fett) {
		obj.spalteNullAlsRand(fett);
	}

	/**
	 * Lesen des Spaltenkopf-Texts
	 * 
	 * @param spalte
	 * @return Text des Spaltenkopf
	 */
	public String leseSpaltenkopfText(int spalte) {
		return obj.leseSpaltenkopfText(spalte);
	}

	/**
	 * Anzeigetext der Spalte setzen
	 * 
	 * @param spaltenNr
	 * @param kopf
	 */
	public void setzeSpaltenkopfText(int spaltenNr, String kopf) {
		obj.setzeSpaltenkopfText(spaltenNr, kopf);
	}

	/**
	 * Spalten hinzufuegen
	 * 
	 * @param kopf
	 *            Kopfbezeichnung
	 * @param spalte
	 *            Spalteninhalt
	 */
	public void spalteHinzufuegen(String kopf, String[] spalte) {
		obj.spalteHinzufuegen(kopf, spalte);
	}

	/**
	 * leere Spalte hinzufuegen
	 */
	public void spalteHinzufuegen() {
		obj.spalteHinzufuegen();
	}

	/**
	 * Spalte einfuegen
	 * 
	 * @param i
	 * @param kopf
	 *            Kopfbezeichnung
	 * @param spalte
	 *            Spalteninhalt
	 */
	public void spalteEinfuegen(int i, String kopf, String[] spalte) {
		obj.spalteEinfuegen(i, kopf, spalte);
	}

	/**
	 * leere Spalte einfuegen
	 * 
	 * @param i
	 */
	public void spalteEinfuegen(int i) {
		obj.spalteEinfuegen(i);
	}

	public void spalteLoeschen(int i) {
		obj.spalteLoeschen(i);
	}

	/**
	 * Bei nicht editierbarer Tabelle:<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(xxx)<br/>
	 * selektionsartZeile(false)<br/>
	 * selektionsartSpalte(false) -> Nur ZELLENSELECT-Ereignis bei Zeilenwechsel<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(false)<br/>
	 * selektionsartZeile(false)<br/>
	 * selektionsartSpalte(true) -> <strong>Spaltenauswahl</strong> <br/>
	 * SPALTENSELECT-Ereignis bei Spaltenwechsel und ZELLENSELECT- Ereignis bei
	 * Zeilenwechsel<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(false)<br/>
	 * selektionsartZeile(true)<br/>
	 * selektionsartSpalte(false) -> Nur ZEILENSELECT-Ereignis bei Zeilenwechsel<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(false)<br/>
	 * selektionsartZeile(true)<br/>
	 * selektionsartSpalte(true) -> <strong>Bereichsauswahl</strong> <br/>
	 * ZEILENSELECT- und SPALTENSELECT- Ereignis<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(true)<br/>
	 * selektionsartZeile(true)<br/>
	 * selektionsartSpalte(true) -> <strong>Nur Zelle auswaehlbar</strong> <br/>
	 * ZEILENSELECT- und SPALTENSELECT- Ereignis<br/>
	 * 
	 * @param zelle
	 *            : true: Zellenbereich auswaehlbar, sonst nur Zelle auswaehlbar
	 */
	public void selektionsZelleBereich(boolean zelle) {
		obj.selektionsZelleBereich(zelle);
	}

	/**
	 * Bei nicht editierbarer Tabelle:<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(xxx)<br/>
	 * selektionsartZeile(false)<br/>
	 * selektionsartSpalte(false) -> Nur ZELLENSELECT-Ereignis bei Zeilenwechsel<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(false)<br/>
	 * selektionsartZeile(false)<br/>
	 * selektionsartSpalte(true) -> <strong>Spaltenauswahl</strong> <br/>
	 * SPALTENSELECT-Ereignis bei Spaltenwechsel und ZELLENSELECT- Ereignis bei
	 * Zeilenwechsel<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(false)<br/>
	 * selektionsartZeile(true)<br/>
	 * selektionsartSpalte(false) -> Nur ZEILENSELECT-Ereignis bei Zeilenwechsel<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(false)<br/>
	 * selektionsartZeile(true)<br/>
	 * selektionsartSpalte(true) -> <strong>Bereichsauswahl</strong> <br/>
	 * ZEILENSELECT- und SPALTENSELECT- Ereignis<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(true)<br/>
	 * selektionsartZeile(true)<br/>
	 * selektionsartSpalte(true) -> <strong>Nur Zelle auswaehlbar</strong> <br/>
	 * ZEILENSELECT- und SPALTENSELECT- Ereignis<br/>
	 * 
	 * @param erlaubt
	 *            : true: Zellenbereich auswaehlbar, sonst nur Zelle auswaehlbar
	 */
	public void selektionsartZeile(boolean erlaubt) {
		obj.selektionsartZeile(erlaubt);
	}

	/**
	 * Bei nicht editierbarer Tabelle:<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(xxx)<br/>
	 * selektionsartZeile(false)<br/>
	 * selektionsartSpalte(false) -> Nur ZELLENSELECT-Ereignis bei Zeilenwechsel<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(false)<br/>
	 * selektionsartZeile(false)<br/>
	 * selektionsartSpalte(true) -> <strong>Spaltenauswahl</strong> <br/>
	 * SPALTENSELECT-Ereignis bei Spaltenwechsel und ZELLENSELECT- Ereignis bei
	 * Zeilenwechsel<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(false)<br/>
	 * selektionsartZeile(true)<br/>
	 * selektionsartSpalte(false) -> Nur ZEILENSELECT-Ereignis bei Zeilenwechsel<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(false)<br/>
	 * selektionsartZeile(true)<br/>
	 * selektionsartSpalte(true) -> <strong>Bereichsauswahl</strong> <br/>
	 * ZEILENSELECT- und SPALTENSELECT- Ereignis<br/>
	 * 
	 * Selektionsart: <br />
	 * selektionsZelleBereich(true)<br/>
	 * selektionsartZeile(true)<br/>
	 * selektionsartSpalte(true) -> <strong>Nur Zelle auswaehlbar</strong> <br/>
	 * ZEILENSELECT- und SPALTENSELECT- Ereignis<br/>
	 * 
	 * @param erlaubt
	 *            : true: Zellenbereich auswaehlbar, sonst nur Zelle auswaehlbar
	 */
	public void selektionsartSpalte(boolean erlaubt) {
		obj.selektionsartSpalte(erlaubt);
	}

	/**
	 * Hebt die Zellenselektion auf
	 */
	public void selektionAufheben() {
		obj.selektionAufheben();
	}

	/**
	 * lesen der aktuell selektierten Zeilennummer
	 * 
	 * @return selektierte Zeilennummer
	 */
	public int getSelectZeile() {
		return obj.getSelectZeile();
	}

	/**
	 * lesen der aktuell selektierten Spaltennummer
	 * 
	 * @return selektierte Spaltennummer
	 */
	public int getSelectSpalte() {
		return obj.getSelectSpalte();
	}

	/**
	 * Bei moeglicher Mehrfachauswahl: lesen des Zeilenfelds, das die
	 * selektierten Zeilen enthaelt
	 * 
	 * @return Zeilenfeld
	 */
	public int[] getSelectZeilenFeld() {
		return obj.getSelectZeilenFeld();
	}

	/**
	 * Bei moeglicher Mehrfachauswahl: lesen des Spaltenfelds, das die
	 * selektierten Spalten enthaelt
	 * 
	 * @return Spaltenfeld
	 */
	public int[] getSelectSpaltenFeld() {
		return obj.getSelectSpaltenFeld();
	}

	/**
	 * fuer die Tabelle: Editierbarkeit setzen <br/>
	 * Er wird zuerst die Zelle gefragt, dann die Spalte, dann die Zeile und
	 * dann die Tabelle
	 * 
	 * @param editierbar
	 *            true: editierbar <br/>
	 *            false: nicht editierbar <br/>
	 *            null : nicht relevat <br/>
	 *            alle null: editierbar
	 */
	public void setzeEditierbarTabelle(Boolean editierbar) {
		obj.setzeEditierbarTabelle(editierbar);
	}

	/**
	 * fuer die Zeile: Editierbarkeit setzen<br/>
	 * Er wird zuerst die Zelle gefragt, dann die Zeile ,dann die Spalte und
	 * dann die Tabelle
	 * 
	 * @param zeile
	 * @param editierbar
	 *            true: editierbar <br/>
	 *            false: nicht editierbar <br/>
	 *            null : nicht relevat <br/>
	 *            alle null: editierbar
	 */
	public void setzeEditierbarZeile(int zeile, Boolean editierbar) {
		obj.setzeEditierbarZeile(zeile, editierbar);
	}

	/**
	 * fuer die Spalte: Editierbarkeit setzen<br/>
	 * Er wird zuerst die Zelle gefragt, dann die Zeile ,dann die Spalte und
	 * dann die Tabelle
	 * 
	 * @param spalte
	 * @param editierbar
	 *            true: editierbar <br/>
	 *            false: nicht editierbar <br/>
	 *            null : nicht relevat <br/>
	 *            alle null: editierbar
	 */
	public void setzeEditierbarSpalte(int spalte, Boolean editierbar) {
		obj.setzeEditierbarSpalte(spalte, editierbar);
	}

	/**
	 * fuer die Zelle: Editierbarkeit setzen<br/>
	 * Er wird zuerst die Zelle gefragt, dann die Zeile ,dann die Spalte und
	 * dann die Tabelle
	 * 
	 * @param zeile
	 * @param spalte
	 * @param editierbar
	 *            true: editierbar <br/>
	 *            false: nicht editierbar <br/>
	 *            null : nicht relevat <br/>
	 *            alle null: editierbar
	 */
	public void setzeEditierbarZelle(int zeile, int spalte, Boolean editierbar) {
		obj.setzeEditierbarZelle(zeile, spalte, editierbar);
	}

	/**
	 * fuer die Spalte: Chekbox-Eigenschaft setzen <br/>
	 * 
	 * @param spalte
	 * @param aktiv
	 */
	public void aktiviereChekboxSpalte(int spalte, boolean aktiv) {
		obj.aktiviereChekboxSpalte(spalte, aktiv);
	}

	/**
	 * fuer die Zelle: Chekbox-Eigenschaft setzen <br/>
	 * 
	 * @param zeile
	 * @param spalte
	 * @param aktiv
	 */
	public void aktiviereChekboxZelle(int zeile, int spalte, boolean aktiv) {
		obj.aktiviereChekboxZelle(zeile, spalte, aktiv);
	}

	/**
	 * fuer die Zelle: Chekbox-Wert setzen <br/>
	 * 
	 * @param zeile
	 * @param spalte
	 * @param select
	 */

	public void setzeChekboxStatus(int zeile, int spalte, boolean select) {
		obj.setzeChekboxStatus(zeile, spalte, select);
	}

	/**
	 * fuer die Zelle: Chekbox-Wert setzen <br/>
	 * 
	 * @param zeile
	 * @param spalte
	 */
	public boolean leseChekboxStatus(int zeile, int spalte) {
		return obj.leseChekboxStatus(zeile, spalte);
	}

	/**
	 * Setzen der Spaltenbreite. Breite kann mit der Maus veraendert werden.
	 * 
	 * 
	 * @param nr
	 * @param breite
	 */
	public void setzeSpaltenbreite(int nr, int breite) {
		obj.setzeSpaltenbreite(nr, breite);
	}

	/**
	 * Setzen der Spaltenbreite. Breite kann nicht veraendert werden
	 * 
	 * @param nr
	 * @param breite
	 */
	public void setzeSpaltenbreiteFix(int nr, int breite) {
		obj.setzeSpaltenbreiteFix(nr, breite);
	}

	/**
	 * Setzen mehrerer Spaltenbreiten. Breite kann mit der Maus veraendert
	 * werden.
	 * 
	 * 
	 * @param breiten
	 */
	public void setzeSpaltenbreiten(int[] breiten) {
		obj.setzeSpaltenbreiten(breiten);
	}

	/**
	 * Setzen mehrerer Spaltenbreiten. Breite kann nicht veraendert werden
	 * 
	 * @param breiten
	 *            Breitenfeld
	 */
	public void setzeSpaltenbreitenFix(int[] breiten) {
		obj.setzeSpaltenbreitenFix(breiten);
	}

	/**
	 * autoresize expandiert die Spalten der Tabelle auf die Tabellenbreite.
	 * Default: true ; false: die Spaltenbreiten bleiben auf dem Defaultwert
	 * 100;
	 * 
	 * @param an
	 */
	public void autoresize(boolean an) {
		obj.autoresize(an);
	}

	public void setzeTextblock(String[][] zellen, int startZeile,
			int startSpalte) {
		obj.setzeTextblock(zellen, startZeile, startSpalte);
	}

	/**
	 * Lesen des Tabelleninhalts als zweidimensionales String-Feld
	 * 
	 * Inhalt wird mit Checkbox-Informationen gelesen. Siehe leseTextExtended
	 * 
	 * @return Stringfeld
	 */
	public String[][] leseTextblock() {
		return obj.leseTextblock();
	}

	/**
	 * /** Lesen des eines Rechtecks als zweidimensionales String-Feld
	 * 
	 * Inhalt wird mit Checkbox-Informationen gelesen. Siehe leseTextExtended
	 * 
	 * @return Stringfeld
	 * 
	 * @param startZeile
	 * @param endeZeile
	 * @param startSpalte
	 * @param endeSpalte
	 * 
	 * @return Stringfeld
	 */
	public String[][] leseTextblock(int startZeile, int endeZeile,
			int startSpalte, int endeSpalte) {
		return obj
				.leseTextblock(startZeile, endeZeile, startSpalte, endeSpalte);
	}

	/**
	 * lese Zelleninhalt als String
	 * 
	 * @param zeile
	 * @param spalte
	 * @return String
	 */
	public String leseText(int zeile, int spalte) {
		return obj.getString(zeile, spalte);
	}

	public void setzeText(String text, int zeile, int spalte) {
		obj.setString(text, zeile, spalte);
	}

	/**
	 * Zelleninhalt mit CheckBox-Status
	 * 
	 * @param zeile
	 * @param spalte
	 * @return String
	 */
	public String leseTextExtended(int zeile, int spalte) {
		return obj.getTextExtended(zeile, spalte);
	}

	/**
	 * Bei Start mit Eckigen Klammern: Eckige Klammern umschliessen
	 * CheckBox-Wert<br/>
	 * interpretierte Werte:<br/>
	 * [true][false][wahr][falsch][ja][nein][richtig][ok][yes][no]
	 * 
	 * @param text
	 * @param zeile
	 * @param spalte
	 */
	public void setzeTextExtended(String text, int zeile, int spalte) {
		obj.setTextExtended(text, zeile, spalte);
	}

	/**
	 * Zeilen haben zusaetzlich zum Anzeigetext einen Identifier, der
	 * zusaetzlich zur Identifikation benutzt werden kann<br/>
	 * (Tabellen koennen nach Spalten sortiert werden: die Zeilennummer ist
	 * nicht eindeutig!)<br/>
	 * 
	 * @param wert
	 * @param zeile
	 */
	public void setzeIdentifierZeile(long wert, int zeile) {
		obj.setzeIdentifierZeile(wert, zeile);
	}

	/**
	 * Zeilen haben zusaetzlich zum Anzeigetext einen Identifier, der
	 * zusaetzlich zur Identifikation benutzt werden kann<br/>
	 * (Tabellen koennen nach Spalten sortiert werden; die Zeilennummer ist
	 * nicht eindeutig!)<br/>
	 * 
	 * @param zeile
	 * @return Identifier
	 */
	public long leseIdentifierZeile(int zeile) {
		return obj.leseIdentifierZeile(zeile);
	}

	/**
	 * Spalten haben zusaetzlich zum Anzeigetext einen Identifier, der
	 * zusaetzlich zur Identifikation benutzt werden kann<br/>
	 * 
	 * @param wert
	 * @param spalte
	 */
	public void setzeIdentifierSpalte(long wert, int spalte) {
		obj.setzeIdentifierSpalte(wert, spalte);
	}

	/**
	 * Spalten haben zusaetzlich zum Anzeigetext einen Identifier, der
	 * zusaetzlich zur Identifikation benutzt werden kann<br/>
	 * 
	 * @param spalte
	 * @return Identifier
	 */
	public long leseIdentifierSpalte(int spalte) {
		return obj.leseIdentifierSpalte(spalte);
	}

	/**
	 * Zellen haben zusaetzlich zum Anzeigetext einen Identifier, der
	 * zusaetzlich zur Identifikation benutzt werden kann<br/>
	 * (Tabellen koennen nach Spalten sortiert werden; die Zeilen- und
	 * Spaltennummer ist nicht eindeutig!)<br/>
	 * 
	 * @param wert
	 * @param zeile
	 * @param spalte
	 */
	public void setzeIdentifierZelle(long wert, int zeile, int spalte) {
		obj.setzeIdentifierZelle(wert, zeile, spalte);
	}

	/**
	 * Zellen haben zusaetzlich zum Anzeigetext einen Identifier, der
	 * zusaetzlich zur Identifikation benutzt werden kann<br/>
	 * (Tabellen koennen nach Spalten sortiert werden. Die Zeilennummer ist
	 * nicht eindeutig!)<br/>
	 * 
	 * @param zeile
	 * @param spalte
	 * @return Identifier
	 */
	public long leseIdentifierZelle(int zeile, int spalte) {
		return obj.leseIdentifierZelle(zeile, spalte);
	}

	/**
	 * Setzt Zeile fett, wenn true
	 * 
	 * @param wert
	 * @param zeile
	 */
	public void setzeHervorgehobenZeile(boolean wert, int zeile) {
		obj.setzeHervorgehobenZeile(wert, zeile);
	}

	/**
	 * lesen des Zeilenattributs
	 * 
	 * @param zeile
	 * @return Wert fuer Hervorgehoben
	 */
	public boolean leseHervorgehobenZeile(int zeile) {
		return obj.leseHervorgehobenZeile(zeile);
	}

	public void setzeInteger(int zahl, int zeile, int spalte) {
		obj.setString("" + zahl + " ", zeile, spalte);
	}

	public int leseInteger(int zeile, int spalte, int def) {
		int value = 0;
		String neu = obj.getString(zeile, spalte);
		neu = neu.trim();

		try {
			value = Integer.parseInt(neu);
		} catch (Exception e) {
			value = def;
		}
		return value;
	}

	public int leseIntegerGerundet(int zeile, int spalte, int def) {
		int value = 0;
		value = (int) Math.round(leseDouble(zeile, spalte, def));
		return value;
	}

	public void setzeDouble(double zahl, int zeile, int spalte) {
		obj.setString("" + zahl + " ", zeile, spalte);
	}

	public double leseDouble(int zeile, int spalte, double def) {
		double value = 0;
		String neu = obj.getString(zeile, spalte).replace(',', '.');

		try {
			value = Double.parseDouble(neu);
		} catch (Exception e) {
			value = def;
		}
		return value;
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

	/**
	 * Zeilennummer der letzten editierten Zelle
	 * 
	 * @return Zeile. Fehler: -1;
	 */
	public int getEditZeile() {
		return obj.getEditZeile();
	}

	/**
	 * Spaltennummer der letzten editierten Zelle
	 * 
	 * @return Spalte. Fehler: -1;
	 */
	public int getEditSpalte() {
		return obj.getEditSpalte();
	}

	public void mitSpaltenSorter(boolean sortiert) {
		obj.mitSpaltenSorter(sortiert);

	}

}

@SuppressWarnings("serial")
class CTabelle extends BasisComponente implements TableModelListener {
	static final int DELTAY = 6;
	JScrollPane scrollPane;
	JTable tabelle;
	// Create a list that allows adds and removes
	Tabellenmodell model = null;
	TableCellEditor ceSpezial = new ZellenEditor(this);
	TableCellRenderer renderer = new ZellenRenderer();

	int[] selectZeilen = null;
	int[] selectSpalten = null;

	int editZeile = -1;
	int editSpalte = -1;

	/**
	 * Spaltennummer der letzten editierten Zelle
	 * 
	 * @return Spalte. Fehler: -1;
	 */
	public int getEditSpalte() {
		return editSpalte;
	}

	/**
	 * Zeilennummer der letzten editierten Zelle
	 * 
	 * @return Zeile. Fehler: -1;
	 */
	public int getEditZeile() {
		return editZeile;
	}

	public int getSelectZeile() {
		if (selectZeilen.length > 0) {
			return selectZeilen[0];
		} else {
			return -1;
		}
	}

	public int getSelectSpalte() {
		if (selectSpalten.length > 0) {
			return selectSpalten[0];
		} else {
			return -1;
		}
	}

	public int[] getSelectZeilenFeld() {
		return selectZeilen;
	}

	public int[] getSelectSpaltenFeld() {
		return selectSpalten;
	}

	// JTableHeader holen
	JTableHeader header = null;

	public static String[] getStringABC(int len) {
		String[] erg = new String[len];
		int basis = 0;
		String se = "";
		int z = 0;
		for (int i = 0; i < len; i++) {
			erg[i] = se + (char) ((int) ('A') + z);
			z++;
			if (z > 25) {
				basis++;
				se = "" + (char) ((int) ('A') + basis);
				z = 0;
			}
		}
		return erg;
	}

	/**
	 * Konstruktor fuer Objekte der Klasse Tabelle
	 */
	public CTabelle(int zeilen, int spalten) {
		this(getStringABC(spalten), new String[zeilen][spalten]);
	}

	public CTabelle(String[] spaltenkopf, String[][] zellen) {
		if (spaltenkopf == null && zellen == null)
			return;
		this.setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		if (spaltenkopf == null) {
			int anzSpalten = zellen[0].length;
			spaltenkopf = getStringABC(anzSpalten);
		}
		model = new Tabellenmodell(spaltenkopf, zellen);
		tabelle = new JTable(model);
		tabelle.setDefaultEditor(Object.class, ceSpezial);
		tabelle.setDefaultRenderer(Object.class, renderer);
		// Beenden des ZellEditors bei Fokusverlusst
		tabelle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		// JTableHeader holen
		header = tabelle.getTableHeader();
		header.setReorderingAllowed(false);
		header.setDefaultRenderer(new KopfZellenRenderer(this));

		// tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		selektionsZelleBereich(true);
		setzeBasisfarbe("weiss");
		setzeSchriftgroesse(originalFontGroesse);
		model.addTableModelListener(this);

		tabelle.getSelectionModel().addListSelectionListener(
				new AuswahlAuswertungZellen(this));

		tabelle.getColumnModel().getSelectionModel()
				.addListSelectionListener(new AuswahlAuswertungKopf(this));

		scrollPane.setViewportView(tabelle);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		meldeAnLink(Tabelle.TABELLENSTRUKTURAENDERUNG);
	}

	public void meldeAnLink(int lID) {
		final int mID = id + lID;
		if (linkObj != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					linkObj.tuWas(mID);
				}
			}).start();
		}
	}

	public void meldeEditEnde(int zeile, int spalte) {
		editZeile = zeile;
		editSpalte = spalte;
		meldeAnLink(Tabelle.EDITENDE);

	}

	public void setzeAnzeigetypZellen() {
		model.setzeAnzeigetypZellen();
	}

	public void setzeAnzeigetypZeilen() {
		model.setzeAnzeigetypZeilen();
	}

	public void setzeAnzeigtypUni() {
		model.setzeAnzeigtypUni();
	}

	public int leseAnzahlZeilen() {
		return model.getRowCount();
	}

	public int leseAnzahlSpalten() {
		return model.getColumnCount();
	}

	public void zeileHinzufuegen(String[] zeile) {
		final String[] zeileHinzufuegen = zeile;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.zeileHinzufuegen(zeileHinzufuegen);
					validate();
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void zeileHinzufuegen() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.zeileHinzufuegen();
					validate();
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void zeileEinfuegen(int zeilenNr, String[] zeile) {
		final int zeilenNrEinfuegen = zeilenNr;
		final String[] zeileEinfuegen = zeile;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.zeileEinfuegen(zeilenNrEinfuegen, zeileEinfuegen);
					validate();
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void zeileEinfuegen(int zeilenNr) {
		final int zeilenNrEinfuegenSolo = zeilenNr;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.zeileEinfuegen(zeilenNrEinfuegenSolo);
					validate();
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void zeileLoeschen(int zeilenNr) {
		final int zeilenNrLoeschen = zeilenNr;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.zeileLoeschen(zeilenNrLoeschen);
					validate();
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void spalteNullAlsRand(boolean fett) {
		model.spalteNullspalteAlsRand(fett);
	}

	public String leseSpaltenkopfText(int spalte) {
		return (String) model.leseSpaltenkopfText(spalte);
	}

	public void setzeSpaltenkopfText(int spaltenNr, String kopf) {
		final int spaltenNrTextSetzen = spaltenNr;
		final String kopfTextSetzen = kopf;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.setzeSpaltenkopfText(spaltenNrTextSetzen,
							kopfTextSetzen);
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void spalteHinzufuegen(String kopf, String[] spalte) {
		final String kopfHinzufuegen = kopf;
		final String[] spalteHinzufuegen = spalte;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.spalteHinzufuegen(kopfHinzufuegen, spalteHinzufuegen);
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void spalteHinzufuegen() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.spalteHinzufuegen();
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void spalteEinfuegen(int spaltenNr, String kopf, String[] spalte) {
		final int spaltenNrEinfuegen = spaltenNr;
		final String kopfEinfuegen = kopf;
		final String[] spalteEinfuegen = spalte;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.spalteEinfuegen(spaltenNrEinfuegen, kopfEinfuegen,
							spalteEinfuegen);
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void spalteEinfuegen(int spaltenNr) {
		final int spaltenNrEinfuegenSolo = spaltenNr;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.spalteEinfuegen(spaltenNrEinfuegenSolo);
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void spalteLoeschen(int spaltenNr) {
		final int spaltenNrLoeschen = spaltenNr;
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					model.spalteLoeschen(spaltenNrLoeschen);
				}
			});
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
		}
	}

	public void mitSpaltenSorter(boolean sortiert) {
		tabelle.setAutoCreateRowSorter(sortiert);
	}

	public void setzeEditierbarTabelle(Boolean editierbar) {
		model.setzeEditierbarTabelle(editierbar);
	}

	public void setzeEditierbarZeile(int zeile, Boolean editierbar) {
		model.setzeEditierbarZeile(zeile, editierbar);
	}

	public void setzeEditierbarSpalte(int spalte, Boolean editierbar) {
		model.setzeEditierbarSpalte(spalte, editierbar);
	}

	public void setzeEditierbarZelle(int zeile, int spalte, Boolean editierbar) {
		model.setzeEditierbarZelle(zeile, spalte, editierbar);
	}

	public void aktiviereChekboxSpalte(int spalte, boolean aktiv) {
		model.aktiviereChekboxSpalte(spalte, aktiv);
	}

	public void aktiviereChekboxZelle(int zeile, int spalte, boolean aktiv) {
		model.aktiviereChekboxZelle(zeile, spalte, aktiv);
	}

	public void setzeChekboxStatus(int zeile, int spalte, boolean select) {
		model.setzeChekboxStatus(zeile, spalte, select);
	}

	public boolean leseChekboxStatus(int zeile, int spalte) {
		return model.leseChekboxStatus(zeile, spalte);
	}

	public String getString(int zeile, int spalte) {
		return (String) model.leseText(zeile, spalte);
	}

	public String getTextExtended(int zeile, int spalte) {
		return (String) model.leseTextExtended(zeile, spalte);
	}

	public void setString(String text, int zeile, int spalte) {
		model.setText(text, zeile, spalte);
	}

	public void setTextExtended(String text, int zeile, int spalte) {
		model.setTextExtended(text, zeile, spalte);
	}

	public void setzeTextblock(String[][] zellen, int startZeile,
			int startSpalte) {
		model.setTextExtended(zellen, startZeile, startSpalte);
	}

	public String[][] leseTextblock() {
		return model.leseTextblock();
	}

	public String[][] leseTextblock(int startZeile, int endeZeile,
			int startSpalte, int endeSpalte) {
		return model.leseTextblock(startZeile, endeZeile, startSpalte,
				endeSpalte);
	}

	/**
	 * autoresize expandiert die Spalten der Tabelle auf die Tabellenbreite.
	 * Default: true ; false: die Spaltenbreiten bleiben auf dem Defaultwert
	 * 100;
	 * 
	 * @param an
	 */
	public void autoresize(boolean an) {
		if (an) {
			tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		} else {
			tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
	}

	public void setzeSpaltenbreite(int nr, int breite) {
		tabelle.getColumnModel().getColumn(nr).setPreferredWidth(breite);
	}

	public void setzeSpaltenbreiteFix(int nr, int breite) {
		tabelle.getColumnModel().getColumn(nr).setPreferredWidth(breite);
		tabelle.getColumnModel().getColumn(nr).setMaxWidth(breite);
		tabelle.getColumnModel().getColumn(nr).setMinWidth(breite);
	}

	public void setzeSpaltenbreiten(int[] breiten) {
		if (breiten != null) {
			for (int i = 0; i < breiten.length; i++) {
				setzeSpaltenbreite(i, breiten[i]);
			}
		}
	}

	public void setzeSpaltenbreitenFix(int[] breiten) {
		if (breiten != null) {
			for (int i = 0; i < breiten.length; i++) {
				setzeSpaltenbreiteFix(i, breiten[i]);
			}
		}
	}

	public void setzeWertBei(String wert, int zeile, int spalte) {
		tabelle.setValueAt(wert, zeile, spalte);
	}

	public void setzeHervorgehobenZeile(boolean wert, int zeile) {
		model.setzeHervorgehobeneZeile(wert, zeile);
	}

	public boolean leseHervorgehobenZeile(int zeile) {
		return model.leseHervorgehobeneZeile(zeile);
	}

	public void setzeIdentifierZeile(long wert, int zeile) {
		model.setzeIdentifierZeile(wert, zeile);
	}

	public long leseIdentifierZeile(int zeile) {
		return model.leseIdentifierZeile(zeile);
	}

	public void setzeIdentifierSpalte(long wert, int spalte) {
		model.setzeIdentifierSpalte(wert, spalte);
	}

	public long leseIdentifierSpalte(int spalte) {
		return model.leseIdentifierSpalte(spalte);
	}

	public void setzeIdentifierZelle(long wert, int zeile, int spalte) {
		model.setzeIdentifierZelle(wert, zeile, spalte);
	}

	public long leseIdentifierZelle(int zeile, int spalte) {
		return model.leseIdentifierZelle(zeile, spalte);
	}

	public String leseZelleBei(int zeile, int spalte) {
		return (String) tabelle.getValueAt(zeile, spalte);
	}

	public void selektionsZelleBereich(boolean zelle) {
		if (zelle) {
			tabelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		} else {
			tabelle.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		}
	}

	public void selektionsartZeile(boolean erlaubt) {
		tabelle.setRowSelectionAllowed(erlaubt);
	}

	public void selektionsartSpalte(boolean erlaubt) {
		tabelle.setColumnSelectionAllowed(erlaubt);
	}

	public void selektionAufheben() {
		tabelle.clearSelection();
	}

	@Override
	public void setzeFontName(String name) {
		super.setzeFontName(name);
		setzeSchriftgroesse(fontGroesse);
	}

	
	@Override
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		tabelle.setFont(f);
		ZellenEditor.font = f;
		tabelle.setRowHeight((int) (x + DELTAY));
		repaint();
	}

	@Override
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		tabelle.setFont(f);
		repaint();
	}

	protected Color schriftFarbe = StaticTools.getColor("schwarz");

	public void setzeSchriftfarbe(String farbname) {
		schriftFarbe = StaticTools.getColor(farbname);
		tabelle.setForeground(schriftFarbe);
		model.fordergrundfarbe_1 = schriftFarbe;
		repaint();
	}

	public void setzeHintergrundfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		tabelle.setBackground(farbe);
		model.hintergrundfarbe_1 = farbe;
		repaint();
	}

	public void setzeFarben(String vordergrund1, String hintergrund1,
			String vordergrund2, String hintergrund2) {

		model.fordergrundfarbe_1 = StaticTools.getColor(vordergrund1);
		model.hintergrundfarbe_1 = StaticTools.getColor(hintergrund1);
		model.fordergrundfarbe_2 = StaticTools.getColor(vordergrund2);
		model.hintergrundfarbe_2 = StaticTools.getColor(hintergrund2);

		repaint();
	}

	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
	}

}

class Zelle {
	String text;
	long identifier;

	private boolean chekboxEnabled = false;

	public boolean getCheckBoxEnabled() {
		return chekboxEnabled;
	}

	boolean chekboxStatus = false;

	// Klasse Boolean !
	// Wenn null, unberuecksichtigt
	Boolean editierbar = null;

	public Zelle(String text) {
		setTextExtended(text);
		identifier = Long.MIN_VALUE;
	}

	public Zelle(String text, long identifier) {
		setTextExtended(text);
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return text;
	}

	public String getText() {
		return text;
	}

	public String getTextExtended() {
		if (chekboxEnabled) {
			return (chekboxStatus ? Tabelle.CHEKBOXTRUE : Tabelle.CHEKBOXFALSE)
					+ text;
		} else {
			return text;
		}
	}

	public void setText(String text) {
		this.text = text.trim();
	}

	public void setTextExtended(String text) {
		text = text.trim() + " ";
		text = text.toLowerCase();
		if (text.equals("")) {
			this.text = text;
			chekboxEnabled = false;
		} else if (text.startsWith("[true]")) {
			aktiviereCheckBoxUndsetzeWert(true);
			this.text = text.substring(6);
		} else if (text.startsWith("[false]")) {
			aktiviereCheckBoxUndsetzeWert(false);
			this.text = text.substring(7);
		} else if (text.startsWith("[wahr]")) {
			aktiviereCheckBoxUndsetzeWert(true);
			this.text = text.substring(6);
		} else if (text.startsWith("[falsch]")) {
			aktiviereCheckBoxUndsetzeWert(false);
			this.text = text.substring(8);
		} else if (text.startsWith("[ja]")) {
			aktiviereCheckBoxUndsetzeWert(true);
			this.text = text.substring(4);
		} else if (text.startsWith("[nein]")) {
			aktiviereCheckBoxUndsetzeWert(false);
			this.text = text.substring(6);
		} else if (text.startsWith("[richtig]")) {
			aktiviereCheckBoxUndsetzeWert(true);
			this.text = text.substring(9);
		} else if (text.equalsIgnoreCase("[ok]")) {
			aktiviereCheckBoxUndsetzeWert(true);
			this.text = text.substring(4);
		} else if (text.equalsIgnoreCase("[yes]")) {
			aktiviereCheckBoxUndsetzeWert(true);
			this.text = text.substring(4);
		} else if (text.equalsIgnoreCase("[no]")) {
			aktiviereCheckBoxUndsetzeWert(false);
			this.text = text.substring(4);
		} else {
			this.text = text;
			chekboxEnabled = false;
		}
		this.text = this.text.trim();
	}

	public Boolean leseCheckBoxStatus() {
		return chekboxStatus;
	}

	public void aktiviereCheckBoxUndsetzeWert(boolean status) {
		chekboxEnabled = true;
		this.chekboxStatus = status;
	}

	public long getIdentifierZelle() {
		return identifier;
	}

	public void setzeIdentifierZelle(long identifier) {
		this.identifier = identifier;
	}

	public Boolean isCellEditable() {
		return editierbar;
	}

	public void setzeEditierbarZelle(Boolean editierbar) {
		this.editierbar = editierbar;
	}

	public void setzeChekboxStatus(boolean status) {
		this.chekboxStatus = status;
	}

	public boolean leseChekboxStatus() {
		return chekboxStatus;
	}

	/**
	 * Wird nur gesetzt, wenn noch nicht aktiv
	 * 
	 * wird zurckgesetzt, wenn aktiv==false
	 * 
	 * @param aktiv
	 */
	public void aktiviereChekboxZelle(boolean aktiv) {
		if (!chekboxEnabled) {
			this.chekboxEnabled = aktiv;
			this.chekboxStatus = false;
		} else if (!aktiv) {
			this.chekboxEnabled = aktiv;
		}
	}

	public void setBoolean(boolean wert) {
		this.chekboxStatus = wert;
	}

}

class Spalte {
	String text;
	Color fordergrundfarbe = null;
	Color hintergrundfarbe = null;

	long identifier;
	Boolean editierbar = null;
	private boolean chekboxEnabled = false;

	public boolean getCheckBoxEnabled() {
		return chekboxEnabled;
	}

	boolean fett = false;

	public Spalte(String text) {
		this.text = text;
		identifier = Long.MIN_VALUE;
	}

	@Override
	public String toString() {
		return text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getIdentifierSpalte() {
		return identifier;
	}

	public void setzeIdentifierSpalte(long identifier) {
		this.identifier = identifier;
	}

	public Boolean isEditable() {
		return editierbar;
	}

	public void setzeEditierbarSpalte(Boolean editierbar) {
		this.editierbar = editierbar;
	}

	public void aktiviereChekboxSpalte(boolean aktiv) {
		this.chekboxEnabled = aktiv;
	}

	public Color getbgFarbe() {
		return hintergrundfarbe;
	}

	public Color getfgFarbe() {
		return fordergrundfarbe;
	}

	public void resetColor() {
		fordergrundfarbe = null;
		hintergrundfarbe = null;
	}

	public boolean getFett() {
		return fett;
	}

	public void setFett(boolean fett) {
		this.fett = fett;
	}

	public void setBorderColor() {
		fordergrundfarbe = new Color((SystemColor.menuText).getRGB());
		hintergrundfarbe = new Color((SystemColor.menu).getRGB());
	}

}

class Zeile {

	Tabellenmodell tabellenmodell = null;
	String text;
	long identifier;
	boolean hervorgehoben = false;

	Boolean editierbar = null;
	Vector<Zelle> zeilenVektor;

	public Zeile(Tabellenmodell tabellenmodell, String[] zeile) {
		this.tabellenmodell = tabellenmodell;
		zeilenVektor = new Vector<Zelle>();
		text = "";
		identifier = Long.MIN_VALUE;
		zeilenaufbau(zeile);
	}

	public void zeilenaufbau(String[] zeile) {
		for (int j = 0; j < tabellenmodell.anzahlSpalten; j++) {
			if (zeile == null || j >= zeile.length) {
				add(new Zelle(""));
			} else if (zeile[j] == null) {
				add(new Zelle(""));
			} else {
				add(new Zelle(new String(zeile[j])));
			}
		}

		for (int j = 0; j < tabellenmodell.anzahlSpalten; j++) {
			if (tabellenmodell.kopfVektor.get(j).getCheckBoxEnabled()) {
				zeilenVektor.get(j).aktiviereChekboxZelle(true);
			}
		}
	}

	public void setzeIdentifierZeile(long wert) {
		identifier = wert;
	}

	public long leseIdentifierZeile() {
		return identifier;
	}

	public void setzeIdentifierZelle(long wert, int spalte) {
		if (spalte >= 0 && spalte < zeilenVektor.capacity())
			zeilenVektor.get(spalte).setzeIdentifierZelle(wert);
	}

	public long leseIdentifierZelle(int spalte) {
		if (spalte >= 0 && spalte < zeilenVektor.capacity()) {
			return zeilenVektor.get(spalte).getIdentifierZelle();
		} else {
			return Long.MIN_VALUE;
		}
	}

	public void add(Zelle zelle) {
		zeilenVektor.add(zelle);
	}

	public void insertElementAt(Zelle zelle, int spaltenNr) {
		zeilenVektor.insertElementAt(zelle, spaltenNr);
	}

	public void remove(int spaltenNr) {
		zeilenVektor.remove(spaltenNr);
	}

	public Object get(int spalte) {
		return zeilenVektor.get(spalte);
	}

	public Boolean isCellEditable(int spalte) {
		return zeilenVektor.get(spalte).isCellEditable();
	}

	public void setText(String wert, int spalte) {
		zeilenVektor.get(spalte).setText(wert);
	}

	public void setTextExtended(String wert, int spalte) {
		zeilenVektor.get(spalte).setTextExtended(wert);
	}

	public void setzeEditierbarZeile(Boolean editierbar) {
		this.editierbar = editierbar;
	}

	public void setzeEditierbarZelle(Boolean editierbar, int spalte) {
		if (spalte >= 0 && spalte < zeilenVektor.capacity())
			zeilenVektor.get(spalte).setzeEditierbarZelle(editierbar);
	}

	public void aktiviereChekboxZelle(boolean aktiv, int spalte) {
		if (spalte >= 0 && spalte < zeilenVektor.capacity())
			zeilenVektor.get(spalte).aktiviereChekboxZelle(aktiv);
	}

	public void setzeChekboxStatus(boolean status, int spalte) {
		if (spalte >= 0 && spalte < zeilenVektor.capacity())
			zeilenVektor.get(spalte).setzeChekboxStatus(status);
	}

	public boolean leseChekboxStatus(int spalte) {
		if (spalte >= 0 && spalte < zeilenVektor.capacity()) {
			return zeilenVektor.get(spalte).leseChekboxStatus();
		}
		return false;
	}

	public Boolean isEditable() {
		return editierbar;
	}

	public boolean isHervorgehoben() {
		return hervorgehoben;
	}

	public void setzeHervorgehoben(boolean hervorgehoben) {
		this.hervorgehoben = hervorgehoben;
	}

	public void setBoolean(boolean wert, int spalte) {
		zeilenVektor.get(spalte).setBoolean(wert);
	}

	public String leseText(int spalte) {
		return zeilenVektor.get(spalte).getText();
	}

	public String leseTextExtended(int spalte) {
		return zeilenVektor.get(spalte).getTextExtended();
	}

}

@SuppressWarnings("serial")
class Tabellenmodell extends AbstractTableModel {

	enum renderTyp {
		uni, zeilen, zellen
	}

	public renderTyp anzeigeRendern = renderTyp.uni;

	Color hintergrundfarbe_1 = new Color(255, 255, 255);
	Color fordergrundfarbe_1 = new Color(0, 0, 0);

	Color hintergrundfarbe_2 = new Color(245, 245, 245);
	Color fordergrundfarbe_2 = new Color(0, 0, 0);

	int anzahlSpalten = 0;
	// true, wenn die Spaltenzahl geaendert wird.
	boolean spaltenaktion = false;
	int anzahlZeilen = 0;

	Vector<Spalte> kopfVektor = null;
	Vector<Zeile> zeilenVektor = null;

	Boolean editierbar = null;

	public void setzeIdentifierZeile(long wert, int zeile) {
		if (zeile >= 0 && zeile < anzahlZeilen)
			zeilenVektor.get(zeile).setzeIdentifierZeile(wert);
	}

	public long leseIdentifierZeile(int zeile) {
		if (zeile >= 0 && zeile < anzahlZeilen) {
			return zeilenVektor.get(zeile).leseIdentifierZeile();
		} else {
			return Long.MIN_VALUE;
		}
	}

	public void setzeHervorgehobeneZeile(boolean wert, int zeile) {
		if (zeile >= 0 && zeile < anzahlZeilen)
			zeilenVektor.get(zeile).setzeHervorgehoben(wert);
	}

	public boolean leseHervorgehobeneZeile(int zeile) {
		if (zeile >= 0 && zeile < anzahlZeilen) {
			return zeilenVektor.get(zeile).isHervorgehoben();
		} else {
			return false;
		}
	}

	public void setzeIdentifierSpalte(long wert, int spalte) {
		if (spalte >= 0 && spalte < anzahlSpalten) {
			kopfVektor.get(spalte).setzeIdentifierSpalte(wert);
		}
	}

	public long leseIdentifierSpalte(int spalte) {
		if (spalte >= 0 && spalte < anzahlSpalten) {
			return kopfVektor.get(spalte).getIdentifierSpalte();
		} else {
			return Long.MIN_VALUE;
		}
	}

	public void setzeIdentifierZelle(long wert, int zeile, int spalte) {
		if (zeile >= 0 && zeile < anzahlZeilen && spalte >= 0
				&& spalte < anzahlSpalten)
			zeilenVektor.get(zeile).setzeIdentifierZelle(wert, spalte);
	}

	public long leseIdentifierZelle(int zeile, int spalte) {
		if (zeile >= 0 && zeile < anzahlZeilen && spalte >= 0
				&& spalte < anzahlSpalten) {
			return zeilenVektor.get(zeile).leseIdentifierZelle(spalte);
		} else {
			return Long.MIN_VALUE;
		}
	}

	public void spalteNullspalteAlsRand(boolean fett) {
		setzeEditierbarSpalte(0, false);
		kopfVektor.get(0).setBorderColor();
		kopfVektor.get(0).setFett(fett);
	}

	public void setzeAnzeigetypZellen() {
		anzeigeRendern = renderTyp.zellen;
	}

	public void setzeAnzeigetypZeilen() {
		anzeigeRendern = renderTyp.zeilen;
	}

	public void setzeAnzeigtypUni() {
		anzeigeRendern = renderTyp.uni;
	}

	public void setzeEditierbarTabelle(Boolean editierbar) {
		this.editierbar = editierbar;
	}

	public void setzeEditierbarZeile(int zeile, Boolean editierbar) {
		if (zeile >= 0 && zeile < anzahlZeilen) {
			zeilenVektor.get(zeile).setzeEditierbarZeile(editierbar);
		}
	}

	public void setzeEditierbarSpalte(int spalte, Boolean editierbar) {
		if (spalte >= 0 && spalte < anzahlSpalten) {
			kopfVektor.get(spalte).setzeEditierbarSpalte(editierbar);
		}
	}

	public void setzeEditierbarZelle(int zeile, int spalte, Boolean editierbar) {
		if (spalte >= 0 && spalte < anzahlSpalten && zeile >= 0
				&& zeile < anzahlZeilen) {
			zeilenVektor.get(zeile).setzeEditierbarZelle(editierbar, spalte);
		}
	}

	public void aktiviereChekboxSpalte(int spalte, boolean aktiv) {
		if (spalte >= 0 && spalte < anzahlSpalten) {
			kopfVektor.get(spalte).aktiviereChekboxSpalte(aktiv);
			for (int i = 0; i < anzahlZeilen; i++) {
				aktiviereChekboxZelle(i, spalte, aktiv);
			}
		}
	}

	public void aktiviereChekboxZelle(int zeile, int spalte, boolean aktiv) {
		if (spalte >= 0 && spalte < anzahlSpalten && zeile >= 0
				&& zeile < anzahlZeilen) {
			zeilenVektor.get(zeile).aktiviereChekboxZelle(aktiv, spalte);
		}
	}

	public void setzeChekboxStatus(int zeile, int spalte, boolean select) {
		if (spalte >= 0 && spalte < anzahlSpalten && zeile >= 0
				&& zeile < anzahlZeilen) {
			zeilenVektor.get(zeile).setzeChekboxStatus(select, spalte);
		}
	}

	public boolean leseChekboxStatus(int zeile, int spalte) {
		if (spalte >= 0 && spalte < anzahlSpalten && zeile >= 0
				&& zeile < anzahlZeilen) {
			return zeilenVektor.get(zeile).leseChekboxStatus(spalte);
		}
		return false;
	}

	/**
	 * Konstruktor fuer Tabellenmodell
	 * 
	 * @param zellen
	 * @param spaltenkopf
	 */
	public Tabellenmodell(String[] spaltenkopf, String[][] zellen) {
		// Kopie des Spaltenkopfstrings
		kopfVektor = new Vector<Spalte>();
		if (spaltenkopf != null) {
			for (int i = 0; i < spaltenkopf.length; i++) {
				if (spaltenkopf[i] != null)
					kopfVektor.add(new Spalte(spaltenkopf[i]));
				else
					kopfVektor.add(new Spalte(""));
			}
		}
		anzahlSpalten = kopfVektor.size();

		// Kopie des Zellenstrings
		zeilenVektor = new Vector<Zeile>();
		if (zellen != null) {
			for (int i = 0; i < zellen.length; i++) {
				Zeile tabZeile = new Zeile(this, zellen[i]);
				zeilenVektor.add(tabZeile);
				anzahlZeilen++;
			}
		}
	}

	public Color getSpaltenBGfarbe(int column) {
		if (column >= anzahlSpalten)
			return null;
		return kopfVektor.get(column).getbgFarbe();
	}

	public boolean getSpalteFett(int column) {
		if (column >= anzahlSpalten)
			return false;
		return kopfVektor.get(column).getFett();
	}

	public Color getSpaltenFGfarbe(int column) {
		if (column >= anzahlSpalten)
			return null;
		return kopfVektor.get(column).getfgFarbe();
	}

	public void zeileHinzufuegen() {
		zeileHinzufuegen(new String[anzahlZeilen]);
	}

	public void zeileHinzufuegen(String[] zeile) {
		Zeile tabZeile = new Zeile(this, zeile);
		zeilenVektor.add(tabZeile);
		anzahlZeilen++;
	}

	public void zeileEinfuegen(int zeilenNr) {
		zeileEinfuegen(zeilenNr, null);
	}

	public void zeileEinfuegen(int zeilenNr, String[] zeile) {
		Zeile tabZeile = new Zeile(this, zeile);
		zeilenVektor.insertElementAt(tabZeile, zeilenNr);
		anzahlZeilen++;

		fireTableStructureChanged();

	}

	public void zeileLoeschen(int zeilenNr) {

		if (zeilenNr >= 0 && zeilenNr < anzahlZeilen) {
			zeilenVektor.remove(zeilenNr);
			anzahlZeilen--;

			fireTableStructureChanged();
		}

	}

	public void setzeSpaltenkopfText(int spaltenNr, String kopf) {
		if (spaltenNr >= 0 && spaltenNr < anzahlSpalten) {
			if (kopf != null) {
				kopfVektor.get(spaltenNr).setText(kopf);
			} else {
				kopfVektor.get(spaltenNr).setText("");
			}
			fireTableStructureChanged();
		}
	}

	public String leseSpaltenkopfText(int spalte) {
		if (spalte >= 0 && spalte < anzahlSpalten) {
			return kopfVektor.get(spalte).getText();
		} else {
			return "";
		}
	}

	public void spalteHinzufuegen() {
		spalteHinzufuegen(null, null);
	}

	public synchronized void spalteHinzufuegen(String kopf, String[] spalte) {
		spaltenaktion = true;
		if (kopf != null) {
			kopfVektor.add(new Spalte(kopf));
		} else {
			kopfVektor.add(new Spalte(""));
		}

		Zeile tabZeile = null;
		for (int i = 0; i < anzahlZeilen; i++) {
			tabZeile = zeilenVektor.get(i);
			if (i >= spalte.length) {
				tabZeile.add(new Zelle(""));
			} else if (spalte[i] == null) {
				tabZeile.add(new Zelle(""));
			} else {
				tabZeile.add(new Zelle(new String(spalte[i])));
			}
		}
		anzahlSpalten++;
		spaltenaktion = false;
		fireTableStructureChanged();
	}

	public void spalteEinfuegen(int spaltenNr) {
		spalteEinfuegen(spaltenNr, null, null);
	}

	public synchronized void spalteEinfuegen(int spaltenNr, String kopf,
			String[] spalte) {
		if (spaltenNr >= 0 && spaltenNr <= anzahlSpalten) {
			spaltenaktion = true;
			if (kopf != null) {
				kopfVektor.insertElementAt(new Spalte(kopf), spaltenNr);
			} else {
				kopfVektor.insertElementAt(new Spalte(""), spaltenNr);
			}

			Zeile tabZeile = null;
			for (int i = 0; i < anzahlZeilen; i++) {
				tabZeile = zeilenVektor.get(i);
				if (spalte == null || i >= spalte.length) {
					tabZeile.insertElementAt(new Zelle(""), spaltenNr);
				} else if (spalte[i] == null) {
					tabZeile.insertElementAt(new Zelle(""), spaltenNr);
				} else {
					tabZeile.insertElementAt(new Zelle(new String(spalte[i])),
							spaltenNr);
				}
			}
			anzahlSpalten++;
			spaltenaktion = false;
			fireTableStructureChanged();

		}
	}

	public synchronized void spalteLoeschen(int spaltenNr) {

		if (spaltenNr >= 0 && spaltenNr < anzahlSpalten) {
			spaltenaktion = true;
			Zeile tabZeile = null;
			kopfVektor.remove(spaltenNr);

			for (int i = 0; i < anzahlZeilen; i++) {
				tabZeile = zeilenVektor.get(i);
				tabZeile.remove(spaltenNr);
			}

			anzahlSpalten--;
			spaltenaktion = false;
			fireTableStructureChanged();
		}
	}

	@Override
	public int getColumnCount() {
		if (spaltenaktion)
			return 0;
		return anzahlSpalten;
	}

	@Override
	public int getRowCount() {
		return anzahlZeilen;
	}

	@Override
	public String getColumnName(int spalte) {
		return kopfVektor.get(spalte).getText();
	}

	public String leseText(int zeile, int spalte) {
		return (zeilenVektor.get(zeile)).leseText(spalte);
	}

	public String leseTextExtended(int zeile, int spalte) {
		return (zeilenVektor.get(zeile)).leseTextExtended(spalte);
	}

	@Override
	public Object getValueAt(int zeile, int spalte) {
		return (zeilenVektor.get(zeile)).get(spalte);
	}

	@Override
	public boolean isCellEditable(int zeile, int spalte) {
		if (zeilenVektor.get(zeile).isCellEditable(spalte) != null) {
			// Zelle wird gefragt
			return zeilenVektor.get(zeile).isCellEditable(spalte);
		} else if (zeilenVektor.get(zeile).isEditable() != null) {
			// Zeile wird gefragt
			return zeilenVektor.get(zeile).isEditable();
		} else if (kopfVektor.get(spalte).isEditable() != null) {
			// Spalte wird gefragt
			return kopfVektor.get(spalte).isEditable();
		} else if (editierbar != null) {
			// Tabelle wird gefragt
			return editierbar;
		} else {
			return true;
		}
	}

	public void setText(Object wert, int zeile, int spalte) {
		if (zeile >= 0 && zeile < anzahlZeilen && spalte >= 0
				&& spalte < anzahlSpalten) {
			(zeilenVektor.get(zeile)).setText((String) wert, spalte);
		}
	}

	public void setTextExtended(Object wert, int zeile, int spalte) {
		if (zeile >= 0 && zeile < anzahlZeilen && spalte >= 0
				&& spalte < anzahlSpalten) {
			(zeilenVektor.get(zeile)).setTextExtended((String) wert, spalte);
		}
	}

	// Fuer Tabelleneditor
	@Override
	public void setValueAt(Object wert, int zeile, int spalte) {
		if (zeile >= 0 && zeile < anzahlZeilen && spalte >= 0
				&& spalte < anzahlSpalten) {
			if (wert.getClass().isInstance(true)) {
				(zeilenVektor.get(zeile)).setBoolean((Boolean) wert, spalte);
			} else if (wert.getClass().isInstance("")) {
				(zeilenVektor.get(zeile)).setText((String) wert, spalte);
			}
		}
	}

	public void setTextExtended(String[][] zellen, int startZeile,
			int startSpalte) {
		for (int z = 0; z < zellen.length; z++) {
			for (int s = 0; s < zellen[z].length; s++) {
				setTextExtended(zellen[z][s], z + startZeile, s + startSpalte);
			}
		}
	}

	public String[][] leseTextblock() {
		String[][] erg = new String[anzahlZeilen][anzahlSpalten];
		for (int z = 0; z < anzahlZeilen; z++) {
			for (int s = 0; s < anzahlSpalten; s++) {
				erg[z][s] = "" + leseTextExtended(z, s);
			}
		}
		return erg;
	}

	public String[][] leseTextblock(int startZeile, int endeZeile,
			int startSpalte, int endeSpalte) {
		String[][] erg = new String[endeZeile - startZeile + 1][endeSpalte
				- startSpalte + 1];
		for (int z = startZeile; (z < anzahlZeilen) && (z <= endeZeile); z++) {
			for (int s = startSpalte; (s < anzahlSpalten) && (s <= endeSpalte); s++) {
				erg[z - startZeile][s - startSpalte] = ""
						+ leseTextExtended(z, s);
			}
		}
		return erg;
	}

}

@SuppressWarnings("serial")
class ZellenEditor extends AbstractCellEditor implements TableCellEditor {
	public static Font font;
	private CTabelle cTabelle;

	Component component = null;

	int zeile = 0;
	int spalte = 0;

	public ZellenEditor(CTabelle cTabelle) {
		this.cTabelle = cTabelle;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int rowIndex, int colIndex) {
		zeile = rowIndex;
		spalte = colIndex;

		Zelle z = (Zelle) value;

		if (z.getCheckBoxEnabled()) {
			component = new JCheckBox();
			((JCheckBox) component).setText(z.text);
			((JCheckBox) component).setSelected(z.chekboxStatus);
			((JCheckBox) component).setFont(font);
		} else {
			component = new JTextField();
			((JTextField) component).setText(z.text);
			((JTextField) component).setFont(font);
		}
		return component;
	}

	@Override
	public Object getCellEditorValue() {
		cTabelle.meldeEditEnde(zeile, spalte);
		if ((component.getClass()).isInstance(new JTextField())) {
			return ((JTextField) component).getText();
		} else if ((component.getClass()).isInstance(new JCheckBox())) {
			return ((JCheckBox) component).isSelected() ? true : false;
		} else {
			return "";
		}
	}
}

@SuppressWarnings("serial")
class ZellenRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);

		if (value instanceof Zelle) {
			Tabellenmodell model = (Tabellenmodell) table.getModel();
			Tabellenmodell.renderTyp typ = model.anzeigeRendern;

			Zelle z = (Zelle) value;
			setFont(table.getFont());
			String text = new String(z.text);
			if (z.getCheckBoxEnabled()) {
				JCheckBox cb = new JCheckBox();
				cb.setSelected(z.chekboxStatus);
				Font f = table.getFont();
				cb.setFont(f);
				if (model.getSpaltenBGfarbe(column) != null) {
					cb.setBackground(model.getSpaltenBGfarbe(column));
					cb.setForeground(model.getSpaltenFGfarbe(column));
					if (model.getSpalteFett(column)) {
						f = f.deriveFont(Font.BOLD);
						cb.setFont(f);
					}
				} else if (typ == Tabellenmodell.renderTyp.zellen) {
					cb.setBackground(((column + row) % 2 == 0) ? model.hintergrundfarbe_1
							: model.hintergrundfarbe_2);
					cb.setForeground(((column + row) % 2 == 0) ? model.fordergrundfarbe_1
							: model.fordergrundfarbe_2);
				} else if (typ == Tabellenmodell.renderTyp.zeilen) {
					cb.setBackground((row % 2 == 0) ? model.hintergrundfarbe_1
							: model.hintergrundfarbe_2);
					cb.setForeground((row % 2 == 0) ? model.fordergrundfarbe_1
							: model.fordergrundfarbe_2);
				} else {
					cb.setBackground(model.hintergrundfarbe_1);
					cb.setForeground(model.fordergrundfarbe_1);
				}

				if (isSelected) {
					cb.setBackground(Color.BLUE);
					cb.setForeground(Color.WHITE);
					cb.setBorder(BorderFactory
							.createLineBorder(Color.DARK_GRAY));
				} else {
					cb.setBorder(BorderFactory
							.createLineBorder(Color.LIGHT_GRAY));
				}

				if (model.leseHervorgehobeneZeile(row)) {
					cb.setBackground(getBackground().brighter());
					cb.setBorder(BorderFactory
							.createLineBorder(Color.DARK_GRAY));
					f = cb.getFont();
					f = f.deriveFont(Font.BOLD);
					cb.setFont(f);
				}
				cb.setText(text);
				return cb;
			} else {

				if (model.getSpaltenBGfarbe(column) != null) {
					setBackground(model.getSpaltenBGfarbe(column));
					setForeground(model.getSpaltenFGfarbe(column));
					if (model.getSpalteFett(column)) {
						Font f = table.getFont();
						f = f.deriveFont(Font.BOLD);
						setFont(f);
					}
				} else if (typ == Tabellenmodell.renderTyp.zellen) {
					setBackground(((column + row) % 2 == 0) ? model.hintergrundfarbe_1
							: model.hintergrundfarbe_2);
					setForeground(((column + row) % 2 == 0) ? model.fordergrundfarbe_1
							: model.fordergrundfarbe_2);
				} else if (typ == Tabellenmodell.renderTyp.zeilen) {
					setBackground((row % 2 == 0) ? model.hintergrundfarbe_1
							: model.hintergrundfarbe_2);
					setForeground((row % 2 == 0) ? model.fordergrundfarbe_1
							: model.fordergrundfarbe_2);
				} else {
					setBackground(model.hintergrundfarbe_1);
					setForeground(model.fordergrundfarbe_1);
				}

				if (isSelected) {
					setBackground(Color.BLUE);
					setForeground(Color.WHITE);
					setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				} else {
					setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				}

				if (model.leseHervorgehobeneZeile(row)) {
					setBackground(getBackground().brighter());
					setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
					Font f = table.getFont();
					f = f.deriveFont(Font.BOLD);
					setFont(f);
				}

				setText(text);
			}

		}
		return this;
	}
}

// Eigener CellRenderer fuer die Spaltenkoepfe. Liefert immer ein
// JLabel zurueck, und implementiert TableCellRenderer
@SuppressWarnings("serial")
class KopfZellenRenderer extends DefaultTableCellRenderer {
	CTabelle cTabelle;
	TableCellRenderer tsr;

	public KopfZellenRenderer(CTabelle cTabelle) {
		this.cTabelle = cTabelle;
		tsr = cTabelle.tabelle.getTableHeader().getDefaultRenderer();
	}

	// einzig wichtige methode
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component c = tsr.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		// System.out.println(value.getClass().toString());
		Font f = table.getFont();
		f = f.deriveFont(Font.BOLD);
		c.setFont(f);
		((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
		((JComponent) c).setBorder(BorderFactory
				.createLineBorder(Color.LIGHT_GRAY));
		((JLabel) c).setText(value.toString());

		return c;
	}
}

class AuswahlAuswertungZellen implements ListSelectionListener {
	CTabelle cTabelle;
	ListSelectionModel model;

	public AuswahlAuswertungZellen(CTabelle cTabelle) {
		this.cTabelle = cTabelle;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selectedRow = cTabelle.tabelle.getSelectedRows();
			int[] selectedColumns = cTabelle.tabelle.getSelectedColumns();
			int[] selectZeilen = new int[selectedRow.length];
			int[] selectSpalten = new int[selectedColumns.length];

			for (int i = 0; i < selectedRow.length; i++) {
				selectZeilen[i] = selectedRow[i];
			}
			for (int j = 0; j < selectedColumns.length; j++) {
				selectSpalten[j] = selectedColumns[j];
			}
			cTabelle.selectZeilen = selectZeilen;
			cTabelle.selectSpalten = selectSpalten;
			if (cTabelle.tabelle.getRowSelectionAllowed()) {
				cTabelle.meldeAnLink(Tabelle.ZEILENSELECT);
			} else {
				cTabelle.meldeAnLink(Tabelle.ZELLENSELECT);
			}
		}
	}
}

class AuswahlAuswertungKopf implements ListSelectionListener {
	CTabelle cTabelle;
	ListSelectionModel model;

	public AuswahlAuswertungKopf(CTabelle cTabelle) {
		this.cTabelle = cTabelle;
		this.model = cTabelle.tabelle.getColumnModel().getSelectionModel();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			if (cTabelle.tabelle.getColumnSelectionAllowed()) {
				cTabelle.meldeAnLink(Tabelle.SPALTENSELECT);
			}
		}
	}

}
