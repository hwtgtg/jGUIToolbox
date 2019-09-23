//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

import java.awt.Color;

public class EigneFarbeFarbdialog implements ITuWas {

	Rechteck anzeige;
	Schieberegler sR;
	Schieberegler sG;
	Schieberegler sB;
	Ausgabe aR;
	Ausgabe aG;
	Ausgabe aB;

	Taste taste;
	Dlg_Farbe farbe;

	public EigneFarbeFarbdialog() {
		anzeige = new Rechteck(10, 10, 300, 80);

		taste = new Taste("Farbwaehler", 320, 10, 150, 80);
		taste.setzeLink(this);

		sR = new Schieberegler('H', 10, 140, 300, 40);
		sR.setzeDimensionen(10, 140, 300, 40);
		sR.setzeFarbe("rot");
		aR = new Ausgabe("###", 320, 120, 100, 80);

		sG = new Schieberegler('H', 10, 180, 200, 40);
		sG.setzeDimensionen(10, 180, 300, 40);
		aG = new Ausgabe("###", 320, 160, 100, 80);
		sG.setzeFarbe("gruen");

		sB = new Schieberegler('H', 10, 220, 200, 40);
		sB.setzeDimensionen(10, 220, 300, 40);
		aB = new Ausgabe("###", 320, 200, 100, 80);
		sB.setzeFarbe("blau");

		sR.setzeBereich(0, 255, 100);
		sG.setzeBereich(0, 255, 100);
		sB.setzeBereich(0, 255, 100);
		while (true) {
			aR.setzeAusgabetext("" + sR.leseIntWert());
			aG.setzeAusgabetext("" + sG.leseIntWert());
			aB.setzeAusgabetext("" + sB.leseIntWert());
			StaticTools.setzeFarbe("beliebig", sR.leseIntWert(),
					sG.leseIntWert(), sB.leseIntWert());
			anzeige.setzeFarbe("beliebig");
			StaticTools.warte(100);
		}
	}

	@Override
	public void tuWas(int ID) {
		farbe = new Dlg_Farbe("Neue Farbe", "beliebig");

		Color newColor = farbe.leseFarbe();

		StaticTools.setzeFarbe("beliebig", newColor);
		sR.setzeWert(StaticTools.getColor("beliebig").getRed());
		sG.setzeWert(StaticTools.getColor("beliebig").getGreen());
		sB.setzeWert(StaticTools.getColor("beliebig").getBlue());
		anzeige.setzeFarbe("beliebig");

	}
}
