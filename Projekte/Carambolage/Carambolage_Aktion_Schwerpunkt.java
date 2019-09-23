//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Carambolage_Aktion_Schwerpunkt implements ITuWas {
	
	Taktgeber			takt;
	
	Carambolage_Ball	ball_1;
	Carambolage_Ball	ball_2;
	Carambolage_Ball	ball_Schwerpunkt;
	
	Schieberegler		sb_1;
	Schieberegler		sb_2;
	Taste				richtung;
	
	Linie				verbindung;
	
	int					anzahl	= 3;
	
	Rechteck			feld;
	static int			breit	= 400;
	static int			hoch	= 400;
	
	int					sx;
	int					sy;
	
	public Carambolage_Aktion_Schwerpunkt() {
		feld = new Rechteck();
		feld.setzeDimensionen(0, 0, breit, hoch);
		feld.setzeFarbe("gruen");
		
		ball_1 = new Carambolage_Ball();
		ball_1.setzeGeschwindigkeit(8);
		ball_1.setzeFarbe("rot");
		zufallsPosition(ball_1);
		zufallsRichtung(ball_1);
		
		sb_1 = new Schieberegler('H', 0, hoch, breit, 30, 5, 60, 20);
		sb_1.setzeFarbe("rot");
		sb_1.setzeLink(this, 1);
		ball_1.setzeRadius(sb_1.leseDoubleWert());
		
		ball_2 = new Carambolage_Ball();
		ball_2.setzeGeschwindigkeit(8);
		ball_2.setzeFarbe("blau");
		zufallsPosition(ball_2);
		zufallsRichtung(ball_2);
		sb_2 = new Schieberegler('H', 0, hoch + 30, breit, 30, 5, 60, 10);
		sb_2.setzeFarbe("blau");
		sb_2.setzeLink(this, 2);
		ball_2.setzeRadius(sb_2.leseDoubleWert());
		
		verbindung = new Linie(ball_1.leseMittelpunktX(), ball_1
				.leseMittelpunktY(), ball_2.leseMittelpunktX(), ball_2
				.leseMittelpunktY());
		verbindung.setzeFarbe("gelb");
		
		ball_Schwerpunkt = new Carambolage_Ball();
		ball_Schwerpunkt.setzeRadius(6);
		ball_Schwerpunkt.setzeFarbe("schwarz");
		berechneSchwerpunkt();
		ball_Schwerpunkt.setzeMittelpunkt(sx, sy);
		takt = new Taktgeber();
		takt.setzeAnfangsZeitverzoegerung(100);
		takt.setzeZeitZwischenAktionen(50);
		takt.setzeLink(this, 0);
		takt.endlos();
		
		richtung = new Taste("", hoch, breit, 30, 30);
		richtung.setzeSchriftgroesse(8);
		richtung.setzeSchriftfarbe("gelb");
		richtung.setzeHintergrundfarbe("lila");
		richtung.setzeLink(this, 10);
		
	}
	
	@SuppressWarnings("static-method")
	public void zufallsPosition(Carambolage_Ball ball) {
		ball.setzeMittelpunkt(StaticTools.gibZufall(breit - 2
				* ball.leseRadius()), StaticTools.gibZufall(hoch - 2
				* ball.leseRadius()));
	}
	
	@SuppressWarnings("static-method")
	public void zufallsRichtung(Carambolage_Ball ball) {
		ball.setzeRichtung((int) (StaticTools.gibZufall() * 180));
	}
	
	public void berechneSchwerpunkt() {
		sx = (int) ((ball_1.leseMittelpunktX() * ball_1.leseMasse() + ball_2
				.leseMittelpunktX()
				* ball_2.leseMasse()) / (ball_1.leseMasse() + ball_2
				.leseMasse())

		);
		sy = (int) ((ball_1.leseMittelpunktY() * ball_1.leseMasse() + ball_2
				.leseMittelpunktY()
				* ball_2.leseMasse()) / (ball_1.leseMasse() + ball_2
				.leseMasse())

		);
	}
	
	public void tuWas(int ID) {
		switch (ID) {
		case 0:

			ball_1.bewegung();
			ball_2.bewegung();
			
			verbindung.setzeEndpunkte(ball_1.leseMittelpunktX(), ball_1
					.leseMittelpunktY(), ball_2.leseMittelpunktX(), ball_2
					.leseMittelpunktY());
			berechneSchwerpunkt();
			ball_Schwerpunkt.setzeMittelpunkt(sx, sy);
			break;
		case 1:
			ball_1.setzeRadius(sb_1.leseDoubleWert());
			break;
		case 2:
			ball_2.setzeRadius(sb_2.leseDoubleWert());
			break;
		
		case 10:
			zufallsPosition(ball_1);
			zufallsPosition(ball_2);
			break;
		}
	}
	
	public static void main(String[] args) {
		 new Carambolage_Aktion_Schwerpunkt();
	}
	
}
