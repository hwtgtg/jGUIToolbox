//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class Modul_Ausgabe {
	Behaelter rahmen ;
	
	AusgabePanel text ;
	
	
	public Modul_Ausgabe(int x , int y){
		rahmen = new Behaelter(x,y,400,100);
		rahmen.setzeMitRand(true);
		rahmen.setzeHintergrundfarbe("lila");
		text = new AusgabePanel(rahmen,"Ausgabe leer",5,10,390,80);
		text.setzeHintergrundfarbe("lila");
	}
	
	public void setzeAusgabe(String ausg){
		text.setzeAusgabetext(ausg);
	}
	
}
