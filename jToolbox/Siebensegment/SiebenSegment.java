
//%$JGUIToolbox$%//ID fuer Toolboxdateien

//%$JGUIToolbox$%//ID fuer Toolboxdateien
/**
 * Eine Ziffer einer Siebensegmentanzeige.
 * 
 * @author Hans Witt
 */
public class SiebenSegment implements IComponente {

	private Behaelter anzeige;
	private Segment sa;
	private Segment sb;
	private Segment sc;
	private Segment sd;
	private Segment se;
	private Segment sf;
	private Segment sg;
	private Kreis dp;

	public int hoehe = 100;
	public int sBreit = 60;
	public int sHoch = 20;

	// Positionen werden benoetigt fuer Groesse aendern
	int positionX = 0;
	int positionY = 0;

	String farbe = "rot";

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public SiebenSegment() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueHoehe
	 */
	public SiebenSegment(int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueHoehe);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueHoehe
	 */
	public SiebenSegment(int neuesX, int neuesY, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public SiebenSegment(IContainer behaelter) {
		this(behaelter, 0, 0, 100);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neuesX
	 * @param neuesY
	 * @param neueHoehe
	 */
	public SiebenSegment(IContainer behaelter, int neuesX, int neuesY,
			int neueHoehe) {
		anzeige = new Behaelter(behaelter);
		sa = new Segment(anzeige);
		sb = new Segment(anzeige);
		sc = new Segment(anzeige);
		sd = new Segment(anzeige);
		se = new Segment(anzeige);
		sf = new Segment(anzeige);
		sg = new Segment(anzeige);
		dp = new Kreis(anzeige);
		dp.unsichtbarMachen();

		setzeGroesse(neueHoehe);
		setzeFarbe(farbe);
		setzePosition(neuesX, neuesY);
	}

	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurueckliefert. Sie wird benoetigt, um ein Objekt zu einem anderen
	 * Behaelter hinzuzufuegen
	 */
	public BasisComponente getBasisComponente() {
		return anzeige.getBasisComponente();
	}

	public void zeigeDP(boolean dp) {
		if (dp) {
			this.dp.sichtbarMachen();
		} else {
			this.dp.unsichtbarMachen();
		}
	}

	public void anzeige(int i, boolean dp) {
		anzeige(i);
		zeigeDP(dp);
	}

	public void anzeigeMinus() {
		sa.rand();
		sb.rand();
		sc.rand();
		sd.rand();
		se.rand();
		sf.rand();
		sg.fuellen();
	}

	public void anzeige(int i) {
		switch (i) {
		case 0:
			sa.fuellen();
			sb.fuellen();
			sc.fuellen();
			sd.fuellen();
			se.fuellen();
			sf.fuellen();
			sg.rand();
			break;
		case 1:
			sa.rand();
			sb.fuellen();
			sc.fuellen();
			sd.rand();
			se.rand();
			sf.rand();
			sg.rand();
			break;
		case 2:
			sa.fuellen();
			sb.fuellen();
			sc.rand();
			sd.fuellen();
			se.fuellen();
			sf.rand();
			sg.fuellen();
			break;
		case 3:
			sa.fuellen();
			sb.fuellen();
			sc.fuellen();
			sd.fuellen();
			se.rand();
			sf.rand();
			sg.fuellen();
			break;
		case 4:
			sa.rand();
			sb.fuellen();
			sc.fuellen();
			sd.rand();
			se.rand();
			sf.fuellen();
			sg.fuellen();
			break;
		case 5:
			sa.fuellen();
			sb.rand();
			sc.fuellen();
			sd.fuellen();
			se.rand();
			sf.fuellen();
			sg.fuellen();
			break;
		case 6:
			sa.rand();
			sb.rand();
			sc.fuellen();
			sd.fuellen();
			se.fuellen();
			sf.fuellen();
			sg.fuellen();
			break;
		case 7:
			sa.fuellen();
			sb.fuellen();
			sc.fuellen();
			sd.rand();
			se.rand();
			sf.rand();
			sg.rand();
			break;
		case 8:
			sa.fuellen();
			sb.fuellen();
			sc.fuellen();
			sd.fuellen();
			se.fuellen();
			sf.fuellen();
			sg.fuellen();
			break;
		case 9:
			sa.fuellen();
			sb.fuellen();
			sc.fuellen();
			sd.rand();
			se.rand();
			sf.fuellen();
			sg.fuellen();
			break;
		default:
			sa.rand();
			sb.rand();
			sc.rand();
			sd.rand();
			se.rand();
			sf.rand();
			sg.rand();
		}

	}

	/**
	 * 
	 * 
	 */
	public void setzeGroesse(int neueHoehe) {
		hoehe = neueHoehe;
		sBreit = hoehe * 4 / 10;
		sHoch = hoehe / 8;
		anzeige.setzeGroesse(sBreit + 2 * sHoch, hoehe);
		sa.setzeGroesse(sBreit, sHoch);
		sb.setzeGroesse(sHoch, sBreit);
		sc.setzeGroesse(sHoch, sBreit);
		sd.setzeGroesse(sBreit, sHoch);
		se.setzeGroesse(sHoch, sBreit);
		sf.setzeGroesse(sHoch, sBreit);
		sg.setzeGroesse(sBreit, sHoch);
		dp.setzeGroesse(sHoch * 2 / 3);
		setzePositionSegmente();
		setzePosition(0, 0);
	}

	private void setzePositionSegmente() {
		sa.setzePosition(sHoch / 2, 0);
		sb.setzePosition(sBreit, sHoch / 2);
		sc.setzePosition(sBreit, sHoch / 2 + sBreit);
		sd.setzePosition(sHoch / 2, 2 * sBreit);
		se.setzePosition(0, sHoch / 2 + sBreit);
		sf.setzePosition(0, sHoch / 2);
		sg.setzePosition(sHoch / 2, sBreit);
		dp.setzePosition(sHoch / 2 + sBreit, 2 * sBreit);
	}

	public void setzePosition(int x, int y) {
		positionX = x;
		positionY = y;
		anzeige.setzePosition(positionX, positionY);

	}

	// Methode noetig zum Hinzufuegen mit Anpassung beim Behaelter
	// Die Enden werden relativ zur aktuellen position verschoben
	public void verschieben(int dx, int dy) {
		setzePosition(positionX + dx, positionY + dy);
	}

	/**
	 * Standardfunktionen
	 */

	public void sichtbarMachen() {
		sa.sichtbarMachen();
		sb.sichtbarMachen();
		sc.sichtbarMachen();
		sd.sichtbarMachen();
		se.sichtbarMachen();
		sf.sichtbarMachen();
		sg.sichtbarMachen();
	}

	/**
	 * Mache unsichtbar. Wenn es bereits unsichtbar ist, tue nichts.
	 */
	public void unsichtbarMachen() {
		sa.unsichtbarMachen();
		sb.unsichtbarMachen();
		sc.unsichtbarMachen();
		sd.unsichtbarMachen();
		se.unsichtbarMachen();
		sf.unsichtbarMachen();
		sg.unsichtbarMachen();
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(String neueFarbe) {
		sa.setzeFarbe(neueFarbe);
		sb.setzeFarbe(neueFarbe);
		sc.setzeFarbe(neueFarbe);
		sd.setzeFarbe(neueFarbe);
		se.setzeFarbe(neueFarbe);
		sf.setzeFarbe(neueFarbe);
		sg.setzeFarbe(neueFarbe);
		dp.setzeFarbe(neueFarbe);
	}

	/*
	 * gueltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeRandfarbe(String neueFarbe) {
		farbe = neueFarbe;
		sa.setzeRandfarbe(neueFarbe);
		sb.setzeRandfarbe(neueFarbe);
		sc.setzeRandfarbe(neueFarbe);
		sd.setzeRandfarbe(neueFarbe);
		se.setzeRandfarbe(neueFarbe);
		sf.setzeRandfarbe(neueFarbe);
		sg.setzeRandfarbe(neueFarbe);
	}

	public void fuellen() {
		sa.fuellen();
		sb.fuellen();
		sc.fuellen();
		sd.fuellen();
		se.fuellen();
		sf.fuellen();
		sg.fuellen();
	}

	public void rand() {
		sa.rand();
		sb.rand();
		sc.rand();
		sd.rand();
		se.rand();
		sf.rand();
		sg.rand();
	}

	// Eine Moeglichkeit, bei Ableitungen eine Bearbeitungsmehtode zu
	// Ueberschreiben
	// Dazu wird als linkziel auftrag angegeben.
	// Die Methode heist auch auftrag;
	protected ITuWas auftrag = new ITuWas() {
		@Override
		public void tuWas(int ID) {
			final int linkID = ID;
			new Thread(new Runnable() {
				public void run() {
					ausfuehren(linkID);
				}
			}).start();
		}

	};

	protected void ausfuehren(int ID) {

	}

}
