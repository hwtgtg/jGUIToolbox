//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * @author Witt
 *
 */
public class MemmoryKarte extends MausBehaelter {
    Bild bild;
    Bilddatei rueckseite;
    Bilddatei vorderseite;
    boolean vorne;

    /**
     * Konstruktor
     */
    public MemmoryKarte(int x, int y, int Breite, int Hoehe) {
        super(x, y, Breite, Hoehe);
        super.setzeHintergrundfarbe("orange");
        // Mausereignisse von Mausbehaelter landen hier
        super.setzeMausereignisse((1 << PRESS));
        this.setzeMitRand(true);
    }

    void setzeBilddatei(Bilddatei datei) {
        if (bild == null) {
            bild = new Bild(this, 10, 10, breite - 20, hoehe - 20, datei);
        } else {
            bild.wechsleBild(datei);
        }

        bild.einpassen();
    }

    public void setzeBilder(Bilddatei vorderseite, Bilddatei rueckseite) {
        this.rueckseite = rueckseite;
        this.vorderseite = vorderseite;
        setzeBilddatei(rueckseite);
        vorne = false;
    }

    public void setzeRueckseite() {
        vorne = false;
        setzeBilddatei(rueckseite);
    }

    public void setzeVorderseite() {
        vorne = true;
        setzeBilddatei(vorderseite);
    }

    public static void main(String[] args) {
        Bilddatei b1 = new Bilddatei("img/blaubeere.gif");
        Bilddatei b2 = new Bilddatei("img/planets.gif");
        MemmoryKarte k = new MemmoryKarte(10, 10, 150, 150);
        k.setzeBilder(b1, b2);

        k.setzeVorderseite();
        StaticTools.warte(1000);
        k.setzeRueckseite();
    }
}
