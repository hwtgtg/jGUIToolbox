//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class MM_Ergebnis {

	Behaelter q ;
	Kreis [] e ;
	
	public MM_Ergebnis(int x , int y){
		q = new Behaelter(0,0,40,40);
		q.setzeMitRand(true);
		e = new Kreis[4];
		e[0] = new Kreis(q,1,1,9);
		e[2] = new Kreis(q,1,21,9);
		e[3] = new Kreis(q,21,1,9);
		e[1] = new Kreis(q,21,21,9);
		q.setzePosition(x, y);
	}
	
	public void setzeFarben(String[] farbe){
		for ( int i = 0 ; i < 4 ; i++){
			e[i].setzeFarbe(farbe[i]);
		}
	}

	public void zuruecksetzen(){
		for ( int i = 0 ; i < 4 ; i++){
			e[i].setzeFarbe("hellgrau");
		}
	}
	
	public void anzeigen(int idAnzahl, int fAnzahl){
		// String [] farben = new String[4];
		for ( int i = 0 ; i < 4 ; i++){
			if (i<idAnzahl){
				e[i].setzeFarbe("schwarz");
			} else if (i < fAnzahl){
				e[i].setzeFarbe("weiss");
			} else {
				e[i].setzeFarbe("hellgrau");
			}
		}
	}
	
	
	public static void  main(String[] args) {
		MM_Ergebnis e = new MM_Ergebnis(10,10);
		String[] f = {"schwarz","schwarz","weiss","hellgrau"};
		e.setzeFarben(f);
	}
	
}
