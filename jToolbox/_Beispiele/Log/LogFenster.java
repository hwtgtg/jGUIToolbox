package logging;
//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

/**
 * 
 * angepasst Hans Witt, 2012
 * 
 */

public class LogFenster extends TextfensterFarbe implements LogOutput {

	/**
	 * 
	 */
	public LogFenster() {

	}

	/**
	 * @param behaelter
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public LogFenster(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		super(behaelter, neuesX, neuesY, neueBreite, neueHoehe);
	}

	/**
	 * @param behaelter
	 */
	public LogFenster(IContainer behaelter) {
		super(behaelter);
	}

	/**
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public LogFenster(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		super(neuesX, neuesY, neueBreite, neueHoehe);
	}

	/**
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public LogFenster(int neueBreite, int neueHoehe) {
		super(neueBreite, neueHoehe);
	}

	@Override
	public void out(String s) {
		print(s);
	}

	@Override
	public void outln(String s) {
		println(s);
	}

	@Override
	public void outColor(String s, String farbe) {
		print(s);
	}

	@Override
	public void outlnColor(String s, String farbe) {
		println(farbe, s);
	}

	@Override
	public void setProgressBarMaximum(int n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProgressBarValue(int value, String s) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void clear(){
		super.clear();
		
	}

	@Override
	public void tuWas(int ID) {
	}

	@Override
	public void setVisible() {
		
		
	}

}
