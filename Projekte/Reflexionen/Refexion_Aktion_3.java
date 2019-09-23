//%$JGUIToolboxEX$%//ID fuer Toolboxdateien


public class Refexion_Aktion_3 implements ITuWas {

	Taktgeber takt;

	Ball ball_1;
	Ball ball_2;
	Ball ball_3;

	Rechteck feld;
	int breit = 550;
	int hoch = 400;

	public Refexion_Aktion_3() {
		feld = new Rechteck();
		feld.setzeDimensionen(0, 0, breit, hoch);
		feld.setzeFarbe("gruen");

		ball_1 = new Ball();
		ball_1.radius = 20;
		ball_1.geschwindigkeit = 10;
		ball_1.setzeGroesse(ball_1.radius);
		ball_1.setzeFarbe("rot");

		ball_2 = new Ball();
		ball_2.radius = 30;
		ball_2.geschwindigkeit = 15;
		ball_2.setzeGroesse(ball_2.radius);
		ball_2.setzeFarbe("gelb");

		ball_3 = new Ball();
		ball_2.radius = 25;
		ball_2.geschwindigkeit = 20;
		ball_2.setzeGroesse(ball_2.radius);
		ball_2.setzeFarbe("lila");

		zufallsPosition(ball_1);
		zufallsPosition(ball_2);
		zufallsPosition(ball_3);

		zufallsRichtung(ball_1);
		zufallsRichtung(ball_2);
		zufallsRichtung(ball_3);

		takt = new Taktgeber();
		takt.setzeAnfangsZeitverzoegerung(100);
		takt.setzeZeitZwischenAktionen(15);
		
		takt.setzeLink(this, 0);
		takt.endlos();

	}

	public void zufallsPosition(Ball ball ) {
		ball.posX = StaticTools.gibZufall(breit - 2 * ball.radius);
		ball.posY = StaticTools.gibZufall(hoch - 2 * ball.radius);
		ball.setzePosition(ball.posX, ball.posY);
	}

	@SuppressWarnings("static-method")
	public void zufallsRichtung(Ball ball ) {
		ball.richtung = StaticTools.gibZufall() * Math.PI * 2;
		ball.dx = (int) (Math.cos(ball.richtung) * ball.geschwindigkeit);
		ball.dy = (int) (Math.sin(ball.richtung) * ball.geschwindigkeit);
	}


	public void tuWas(Ball ball ){
		ball.posX += ball.dx;
		ball.posY += ball.dy;

		spiegeln(ball);

		ball.setzePosition(ball.posX, ball.posY);
		
	}
	
	public void tuWas(int ID) {
		tuWas(ball_1);
		tuWas(ball_2);
		tuWas(ball_3);
	}

	public void spiegeln(Ball ball) {
		if (ball.posX < 0) {
			ball.posX = -ball.posX;
			ball.dx = -ball.dx;
		}
		if (ball.posY < 0) {
			ball.posY = -ball.posY;
			ball.dy = -ball.dy;
		}

		if (ball.posX > (breit - 2 * ball.radius)) {
			ball.posX = 2 * (breit - 2 * ball.radius) - ball.posX;
			ball.dx = -ball.dx;
		}
		if (ball.posY > (hoch - 2 * ball.radius)) {
			ball.posY = 2 * (hoch - 2 * ball.radius) - ball.posY;
			ball.dy = -ball.dy;
		}
	}

	public static void main(String[] args) {
		 new Refexion_Aktion_3();
	}

}
