
import java.awt.Graphics;
//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * <h1>Bilddatei</h1>.<br />
 * Ein mehrfach benoetigtes Bild wird nur einmal aus der Datei gelesen. Es kann
 * kann an mehrere Bild-Klassen zum Anzeigen uebergeben werden. <br/>
 * Uebergabe im Bild-Konstruktor oder durch die Methode wechsleBild<br />
 *
 * Vorteil: <br>
 * - Ein Bild kann mehreren Bildkomponenten zur Verfuegung gestellt werden.<br>
 * - Methoden wie Einpassen in einen Rahmen geschehen bereits beim Einlesen <br>
 * 
 * @author Hans Witt
 *
 * @version 1 (2.5.09)
 *
 */
public class Bilddatei extends BilddateiAbstrakt {
	static int lesen = 0;
	BufferedImage bBild;
	public boolean zoomen = false;
	public int breite = 100;
	public int hoehe = 100;

	public Bilddatei() {
		erzeugeLeeresBild();
	}

	public Bilddatei(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		erzeugeLeeresBild();
	}

	public Bilddatei(String filename) {
		leseBildDatei(filename);
		breite = bBild.getWidth();
		hoehe = bBild.getHeight();
	}

	public Bilddatei(String filename, int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		leseBildDatei(filename);
		einpassen(breite, hoehe);
	}

	public Bilddatei(Bilddatei bildNeu) {

		bBild = new BufferedImage(bildNeu.bBild.getWidth(), bildNeu.bBild.getHeight(), bildNeu.bBild.getType());
		Graphics g = bBild.getGraphics();
		g.drawImage(bildNeu.bBild, 0, 0, null);
		g.dispose();

	}

	public void einpassen(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;

		double faktor = 1;

		if (bBild != null) {
			faktor = (breite * 1.0) / (bBild.getWidth() * 1.0);

			double faktor2 = (hoehe * 1.0) / (bBild.getHeight() * 1.0);

			if (faktor2 < faktor) {
				faktor = faktor2;
			}
		} else {
			faktor = 1;
		}

		Image bildM = bBild.getScaledInstance((int) (bBild.getWidth() * faktor), (int) (bBild.getHeight() * faktor),
				Image.SCALE_SMOOTH);
		BufferedImage bildTemp = new BufferedImage(breite, hoehe, BufferedImage.TYPE_4BYTE_ABGR);
		bildTemp.createGraphics().drawImage(bildM, 0, 0, null);
		bBild = bildTemp;
	}

	public void erzeugeLeeresBild() {
		bBild = new BufferedImage(breite, hoehe, BufferedImage.TYPE_BYTE_BINARY);

		Graphics2D g2 = bBild.createGraphics();
		g2.drawString("Kein Bild !", 20, 50);
	}

	/*
	 * Waehle das Bild aus!
	 */
	public void leseBildDatei(String filename) {
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
			erzeugeLeeresBild();
		}
	}

	/**
	 * liefert das Bild an eine andere Komponente
	 */
	@Override
	public BufferedImage leseBild() {
		return bBild;
	}

}
