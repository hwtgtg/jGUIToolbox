

public class Beispiel_INIDatei
{
    INIDatei datei ;
    TBConsole cs ;
    /**
     * Konstruktor für Objekte der Klasse Beispiel_INIDatei
     */
    public Beispiel_INIDatei()
    {
        datei = new INIDatei("TestINI.txt");
        cs = new TBConsole();
        
        // Existiert der Eintrag nicht oder tritt ein Umwandelfehler auf, wird der Wert 3.1 gelesen
        cs.print("[Double-Werte] pi: " );
        cs.printlnDouble(datei.leseDouble("Double-Werte", "pi",3.1)); 
        
        if( datei.existiert("Anfang", "eins")){
            cs.println("[Anfang] eins: "+ datei.leseString("Anfang", "eins"));
        } else {
            cs.println("[Anfang] eins: -nicht vorhanden-");
        }
    
        datei.setzeString("Anfang","eins","veraenderter Eintrag");
        datei.setzeString("Anfang","zwei","neuer Eintrag");
    
        datei.setzeDouble("Double-Werte", "pi", 3.14159265359);
        
        datei.schreibeINIDatei();
    
    }
    
}
