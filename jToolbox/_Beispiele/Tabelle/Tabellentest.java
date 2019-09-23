//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * @author Witt
 * 
 */
public class Tabellentest implements ITuWas {
    Tabelle tab;

    // Geaendert fuer Test VCS

    public Tabellentest() {
        Zeichnung.setzeFenstergroesse(1000, 800);
    }

    public void tuWas(int ID) {
        int[] zeilenselect = null;
        int[] spaltenselect = null;
        switch (ID) {
            case Tabelle.ZELLENSELECT:
                System.out.println("ZellenSelect");
                zeilenselect = tab.getSelectZeilenFeld();
                spaltenselect = tab.getSelectSpaltenFeld();
                System.out.println("zZeilen");
                for (int i = 0; i < zeilenselect.length; i++) {
                    System.out.print(zeilenselect[i]);
                }
                System.out.println("-");
                System.out.println("zSpalten");
                for (int j = 0; j < spaltenselect.length; j++) {
                    System.out.print(spaltenselect[j]);
                }
                System.out.println("-");
    
                break;
            case Tabelle.SPALTENSELECT:
                System.out.println("SpaltenSelect");
                spaltenselect = tab.getSelectSpaltenFeld();
                System.out.println("Spalten");
                for (int j = 0; j < spaltenselect.length; j++) {
                    System.out.print(spaltenselect[j]);
                }
                System.out.println("s");
    
                break;
            case Tabelle.ZEILENSELECT:
                System.out.println("ZeilenSelect");
                zeilenselect = tab.getSelectZeilenFeld();
                System.out.println("Zeilen");
                for (int i = 0; i < zeilenselect.length; i++) {
                    System.out.print(zeilenselect[i]);
                }
                System.out.println("z");
                break;
            case Tabelle.EDITENDE:
                System.out.println("EditEnde");
                int zeileEdit = tab.getEditZeile();
                int spalteEdit = tab.getEditSpalte();
                System.out.println("ez" + zeileEdit + " es" + spalteEdit);
    
                // Schreibt nach jeder Aenderung die CSV-Datei neu
                CSVDatei datei = new CSVDatei();
                datei.importiereStringfeld(tab.leseTextblock(1,3,1,3));
                datei.schreibeCSVDatei("test-neu.csv",true);
    
                break;
            default:
            System.out.println("Sonst");

            break;
        }

    }

    public void test98() {
        String[] kopf = { "Eins", "Zwei", "Drei", "Vier" };
        String[][] daten = { { "111", "221", "331" }, { "222", "222", "332" },
                { "333", "223", "333" } };
        tab = new Tabelle(kopf, daten, 10, 10, 600, 250);

        // String[][] kopfUNDzellen = { { "Eins", "Zwei", "Drei", "Vier" },{
        // "111", "221", "331" }, { "222", "222", "332" },
        // { "333", "223", "333" } };
        // tab = new Tabelle(kopfUNDzellen, 10, 10, 600, 250);

        // tab = new Tabelle(10,10,0, 10,600,150);
        tab.setzeLink(this);
        // tab.autoresize(false);
        // int [] breiten = {100,150,100};
        // tab.setzeSpaltenbreiten(breiten);
        // tab.setzeSpaltenbreite(2,200);
        // tab.selektionsartSpalte(true);
        // tab.selektionsartZelle();

        // tab.selektionsartZeile(true);
        // tab.selektionsartSpalte(false);
        // tab.setzeText("xyz", 2, 2);
        // tab.setzeText("abc", 3, 3);

        // tab.setzeEditierbar(false);
        tab.setzeEditierbarSpalte(1, false);
        // tab.setzeEditierbar(false, 1, 2);

        // tab.setzeText("12,3", 0, 2);

        // tab.setzeText(tab.leseText(0, 1), 1, 1);
        // tab.setzeText("" + tab.leseInteger(0, 2, -1), 1, 2);
        // tab.setzeText("" + tab.leseIntegerGerundet(0, 2, -1), 2, 2);

        String[] zeile = { "Hinten", "224", "334" };

        tab.zeileHinzufuegen(zeile);
        tab.zeileHinzufuegen();

        tab.setzeAnzeigetypUni();
        String[] zeile2 = { "ins", "224", "334" };
        tab.zeileEinfuegen(2, zeile2);
        tab.zeileEinfuegen(1);
        tab.zeileLoeschen(3);

        // int anzSpalten = tab.leseAnzahlSpalten();
        // int anzZeilen = tab.leseAnzahlZeilen();

        String[] stringX = { "E01", "E02", "E03", "E04" };
        tab.spalteHinzufuegen("ans Ende", stringX);

        String[] string3 = { "01", "02", "03", "04" };
        tab.spalteEinfuegen(2, "E2", string3);

        // tab.setzeSpaltenkopfText(2, "Test");

        // tab.spalteLoeschen(1);
        // tab.spalteLoeschen(2);

        // String[] string4 = {"1","2","3","4"};
        // tab.spalteEinfuegen(4,"004",string4);
        // tab.spalteEinfuegen(3,"003",string4);
        String[] string5 = { "0", "1", "2", "3", "4" };
        tab.spalteEinfuegen(0, "null", string5);

        tab.setzeEditierbarTabelle(true);
        tab.setzeAnzeigetypZeilen();
        tab.setzeFarben("gruen", "gelb", "rot", "weiss");
        tab.selektionsartZeile(false);
        tab.selektionsartSpalte(false);
        tab.selektionsZelleBereich(true);

        tab.spalteNullAlsRand(true);
        tab.spalteNullAlsRand(true);

        tab.mitSpaltenSorter(true);

        tab.setzeText("123", 0, 1);

        tab.setzeIdentifierZelle(15, 0, 1);

        System.out.println("ID" + tab.leseIdentifierZelle(0, 1));

    }

    public void test99() {
        CSVDatei datei = new CSVDatei("test-text.csv");
        String[] kopf = null;
        // String[][] zellen = null;
        tab = new Tabelle(kopf, datei.exportiereStringfeld(), 0, 10, 600, 150);

        // tab = new Tabelle(datei.getKopf(),zellen,0, 10,600,150);
        // tab.zeileHinzufuegen();
        // tab.zeileHinzufuegen();

        tab = new Tabelle(datei.getKopf(), datei.getZellenAbZweiterZeile(), 0,
            10, 600, 150);
        // tab =new
        // Tabelle(datei.getAnzahlZeilen(),datei.getAnzahlSpalten(),0,10,600,150);
        // tab.setzeTextblock(datei.exportiereStringfeld(), 0, 0);
        //
        tab.setzeLink(this);

        //
        // datei.importiereStringfeld(tab.leseTextblock());
        // datei.schreibeCSVDatei("test-neu.csv",true);

    }

    public void test1() {
        String[] kopf = { "Eins", "Zwei", "Drei", "Vier" };
        String[][] daten = { { "111", "221", "331" }, { "222", "222", "332" },
                { "333", "223", "333" } };
        tab = new Tabelle(kopf, daten, 10, 10, 600, 250);

    }

    public void test2() {
        String[][] daten = { { "111", "221", "331" }, { "222", "222", "332" },
                { "333", "223", "333" } };

        // Spaltenkopf A,B,C,...
        tab = new Tabelle((String[]) null, daten, 10, 10, 600, 250);

    }

    public void test3() {
        // 6 Zeilen und 4 Spalten
        tab = new Tabelle(6, 4, 10, 10, 600, 250);

    }

    public void test4() {
        // 6 Zeilen und 4 Spalten
        tab = new Tabelle(6, 4, 10, 10, 600, 250);
        // Kein automatisches Expandieren auf Tabellenbreite
        tab.autoresize(false);
    }

    public void test5() {
        // 6 Zeilen und 4 Spalten
        tab = new Tabelle(6, 4, 10, 10, 600, 250);
        // Spaltenbreite (Vorschlag)
        tab.setzeSpaltenbreite(0, 200);
        // Spaltenbreite (Fix)
        tab.setzeSpaltenbreiteFix(1, 50);
    }

    public void test6() {
        // 6 Zeilen und 4 Spalten
        tab = new Tabelle(6, 4, 10, 10, 600, 250);

        // TuWasKlasse reagiert auf EditEnde
        ITuWas link = new ITuWas() {
                public void tuWas(int ID) {
                    switch (ID) {
                        case Tabelle.EDITENDE:
                        System.out.println("EditEnde");
                        int zeileEdit = tab.getEditZeile();
                        int spalteEdit = tab.getEditSpalte();
                        System.out.println("editZeile:" + zeileEdit
                            + " editSpalte:" + spalteEdit);
                        break;
                        default:
                        break;
                    }
                }
            };
        tab.setzeLink(link);
    }

    public void test7() {
        // 6 Zeilen und 4 Spalten
        tab = new Tabelle(6, 4, 10, 10, 600, 250);

        tab.setzeFarben("schwarz", "weiss", "dunkelgrau", "gelb");
        // einfarbig
        // tab.setzeAnzeigtypUni();

        // Zeilen werden abwechselnd gefaerbt
        // tab.setzeAnzeigetypZeilen();

        // Zellen werden abwechselnd gefaerbt: Schachbrettmuster
        tab.setzeAnzeigetypZellen();

    }

    public void test8() {
        // 6 Zeilen und 4 Spalten
        tab = new Tabelle(6, 4, 10, 10, 600, 250);

        tab.setzeFarben("schwarz", "weiss", "dunkelgrau", "gelb");
        // Zellen werden abwechselnd gefaerbt: Schachbrettmuster
        tab.setzeAnzeigetypZellen();
        tab.setzeEditierbarTabelle(false);

        tab.selektionsZelleBereich(true);
        tab.selektionsartZeile(true);
        tab.selektionsartSpalte(true);

        // TuWasKlasse reagiert auf Selektion
        ITuWas link = new ITuWas() {
                public void tuWas(int ID) {
                    int[] zeilenselect = null;
                    int[] spaltenselect = null;
                    switch (ID) {
                        case Tabelle.ZELLENSELECT:
                        System.out.println("ZellenSelect");
                        zeilenselect = tab.getSelectZeilenFeld();
                        spaltenselect = tab.getSelectSpaltenFeld();
                        System.out.print("Zeilen");
                        for (int i = 0; i < zeilenselect.length; i++) {
                            System.out.print(":" + zeilenselect[i]);
                        }
                        System.out.println();
                        System.out.print("Spalten");
                        for (int j = 0; j < spaltenselect.length; j++) {
                            System.out.print(":" + spaltenselect[j]);
                        }
                        System.out.println();
                        break;
                        case Tabelle.SPALTENSELECT:
                        System.out.println("SpaltenSelect");
                        spaltenselect = tab.getSelectSpaltenFeld();
                        System.out.print("Spalten");
                        for (int j = 0; j < spaltenselect.length; j++) {
                            System.out.print(":" + spaltenselect[j]);
                        }
                        System.out.println();

                        break;
                        case Tabelle.ZEILENSELECT:
                        System.out.println("ZeilenSelect");
                        zeilenselect = tab.getSelectZeilenFeld();
                        System.out.print("Zeilen");
                        for (int i = 0; i < zeilenselect.length; i++) {
                            System.out.print(":" + zeilenselect[i]);
                        }
                        System.out.println();
                        break;
                        default:
                        System.out.println(".");
                        break;
                    }
                }
            };
        tab.setzeLink(link);

    }

    public void test9() {
        // 6 Zeilen und 4 Spalten
        tab = new Tabelle(6, 4, 10, 10, 600, 250);

        tab.setzeFarben("schwarz", "weiss", "dunkelgrau", "gelb");
        // Zellen werden abwechselnd gefaerbt: Schachbrettmuster
        tab.setzeAnzeigetypZellen();
        tab.setzeEditierbarTabelle(false);

        tab.selektionsZelleBereich(true);
        tab.selektionsartZeile(true);
        tab.selektionsartSpalte(true);

        tab.setzeEditierbarTabelle(false);
        tab.setzeEditierbarSpalte(2, true);
        tab.setzeEditierbarZeile(2, true);

    }

    public void test10() {
        String[] kopf = { "Eins", "Zwei", "Drei", "Vier" };
        String[][] daten = { { "111", "221", "331" }, { "222", "222", "332" },
                { "333", "223", "333" } };
        tab = new Tabelle(kopf, daten, 10, 10, 600, 250);

        tab.setzeFarben("schwarz", "weiss", "dunkelgrau", "gelb");
        // Zellen werden abwechselnd gefaerbt: Schachbrettmuster
        tab.setzeAnzeigetypZeilen();
        tab.setzeEditierbarTabelle(false);

        tab.setzeHervorgehobenZeile(true, 2);

    }

    public void test11() {
        String[] kopf = { "Eins", "Zwei", "Drei", "Vier" };
        String[][] daten = { { "111", "221", "331" },
                { "222", "[nein] 222", "332" }, { "333", "223", "333" } };
        tab = new Tabelle(kopf, daten, 10, 10, 600, 250);

        tab.setzeSchriftgroesse(20);

        tab.setzeFarben("schwarz", "weiss", "dunkelgrau", "gelb");
        // Zellen werden abwechselnd gefaerbt: Schachbrettmuster
        tab.setzeAnzeigetypZeilen();
        tab.setzeEditierbarTabelle(true);

        tab.setzeHervorgehobenZeile(true, 2);

    }

    public void test12() {
        String[] kopf = { "Eins", "Zwei", "Drei", "Vier" };
        String[][] daten = { { "111", "221", "331" },
                { "221", "[nein] 222", "332" }, { "333", "223", "333" } };
        tab = new Tabelle(kopf, daten, 10, 10, 600, 250);

        tab.setzeSchriftgroesse(20);

        tab.setzeFarben("schwarz", "weiss", "dunkelgrau", "gelb");
        // Zellen werden abwechselnd gefaerbt: Schachbrettmuster
        tab.setzeAnzeigetypZeilen();
        tab.setzeEditierbarTabelle(true);

        tab.setzeHervorgehobenZeile(true, 2);

        tab.aktiviereChekboxSpalte(0, true);
        tab.aktiviereChekboxZelle(1, 1, false);

        tab.zeileHinzufuegen();

        tab.zeileEinfuegen(3);

        tab.setzeText("Hallo!", 2, 0);

    }

    public void test() {
        String[] kopf = { "Eins", "Zwei", "Drei", "Vier" };
        String[][] daten = { { "111", "221", "331" },
                { "221", "[nein] 222", "332" }, { "333", "223", "333" } };
        tab = new Tabelle(kopf, daten, 10, 10, 600, 250);

        tab.setzeSchriftgroesse(30);
        tab.setzeSchriftName("Times New Roman");

        tab.setzeFarben("schwarz", "weiss", "dunkelgrau", "gelb");
        // Zellen werden abwechselnd gefaerbt: Schachbrettmuster
        tab.setzeAnzeigetypZeilen();
        tab.setzeEditierbarTabelle(true);

        tab.aktiviereChekboxSpalte(0, true);
        tab.aktiviereChekboxZelle(1, 1, false);

        tab.zeileHinzufuegen();

        tab.setzeText("Hallo!", 2, 0);

        Taste test = new Taste("Test fuer Fokusverlusst", 10, 300, 300, 80);
        test.setzeSchriftgroesse(12);

        // Schreibt nach jeder Aenderung die CSV-Datei neu
        CSVDatei datei = new CSVDatei();
        datei.importiereStringfeld(tab.leseTextblock());
        datei.schreibeCSVDatei("tabNeu.csv", true);

    }

    public void test14() {
        CSVDatei datei = new CSVDatei("tabNeu.csv");

        String[] kopf = { "Eins", "Zwei", "Drei", "Vier" };

        tab = new Tabelle(kopf,datei.exportiereStringfeld(), 10, 10, 600, 250);

        tab.setzeSchriftgroesse(20);

        tab.setzeFarben("schwarz", "weiss", "dunkelgrau", "gelb");
        // Zellen werden abwechselnd gefaerbt: Schachbrettmuster
        tab.setzeAnzeigetypZeilen();
        tab.setzeEditierbarTabelle(true);

        tab.aktiviereChekboxSpalte(0, true);

    }

    public static void main(String[] args) {
        Tabellentest t = new Tabellentest();
        t.test();
    }

}
