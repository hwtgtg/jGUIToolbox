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

public class BehaelterHaus {

    public Behaelter beh_haus ;
    private Dreieck dach;
    private Rechteck haus;
    private Rechteck tuer;
    private Rechteck fenster;

    public BehaelterHaus() {

        beh_haus = new Behaelter(300,300);
        dach = new Dreieck(beh_haus, 50, 50,200, 100);
        dach.setzeFarbe("rot");

        haus = new Rechteck(beh_haus, 50, 150, 200, 150);

        tuer = new Rechteck(beh_haus, 170, 190, 50, 100);
        tuer.setzeFarbe("blau");

        fenster = new Rechteck(beh_haus, 75, 190, 50, 50);
        fenster.setzeFarbe("gelb");

    }

    public void setzePosition( int x , int y){
        beh_haus.setzePosition(x,y);
    }

    public static void main(String[] args) {
        new BehaelterHaus();
        Zeichenflaeche.setzeMitRaster(true);
	}
}
