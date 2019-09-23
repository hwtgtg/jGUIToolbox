//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Pumpe_Ventil {
	
	int			wandstaerke	= 14;
	
	Behaelter	alles;
	Rechteck	luecke;
	
	Behaelter	ventil;
	Rechteck	deckel;
	Rechteck	innen;
	
	public Pumpe_Ventil(Behaelter behaelter) {
		alles = new Behaelter(behaelter,0, 0, 2 * wandstaerke, 3 * wandstaerke);
		luecke = new Rechteck(alles, wandstaerke / 2, wandstaerke, wandstaerke,
				wandstaerke+2);
		luecke.setzeFarbe("blau");
		ventil = new Behaelter(alles,0, 0, 2 * wandstaerke, 2 * wandstaerke+1);
		;
		
		deckel = new Rechteck(ventil, 0, wandstaerke/2, 2 * wandstaerke, wandstaerke/2);
		innen = new Rechteck(ventil, wandstaerke / 2 + 4, wandstaerke,
				wandstaerke - 8 , wandstaerke - 3);
		
	}
	
	
	public void auf(){
		ventil.setzePosition(0, -wandstaerke/2);
	}
	
	public void zu(){
		ventil.setzePosition(0, 0);
	}

	public void setzePosition(int neuesX, int neuesY){
		alles.setzePosition(neuesX, neuesY);
	}
	
/*	public static void main(String[] args) {
		B_Physik_Ventil s = new B_Physik_Ventil();
		Zeichnung.setzeFenstergroesse(1000, 600);
		Zeichnung.setzeRasterEin();
		while (true){
			s.auf();
			StaticTools.warte(500);
			s.zu();
			StaticTools.warte(500);
		}
	}*/
}
