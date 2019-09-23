/**
 * Write a description of class Kiste here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Kiste {
    // instance variables - replace the example below with your own
    Erzeuger erzeuger;
    int lfdNr;

    Bild pizza ;

    Behaelter kB;
    Rechteck kR;
    Ausgabe kAE;
    Ausgabe kAnr;

    static int hoch = 80;
    static int breit = 80;

    int posX;
    int posY;

    /**
     * Constructor for objects of class Kiste
     */
    public Kiste(Erzeuger erzeuger, int lfdNr) {
        this.erzeuger = erzeuger;
        this.lfdNr = lfdNr;

        posX = erzeuger.posX+Erzeuger.breit-breit ;
        posY = erzeuger.posY+Erzeuger.dy  ;

        kB = new Behaelter(GUI.bKisten, posX,posY,breit,hoch);
        kR = new Rechteck(kB,0,0,breit,hoch);
        kR.setzeFarbe("rot");

        pizza = new Bild(kB,new Bilddatei("pizza.jpg"));
        pizza.setzePosition(0,10);

        //		kAE = new Ausgabe(kB,erzeuger.getFirma(), 0, 5, breit, hoch/2-10);
        //		kAE.setzeAusrichtung(1);
        //		kAnr = new Ausgabe(kB,""+lfdNr, 0, hoch/2+5, breit, hoch/2-10);
        //		kAnr.setzeAusrichtung(1);
    }

    public String leseInfo() {
        return erzeuger + "_" + lfdNr;
    }

    public void verschieben(int i) {
        kB.setzePosition(posX+i, posY);
    }

    public void verschieben(int dx,int dy) {
        kB.setzePosition(posX+dx, posY+dy);
        posX+=dx;
        posY+=dy;
    }

    public void setzePosition(int posX,int posY) {
        this.posX=posX;
        this.posY=posY;
        kB.setzePosition(posX, posY);
    }

    public void endpositionErzeuger() {
        posX=posX+breit;
        kB.setzePosition(posX, posY);
    }

    public void setzeFrabe(String farbe) {
        kR.setzeFarbe(farbe);

    }

}
