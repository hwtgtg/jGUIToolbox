//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * zwei abwechselnd blinkende Lampen
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (6.8.2008)
 */

public class Blinken_Taktgeber implements ITuWas{

    private Kreis lampeLi ;
    private Kreis lampeRe ;
    
    Taktgeber tik ;
    boolean bLiAn = true ;
    
    public Blinken_Taktgeber(){     
        lampeLi = new Kreis(100, 100,50);
        lampeLi.setzeFarbe("rot");
        lampeLi.fuellen();
        
        lampeRe = new Kreis(300, 100,50);
        lampeRe.setzeFarbe("rot");
        lampeRe.rand();
        
        tik = new Taktgeber();
        tik.setzeZeitZwischenAktionen(250);
        tik.setzeLink(this);
        tik.endlos();
        
    }
    
    
    public void tuWas( int ID ){

        if  (bLiAn == true){
            lampeLi.rand();
            lampeRe.fuellen();
            bLiAn = false ;
        } else {
            lampeLi.fuellen();
            lampeRe.rand();
            bLiAn = true ;
        }

    }
    
    public static void main(String[] args) {
        Blinken_Taktgeber p = new Blinken_Taktgeber();
        Zeichnung.setzeRasterEin();
    }
 
}
