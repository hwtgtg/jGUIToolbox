//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class Steuerung {
	
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Umgebung u = new Umgebung();
		Ampelsteuerung s = new Ampelsteuerung(u);
		
		AutoSchlangenSteuerung aO = new AutoSchlangenSteuerung(5, u, "O");
		AutoSchlangenSteuerung aW = new AutoSchlangenSteuerung(5, u, "W");
		
		PersonenSchlangenSteuerung pS = new PersonenSchlangenSteuerung(20, u);
		int i = 0 ;
		
		for (i = 0 ; i < 15;i++){
			pS.hinzufuegen(new Person(u,"S"));
		}
		
		for (i = 0 ; i <5;i++){
			pS.hinzufuegen(new Person(u,"N"));
//			pS.hinzufuegen(new Person(u,"N",'r'));
		}
		//PersonenSchlangenSteuerung pS = new PersonenSchlangenSteuerung(10, u, "S");
		//PersonenSchlangenSteuerung pN = new PersonenSchlangenSteuerung(10, u, "N");
	}
	
}
