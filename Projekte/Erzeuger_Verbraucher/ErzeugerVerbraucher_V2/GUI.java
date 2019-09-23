public class GUI {

	public static Behaelter bLager; // Hintergrund
	public static Behaelter bKisten; // Mitte
	public static Behaelter bFabriken; // Vordergrund ;
	public static Schieberegler eRegler;
	public static Schieberegler vRegler;

	public static int guiBreit = 1000;
	public static int guiHoch = 700;

	public GUI() {
		Zeichnung.setzeFenstergroesse(guiBreit + 50, guiHoch + 50);
		bLager = new Behaelter(guiBreit, guiHoch);
		bKisten = new Behaelter(guiBreit, guiHoch);
		bFabriken = new Behaelter(guiBreit, guiHoch);

		Ausgabe vA = new Ausgabe(bFabriken, "Verzoegerung", guiBreit - 200, guiHoch - 60,
				150, 30);
		vA.setzeAusrichtung(1);

		vRegler = new Schieberegler(bFabriken, 'H', guiBreit - 200,
				guiHoch - 30, 150, 20, 10, 1500, 800);

		Ausgabe eA = new Ausgabe(bFabriken, "Verzoegerung", 0, guiHoch - 60,
				150, 30);
		eA.setzeAusrichtung(1);

		eRegler = new Schieberegler(bFabriken, 'H', 0, guiHoch - 30, 150, 20,
				10, 1500, 900);
		

	}

}
