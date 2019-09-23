package logging;
//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * 
 * @author Martin Pabst, 2009
 * 
 * angepasst Hans Witt, 2012
 */
import java.io.IOException;
import java.io.OutputStream;

public class LogStream extends OutputStream {

	private static String farbe = "stream";

	private StringBuffer buffer = new StringBuffer(300);
	private LogLevel logLevel;

	public LogStream(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	@Override
	public void write(int b) throws IOException {
		buffer.append((char) b);
		if ((char) b == '\n') {
			flush();
		}
	}

	@Override
	public void flush() {
		Log.outColor(buffer.toString(), logLevel, farbe);
		buffer = new StringBuffer(300);
	}

}
