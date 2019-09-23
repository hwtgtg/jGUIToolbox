//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Refexion_Aktion_N implements ITuWas {

	Taktgeber takt;

	Ball baelle[];

	int anzahl = 40;

	Rechteck feld;
	int breit = 550;
	int hoch = 400;

	public Refexion_Aktion_N() {
		feld = new Rechteck();
		feld.setzeDimensionen(0, 0, breit, hoch);
		feld.setzeFarbe("gruen");

		baelle = new Ball[anzahl];

		for (int i = 0; i < anzahl; i++) {
			baelle[i] = new Ball();
			baelle[i].radius = 10 + StaticTools.gibZufall(10);
			baelle[i].geschwindigkeit = 5 + StaticTools.gibZufall(10);
			baelle[i].setzeGroesse(baelle[i].radius);
			baelle[i].setzeFarbe("rot");
			zufallsPosition(baelle[i]);
			zufallsRichtung(baelle[i]);
		}

		takt = new Taktgeber();
		takt.setzeAnfangsZeitverzoegerung(100);
		takt.setzeZeitZwischenAktionen(20);

		takt.setzeLink(this, 0);
		takt.endlos();
	}

	public void zufallsPosition(Ball ball) {
		ball.posX = StaticTools.gibZufall(breit - 2 * ball.radius);
		ball.posY = StaticTools.gibZufall(hoch - 2 * ball.radius);
		ball.setzePosition(ball.posX, ball.posY);
	}

	@SuppressWarnings("static-method")
	public void zufallsRichtung(Ball ball) {
		ball.richtung = StaticTools.gibZufall() * Math.PI * 2;
		ball.dx = (int) (Math.cos(ball.richtung) * ball.geschwindigkeit);
		ball.dy = (int) (Math.sin(ball.richtung) * ball.geschwindigkeit);
	}

	public void tuWas(Ball ball) {
		ball.posX += ball.dx;
		ball.posY += ball.dy;
		spiegeln(ball);
		ball.setzePosition(ball.posX, ball.posY);
	}

	public void tuWas(int ID) {
		for (int i = 0; i < anzahl; i++) {
			tuWas(baelle[i]);
		}
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
		 new Refexion_Aktion_N();
	}

}
