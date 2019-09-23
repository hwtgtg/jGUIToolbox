//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * Beispielanwendung fuer FrameworkGUI
 * 
 * Waehrungsrechner
 * Beispiel fuer Eingabefelder
 * 
 * @author Hans Witt
 * 
 * Version: 1.0
 * @version: 2 (3.8.2008)
 *       angepasst an geaendertes ITuWas

 */

public class Waehrungsrechner implements ITuWas {
    AusgabePanel titel;
    Ausgabe lDollar;
    Ausgabe lEuro;
    Eingabefeld kurs ;
    Eingabefeld dollar;
    Eingabefeld euro;

    double umrechnungskursEnachD = 1.20;

    public Waehrungsrechner() {

        titel = new AusgabePanel(
            "1 € kostet            $", 200, 0, 270, 100);
            
        kurs = new  Eingabefeld("" + umrechnungskursEnachD, 350, 0 , 80, 100);   
        kurs.setzeSchriftfarbe("magenta");
        kurs.setzeSchriftgroesse(30);
        kurs.setzeSchriftStilFett();
        kurs.setzeLink(this, 10);
        
        titel.setzeSchriftgroesse(30);
        titel.setzeSchriftfarbe("magenta");

        lDollar = new Ausgabe("$", 450, 200, 60, 100);
        lDollar.setzeFarbe("rot");

        dollar = new Eingabefeld("Dollar", 250, 200, 200, 100);
        dollar.setzeLink(this, 20);

        lEuro = new Ausgabe("€", 450, 300, 60, 100);

        euro = new Eingabefeld("Euro", 250, 300, 200, 100);
        euro.setzeLink(this, 30);

    }

    public void tuWas(int ID) {

        switch (ID) {
            case 10:
                dollar.setzeAusgabetext("Dollar");
                euro.setzeAusgabetext("Euro");
                umrechnungskursEnachD = kurs.leseDouble(1.0);
                kurs.setzeAusgabetext(""+umrechnungskursEnachD); // FÜr den Fehlerfall
                break ;
            case 20: // Ausloeser: ENTER auf dollar
                euro.setzeDouble(dollar.leseDouble(0) / umrechnungskursEnachD);
                break;
            case 30: // Ausloeser: ENTER auf euro
                dollar.setzeDouble(euro.leseDouble(0) * umrechnungskursEnachD);
                break;
            default:
                break;  
        }
    }

}
