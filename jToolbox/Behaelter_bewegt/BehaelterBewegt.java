//%$JGUIToolbox$%//ID fuer Toolboxdateien
/**
 * Die Klasse BehaelterBewegt bewegt der Behaelter drehbar. \\
 * 
 * Die Methoden bewegen_... laufen parallel zum normalen Ablauf und werden als Liste abgearbeitet.
 * 
 * Um andere Aktionen zeitgenau ausfuehren zu koennen, kann mit der Methode bewegen_WartenAufEndeBewegung das Ende der eingegebenen Bewegungsfolge erwartet werden.
 */

//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.Component;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author Witt
 *
 */
public class BehaelterBewegt implements IContainer, IComponente, ITuWas {

	private static float PI = (float) Math.PI;
	private static int BilderProSekundeSTART = 20;

	private static final int BEWEGUNG = 100;
	static final int ZEIT_STARTBEWEGUNG = 1;
	private static final int ZEIT_PAUSE = 20;
	private static final int ZEIT_MINIMUM = 5;
	private static final int ZEIT_EINRICHTEN = 1;
	private int ZEIT_SCHRITT = 1000 / BilderProSekundeSTART;

	public void setBilderProSekunde(int bilderProSekunde) {
		ZEIT_SCHRITT = 1000 / bilderProSekunde;
	}

	private BewegungTyp bewegungsart = BewegungTyp.AKTUELLEBEWEGUNGENDE;

	private static int ZEIT_BEWEGUNGAKTIVTEST = 10;

	BehaelterDrehbar drehbehaelter;

	Schrittliste auftragsliste;

	Taktgeber ticker;

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public BehaelterBewegt() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterBewegt(int neueBreite, int neueHoehe) {
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
	public BehaelterBewegt(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public BehaelterBewegt(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterBewegt(IContainer behaelter, int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		drehbehaelter = new BehaelterDrehbar(behaelter, neuesX, neuesY, neueBreite, neueHoehe);

		auftragsliste = new Schrittliste(this);

		ticker = new Taktgeber(this, BEWEGUNG);
		ticker.setzeAnfangsZeitverzoegerung(ZEIT_MINIMUM);

	}

	// ----Bewegter Drehbehaelter----------------------------------------

	float richtungRelativZurBewegungsrichtungBogenmass = 0;
	float bewegungsrichtungBogenmass = 0;

	float posRotX = 0;
	float posRotY = 0;

	float geschwindigkeit = 10;

	public int leseRotationspunkt_X() {
		return (int) posRotX;
	}

	public int leseRotationspunkt_Y() {
		return (int) posRotY;
	}

	public Point2D.Float leseRotationspunkt() {
		return new Point2D.Float(posRotX, posRotY);
	}

	// Der Behaelter wird relativ zur Bewegungsrichtungrichtung gedreht.
	public void setzeRichtungRelativZurBewegungsrichtungGradmass(float winkel) {
		setzeRichtungRelativZurBewegungsrichtungBogenmass(winkel * PI / 180);
	}

	public void setzeRichtungRelativZurBewegungsrichtungBogenmass(float winkel) {
		richtungRelativZurBewegungsrichtungBogenmass = winkel;
		setzeWinkelBogenmass(bewegungsrichtungBogenmass + richtungRelativZurBewegungsrichtungBogenmass);
	}

	// Die Bewegungsrichtungrichtung des Koerpers wird festgelegt.
	// Der Behaelter dreht sich in die Richtung
	public void setzeBewegungsrichtungGradmass(float winkel) {
		setzeBewegungsrichtungBogenmass(winkel * PI / 180);
	}

	public void setzeBewegungsrichtungBogenmass(float winkel) {
		bewegungsrichtungBogenmass = winkel;
		setzeWinkelBogenmass(bewegungsrichtungBogenmass + richtungRelativZurBewegungsrichtungBogenmass);
	}

	public void aenderBewegungsrichtungGradmass(float winkel) {
		aenderBewegungsrichtungBogenmass(winkel * PI / 180);
	}

	public void aenderBewegungsrichtungBogenmass(float winkel) {
		bewegungsrichtungBogenmass += winkel;
		setzeWinkelBogenmass(bewegungsrichtungBogenmass + richtungRelativZurBewegungsrichtungBogenmass);
	}

	// Bewegung

	public void bewegen_NeuePosition(float posRotX, float posRotY) {
		auftragsliste.hinzufuegenNeuePosition(posRotX, posRotY);
	}

	public void bewegen_NeueAusrichtungGradmass(float bewegungsrichtungBogenmass) {
		bewegen_NeueAusrichtungBogenmass(bewegungsrichtungBogenmass * PI / 180);
	}

	public void bewegen_NeueAusrichtungBogenmass(float bewegungsrichtungBogenmass) {
		auftragsliste.hinzufuegenNeueAusrichtung(bewegungsrichtungBogenmass);
	}

	public void bewegen_Delay(float delay) {
		auftragsliste.hinzufuegenDelay(delay);
	}

	public void bewegen_LINEAR(float zielX, float zielY, float geschwindigkeit) {
		bewegen_WartenAufEndeBewegen();
		Point2D.Float p = leseRotationspunkt();
		float laenge = (float) Math.sqrt((zielX - p.x) * (zielX - p.x) + (zielY - p.y) * (zielY - p.y));
		float winkel = 0;
		if (zielX == p.x) {
			if (zielY > p.y) {
				winkel = (float) Math.PI / 2;
			} else {
				winkel = -((float) Math.PI / 2);
			}
		} else {
			winkel = (float)Math.atan((zielY - p.y) /(zielX - p.x) );
			if (zielX < p.x) {
				winkel = (float)(winkel+Math.PI);
			} 
		}
		bewegen_NeueAusrichtungBogenmass(winkel);
		bewegen_LINEAR(laenge, geschwindigkeit);
	}

	public void bewegen_LINEAR(float laenge, float geschwindigkeit) {
		auftragsliste.hinzufuegenBEWEGUNG_LINEAR_LAENGE(laenge, geschwindigkeit);
	}

	public void bewegen_KREIS_Grad(float radius, float grad, float geschwindigkeit) {
		bewegen_KREIS_Bogen(radius, grad * PI / 180, geschwindigkeit);

	}

	public void bewegen_KREIS_Bogen(float radius, float bogen, float geschwindigkeit) {
		auftragsliste.hinzufuegenBEWEGUNG_KREIS(radius, bogen, geschwindigkeit);
	}

	public void bewegen_DREHEN_Grad(float aenderungBogenmass, float zeit) {
		bewegen_DREHEN_Bogen(aenderungBogenmass * PI / 180, zeit);
	}

	public void bewegen_DREHEN_Bogen(float aenderungBogenmass, float zeit) {
		auftragsliste.hinzufuegenBEWEGUNG_DREHEN(aenderungBogenmass, zeit);
	}

	public void bewegen_WartenAufEndeBewegen() {
		while (!auftragsliste.isEmpty()) {
			StaticTools.warte(ZEIT_BEWEGUNGAKTIVTEST);
		}
	}

	// ----Drehbehaelter bewegen durchfuehren

	private Schritt aktuellerSchritt = null;
	// Zum Merken fuer Pause
	private BewegungTyp aktuelleBewegungsart = BewegungTyp.PAUSE; // Merker
																	// fuer
																	// Pause
	private int aktuelleZeitFuerTicker = ZEIT_MINIMUM; // fuer Pause vorgesehen
														// zum Merken

	private void einrichtenPosition() {
		aktuelleBewegungsart = aktuellerSchritt.art;
		bewegungsart = aktuelleBewegungsart;
		aktuelleZeitFuerTicker = ZEIT_MINIMUM;
		ticker.einmal(ZEIT_EINRICHTEN);
	}

	private void ausfuehrenPosition() {
		setzePositionRotationszentrum(aktuellerSchritt.posRotX, aktuellerSchritt.posRotY);

		aktuelleBewegungsart = BewegungTyp.AKTUELLEBEWEGUNGENDE;
		bewegungsart = aktuelleBewegungsart;
		aktuelleZeitFuerTicker = ZEIT_MINIMUM;
		ticker.einmal(ZEIT_MINIMUM);
	}

	private void einrichtenAusrichtung() {
		aktuelleBewegungsart = aktuellerSchritt.art;
		bewegungsart = aktuelleBewegungsart;
		aktuelleZeitFuerTicker = ZEIT_MINIMUM;
		ticker.einmal(ZEIT_EINRICHTEN);
	}

	private void ausfuehrenAusrichtung() {
		setzeBewegungsrichtungBogenmass(aktuellerSchritt.winkelBogenmass);

		aktuelleBewegungsart = BewegungTyp.AKTUELLEBEWEGUNGENDE;
		bewegungsart = aktuelleBewegungsart;
		aktuelleZeitFuerTicker = ZEIT_MINIMUM;
		ticker.einmal(ZEIT_MINIMUM);
	}

	private float verbleibendesDelay = 0;

	private void einrichtenDelay() {
		aktuelleBewegungsart = aktuellerSchritt.art;
		bewegungsart = aktuelleBewegungsart;
		verbleibendesDelay = aktuellerSchritt.zeit;

		aktuelleZeitFuerTicker = ZEIT_SCHRITT;
		ticker.einmal(ZEIT_SCHRITT);
	}

	private void ausfuehrenDelay() {
		verbleibendesDelay -= ZEIT_SCHRITT;
		if (verbleibendesDelay > 0) {
			aktuelleZeitFuerTicker = ZEIT_SCHRITT;
			ticker.einmal(ZEIT_SCHRITT);
		} else {
			aktuelleBewegungsart = BewegungTyp.AKTUELLEBEWEGUNGENDE;
			bewegungsart = aktuelleBewegungsart;
			aktuelleZeitFuerTicker = ZEIT_MINIMUM;
			ticker.einmal(ZEIT_MINIMUM);
		}
	}

	private float deltaX = 0;
	private float deltaY = 0;

	private float endePosX = 0;
	private float endePosY = 0;

	private void einrichtenLinear() {
		aktuelleBewegungsart = aktuellerSchritt.art;
		bewegungsart = aktuelleBewegungsart;
		verbleibendesDelay = 1000 * aktuellerSchritt.groesse / aktuellerSchritt.geschwindigkeit;

		float deltaLaenge = aktuellerSchritt.groesse / (verbleibendesDelay / ZEIT_SCHRITT);

		endePosX = posRotX + (float) (aktuellerSchritt.groesse * Math.cos(bewegungsrichtungBogenmass));
		endePosY = posRotY + (float) (aktuellerSchritt.groesse * Math.sin(bewegungsrichtungBogenmass));

		deltaX = (float) (deltaLaenge * Math.cos(bewegungsrichtungBogenmass));
		deltaY = (float) (deltaLaenge * Math.sin(bewegungsrichtungBogenmass));

		aktuelleZeitFuerTicker = ZEIT_SCHRITT;
		ticker.einmal(ZEIT_SCHRITT);
	}

	private void ausfuehrenLinear() {
		verbleibendesDelay -= ZEIT_SCHRITT;
		if (verbleibendesDelay > 0) {
			aenderePositionRotationszentrum(deltaX, deltaY);
			ticker.einmal(ZEIT_SCHRITT);
		} else {
			setzePositionRotationszentrum(endePosX, endePosY);
			aktuelleBewegungsart = BewegungTyp.AKTUELLEBEWEGUNGENDE;
			bewegungsart = aktuelleBewegungsart;
			aktuelleZeitFuerTicker = ZEIT_MINIMUM;
			ticker.einmal(ZEIT_MINIMUM);
		}
	}

	private float kreisMittelpunktX = 0;
	private float kreisMittelpunktY = 0;
	private float deltaBogenmass = 0;

	private float endeBewegungsrichtungBogenmass = 0;

	private void einrichtenKreis() {
		aktuelleBewegungsart = aktuellerSchritt.art;
		bewegungsart = aktuelleBewegungsart;
		// Kreismittelpunkt
		kreisMittelpunktX = (float) (posRotX - Math.sin(bewegungsrichtungBogenmass) * aktuellerSchritt.radius);
		kreisMittelpunktY = (float) (posRotY + Math.cos(bewegungsrichtungBogenmass) * aktuellerSchritt.radius);

		// Bogenlaenge
		float laenge = aktuellerSchritt.radius * aktuellerSchritt.winkelBogenmass;

		verbleibendesDelay = 1000 * laenge / aktuellerSchritt.geschwindigkeit;

		// float deltaBogen = laenge / (verbleibendesDelay / ZEIT_SCHRITT);
		// deltaBogenmass = aktuellerSchritt.winkelBogenmass /
		// (verbleibendesDelay / ZEIT_SCHRITT);

		// Falls der Radius negativ ist, wird hier die Zeit wieder positiv

		verbleibendesDelay = (verbleibendesDelay > 0) ? verbleibendesDelay : -verbleibendesDelay;
		deltaBogenmass = aktuellerSchritt.winkelBogenmass / (verbleibendesDelay / ZEIT_SCHRITT);

		deltaBogenmass = (aktuellerSchritt.radius > 0) ? deltaBogenmass : -deltaBogenmass;

		endeBewegungsrichtungBogenmass = bewegungsrichtungBogenmass + ((aktuellerSchritt.radius > 0)
				? aktuellerSchritt.winkelBogenmass : -aktuellerSchritt.winkelBogenmass);

		endePosX = (float) (kreisMittelpunktX + Math.sin(endeBewegungsrichtungBogenmass) * aktuellerSchritt.radius);
		endePosY = (float) (kreisMittelpunktY - Math.cos(endeBewegungsrichtungBogenmass) * aktuellerSchritt.radius);

		aktuelleZeitFuerTicker = ZEIT_SCHRITT;
		ticker.einmal(ZEIT_SCHRITT);
	}

	private void ausfuehrenKreis() {
		verbleibendesDelay -= ZEIT_SCHRITT;
		if (verbleibendesDelay > 0) {

			bewegungsrichtungBogenmass += deltaBogenmass;
			posRotX = (float) (kreisMittelpunktX + Math.sin(bewegungsrichtungBogenmass) * aktuellerSchritt.radius);
			posRotY = (float) (kreisMittelpunktY - Math.cos(bewegungsrichtungBogenmass) * aktuellerSchritt.radius);

			setzeBewegungsrichtungBogenmass(bewegungsrichtungBogenmass);
			setzePositionRotationszentrum(posRotX, posRotY);
			ticker.einmal(ZEIT_SCHRITT);
		} else {
			setzeBewegungsrichtungBogenmass(endeBewegungsrichtungBogenmass);
			setzePositionRotationszentrum(endePosX, endePosY);
			aktuelleBewegungsart = BewegungTyp.AKTUELLEBEWEGUNGENDE;
			bewegungsart = aktuelleBewegungsart;
			aktuelleZeitFuerTicker = ZEIT_MINIMUM;
			ticker.einmal(ZEIT_MINIMUM);
		}
	}

	private void einrichtenDrehen() {
		aktuelleBewegungsart = aktuellerSchritt.art;
		bewegungsart = aktuelleBewegungsart;

		verbleibendesDelay = aktuellerSchritt.zeit;

		verbleibendesDelay = (verbleibendesDelay > 0) ? verbleibendesDelay : -verbleibendesDelay;
		deltaBogenmass = aktuellerSchritt.winkelBogenmass / (verbleibendesDelay / ZEIT_SCHRITT);

		endeBewegungsrichtungBogenmass = bewegungsrichtungBogenmass + aktuellerSchritt.winkelBogenmass;

		aktuelleZeitFuerTicker = ZEIT_SCHRITT;
		ticker.einmal(ZEIT_SCHRITT);
	}

	private void ausfuehrenDrehen() {
		verbleibendesDelay -= ZEIT_SCHRITT;
		if (verbleibendesDelay >= 0) {

			bewegungsrichtungBogenmass += deltaBogenmass;

			setzeBewegungsrichtungBogenmass(bewegungsrichtungBogenmass);
			ticker.einmal(ZEIT_SCHRITT);
		} else {
			setzeBewegungsrichtungBogenmass(endeBewegungsrichtungBogenmass);
			aktuelleBewegungsart = BewegungTyp.AKTUELLEBEWEGUNGENDE;
			bewegungsart = aktuelleBewegungsart;
			aktuelleZeitFuerTicker = ZEIT_MINIMUM;
			ticker.einmal(ZEIT_MINIMUM);
		}
	}

	private void neueBewegungEinrichten() {
		aktuellerSchritt = auftragsliste.pollFirst();
		if (aktuellerSchritt == null) {
			aktuelleBewegungsart = BewegungTyp.LISTEENDE;
			bewegungsart = aktuelleBewegungsart;
		} else {
			switch (aktuellerSchritt.art) {
			case POSITION:
				einrichtenPosition();
				break;
			case AUSRICHTUNG:
				einrichtenAusrichtung();
				break;
			case DELAY:
				einrichtenDelay();
				break;
			case BEWEGUNG_LINEAR:
				einrichtenLinear();
				break;
			case BEWEGUNG_KREIS:
				einrichtenKreis();
				break;
			case BEWEGUNG_DREHEN:
				einrichtenDrehen();
				break;
			case AKTUELLEBEWEGUNGENDE:
				neueBewegungEinrichten();
				break;
			case LISTEENDE:
				auftragsliste.clear();
				break;
			default:
				ticker.setzeZeitZwischenAktionen(ZEIT_STARTBEWEGUNG);
				break;
			}
		}
	}

	private void bewegenTicker() {
		switch (bewegungsart) {
		case PAUSE:
			ticker.einmal(ZEIT_PAUSE);
			break;
		case WEITER:
			ticker.einmal(ZEIT_PAUSE);
			break;
		case STOP:
			ticker.einmal(ZEIT_PAUSE);
			break;
		case AKTUELLEBEWEGUNGENDE:
			neueBewegungEinrichten();
			break;
		case POSITION:
			ausfuehrenPosition();
			break;
		case AUSRICHTUNG:
			ausfuehrenAusrichtung();
			break;
		case DELAY:
			ausfuehrenDelay();
			break;
		case BEWEGUNG_LINEAR:
			ausfuehrenLinear();
			break;
		case BEWEGUNG_KREIS:
			ausfuehrenKreis();
			break;
		case BEWEGUNG_DREHEN:
			ausfuehrenDrehen();
			break;

		default:
			break;
		}

	}

	@Override
	public void tuWas(int ID) {
		if (ID == BEWEGUNG) {
			bewegenTicker();
		}
	}

	// ----Drehbehaelter----------------------------------------

	@SuppressWarnings("unused")
	private float leseWinkelGradmass() {
		return drehbehaelter.leseWinkelGradmass();
	}

	@SuppressWarnings("unused")
	private void setzeWinkelGradmass(float winkel) {
		drehbehaelter.setzeWinkelGradmass(winkel);
	}

	@SuppressWarnings("unused")
	private float leseWinkelBogenmass() {
		return drehbehaelter.leseWinkelBogenmass();
	}

	private void setzeWinkelBogenmass(float winkel) {
		drehbehaelter.setzeWinkelBogenmass(winkel);
	}

	public void setzeRotationszentrumRelativ(float rotXRel, float rotYRel) {
		// posRotX = posRotX + neueBreite / 2;
		// posRotY = neuesY + neueHoehe / 2;
		drehbehaelter.setzeRotationszentrumRelativ(rotXRel, rotYRel);
	}

	public void setzePositionRotationszentrum(float posRotX, float posRotY) {
		this.posRotX = posRotX;
		this.posRotY = posRotY;
		drehbehaelter.setzePositionRotationszentrum(posRotX, posRotY);
	}

	public void aenderePositionRotationszentrum(float deltaX, float deltaY) {
		this.posRotX += deltaX;
		this.posRotY += deltaY;
		drehbehaelter.setzePositionRotationszentrum(posRotX, posRotY);
	}

	// -Behaelter-Methoden-----------------------------------------------------------------------
	public void hinzufuegen(IComponente comp) {
		drehbehaelter.hinzufuegen(comp);
	}

	public void hinzufuegenUndAnpassen(IComponente comp) {
		drehbehaelter.hinzufuegenUndAnpassen(comp);
	}

	public void setzeBeschreibungstext(String neuerText) {
		drehbehaelter.setzeBeschreibungstext(neuerText);
	}

	public void setzeSchriftName(String name) {
		drehbehaelter.setzeSchriftName(name);
	}

	public void setzeSchriftgroesse(int neueFontgroesse) {
		drehbehaelter.setzeSchriftgroesse(neueFontgroesse);
	}

	public void setzeSchriftStilNormal() {
		drehbehaelter.setzeSchriftStilNormal();
	}

	public void setzeSchriftStilFett() {
		drehbehaelter.setzeSchriftStilFett();
	}

	public void setzeSchriftStilKursiv() {
		drehbehaelter.setzeSchriftStilKursiv();
	}

	public void setzeFarbe(String neueFarbe) {
		drehbehaelter.setzeFarbe(neueFarbe);
	}

	public void setzeHintergrundfarbe(String neueFarbe) {
		drehbehaelter.setzeHintergrundfarbe(neueFarbe);
	}

	public void setzeMitRand(boolean mitRand) {
		drehbehaelter.setzeMitRand(mitRand);
	}

	public void setzeDimensionen(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		posRotX = neuesX + neueBreite / 2;
		posRotY = neuesY + neueHoehe / 2;
		drehbehaelter.setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
	}

	public void sichtbarMachen() {
		drehbehaelter.sichtbarMachen();
	}

	public void unsichtbarMachen() {
		drehbehaelter.unsichtbarMachen();
	}

	public void setzeMitRaster(boolean mitRaster) {
		drehbehaelter.setzeMitRaster(false, mitRaster);
	}

	public void setzeMitRaster(boolean innenMitRaster, boolean mitRaster) {
		drehbehaelter.setzeMitRaster(innenMitRaster, mitRaster);
	}

	public void setzeDeltaX(int deltaX) {
		drehbehaelter.setzeDeltaX(deltaX, deltaX);
	}

	public void setzeDeltaX(int innenDeltaX, int deltaX) {
		drehbehaelter.setzeDeltaX(innenDeltaX, deltaX);
	}

	public void setzeDeltaY(int deltaY) {
		drehbehaelter.setzeDeltaY(deltaY, deltaY);
	}

	public void setzeDeltaY(int innenDeltaY, int deltaY) {
		drehbehaelter.setzeDeltaY(innenDeltaY, deltaY);
	}

	public JPanel getPanel() {
		return drehbehaelter.getPanel();
	}

	/**
	 * Entfernen des Graphikobjekts
	 */
	public void entfernen() {
		if (drehbehaelter != null) {
			drehbehaelter.entfernen();
			drehbehaelter = null;
		}
	}

	/**
	 * Destruktor
	 */
	@Override
	protected void finalize() {
		if (!Zeichnung.verweistesGUIElementEntfernen)
			return;
		if (drehbehaelter != null)
			entfernen();
	}

	// ------------------------------------------------------------------------
	@Override
	public BasisComponente getBasisComponente() {
		return drehbehaelter.getBasisComponente();
	}

	@Override
	public Component add(Component comp, int index) {
		return drehbehaelter.add(comp, index);
	}

	@Override
	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y, int width, int height) {
		drehbehaelter.setzeKomponentenKoordinaten(obj, x, y, width, height);
	}

	@Override
	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		setzeKomponentenGroesse(obj, width, height);
	}

	@Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		drehbehaelter.setzeKomponentenPosition(obj, x, y);
	}

	@Override
	public void validate() {
		drehbehaelter.validate();
	}

	@Override
	public double getBehaelterZoom() {
		return drehbehaelter.getBehaelterZoom();
	}

	@Override
	public double setzeZoomfaktor(double zf) {
		return drehbehaelter.setzeZoomfaktor(zf);
	}

}

enum BewegungTyp {
	LISTEENDE, AKTUELLEBEWEGUNGENDE, POSITION, AUSRICHTUNG, BEWEGUNG_LINEAR, BEWEGUNG_KREIS, BEWEGUNG_DREHEN, DELAY, PAUSE, WEITER, STOP
}

class Schritt {
	BewegungTyp art;
	float geschwindigkeit;
	float zeit;
	float posRotX;
	float posRotY;
	float radius;
	float winkelBogenmass;
	float groesse;

	public Schritt(BewegungTyp art, float geschwindigkeit, float zeit, float posRotX, float posRotY, float radius,
			float winkelBogenmass, float groesse) {
		this.art = art;
		this.geschwindigkeit = geschwindigkeit;
		this.zeit = zeit;
		this.posRotX = posRotX;
		this.posRotY = posRotY;
		this.radius = radius;
		this.winkelBogenmass = winkelBogenmass;
		this.groesse = groesse;
	}
}

@SuppressWarnings("serial")
class Schrittliste extends LinkedList<Schritt> {
	BehaelterBewegt behaelter = null;

	@SuppressWarnings("unused")
	private Schrittliste() {
		super();
	}

	public Schrittliste(BehaelterBewegt behaelter) {
		super();
		this.behaelter = behaelter;
	}

	public synchronized void hinzufuegenNeuePosition(float posRotX, float posRotY) {
		add(new Schritt(BewegungTyp.POSITION, 0, 0, posRotX, posRotY, 0, 0, 0));
	}

	public synchronized void hinzufuegenNeueAusrichtung(float bewegungsrichtungBogenmass) {
		add(new Schritt(BewegungTyp.AUSRICHTUNG, 0, 0, 0, 0, 0, bewegungsrichtungBogenmass, 0));
	}

	public synchronized void hinzufuegenDelay(float delay) {
		add(new Schritt(BewegungTyp.DELAY, 0, delay, 0, 0, 0, 0, 0));
	}

	public synchronized void hinzufuegenBEWEGUNG_LINEAR_LAENGE(float laenge, float geschwindigkeit) {
		add(new Schritt(BewegungTyp.BEWEGUNG_LINEAR, geschwindigkeit, 0, 0, 0, 0, 0, laenge));
	}

	public synchronized void hinzufuegenBEWEGUNG_KREIS(float radius, float aenderungBogenmass, float geschwindigkeit) {
		add(new Schritt(BewegungTyp.BEWEGUNG_KREIS, geschwindigkeit, 0, 0, 0, radius, aenderungBogenmass, 0));
	}

	public synchronized void hinzufuegenBEWEGUNG_DREHEN(float aenderungBogenmass, float zeit) {
		add(new Schritt(BewegungTyp.BEWEGUNG_DREHEN, 0, zeit, 0, 0, 0, aenderungBogenmass, 0));
	}

	public boolean add(Schritt schritt) {
		boolean erg = false;
		if (super.isEmpty()) {
			super.add(schritt);
			erg = super.add(new Schritt(BewegungTyp.LISTEENDE, 0, 0, 0, 0, 0, 0, 0));
			behaelter.ticker.einmal(BehaelterBewegt.ZEIT_STARTBEWEGUNG);
		} else {
			Schritt temp = removeLast();
			super.add(schritt);
			erg = super.add(temp);
		}

		return erg;
	}

	public synchronized Schritt pollFirst() {
		return super.pollFirst();
	}

	public synchronized void clear() {
		super.clear();
	}

	public synchronized boolean isEmpty() {
		return super.isEmpty();
	}

}
