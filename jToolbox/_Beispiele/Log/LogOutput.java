package logging;
//%$JGUIToolboxEX$%//ID fuer Toolboxdateien


/**
 * Interface for a class which is used as output for log messages
 * 
 * 
 * @author Martin Pabst, 2009
 * 
 * angepasst Hans Witt, 2012
 * 
 */
public interface LogOutput extends ITuWas{

	/**
	 * print given string if logLevel <= minimumLogLevel
	 * 
	 * @param s
	 */
	public void out(String s);

	/**
	 * print given string + '\n' if logLevel <= minimumLogLevel
	 * 
	 * @param s
	 */
	public void outln(String s);

	/**
	 * print given string in given color if logLevel <= minimumLogLevel
	 * 
	 * @param s
	 */
	public void outColor(String s, String farbe);

	/**
	 * print given string + '\n' in given color if logLevel <= minimumLogLevel
	 * 
	 * @param s
	 */
	public void outlnColor(String s, String farbe);

	/**
	 * set maximum value of progressbar; n == -1 sets progressbar in indefinite
	 * mode.
	 * 
	 * @param n
	 */
	public void setProgressBarMaximum(int n);

	/**
	 * set current value of progressbar
	 * 
	 * @param value
	 * @param s
	 */
	public void setProgressBarValue(int value, String s);

	/**
	 * Logausgabe loeschen 
	 */
	public void clear();

	public void setVisible();
}
