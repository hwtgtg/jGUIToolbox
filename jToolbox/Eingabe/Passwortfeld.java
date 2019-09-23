
//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

/**
 * <h1>Passwortfeld</h1 kapselt die Klasse JPasswordField.<br/>
 * <br/>
 * 
 * setzePasswortfeld() schaltet Ausgabe mit Zeichen '*' ein
 * setzePasswortfeld(char ausgabezeichen) setzt gleichzeitig ein anderes
 * Ausgabezeichen setzeTextfeld() schaltet auf Normalausgabe um
 * 
 * Ist der Kommunikationslink gesetzt, meldet das Objekt ein <b><i>ENTER</i></b>
 * im Textfeld.<br/>
 * <br/>
 * 
 * Der Inhalt des Textfelds kann mit leseText() gelesen werden <br/>
 * <br/>
 * 
 * Die Methoden leseXXX( def ) wandeln dem Inhalt des Textfelds in die
 * entsprechenden Zahlentypen um.<br/>
 * Def gibt den Wert an, der im Fehlerfall zurueckgegeben wird.<br/>
 * <hr>
 * 
 * @author Hans Witt
 *
 */
public class Passwortfeld implements IComponente {
	private CPasswortfeld obj;
	public int breite = 0;
	public int hoehe = 0;
	public int xPos = 0;
	public int yPos = 0;
	public String anzeigeText = "Eingabe...";
	public int fontGroesse = -1;
	public String schriftFarbe = "schwarz";
	public String hintergrundFarbe = "weiss";

	public static final int LINKSBUENDIG = 0;
	public static final int ZENTRIERT = 1;
	public static final int RECHTSBUENDIG = 2;

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public Passwortfeld() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Passwortfeld(String neuerText, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, 0, 0, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuerText
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Passwortfeld(String neuerText, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
				neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Passwortfeld(IContainer behaelter) {
		this(behaelter, "Eingabe...", 0, 0, 150, 80);
	}

	public Passwortfeld(IContainer behaelter, String neuerText, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		anzeigeText = neuerText;
		obj = new CPasswortfeld(anzeigeText);
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
	 * 
	 */
	public void setzeLink(ITuWas linkObj, int ID) {
		obj.setzeLink(linkObj, ID);
	}

	public void setEditable() {
		obj.setEditable();
	}

	public void setReadonly() {
		obj.setReadonly();
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
		obj.setzeDimensionen(xPos, yPos, breite, hoehe);
	}

	public void setzeAusgabetext(String neuerText) {
		anzeigeText = neuerText;
		obj.linksbuendig();
		obj.setText(neuerText);
	}

	public void setzeTextRechtsbuendig(String neuerText) {
		anzeigeText = neuerText;
		obj.rechtsbuendig();
		obj.setText(neuerText);
	}

	public String leseText() {
		anzeigeText = obj.getText();

		return anzeigeText;
	}

	@Override
	public String toString() {
		return leseText();
	}

	/**
	 * Zeichen als * ausgeben
	 */
	public void setzePasswortfeld() {
		obj.setPasswordField();
	}

	/**
	 * Zeichen als >>ausgabezeichen<< ausgeben
	 */
	public void setzePasswortfeld(char ausgabezeichen) {
		obj.setPasswordField(ausgabezeichen);
	}

	/**
	 * Zeichen normal ausgeben
	 */
	public void setzeTextfeld() {
		obj.setTextfield();
	}

	public void setzeAusrichtung(int ausrichtung) {
		switch (ausrichtung) {
		case Ausgabe.LINKSBUENDIG:
			obj.linksbuendig();
			break;
		case Ausgabe.ZENTRIERT:
			obj.zentrieren();
			break;
		case Ausgabe.RECHTSBUENDIG:
			obj.rechtsbuendig();
			break;
		default:
			obj.linksbuendig();
		}
	}

	public void zentrieren() {
		obj.zentrieren();
	}

	public void setzeInteger(int zahl) {
		obj.rechtsbuendig();
		obj.setText("" + zahl + " ");
	}

	public int leseInteger(int def) {
		int value = 0;
		String neu = obj.getText();
		neu = neu.trim();

		try {
			value = Integer.parseInt(neu);
		} catch (Exception e) {
			value = def;
		}

		setzeInteger(value);

		return value;
	}

	public int leseIntegerGerundet(int def) {
		int value = 0;
		value = (int) Math.round(leseDouble(def));
		setzeInteger(value);

		return value;
	}

	public void setzeDouble(double zahl) {
		obj.rechtsbuendig();
		obj.setText("" + zahl + " ");
	}

	public double leseDouble(double def) {
		double value = 0;
		String neu = obj.getText().replace(',', '.');

		try {
			value = Double.parseDouble(neu);
		} catch (Exception e) {
			value = def;
		}

		setzeDouble(value);

		return value;
	}

	/**
	 * Lese Wert als longFeld ( Fuer Klasse Dezimal )
	 * 
	 * erg[0] : Ziffernfolge erg[1] : Nachkommastellen
	 * 
	 * @param longFeld
	 * @return longFeld
	 */
	public long[] leseLongFeld(long[] longFeld) {
		long[] erg = new long[2];
		erg[0] = longFeld[0]; // Ziffernfolge
		erg[1] = longFeld[1]; // Nachkommastellen

		boolean fehler = false;
		String neu = obj.getText().replace(',', '.');
		neu = neu.trim(); // Leerzeichen am Anfang uind Ende entfernt

		long nk = -1;
		long zf = 0;
		long vz = 1;

		for (int i = 0; !fehler && (i < neu.length()); i++) {
			char c = neu.charAt(i);

			if ((i == 0) && (c == '-')) {
				vz = -1;
			} else if ((i == 0) && (c == '+')) {
				vz = +1;
			} else if (Character.isDigit(c)) {
				zf = (zf * 10) + Character.digit(c, 10);

				if (nk > -1) {
					nk++;
				}
			} else if (c == '.') {
				if (nk == -1) {
					nk++;
				} else {
					fehler = true; // zweiter Punkt!!
				}
			} else {
				fehler = true;
			}
		}

		if (!fehler) {
			erg[0] = vz * zf;

			if (nk == -1) {
				nk = 0;
			}

			erg[1] = nk;
		}

		setzeLongFeld(erg);

		return erg;
	}

	/**
	 * Defaultwert 0
	 * 
	 * @return long-Wert umgewandelt
	 */
	public long[] leseLongFeld() {
		long[] feld = new long[2];
		feld[0] = 0;
		feld[1] = 0;

		return leseLongFeld(feld);
	}

	public void setzeLongFeld(long[] longFeld) {
		obj.rechtsbuendig();

		String text = "" + longFeld[0];
		int i = (int) longFeld[1];

		if (i > 0) {
			int ln = text.length() - (int) longFeld[1];

			if (ln > 0) {
				text = text.substring(0, text.length() - (int) longFeld[1])
						+ ","
						+ text.substring(text.length() - (int) longFeld[1]);
			} else {
				ln = -ln;
				text = "0,"
						+ "00000000000000000000000000000000000000000000"
								.substring(0, ln) + text;
			}
		}

		obj.setText("" + text + " ");
	}

	public void unsichtbarMachen() {
		obj.unsichtbarMachen();
	}

	public void sichtbarMachen() {
		obj.sichtbarMachen();
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
class CPasswortfeld extends BasisComponente implements ActionListener {
	private JPasswordField eingabe;
	protected Color schriftFarbe = StaticTools.getColor("schwarz");

	/**
	 * Konstruktor fuer Objekte der Klasse Taste
	 */
	public CPasswortfeld(String text) {
		this.setLayout(new BorderLayout());
		eingabe = new JPasswordField(text);
		eingabe.setEchoChar((char) 0);
		setzeBasisfarbe("weiss");
		eingabe.setFont(f);
		eingabe.setHorizontalAlignment(SwingConstants.CENTER);
		eingabe.addActionListener(this);
		eingabe.updateUI();
		this.add(eingabe);
	}

	public void setPasswordField() {
		eingabe.setEchoChar('*');
	}

	public void setPasswordField(char ausgabezeichen) {
		eingabe.setEchoChar(ausgabezeichen);
	}

	public void setTextfield() {
		eingabe.setEchoChar((char) 0);
	}

	public void setEditable() {
		eingabe.setEditable(true);
	}

	public void setReadonly() {
		eingabe.setEditable(false);
	}

	@Override
	public void setzeFontName(String name) {
		super.setzeFontName(name);
		eingabe.setFont(f);
		repaint();
	}

	@Override
	public void setzeSchriftgroesse(float x) {
		setFontsize(x);
		eingabe.setFont(f);
		repaint();
	}

	@Override
	public void setzeSchriftStil(int fontstyle) {
		setFontstyle(fontstyle);
		eingabe.setFont(f);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (linkObj != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					linkObj.tuWas(id);
				}
			}).start();
		}
	}

	public void setText(String txt) {
		eingabe.setText(txt);
	}

	public String getText() {
		return new String(eingabe.getPassword());
	}

	public void zentrieren() {
		eingabe.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void linksbuendig() {
		eingabe.setHorizontalAlignment(SwingConstants.LEFT);
	}

	public void rechtsbuendig() {
		eingabe.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public void setzeSchriftfarbe(String farbname) {
		schriftFarbe = StaticTools.getColor(farbname);
		eingabe.setForeground(schriftFarbe);
		repaint();
	}

	public void setzeHintergrundfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		eingabe.setBackground(farbe);
		repaint();
	}

	@Override
	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun
	}
}
