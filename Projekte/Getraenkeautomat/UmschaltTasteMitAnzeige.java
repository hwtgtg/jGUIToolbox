



//%$JGUIToolbox$%//ID fuer Toolboxdateien
/**
 * <h1>UmschaltTasteMitAnzeige</h1>.
 * 
 * Ueber die Methode istGewaehlt() kann der Status abgefragt werden.<br />
 * <br/>
 * 
 * Ist der Kommunikationslink gesetzt, meldet das Objekt eine
 * <b><i>&Auml;nderung</i></b>.<br/>
 * Bei einer Aenderung zu <b>Select</b> wird tuWas(id) <br />
 * bei einer Aenderung zu <b>DesSelect</b> tuWas(<b>id + 1</b> ) aufgerufen.<br />
 * Lokalen IDs als Konstanten. <br />
 * <br />
 * <b>Ausrichtung von Text und Taste:</b><br>
 * <ul>
 * <li>0 = Tastelinks TextLinks,</li>
 * <li>1 = TasteLinks TextCenter,</li>
 * <li>2 = TasteLinks TextRechts</li>
 * <li>4 = TasteRechts TextLinks,</li>
 * <li>5 = TasteRechts TextCenter,</li>
 * <li>6 = TasteRechts TextRechts</li>
 * </ul>
 * <hr>
 * 
 * @author Hans Witt
 * 
 * @version Version 1.1 (14.7.2008) Hinzufuegen von Statusvariablen fuer
 *          Position ...<br />
 *          Version: 1.1.1 (17.7.2008) Neue Komponenten werden von Unten nach
 *          Oben aufgebaut, d.h.vor die alten gesetzt<br />
 *          Version: 2 (3.8.2008) angepasst an geaendertes ITuWas<br />
 *          Version: 3 (9.8.2008) ergaenzt fuer Behaelter fuer GUI-Elemente<br />
 *          Version: 3.1 (14.8.2008) Konstruktor auf int neuesX, int neuesY ,
 *          int neueBreite, int neueHoehe angepasst<br />
 *          Version: 3.2 (18.8.2008) Zustandsvariable auf protected gesetzt<br />
 *          Version 5.0: (4.9.2010 Entfernen fuer Graphikkomponente eingefuehrt<br />
 *          Destruktor entfernt Graphikkomponente automatisch bei gc()
 * 
 */
public class UmschaltTasteMitAnzeige implements IComponente {
	private Behaelter taste;
	private UmschaltTaste knopf;
	private AusgabePanel anzeige;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;
	public int fontGroesse = -1;
	public boolean gedrueckt = false;
	public String anzeigeText = "Drueckmich";
	public String knopfFarbe = "blau";
	public String hintergrundFarbe = "hellgrau";
	public String schriftFarbe = "schwarz";
	ITuWas linkObj; // Damit das
					// Interface
					// angezeigt wird
	protected int ausrichtung = 0;

	// 0 = Knopflinks TextLinks, 1 KnopfLinks TextCenter, 2 KnopfLinks
	// TextRechts
	// 4 = KnopfRechts TextLinks, 5 KnopfRechts TextCenter, 6 KnopfRechts
	// TextRechts

	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public UmschaltTasteMitAnzeige() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public UmschaltTasteMitAnzeige(String neuerText, int neueBreite,
			int neueHoehe, int neueAusrichtung) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, 0, 0, neueBreite,
				neueHoehe, neueAusrichtung);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuerText
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param neueAusrichtung
	 */
	public UmschaltTasteMitAnzeige(String neuerText, int neuesX, int neuesY,
			int neueBreite, int neueHoehe, int neueAusrichtung) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
				neueBreite, neueHoehe, neueAusrichtung);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public UmschaltTasteMitAnzeige(IContainer behaelter) {
		this(behaelter, "Anzeige", 0, 0, 180, 50, 0);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param neuerText
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public UmschaltTasteMitAnzeige(IContainer behaelter, String neuerText,
			int neuesX, int neuesY, int neueBreite, int neueHoehe,
			int neueAusrichtung) {
		breite = neueBreite;
		hoehe = neueHoehe;
		anzeigeText = neuerText;
		taste = new Behaelter(behaelter, 0, 0, neueBreite, neueHoehe);
		knopf = new UmschaltTaste(taste, "", 0, 0, neueHoehe - 2, neueHoehe - 2);
		anzeige = new AusgabePanel(taste, anzeigeText, 0, 0, neueBreite
				- neueHoehe - 3, neueHoehe);
		this.ausrichtung = neueAusrichtung;
		setzeFarbeAnzeigetext(hintergrundFarbe);
		setzeFarbeknopf(knopfFarbe);
		setzePosition(neuesX, neuesY);
	}

	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen
	 * Behaelter hinzuzufuegen
	 */
	public BasisComponente getBasisComponente() {
		return taste.getBasisComponente();
	}

	/**
	 * <b>Ausrichtung von Text und Taste:</b><br>
	 * <ul>
	 * <li>0 = Tastelinks TextLinks,</li>
	 * <li>1 = TasteLinks TextCenter,</li>
	 * <li>2 = TasteLinks TextRechts</li>
	 * <li>4 = TasteRechts TextLinks,</li>
	 * <li>5 = TasteRechts TextCenter,</li>
	 * <li>6 = TasteRechts TextRechts</li>
	 * </ul>
	 */
	public void setzeAusrichtung(int neueAusrichtung) {
		ausrichtung = neueAusrichtung;
		setzePosition(xPos, yPos);
	}

	public void setzePosition(int x, int y) {
		xPos = x;
		yPos = y;

		if (ausrichtung < 3) {
			taste.setzePosition(x, y);
			knopf.setzePosition(1, 1);
			anzeige.setzePosition(hoehe + 3, 0);
			anzeige.setzeAusrichtung(ausrichtung & 3);
		} else {
			taste.setzePosition(x, y);
			knopf.setzePosition(breite - hoehe + 1, 1);
			anzeige.setzePosition(0, 0);
			anzeige.setzeAusrichtung(ausrichtung & 3);
		}
	}

	// Methode noetig zum Hinzufuegen mit Anpassung beim Behaelter
	// Die Enden werden relativ zur aktuellen position verschoben
	public void verschieben(int dx, int dy) {
		setzePosition(xPos + dx, yPos + dy);
	}

	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		taste.setzeGroesse(breite, hoehe);
		knopf.setzeGroesse(hoehe, hoehe);
		anzeige.setzeGroesse(breite - hoehe - 3, hoehe);
		setzePosition(xPos, yPos);
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
		setzePosition(xPos, yPos);
		setzeGroesse(breite, hoehe);
	}

	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param ID
	 */
	public void setzeID(int ID) {
		knopf.setzeID(ID);
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * 
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj) {
		knopf.setzeLink(linkObj);
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param ID
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj, int ID) {
		knopf.setzeLink(linkObj, ID);
	}

	public void setzeAusgabetext(String neuerText) {
		anzeigeText = neuerText;
		anzeige.setzeAusgabetext(anzeigeText);
	}

	public void setzeSchriftgroesse(int neueFontgroesse) {
		fontGroesse = neueFontgroesse;
		anzeige.setzeSchriftgroesse(fontGroesse);
	}

	public void setzeSchriftStilNormal() {
		anzeige.setzeSchriftStilNormal();
	}

	public void setzeSchriftStilFett() {
		anzeige.setzeSchriftStilFett();
	}

	public void setzeSchriftStilKursiv() {
		anzeige.setzeSchriftStilKursiv();
	}

	/**
	 * Status der Komponente abrufen
	 */
	public boolean istGewaehlt() {
		gedrueckt = knopf.istGewaehlt();

		return gedrueckt;
	}

	/**
	 * Status der Komponente auf >>gewaehlt<< setzen
	 */
	public void setzeGewaehlt() {
		gedrueckt = true;
		knopf.setzeGewaehlt();
	}

	/**
	 * Status der Komponente auf >>Nicht gewaehlt<< setzen
	 */
	public void setzeNichtGewaehlt() {
		gedrueckt = false;
		knopf.setzeNichtGewaehlt();
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbeknopf(String neueFarbe) {
		knopfFarbe = neueFarbe;
		knopf.setzeHintergrundfarbe(knopfFarbe);
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbeAnzeigetext(String neueFarbe) {
		hintergrundFarbe = neueFarbe;
		anzeige.setzeHintergrundfarbe(hintergrundFarbe);
	}

	public void setzeSchriftfarbe(String neueFarbe) {
		schriftFarbe = neueFarbe;
		anzeige.setzeSchriftfarbe(schriftFarbe);
	}

	/**
	 * Entfernen des Graphikobjekts
	 */
	public void entfernen() {
		if (taste != null) {
			knopf.entfernen();
			anzeige.entfernen();
			taste.entfernen();

			taste = null;
			knopf = null;
			anzeige = null;
		}
	}

	/**
	 * Destruktor
	 */
	@Override
	protected void finalize() {
		if (!Zeichnung.verweistesGUIElementEntfernen)
			return;
		if (taste != null)
			entfernen();
	}
	
}
