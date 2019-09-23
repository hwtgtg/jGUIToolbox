//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * @author Witt
 * 
 */
public class Pumpe_Schwungrad implements ITuWas {
	
	Behaelter	schwungrad;
	Kreis		radA;
	Kreis		radI;
	Kreis		mitte;
	Kreis		zapfen;
	int			kurbelRadius	= 150;
	int			dx				= 0;
	int			dy				= 0;
	Kreis		zapfen_2;
	int			kurbelRadius_2	= 100;
	
	int			dWinkel_2		= 90;
	int			dx_2			= 0;
	int			dy_2			= 0;
	
	Linie[]		speichen;
	int			speichenanzahl	= 2;
	
	int			radius			= 200;
	
	Taktgeber	takt;
	
	int			winkel			= 0;
	
	int			mitteX			= 200;
	int			mitteY			= 200;
	
	/**
	 * 
	 */
	public Pumpe_Schwungrad() {
		
		schwungrad = new Behaelter(0, 0, 1,1);
		//schwungrad.setzeMitRaster(true);
		mitteX = radius;
		mitteY = radius;

		radA = new Kreis(schwungrad);
		radA.setzeFarbe("schwarz");

		radI= new Kreis(schwungrad);
		radI.setzeFarbe("weiss");
		
		
		speichen = new Linie[speichenanzahl];
		for (int i = 0; i < speichenanzahl; i++) {
			int liX = (int) (Math.cos((winkel + 180 * i / speichenanzahl)
					* Math.PI / 180) * radius);
			int liY = (int) (Math.sin((winkel + 180 * i / speichenanzahl)
					* Math.PI / 180) * radius);
			speichen[i] = new Linie(schwungrad, mitteX - liX, mitteY - liY,
					mitteX + liX, mitteY + liY);
			speichen[i].setzeFarbe("dunkelgrau");
		}
		
		mitte = new Kreis(schwungrad);
		mitte.setzeFarbe("schwarz");
		
		zapfen = new Kreis(schwungrad);
		zapfen.setzeRadius(8);
		zapfen.setzeFarbe("schwarz");
		
		zapfen_2 = new Kreis(schwungrad);
		zapfen_2.setzeFarbe("schwarz");
		zapfen_2.setzeRadius(6);

		setzeRadius(200);
		setzeWinkel(0);
		
		takt = new Taktgeber(this, 99);
		takt.setzeAnfangsZeitverzoegerung(1000);
		takt.setzeZeitZwischenAktionen(25);
		
	}
	
	public void setzeWinkel(int winkel) {
		while (winkel > 360) {
			winkel -= 360;
		}
		dx = (int) (Math.cos(winkel * Math.PI / 180) * kurbelRadius);
		// Koordinatensystem in y-Richtung nach unten !
		dy = (int) (Math.sin(winkel * Math.PI / 180) * kurbelRadius);
		zapfen.setzeMittelpunkt(radius + dx, radius + dy);
		
		dx_2 = (int) (Math.cos((winkel + dWinkel_2) * Math.PI / 180) * kurbelRadius_2);
		// Koordinatensystem in y-Richtung nach unten !
		dy_2 = (int) (Math.sin((winkel + dWinkel_2) * Math.PI / 180) * kurbelRadius_2);
		zapfen_2.setzeMittelpunkt(radius + dx_2, radius + dy_2);
		
		for (int i = 0; i < speichenanzahl; i++) {
			int liX = (int) (Math.cos((winkel + 180 * i / speichenanzahl)
					* Math.PI / 180) * radius);
			int liY = (int) (Math.sin((winkel + 180 * i / speichenanzahl)
					* Math.PI / 180) * radius);
			speichen[i].setzeEndpunkte(radius - liX, radius - liY,
					radius + liX, radius + liY);
		}
	}
	
	public int leseDx() {
		return dx ;
	}
	
	public int leseDy() {
		return dy ;
	}
	
	public int leseDx_2() {
		return dx_2 ;
	}
	
	public int leseDy_2() {
		return dy_2 ;
	}
	
	public void setzeMittelpunkt(int neuesX, int neuesY) {
		mitteX = neuesX;
		mitteY = neuesY;
		schwungrad.setzeDimensionen(mitteX - radius, mitteY - radius,2*radius,2*radius);
	}
	
	public void setzeRadius(int neuerRadius) {
		radius = neuerRadius;
		kurbelRadius = radius * 3 / 4;
		kurbelRadius_2 = radius / 2;
		
		radA.setzeMittelpunktUndRadius(radius, radius, radius);
		radI.setzeMittelpunktUndRadius(radius, radius, radius-6);
		mitte.setzeMittelpunktUndRadius(radius, radius, 8);
		schwungrad.setzeDimensionen(mitteX - radius, mitteY - radius,2*radius,2*radius);
		setzeWinkel(winkel);
	}
	
	public void setzeKurbelradius(int neuerRadius) {
		kurbelRadius = neuerRadius;
		setzeWinkel(winkel);
	}
	
	
	public void tuWas(int ID) {
		winkel += 5;
		setzeWinkel(winkel);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pumpe_Schwungrad s = new Pumpe_Schwungrad();
		s.setzeMittelpunkt(400, 300);
		s.setzeRadius(150);
		s.takt.endlos();
		
		Zeichnung.setzeFenstergroesse(1000, 600);
		// Zeichnung.setzeRasterEin();
		
	}
	
}
