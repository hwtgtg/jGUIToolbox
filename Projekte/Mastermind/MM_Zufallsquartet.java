//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class MM_Zufallsquartet {
	
	MM_Quartet q ;
	Rechteck r ;

	Ausgabe a ;
	
	public MM_Zufallsquartet (){
		q = new MM_Quartet( 5 , 5 );
		q.q.setzeHintergrundfarbe("lila");
		r = new Rechteck(3,3, 164 , 44 );
		r.setzeFarbe("gelb");
		a = new Ausgabe("Mastermind",25,5,160,40);
	}

	public void zeigeAusgangswert(){
		r.unsichtbarMachen();
		a.setzeAusgabetext("");
	}

	public void verbergeAusgangswert(){
		r.sichtbarMachen();
		a.setzeAusgabetext("Mastermind");
	}
	
		
}
