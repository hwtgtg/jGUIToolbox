
/**
 * Beschreiben Sie hier die Klasse POPUPMenue_Beispiel.
 * 
 * Erzeugung: new MenuePopup()<BR/>
 * 
 * Aktiv schalten fuer alles:aktivierePopupMenue()<BR/>
 * 
 * Aktivieren mit rechter Maustaste
 * 
 * @author (Hans Witt)
 * @version (25.9.2019)
 */
public class POPUPMenue_Beispiel implements ITuWas {
	Quadrat qrotblau;

	Quadrat qgelb ;
	Quadrat qgruen ;
	Quadrat qmagenta ;
	/**
	 * Konstruktor für Objekte der Klasse POPUPMenue_Beispiel
	 */
	public POPUPMenue_Beispiel() {

		qrotblau = new Quadrat(100, 50, 100);

		qgelb = new Quadrat(0, 200, 100);
		qgelb.setzeFarbe("gelb");
		qgelb.rand();
		qgruen = new Quadrat(100, 200, 100);
		qgruen.setzeFarbe("gruen");
		qgruen.rand();
		qmagenta = new Quadrat(200, 200, 100);
		qmagenta.setzeFarbe("magenta");
		qmagenta.rand();
		
		// PopUp-Menue erzeugen
		MenuePopup m_POPUP = new MenuePopup();

		MenueEintrag mPOPUP_rot = new MenueEintrag("rot");
		mPOPUP_rot.setzeMnemonik('r');
		mPOPUP_rot.setzeLink(this, 200);
		mPOPUP_rot.setzeBeschreibung("rot");
		m_POPUP.menueEintragHinzufuegen(mPOPUP_rot);

		MenueEintrag mPOPUP_blau = new MenueEintrag("blau");
		mPOPUP_blau.setzeMnemonik('b');
		mPOPUP_blau.setzeLink(this, 210);
		mPOPUP_blau.setzeBeschreibung("blau");
		m_POPUP.menueEintragHinzufuegen(mPOPUP_blau);

		MenueRadioBehaelter mb = new MenueRadioBehaelter();

		MenueRadioTaste mr1 = new MenueRadioTaste("gelb");
		mr1.setzeLink(this, 250);
		mr1.setzeAktivierung("alt E");
		MenueRadioTaste mr2 = new MenueRadioTaste("gruen");
		mr2.setzeLink(this, 260);
		mr2.setzeAktivierung("alt U");
		MenueRadioTaste mr3 = new MenueRadioTaste("magenta");
		mr3.setzeLink(this, 270);
		mr3.setzeAktivierung("alt M");
		
		mb.addMenueRadioTaste(mr1);
		mb.addMenueRadioTaste(mr2);
		mb.addMenueRadioTaste(mr3);

		m_POPUP.menueEintragHinzufuegen(mr1);
		m_POPUP.menueEintragHinzufuegen(mr2);
		m_POPUP.menueEintragHinzufuegen(mr3);

		mr3.setzeGewaehlt();

		m_POPUP.aktivierePopupMenue();
		
	}

	public void tuWas(int ID) {
		
		if (ID == 200) {
			qrotblau.setzeFarbe("rot");
		} else if (ID == 210) {
			qrotblau.setzeFarbe("blau");
		} else if (ID == 250) {
			qgelb.fuellen();
		} else if (ID == 251) {
			qgelb.rand();
		} else if (ID == 260) {
			qgruen.fuellen();
		} else if (ID == 261) {
			qgruen.rand();
		} else if (ID == 270) {
			qmagenta.fuellen();
		} else if (ID == 271) {
			qmagenta.rand();
		}
		
	}

}
