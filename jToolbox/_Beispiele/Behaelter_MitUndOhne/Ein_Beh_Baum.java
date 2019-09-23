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

public class Ein_Beh_Baum {
    private Behaelter behaelter ;
    private Rechteck stamm;
    private Kreis krone;

    public Ein_Beh_Baum(int x , int y) {
        behaelter = new Behaelter(x,y,200,350);	
        stamm = new Rechteck(behaelter,55, 150,90, 200);
        stamm.setzeFarbe("dunkelgrau");

        krone = new Kreis(behaelter,0,0,100);
        krone.setzeFarbe("gruen");
    }

    void setzePosition( int x , int y ){
        behaelter.setzePosition(x, y);
    }

    void zoom(double f){
        behaelter.setzeZoomfaktor(f);
    }

    public static void main(String[] args) {
        Ein_Beh_Baum b1 = new Ein_Beh_Baum(0,0);
        Ein_Beh_Baum b2 = new Ein_Beh_Baum(100,150);
        b1.zoom(0.7);
        b2.zoom(1.2);
        Zeichenflaeche.setzeMitRaster(true);
    }
}
