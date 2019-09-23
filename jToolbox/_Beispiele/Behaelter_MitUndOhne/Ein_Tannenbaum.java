//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class Ein_Tannenbaum {

    private Rechteck stamm;
    private Dreieck krone1;
    private Dreieck krone2;
    private Dreieck krone3;
    private Dreieck krone4;

    public Ein_Tannenbaum() {

        stamm = new Rechteck(80,250,40, 50);
        stamm.setzeFarbe("dunkelgrau");

        krone1 = new Dreieck(0,120,200,150);
        krone1.setzeFarbe("gruen");
        krone2 = new Dreieck(20,80,160,120);
        krone2.setzeFarbe("gruen");
        krone3 = new Dreieck(40,30,120,100);
        krone3.setzeFarbe("gruen");
        krone4 = new Dreieck(60,0,80,70);
        krone4.setzeFarbe("gruen");

    }

    public static void main(String[] args) {
        new Ein_Tannenbaum();
        Zeichenflaeche.setzeMitRaster(true);
    }

}
