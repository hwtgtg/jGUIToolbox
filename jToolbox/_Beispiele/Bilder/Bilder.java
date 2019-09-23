
/**
 * Beschreiben Sie hier die Klasse Bilder.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Bilder
{

    Bild bildanzeige ;
    Bilddatei datei ;
    
    Bild BueberDatei ;
    
    Taste tst ;
    
    /**
     * Konstruktor für Objekte der Klasse Bilder
     */
    public Bilder()
    {
        bildanzeige = new Bild( "apfel.gif");
   
        bildanzeige.setzePosition(200, 200);
        
        datei = new Bilddatei("erdbeere.gif");
        datei.einpassen(60,60);
        
        BueberDatei = new Bild(datei);
        BueberDatei.setzePosition(400,200);
        
        tst = new Taste("",50,50,100,100);
        tst.setzeIcon(datei);
    }

}
