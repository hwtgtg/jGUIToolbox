//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Pumpe implements ITuWas {
    Behaelter pumpe;
    Pumpe_Schwungrad schwungrad;
    Pumpe_Pleuel pleuel;
    int lPleuel = 200;

    // B_Physik_Pleuel achse;
    // int lAchse = 300;
    Pumpe_Kolben_W kolben;
    Pfeil rohrAussenO;
    Pfeil rohrInnenO;
    Pfeil rohrAussenU;
    Pfeil rohrInnenU;
    Pfeil saugen;
    Pfeil druecken;
    Taktgeber takt;
    int winkel = 0;
    int mitteX = 200;
    int mitteY = 300;
    int radius = 70;

    /**
     *
     */
    public Pumpe() {
        Zeichnung.setzeFenstergroesse(1000, 600);

        schwungrad = new Pumpe_Schwungrad();
        schwungrad.setzeMittelpunkt(mitteX, mitteY);
        schwungrad.setzeRadius(radius + 10);
        schwungrad.setzeKurbelradius(radius);
        schwungrad.setzeWinkel(0);

        rohrAussenO = new Pfeil();
        rohrAussenO.setzeEndpunkte(mitteX + lPleuel + 241, 100,
            mitteX + lPleuel + 241, 250);
        rohrAussenO.setzeEnden(false, false);

        rohrAussenU = new Pfeil();
        rohrAussenU.setzeEndpunkte(mitteX + lPleuel + 241, 350,
            mitteX + lPleuel + 241, 500);
        rohrAussenU.setzeEnden(false, false);

        rohrInnenO = new Pfeil();
        rohrInnenO.setzeEnden(false, false);
        rohrInnenO.setzeEndpunkte(mitteX + lPleuel + 241, 100,
            mitteX + lPleuel + 241, 250);
        rohrInnenO.setzeBreite(36);
        rohrInnenO.setzeFarbe("blau");

        rohrInnenU = new Pfeil();
        rohrInnenU.setzeEnden(false, false);
        rohrInnenU.setzeEndpunkte(mitteX + lPleuel + 241, 350,
            mitteX + lPleuel + 241, 500);
        rohrInnenU.setzeBreite(36);
        rohrInnenU.setzeFarbe("blau");

        druecken = new Pfeil();
        ;
        druecken.setzeBreite(3);
        druecken.setzeFarbe("gelb");
        druecken.setzeEndpunkte(mitteX + lPleuel + 241, 200,
            mitteX + lPleuel + 241, 120);

        saugen = new Pfeil();
        saugen.setzeBreite(7);
        saugen.setzeFarbe("gelb");
        saugen.setzeEndpunkte(mitteX + lPleuel + 241, 480,
            mitteX + lPleuel + 241, 400);

        kolben = new Pumpe_Kolben_W();
        kolben.setzePosition(mitteX - radius + lPleuel, mitteY);
        kolben.positioniereZylinder(2 * radius);

        pleuel = new Pumpe_Pleuel(mitteX + schwungrad.leseDx(), mitteY,
                mitteX + schwungrad.leseDx() + lPleuel, mitteY);
        pleuel.setzeBreite(20);

        positioniere(0); // Startwinkel setzen

        /* */
        pumpe = new Behaelter(100, 100, 600, 600);
        //pumpe.setzeMitRaster(true);
        pumpe.hinzufuegenUndAnpassen(schwungrad.schwungrad);
        pumpe.hinzufuegenUndAnpassen(rohrAussenO);
        pumpe.hinzufuegenUndAnpassen(rohrAussenU);
        pumpe.hinzufuegenUndAnpassen(rohrInnenO);
        pumpe.hinzufuegenUndAnpassen(rohrInnenU);
        pumpe.hinzufuegenUndAnpassen(druecken);
        pumpe.hinzufuegenUndAnpassen(saugen);
        pumpe.hinzufuegenUndAnpassen(kolben.bZylinder);
        pumpe.hinzufuegenUndAnpassen(pleuel);

        pumpe.setzePosition(0, 0);
        //pumpe.setzeZoomfaktor(1);
        pumpe.setzeZoomfaktor(0.75);
        //pumpe.setzeZoomfaktor(1.5);
        //pumpe.setzePosition(200,200);

        /* */
        kolben.unten.auf();
        kolben.oben.zu();
        saugen.setzeEnden(false, true);
        druecken.setzeEnden(false, false);

        takt = new Taktgeber(this, 99);
        takt.setzeAnfangsZeitverzoegerung(1000);
        takt.setzeZeitZwischenAktionen(100);
        takt.endlos();
    }

    public void positioniere(int neuerWinkel) {
        this.winkel = neuerWinkel;

        while (winkel >= 360) {
            winkel = 0;
        }

        schwungrad.setzeWinkel(winkel);

        int dx = schwungrad.leseDx();

        // Koordinatensystem in y-Richtung nach unten !
        int dy = schwungrad.leseDy();

        int lx = (int) Math.sqrt((lPleuel * lPleuel) - (dy * dy));

        pleuel.setzeEndpunkte(mitteX + dx, mitteY + dy, mitteX + lx + dx, mitteY);
        // achse.setzeEndpunkte(mitteY + lx + dx, mitteY, mitteY + lx + dx
        // + lAchse, mitteY);
        kolben.positioniereZylinder((dx + radius + lx) - lPleuel);
    }

    public void tuWas(int ID) {
        winkel += 10;
        positioniere(winkel);

        if (winkel == 0) {
            kolben.unten.auf();
            kolben.oben.zu();
            saugen.setzeEnden(false, true);
            druecken.setzeEnden(false, false);
            saugen.setzeBreite(7);
            druecken.setzeBreite(3);
        }

        if (winkel == 180) {
            kolben.unten.zu();
            kolben.oben.auf();
            saugen.setzeEnden(false, false);
            druecken.setzeEnden(false, true);
            saugen.setzeBreite(3);
            druecken.setzeBreite(7);
        }
    }

    /**
     * @param args
     */
	public static void main(String[] args) {
         new Pumpe();
        Zeichnung.setzeFenstergroesse(1000, 600);
        Zeichnung.setzeRasterEin();
    }
}
