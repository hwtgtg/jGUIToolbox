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

public class EinBaum {

    private Rechteck stamm;
    private Kreis krone;

    public EinBaum() {

        stamm = new Rechteck(90, 200);
        stamm.setzePosition(55, 150);
        stamm.setzeFarbe("dunkelgrau");

        krone = new Kreis(100);
        krone.setzePosition(0, 0);
        krone.setzeFarbe("gruen");

    }

    public static void main(String[] args) {
        new EinBaum();
        Zeichenflaeche.setzeMitRaster(true);
    }
}
