//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_Ball_Schieberegler implements ITuWas {
	
	Taktgeber		takt;
	B_Ball			ball;
	Schieberegler	sb;
	Rechteck		feld;
	
	static int		breit	= 550;
	static int		hoch	= 400;
	
	public B_Ball_Schieberegler() {
		feld = new Rechteck();
		feld.setzeDimensionen(0, 0, breit, hoch);
		feld.setzeFarbe("gruen");
		
		ball = new B_Ball();
		ball.setzeGeschwindigkeit(15);
		ball.setzeFarbe("rot");
		zufallsPosition(ball);
		zufallsRichtung(ball);
		ball.rBreit = breit ;
		ball.rHoch = hoch ;
		
		sb = new Schieberegler('H', 100, 450, 300, 40, 5, 100, 30);
		sb.setzeFarbe("rot");
		sb.setzeLink(this, 1);
		ball.setzeRadius(sb.leseDoubleWert());
		
		takt = new Taktgeber();
		takt.setzeAnfangsZeitverzoegerung(100);
		takt.setzeZeitZwischenAktionen(50);
		takt.setzeLink(this, 0);
		takt.endlos();
	}
	
	@SuppressWarnings("static-method")
	public void zufallsPosition(B_Ball ball) {
		ball.setzeMittelpunkt(StaticTools.gibZufall(breit - 2
				* ball.leseRadius()), StaticTools.gibZufall(hoch - 2
				* ball.leseRadius()));
	}
	
	@SuppressWarnings("static-method")
	public void zufallsRichtung(B_Ball ball) {
		ball.setzeRichtung((int) (StaticTools.gibZufall() * 360 ));
	}
	
	public void tuWas(int ID) {
		switch (ID) {
		case 0:
			ball.bewegung();
			break;
		case 1:
			ball.setzeRadius(sb.leseDoubleWert());
			break;
		default:

		}
	}
	
	public static void main(String[] args) {
		 new B_Ball_Schieberegler();
	}
	
}
