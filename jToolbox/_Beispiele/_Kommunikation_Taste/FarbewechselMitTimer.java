
/**
 * Beschreiben Sie hier die Klasse FarbewechselMitTimer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class FarbewechselMitTimer implements ITuWas {
    private Kreis anzeige ;
    private Taste knopf_rot ;
    private Taste knopf_blau ;
    
    private Taktgeber ticker ;
    
    /**
     * Konstruktor für Objekte der Klasse FarbewechselMitTimer
     */
    public FarbewechselMitTimer()
    {
        anzeige = new Kreis();
        anzeige.setzeMittelpunktUndRadius(100, 100 , 50);
        anzeige.setzeFarbe("blau");
        
        knopf_rot = new Taste("rot",60,60);
        knopf_rot.setzePosition(40, 160);
        knopf_rot.setzeHintergrundfarbe("rot");
        
        knopf_rot.setzeLink(this);
        knopf_rot.setzeID(0);
        
        knopf_blau = new Taste("blau",60,60);
        knopf_blau.setzePosition(100, 160);
        knopf_blau.setzeHintergrundfarbe("blau");
        
        knopf_blau.setzeLink(this);
        knopf_blau.setzeID(1);
        
        ticker = new Taktgeber();
        ticker.setzeAnfangsZeitverzoegerung(3000);
        ticker.setzeZeitZwischenAktionen(3000);
        ticker.setzeLink(this,3);
        
        ticker.endlos();
        
    }
    
    public void tuWas( int ID) {
        if( ID == 0 ){
            anzeige.setzeFarbe("rot");
        } else if ( ID == 1 ){
            anzeige.setzeFarbe("blau");
        } else if ( ID == 3 ){
            anzeige.setzeFarbe("gruen");
        }
    }

}
