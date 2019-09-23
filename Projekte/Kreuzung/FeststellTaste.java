//%$JGUIToolbox$%//ID fuer Toolboxdateien

/**
 * <h1>Feststelltaste</h1> ist eine Taste mit zwei Zustaenden, bei der der Gedrueckt-Zustand per Programm geloest wird.<br/>
 * Durch die Maus kann der Zustand von nichtGedrueckt auf gedrueckt geaendert
 * werden <br />
 * 
 * Zurueckgesetzt kann der Zustand nur durch das Programm werden.<br />
 * 
 * Ueber die Methode setzeTextNichtGedrueckt und setzeGedrueckt kann der
 * Anzeigetext fuer beide Moeglichkeiten gesetzt werden<br />
 * 
 * Im Konstruktor werden beide Anzeigetext uebergeben<br />
 * 
 * Ueber die Methode istGedrueckt() kann der Status abgefragt werden. Der Status
 * wird dadurch nicht zurueckgesetzt !<br />
 * 
 * setzeGewaehlt() und setzeNichtGewaehlt() setzen den Status der Umschalttaste<br />
 * <br />
 * 
 * Ist der Kommunikationslink gesetzt, meldet das Objekt das
 * <b><i>Dr&uuml;cken</i></b> der Taste. <br/>
 * <br/>
 * <hr>
 * 
 * @author Hans Witt
 * @version Version: 3.1 (16.8.2008)<br />
 * 
 *          Version 3.2 (18.8.2008) Zustandsvariable auf protected gesetzt
 */
public class FeststellTaste implements ITuWas, IComponente {

	private Taste knopf;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;
	public int fontGroesse = -1;
	public boolean gedrueckt = false;
	public String anzeigeTextNichtGedrueckt = "Drueckmich";
	public String anzeigeTextGedrueckt = "Gedrueckt";
	public String knopfFarbeNichtGedrueckt = "blau";
	public String knopfFarbeGedrueckt = "blau";
	public String schriftFarbe = "schwarz";

	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public FeststellTaste() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public FeststellTaste(String textNichtGedrueckt, String textGedrueckt,
			int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), textNichtGedrueckt, textGedrueckt,
				0, 0, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param textNichtGedrueckt
	 * @param textGedrueckt
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public FeststellTaste(String textNichtGedrueckt, String textGedrueckt,
			int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), textNichtGedrueckt, textGedrueckt,
				neuesX, neuesY, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public FeststellTaste(IContainer behaelter) {
		this(behaelter, "Bitte Druecken", "Gedrueckt! Bitte warten!", 0, 0,
				180, 80);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param textNichtGedrueckt
	 * @param textGedrueckt
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public FeststellTaste(IContainer behaelter, String textNichtGedrueckt,
			String textGedrueckt, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;

		anzeigeTextNichtGedrueckt = textNichtGedrueckt;
		anzeigeTextGedrueckt = textGedrueckt;

		knopf = new Taste(behaelter, anzeigeTextNichtGedrueckt, neuesX, neuesY,
				neueBreite, neueHoehe);
		gedrueckt = false;
		zeigeStatus();
		knopf.setzeLink(this, 0);
	}

	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen
	 * Behaelter hinzuzufuegen
	 */
	@Override
	public BasisComponente getBasisComponente() {
		return knopf.getBasisComponente();
	}

	public void setzePosition(int neuesX, int neuesY) {
		xPos = neuesX;
		yPos = neuesY;
		knopf.setzePosition(xPos, yPos);
	}

	// Methode noetig zum Hinzufuegen mit Anpassung beim Behaelter
	// Die Enden werden relativ zur aktuellen position verschoben
	public void verschieben(int dx, int dy) {
		setzePosition(xPos + dx, yPos + dy);
	}

	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		knopf.setzeGroesse(breite, hoehe);
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
		knopf.setzeDimensionen(xPos, yPos, breite, hoehe);
	}

	public void setzeTextNichtGedrueckt(String textNichtGedrueckt) {
		anzeigeTextNichtGedrueckt = textNichtGedrueckt;
		zeigeStatus();
	}

	public void setzeTextGedrueckt(String textGedrueckt) {
		anzeigeTextGedrueckt = textGedrueckt;
		zeigeStatus();
	}

	public void setzeSchriftgroesse(int neueFontgroesse) {
		fontGroesse = neueFontgroesse;
		knopf.setzeSchriftgroesse(fontGroesse);
	}

	public void setzeSchriftStilNormal() {
		knopf.setzeSchriftStilNormal();
	}
	
	public void setzeSchriftStilFett() {
		knopf.setzeSchriftStilFett();
	}
	
	public void setzeSchriftStilKursiv() {
		knopf.setzeSchriftStilKursiv();
	}
	
	/**
	 * Wartet, bis die Taste gedrueckt wurde. Dabei wird der Status
	 * zurueckgesetzt
	 */
	public void warteBisGedrueckt() {
		knopf.warteBisGedrueckt();
		setzeNichtGewaehlt();
	}

	/**
	 * Status der Komponente abrufen
	 */
	public boolean istGedrueckt() {
		boolean erg = gedrueckt;
		return erg;
	}

	/**
	 * Status der Komponente auf >>gewaehlt<< setzen
	 */
	public void setzeGewaehlt() {
		gedrueckt = true;
		zeigeStatus();
	}

	/**
	 * Status der Komponente auf >>Nicht gewaehlt<< setzen
	 */
	public void setzeNichtGewaehlt() {
		gedrueckt = false;
		zeigeStatus();
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbeNichtGedrueckt(String neueFarbe) {
		knopfFarbeNichtGedrueckt = neueFarbe;
		zeigeStatus();
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbeGedrueckt(String neueFarbe) {
		knopfFarbeGedrueckt = neueFarbe;
		zeigeStatus();
	}

	private void zeigeStatus() {
		if (gedrueckt) {
			knopf.setzeAusgabetext(anzeigeTextGedrueckt);
			knopf.setzeHintergrundfarbe(knopfFarbeGedrueckt);
		} else {
			knopf.setzeAusgabetext(anzeigeTextNichtGedrueckt);
			knopf.setzeHintergrundfarbe(knopfFarbeNichtGedrueckt);
		}
	}

	public void setzeSchriftfarbe(String neueFarbe) {
		schriftFarbe = neueFarbe;
		knopf.setzeSchriftfarbe(schriftFarbe);
	}

	ITuWas linkObj;
	int id = 0; // ID der Komponente fuer Callback

	/**
	 * Setzen des KommunikationsIDs. Der ID wird beim Aufruf von tuWas
	 * zur&uuml;ckgegeben und kann den Aufrufer indentifizieren<br/>
	 * 
	 * @param ID
	 */
	public void setzeID(int ID) {
		this.id = ID;
	}

	/**
	 * Setzen des Kommunikationslinks<br/>
	 * 
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj) {
		this.linkObj = linkObj;
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
		this.linkObj = linkObj;
		id = ID;
	}

	/**
	 * Diese Methode wird von der Taste der Komponente selbst aufgerufen
	 */
	@Override
	public void tuWas(int ID) { // Wird aufgerufen beim Druecken des Knopfs
		if (!gedrueckt) {
			gedrueckt = true;
			zeigeStatus();
			if (linkObj != null) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						linkObj.tuWas(id);
					}
				}).start();
			}
		}

	}

	/**
	 * Entfernen des Graphikobjekts
	 */
	public void entfernen() {
		if (knopf != null) {
			knopf.entfernen();
			knopf = null;
		}
	}

	/**
	 * Destruktor
	 */
	@Override
	protected void finalize() {
		if (!Zeichnung.verweistesGUIElementEntfernen)
			return;
		if (knopf != null)
			entfernen();
	}

}
