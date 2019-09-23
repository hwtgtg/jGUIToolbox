//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class MM_Quartet {
	
	Behaelter q ;
	Kreis [] f ;
	
	String [] anzeigeFarben;
	
	
	public MM_Quartet(int x , int y){
		q = new Behaelter(0,0,160,40);
		q.setzeMitRand(true);
		f = new Kreis[4];
		for ( int i = 0 ; i < 4 ; i++){
			f[i] = new Kreis(q,2+i*40,2,18);
		}
		q.setzePosition(x, y);
		
		anzeigeFarben = new String[4];
		zuruecksetzen();
		
	}
	
	public void setzeFarben(String[] farben){
		anzeigeFarben = farben;
		for ( int i = 0 ; i < 4 ; i++){
			f[i].setzeFarbe(farben[i]);
		}
	}

	public void zuruecksetzen(){
		for ( int i = 0 ; i < 4 ; i++){
			anzeigeFarben[i]="hellgrau";
			f[i].setzeFarbe("hellgrau");
		}
		
	}
	
	public String[] gibFarben(){
		return anzeigeFarben;
	}
	
	public static void  main(String[] args) {
		MM_Quartet q = new MM_Quartet(10,10);
		String[] f = {"rot","gelb","rot","gruen"};
		q.setzeFarben(f);
	}
}
