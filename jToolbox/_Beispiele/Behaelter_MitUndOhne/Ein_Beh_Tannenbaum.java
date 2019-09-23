//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Ein_Beh_Tannenbaum {

    private Behaelter	behaelter;

    private Rechteck	stamm;
    private Dreieck		krone1;
    private Dreieck		krone2;
    private Dreieck		krone3;
    private Dreieck		krone4;

    public Ein_Beh_Tannenbaum(int x , int y) {
        behaelter = new Behaelter(x,y,200,300);	

        stamm = new Rechteck(behaelter,80, 250, 40, 50);
        stamm.setzeFarbe("dunkelgrau");

        krone1 = new Dreieck(behaelter,0, 120, 200, 150);
        krone1.setzeFarbe("gruen");
        krone2 = new Dreieck(behaelter,20, 80, 160, 120);
        krone2.setzeFarbe("gruen");
        krone3 = new Dreieck(behaelter,40, 30, 120, 100);
        krone3.setzeFarbe("gruen");
        krone4 = new Dreieck(behaelter,60, 0, 80, 70);
        krone4.setzeFarbe("gruen");
    }

    void setzePosition( int x , int y ){
        behaelter.setzePosition(x, y);
    }

    void zoom(double f){
        behaelter.setzeZoomfaktor(f);
    }

    public static void main(String[] args) {
        Ein_Beh_Tannenbaum b1 = new Ein_Beh_Tannenbaum(0,0);
        Ein_Beh_Tannenbaum b2 = new Ein_Beh_Tannenbaum(100,150);
        b1.zoom(0.7);
        b2.zoom(1.2);
        Zeichenflaeche.setzeMitRaster(true);
    }
}
