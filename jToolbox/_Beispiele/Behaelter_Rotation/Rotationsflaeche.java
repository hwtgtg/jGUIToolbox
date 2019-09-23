 

public class Rotationsflaeche extends BehaelterDrehbar implements ITuWas{

	private Rechteck flaeche;
	
	private Dreieck dre ; 
	
	public Taktgeber takt;

	public Rotationsflaeche(int x , int y ) {
		super(x, y, 150, 150);
		flaeche = new Rechteck(this, 25, 25, 100, 100);
		flaeche.setzeFarbe("gruen");
		
		dre = new Dreieck(this, 25, 25, 100, 100);
		dre.setzeFarbe("rot");
		dre.setzeAusrichtung("N");
		
		takt = new Taktgeber();
		takt.setzeZeitZwischenAktionen(50);
		takt.endlos();
		takt.setzeLink(this);

	}

	
	
	
	public Rotationsflaeche(IContainer behaelter,int x , int y ) {
		super(behaelter, x, y, 150, 150);
		flaeche = new Rechteck(this, 25, 25, 100, 100);
		flaeche.setzeFarbe("gruen");
		
		dre = new Dreieck(this, 25, 25, 100, 100);
		dre.setzeFarbe("rot");
		dre.setzeAusrichtung("N");
		
		takt = new Taktgeber();
		takt.setzeZeitZwischenAktionen(50);
		takt.endlos();
		takt.setzeLink(this);

	}
	
	int winkel = 0;
	int dwinkel = 10;

	@Override
	public void tuWas(int ID) {
		winkel += dwinkel;
		this.setzeWinkelGradmass(winkel);
	}
	
	public static void main(String[] args) {
		Zeichnung.setzeFenstergroesse(1000, 800);
		Zeichnung.setzeRasterEin();
		// Zeichnung.setzeRasterEin();
		// B_Auto_Rotationstest test =
		new Rotationsflaeche( 200,300 );
		
		
	}

	
	
}
