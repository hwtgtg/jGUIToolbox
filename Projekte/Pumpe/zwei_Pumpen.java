//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class zwei_Pumpen {
    Pumpe p1;
    Pumpe p2;

    public zwei_Pumpen() {
        p1 = new Pumpe();
        p2 = new Pumpe();
        p1.pumpe.setzeZoomfaktor(0.5);
        p1.pumpe.setzePosition(200, 200);
        p2.pumpe.setzeZoomfaktor(1.5);
        p2.pumpe.setzePosition(200, 200);
    }

	public static void main(String[] args) {
        new zwei_Pumpen();
        Zeichnung.setzeFenstergroesse(1000, 800);

        //Zeichnung.setzeRasterEin();
    }
}
