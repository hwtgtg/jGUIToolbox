//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 * 
 * Ziffernblock mit Siebensegmentanzeige
 * 
 * @author Hans Witt
 * 
 * @version: 1.1 
 * 
 */

public class ZiffernblockMitAnzeige implements ITuWas {

    Segment8x out ;
    Ziffernblock ein ;

    @SuppressWarnings("unused")
    private long zahl = 0;
    @SuppressWarnings("unused")
    private int dpStelle = 0 ; // 0 = ganze Zahl n = n-te Stelle von rechts

    public ZiffernblockMitAnzeige() {

        out = new Segment8x(9,100); 
        ein = new Ziffernblock(); 

        ein.setLink(this, 0);
        ein.setzeGroesse(110);
        ein.setzePosition(200, 100 );
    }

    public void setzePosition(int x, int y) {
        out.setzePosition(x, y);
        ein.setzePosition(x+200, y+100);
    }

    public void tuWas(int ID) {
        // Verarbeiten
        double zahl = ein.wert() ;
        int i = ein.dpS() ;
        while ( i > 0 ){
            zahl = zahl / 10 ;
            i-- ;
        }		
        out.anzeige(zahl ,ein.dpS());
    }

    public void aktiviere() {
        ein.aktiviere();
    }

    public void deaktiviere() {
        ein.deaktiviere();
    }

	
    public static void main(String[] args) {
        ZiffernblockMitAnzeige zba = new ZiffernblockMitAnzeige();
        zba.aktiviere();
    }
}
