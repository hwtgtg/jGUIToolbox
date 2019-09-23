//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer JGUIToolbox
 * 
 * Landschaft 
 * 
 * @author Hans Witt
 * 
 * @version: 1.0 (6.8.2008)
 */

public class Landschaft {

	private Dreieck dach;
	private Rechteck haus;
	private Rechteck tuer;
	private Rechteck fenster;
	private Rechteck stamm;
	private Kreis krone;
	private Ellipse halbfenster ;

	public Landschaft() {
		
	  stamm = new Rechteck(70, 200);
          stamm.setzePosition(85, 200);
          stamm.setzeFarbe("dunkelgrau");

          krone = new Kreis(100);
          krone.setzeMittelpunkt(120, 150) ;
          krone.setzeFarbe("gruen");

          dach = new Dreieck(200, 100);
          dach.setzePosition(200, 100);
          dach.setzeFarbe("rot");

          haus = new Rechteck(200, 200);
          haus.setzePosition(200, 200);

          tuer = new Rechteck(50, 100);
          tuer.setzePosition(300, 300);
          tuer.setzeFarbe("blau");

          fenster = new Rechteck(50, 50);
          fenster.setzePosition(225, 300);
          fenster.setzeFarbe("gelb");
          
          halbfenster= new Ellipse(0 ,0 , 80 , 80 , 0 , 180 );
          halbfenster.setzePosition(260, 230 );
          halbfenster.setzeFarbe("grau");
	}

	public static void main(String[] args) {
		new Landschaft();
		Zeichenflaeche.setzeMitRaster(true);
	}
}
