//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

public class Anwendung_ZeichnugSplit {
    
    public Anwendung_ZeichnugSplit(){
        new ZeichnungSplit("Graphik-Fenster-Tab");
        ZeichnungSplit.maximiere();
        new Taste();
        new Ausgabe(ZeichnungSplit.getBehaelterLO(), "Links", 10,100, 100, 60);
    }

    public static void main(String[] args) {
        new Anwendung_ZeichnugSplit();
    }

	
}
