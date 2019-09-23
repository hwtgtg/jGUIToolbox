//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * zwei abwechselnd blinkende Lampen
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (12.12.2008)
 */

public class B_BlinkenMitSound implements ITuWas {
    
    private Kreis   lampeLi;
    private Kreis   lampeRe;
    
    Audio           sound;
    Audio           sound_bell;
    
    Taktgeber       takt;
    
    public B_BlinkenMitSound() {
        lampeLi = new Kreis(100, 100, 50);
        lampeLi.setzeFarbe("rot");
        
        lampeRe = new Kreis(300, 100, 50);
        lampeRe.setzeFarbe("rot");
        
        takt = new Taktgeber(this, 0);
        takt.setzeZeitZwischenAktionen(800);
        sound = new Audio("explosion_m.wav");
        sound_bell = new Audio("doumbek-m.wav");
    }
    
    public void playexplosion() {
        sound.play();
    }

    public void play_bell() {
        sound_bell.play();
    }
    
    boolean ein = true;
    int anzahl = 0 ; 
    public void action() {
        anzahl++ ;
        ein = !ein;
        if (ein) {
            lampeLi.fuellen();
            lampeRe.rand();
        } else {
            lampeLi.rand();
            lampeRe.fuellen();
        }
        play_bell();
        if( anzahl > 5 ){
            takt.stop();
            playexplosion();
        }
    }
    
    public void tuWas(int ID) {
        action();
    }
    
    public void starten(){
        takt.endlos();
    }
    
    public static void main(String[] args) {
        B_BlinkenMitSound p = new B_BlinkenMitSound();
        p.starten();
    }
    
}
