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

public class Blinken_Warten {

    private Kreis lampeLi ;
    private Kreis lampeRe ;
    
    public Blinken_Warten(){     
        lampeLi = new Kreis(100, 100,50);
        lampeLi.setzeFarbe("rot");
        
        lampeRe = new Kreis(300, 100,50);
        lampeRe.setzeFarbe("rot");
        
    }
    
    
    public void action(){
        while (true){
            lampeLi.fuellen();
            lampeRe.rand();
            StaticTools.warte(200);
            
            lampeLi.rand();
            lampeRe.fuellen();
            StaticTools.warte(200);
        }
    }
    
    public static void main(String[] args) {
        Blinken_Warten p = new Blinken_Warten();
        StaticTools.warte(1000);
        Zeichnung.setzeRasterEin();
        p.action();
    }
 
}
