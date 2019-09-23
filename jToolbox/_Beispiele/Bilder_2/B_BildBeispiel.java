//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_BildBeispiel implements ITuWas{
	Bilddatei	eins;
	Bilddatei	zwei;
	Bilddatei[]	bilder;
	
	Bild		anzeigebild1;
	Bild		anzeigebild2;
	Taktgeber	takt;
	
    int maxBilder ;
    int bildNr ;


	public B_BildBeispiel() {
        maxBilder = 11 ;
        bildNr = 0;
		bilder = new Bilddatei[maxBilder];
		bilder[0] = new Bilddatei("img\\granatapfel.gif",200,200);
		bilder[1] = new Bilddatei("img\\apfel.gif",200,200);
		bilder[2] = new Bilddatei("img\\banane.gif",200,200);
		bilder[3] = new Bilddatei("img\\birne.gif",200,200);
		bilder[4] = new Bilddatei("img\\blaubeere.gif",200,200);
		bilder[5] = new Bilddatei("img\\erdbeere.gif",200,200);
		bilder[6] = new Bilddatei("img\\grapefruit.gif",200,200);
		bilder[7] = new Bilddatei("img\\lemmon.gif",200,200);
		bilder[8] = new Bilddatei("img\\orange.gif",200,200);
		bilder[9] = new Bilddatei("img\\weinbeere.gif",200,200);
		bilder[10] = new Bilddatei("img\\zitrone.gif",200,200);
		
		anzeigebild1 = new Bild(bilder[bildNr]);
		anzeigebild1.setzePosition(100, 100);
		anzeigebild2 = new Bild(bilder[maxBilder-bildNr-1]);
		anzeigebild2.setzePosition(300, 100);
		takt = new Taktgeber();
		takt.setzeLink(this, 0);
		takt.setzeZeitZwischenAktionen(200);
	}
	   // Methoden

    public void farbeWechseln(){
        bildNr++ ;
        if (bildNr>=maxBilder) bildNr = 0 ;
    	anzeigebild1.wechsleBild(bilder[bildNr]);
    	anzeigebild2.wechsleBild(bilder[maxBilder-bildNr-1]);
    }

    public void tuWas(int ID ){
        farbeWechseln();
    }

    public void wechsle(int anzahl, int zeit ){
        int delay = zeit / anzahl ;
        //takt.setzteAnfangsZeitverzoegerung(delay);
        takt.setzeZeitZwischenAktionen(delay);
        takt.mehrfach(anzahl);
    }

	public static void main(String[] args) {
		B_BildBeispiel t = new B_BildBeispiel();
		t.wechsle(30, 6000);
	}
	
}
