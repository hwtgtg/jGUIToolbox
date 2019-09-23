
/**
 * Beschreiben Sie hier die Klasse Strasse.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Strassenseite
{
    public Behaelter beh_strassenseite ;
    private BehaelterHaus h1 ;
    private BehaelterHaus h2 ;
    private BehaelterHaus h3 ;
    private BehaelterHaus h4 ;
    
    /**
     * Konstruktor für Objekte der Klasse Strasse
     */
    public Strassenseite()
    {
        beh_strassenseite = new Behaelter(1200,400);

        h1 = new BehaelterHaus();
        beh_strassenseite.hinzufuegen(h1.beh_haus);

        h2 = new BehaelterHaus();
        h2.setzePosition(250,0);
        beh_strassenseite.hinzufuegen(h2.beh_haus);

        h3 = new BehaelterHaus();
        h3.setzePosition(500,0);
        beh_strassenseite.hinzufuegen(h3.beh_haus);
        
        h4 = new BehaelterHaus();
        h4.setzePosition(750,0);
        beh_strassenseite.hinzufuegen(h4.beh_haus);
    }
    
    public void setzePosition(int x , int y ){
        beh_strassenseite.setzePosition(x,y);
    }

}
