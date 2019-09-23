//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Dual_Ziffern {
	
	Behaelter	zeichen;
	Kreis[]		zf;
	int			stellen	= 5;
	int radius = 20 ;
	
	public Dual_Ziffern(IContainer behaelter, int stellen) {
		zeichen = new Behaelter(behaelter, 100, 100, stellen * 2 * radius * 12/5 , 50 );
		zf = new Kreis[stellen];
		this.stellen = stellen;
		for (int i = 0; i < stellen; i++) {
			zf[i] = new Kreis(zeichen, 0, 0, 20);
		}
		setzeZifferPosition(0, 0);
	}
	
	public void setzeZifferPosition(int neuesX, int neuesY) {
		int xPos = neuesX;
		int yPos = neuesY;
		for (int i = 0; i < stellen; i++) {
			zf[i].setzePosition(xPos + radius * 12 / 5 * (stellen - i - 1), yPos);
		}
	}
	
	public void setzeZiffer(int ziffer) {
		int a = ziffer;
		for (int i = 0; i < stellen; i++) {
			if ( (a%2) != 0 ){
				zf[i].setzeFarbe("rot");
			} else {
				zf[i].setzeFarbe("hellgrau");
			}
			a = a / 2 ;	
		}
	}
	
	public void setzePosition(int neuesX, int neuesY) {
		zeichen.setzePosition(neuesX, neuesY);
	}
	
	public static void main(String[] args) {
		Dual_Ziffern u = new Dual_Ziffern(Zeichnung.gibZeichenflaeche(),8);
		
		for (int i = 0; i < 256; i++) {
			u.setzePosition( 0, i*1 );
			u.setzeZiffer(i);
			StaticTools.warte(100);
		}
		
		// u.setzePosition(200,200);
		// Zeichnung.setzeRasterEin();
	}
	
}
