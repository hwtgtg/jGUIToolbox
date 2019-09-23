import java.util.Vector;
/**
 * Beschreiben Sie hier die Klasse Beispiel_XMLDatei.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Beispiel_XMLDatei
{

    /**
     * Konstruktor für Objekte der Klasse Beispiel_XMLDatei
     */
    public Beispiel_XMLDatei()
    {

    }

    public static void lesen(String dateiname){
        OXML oxml;
        OXML_Element root ;
        TBConsole cs = new TBConsole() ;

        oxml = OXML.leseOXML(dateiname);
        root = oxml.getRoot();
        cs.println("Wurzelelement: " + root.getBezeichnung());

        OXML_Element knoten_O = oxml.getElement(root, "Knoten_1");
        cs.println("Inhalt Element Knoten_1: " + knoten_O.getInhalt() );
        OXML_Element knoten_U = knoten_O.getElement(knoten_O, "Knoten_1_1");
        cs.println("Inhalt Unter-Element Knoten_1_1: " + knoten_U.getInhalt() );

        knoten_O = oxml.getElement(root, "Knoten_2");
        cs.println("Inhalt Element Knoten_2: " + knoten_O.getInhalt() );

        knoten_O = oxml.getElement(root, "Knoten_3");
        cs.println("Inhalt Element Knoten_3: " + knoten_O.getInhalt() );

        Vector<OXML_Element> module = oxml.getElements(knoten_O, "Unterknoten");
        for (OXML_Element aktuellesKnoten : module) {
            cs.println("   Inhalt Unterknoten von Knoten_3: " + aktuellesKnoten.getInhalt() );
        }

    }

    public static void schreiben(String dateiname){
        OXML oxml;
        OXML_Element root ;
        OXML_Element knoten_1 ;
        OXML_Element knoten_2 ;
        OXML_Element knoten_3 ;
        OXML_Element knoten_4 ;

        oxml = OXML.createEmptyOXML();
        root = oxml.addElement("Wurzel"); // Ein Wurzelelement!
        knoten_1 = root.addElement("Knoten_1",root);
        knoten_1.addText(knoten_1, "Erstes Element");

        knoten_2 = root.addElement("Knoten_1_1",knoten_1);
        knoten_1.addText(knoten_2, "Erstes Unterelement");

        knoten_3 = root.addElement("Knoten_2",root);
        knoten_3.addText(knoten_3, "Zweites Element");

        knoten_4 = root.addElement("Knoten_4",knoten_3);
        knoten_3.addText(knoten_4, "Zweites Unterelement");

        knoten_4 = root.addElement("Knoten_5",knoten_3);
        knoten_4.addText(knoten_4, "UE5 in E2"); 

        knoten_4 = knoten_3.addElement("PI",knoten_3);
        knoten_4.addDouble(knoten_4 , 3.14159265359 );

        knoten_4 = root.addElement("Knoten_6",knoten_3);
        knoten_4.addText(knoten_4, "UE6 in E2"); 

        knoten_1 = knoten_4.addElement("UE_7",knoten_4);
        knoten_1.addComment(knoten_1 , "Kommentar zum Element");

        knoten_3 = root.addElement("Knoten_3",root);
        knoten_3.addText(knoten_3, "Drittes Element");
        // Mehrere Knoten mit der Bezeichnung Unterknoten im Knoten_3
        knoten_4 = knoten_3.addElement("Unterknoten",knoten_3);
        knoten_3.addText(knoten_4, "Unterknoten 1");

        knoten_4 = knoten_3.addElement("Unterknoten",knoten_3);
        knoten_3.addText(knoten_4, "Unterknoten 2");

        knoten_4 = knoten_3.addElement("Unterknoten",knoten_3);
        knoten_3.addText(knoten_4, "Unterknoten 3");

        knoten_4 = knoten_3.addElement("Unterknoten",knoten_3);
        knoten_3.addText(knoten_4, "Unterknoten 4");

        oxml.addComment(root, " Ende ");
        oxml.schreibeOXML(dateiname);
    }

    public static void lesen(){
        lesen("TestXML.oxml");
    }

    public static void schreiben(){
        schreiben("TestXML.oxml");
    }

}
