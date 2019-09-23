//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * <h1>Bildklasse zur Darstellung einer Bilddatei</h1>.<br/>
 * 
 * @author Hans Witt, Claus Behl
 * 
 * @version Version 1 (4.12.08)<br/>
 *          Version 5.0: (4.9.2010) Entfernen fuer Graphikkomponente eingefuehrt<br/>
 * 
 */
public class Bild implements IComponente {
	private CBild obj;
	private BufferedImage bild;
	public int breite = 0;
	public int hoehe = 0;

	public int xPos = 0;
	public int yPos = 0;
	public boolean sichtbar = true;
	public boolean zoomen = false;

	/**
	 * 
	 * @param filename
	 */
	public Bild(String filename) {
		this(Zeichnung.gibZeichenflaeche(), filename);
	}

	/**
	 * 
	 * @param datei
	 */
	public Bild(BilddateiAbstrakt datei) {
		this(Zeichnung.gibZeichenflaeche(), datei);
	}

	/**
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 * @param filename
	 */
	public Bild(int neueBreite, int neueHoehe, String filename) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe,
				filename);
	}

	/**
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 * @param datei
	 */
	public Bild(int neueBreite, int neueHoehe, BilddateiAbstrakt datei) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe, datei);
	}

	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param filename
	 */
	public Bild(int neuesX, int neuesY, int neueBreite, int neueHoehe,
			String filename) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe, filename);
	}

	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param datei
	 */
	public Bild(int neuesX, int neuesY, int neueBreite, int neueHoehe,
			BilddateiAbstrakt datei) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe, datei);
	}

	/**
	 * 
	 * @param behaelter
	 * @param filename
	 */
	public Bild(IContainer behaelter, String filename) {
		this(behaelter, 0, 0, 100, 100, filename);
	}

	/**
	 * 
	 * @param behaelter
	 * @param datei
	 */
	public Bild(IContainer behaelter, BilddateiAbstrakt datei) {
		this(behaelter, 0, 0, 100, 100, datei);
	}

	/**
	 * 
	 * @param behaelter
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param filename
	 */
	public Bild(IContainer behaelter, int neuesX, int neuesY, int neueBreite,
			int neueHoehe, String filename) {
		bild = setzeBilddatei(filename);
		obj = new CBild(bild);
		behaelter.add(obj, 0);
		breite = neueBreite;
		hoehe = neueHoehe;

		if (bild != null) {
			setzeDimensionen(neuesX, neuesY, bild.getWidth(), bild.getHeight());
		} else {
			setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		}

		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);

		behaelter.validate();
	}

	/**
	 * 
	 * @param behaelter
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param datei
	 */
	public Bild(IContainer behaelter, int neuesX, int neuesY, int neueBreite,
			int neueHoehe, BilddateiAbstrakt datei) {
		bild = datei.leseBild();
		obj = new CBild(bild);
		behaelter.add(obj, 0);
		breite = neueBreite;
		hoehe = neueHoehe;

		if (bild != null) {
			setzeDimensionen(neuesX, neuesY, bild.getWidth(), bild.getHeight());
		} else {
			setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		}

		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);

		behaelter.validate();
	}

	public int leseBildBreite() {
		if (bild == null)
			return 0;
		return bild.getWidth();
	}

	public int leseBildHoehe() {
		if (bild == null)
			return 0;
		return bild.getHeight();
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

	static int lesen = 0;

	/*
	 * Waehle das Bild aus!
	 */
	private BufferedImage setzeBilddatei(String filename) {
		BufferedImage bBild;
		try {

			// Aus jar-Datei lesen
			InputStream stream = Bild.class.getResourceAsStream("/" + filename);
			if (stream != null) {
				// lesen aus jar
				bBild = ImageIO.read(stream);
			} else {
				bBild = ImageIO.read(new File(filename));
			}
		} catch (IOException e) {
			// javax.swing.JOptionPane.showMessageDialog(null,"Das Bild ist nicht vorhanden");
			bBild = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_BINARY);

			Graphics2D g2 = bBild.createGraphics();
			g2.drawString("Kein Bild !", 20, 50);
		}

		return bBild;
	}

	/*
	 * Bild wird in das Fenster eingepasst
	 */
	public void einpassen() {
		zoomen = true;

		double faktor = 1;

		if (bild != null) {
			faktor = (breite * 1.0) / (bild.getWidth() * 1.0);

			double faktor2 = (hoehe * 1.0) / (bild.getHeight() * 1.0);

			if (faktor2 < faktor) {
				faktor = faktor2;
			}
		} else {
			faktor = 1;
		}

		obj.setzeAnpassungsfaktor(faktor);
	}

	/*
	 * Keine Anpassung des Bilds
	 */
	public void normal() {
		zoomen = false;
		obj.setzeAnpassungsfaktor(1);
	}

	/**
	 * zeigt Bild vollstaendig an
	 */
	public void expandieren() {
		setzeDimensionen(xPos, yPos, bild.getWidth(), bild.getHeight());
	}

	/*
	 * @param String filename Der Name der gewuenschten BilddateiAbstrakt
	 */
	public void wechsleBild(String filename) {
		bild = setzeBilddatei(filename);

		if (bild != null) {
			if (zoomen) {
				setzeDimensionen(xPos, yPos, breite, hoehe);
			} else {
				setzeDimensionen(xPos, yPos, bild.getWidth(), bild.getHeight());
			}
		}

		obj.wechsleBild(bild);
	}

	/*
	 * @param String filename Der Name der gewuenschten BilddateiAbstrakt
	 */
	public void wechsleBild(BilddateiAbstrakt datei) {
		bild = datei.leseBild();

		if (bild != null) {
			if (zoomen) {
				setzeDimensionen(xPos, yPos, breite, hoehe);
			} else {
				setzeDimensionen(xPos, yPos, bild.getWidth(), bild.getHeight());
			}
		}

		obj.wechsleBild(bild);
	}

	public void sichtbarMachen() {
		sichtbar = true;
		obj.sichtbarMachen();
	}

	/**
	 * Mache unsichtbar. Wenn es bereits unsichtbar ist, tue nichts.
	 */
	public void unsichtbarMachen() {
		sichtbar = false;
		obj.unsichtbarMachen();
	}

	/**
	 * Bewege horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void nachRechtsBewegen() {
		horizontalBewegen(20);
	}

	/**
	 * Bewege einige Bildschirmpunkte nach links.
	 */
	public void nachLinksBewegen() {
		horizontalBewegen(-20);
	}

	/**
	 * Bewege einige Bildschirmpunkte nach oben.
	 */
	public void nachObenBewegen() {
		vertikalBewegen(-20);
	}

	/**
	 * Bewege einige Bildschirmpunkte nach unten.
	 */
	public void nachUntenBewegen() {
		vertikalBewegen(20);
	}

	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamVertikalBewegen(int entfernung) {
		int delta;

		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}

		for (int i = 0; i < entfernung; i++) {
			vertikalBewegen(delta);
			StaticTools.warte(10);
		}
	}

	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamHorizontalBewegen(int entfernung) {
		int delta;

		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}

		for (int i = 0; i < entfernung; i++) {
			horizontalBewegen(delta);
			StaticTools.warte(10);
		}
	}

	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;

		if (zoomen) {
			einpassen();
		}

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

		if (zoomen) {
			einpassen();
		}

		obj.setzeDimensionen(xPos, yPos, breite, hoehe);
	}

	/**
	 * Bewege horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void horizontalBewegen(int entfernung) {
		xPos += entfernung;
		obj.setzePosition(xPos, yPos);
	}

	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void vertikalBewegen(int entfernung) {
		yPos += entfernung;
		obj.setzePosition(xPos, yPos);
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
	
}

@SuppressWarnings("serial")
class CBild extends BasisComponente {
	private BufferedImage bild;
	private Image bildM;
	private double anpassung = 1; // Faktor zum Einpassen an das

	// Fenstert

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CBild(BufferedImage bild) {
		this.bild = bild;
		this.bildM = bild;
	}

	public void wechsleBild(BufferedImage bild) {
		this.bild = bild;
		setzeZoomfaktor(zoomFaktor);
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	@Override
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Graphik-Abmessungen
		breite = getSize().width;
		hoehe = getSize().height;

		g2.drawImage(bildM, 0, 0, null);
	}

	/*
	 * Anpassung fuer Einfuegen in das Fenster
	 */
	public void setzeAnpassungsfaktor(double zf) {
		anpassung = zf;
		setzeZoomfaktor(zoomFaktor);
	}

	@Override
	public double setzeZoomfaktor(double zf) {
		zoomFaktor = zf;
		bzf = ((IContainer) this.getParent()).getBehaelterZoom();
		bildM = bild.getScaledInstance((int) (bild.getWidth() * zoomFaktor
				* bzf * anpassung),
				(int) (bild.getHeight() * zoomFaktor * bzf * anpassung),
				Image.SCALE_SMOOTH);
		zoomen();

		if (sichtbar) {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, 0, 0);
		}

		return zoomFaktor;
	}
}
