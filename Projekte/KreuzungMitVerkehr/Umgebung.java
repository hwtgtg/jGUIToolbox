//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Umgebung {
	int					breite			= 1000;
	int					hoehe			= 400;
	int					strassenbreite	= 100;
	int					gehwegbreite	= 200;
	
	Behaelter			umgebung;
	
	AutoAmpel			aaWest;
	AutoAmpel			aaOst;
	FussgaengerAmpel	faNord;
	FussgaengerAmpel	faSued;
	
	Rechteck			strasse;
	Rechteck			gehwegOben;
	Rechteck			gehwegUnten;
	
	int					strasseOben		= (hoehe - strassenbreite) / 2;
	int					strasseMitte	= strasseOben + strassenbreite / 2;
	int					strasseUnten	= strasseOben + strassenbreite;
	
	int					gehwegLinks		= (breite - gehwegbreite) / 2;
	int					gehwegRechts	= gehwegLinks + gehwegbreite;
	
	Rechteck[]			markierung;
	int					mAnzahl			= (breite - 50) / 100 + 1;
	int					mBreit			= 8;
	
	Rechteck[]			zebrastreifen;
	int					zAnzahl			= 5;
	int					zBreit			= (strasseUnten - strasseOben)
												/ (zAnzahl * 2 + 1);
	
	Rechteck			haltelinieLinks;
	Rechteck			haltelinieRechts;
	Rechteck			haltelinieOben;
	Rechteck			haltelinieUnten;
	
	public Umgebung(){
		Zeichnung.setzeFenstergroesse(breite+30,hoehe+40);
		umgebung = new Behaelter(breite,hoehe);
		
		strasse = new Rechteck(umgebung,0,strasseOben,breite,strassenbreite );
		strasse.setzeFarbe("schwarz");
		
		gehwegOben = new Rechteck(umgebung,gehwegLinks,0,gehwegbreite,strasseOben);
		gehwegUnten = new Rechteck(umgebung,gehwegLinks,strasseUnten,gehwegbreite,hoehe-strasseUnten);
		
		markierung = new Rechteck[ mAnzahl ];
		for ( int i = 0 ; i < mAnzahl ; i++ ){
			markierung[i] = new Rechteck( umgebung,100*i,strasseMitte-(mBreit/2),60,mBreit);
			markierung[i].setzeFarbe("weiss");
		}
		
		zebrastreifen = new Rechteck[ zAnzahl ];
		for ( int i = 0 ; i < zAnzahl ; i++ ){
			zebrastreifen[i] = new Rechteck( umgebung,gehwegLinks,strasseOben+zBreit + i*(2*zBreit) ,gehwegbreite,zBreit);
			zebrastreifen[i].setzeFarbe("weiss");
		}
		
		haltelinieLinks = new Rechteck( umgebung, gehwegLinks-10-1,strasseMitte+5,10, strasseUnten-strasseMitte-5 );
		haltelinieLinks.setzeFarbe("weiss");
			
		haltelinieRechts = new Rechteck( umgebung, gehwegRechts+1,strasseOben,10, strasseMitte-strasseOben-5 );
		haltelinieRechts.setzeFarbe("weiss");
		
		haltelinieOben = new Rechteck( umgebung, gehwegLinks,strasseOben-5,gehwegbreite, 5);
		haltelinieOben.setzeFarbe("weiss");
			
		haltelinieUnten = new Rechteck( umgebung, gehwegLinks,strasseUnten,gehwegbreite, 5);
		haltelinieUnten.setzeFarbe("weiss");
			
		aaWest = new AutoAmpel(gehwegLinks - AutoAmpel.breite,strasseUnten,'W');
		aaOst = new AutoAmpel(gehwegRechts ,strasseOben - AutoAmpel.hoehe,'O');
		
		faNord = new FussgaengerAmpel(gehwegLinks - FussgaengerAmpel.breite,strasseOben- FussgaengerAmpel.hoehe,'N');
		faSued = new FussgaengerAmpel(gehwegRechts ,strasseUnten ,'S');
		
		umgebung.hinzufuegen(aaWest.aampel);
		umgebung.hinzufuegen(aaOst.aampel);
		umgebung.hinzufuegen(faNord.fampel);
		umgebung.hinzufuegen(faSued.fampel);
	}
		
}
