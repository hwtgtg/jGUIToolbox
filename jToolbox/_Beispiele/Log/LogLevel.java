package logging;
//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * Log levels to characterize importance of log entries
 * 
 * @author Martin Pabst, 2009
 * 
 * angepasst Hans Witt, 2012
 * 
 */
public enum LogLevel {
	/**
	 * for debug purpose
	 */
	debug,
	/**
	 * additional information, not necessary
	 */
	info,
	/**
	 * useful information
	 */
	hilfe,
	/**
	 * warning
	 */
	warnung,
	/**
	 * severe warning, error
	 */
	fehler;


}
