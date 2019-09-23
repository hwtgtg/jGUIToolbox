//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * Landschaft 
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (6.8.2008)
 */

public class Haus {

    private Dreieck dach;
    private Rechteck haus;
    private Rechteck tuer;
    private Rechteck fenster;

    public Haus() {

        dach = new Dreieck(50, 50,200, 100);
        dach.setzeFarbe("rot");

        haus = new Rechteck(50, 150, 200, 150);

        tuer = new Rechteck(170, 190, 50, 100);
        tuer.setzeFarbe("blau");

        fenster = new Rechteck(75, 190, 50, 50);
        fenster.setzeFarbe("gelb");

    }

    public void setzePosition( int x , int y){
        dach.setzePosition(50+x, 50+y);
        haus.setzePosition(50+x, 150+y);
        tuer.setzePosition(170+x, 190+y);
        fenster.setzePosition(75+x, 190+y);
    }

    public static void main(String[] args) {
        new Haus();
        Zeichenflaeche.setzeMitRaster(true);
	}
}
