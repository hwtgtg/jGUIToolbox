//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * Die Klasse AutoAmpel.
 * Das Lichtsignal mit den benoetigten Methoden
 *  
 * @author Hans Witt 
 * @version 1.0 (12.07.2008)
 * @version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst		
 */
public class Ampel
{
    private Kreis rot;
    private Kreis gelb;
    private Kreis gruen;
    private RechteckMitRundenEcken rechteck ;

    /**
     * Erzeuge ein Exemplar der Klasse Ampel
     */
    public Ampel()
    {       
        rot = new Kreis(48);
        rot.setzeFarbe("rot");
        rot.setzePosition(2, 2);
        rot.sichtbarMachen();
        
        gelb = new Kreis(48);
        gelb.setzeFarbe("gelb");
        gelb.setzePosition( 2,102);
        gelb.sichtbarMachen();
        
        gruen  = new Kreis(48);
        gruen.setzeFarbe("gruen");
        gruen.setzePosition(2,202);
        gruen.sichtbarMachen();
        
        rechteck  = new RechteckMitRundenEcken(100,300,50);
        rechteck.setzeFarbe("blau");
        rechteck.setzePosition(0,0);
        rechteck.rand();
        rechteck.sichtbarMachen();

        rechteck.sichtbarMachen();
        
        alleAn();
    }

    public void setzePosition(int x, int y) {
        rot.setzePosition(2+x, 2+y); 
        gelb.setzePosition(2+x, 102+y);
        gruen.setzePosition(2+x, 202+y);
        rechteck.setzePosition(0+x, 0+y );
    }

    public void rotAn(){
        rot.fuellen();
    }

    public void rotAus(){
        rot.rand();
    }

    public void gelbAn(){
        gelb.fuellen();
    }
    
    public void gelbAus(){
        gelb.rand();
    }
    
    public void gruenAn(){
        gruen.fuellen();
    }

    public void gruenAus(){
        gruen.rand();
    }
    
    public void rot(){
        rotAn();
        gelbAus();
        gruenAus();
    }
    
    public void rotGelb(){
        rotAn();
        gelbAn();
        gruenAus();
    }
    
    public void gelb(){
        rotAus();
        gelbAn();
        gruenAus();
    }

    public void gruen(){
        rotAus();
        gelbAus();
        gruenAn();
    }
    
    public void ampelAus(){
        rotAus();
        gelbAus();
        gruenAus();
    }
    
    
    public void alleAn(){
        rotAn();
        gelbAn();
        gruenAn();
    }

    public static void main(String[] args) {
		new Ampel(); 
	}
    
}
