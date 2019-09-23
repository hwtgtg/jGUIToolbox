//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class Ein_Haus {

    private Dreieck dach;
    private Rechteck haus;
    private Rechteck tuer;
    private Rechteck fenster;
    private Ellipse halbfenster ;

    public Ein_Haus() {

        dach = new Dreieck(200, 100);
        dach.setzePosition(0,0);
        dach.setzeFarbe("rot");

        haus = new Rechteck(200, 200);
        haus.setzePosition(0, 100);

        tuer = new Rechteck(50, 100);
        tuer.setzePosition(100, 200);
        tuer.setzeFarbe("blau");

        fenster = new Rechteck(50, 50);
        fenster.setzePosition(25, 200);
        fenster.setzeFarbe("gelb");

        halbfenster= new Ellipse(0 ,0 , 80 , 80 , 0 , 180 );
        halbfenster.setzePosition(60, 130 );
        halbfenster.setzeFarbe("grau");
    }

    public static void main(String[] args) {
        new Ein_Haus();
        Zeichenflaeche.setzeMitRaster(true);
    }
}
