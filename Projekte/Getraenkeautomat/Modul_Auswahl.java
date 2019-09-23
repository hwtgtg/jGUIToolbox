//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Modul_Auswahl implements ITuWas {
	
	Behaelter rahmen ;
	
	Ausgabe text ;
	
	UmschaltTasteMitAnzeige a1 ;
	UmschaltTasteMitAnzeige a2 ;
	UmschaltTasteMitAnzeige a3 ;
	UmschaltTasteMitAnzeige a4 ;
	
	Modul_Automat automat ;
	Boolean aktiviert = false ;
	
	public Modul_Auswahl( Modul_Automat automat , int x , int y){
		this.automat = automat ;
		rahmen = new Behaelter(x,y,220,265);
		rahmen.setzeMitRand(true);
		rahmen.setzeHintergrundfarbe("gruen");
		text = new Ausgabe(rahmen,"Getraenk",5,0,200,60);
		a1 = new UmschaltTasteMitAnzeige(rahmen,"Zitrone - 1,10 €",5,60 ,210,50,0);
		a1.setzeLink(this, 10);
		a2 = new UmschaltTasteMitAnzeige(rahmen,"Orange  - 1,20 €",5,110,210,50,0);
		a2.setzeLink(this, 20);
		a3 = new UmschaltTasteMitAnzeige(rahmen,"Kirsche - 1,70 €",5,160,210,50,0);
		a3.setzeLink(this, 30);
		a4 = new UmschaltTasteMitAnzeige(rahmen,"Apfel   - 0,90 €",5,210,210,50,0);
		a4.setzeLink(this, 40);
	}

	public void aktivieren(){
		a1.setzeNichtGewaehlt();
		a2.setzeNichtGewaehlt();
		a3.setzeNichtGewaehlt();
		a4.setzeNichtGewaehlt();
		aktiviert = true ;
	}

	public void deaktivieren(){
		aktiviert = false ;
	}
	
	public void tuWas(int ID) {
		if (aktiviert){
			switch (ID) {
			case 10:
				automat.gewaehlt("Zitrone",new Euro(1,10));
				break;
			case 20:
				automat.gewaehlt("Orange",new Euro(1,20));
				break;
			case 30:
				automat.gewaehlt("Kirsche",new Euro(1,70));
				break;
			case 40:
				automat.gewaehlt("Apfel",new Euro(0,90));
				break;
			
			default:
				break;
			}
		} else {
			switch (ID) {
			case 10:
				a1.setzeNichtGewaehlt();
				break;
			case 11:
				a1.setzeGewaehlt();
				break;
			case 20:
				a2.setzeNichtGewaehlt();
				break;
			case 21:
				a2.setzeGewaehlt();
				break;
			case 30:
				a3.setzeNichtGewaehlt();
				break;
			case 31:
				a3.setzeGewaehlt();
				break;
			case 40:
				a4.setzeNichtGewaehlt();
				break;
			case 41:
				a4.setzeGewaehlt();
				break;
			default:
				break;
			}
		}
	}
}
