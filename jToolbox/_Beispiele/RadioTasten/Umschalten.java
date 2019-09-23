
/**
 * Beschreiben Sie hier die Klasse Umschalten.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Umschalten implements ITuWas
{
    Quadrat rb ;
    Quadrat gw ;

    RadioBehaelter r_rb ;
    RadioTaste rt_r ;
    RadioTaste rt_b ;

    RadioBehaelter r_gw ;
    RadioTaste rt_g ;
    RadioTaste rt_w ;

    /**
     * Konstruktor für Objekte der Klasse Umschalten
     */
    public Umschalten()
    {
        rb = new Quadrat( 50,50,100 );
        gw = new Quadrat( 450,50,100);
        
        r_rb = new RadioBehaelter( 20,200,200,60);
        rt_r = new RadioTaste(r_rb , "rot",5,1,70,60);
        rt_r.setzeLink(this , 0 );
        rt_b = new RadioTaste(r_rb , "blau",80,1,70,60);
        rt_b.setzeLink(this , 1 );        
        
        r_gw = new RadioBehaelter( 410,200,200,60);
        rt_g = new RadioTaste(r_gw , "gruen",5,1,80,60);
        rt_g.setzeLink(this , 3 );
        rt_w = new RadioTaste(r_gw , "weiss",90,1,80,60);
        rt_w.setzeLink(this , 4 );        

    }

    public void tuWas( int ID ){
        if( ID == 0 ) {
            rb.setzeFarbe("rot");
        } else if( ID == 1 ) {
            rb.setzeFarbe("blau");
        } else if( ID == 3 ) {
            gw.setzeFarbe("gruen");
        } else if( ID == 4 ) {
            gw.setzeFarbe("weiss");
        } ;
    }
}
