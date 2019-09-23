
/**
 * Beschreiben Sie hier die Klasse Bewegen.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Bewegen implements ITuWas 
{
    Kreis ball ;
    Rechteck wand_links ;
    Rechteck wand_rechts ;
    Taktgeber tik ;

    int v ;
    int pos ;

    Schieberegler regler  ;
    Rollbalken rb ;

    Ausgabe a_v;
    Ausgabe a_dt ;

    /**
     * Konstruktor für Objekte der Klasse Bewegen
     */
    public Bewegen()
    {
        wand_links = new Rechteck( 0,0,20,300);
        wand_links.setzeFarbe("blau");
        wand_rechts = new Rechteck(480,0,20,300);
        wand_rechts.setzeFarbe("blau");
        ball = new Kreis(20);
        ball.setzeMittelpunkt(50,150);
        ball.setzeFarbe("rot");
        pos = 50 ;
        v = 5 ;
        regler = new Schieberegler();
        regler.setzeDimensionen(100,310,300,40);
        regler.setzeBereich(-20, 20, v);
        regler.setzeLink(this,10);
        a_v = new Ausgabe("v", 410, 310, 50, 40);

        rb  = new Rollbalken();
        rb.setzeDimensionen(100,360,300,40);
        rb.setzeBereich(50,1000,100);
        rb.setzeLink( this , 30 );
        a_dt = new Ausgabe("dt", 410, 360, 50, 40);

        tik = new Taktgeber();

        tik.setzeZeitZwischenAktionen(100);
        tik.setzeLink(this, 20);
        tik.endlos();

    }

    public void tuWas( int ID ){
        if (ball.kollisionMit(wand_links)){
            v = Math.abs(v);
            regler.setzeWert(v);
        }
        if (ball.kollisionMit(wand_rechts)){
            v = -Math.abs(v);
            regler.setzeWert(v);
        }

        if ( ID == 10 ){
            v = regler.leseIntWert();
        }    

        if( ID == 20 ){
            pos += v ;
            ball.setzeMittelpunkt(pos, 150);
        }

        if( ID == 30 ){
            tik.stop();
            tik.setzeAnfangsZeitverzoegerung(rb.leseIntWert());
            tik.setzeZeitZwischenAktionen(rb.leseIntWert());
            tik.endlos();
        }
    }
}
