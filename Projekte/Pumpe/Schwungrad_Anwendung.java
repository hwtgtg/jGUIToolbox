//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * @author Witt
 *
 */
public class Schwungrad_Anwendung implements ITuWas {
    Behaelter aw;
    Kreis rad;
    Kreis mitte;
    Kreis zapfen;
    Linie pleuel;
    int lPleuel = 300;
    Linie achse;
    int lAchse = 300;
    Taktgeber takt;
    int winkel = 0;
    int mitteX = 200;
    int mitteY = 200;
    int radius = 100;

    /**
     *
     */
    public Schwungrad_Anwendung() {
        aw = new Behaelter(1000, 600);
        rad = new Kreis(150);
        rad.setzeMittelpunktUndRadius(mitteX, mitteY, 150);
        rad.setzeFarbe("schwarz");
        rad.rand();

        mitte = new Kreis(150);
        mitte.setzeMittelpunktUndRadius(mitteX, mitteY, 10);
        mitte.setzeFarbe("schwarz");

        pleuel = new Linie(mitteX + radius, mitteY, mitteY + radius + lPleuel,
                mitteY);
        pleuel.setzeLinienDicke(20);

        achse = new Linie(mitteY + radius + lPleuel, mitteY,
                mitteY + radius + lPleuel + lAchse, mitteY);
        achse.setzeLinienDicke(20);

        zapfen = new Kreis(150);
        zapfen.setzeMittelpunktUndRadius(mitteX + radius, mitteY, 6);
        zapfen.setzeFarbe("schwarz");

        aw.hinzufuegen(rad);
        aw.hinzufuegen(mitte);
        aw.hinzufuegen(pleuel);
        aw.hinzufuegen(achse);
        aw.hinzufuegen(zapfen);

        aw.setzeZoomfaktor(0.5);

        takt = new Taktgeber(this, 99);
        takt.setzeAnfangsZeitverzoegerung(1000);
        takt.setzeZeitZwischenAktionen(25);
        takt.endlos();

        //takt.einmal();
    }

    public void tuWas(int ID) {
        winkel += 5;

        while (winkel > 360) {
            winkel -= 360;
        }

        int dx = (int) (Math.cos((winkel * Math.PI) / 180) * radius);

        // Koordinatensystem in y-Richtung nach unten !
        int dy = (int) (Math.sin((winkel * Math.PI) / 180) * radius);
        zapfen.setzeMittelpunkt(mitteX + dx, mitteY + dy);

        int lx = (int) Math.sqrt((lPleuel * lPleuel) - (dx * dx));
        pleuel.setzeEndpunkte(mitteX + dx, mitteY + dy, mitteY + lx + dx, mitteY);
        achse.setzeEndpunkte(mitteY + lx + dx, mitteY,
            mitteY + lx + dx + lAchse, mitteY);
    }

    /**
     * @param args
     */
	public static void main(String[] args) {
         new Schwungrad_Anwendung();
        Zeichnung.setzeFenstergroesse(1000, 600);
        Zeichnung.setzeRasterEin();
    }
}
