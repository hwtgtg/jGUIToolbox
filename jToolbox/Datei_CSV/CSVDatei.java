//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

/**
 * Die Klasse <b>CSVDatei</b> dient dem Lesen und Schreiben von CSV-Dateien<br/>
 * Die Klasse CSVDatei erstellt ein pseudozweidimensionales Feld<br/>
 * Die Datenfelder koennen ueber Indizes direkt gelesen/erzeugt/veraendert werden<br/>
 * Beispielzeile: <br/>
 * 15;19;"Bayern";"Ein ""Wort"" mit Anfuehrungszeichen"<br/>
 * .
 * 
 * Eine Zeile einer CSV-Datei besteht aus einzelnen (String-)Feldern, die durch
 * ein Trennzeichen voneinander getrennt sind. Standard-Trennzeichen ist der
 * Strichpunkt <b>;</b>. Felder, die das Trennzeichen enthalten, werden von
 * Feldbegrenzerzeichen umgeben. Standard-Feldbegrenzerzeichen ist das doppelte
 * Anfuehrungszeichen <b>"</b>. <br />
 * In Feldern vorhandene Feldbegrenzerzeichen werden beim Speichern gedoppelt. <br />
 * <br />
 * Beispiel: <br />
 * 15;19;"Eintrag mit ; "; "Das ""Wort"" wird hervorhgehoben" <br/>
 * <br/>
 * 
 * Der Konstruktor der Klasse erstellt die Datenstruktur. <br />
 * Ist ein Dateiname angegeben, so wird die Datei in die Datenstruktur
 * importiert.<br/>
 * <br/>
 * Alternativ: lesenCSVDatei(String dateiname) importiert die Datei,wenn sie
 * existiert.<br/>
 * <br/>
 * schreibeCSVDatei(String dateiname, boolean ersetzen) schreibt die Datei<br/>
 * ersetzen=true ersetzt eine bestehende Datei<br/>
 * <br/>
 * schreibeCSVDatei() ersetzt die letzte (gelesene oder geschriebene) Datei<br/>
 * <br/>
 * 
 * setzeString(String wert , int zeile , int spalte) erzeugt/aendert einen
 * CSVDatei-Eintrag <br/>
 * genauso setzeInteger und setzeDouble<br/>
 * <br/>
 * <br/>
 * 
 * leseString(int zeile , int spalte) liest einen Eintrag als String<br/>
 * <br/>
 * leseInteger(int zeile , int spalte, double error)<br/>
 * und leseDouble(int zeile , int spalte, double error)<br/>
 * lesen eine INTEGER, LONG, BOOLEAN bzw DOUBLE-Zahl.<br/>
 * errror erhaelt man, falls dabei ein Fehler auftritt<br/>
 * <hr>
 * autor: Witt
 * 
 */
public class CSVDatei {

    String dateiname = null;

    LinkedList<String> strZeilen = new LinkedList<String>();
    int anzahlZeilen = 0;
    int anzahlSpalten = 0;

    class CSVDatensatz {

        String strZeile = null;
        LinkedList<String> csvZeile = null;

        /**
         * @param daten
         */
        protected CSVDatensatz(String daten) {
            csvZeile = new LinkedList<String>();
            importiereZeile(daten);
        }

        public void importiereZeile(String daten) {
            strZeile = daten.trim();
            if (strZeile == null || strZeile == "") { // Zeile loeschen
                csvZeile.clear();
            } else {
                analysiereZeile(strZeile);
            }
        }

        private void analysiereZeile(String strZeile) {

            // System.out.println(strZeile);//
            // //////////////////////////////////

            /*
             * Wenn erstes Zeichen = Feldbegrenzerzeichen , Zeichen in String
             * bis naechstes Feldbegerenzerzeichen. Ist naechstes Zeichen wieder
             * Feldbegerenzerzeichen, so wird eines in den String uebernommen.
             * 
             * Nach nicht doppeltem Feldbegerenzerzeichen muss trennzeichen
             * kommen.
             * 
             * sonst: Lesen bis trennzeichen.
             */
            int len = strZeile.length();
            int i = 0;
            boolean feldstart = true;
            boolean inFeldbegrenzern = false;
            StringBuffer strb = new StringBuffer();
            while (i < len) {
                if (feldstart) {
                    // Wenn erstes Zeichen Feldbegrenzer -> Marke setzen und
                    // Zeichen
                    // ignorieren
                    inFeldbegrenzern = (strZeile.charAt(i) == Feldbegrenzerzeichen);
                    if (inFeldbegrenzern)
                        i++;
                    feldstart = false;
                    if ((i >= len))
                        break;
                }

                if (inFeldbegrenzern) { // Warten auf Feldbegrenzer Ende
                    // Spezialfall Feldbegrenzer doppelt
                    if ((strZeile.charAt(i) == Feldbegrenzerzeichen)) {
                        if (((i + 1) < len)
                        && (strZeile.charAt(i + 1) == Feldbegrenzerzeichen)) {
                            i++;
                            strb.append(strZeile.charAt(i));
                        } else { // Ende Feld
                            inFeldbegrenzern = false;
                        }
                    } else { // Normales zeichen im Feld
                        strb.append(strZeile.charAt(i));
                    }
                } else {// Ohne Feldbegrenzer
                    // Warten auf Trennzeichen
                    if (strZeile.charAt(i) == trennzeichen) {
                        // String hinzufuegen
                        csvZeile.add(strb.toString());
                        // Neuen Stringbuffer vorbereiten
                        strb = new StringBuffer();
                        feldstart = true;
                    } else { // Normales zeichen
                        strb.append(strZeile.charAt(i));
                    }
                }
                i++;
            }
            // String nach letztem Trennzeichen hinzufuegen
            csvZeile.add(strb.toString());
            // AnzahlSpalten anpassen
            if (csvZeile.size() > anzahlSpalten)
                anzahlSpalten = csvZeile.size();
        }

        @Override
        public String toString() {
            StringBuffer erg = new StringBuffer();
            for (String str : csvZeile) {
                // ist das Trennzeichen im String, wird auf jedem Fall mit
                // Feldbegrenzern gearbeitet
                if ((str.indexOf(trennzeichen) >= 0)
                || (str.indexOf(Feldbegrenzerzeichen) >= 0)
                || mitFeldbegrenzer) {
                    erg.append(Feldbegrenzerzeichen);
                    for (int i = 0; i < str.length(); i++) {
                        erg.append(str.charAt(i));
                        if (str.charAt(i) == Feldbegrenzerzeichen) {
                            erg.append(Feldbegrenzerzeichen);
                        }
                    }
                    erg.append(Feldbegrenzerzeichen);
                } else {
                    erg.append(str);
                }
                erg.append(trennzeichen);
            }
            if (erg.length() > 0) {
                erg.deleteCharAt(erg.length() - 1);
            }
            return erg.toString();
        }

        public int anzahlElemente() {
            return csvZeile.size();
        }

        public String getWert(int spalte) {
            return csvZeile.get(spalte);
        }

        public void setWert(String text, int spalte) {
            while (csvZeile.size() - 1 < spalte) {
                csvZeile.add("");
            }
            if (csvZeile.size() > anzahlSpalten)
                anzahlSpalten = csvZeile.size();
            csvZeile.set(spalte, text);
        }
    }

    LinkedList<CSVDatensatz> csvZeilen = new LinkedList<CSVDatensatz>();

    char trennzeichen = ';';
    char Feldbegrenzerzeichen = '"';
    boolean mitFeldbegrenzer = true;

    final static int CSV_ENDE = -1;

    /**
     * Konstruktor
     * 
     * erzeugt leere LinkedList
     */
    public CSVDatei() {
        strZeilen = new LinkedList<String>();
        csvZeilen = new LinkedList<CSVDatensatz>();
    }

    /**
     * Konstruktor
     * 
     * erzeugt LinkedList und liest Datei "dateiname" in die LinkedList
     */
    public CSVDatei(String dateiname) {
        super();
        importiereCSVDatei(dateiname);
    }

    /**
     * importieren der CSV-Datei
     * 
     * @param dateiname
     */
    public void importiereCSVDatei(String dateiname) {
        lesenCSVDatei(dateiname);
        for (String strg : strZeilen) {
            csvZeilen.add(new CSVDatensatz(strg));
        }
        anzahlZeilen = csvZeilen.size();
    }

    /**
     * Importieren einer CSV-String-Zeile
     * 
     * @param zeile
     */
    public void importiereCSVString(String zeile) {
        csvZeilen.add(new CSVDatensatz(zeile));
        anzahlZeilen = csvZeilen.size();
    }

    /**
     * exportiere Zeile als CSV-String
     * 
     * @param zeile
     */
    public String exportiereAlsCSVString(int zeile) {
        if (zeile < anzahlZeilen) {
            return csvZeilen.get(zeile).toString();
        } else {
            return "";
        }
    }

    /**
     * Exportiere aller Zeilen als einen String. Die einzelen Zeilen sind
     * CSV-Strings. Zeilen werden mit \n getrennt
     * 
     */
    @Override
    public String toString() {
        String erg = "";
        for (CSVDatensatz ds : csvZeilen) {
            erg += ds.toString();
            erg += "\n";
        }
        return erg;
    }

    /**
     * Importieren eines Stringfelds in eine CSV-Klasse.<br />
     * Bereits vorhandene Werte werden ueberschrieben.
     * 
     * @param zellen
     */
    public void importiereStringfeld(String[][] zellen) {
        for (int z = 0; z < zellen.length; z++) {
            for (int s = 0; s < zellen[z].length; s++) {
                setzeString(zellen[z][s], z, s);
            }
        }
        anzahlZeilen = csvZeilen.size();
    }

    /**
     * exportieren der CSV-Klasse als Stringfeld
     * 
     * @return Stringfeld
     */
    public String[][] exportiereStringfeld() {
        int anzahlZeilen = getAnzahlZeilen();
        int anzahlSpalten = getAnzahlSpalten();
        String[][] erg = new String[anzahlZeilen][anzahlSpalten];

        for (int z = 0; z < anzahlZeilen; z++) {
            for (int s = 0; s < anzahlSpalten; s++) {
                erg[z][s] = leseString(z, s);
            }
        }
        return erg;
    }

    /**
     * Gibt die Kopfzeile/Erste Zeile als Stringfeld zurueck.<br />
     * Hilfreich, wenn die erste Zeile Kopfzeile der Tabellarischen Daten ist.
     * 
     * @return Stringfeld
     */
    public String[] getKopf() {
        int anzahlZeilen = getAnzahlZeilen();
        int anzahlSpalten = getAnzahlSpalten();
        String[] spaltenkopf = new String[anzahlSpalten];
        if (anzahlZeilen < 1) {
            return null;
        }
        for (int s = 0; s < anzahlSpalten; s++) {
            spaltenkopf[s] = leseString(0, s);
        }
        return spaltenkopf;

    }

    /**
     * Gibt die Zeilen ab der zweiten Zeile als Stringfeld zurueck.<br />
     * Hilfreich, wenn die erste Zeile Kopfzeile der Tabellarischen Daten ist.
     * 
     * @return Stringfeld
     */
    public String[][] getZellenAbZweiterZeile() {
        int anzahlZeilen = getAnzahlZeilen();
        int anzahlSpalten = getAnzahlSpalten();
        String[][] erg = new String[anzahlZeilen][anzahlSpalten];

        for (int z = 1; z < anzahlZeilen; z++) {
            for (int s = 0; s < anzahlSpalten; s++) {
                erg[z - 1][s] = leseString(z, s);
            }
        }
        return erg;
    }

    /**
     * <strong>Lesen der Datei in die Zeilen-Liste</strong>. Wird von
     * importiereCSVDatei verwendet.
     * 
     * @param dateiname
     */
    private void lesenCSVDatei(String dateiname) {
        this.dateiname = dateiname;
        File datei = new File(dateiname);
        strZeilen.clear();
        if (!datei.exists()) {
            return;
        } else {
            FileReader reader = null;
            try {
                String pfad = datei.getPath();
                reader = new FileReader(pfad);
                BufferedReader br = new BufferedReader(reader);

                String zeile;

                while ((zeile = br.readLine()) != null) {

                    if (zeile != null) {
                        strZeilen.add(zeile);
                    }
                }
                if(br!=null){br.close();

                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, e.toString(),
                    "Probleme beim Lesen", JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            } finally {
                try {
                    if (reader != null)
                        reader.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public char getTrennzeichen() {
        return trennzeichen;
    }

    /**
     * Legt das Trennzeichen fest. Default: ;
     * 
     * @param trennzeichen
     */
    public void setTrennzeichen(char trennzeichen) {
        this.trennzeichen = trennzeichen;
    }

    public char getFeldbegrenzerzeichen() {
        return Feldbegrenzerzeichen;
    }

    /**
     * legt das Feldbegrenzerzeichen fest. Default: ".
     * 
     * @param feldbegrenzerzeichen
     */
    public void setFeldbegrenzerzeichen(char feldbegrenzerzeichen) {
        Feldbegrenzerzeichen = feldbegrenzerzeichen;
    }

    public boolean isMitFeldbegrenzer() {
        return mitFeldbegrenzer;
    }

    /**
     * Legt fest, ob mit Feldbegrenzerzeichen geschrieben wird. Defaut: true
     * 
     * true: Alle Felder werden mit Feldbegrenzer-Zeichen geschrieben<br />
     * sonst: nur wenn das Trennzeichen bzw. das Feldbegrenzerzeichen im String
     * ist werden Feldbegrenzerzeichen gesetzt.
     * 
     * @param mitFeldbegrenzer
     */
    public void setMitFeldbegrenzer(boolean mitFeldbegrenzer) {
        this.mitFeldbegrenzer = mitFeldbegrenzer;
    }

    public int getAnzahlZeilen() {
        return csvZeilen.size();
    }

    public int getAnzahlSpalten() {
        return anzahlSpalten;
    }

    /**
     * Eingabe/Veraenderung eines Werts als String
     * 
     * @param text
     * @param zeile
     * @param spalte
     */
    public void setzeString(String text, int zeile, int spalte) {
        while (csvZeilen.size() - 1 < zeile) {
            csvZeilen.add(new CSVDatensatz(""));
        }
        if (text == null)
            text = "";
        csvZeilen.get(zeile).setWert(text, spalte);
    }

    /**
     * Eingabe/Veraenderung eines Werts als Integer
     * 
     * @param wert
     * @param zeile
     * @param spalte
     */
    public void setzeInteger(int wert, int zeile, int spalte) {
        setzeString("" + wert, zeile, spalte);
    }

    /**
     * Eingabe/Veraenderung eines Werts als Long
     * 
     * @param wert
     * @param zeile
     * @param spalte
     */
    public void setzeLong(long wert, int zeile, int spalte) {
        setzeString("" + wert, zeile, spalte);
    }

    /**
     * Eingabe/Veraenderung eines Werts als double
     * 
     * @param wert
     * @param zeile
     * @param spalte
     */
    public void setzeDouble(double wert, int zeile, int spalte) {
        setzeString("" + wert, zeile, spalte);
    }

    /**
     * Eingabe/Veraenderung eines Werts als boolean
     * 
     * @param wert
     * @param zeile
     * @param spalte
     */
    public void setzeBoolean(Boolean wert, int zeile, int spalte) {
        setzeString("" + wert, zeile, spalte);
    }

    /**
     * Testet, ob eine Zelle existiert
     * 
     * @param zeile
     * @param spalte
     * @return existiert
     */
    public boolean existiert(int zeile, int spalte) {
        if (zeile >= anzahlZeilen)
            return false;
        if (spalte >= csvZeilen.get(zeile).anzahlElemente())
            return false;
        return true;
    }

    /**
     * gibt der Wert des Attributs als String zurueck
     * 
     * @param zeile
     * @param spalte
     * @return String
     */
    public String leseString(int zeile, int spalte) {
        if (!existiert(zeile, spalte)) {
            return "";
        } else {
            return csvZeilen.get(zeile).getWert(spalte);
        }
    }

    /**
     * gibt den Wert des Attributs als ganze Zahl zurueck
     * 
     * <br/>
     * Falls das nicht geht, wird der error-Wert zurueckgegeben
     * 
     * @param zeile
     * @param spalte
     * @param error
     * @return Integer
     */
    public int leseInteger(int zeile, int spalte, int error) {
        if (!existiert(zeile, spalte))
            return error;
        String erg = leseString(zeile, spalte);
        int ret = error;
        try {
            ret = Integer.parseInt(erg);
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * gibt den Wert des Attributs als ganze Zahl (long) zurueck
     * 
     * <br/>
     * Falls das nicht geht, wird der error-Wert zurueckgegeben
     * 
     * @param zeile
     * @param spalte
     * @param error
     * @return Integer
     */
    public long leseLong(int zeile, int spalte, long error) {
        if (!existiert(zeile, spalte))
            return error;
        String erg = leseString(zeile, spalte);
        long ret = error;
        try {
            ret = Long.parseLong(erg);
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * gibt den Wert des Attributs als double-Zahl zurueck
     * 
     * <br/>
     * Falls das nicht geht, wird der error-Wert zurueckgegeben
     * 
     * @param zeile
     * @param spalte
     * @param error
     * @return als Double
     */
    public double leseDouble(int zeile, int spalte, double error) {
        if (!existiert(zeile, spalte))
            return error;
        String erg = leseString(zeile, spalte);
        double ret = error;
        try {
            ret = Double.parseDouble(erg);
        } catch (Exception e) {
        }

        return ret;
    }

    /**
     * gibt den Wert des Attributs als boolen-Wert zurueck
     * 
     * <br/>
     * Falls das nicht geht, wird der error-Wert zurueckgegeben
     * 
     * @param zeile
     * @param spalte
     * @param error
     * @return boolean
     */
    public boolean leseBoolean(int zeile, int spalte, boolean error) {
        if (!existiert(zeile, spalte))
            return error;
        String erg = leseString(zeile, spalte);
        boolean ret = error;
        try {
            ret = Boolean.parseBoolean(erg);
        } catch (Exception e) {
        }

        return ret;
    }

    /**
     * Loeschen aller Eintraege
     */
    public void clear() {
        csvZeilen.clear();
        strZeilen.clear();
        anzahlZeilen = 0;
        anzahlSpalten = 0;
    }

    /**
     * <strong>Schreiben der CSV-Datei</strong><br />
     * Dateiname bekannt, z.B. durch vorheriges lesen
     */
    public void schreibeCSVDatei() {
        if (dateiname != "")
            schreibeCSVDatei(dateiname, true);
    }

    /**
     * <strong>Schreiben der CSV-Datei. Der Dateiname wird fuer spaetere
     * Verwendung gespeichert. </strong>
     * 
     * <br />
     * Eine bestehende Datei wird bei ersetzen = true ueberschrieben Bei ersetzen
     * = false wird mit fehlermeldung abgebrochen
     */
    public boolean schreibeCSVDatei(String dateiname, boolean ersetzen) {
        this.dateiname = dateiname;
        File datei = new File(dateiname);
        if (datei.exists()) {
            if (ersetzen) {
                datei.delete();
                try {
                    datei.createNewFile();
                } catch (IOException e) {
                    JOptionPane.showConfirmDialog(null,
                        "Probleme beim Speichern !", "Speichern",
                        JOptionPane.OK_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } else {
                return false;
            }
        }

        try {
            FileWriter fw = new FileWriter(dateiname);
            BufferedWriter bfw = new BufferedWriter(fw);
            for (CSVDatensatz ds : csvZeilen) {
                bfw.write(ds.toString());
                bfw.newLine();
            }
            bfw.close();
            fw.close();
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, e.toString(),
                "Probleme beim Speichern", JOptionPane.OK_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    // public static void main(String[] args) {
    // CSVDatei datei = new CSVDatei("test-text.csv");
    //
    // for (int z = 0; z < datei.anzahlZeilen; z++) {
    // for (int s = 0; s < datei.anzahlSpalten; s++) {
    // System.out.print(datei.leseString(z, s) + "%");
    // }
    // System.out.println();
    // }
    //
    // // datei.setzeString("0-0", 0, 0);
    // // datei.setzeString("1-1", 1, 1);
    // // datei.setzeString("2-2", 2, 2);
    // // datei.setzeString("3-0", 3, 0);
    // // datei.setzeString("4-0", 4, 0);
    // datei.setzeString("12-12", 12, 12);
    //
    // datei.setMitFeldbegrenzer(false);
    // datei.schreibeCSVDatei("testEXC-s.csv", true);
    // System.out.println("Ergebnis:");
    // System.out.print(datei.toString());
    // }

}
