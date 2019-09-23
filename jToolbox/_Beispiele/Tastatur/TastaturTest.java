//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class TastaturTest implements ITastatur {
	AusgabePanel ausgabe ;
	AusgabePanel ausgabe2 ;
	Eingabefeld ein1 ;
	Eingabefeld ein2 ;
	
	Tastatur test ; 
	Tastatur test2 ; 
	String s = "";
	
	public TastaturTest(){
		ausgabe = new AusgabePanel("",500,100);
		ausgabe2 = new AusgabePanel("",100,200,400,100);
		test2 = new Tastatur(ausgabe2.getBasisComponente(),true);
		
		Zeichnung.setzeScrollbar(false);
		test = new Tastatur();
		
		test.meldeAnTaste('A', "A");
		test.meldeAnTaste('B', "B");
		
		test2.meldeAnTaste('X', "X2");
		test2.meldeAnTaste('C', "C2");

		test.meldeAnTaste("ctrl alt shift released X", "CTRL ALT SHIFT X loslassen");

		// fuer Taste VK_INSERT: 
		test.meldeAnTaste("ctrl released INSERT", "CTRL Einfuegen");

		test2.setzeLink(this);
		test.setzeLink(this);
		// Focus setzen
		//if(!ausgabe2.getBasisComponente().requestFocusInWindow()) ausgabe2.setzeAusgabetext("Mist");
	}

	public void tastenAktion(String rueckgabe) {
		s+=" "+rueckgabe;
		if (s.length()>30)	s = s.substring(s.length()-30); 
		ausgabe.setzeAusgabetext(s);
	}
	
	public static void main( String[]args){
		new TastaturTest();
	}	
}
