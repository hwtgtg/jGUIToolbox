//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <h1>Zugriff auf eine MYSQL-Datenbank</h1><br/>
 * 
 * * conOeffnen erzeugt eine Verbindung zu einer MySQL-Datenbank.<br/>
 * 
 * * conClose schliesst die Verbindung zur Datenbank<br/>
 * 
 * * conAbfrage startet eine SQL-Abfrage. conAbfrage speichert das Ergebnis der
 * letzten Abfrage in einem ResultSet.<br/>
 * 
 * * conExecute startet eine SQL-Aktionen ohne Resultset wie Create, Update,
 * Insert<br/>
 * 
 * * conInsertAutoIncrement ist fuer ein Insert gedacht, das einen Datensatz mit
 * AutoIncrementFeld an erster Stelle erzeugt. Rueckgabewert ist der
 * AutoIncrementwert. (Bei Problemen siehe MySQL-Connector-Dokumentation)<br/>
 * 
 * Nach einem Select-Statement steht der Cursor des ResultSetz VOR dem ersten
 * Datensatz.<br/>
 * 
 * Eine Select-Statement liefert immer einen Result-Set. Zugriff erfolgt durch
 * die getXXX-Methoden der Klasse.<br/>
 * 
 * Diese Klasse kapselt viele get-Methoden des ResultSets. Dabei werden im
 * Fehlerfall Fehlermeldungen ignoriert. Damit wird erreicht, dass auch mit
 * Anfaengern in Java, die aber bereits im letzten Jahr
 * Datenbank/SQL-Erfahrungen gesammelt haben, mit Datenbankanwendungen
 * gearbeitet werden kann.<br/>
 * 
 * Die Datenbank wird mit SQL-Anweisungen wie Create / Select / Insert / Update
 * bearbeitet<br/>
 * 
 * Soll direkt auf den ResultSet zugegriffen werden, so findet man den letzten
 * ResultSet als Attribut res der Klasse. Diese Methoden des ResutSet erwarten
 * immer eine Kapselung mit try/catch.<br/>
 * 
 * * Fuer Transaktionen: Vor Transaktion-Steuerung mit AutoCommit(false) das
 * automatische Commit nach jeder Aktion ausschalten. AutoCommit wieder
 * einschalten!<br/>
 * 
 * public void AutoCommit(Boolean ON )<br/>
 * 
 * public void Commit()<br/>
 * 
 * public void Rollback()<br/>
 * 
 * 
 * Benoetigt:<br/>
 * 
 * MySQL Connector/J (http://dev.mysql.com/downloads/connector/j/).<br/> 
 * Das Jar-Archiv muss nach dem Entpacken dem Classpath hinzugefuegt werden.<BR>
 * getestet mit mysql-connector-java-5.1.14-bin.jar<br/>
 * <hr> 
 * Hilfreiche Quelle: Java ist eine Insel von Christian Ullenboom<br/>
 * 
 * http://openbook.galileocomputing.de/javainsel8/<br/>
 * 
 * 8.aktualisierte Auflage Kapitel 23<br/>
 * <hr>
 * @author Hans Witt
 * 
 * @version 1.00 01.06.2009
 */
public class DB_MySQL {

	public Connection db = null;
	public ResultSet res = null;

	public DB_MySQL() {
	}

	/**
	 * oeffnen der Datenbank
	 * 
	 * @param datenbank
	 * @param user
	 * @param kennwort
	 * @return Erfolg
	 */
	public boolean conOeffnen(String datenbank, String user, String kennwort) {

		try {
			// 1. JDBC-Treiber laden
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// 2. Datenbank-URL spezifizieren
			// String dbUrl = "jdbc:odbc:EAdressen";
			// String dbFilename = Optionen.dbName ;

			String dbUrl = "jdbc:mysql://" + datenbank;

			// 3. Connect zur Datenbank ausfuehren (Kennwort muss beim
			// Programmaufruf uebergeben werden)
			db = DriverManager.getConnection(dbUrl, user, kennwort);

			// scrollbare Cursor unterstuetzen
			@SuppressWarnings("unused")
			Statement stmt = db.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			// System.out.println("oeffnen mit Erfolg");
		}
		// Ggf. aufgetretene Exceptions abfangen
		catch (SQLException my_sqlex) {
			System.out.println("ERROR: Datenbankfehler: "
					+ my_sqlex.getMessage());
			System.err.println("SQL-State: " + my_sqlex.getSQLState());
			my_sqlex.printStackTrace();
			db = null;
			return false;
		} catch (Exception my_ex) {
			System.out
					.println("ERROR: Sonstiger Fehler: " + my_ex.getMessage());
			my_ex.printStackTrace();
			db = null;
			return false;
		}
		return true;
	}

	// ::::::::::::::::::::::::::::: Ab hier fuer alle Datenbanken gleich
	/**
	 * Upadate/Create/.. ohne Resultset mit SQL-String
	 * 
	 * @param updStr
	 * @return erfolgreich
	 */
	public boolean conExecute(String updStr) {
		boolean erg = true;
		try {
			// 4. Statement erzeugen
			Statement myStmt = db.createStatement();
			@SuppressWarnings("unused")
			int tmp = myStmt.executeUpdate(updStr);
		}
		// Ggf. aufgetretene Exceptions abfangen
		catch (SQLException my_sqlex) {
			System.out.println("Aktion: Datenbankfehler: "
					+ my_sqlex.getMessage());
			System.err.println("SQL-State: " + my_sqlex.getSQLState());
			System.out.println(updStr);
			// my_sqlex.printStackTrace();
			erg = false;
		} catch (Exception my_ex) {
			System.out.println("Abfrage: Sonstiger Fehler: "
					+ my_ex.getMessage());
			System.out.println(updStr);
			// my_ex.printStackTrace();
			erg = false;
		}
		return erg;
	}
	/**
	 * Abfrage mit SQL-String
	 * 
	 * @param queryStr
	 *            ResultSet wird in Public-Attribut res gespeichert.
	 */
	public void conAbfrage(String queryStr) {
		ResultSet myResult = null; // Ergebnissatz
		try {
			// 4. Statement erzeugen
			Statement myStmt = db.createStatement();
			// 5. Abfrageterm als String festlegen
			// String queryStr =
			// "SELECT DISTINCT Klasse , Vorname, Name FROM Adressen WHERE Email <> '' ";
			// 6. Abfrage ausfuehren
			myResult = myStmt.executeQuery(queryStr);
		}
		// Ggf. aufgetretene Exceptions abfangen
		catch (SQLException my_sqlex) {
			System.out.println("Abfrage: Datenbankfehler: "
					+ my_sqlex.getMessage());
			System.err.println("SQL-State: " + my_sqlex.getSQLState());
			System.out.println(queryStr);
			// my_sqlex.printStackTrace();
		} catch (Exception my_ex) {
			System.out.println("Abfrage: Sonstiger Fehler: "
					+ my_ex.getMessage());
			System.out.println(queryStr);
			// my_ex.printStackTrace();
		}
		res = myResult;
	}


	/**
	 * Schliessen der Datenbank
	 */
	public boolean conClose() {
		boolean erg = true;
		try {
			// 8. Connect zur Datenbank beenden
			if (db != null)
				db.close();
			else
				erg = false;
		}
		// Ggf. aufgetretene Exceptions abfangen
		catch (SQLException my_sqlex) {
			System.out.println("Datenbankschliessen: Datenbank: "
					+ my_sqlex.getMessage());
			System.err.println("SQL-State: " + my_sqlex.getSQLState());
			my_sqlex.printStackTrace();
			erg = false;
		} catch (Exception my_ex) {
			System.out.println("Datenbankschliessen: Sonstiger Fehler: "
					+ my_ex.getMessage());
			my_ex.printStackTrace();
			erg = false;
		}
		return erg;
	}

	public void AutoCommit(Boolean ON) {
		if (db != null) {
			try {
				db.setAutoCommit(ON);
			} catch (SQLException e) {
				// e.printStackTrace();
			}
		}
	}

	public void Commit() {
		if (db != null) {
			try {
				db.commit();
			} catch (SQLException e) {
				// e.printStackTrace();
			}
		}
	}

	public void Rollback() {
		if (db != null) {
			try {
				db.rollback();
			} catch (SQLException e) {
				// e.printStackTrace();
			}
		}
	}


	/**
	 * Anzahl im Resultset
	 * 
	 * @return Anzahl
	 */
	public int anzahlDatensaetze() {
		int rows = 0;
		try {
			res.last();
			rows = res.getRow();
			res.beforeFirst();

		} catch (SQLException e) {
			// e.PrintStackTrace();
		}
		return rows;
	}

	/**
	 * Schaltet auf einen neuen Datensatz Beachten: nach der Abfrage steht der
	 * Cursor der Datenbank <u>VOR</u> dem ersten Datensatz
	 * 
	 * @return Erfolg
	 */
	public boolean neuerDatensatz() {
		boolean erg = false;
		try {
			erg = res.next();
		} catch (SQLException e) {
			// e.PrintStackTrace();
		}
		return erg;
	}

	/**
	 * Zeigt, ob bei der letzten Leseaktion ein SQL-Null-Wert gelesen wurde.
	 * 
	 * @return War Nullwert
	 */
	public boolean warNull() {
		try {
			return res.wasNull();
		} catch (SQLException e) {
			// e.PrintStackTrace();
		}
		return false;
	}

	/**
	 * Bewegt den Cursor an eine bestimmte Zeile im Resutset
	 * 
	 * @param row
	 * @return Erfolg
	 */
	public boolean absolute(int row) {
		try {
			return res.absolute(row);
		} catch (SQLException e) {
			// e.PrintStackTrace();
		}
		return false;
	}

	/**
	 * Liefert aktuelle Zeilennummer
	 * 
	 * @return Fehler -> -1
	 */
	public int getRow() {
		try {
			return res.getRow();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return -1;
		}
	}

	/**
	 * 
	 */
	public void beforeFirst() {
		try {
			res.beforeFirst();
		} catch (SQLException e) {
			// e.PrintStackTrace();
		}
	}

	public boolean istVorErstemDatensatz() {
		try {
			return res.isBeforeFirst();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return false;
		}
	}

	public boolean istNachLetztemDatensatz() {
		try {
			return res.isAfterLast();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return false;
		}
	}

	public boolean isClosed() {
		try {
			return res.isClosed();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return true;
		}
	}

	/*
	 * Ab hier finden sich die get-Methoden fuer verschiedene Datentypen.
	 * Fehlermeldungen werden ignoriert.
	 */

	public boolean getBoolean(int columnIndex) {
		try {
			return res.getBoolean(columnIndex);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return false;
		}
	}

	public boolean getBoolean(String columnLabel) {
		try {
			return res.getBoolean(columnLabel);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return false;
		}
	}

	public double getDouble(int columnIndex) {
		try {
			return res.getDouble(columnIndex);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return Double.MIN_VALUE;
		}
	}

	public double getDouble(String columnLabel) {
		try {
			return res.getDouble(columnLabel);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return Double.MIN_VALUE;
		}
	}

	public float getFloat(int columnIndex) {
		try {
			return res.getFloat(columnIndex);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return Float.MIN_VALUE;
		}
	}

	public Float getFloat(String columnLabel) {
		try {
			return res.getFloat(columnLabel);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return Float.MIN_VALUE;
		}
	}

	public int getInt(int columnIndex) {
		try {
			return res.getInt(columnIndex);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return Integer.MIN_VALUE;
		}
	}

	public int getInt(String columnLabel) {
		try {
			return res.getInt(columnLabel);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return Integer.MIN_VALUE;
		}
	}

	public long getLong(int columnIndex) {
		try {
			return res.getLong(columnIndex);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return Long.MIN_VALUE;
		}
	}

	public long getLong(String columnLabel) {
		try {
			return res.getLong(columnLabel);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return Long.MIN_VALUE;
		}
	}

	public String getString(int columnIndex) {
		try {
			return res.getString(columnIndex);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return "#####";
		}
	}

	public String getString(String columnLabel) {
		try {
			return res.getString(columnLabel);
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return "#####";
		}
	}

	public String getDate(int columnIndex) {
		try {
			return (res.getDate(columnIndex)).toString();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return "#####";
		}
	}

	public String getDate(String columnLabel) {
		try {
			return (res.getDate(columnLabel)).toString();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return "#####";
		}
	}

	public String getTime(int columnIndex) {
		try {
			return (res.getTime(columnIndex)).toString();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return "#####";
		}
	}

	public String getTime(String columnLabel) {
		try {
			return (res.getTime(columnLabel)).toString();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return "#####";
		}
	}

	public String getTimestamp(int columnIndex) {
		try {
			return (res.getTimestamp(columnIndex)).toString();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return "#####";
		}
	}

	public String getTimestamp(String columnLabel) {
		try {
			return (res.getTimestamp(columnLabel)).toString();
		} catch (SQLException e) {
			// e.PrintStackTrace();
			return "#####";
		}
	}

	/**
	 * Fuer Insert, wenn <b>erstes Attribut</b> Autoincrementwert
	 * 
	 * @param updStr
	 * @return Autoincrementwert Fehler -> -1
	 */
	public int conInsertAutoIncrement(String updStr) {
		int erg = -1;
		try {
			// 4. Statement erzeugen
			Statement myStmt = db.createStatement();
			@SuppressWarnings("unused")
			int tmp = myStmt.executeUpdate(updStr,
					Statement.RETURN_GENERATED_KEYS);
			//
			// Example of using Statement.getGeneratedKeys()
			// to retrieve the value of an auto-increment
			// value
			//
			res = myStmt.getGeneratedKeys();
			if (res.next()) {
				erg = res.getInt(1);
			} else {
				// throw an exception from here
			}

		}
		// Ggf. aufgetretene Exceptions abfangen
		catch (SQLException my_sqlex) {
			System.out.println("Aktion: Datenbankfehler: "
					+ my_sqlex.getMessage());
			System.err.println("SQL-State: " + my_sqlex.getSQLState());
			System.out.println(updStr);
			// my_sqlex.printStackTrace();
		} catch (Exception my_ex) {
			System.out.println("Abfrage: Sonstiger Fehler: "
					+ my_ex.getMessage());
			System.out.println(updStr);
			// my_ex.printStackTrace();
		}
		return erg;
	}
}
