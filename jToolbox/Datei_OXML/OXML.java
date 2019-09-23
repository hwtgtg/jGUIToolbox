//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Vector;

import javax.swing.JOptionPane;

public class OXML {

    private OXML_Element root = null;
    private String dateiname = null;

    public static final String einruecken = "   ";

    public OXML_Element addElement(String elementbezeichnung) {
        root = OXML_Element.getElement(elementbezeichnung);
        root.bKommentar = false;
        return root;

    }

    public static OXML createEmptyOXML() {
        OXML writer = new OXML();
        writer.root = null;
        return writer;
    }

    public OXML_Element addElement(String elementbezeichnung, OXML_Element parent) {
        return root.addElement(elementbezeichnung, parent);
    }

    public void addText(OXML_Element element, String inhalt) {
        root.addText(element, inhalt);
    }

    public void addComment(OXML_Element element, String text) {
        root.addComment(element, text);
    }

    static String[] ZeichenOriginal = { "&", "<", ">" };
    static String[] ZeichenCode = { "&&", "&lt;", "&gt;" };

    public static String codeString(String text) {
        if (text == null)
            return "";
        String erg = new String(text);

        for (int i = 0; i < ZeichenOriginal.length; i++) {
            erg = erg.replaceAll(ZeichenOriginal[i], ZeichenCode[i]);
        }
        return erg;
    }

    public static String decodeString(String text) {
        String ein = text;
        String erg = "";

        int pos = 0;
        boolean erfolg = false;
        do {
            erfolg = false;
            for (int i = 0; i < ZeichenOriginal.length; i++) {
                if (ein.substring(pos).startsWith(ZeichenCode[i])) {
                    erg = erg + ZeichenOriginal[i];
                    erfolg = true;
                    pos = pos + ZeichenCode[i].length();
                    break;
                }
            }
            if (!erfolg) {
                erg = erg + ein.charAt(pos);
                pos++;
            }

        } while (pos < ein.length());

        return erg;
    }

    public boolean schreibeOXML() {
        return schreibeOXML(this.dateiname, true, true);
    }

    public boolean schreibeOXML(String dateiname) {
        return schreibeOXML(dateiname, true, true);
    }

    public boolean schreibeOXML(String dateiname, boolean ersetzen, boolean keinFEHLER) {

        this.dateiname = dateiname;
        File datei = new File(dateiname);
        if (datei.exists()) {
            if (ersetzen) {
                datei.delete();
                try {
                    datei.createNewFile();
                } catch (IOException e) {
                    if (!keinFEHLER) {
                        JOptionPane.showConfirmDialog(null, "Probleme beim Speichern !", "Speichern",
                            JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }
                    return false;
                }
            } else {
                return false;
            }
        }

        try {
            FileWriter fw = new FileWriter(dateiname);
            BufferedWriter bfw = new BufferedWriter(fw);

            bfw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            bfw.newLine();

            root.schreibeElement(bfw, "");

            bfw.close();
            fw.close();
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, e.toString(), "Probleme beim Speichern", JOptionPane.OK_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    class stackElement {
        public stackElement(OXML_Element element, String elementbezeichnung) {
            this.element = element;
            this.elementbezeichnung = elementbezeichnung;
        }

        OXML_Element element;
        String elementbezeichnung;
    }

    static String eingabezeile = "";
    static String untersuchungsString = null;

    enum Zustand {
        start, xmlGefunden, token, token_mit_Inhalt
    };

    enum AnalyseErg {
        NOK, nichts, xml_gefunden, token_gefunden, inhalt_gefunden, endetoken_gefunden, kommentar_gefunden
    };

    enum ParseErfolg {
        OK, NOK
    };

    private static AnalyseErg sucheXMLStart() {
        String test = new String(eingabezeile);
        eingabezeile = ""; // Eingabezeile verbrauchen
        if (test.startsWith("<?xml")) {
            return AnalyseErg.xml_gefunden;
        }
        return AnalyseErg.NOK;
    }

    private static AnalyseErg untersucheEingang() throws Exception {
        eingabezeile = eingabezeile.trim();
        if (eingabezeile.equalsIgnoreCase(""))
            return AnalyseErg.nichts;
        int posLT = eingabezeile.indexOf('<');
        if (posLT == -1) {
            untersuchungsString = OXML.decodeString(eingabezeile);
            eingabezeile = "";
            return AnalyseErg.inhalt_gefunden;
        } else if (posLT == 0) {
            int posKommmentar = eingabezeile.indexOf("<!--");
            if (posKommmentar == 0) {
                eingabezeile = eingabezeile.substring(4);
                posKommmentar = eingabezeile.indexOf("-->");
                if (posKommmentar >= 0) {
                    untersuchungsString = eingabezeile.substring(0, posKommmentar);
                } else {
                    throw new Exception("Kommentarklammer rechts fehlt");
                }
                if (eingabezeile.length() > posKommmentar) {
                    eingabezeile = eingabezeile.substring(posKommmentar + 3);
                } else {
                    eingabezeile = "";
                }
                return AnalyseErg.kommentar_gefunden;
            }
            AnalyseErg erg;
            int posEndetag = eingabezeile.indexOf("</");
            if (posEndetag == 0) {
                eingabezeile = (eingabezeile.substring(2)).trim();
                erg = AnalyseErg.endetoken_gefunden;
            } else {
                eingabezeile = (eingabezeile.substring(1)).trim();
                erg = AnalyseErg.token_gefunden;
            }
            posEndetag = eingabezeile.indexOf('>');
            if (posEndetag == -1) {
                throw new Exception("ElementEndezeichen fehlt");
            }
            if (posEndetag == 0) {
                throw new Exception("Kein Elementname");
            }
            untersuchungsString = (eingabezeile.substring(0, posEndetag)).trim();
            if (untersuchungsString.isEmpty()) {
                throw new Exception("Kein Elementname");
            }
            if (eingabezeile.length() > posEndetag) {
                eingabezeile = (eingabezeile.substring(posEndetag + 1)).trim();
                if (eingabezeile.length() == 0)
                    eingabezeile = "";
            } else {
                eingabezeile = "";
            }
            return erg;
        } else {
            // Inhalt vorhanden, dann Token
            untersuchungsString = OXML.decodeString(eingabezeile.substring(0, posLT));
            eingabezeile = eingabezeile.substring(posLT);
            return AnalyseErg.inhalt_gefunden;
        }
    }

    private static OXML leseOXMLStream(InputStream stream) {

        InputStreamReader reader = new InputStreamReader(stream);
        return leseOXMLStreamReader(reader);
    }

    public static OXML leseOXML(String dateiname) {
        File datei = new File(dateiname);
        FileReader reader = null;
        if (!datei.exists()) {
            return null;
        } else {
            String pfad = datei.getPath();
            try {
                reader = new FileReader(pfad);
            } catch (FileNotFoundException e) {
            }
            OXML OXML = leseOXMLStreamReader(reader);
            if (OXML != null) {
                OXML.dateiname = dateiname;
            }
            try {
                if (reader != null)
                    reader.close();
            } catch (Exception e) {
            }
            return OXML;
        }
    }

    private static OXML leseOXMLStreamReader(Reader stream) {

        Vector<stackElement> stack = new Vector<stackElement>();

        OXML OXML = null;
        OXML_Element element = null;

        ParseErfolg parseerfolg = ParseErfolg.NOK;
        {
            try {
                BufferedReader br = new BufferedReader(stream);

                Zustand zustand = Zustand.start;

                eingabezeile = "";
                while (!eingabezeile.isEmpty() || (eingabezeile = br.readLine()) != null) {

                    switch (zustand) {
                        case start:
                        if (sucheXMLStart() == AnalyseErg.xml_gefunden) {
                            zustand = Zustand.xmlGefunden;
                        }
                        break;
                        case xmlGefunden:
                        if (untersucheEingang() == AnalyseErg.token_gefunden) {
                            OXML = createEmptyOXML();
                            // root-Element erzeugen
                            element = OXML.addElement(untersuchungsString);
                            // Vorgänger von root ist null
                            // Damit beim Ende des 1. ELements auch etwas auf
                            // dem Stack ist, wird null draufgelegt
                            stack.add(OXML.new stackElement(null, ""));
                            zustand = Zustand.token;
                        }
                        break;
                        case token:
                        switch (untersucheEingang()) {
                            case inhalt_gefunden:
                            OXML.addText(element, untersuchungsString);
                            zustand = Zustand.token_mit_Inhalt;
                            break;
                            case kommentar_gefunden:
                            OXML.addComment(element, untersuchungsString);
                            break;
                            case token_gefunden:
                            stack.add(OXML.new stackElement(element, element.elementbezeichnung));
                            element = OXML.addElement(untersuchungsString, element);
                            break;
                            case endetoken_gefunden:
                            // letztes Element entfernen
                            if (!element.elementbezeichnung.equalsIgnoreCase(untersuchungsString)) {
                                throw new Exception("Falsches Ende-Elementname");
                            }
                            stackElement stackelement = stack.remove(stack.size() - 1);
                            element = stackelement.element;
                            break;
                            default:
                            break;
                        }
                        break;
                        case token_mit_Inhalt:
                        switch (untersucheEingang()) {
                            case kommentar_gefunden:
                            OXML.addComment(element, untersuchungsString);
                            break;
                            case token_gefunden:
                            stack.add(OXML.new stackElement(element, element.elementbezeichnung));
                            element = OXML.addElement(untersuchungsString, element);
                            zustand = Zustand.token;
                            break;
                            case endetoken_gefunden:
                            // letztes Element entfernen
                            if (!element.elementbezeichnung.equalsIgnoreCase(untersuchungsString)) {
                                throw new Exception("Falsches Ende-Elementname");
                            }
                            stackElement stackelement = stack.remove(stack.size() - 1);
                            element = stackelement.element;
                            break;
                            default:
                            break;
                        }
                        break;
                    }

                }
                if (br != null) {
                    br.close();
                }
                parseerfolg = ParseErfolg.OK;
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, e.toString(), "Probleme beim Lesen", JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e.toString(), "Probleme bei der Importanalyse:",
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
            } finally {

            }
            return (parseerfolg == ParseErfolg.OK) ? OXML : null;
        }

    }

    public OXML_Element getRoot() {
        return root;
    }

    public OXML_Element getElement(OXML_Element parent, String elementbezeichnung) {
        return root.getElement(parent, elementbezeichnung);
    }

    public Vector<OXML_Element> getElements(OXML_Element parent, String elementbezeichnung) {
        return root.getElements(parent, elementbezeichnung);
    }

}
