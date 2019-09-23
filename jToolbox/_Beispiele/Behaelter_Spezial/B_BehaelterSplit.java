//%$JGUIToolboxEX$%//ID fuer Toolboxdateien


public class B_BehaelterSplit {

	public static void main(String[] args) {
		Zeichnung.gibZeichenflaeche().expand();
		BehaelterSplit b = new BehaelterSplit(0,0,500, 300);
		b.splitVertikal();
		b.setDividerLocation(50);
		b.setResizeWeight(0);
		b.expandRU();
		BehaelterSplit b2 = new BehaelterSplit(b.getBehaelterRU(),0,0,500, 300);
		b2.splitVertikal();
		b2.setDividerLocation(50);
		b2.setResizeWeight(0);

		new Taste(b.getBehaelterLO(),"Adresse",0,0,200,40);
		new Taste(b2.getBehaelterLO(),"Betreff",0,0,200,40);
		new Taste(b2.getBehaelterRU(),"Mail",0,0,300,300);

	}


}
