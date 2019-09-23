//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class B_ScrollGUI {
	
	Bild bild ;
	KomponenteImScrollfenster scrollPane;

	public B_ScrollGUI(){
		
		bild = new Bild("img//planets.gif");
		scrollPane = new KomponenteImScrollfenster(bild);
		scrollPane.setzeDimensionen(10,10,300,300);
	}


}
