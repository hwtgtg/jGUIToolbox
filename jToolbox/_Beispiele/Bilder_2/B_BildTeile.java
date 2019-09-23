//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_BildTeile implements ITuWas{
	Bilddatei	eins;
	
	Bild		anzeigebild1;
	Bild		anzeigebild2;
	
	Behaelter behaelter ;
	int breite = 0 ;
	int hoehe = 0 ;
			
	
	
	Taktgeber	takt;
	


	public B_BildTeile() {
		eins = new Bilddatei("img\\granatapfel.gif",200,200);
		
		anzeigebild1 = new Bild(eins);
		anzeigebild1.setzePosition(100, 100);
		anzeigebild1.expandieren();
		breite = anzeigebild1.leseBildBreite()/4;
		hoehe = anzeigebild1.leseBildHoehe()/4;
		behaelter = new Behaelter();
		behaelter.setzePosition(300,100);
		behaelter.setzeGroesse(breite, hoehe);
		anzeigebild2 = new Bild(behaelter,eins);
		anzeigebild2.expandieren();
		anzeigebild2.setzePosition(0, 0);
	
		
		takt = new Taktgeber();
		takt.setzeLink(this, 0);
		takt.setzeZeitZwischenAktionen(200);
	}
	   // Methoden

	int nr = 0;
	
    public void wechseln(){
    	if (nr == 4)nr = 0 ;
		behaelter.setzePosition(300+nr*breite,100+nr*hoehe);
		anzeigebild2.setzePosition(-nr*breite, -nr*hoehe);
    	nr++;
    }

    public void tuWas(int ID ){
        wechseln();
    }

    public void wechsle(int anzahl, int zeit ){
        int delay = zeit / anzahl ;
        //takt.setzteAnfangsZeitverzoegerung(delay);
        takt.setzeZeitZwischenAktionen(delay);
        takt.mehrfach(anzahl);
    }

	public static void main(String[] args) {
		B_BildTeile t = new B_BildTeile();
		t.wechsle(30, 60000);
	}
	
}
