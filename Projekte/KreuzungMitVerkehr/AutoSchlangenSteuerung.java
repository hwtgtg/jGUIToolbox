//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class AutoSchlangenSteuerung implements ITuWas {
	
	AutoSchlange	as;
	Taktgeber		takt;
	int				anzahl	= 0;
	
	double			abstand	= 20;
	String			orientierung;
	Umgebung		u;
	
	public AutoSchlangenSteuerung(int anzahl, Umgebung u, String orientierung) {
		this.anzahl = anzahl;
		this.orientierung = orientierung;
		this.u = u;
		as = new AutoSchlange(anzahl, u, orientierung);
		for (int i = 0; i < anzahl; i++) {
			Auto a = as.gibAuto(i);
			if (a != null) a.takt.stop();// Die Steuerung uebernimmt
		}
		
		takt = new Taktgeber();
		takt.setzeZeitZwischenAktionen(70);
		takt.endlos();
		takt.setzeLink(this);
	}
	
	public void tuWas(int ID) {
		double posX;
		Auto a;
		int i;
		if (orientierung.equals("O")) {
			for (i = 0; i < anzahl; i++) {
				a = as.gibAuto(i);
				if (a != null) {
					posX = a.neuePosX();
					if (i != 0) {
						@SuppressWarnings("unused")
						double vorn = a.vorn();
						@SuppressWarnings("unused")
						double hinten = as.gibAuto(i - 1).hinten();
						
						if (a.vorn() > (as.gibAuto(i - 1).hinten() - abstand)) {
							posX = (as.gibAuto(i - 1).hinten() - abstand - a.laenge);
						}
					}
					a.setzePositionX(posX);
				}
			}
		} else {
			for (i = 0; i < anzahl; i++) {
				a = as.gibAuto(i);
				if (a != null) {
					posX = a.neuePosX();
					if (i != 0) {
						if (a.vorn() < (as.gibAuto(i - 1).hinten() + abstand)) {
							posX = (as.gibAuto(i - 1).hinten() + abstand);
						}
					}
					a.setzePositionX(posX);
				}
			}
		}

		for ( i = 0; i < anzahl; i++) {
			a = as.gibAuto(i);
			if (a != null) {
				posX = a.posX;
				if (orientierung.equals("O")) {
					if (posX > u.breite) {
						a = as.entferne(i);
						as.hinzufuegen(a);
						a.posX = -a.breite;
					}
				} else {
					if (posX < 0) {
						as.entferne(i);
						as.hinzufuegen(a);
						a.posX = u.breite;
					}
				}
			}
		}
	}
	
}
