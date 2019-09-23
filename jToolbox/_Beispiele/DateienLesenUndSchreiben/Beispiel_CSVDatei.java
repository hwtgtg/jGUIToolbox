public class Beispiel_CSVDatei
{
    CSVDatei dateiEins ;
    CSVDatei dateiZwei ;
    TBConsole cs ;

    /**
     * Konstruktor für Objekte der Klasse Beispiel_CSVdateiEins
     */
    public Beispiel_CSVDatei()
    {
        cs = new TBConsole() ;

        dateiEins = new CSVDatei("TestCSV.csv");
        dateiEins.setzeString("Anfang" , 0, 0);
        dateiEins.setzeString("Ende" , 10, 5);
        dateiEins.setzeString("Ende2" , 11, 6);
        dateiEins.setzeInteger(11, 1, 2);
        dateiEins.setzeInteger(22, 2, 3);
        dateiEins.setzeInteger(42, 4, 5);
        dateiEins.setzeDouble(3.14, 6, 7);
        dateiEins.schreibeCSVDatei("TestCSV.csv",true);

        dateiZwei = new CSVDatei("TestCSV.csv");
        for( int y = 0 ; y < dateiZwei.getAnzahlZeilen() ; y++ ){
            for (int x = 0 ; x < dateiZwei.getAnzahlSpalten() ; x++ ){
                cs.print(dateiZwei.leseString( y , x)+" : "); // Zeile , Spalte
            }
            cs.println();
        }
    }
}
