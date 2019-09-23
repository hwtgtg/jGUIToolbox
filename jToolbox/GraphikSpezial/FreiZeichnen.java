//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * <h1>Muster einer Komponente, in die man direkt zeichnen kann.</h1>
 * Das Zeichnen geschieht in der Methode <b>paintComponentSpezial(Graphics g)</b><br/>
 * Es koennen die Methoden der Klasse Graphics und Graphics2D verwendet werden.<br/>
 * <hr>
 * @author Hans Witt
 * 
 *@version
 * Version 1.1 (14.7.2008)<br />
 * Version: 1.1.1 (17.7.2008) 
 *        Neue Komponenten werden von Unten nach Oben aufgebaut, d.h.vor die alten gesetzt<br />
 * Version: 3 (9.8.2008) 
 *        Containerklasse fuer GUI-Elemente<br />
 * Version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst<br />		
 * Version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt <br />
 * Version 5.0: (4.9.2010
 *  	   Entfernen fuer Graphikkomponente eingefuehrt<br />
 *  
 *   	   Destruktor entfernt Graphikkomponente automatisch bei gc()<br />
 */
@SuppressWarnings("serial")
public class FreiZeichnen extends BasisComponente implements IComponente {
	public int breite = 100;
	public int hoehe = 100;
	public int xPos = 0;
	public int yPos = 0;
	public String strFarbe = StaticTools.leseNormalfarbe();

	/**
	 * Konstruktor fuer Hauptfenster
	 */
	public FreiZeichnen() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public FreiZeichnen(int neueBreite, int neueHoehe) {
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
	public FreiZeichnen(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public FreiZeichnen(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 100);
	}

	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public FreiZeichnen(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		behaelter.add(this, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}

	/**
	 *  Das Interface IComponente fordert eine Methode die eine BasisComponente zurueckliefert.
	 *  Sie wird benoetigt, um ein Objekt zu einem anderen Behaelter hinzuzufuegen
	 */
	@Override
	public BasisComponente getBasisComponente() {
		return this ;
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.<br/>
     * Es koennen die Methoden der Klasse Graphics und Graphics2D verwendet werden.<br/>
	 */
	@Override
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width - 2;
		hoehe = getSize().height - 2;
		g.setColor(farbe);

		int mitteX = breite / 2;
		int mitteY = hoehe / 2;

		// Zeichnet eine Linie zwischen den Koordinaten (x1,y1) und (x2,y2) in
		// der Vordergrundfarbe.
		// void drawLine( int x1, int y1, int x2, int y2 )

		int anzahl = 10;
		int x = 0;
		int y = 0;

		for (int i = 1; i <= anzahl; i++) {
			x = (int) Math.round(breite / 2
					* Math.cos(i * 2 * Math.PI / anzahl));
			y = (int) Math.round(breite / 2
					* Math.sin(i * 2 * Math.PI / anzahl));
			g2.drawLine(mitteX, mitteY, mitteX + x, mitteY - y);
		}
	}

	
	// Methode noetig zum Hinzufuegen mit Anpassung beim Behaelter
	// Die Enden werden relativ zur aktuellen position verschoben
	@Override
	public void verschieben(int dx , int dy ) {
		setzePosition(xPos + dx, yPos + dy );
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(String neueFarbe) {
		strFarbe = neueFarbe;
		super.setzeBasisfarbe(neueFarbe);
	}

    /**
	 * Entfernen des Graphikobjekts
	 */
	public void entfernen() {
			ausContainerEntfernen();
		
	}
	
	/**
	 * Destruktor
	 */
	@Override
	protected void finalize() {
	if (!Zeichnung.verweistesGUIElementEntfernen) return;
		entfernen();
	}


}
