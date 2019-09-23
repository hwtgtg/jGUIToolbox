//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class B_RotAutoStart {

	public B_RotAutoStart() {

	}

	public static void main(String[] args) {
		Zeichnung.setzeFenstergroesse(1000, 800);
		Zeichnung.setzeRasterEin();
		B_RotAutoSport auto = new B_RotAutoSport(100,300);
		auto.bewegen();
	}

}
