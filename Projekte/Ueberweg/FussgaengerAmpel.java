//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 * 
 * Fussgaengerampel
 * Teil des Projekts Ueberweg
 * Beispiel fuer Graphikobjekte
 * 
 * @author Hans Witt
 * 
 * @version: 1.0
 */
public class FussgaengerAmpel {


    private Kreis rot;
    private Kreis gruen;
    private Rechteck hinten ;

    /**
     * Erzeuge ein Exemplar der Klasse Ampel
     */
    public FussgaengerAmpel()
    {
        // nichts zu tun hier, alle Exemplarvariablen werden automatisch
        // mit null initialisiert.
        erzeuge();
    }

    /**
     * Zeichne die Ampel.
     */
    private void erzeuge()
    {
        rot = new Kreis(2,2,48);
        rot.setzeFarbe("rot");
        rot.sichtbarMachen();

        gruen  = new Kreis(2,102,48);
        gruen.setzeFarbe("gruen");
        gruen.sichtbarMachen();
        
        hinten  = new Rechteck(0,0,100,200);
        hinten.setzeFarbe("gruen");
        hinten.rand();
        hinten.sichtbarMachen();
    }

    // Horizontal verschieben
    public void horizontalVerschieben( int hor ){
        rot.horizontalBewegen(hor);
        gruen.horizontalBewegen(hor);
        hinten.horizontalBewegen(hor);
    }
        
    // Vertikal verschieben
    public void vertikalVerschieben( int ver ){
        rot.vertikalBewegen(ver);
        gruen.vertikalBewegen(ver);
        hinten.vertikalBewegen(ver);
    }
 
    private void rotAn(){
        rot.fuellen();
    }

    private void rotAus(){
        rot.rand();
    }

    private void gruenAn(){
        gruen.fuellen();
    }

    private void gruenAus(){
        gruen.rand();
    }
    
    public void rot(){
        rotAn();
        gruenAus();
    }

    public void gruen(){
        rotAus();
        gruenAn();
    }
    
    public void ampelAus(){
        rotAus();
        gruenAus();
    }
}
