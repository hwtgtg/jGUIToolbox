package logging;
//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * simple class for logging
 * 
 * 
 * @author Martin Pabst, 2009
 * 
 *         angepasst Hans Witt, 2012
 * 
 */
public class Log {

	public static final int aktivieren = 10;
	public static final int deaktivieren = 0;
	/**
	 * all log output is written to this object which implements LogOutput
	 */
	private static LogOutput lgOpt;

	public static LogOutput getLogOutput() {
		setMinimumLogLevel(LogLevel.info);
		if (lgOpt == null)
			lgOpt = new LogDummy();
		return lgOpt;
	}

	public static void setLogOutput(LogOutput logOutput) {
		lgOpt = logOutput;
	}

	/**
	 * minimum log level. log entries with log level lower than this are not
	 * printed
	 */
	static LogLevel minimumLogLevel = LogLevel.info;

	/**
	 * print given string if logLevel <= minimumLogLevel
	 * 
	 * @param s
	 * @param logLevel
	 */
	public static void out(String s, LogLevel logLevel) {
		if (logLevel.compareTo(minimumLogLevel) >= 0) {

			if (logLevel == LogLevel.fehler) {
				getLogOutput().outColor(s, "rot");
			} else {
				getLogOutput().out(s);
			}
		}
	}

	/**
	 * print given string + '\n' if logLevel <= minimumLogLevel
	 * 
	 * @param s
	 * @param logLevel
	 */
	public static void outLn(String s, LogLevel logLevel) {
		if (logLevel.compareTo(minimumLogLevel) >= 0) {
			if (logLevel == LogLevel.fehler) {
				getLogOutput().outlnColor(s, "rot");
			} else {
				getLogOutput().outln(s);
			}
		}
	}

	/**
	 * print given string in given color if logLevel <= minimumLogLevel
	 * 
	 * @param s
	 * @param logLevel
	 */
	public static void outColor(String s, LogLevel logLevel, String farbe) {
		if (logLevel.compareTo(minimumLogLevel) >= 0) {

			if (logLevel == LogLevel.fehler) {
				getLogOutput().outColor(s, "rot");
			} else {
				getLogOutput().outColor(s, farbe);
			}
		}
	}

	/**
	 * print given string + '\n' in given color if logLevel <= minimumLogLevel
	 * 
	 * @param s
	 * @param logLevel
	 */
	public static void outColorLn(String s, LogLevel logLevel, String farbe) {
		if (logLevel.compareTo(minimumLogLevel) >= 0) {
			if (logLevel == LogLevel.fehler) {
				getLogOutput().outlnColor(s, "rot");
			} else {
				getLogOutput().outlnColor(s, farbe);
			}
		}
	}

	public static void outColor(String s, LogLevel logLevel) {
		outColor(s, logLevel, getFarbe(logLevel));
	}

	public static void outColorLn(String s, LogLevel logLevel) {
		outColorLn(s, logLevel, getFarbe(logLevel));
	}

	public static LogLevel getMinimumLogLevel() {
		return minimumLogLevel;
	}

	public static void setMinimumLogLevel(LogLevel minimumLogLevel) {
		Log.minimumLogLevel = minimumLogLevel;
	}

	public static void setProgressBarMaximum(int n) {
		getLogOutput().setProgressBarMaximum(n);
	}

	public static void setProgressBarValue(int value, String s) {
		getLogOutput().setProgressBarValue(value, s);
	}

	public static String getFarbe(LogLevel logLevel) {
		switch (logLevel) {
		case debug:
			return "schwarz";
		case info:
			return "schwarz";
		case hilfe:
			return "blau";
		case warnung:
			return "magenta";
		case fehler:
			return "rot";
		default:
			return "schwarz";
		}
	}

	public static void clear() {
		getLogOutput().clear();
	}

	public static void setVisible() {
		getLogOutput().setVisible();
	}
}

class LogDummy implements LogOutput {

	@Override
	public void out(String s) {
	}

	@Override
	public void outln(String s) {
	}

	@Override
	public void outColor(String s, String farbe) {
	}

	@Override
	public void outlnColor(String s, String farbe) {
	}

	@Override
	public void setProgressBarMaximum(int n) {
	}

	@Override
	public void setProgressBarValue(int value, String s) {
	}

	@Override
	public void clear() {
	}

	@Override
	public void tuWas(int ID) {

	}

	@Override
	public void setVisible() {
	}
}
