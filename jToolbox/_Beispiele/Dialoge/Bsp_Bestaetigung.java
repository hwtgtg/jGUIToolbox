
/**
 * Beschreiben Sie hier die Klasse Bsp_Bestaetigung.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Bsp_Bestaetigung
{
    Dlg_Bestaetigung dlg ;
    char erg ;
    AusgabePanel anz ;
    /**
     * Konstruktor für Objekte der Klasse Bsp_Bestaetigung
     */
    public Bsp_Bestaetigung(){
        dlg = new Dlg_Bestaetigung();
        dlg.setzeTitel("Meldungstitel");
        dlg.setzeMeldungstext("Warnung! bestätigen mit Taste");
        dlg.typ_typOKAbbruch();
        dlg.icon_warnug(); // Icon 

        anz = new AusgabePanel(" ",0,350,200,50);
    }

    public void bestaetigung(){
        dlg.zeigeMeldung();
        erg = dlg.leseErgebnis();
        if( erg == 'J' ) {
            anz.setzeAusgabetext("bestaetigt");
        } else {
            anz.setzeAusgabetext("abgelehnt");
        }

    }
}
