//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class EignenFarbe {

	Rechteck anzeige;
	Schieberegler sR;
	Schieberegler sG;
	Schieberegler sB;
	Ausgabe aR ;
	Ausgabe aG ;
	Ausgabe aB ;
	

	public EignenFarbe() {
		anzeige = new Rechteck(10, 10, 300, 80);
		sR = new Schieberegler('H', 10, 140, 300, 40);
		sR.setzeDimensionen        (10, 140, 300, 40);
		sR.setzeFarbe("rot");
		aR = new Ausgabe("###", 320,120,100,80);
		
		sG = new Schieberegler('H', 10, 180, 200, 40);
		sG.setzeDimensionen    (10, 180, 300, 40);
		aG = new Ausgabe("###", 320,160,100,80);
		sG.setzeFarbe("gruen");
		
		sB = new Schieberegler('H', 10, 220, 200, 40);
		sB.setzeDimensionen    (10, 220, 300, 40);
		aB = new Ausgabe("###", 320,200,100,80);
		sB.setzeFarbe("blau");
		
		sR.setzeBereich(0, 255, 100);
		sG.setzeBereich(0, 255, 100);
		sB.setzeBereich(0, 255, 100);
		while (true) {
			aR.setzeAusgabetext(""+sR.leseIntWert());
			aG.setzeAusgabetext(""+sG.leseIntWert());
			aB.setzeAusgabetext(""+sB.leseIntWert());
			StaticTools.setzeFarbe("neueFarbe", sR.leseIntWert(), sG.leseIntWert(),
					sB.leseIntWert());
			anzeige.setzeFarbe("neueFarbe");
			StaticTools.warte(100);
		}
	}
	
}
