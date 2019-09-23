
/**
 * Beschreiben Sie hier die Klasse K_Farbwechel.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class K_Umschalttaste implements ITuWas {
    private Kreis anzeige ;
    private UmschaltTaste taste ;
    
    /**
     * Konstruktor für Objekte der Klasse K_Farbwechel
     */
    public K_Umschalttaste()
    {
        anzeige = new Kreis();
        anzeige.setzeMittelpunktUndRadius(100, 100 , 50);
        anzeige.setzeFarbe("blau");
        
        taste = new UmschaltTaste("druecken",100,60);
        taste.setzePosition(50, 160);
        taste.setzeHintergrundfarbe("rot");
                
        taste.setzeLink(this);
        taste.setzeID(0);
        
        }
    
    public void tuWas( int ID) {
        if( ID == 0 ){
            anzeige.setzeFarbe("rot");
            taste.setzeAusgabetext("gedrueckt");
        } else if ( ID == 1 ){
            anzeige.setzeFarbe("blau");
            taste.setzeAusgabetext("druecken");
        }
    }

}
