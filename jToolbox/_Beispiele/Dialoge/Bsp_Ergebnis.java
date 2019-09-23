
/**
 * Beschreiben Sie hier die Klasse Bsp_Ergebnis.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Bsp_Ergebnis
{
    Dlg_Eingabe dlg ;
    String erg ;
    AusgabePanel anz ;
    /**
     * Konstruktor für Objekte der Klasse Bsp_Ergebnis
     */
    public Bsp_Ergebnis(){
        dlg = new Dlg_Eingabe();
        dlg.setzeTitel("Meldungstitel");
        dlg.setzeMeldungstext("Frage! Was wird gewuenscht?");
        dlg.icon_frage();// Icon 
        
        anz = new AusgabePanel(" ",0,350,200,50);
    }

    public void zurEingabe(){
        dlg.zeigeMeldung();
        erg = dlg.leseErgebnis();
        anz.setzeAusgabetext(erg);
    }
}
