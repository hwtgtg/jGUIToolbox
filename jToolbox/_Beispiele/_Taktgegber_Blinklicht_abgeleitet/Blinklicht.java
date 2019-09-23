
/**
 * Beschreiben Sie hier die Klasse Blinklicht.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Blinklicht extends Kreis implements ITuWas {

    boolean Li_an = true ;
    Taktgeber tik ;
    
    /**
     * Konstruktor fuer Hauptfenster
     */
    public Blinklicht() {
        super();
        installiereTaktgeber();
    }

    /**
     * Konstruktor fuer Hauptfenster
     * 
     * @param neuerRadius
     */
    public Blinklicht(int neuerRadius) {
        super(neuerRadius);
        installiereTaktgeber();
    }

    /**
     * Konstruktor fuer Hauptfenster
     * 
     * @param neuesX
     * @param neuesY
     * @param neuerRadius
     */
    public Blinklicht(int neuesX, int neuesY, int neuerRadius) {
        super( neuesX, neuesY, neuerRadius);
        installiereTaktgeber();
    }

    private void installiereTaktgeber(){
        tik = new Taktgeber();
        tik.setzeAnfangsZeitverzoegerung(500);
        tik.setzeZeitZwischenAktionen(500);
        tik.setzeLink(this);
    }

    public void setzeZeit( int ms ){
        stop();
        tik.setzeAnfangsZeitverzoegerung(ms);
        tik.setzeZeitZwischenAktionen(ms);
        start();
    }
    
    public void start(){
        tik.endlos();
    }
    
    public void stop(){
        tik.stop();
    }
    
    public void tuWas(int ID){
        if( Li_an == true ){
            Li_an = false ;
            rand();
        } else {
            Li_an = true ;
            fuellen();
        }
    }
    
	public static void main(String[] args) {
		Blinklicht b = new Blinklicht();
		b.setzeMittelpunktUndRadius(200,200, 100);
		b.setzeFarbe("blau");
		b.setzeZeit(200);
		Zeichnung.setzeRasterEin();
	}

}
