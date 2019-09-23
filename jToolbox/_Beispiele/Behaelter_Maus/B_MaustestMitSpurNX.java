//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_MaustestMitSpurNX implements ITuWas {
	
	Kreis			aktion;
	Taktgeber		takt;
	MausBehaelter	obj;
	Rechteck		innen;
	
	SpurNX			spur;
	
	Ausgabe			id;
	Ausgabe			x;
	Ausgabe			y;
	Ausgabe			clicks;
	Ausgabe			taste;
	
	Ausgabe			shift;
	Ausgabe			ctrl;
	Ausgabe			alt;
	Ausgabe			rotation;
	
	public B_MaustestMitSpurNX() {
		aktion = new Kreis();
		aktion.setzeFarbe("rot");
		
		takt = new Taktgeber(this, 99);
		
		obj = new MausBehaelter(100, 200, 300, 300);
		obj.setzeMausereignisse((1 << MausBehaelter.CLICK)
				| (1 << MausBehaelter.PRESS) | (1 << MausBehaelter.RELEASE)
				| (1 << MausBehaelter.ENTER) | (1 << MausBehaelter.EXIT)
				| (1 << MausBehaelter.DRAGGED) | (1 << MausBehaelter.MOVED)
				| (1 << MausBehaelter.WHEEL));
		
		innen = new Rechteck(obj, 0, 0, 300, 300);
		
		int anzahlDerSpuren = 2;
		spur = new SpurNX(anzahlDerSpuren, 400, 200, 300, 300);
		spur.setzeDurchmesser(0, 20);
		spur.setzeFarbe(0, "blau");
		spur.setzeDurchmesser(1, 10);
		spur.setzeFarbe(1, "rot");
		spur.rand();
		spur.mitRahmen();
		
		id = new Ausgabe("id", 100, 0, 100, 50);
		x = new Ausgabe("x", 200, 0, 100, 50);
		y = new Ausgabe("y", 300, 0, 100, 50);
		
		clicks = new Ausgabe("Clicks", 400, 0, 100, 50);
		
		taste = new Ausgabe("Taste", 100, 100, 100, 50);
		
		shift = new Ausgabe("Shift", 200, 100, 100, 50);
		
		ctrl = new Ausgabe("CTRL", 300, 100, 100, 50);
		
		alt = new Ausgabe("ALT", 400, 100, 100, 50);
		
		rotation = new Ausgabe("Rot", 50, 150, 150, 50);
		
		obj.setzeLink(this, 0);
	}
	
	public void anzeigen(int ID) {
		id.setzeAusgabetext("ID " + ID);
		x.setzeAusgabetext("X:" + obj.getMX());
		y.setzeAusgabetext("Y:" + obj.getMY());
		clicks.setzeAusgabetext("CLC:" + obj.getClickCount());
		taste.setzeAusgabetext("T:" + obj.getButton());
		
		if (ID == MausBehaelter.PRESS) {
			innen.setzeFarbe("blau");
			spur.hinzufuegen(0, obj.getMX(), obj.getMY());		
			spur.loescheSpur(1);
		}
		if (ID == MausBehaelter.RELEASE) {
			innen.setzeFarbe("weiss");
			spur.hinzufuegen(1, obj.getMX(), obj.getMY());
			spur.loescheSpur(0);
		}
		if (ID == MausBehaelter.DRAGGED) {
			spur.hinzufuegen(0, obj.getMX(), obj.getMY());
		}
		if (ID == MausBehaelter.MOVED) {
			spur.hinzufuegen(1, obj.getMX(), obj.getMY());
		}
		
		if (obj.getShift())
			shift.setzeFarbe("rot");
		else
			shift.setzeFarbe("gelb");
		
		if (obj.getCtrl())
			ctrl.setzeFarbe("rot");
		else
			ctrl.setzeFarbe("gelb");
		
		if (obj.getAlt())
			alt.setzeFarbe("rot");
		else
			alt.setzeFarbe("gelb");
		rotation.setzeAusgabetext("Rot:" + obj.getRotation());
		
	}
	
	public int getRotation() {
		return obj.getRotation();
	}
	
	public void tuWas(int ID) {
		switch (ID) {
		case 99:
			aktion.fuellen();
			break;
		default:
			takt.einmal(100);
			aktion.rand();
			anzeigen(ID);
		}
	}
	
	public static void main(String[] args) {
		new B_MaustestMitSpurNX();
		
	}
}
