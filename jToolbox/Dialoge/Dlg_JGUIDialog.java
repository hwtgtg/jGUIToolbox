
//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * <h1>Dlg_JGUIDialog</h1> stellt eine Klasse zum Erzeugen eigener Dialoge zur
 * Verfuegung.<br />
 * <br />
 * 
 * Im Konstruktor wird festgelegt, ob der Dialog <b>modal</B> oder <b>nicht
 * modal</b> ist. <br />
 * <br />
 * Ein <b>modaler Dialog</b> blockiert andere Fenster des Programms ! <br />
 * <b>Nicht modale Dialogfenster</b> schweben ueber der Anwendung. <br />
 * <hr />
 * 
 * <b>Erzeugen eines <i>modalen Dialogs</i></b><br />
 * <ul>
 * <li>Erzeugen einer Klasse fuer den Dialog</li>
 * <li>Erzeugen der Dialogkomponente: dialog = new Dlg_JGUIDialog("Dialogtitel",
 * Dlg_JGUIDialog.MODAL );</li>
 * <li>Lesen des Behaelters der Dialogkomponenten: behaelter =
 * dialog.leseContainer(); <br />
 * Der Behaelter wird gebraucht, um dort graphische Komponenten zu platzieren. <br />
 * Beispiel: taste = new Taste(behaelter, "Uebernehmen", 50, 100, 150, 50);</li>
 * <li>Den Elementen, die den Dialog beenden sollen, einen Link auf den Dialog
 * zuweisen: <br />
 * taste.setzeLink(dialog, 99);</li>
 * <li>Groesse des Dialogfensters einstellen: dialog.setSize(400, 300);</li>
 * <li>Eine Methode zum Sichtbarmachen des Dialogs erstellen:
 * 
 * <pre>
 * public void setzeSichtbar() {
 * 	// Dialog anzeigen
 * 	dialog.setVisible(true);
 * }
 * </pre>
 * 
 * Den Aufruf dialog.setVisible(true) kann man auch am Ende des Konstruktors
 * setzen. <br/>
 * Schreibt man eine eigene Methode, kann man den Dialog mehrfach aufrufen.</li>
 * <li>mit leseCloseID() kann man die ID des Elements erfahren, das den Dialog
 * geschlossen hat.<br />
 * leseCloseID() setzt die ID auf -1 zurueck.</li>
 * </ul>
 * <hr />
 * 
 * <b>Erzeugen eines <i>Nicht modalen Dialogs</i></b><br />
 * <ul>
 * <li>Erzeugen einer Klasse fuer den Dialog</li>
 * <li>Erzeugen der Dialogkomponente: dialog = new Dlg_JGUIDialog("Dialogtitel",
 * Dlg_JGUIDialog.NICHTMODAL );</li>
 * <li>Lesen des Behaelters der Dialogkomponenten: behaelter =
 * dialog.leseContainer(); <br />
 * Der Behaelter wird gebraucht, um dort graphische Komponenten zu platzieren. <br />
 * Beispiel: taste = new Taste(behaelter, "Action", 50, 100, 150, 50);</li>
 * <li>Den Elementen, die den Dialog beenden sollen, einen Link auf den Dialog
 * zuweisen: <br />
 * taste.setzeLink(dialog, 99); <br />
 * Beim Nicht modalen Dialog wir das nicht gebraucht. Das Dialogfenster kann man
 * auch ueber das Schliesskreuz verbergen.</li>
 * <li>Groesse des Dialogfensters einstellen: dialog.setSize(400, 300);</li>
 * <li>Eine Methode zum Sichtbarmachen des Dialogs erstellen:
 * 
 * <pre>
 * public void setzeSichtbar() {
 * 	// Dialog anzeigen
 * 	dialog.setVisible(true);
 * }
 * </pre>
 * 
 * Den Aufruf dialog.setVisible(true) kann man auch am Ende des Konstruktors
 * setzen. <br/>
 * Schreibt man eine eigene Methode, kann man den Dialog mehrfach aufrufen.</li>
 * <li>mit leseCloseID() kann man die ID des Elements erfahren, das den Dialog
 * geschlossen hat.<br />
 * leseCloseID() setzt die ID auf -1 zurueck.</li>
 * </ul>
 * Bei nicht modalen Dialogen besteht das Problem der Informationsuebergabe. Das
 * Hauptprogramm erfaehrt nichts ueber Aktionen beim Dialog.<br />
 * Es gibt prinzipiell zwei Moeglichkeiten:
 * <ul>
 * <li>Das Hauptprogramm greift direkt auf die Komponenten des Dialogs zu.</li>
 * <li>Den Dialogkomponenten wird als Link ein Objekt des Hauptprogramms
 * zugeordnet. <br />
 * Das Objekt des Dialogs infomiert dann selbst das Hauptprogramm ueber die
 * tuWas(..)-Methode</li>
 * <li></li>
 * </ul>
 * 
 * <hr/>
 * <b>Hinweise:</b> <br />
 * Dialoge werden verborgen, nicht geschlossen! Sie koennen mit setzeSichtbar()
 * erneut aktiviert werden.<br />
 * 
 * Einem Dialogelement wie z.B. einem Schieberegler kann man einen Link auf ein
 * Objekt des Hauptprogramms zuweisen. Eine Aenderung beeinflusst dann
 * unmittelbar das Hauptprogramm.<br />
 * Ein Dialogprogramm kann wie jede andere Klasse, die ITuWas implementiert,
 * selbst Ziel von Aktionen sein. <br />
 * Der Status der Elemente ist unabhaengig von der Sichtbarkeit der Elemente.
 * <hr />
 * 
 * @author Hans Witt
 * 
 */
@SuppressWarnings("serial")
public class Dlg_JGUIDialog extends JDialog implements ITuWas {
	// Konstanten
	public final static boolean MODAL = true;
	public final static boolean NICHTMODAL = false;
	public int breite = 300;
	public int hoehe = 500;
	private D_Zeichenflaeche anzeige;
	@SuppressWarnings("unused")
	private boolean modal = false;
	ITuWas linkObj;
	private int closeID = -1; // CloseID
	int id = 0; // ID der Komponente fuer Callback

	/**
	 * 
	 * @param titel
	 *            Titel des Dialogfensters
	 * @param modal
	 *            Dlg_JGUIDialog.NICHTMODAL oder Dlg_JGUIDialog.MODAL
	 */
	public Dlg_JGUIDialog(String titel, boolean modal) {
		super(Zeichnung.gibJFrame(), titel, modal);
		this.modal = modal;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				fensterSchliessen();
			}
		});

		// Groesse nach Behaelter setzen

		Container cp = getContentPane();
		// cp.setLayout(null);
		anzeige = new D_Zeichenflaeche();
		cp.add(anzeige);

		zentrieren();
		setResizable(false);
	}

	public void zentrieren() {
		// Groesse nach Behaelter setzen
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);

	}

	public void setzeSichtbar(boolean sichtbar) {
		if (sichtbar) {
			zentrieren();
		}
		this.setVisible(sichtbar);
	}

	public void setzeResizable(boolean groesseAenderbar) {
		this.setResizable(groesseAenderbar);
	}
	
	@Override
	public void setSize(int neueBreite , int neueHoehe) {
		breite = neueBreite ;
		hoehe = neueHoehe ;
		super.setSize(breite, hoehe);
	}
	
	

	/**
	 * Der Behaelter wird gebraucht, um dort graphische Komponenten zu
	 * platzieren. <br />
	 * Beispiel: taste = new Taste(behaelter, "Action", 50, 100, 150, 50);
	 * 
	 * @return Behaelter-Objekt des Dialogs
	 */
	public IContainer leseContainer() {
		return anzeige;
	}

	public void expand() {
		anzeige.expand();
	}

	protected void fensterSchliessen() {
		setVisible(false); // Dialoge werden unsichtbar
	}

	/**
	 * Der Dialog muss zum Signalisieren eine eindeutige ID haben
	 * 
	 * @param ID
	 */
	public void setzeID(int ID) {
		id = ID;
	}

	/**
	 * Der Dialog braucht zum Signalisieren eine Objekt, dem es signalisieren
	 * kann
	 * 
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj) {
		this.linkObj = linkObj;
	}

	public void setzeLink(ITuWas linkObj, int ID) {
		this.linkObj = linkObj;
		id = ID;
	}

	/**
	 * Komponenten innerhalb des Dialogs signalisieren dem Dialog das Schliessen
	 */
	@Override
	public void tuWas(int ID) {
		closeID = ID;
		fensterSchliessen();

		if (linkObj != null) {
			new Thread(new Runnable() {
				public void run() {
					linkObj.tuWas(id);
				}
			}).start();
		}
	}

	public int leseCloseID() {
		int tmp = closeID;
		resetCloseID();
		return tmp;
	}

	public void resetCloseID() {
		closeID = -1;
	}

}

/**
 * Die Zeichenflaeche des Dialogs. Die Zeichenflaeche ist ein Behaelter
 * 
 * @author Witt
 * 
 */
@SuppressWarnings("serial")
class D_Zeichenflaeche extends JPanel implements IContainer {
	public static boolean mitRaster = false;
	public static int deltaX = 100;
	public static int deltaY = 100;

	public D_Zeichenflaeche() {
		this.setLayout(null);
	}

	public void expand() {
		this.setLayout(new BorderLayout());
	}

	public static void setzeMitRaster(boolean mitRaster) {
		Zeichenflaeche.mitRaster = mitRaster;
		// Zeichnung.gibJFrame().repaint();
		Zeichnung.gibZeichenflaeche().repaint();
	}

	public static void setzeDeltaX(int deltaX) {
		Zeichenflaeche.deltaX = deltaX;
	}

	public static void setzeDeltaY(int deltaY) {
		Zeichenflaeche.deltaY = deltaY;
	}

	public void setzeSichtbarkeit(boolean sichtbar) {
		this.getParent().setVisible(sichtbar);
	}

	@Override
	public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
			int width, int height) {
		obj.setBounds(x, y, width, height);
		repaint();
		validate();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
		obj.setSize(width, height);
		obj.repaint();
		repaint();
		validate();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	@Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
		obj.setLocation(x, y);
		obj.repaint();
		repaint();
		validate();
		// Zeichnung.gibJFrame().validate();
		Zeichnung.gibZeichenflaeche().validate();
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	public void paintComponentSpezial(Graphics g) {
		if (mitRaster) {
			Graphics2D g2 = (Graphics2D) g;

			// Graphik-Abmessungen
			int breite = getSize().width - 1;
			int hoehe = getSize().height - 1;
			Color farbe = StaticTools.getColor("schwarz");
			g.setColor(farbe);

			int hor = deltaX;

			while (hor < breite) {
				g2.drawLine(hor, 0, hor, hoehe);
				hor += deltaX;
			}

			int ver = deltaY;

			while (ver < hoehe) {
				g2.drawLine(0, ver, breite, ver);
				ver += deltaY;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintComponentSpezial(g);
	}

	public double getBehaelterZoom() {
		return 1;
	}

	@Override
	public double setzeZoomfaktor(double zf) {
		return 1;
	}

}
