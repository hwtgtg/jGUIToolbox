//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

import java.util.Vector;

public class FarbeMerken_Dlg implements ITuWas {

	Rechteck anzeige;

	Taste taste;
	Dlg_Farbe farbe;

	public FarbeMerken_Dlg() {
		anzeige = new Rechteck(10, 10, 300, 80);
		StaticTools.setzeFarbe("farbig", "255,255,100");
		anzeige.setzeFarbe("farbig");
		taste = new Taste("Farbwaehler", 320, 10, 150, 80);
		taste.setzeLink(this);

	}

	public void tuWas(int ID) {
		farbe = new Dlg_Farbe("Neue Frabe", "farbig");

		farbe.setzeAlsFarbe("farbig");
		
		StaticTools.setzeFarbe("farbig", farbe.leseFarbe()); // Farbe "farbig" merken
		
		anzeige.setzeFarbe("farbig");
		
		Vector<String[]> farben = StaticTools.leseFarben();
		for (String[] farbe : farben) {
			System.out.println(farbe[0]+"="+farbe[1]);
		}
	}

}
