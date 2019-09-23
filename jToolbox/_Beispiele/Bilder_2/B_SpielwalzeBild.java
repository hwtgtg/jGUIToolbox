//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Write a description of class Spielwalze here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class B_SpielwalzeBild implements ITuWas {   // Attribute

    Rechteck kasten; 
    Bild [] bilder ;
    int maxBilder ;
    int bildNr ;

    Taktgeber takt ;

    //Konstruktor
    public B_SpielwalzeBild(int neuesX, int neuesY) {
        kasten = new Rechteck(neuesX, neuesY, 100, 100);
        maxBilder = 11 ;
        bilder = new Bild[maxBilder];
        bildNr = 0;
        importBilder ( neuesX, neuesY);
        takt = new Taktgeber();
        takt.setzeLink(this, 0);
        takt.setzeZeitZwischenAktionen(200);

    } //Ende Konstruktor
    private void importBilder(int neuesX, int neuesY){
        bilder[0]=new Bild("img\\granatapfel.gif");
        bilder[0].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[1]=new Bild("img\\apfel.gif");
        bilder[1].unsichtbarMachen();
        bilder[1].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[2]=new Bild("img\\banane.gif");
        bilder[2].unsichtbarMachen();
        bilder[2].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[3]=new Bild("img\\birne.gif");
        bilder[3].unsichtbarMachen();
        bilder[3].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[4]=new Bild("img\\blaubeere.gif");
        bilder[4].unsichtbarMachen();
        bilder[4].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[5]=new Bild("img\\erdbeere.gif");
        bilder[5].unsichtbarMachen();
        bilder[5].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[6]=new Bild("img\\grapefruit.gif");
        bilder[6].unsichtbarMachen();
        bilder[6].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[7]=new Bild("img\\lemmon.gif");
        bilder[7].unsichtbarMachen();
        bilder[7].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[8]=new Bild("img\\orange.gif");
        bilder[8].unsichtbarMachen();
        bilder[8].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[9]=new Bild("img\\weinbeere.gif");
        bilder[9].unsichtbarMachen();
        bilder[9].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
        bilder[10]=new Bild("img\\zitrone.gif");
        bilder[10].unsichtbarMachen();
        bilder[10].setzeDimensionen(neuesX+10, neuesY+10, 100, 100);
    }
    // Methoden

    public void farbeWechseln(){
        bilder[bildNr].unsichtbarMachen();
        bildNr++ ;
        if (bildNr>=maxBilder) bildNr = 0 ;
        bilder[bildNr].sichtbarMachen();
    }

    public void tuWas(int ID ){
        farbeWechseln();
    }

    public void wechsle(int anzahl, int zeit ){
        int delay = zeit / anzahl ;
        takt.setzeAnfangsZeitverzoegerung(delay);
        takt.setzeZeitZwischenAktionen(delay);
        takt.mehrfach(anzahl);
    }

    public int gibBildNr(){
        return bildNr;
    }
    public void stop(){
        takt.stop();
    }
    
	public static void main(String[] args) {
		B_SpielwalzeBild t = new B_SpielwalzeBild(100,100);
		t.wechsle(15, 2000);
	}

}

