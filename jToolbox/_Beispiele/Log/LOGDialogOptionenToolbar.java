package logging;
//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

import logging.Log;
import logging.LogLevel;

public class LOGDialogOptionenToolbar implements ITuWas {

	public static int dx = 3;
	public static int dy = 3;
	public static int yLI = dy;
	public static int yRe = dy;
	public static int hoehe = 25;
	public static int breite = 100;

	public int bHoehe = 0;
	public int bBreite = 0;

	Behaelter behaelter;
	RadioBehaelter rtbehaelter;
	RadioTaste rtDebug;
	RadioTaste rtInfo;
	RadioTaste rtHilfe;
	RadioTaste rtWarnung;
	RadioTaste rtFehler;

	Taste tLogLoeschen;
	final static int IDloeschen = 999;

	public LOGDialogOptionenToolbar(IContainer iContainer) {
		behaelter = new Behaelter(iContainer);

		rtbehaelter = new RadioBehaelter(behaelter);
		rtbehaelter.setzeBeschreibungstext("LogInfo");
		rtbehaelter.setzeMitRand(true);

		int posY = dx;
		int posX = dy;
		rtDebug = new RadioTaste(rtbehaelter, "Debug", posX, posY, breite,
				hoehe);
		rtDebug.setzeHintergrundfarbe(Log.getFarbe(LogLevel.debug));
		rtDebug.setzeSchriftfarbe("weiss");
		rtDebug.setzeID(LogLevel.debug.ordinal() * 2);

		posX += dx + breite;
		rtInfo = new RadioTaste(rtbehaelter, "Info", posX, posY, breite, hoehe);
		rtInfo.setzeGewaehlt();
		rtInfo.setzeHintergrundfarbe(Log.getFarbe(LogLevel.info));
		rtInfo.setzeSchriftfarbe("weiss");
		rtInfo.setzeID(LogLevel.info.ordinal() * 2);

		posX += dx + breite;
		rtHilfe = new RadioTaste(rtbehaelter, "Hilfe", posX, posY, breite,
				hoehe);
		rtHilfe.setzeHintergrundfarbe(Log.getFarbe(LogLevel.hilfe));
		rtHilfe.setzeSchriftfarbe("weiss");
		rtHilfe.setzeID(LogLevel.hilfe.ordinal() * 2);

		posX += dx + breite;
		rtWarnung = new RadioTaste(rtbehaelter, "Warnung", posX, posY, breite,
				hoehe);
		rtWarnung.setzeHintergrundfarbe(Log.getFarbe(LogLevel.warnung));
		rtWarnung.setzeSchriftfarbe("weiss");
		rtWarnung.setzeID(LogLevel.warnung.ordinal() * 2);

		posX += dx + breite;
		rtFehler = new RadioTaste(rtbehaelter, "Fehler", posX, posY, breite,
				hoehe);
		rtFehler.setzeHintergrundfarbe(Log.getFarbe(LogLevel.fehler));
		rtFehler.setzeSchriftfarbe("weiss");
		rtFehler.setzeID(LogLevel.fehler.ordinal() * 2);

		/* Behaeltergroesse fuer rtBehaelter setzen */
		bHoehe = 2 * dy + hoehe;
		bBreite = 5 * breite + 6 * dx;
		rtbehaelter.setzeGroesse(bBreite, bHoehe);
		rtbehaelter.setzeLink(this);

		// Fuer LogLoeschen
		posX += dx + breite;

		tLogLoeschen = new Taste(behaelter, "LogClear", posX, posY, breite,
				hoehe);
		tLogLoeschen.setzeSchriftgroesse(14);
		tLogLoeschen.setzeLink(this);
		tLogLoeschen.setzeID(IDloeschen);
		posX += dx + breite;

		/* Behaeltergroesse setzen */
		bHoehe = 2 * dy + hoehe;
		bBreite = bBreite + dx + breite;
		behaelter.setzeGroesse(bBreite, bHoehe);
	}

	public IComponente getBehaelter() {
		return behaelter;
	}

	public void setzePosition(int posX, int posY) {
		rtbehaelter.setzePosition(posX, posY);
	}

	/*
	 * Hinweis: Radiotaste liefert bei gesetz und nicht_gesetzt verschiedene
	 * Werte ! Daher *2
	 */
	@Override
	public void tuWas(int ID) {
		if (ID == LogLevel.debug.ordinal() * 2) {
			Log.setMinimumLogLevel(LogLevel.debug);
			Log.outColorLn("Loglevel gesetzt:Debug", LogLevel.debug);
		} else if (ID == LogLevel.info.ordinal() * 2) {
			Log.setMinimumLogLevel(LogLevel.info);
			Log.outColorLn("Loglevel gesetzt:Info", LogLevel.info);
		} else if (ID == LogLevel.hilfe.ordinal() * 2) {
			Log.setMinimumLogLevel(LogLevel.hilfe);
			Log.outColorLn("Loglevel gesetzt:Hilfe", LogLevel.hilfe);
		} else if (ID == LogLevel.warnung.ordinal() * 2) {
			Log.setMinimumLogLevel(LogLevel.warnung);
			Log.outColorLn("Loglevel gesetzt:Warnung", LogLevel.warnung);
		} else if (ID == LogLevel.fehler.ordinal() * 2) {
			Log.setMinimumLogLevel(LogLevel.fehler);
			Log.outColorLn("Loglevel gesetzt:Fehler", LogLevel.fehler);
		} else if (ID == IDloeschen) {
			Log.clear();
		} else {

		}
	}
}
