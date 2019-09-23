//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;


public class Pumpe_Pleuel implements IComponente {
    private CPleuelB obj;
    protected int x1 = 0;
    protected int y1 = 0;
    protected int x2 = 100;
    protected int y2 = 50;
    protected boolean sichtbar = true;
    protected String farbe = StaticTools.leseNormalfarbe();
    protected int breite = 4;

    /**
     * Konstruktor fuer Hauptfenster
     */
    public Pumpe_Pleuel() {
        this(Zeichnung.gibZeichenflaeche());
    }

    /**
     * Konstruktor fuer Hauptfenster
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public Pumpe_Pleuel(int x1, int y1, int x2, int y2) {
        this(Zeichnung.gibZeichenflaeche(), x1, y1, x2, y2);
    }

    /**
     * Konstruktor
     *
     * @param behaelter
     */
    public Pumpe_Pleuel(IContainer behaelter) {
        this(behaelter, 0, 0, 100, 50);
    }

    /**
     * allgemeiner Konstruktor
     *
     * @param behaelter
     * @param x2
     * @param y2
     */
    public Pumpe_Pleuel(IContainer behaelter, int x1, int y1, int x2, int y2) {
        obj = new CPleuelB();
        behaelter.add(obj, 0);
        setzeEndpunkte(x1, y1, x2, y2);
        behaelter.validate();
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
     *
     * @param neuesX1
     * @param neuesY1
     * @param neuesX2
     * @param neuesY2
     */
    public void setzeEndpunkte(int neuesX1, int neuesY1, int neuesX2,
        int neuesY2) {
        x1 = neuesX1;
        y1 = neuesY1;
        x2 = neuesX2;
        y2 = neuesY2;
        obj.setzeEndpunkte(x1, y1, x2, y2);
    }

    // Methode noetig zum Hinzufuegen mit Anpassung beim Behaelter
    // Die Enden werden relativ zur aktuellen position verschoben
    public void verschieben(int dx, int dy) {
        setzeEndpunkte(x1 + dx, y1 + dy, x2 + dx, y2 + dy);
    }

    /*
     * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
     * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
     */
    public void setzeFarbe(String neueFarbe) {
        farbe = neueFarbe;
        obj.setzeBasisfarbe(neueFarbe);
    }

    /**
     * Liniendicke setzen
     *
     * @param neueDicke
     */
    public void setzeBreite(int neueBreite) {
        breite = neueBreite;
        obj.setzeBreite(breite);
    }

    public static void main(String[] args) {
        Pumpe_Pleuel a = new Pumpe_Pleuel(100, 300, 400, 500);
        a.setzeBreite(50);
        a.setzeFarbe("gelb");
        // a.obj.rand();
        Zeichnung.setzeRasterEin();
    }

    public BasisComponente getBasisComponente() {
        return obj;
    }
}


@SuppressWarnings("serial")
class CPleuelB extends BasisComponente {
    private int originalArmBreite = 50;
    private int armBreite = 50;
    int[] xpoints = new int[4];
    int[] ypoints = new int[4];
    int startX = 10;
    int startY = 10;
    int endX = 100;
    int endY = 100;
    int originalX1;
    int originalY1;
    int originalX2;
    int originalY2;
    int pX1;
    int pY1;
    int pX2;
    int pY2;

    /**
     * Konstruktor ohne Beschriftung
     */
    public CPleuelB() {
    }

    /**
     * Die Darstellung der Komponente wird hier programmiert.
     */
    @Override
	public void paintComponentSpezial(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // Graphik-Abmessungen
        breite = getSize().width;
        hoehe = getSize().height;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER));
        g2.setColor(farbe);

        if (gefuellt) {
            g2.fillPolygon(new Polygon(xpoints, ypoints, 4));
        } else {
            g2.drawPolygon(new Polygon(xpoints, ypoints, 4));
        }

        g2.setColor(Color.ORANGE);
        g2.fillOval(startX - (armBreite / 3), startY - (armBreite / 3),
            (armBreite * 2) / 3, (armBreite * 2) / 3);
        g2.fillOval(endX - (armBreite / 3), endY - (armBreite / 3),
            (armBreite * 2) / 3, (armBreite * 2) / 3);

        g2.setColor(Color.red);
        g2.drawLine(startX, startY, endX, endY);
    }

    @Override
	protected void zoomen() {
        armBreite = (int) Math.round(originalArmBreite * zoomFaktor * bzf);
        pX1 = (int) Math.round((originalX1 + originalVX) * zoomFaktor * bzf);
        pY1 = (int) Math.round((originalY1 + originalVY) * zoomFaktor * bzf);
        pX2 = (int) Math.round((originalX2 + originalVX) * zoomFaktor * bzf);
        pY2 = (int) Math.round((originalY2 + originalVY) * zoomFaktor * bzf);

        if (pX1 < pX2) {
            xPos = pX1 - armBreite;
            breite = pX2 - pX1 + (2 * armBreite);
        } else {
            xPos = pX2 - armBreite;
            breite = pX1 - pX2 + (2 * armBreite);
        }

        if (pY1 < pY2) {
            yPos = pY1 - armBreite;
            hoehe = pY2 - pY1 + (2 * armBreite);
        } else {
            hoehe = pY1 - pY2 + (2 * armBreite);
            yPos = pY2 - armBreite;
        }

        startX = pX1 - xPos;
        startY = pY1 - yPos;
        endX = pX2 - xPos;
        endY = pY2 - yPos;
        setzeKoordinaten();
    }

    public void setzeEndpunkte(int neuesX1, int neuesY1, int neuesX2,
        int neuesY2) {
        originalX1 = neuesX1;
        originalY1 = neuesY1;
        originalX2 = neuesX2;
        originalY2 = neuesY2;
        zoomen();
        super.setzeDimensionen(xPos, yPos, breite, hoehe);
    }

    public void setzeBreite(int neueBreite) {
        originalArmBreite = neueBreite;
        zoomen();
        super.setzeDimensionen(xPos, yPos, breite, hoehe);
    }

    public void setzeKoordinaten() {
        int dx = endX - startX;
        int dy = endY - startY;

        double f = ((double) 0.5 * armBreite) / (Math.sqrt((dx * dx) +
                (dy * dy)));

        double bx = dy * f;
        double by = dx * f;

        xpoints[0] = startX + (int) (-bx - by);
        ypoints[0] = startY + (int) (+by - bx);

        xpoints[1] = endX + (int) (-bx + by);
        ypoints[1] = endY + (int) (+by + bx);

        xpoints[2] = endX + (int) (+bx + by);
        ypoints[2] = endY + (int) (-by + bx);

        xpoints[3] = startX + (int) (+bx - by);
        ypoints[3] = startY + (int) (-by - bx);
    }
}
