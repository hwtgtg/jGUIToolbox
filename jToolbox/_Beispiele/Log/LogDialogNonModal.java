//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * 
 */
package logging;


/**
 * @author Witt
 * 
 */
public class LogDialogNonModal implements LogOutput {

	
	// Dialog
	public D_JGUIDialog dialog;
	// Container der Dialog-Komponente
	private IContainer dialogBehaelter;

	private LogFenster log;

	private BehaelterBorderlayout behaelter;

	/**
	 * @param titel
	 */
	public LogDialogNonModal(String titel) {

		// Dialog anlegen
		// Ein nicht modaler Dialog blockiert nicht !
		// Dialoge werden verborgen, nicht geschlossen!
		dialog = new D_JGUIDialog("Log-Ausgabe", D_JGUIDialog.NICHTMODAL);
		// Behaelter fuer Dialogkomponenten lesen
		dialogBehaelter = dialog.leseContainer();
		dialog.expand();

		behaelter = new BehaelterBorderlayout(dialogBehaelter);

		behaelter.verschiebeNord(new LOGDialogOptionenToolbar(behaelter)
				.getBehaelter());

		log = new LogFenster(behaelter);

		log.setzeEditierbar();

		Log.setMinimumLogLevel(LogLevel.info);
		Log.setLogOutput(log);

		// Groesse des Dialogfensters einstellen
		dialog.setResizable(true);
		dialog.setSize(700, 500);
		dialog.setVisible(true);
		dialog.zentrieren();
	}

	@Override
	public void out(String s) {
		log.out(s);
	}

	@Override
	public void outln(String s) {
		log.outln(s);
	}

	@Override
	public void outColor(String s, String farbe) {
		log.outColor(s, farbe);
	}

	@Override
	public void outlnColor(String s, String farbe) {
		log.outlnColor(s, farbe);
	}

	@Override
	public void setProgressBarMaximum(int n) {
		log.setProgressBarMaximum(n);
	}

	@Override
	public void setProgressBarValue(int value, String s) {
		log.setProgressBarValue(value, s);
	}

	@Override
	public void clear() {
		log.clear();
	}
	
	public void setVisible(){
		dialog.setVisible(true);
	}
	
	public void hide(){
		dialog.setVisible(false);
	}

	public static Object getLog() {
		return null;
	}

	@Override
	public void tuWas(int ID) {
		if (ID == Log.deaktivieren){
			hide();
		} else if (ID == Log.aktivieren){
			setVisible();
		}
		
	}
}
