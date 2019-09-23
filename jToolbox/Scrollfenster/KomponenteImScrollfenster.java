
//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 * KomponenteImScrollfenster kapselt JScrollPane.<br />
 * <br />
 * Der Komponente wird beim Erzeugen ein GUI-Element uebergeben, z.B. ein
 * Behaelter/Bild ....<br />
 * <hr>
 * 
 * @author Hans Witt
 * 
 *         Version: 1 (26.2.2009)
 * 
 * @version 5.0: (4.9.2010 Entfernen fuer Graphikkomponente eingefuehrt
 * 
 *          Destruktor entfernt Graphikkomponente automatisch bei gc()
 */
public class KomponenteImScrollfenster implements IComponente {

	protected CKomponenteImScrollfenster obj;
	public int breite = 100;
	public int hoehe = 100;
	public int xPos = 0;
	public int yPos = 0;

	public boolean sichtbar = true;

	public double zoomInhalt = 1;

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public KomponenteImScrollfenster(IComponente innen) {
		this(Zeichnung.gibZeichenflaeche(), innen);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public KomponenteImScrollfenster(IComponente innen, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), innen, 0, 0, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public KomponenteImScrollfenster(IComponente innen, int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), innen, neuesX, neuesY, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public KomponenteImScrollfenster(IContainer behaelter, IComponente innen) {
		this(behaelter, innen, 0, 0, 100, 100);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public KomponenteImScrollfenster(IContainer behaelter, IComponente innen, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		innen.getBasisComponente().setzePosition(0, 0);
		innen.getBasisComponente().inViewport=true ;
		innen.getBasisComponente().ausContainerEntfernen();
		obj = new CKomponenteImScrollfenster(innen.getBasisComponente());
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}

	public void setzeZoomfaktor(double zf) {
		zoomInhalt = obj.setzeZoomfaktor(zf);
	}

	public double getBehaelterZoom() {
		return obj.getBehaelterZoom();
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
	 * Groesse des Anzeigefelds aendern *
	 */
	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeGroesse((int) (breite / zoomInhalt), (int) (hoehe / zoomInhalt));
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
		obj.setzePosition((int) (xPos / zoomInhalt), (int) (yPos / zoomInhalt));
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
		obj.setzeDimensionen((int) (xPos / zoomInhalt), (int) (yPos / zoomInhalt), (int) (breite / zoomInhalt),
				(int) (hoehe / zoomInhalt));
	}
	
	public void setzeScrollbarDimension(int breite) {
		obj.setzeScrollbarDimension(breite);
	}

	/**
	 * Mache sichtbar.
	 */
	public void sichtbarMachen() {
		sichtbar = true;
		obj.sichtbarMachen();
	}

	/**
	 * Mache unsichtbar.
	 */
	public void unsichtbarMachen() {
		sichtbar = false;
		obj.unsichtbarMachen();
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
class CKomponenteImScrollfenster extends BasisComponente implements IContainer {

	JScrollPane scrollPane;
	BasisComponente innen = null; // Komponente im Scrollfenster

	/**
	 * liefert den Zoomfaktor fuer den Behaelter
	 * 
	 * @return
	 */
	public double getBehaelterZoom() {
		return zoomFaktor * ((IContainer) this.getParent()).getBehaelterZoom();
	}

	@Override
	public double setzeZoomfaktor(double zf) {
		zoomFaktor = zf;
		zoomen();
		if (innen != null) {
			innen.setzeZoomfaktor(zf);
		}
		if (sichtbar) {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this, xPos, yPos, breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this, xPos, yPos, 0, 0);
		}
		return zoomFaktor;
	}

	public CKomponenteImScrollfenster(BasisComponente innen) {
		this.setLayout(new BorderLayout());
		this.innen = innen;
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane, BorderLayout.CENTER);
		innen.setPreferredSize(new Dimension(innen.breite, innen.hoehe));
		scrollPane.setViewportView(innen);
	}

	public void setzeScrollbarDimension(int breite) {
		// Get the scroll-bar and make it a bit wider.
		JScrollBar sb = scrollPane.getVerticalScrollBar();
		sb.setPreferredSize(new Dimension(breite, 0));
		sb = scrollPane.getHorizontalScrollBar();
		sb.setPreferredSize(new Dimension(0,breite));
	}

	public void setzeSichtbarkeit(boolean sichtbar) {
		this.getParent().setVisible(sichtbar);
	}

	// Wird von der Graphikkomponente zum Positionieren im Behaelter aufgerufen
	// position sollte ignoriert werden. Nur Beite und Hoehe wichtig
	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y, int width, int height) {
		obj.setBounds(x, y, width, height);
		obj.setPreferredSize(new Dimension(width, height));
		repaint();
		validate();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		obj.setSize(width, height);
		obj.setPreferredSize(new Dimension(width, height));
		obj.repaint();
		repaint();
		validate();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	@Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		obj.setLocation(x, y);
		obj.setPreferredSize(new Dimension(obj.getWidth(), obj.getHeight()));
		obj.repaint();
		repaint();
		validate();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	@Override
	public void paintComponentSpezial(Graphics g) {

	}

}
