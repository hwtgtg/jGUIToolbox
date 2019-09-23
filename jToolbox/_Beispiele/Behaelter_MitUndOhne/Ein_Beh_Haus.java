//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class Ein_Beh_Haus {

    private Behaelter behaelter ;
    private Dreieck dach;
    @SuppressWarnings("unused")
    private Rechteck haus;
    private Rechteck tuer;
    private Rechteck fenster;
    private Ellipse halbfenster ;

    public Ein_Beh_Haus(int x , int y ) {
        behaelter = new Behaelter(x,y,200,300);	
        dach = new Dreieck(behaelter,0,0,200, 100);
        dach.setzeFarbe("rot");

        haus = new Rechteck(behaelter,0,100,200, 200);

        tuer = new Rechteck(behaelter,100,200,50, 100);
        tuer.setzeFarbe("blau");

        fenster = new Rechteck(behaelter,25,200,50, 50);
        fenster.setzeFarbe("gelb");

        halbfenster= new Ellipse(behaelter,60 ,130 , 80 , 80 , 0 , 180 );
        halbfenster.setzeFarbe("grau");
    }

    void setzePosition( int x , int y ){
        behaelter.setzePosition(x, y);
    }

    void zoom(double f){
        behaelter.setzeZoomfaktor(f);
    }

    public static void main(String[] args) {
        Ein_Beh_Haus b1 = new Ein_Beh_Haus(100,100);
        Ein_Beh_Haus b2 = new Ein_Beh_Haus(200,180);
        b1.zoom(0.8);
        b2.zoom(1.2);
        Zeichenflaeche.setzeMitRaster(true);
    }
}
