//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class Modul_Eingabe implements ITuWas{
	
	Behaelter rahmen ;
	
	UmschaltTasteMitAnzeige a1 ;
	UmschaltTasteMitAnzeige a2 ;
	UmschaltTasteMitAnzeige a3 ;
	UmschaltTasteMitAnzeige a4 ;
	UmschaltTasteMitAnzeige a5 ;

	Taktgeber takt ;
	
	Modul_Automat automat ;
	Boolean aktiviert = false ;

	public Modul_Eingabe(Modul_Automat automat , int x , int y){
		this.automat = automat ;
		rahmen = new Behaelter(x,y,270,170);
		rahmen.setzeMitRand(true);
		rahmen.setzeHintergrundfarbe("orange");
		a1 = new UmschaltTasteMitAnzeige(rahmen,"0,10 €",5,5 ,130,50,6);
		a1.setzeLink(this, 10);
		a2 = new UmschaltTasteMitAnzeige(rahmen,"0,20 €",5,55,130,50,6);
		a2.setzeLink(this, 20);
		a3 = new UmschaltTasteMitAnzeige(rahmen,"0,50 €",135,5,130,50,0);
		a3.setzeLink(this, 50);
		a4 = new UmschaltTasteMitAnzeige(rahmen,"1,00 €",135,55,130,50,0);
		a4.setzeLink(this, 100);
		a5 = new UmschaltTasteMitAnzeige(rahmen,"LoeSCHEN",5,115,260,50,1);
		a5.setzeLink(this, 9999);
		
		takt = new Taktgeber();
		takt.setzeLink(this);
		
	}

	public void reset(){
		a1.setzeNichtGewaehlt();
		a2.setzeNichtGewaehlt();
		a3.setzeNichtGewaehlt();
		a4.setzeNichtGewaehlt();

	}

	public void aktivieren(){
		reset();
		aktiviert = true ;
	}

	public void deaktivieren(){
		aktiviert = false ;
	}

	public void tuWas(int ID) {
		if (aktiviert){
			switch (ID) {
			case 10:
				automat.eingabe(new Euro(0,10));
				takt.setzeID(1010);
				takt.einmal(200);
				break;
			case 20:
				automat.eingabe(new Euro(0,20));
				takt.setzeID(1020);
				takt.einmal(200);
				break;
			case 50:
				automat.eingabe(new Euro(0,50));
				takt.setzeID(1050);
				takt.einmal(200);
				break;
			case 100:
				automat.eingabe(new Euro(1,00));
				takt.setzeID(1100);
				takt.einmal(200);
				break;
			case 1010:
				a1.setzeNichtGewaehlt();
				break;
			case 1020:
				a2.setzeNichtGewaehlt();
				break;
			case 1050:
				a3.setzeNichtGewaehlt();
				break;
			case 1100:
				a4.setzeNichtGewaehlt();
				break;
			
			case 9999:
				a5.setzeNichtGewaehlt();
				automat.reset();
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
			case 50:
				a3.setzeNichtGewaehlt();
				break;
			case 51:
				a3.setzeGewaehlt();
				break;
			case 100:
				a4.setzeNichtGewaehlt();
				break;
			case 101:
				a4.setzeGewaehlt();
				break;

			case 9999:
				a5.setzeNichtGewaehlt();
				automat.reset();
				break;
			default:
				break;
			}
		}
	}

}
