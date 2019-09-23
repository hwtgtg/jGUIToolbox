
/**
 * Beschreiben Sie hier die Klasse Kommunikation.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Kommunikation implements ITuWas {
    private Kreis anzeige ;
    private Taste knopf ;
    /**
     * Konstruktor für Objekte der Klasse Kommunikation
     */
    public Kommunikation()
    {
        anzeige = new Kreis();
        anzeige.setzeMittelpunktUndRadius(100, 100 , 50);
        anzeige.setzeFarbe("blau");
        
        knopf = new Taste("T",60,60);
        knopf.setzePosition(70, 160);
        
        knopf.setzeLink(this);
        
    }
    
    public void tuWas( int ID) {
        anzeige.setzeFarbe("rot");        
    }

}
