//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
import java.awt.BorderLayout;



public class B_BehaelterTablayout implements ITuWas{

	
	public B_BehaelterTablayout(){
		new Zeichnung("TestScroll");
		
		Zeichnung.setzeFenstergroesse(800, 500);
		Zeichnung.gibZeichenflaeche().setLayout(new BorderLayout());

		BehaelterTablayout b = new BehaelterTablayout(100,100,300,300);
		b.setzeLink(this);
		
		int nr = b.getTabID("neuer Tab");
		b.renameTabTitle(nr, "Erster Tab");

		new Taste(b,"eins",0,0,100,100);
		
		Zeichenflaeche z2 = new Zeichenflaeche();
		b.addTab("Test2", z2);
		z2.expand();
		new Taste(z2,"zwei",0,0,10,10);

		Zeichenflaeche z3 = new Zeichenflaeche();
		
		b.insertTab(1, "Tabelle", z3);
		new Tabelle(z3,7,6,10,10,500,300);
    
	    System.out.println("TabID von Test2: "+b.getTabID("Test2"));

	    StaticTools.warte(500);
	    b.setzeTabsUnten();
	    StaticTools.warte(500);
	    b.setzeTabsLinks();
	    StaticTools.warte(500);
	    b.setzeTabsRechts();
	    StaticTools.warte(500);
	    b.setzeTabsOben();
	    
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new B_BehaelterTablayout();
	}

	@Override
	public void tuWas(int ID) {
		System.out.println("Tab-ID:"+ID);
		
	}

}
