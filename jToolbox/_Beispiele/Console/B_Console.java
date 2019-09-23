//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_Console {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TBConsole cs = new TBConsole();
		
		cs.println("Consolentest");
		cs.println("Geben Sie Ihren Namen ein:");
		String strEingabe = cs.readLine();
		cs.println("Vielen Dank, " + strEingabe);
		cs.println("Ihre Zahl-Eingaben werden als Echo wiederholt. Ende mit 99");
		int ein = 0;
		ein =cs.readInt();
		cs.printlnInt(ein);
		double dEin;
		while (ein != 99) {
			dEin = cs.readDouble();
			if (dEin == 99) {
				ein = 99;
			}
			cs.printlnDouble(dEin);
		}
		cs.println("Ende Consolentest");
	}
	
}
