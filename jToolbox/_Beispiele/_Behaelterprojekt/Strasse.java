
/**
 * Beschreiben Sie hier die Klasse Strasse.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Strasse
{
    Behaelter beh_strasse ;
    Strassenseite str1 ;
    Strassenseite str2 ;
    
    /**
     * Konstruktor für Objekte der Klasse Strasse
     */
    public Strasse()
    {
        beh_strasse = new Behaelter( 1200 , 600 );
        str1 = new Strassenseite();
        beh_strasse.hinzufuegen(str1.beh_strassenseite);
        
        str2 = new Strassenseite();
        str2.setzePosition(100,200);
        beh_strasse.hinzufuegen(str2.beh_strassenseite);
    }

}
