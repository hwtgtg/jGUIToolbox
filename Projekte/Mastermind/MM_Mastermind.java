//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class MM_Mastermind {
    Behaelter q;
    MM_Zufallsquartet z;
    MM_Quartet[] anzeige;
    MM_Ergebnis[] ergebniss;
    MM_Eingabe ein;
    int anzahl;
    int versuch;

    public MM_Mastermind() {
        anzahl = 10;

        Zeichnung.setzeFenstergroesse(300, 200 + (anzahl * 50));

        q = new Behaelter(0, 0, 240, 50 + (anzahl * 50));
        q.setzeMitRand(true);

        z = new MM_Zufallsquartet();

        anzeige = new MM_Quartet[anzahl];
        ergebniss = new MM_Ergebnis[anzahl];

        for (int i = 0; i < anzahl; i++) {
            anzeige[i] = new MM_Quartet(5, 50 + (50 * i));
            ergebniss[i] = new MM_Ergebnis(170, 50 + (50 * i));
        }

        ein = new MM_Eingabe(this, 5, 50 + (50 * anzahl));

        versuch = 0;
        neueFarben();
    }

    public void neueFarben() {
        String[] farben;
        farben = new String[4];

        for (int i = 0; i < 4; i++) {
            farben[i] = ein.farbe[StaticTools.gibZufall(5)];
        }

        z.verbergeAusgangswert();
        z.q.setzeFarben(farben);

        for (int i = 0; i < anzahl; i++) {
            anzeige[i].zuruecksetzen();
            ergebniss[i].zuruecksetzen();
        }

        ein.zuruecksetzen();
    }

    public void uebertrageFarben(int position) {
        anzeige[position].setzeFarben(ein.gibFarben());
        auswerten(position);
    }

    public void auswerten(int position) {
        int fAnzahl = 0;

        // Anzahl der Farben bei Zufall
        int[] zanzahl = new int[6];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (z.q.gibFarben()[i].equals(ein.farbe[j])) {
                    zanzahl[j]++;
                }
            }
        }

        // Anzahl der Farben bei Setzen
        int[] eanzahl = new int[6];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (ein.gibFarben()[i].equals(ein.farbe[j])) {
                    eanzahl[j]++;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            fAnzahl += Math.min(zanzahl[i], eanzahl[i]);
        }

        int idAnzahl = 0;

        for (int i = 0; i < 4; i++) {
            if (ein.gibFarben()[i].equals(z.q.gibFarben()[i])) {
                idAnzahl++;
            }
        }

        ergebniss[position].anzeigen(idAnzahl, fAnzahl);

        if (idAnzahl == 4) { // alles richtig 
            z.zeigeAusgangswert();
            ein.nurReset = true;
        } else if (position == (anzahl - 1)) { // Misserfolg
            z.zeigeAusgangswert();
            ein.nurReset = true;
        }
    }

    public void uebertragen() {
        uebertrageFarben(versuch);
        versuch++;

        if (versuch >= anzahl) {
            versuch = 0;
        }
    }

    public void action() {
        for (int i = 0; i < anzahl; i++) {
            neueFarben();
            uebertrageFarben(i);
        }
    }

	public static void main(String[] args) {
         new MM_Mastermind();
      
    }
}
