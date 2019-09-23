//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
import java.awt.geom.Rectangle2D;

public class PersonenSchlangenSteuerung implements ITuWas {
	
	PersonenSchlange	ps;
	Taktgeber			takt;
	int					anzahl	= 0;
	
	double				abstand	= 1;
	Umgebung			u;
	
	public PersonenSchlangenSteuerung(int anzahl, Umgebung u,
			String orientierung) {
		this.anzahl = anzahl;
		this.u = u;
		ps = new PersonenSchlange(anzahl, u, orientierung);
		for (int i = 0; i < anzahl; i++) {
			@SuppressWarnings("unused")
			Person p = ps.gibPerson(i);
		}
		
		takt = new Taktgeber();
		takt.setzeZeitZwischenAktionen(70);
		takt.endlos();
		takt.setzeLink(this);
	}
	
	public PersonenSchlangenSteuerung(int anzahl, Umgebung u) {
		this.anzahl = anzahl;
		this.u = u;
		ps = new PersonenSchlange(anzahl);
		
		takt = new Taktgeber();
		takt.setzeZeitZwischenAktionen(200);
		takt.endlos();
		takt.setzeLink(this);
	}
	
	void hinzufuegen(Person p) {
		if (p != null) {
			ps.hinzufuegen(p);
		}
	}
	
	void sucheNeuePosition(Person p) {
		Person p2 = null;
		boolean erfolg = true;
		if (p != null) {
			// alten Wert retten
			Rectangle2D.Double rect = new Rectangle2D.Double(p.rect.x,
					p.rect.y, p.rect.width, p.rect.height);
			// suche vorwaerts
			p.neuePosY();
			for (int i = 0; i < ps.aktuelleAnzahl; i++) {
				p2 = ps.gibPerson(i);
				if ((p != null) && (p != p2)) {
					if (p.rect.intersects(p2.rect)) {
						erfolg = false;
						if (p.orientierung.equals("S")) {
							p.rect.y = p2.oben() - abstand - p.hoehe;
						} else {
							p.rect.y = p2.unten() + abstand;
						}
					}
				}
			}
			
			// suche rechts
			if (!erfolg) {
				erfolg = true;
				
				if (p.orientierung.equals("S")) {
					p.rect.x -= p.breite
							* (StaticTools.gibZufall() * 1.5 + 0.8);
					
					if (p.rect.x < u.gehwegLinks) {
						p.rect.x = u.gehwegLinks + 1;
						erfolg = false;
					}
					
				} else {
					p.rect.x += p.breite
							* (StaticTools.gibZufall() * 1.5 + 0.8);
					
					if (p.rect.x >= (u.gehwegRechts - p.rect.width)) {
						p.rect.x = u.gehwegRechts - p.rect.width - 1;
						erfolg = false;
					}
					
				}
				
				for (int i = 0; i < ps.aktuelleAnzahl; i++) {
					p2 = ps.gibPerson(i);
					if ((p != null) && (p != p2)) {
						if (p.rect.intersects(p2.rect)) {
							erfolg = false;
							break;
						}
					}
				}
			}
			
			// suche links
			if (!erfolg) {
				p.rect.x = rect.x;
				erfolg = true;
				
				if (p.orientierung.equals("S")) {
					p.rect.x += p.breite * (StaticTools.gibZufall()*1.5 + 0.8);
					
					if (p.rect.x > (u.gehwegRechts - p.rect.width)) {
						p.rect.x = u.gehwegRechts - p.rect.width - 1;
						erfolg = false;
					}
					
				} else {
					p.rect.x -= p.breite * (StaticTools.gibZufall()*1.5 + 0.8);
					
					if (p.rect.x < u.gehwegLinks) {
						p.rect.x = u.gehwegLinks + 1;
						erfolg = false;
					}
					
				}
				
				for (int i = 0; i < ps.aktuelleAnzahl; i++) {
					p2 = ps.gibPerson(i);
					if ((p != null) && (p != p2)) {
						if (p.rect.intersects(p2.rect)) {
							erfolg = false;
							break;
						}
					}
				}
			}
			
			// nach hinten und rechts gehen
			if (!erfolg) {
				erfolg = true;
				p.rect.x = rect.x;
				if (p.orientierung.equals("S")) {
					p.rect.y -= (p.hoehe / 10)* (StaticTools.gibZufall() + 0.8);
					p.rect.x -= p.breite * (StaticTools.gibZufall() + 0.8);
					
					if (p.rect.x < u.gehwegLinks) {
						p.rect.x = u.gehwegLinks + 1;
						erfolg = false;
					}
					
				} else {
					p.rect.y += (p.hoehe / 10)* (StaticTools.gibZufall() + 0.8);
					p.rect.x += p.breite * (StaticTools.gibZufall() + 0.8);
					
					if (p.rect.x >= (u.gehwegRechts - p.rect.width)) {
						p.rect.x = u.gehwegRechts - p.rect.width - 1;
						erfolg = false;
					}
					
				}
				
				for (int i = 0; i < ps.aktuelleAnzahl; i++) {
					p2 = ps.gibPerson(i);
					if ((p != null) && (p != p2)) {
						if (p.rect.intersects(p2.rect)) {
							erfolg = false;
							if (p.orientierung.equals("S")) {
								p.rect.y = p2.oben() - abstand - p.hoehe;
							} else {
								p.rect.y = p2.unten() + abstand;
							}
						}
					}
				}
			}
			
			if (!erfolg) {
				p.rect.x = rect.x;
			}
			
		}
	}
	
	boolean	vorwaerts	= true;
	
	public void tuWas(int ID) {
		Person p;
		int i;
		if (vorwaerts) {
			for (i = 0; i < ps.aktuelleAnzahl; i++) {
				p = ps.gibPerson(i);
				if (p != null) {
					p.neuePosY();
					// Testen, ob andere Person vor ihr
					sucheNeuePosition(p);
					p.setzePosition();
				}
			}
		} else {
			for (i = ps.aktuelleAnzahl-1; i > -1; i--) {
				p = ps.gibPerson(i);
				if (p != null) {
					p.neuePosY();
					// Testen, ob andere Person vor ihr
					sucheNeuePosition(p);
					p.setzePosition();
				}
			}			
		}
		for (i = 0; i < ps.aktuelleAnzahl; i++) {
			p = ps.gibPerson(i);
			if (p != null) {
				if (p.orientierung.equals("S")) {
					if (p.rect.y > u.hoehe) {
						p.rect.y = -p.hoehe;
					}
				} else {
					if (p.rect.y < 0) {
						p.rect.y = u.hoehe;
					}
				}
			}
		}
	}
}
