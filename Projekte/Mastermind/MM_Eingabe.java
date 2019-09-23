//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class MM_Eingabe implements ITuWas {
    Behaelter b;
    RadioBehaelter rb;
    RadioTaste[] rt;
    Taste[] rtAuswahl;
    String[] anzeigeFarben;
    String[] farbe;
    Taste taste;
    Taste reset;
    Boolean nurReset;
    int position;
    MM_Mastermind g;

    public MM_Eingabe(MM_Mastermind g, int x, int y) {
        this.g = g;
        b = new Behaelter(x, y, 240, 100);
        rb = new RadioBehaelter(b, 0, 0, 160, 40);
        rt = new RadioTaste[4];

        for (int i = 0; i < 4; i++) {
            rt[i] = new RadioTaste(rb, "", 2 + (i * 40), 2, 30, 28);
            rt[i].setzeID(i);
        }

        taste = new Taste(b, "S", 160, 2, 70, 30);
        taste.setzeID(30);
        taste.setzeLink(this);

        reset = new Taste(b, "R", 160, 52, 70, 30);
        reset.setzeID(31);
        reset.setzeLink(this);
        nurReset = false;

        farbe = new String[6];
        farbe[0] = "rot";
        farbe[1] = "gelb";
        farbe[2] = "blau";
        farbe[3] = "gruen";
        farbe[4] = "schwarz";
        farbe[5] = "orange";

        rtAuswahl = new Taste[6];

        for (int i = 0; i < 6; i++) {
            rtAuswahl[i] = new Taste(b, "", 2 + (i * 25), 52, 24, 22);
            rtAuswahl[i].setzeHintergrundfarbe(farbe[i]);
            rtAuswahl[i].setzeID(i + 10);
            rtAuswahl[i].setzeLink(this);
        }

        anzeigeFarben = new String[4];
        zuruecksetzen();

        rb.setzeLink(this);
    }

    public void zuruecksetzen() {
        for (int i = 0; i < 4; i++) {
            anzeigeFarben[i] = "blau";
            rt[i].setzeHintergrundfarbe("blau");
        }

        position = 0;
        rt[0].setzeGewaehlt();
    }

    public String[] gibFarben() {
        return anzeigeFarben;
    }

    public void tuWas(int ID) {
        if (nurReset && (ID != 31)) {
            return;
        }

        switch (ID) {
        case 0:
            position = ID;

            break;

        case 1:
            position = ID;

            break;

        case 2:
            position = ID;

            break;

        case 3:
            position = ID;

            break;

        case 10:
            rt[position].setzeHintergrundfarbe(farbe[ID - 10]);
            anzeigeFarben[position] = farbe[ID - 10];
            position++;

            if (position > 3) {
                position = 0;
            }

            rt[position].setzeGewaehlt();

            break;

        case 11:
            rt[position].setzeHintergrundfarbe(farbe[ID - 10]);
            anzeigeFarben[position] = farbe[ID - 10];
            position++;

            if (position > 3) {
                position = 0;
            }

            rt[position].setzeGewaehlt();

            break;

        case 12:
            rt[position].setzeHintergrundfarbe(farbe[ID - 10]);
            anzeigeFarben[position] = farbe[ID - 10];
            position++;

            if (position > 3) {
                position = 0;
            }

            rt[position].setzeGewaehlt();

            break;

        case 13:
            rt[position].setzeHintergrundfarbe(farbe[ID - 10]);
            anzeigeFarben[position] = farbe[ID - 10];
            position++;

            if (position > 3) {
                position = 0;
            }

            rt[position].setzeGewaehlt();

            break;

        case 14:
            rt[position].setzeHintergrundfarbe(farbe[ID - 10]);
            anzeigeFarben[position] = farbe[ID - 10];
            position++;

            if (position > 3) {
                position = 0;
            }

            rt[position].setzeGewaehlt();

            break;

        case 15:
            rt[position].setzeHintergrundfarbe(farbe[ID - 10]);
            anzeigeFarben[position] = farbe[ID - 10];
            position++;

            if (position > 3) {
                position = 0;
            }

            rt[position].setzeGewaehlt();

            break;

        case 30:
            g.uebertragen();

            break;

        case 31:
            g.neueFarben();
            nurReset = false; // Jetzt wieder normal

            break;

        default:
            break;
        }
    }
}
