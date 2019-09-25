
/**
 * Beschreiben Sie hier die Klasse Menue_Beispiel.
 * 
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Menue_Beispiel implements ITuWas {
	Quadrat qrotblau;

	Quadrat qgelb ;
	Quadrat qgruen ;
	Quadrat qmagenta ;
	/**
	 * Konstruktor für Objekte der Klasse Menue_Beispiel
	 */
	public Menue_Beispiel() {

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
		
		MenueLeiste menueleiste = new MenueLeiste();
		// Eintrag in der Menüleiste
		Menue m_Datei = new Menue("Datei");
		m_Datei.setzeMnemonik('d');
		m_Datei.setzeBeschreibung("Datei-Menü");
		menueleiste.menueEintragHinzufuegen(m_Datei);

		MenueEintrag mDatei_Neu = new MenueEintrag("Neu ... ");
		mDatei_Neu.setzeMnemonik('n');
		mDatei_Neu.setzeAktivierung("ctrl N");
		mDatei_Neu.setzeLink(this, 100);
		mDatei_Neu.setzeBeschreibung("Neuer Eintrag");
		m_Datei.menueEintragHinzufuegen(mDatei_Neu);

		MenueEintrag mDatei_Oeffnen = new MenueEintrag("Speichern");
		mDatei_Oeffnen.setzeMnemonik('s');
		mDatei_Oeffnen.setzeAktivierung("ctrl S");
		mDatei_Oeffnen.setzeLink(this, 110);
		mDatei_Oeffnen.setzeBeschreibung("Speichern der Daten");
		m_Datei.menueEintragHinzufuegen(mDatei_Oeffnen);

		// Untermenue
		Menue me2 = new Menue("UnterMenue");
		me2.setzeMnemonik('s');
		m_Datei.menueEintragHinzufuegen(me2);

		MenueCheckBox mc = new MenueCheckBox("chk");
		mc.setzeMnemonik('C');
		mc.setzeAktivierung("alt C");
		mc.setzeLink(this, 130);
		me2.menueEintragHinzufuegen(mc);

		m_Datei.adSeparator();

		MenueEintrag mDatei_Beenden = new MenueEintrag("Beenden");
		mDatei_Beenden.setzeMnemonik('B');
		mDatei_Beenden.setzeAktivierung("alt F4");
		mDatei_Beenden.setzeLink(this, 999);
		mDatei_Beenden.setzeBeschreibung("Programm beenden");
		m_Datei.menueEintragHinzufuegen(mDatei_Beenden);

		// Zweiter Eintrag in der Menüleiste
		Menue m_Bearbeiten = new Menue("Bearbeiten");
		m_Bearbeiten.setzeMnemonik('B');
		menueleiste.menueEintragHinzufuegen(m_Bearbeiten);

		MenueEintrag mBearbeiten_rot = new MenueEintrag("rot");
		mBearbeiten_rot.setzeMnemonik('r');
		mBearbeiten_rot.setzeLink(this, 200);
		mBearbeiten_rot.setzeBeschreibung("rot");
		m_Bearbeiten.menueEintragHinzufuegen(mBearbeiten_rot);

		MenueEintrag mBearbeiten_blau = new MenueEintrag("blau");
		mBearbeiten_blau.setzeMnemonik('b');
		mBearbeiten_blau.setzeLink(this, 210);
		mBearbeiten_blau.setzeBeschreibung("blau");
		m_Bearbeiten.menueEintragHinzufuegen(mBearbeiten_blau);

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



		m_Bearbeiten.menueEintragHinzufuegen(mr1);
		m_Bearbeiten.menueEintragHinzufuegen(mr2);
		m_Bearbeiten.menueEintragHinzufuegen(mr3);

		// Letzter Eintrag in der Menüleiste, ganz rechts angeordnet
		menueleiste.fuellerHinzufuegen();

		Menue m_Hilfe = new Menue("Hilfe");
		m_Hilfe.setzeMnemonik('h');
		m_Hilfe.setzeLink(this, 900);
		menueleiste.menueEintragHinzufuegen(m_Hilfe);

		menueleiste.aktiviereMenue();

		mr3.setzeGewaehlt();

		
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
