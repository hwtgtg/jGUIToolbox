/**
 * @author Witt
 * 
 */
public class DBFormel_1_Access {

    DB_Access db;

    public DBFormel_1_Access() {
        db = new DB_Access();
    }

    public void dbAufgabe() {

        if (! db.conOeffnen("formel1.accdb", "root", "") ) return ;

        /* geht nicht*/
        db.conExecute("DROP TABLE faehrt_bei"); 
        db.conExecute("DROP TABLE gewinnt ");   
        db.conExecute("DROP TABLE fahrer ");
        db.conExecute("DROP TABLE team ");
        db.conExecute("DROP TABLE rennen ");
        /* */
       
        db.conExecute("CREATE TABLE  fahrer (" + "Name VARCHAR(50) NOT NULL,"
            + "Geburtsjahr Integer ," + "PRIMARY KEY  (Name)" + "   );");

        db.conExecute("INSERT INTO fahrer VALUES('Sebastian Vettel',1987);");
        db.conExecute("INSERT INTO fahrer VALUES('Mark Webber',1976 );");
        db.conExecute("INSERT INTO fahrer VALUES('Jenson Button',1980);");
        db.conExecute("INSERT INTO fahrer VALUES('Rubens Barrichello',1972);");
        db.conExecute("INSERT INTO fahrer VALUES('Lewis Hamilton',1985);");
        db.conExecute("INSERT INTO fahrer VALUES('Kimi Raeikkoenen',1979);");
        db.conExecute("INSERT INTO fahrer VALUES('Nick Heidfeld',1977);");

        db.conExecute("CREATE TABLE  team ("
            + "Bezeichnung VARCHAR(50) NOT NULL,"
            + "PRIMARY KEY  (Bezeichnung)" + ") ;");

        db.conExecute("INSERT INTO team  VALUES('Red Bull Racing');");
        db.conExecute("INSERT INTO team  VALUES('Brawn GP');");
        db.conExecute("INSERT INTO team  VALUES('McLaren-Mercedes');");
        db.conExecute("INSERT INTO team  VALUES('Ferrari');");
        db.conExecute("INSERT INTO team  VALUES('BMW Sauber F1 Team');");

        db.conExecute("CREATE TABLE  faehrt_bei ("
            + "Name VARCHAR(50) NOT NULL,"
            + "Bezeichnung VARCHAR(50) NOT NULL," + "PRIMARY KEY  (Name),"
            + "FOREIGN KEY (Name) REFERENCES fahrer (Name),"
            + "FOREIGN KEY (Bezeichnung) REFERENCES  team (Bezeichnung)"
            + ") ;");

        db.conExecute("INSERT INTO faehrt_bei VALUES('Sebastian Vettel', 'Red Bull Racing');");
        db.conExecute("INSERT INTO faehrt_bei VALUES('Mark Webber', 'Red Bull Racing');");
        db.conExecute("INSERT INTO faehrt_bei VALUES('Jenson Button', 'Brawn GP');");
        db.conExecute("INSERT INTO faehrt_bei VALUES('Rubens Barrichello', 'Brawn GP');");
        db.conExecute("INSERT INTO faehrt_bei VALUES('Lewis Hamilton', 'McLaren-Mercedes');");
        db.conExecute("INSERT INTO faehrt_bei VALUES('Kimi Raeikkoenen', 'Ferrari');");
        db.conExecute("INSERT INTO faehrt_bei VALUES('Nick Heidfeld', 'BMW Sauber F1 Team');");

        db.conExecute("CREATE TABLE  rennen (" + "Ort VARCHAR(50) NOT NULL,"
            + "Land VARCHAR(50)," + "Datum Date, " + "PRIMARY KEY  (Ort)"
            + ") ;");

        db.conExecute("INSERT INTO rennen VALUES( 'Melbourne', 'Australien',#2009-03-29#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Sepang', 'Malaysia',#2009-04-05#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Shanghai', 'China',#2009-04-19#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Manama', 'Bahrain',#2009-04-26#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Barcelona', 'Spanien',#2009-05-10#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Monte Carlo', 'Monaco',#2009-05-24#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Istanbul', 'Tuerkei',#2009-06-07#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Silverstone', 'Grossbritannien',#2009-06-21#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Nuerburgring', 'Deutschland',#2009-07-12#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Budapest', 'Ungarn',#2009-07-26#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Valencia', 'Spanien',#2009-08-23#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Spa-Francorchamps', 'Belgien',#2009-08-30#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Monza', 'Italien',#2009-09-13#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Singapur', 'Singapur',#2009-09-27#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Suzuka', 'Japan',#2009-10-04#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Sao Paulo', 'Brasilien',#2009-10-18#);");
        db.conExecute("INSERT INTO rennen VALUES( 'Abu Dhabi', 'Abu Dhabi',#2009-11-01#);");

        db.conExecute("CREATE TABLE  gewinnt ( "
            + "Name VARCHAR(50) NOT NULL, " + "Ort VARCHAR(50) NOT NULL, "
            + "PRIMARY KEY  (Name,Ort), "
            + "FOREIGN KEY (Name) REFERENCES fahrer (Name), "
            + "FOREIGN KEY (Ort) REFERENCES  rennen (Ort) " + ") ;");

        db.conExecute("INSERT INTO gewinnt VALUES('Jenson Button', 'Melbourne');");
        db.conExecute("INSERT INTO gewinnt VALUES('Jenson Button', 'Sepang');");
        db.conExecute("INSERT INTO gewinnt VALUES('Sebastian Vettel', 'Abu Dhabi');");
        db.conExecute("INSERT INTO gewinnt VALUES('Jenson Button', 'Manama');");
        db.conExecute("INSERT INTO gewinnt VALUES('Jenson Button', 'Barcelona');");
        db.conExecute("INSERT INTO gewinnt VALUES('Jenson Button', 'Monte Carlo');");
        db.conExecute("INSERT INTO gewinnt VALUES('Jenson Button', 'Istanbul');");

        db.conAbfrage("SELECT f.Name , t.Bezeichnung FROM fahrer f , faehrt_bei a  , team t"
            + " where (f.Name = a.Name) AND (a.Bezeichnung =t.Bezeichnung) ;");

        int anzahl = db.anzahlDatensaetze();
        System.out.println("Anzahl der Datensaetze Fahrer - Team: " + anzahl);

        while (db.neuerDatensatz()) {
            System.out.println("     " + db.getString("Name") + " --- "
                + db.getString(2));
        }

        db.conAbfrage("SELECT f.Name , r.Ort FROM fahrer f , gewinnt g  , rennen r"
            + " where (f.Name = g.Name) AND (g.Ort = r.Ort) ;");

        anzahl = db.anzahlDatensaetze();
        System.out.println("Anzahl der Datensaetze Fahrer - Rennstrecke: "
            + anzahl);

        while (db.neuerDatensatz()) {
            System.out.println("     " + db.getString("Name") + " --- "
                + db.getString(2));
        }

        db.conExecute("UPDATE gewinnt SET Ort='Shanghai' WHERE Ort = 'Abu Dhabi';");

        db.conExecute("UPDATE gewinnt SET Ort='Shanghai' WHERE Ort = 'Abu Dhabi';");

        db.conAbfrage("SELECT f.Name , r.Ort , r.Datum FROM fahrer f , gewinnt g  , rennen r"
            + " where (f.Name = g.Name) AND (g.Ort = r.Ort) "
            + " ORDER BY r.Datum ;");

        anzahl = db.anzahlDatensaetze();
        System.out
        .println("Anzahl der Datensaetze Fahrer - Rennstrecke - aktualisiert: "
            + anzahl);

        while (db.neuerDatensatz()) {
            System.out.println("     " + db.getString("Name") + " --- "
                + db.getString(2) + " : " + db.getDate(3));
        }

        db.conClose();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        DBFormel_1_Access t = new DBFormel_1_Access();
        t.dbAufgabe();
    }
}
