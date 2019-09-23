//%$JGUIToolbox$%//ID fuer Toolboxdateien
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * <h1>Tastatur</h1>:
 * Klasse fuer die Tastatureingabe.<br/>
 * 
 * Nach dem Sun-Tutorial "How to Use Key Bindings"<br/>
 * http://java.sun.com/docs/books/tutorial/uiswing/misc/keybinding.html<br/><br/>
 * 
 * <h2>Konstruktor</h2>
 * <ul>
 * <li> Anmelden beim Programmfenster :public Tastatur() </li>
 * <li> Anmelden bei der Komponente ziel: public Tastatur(JComponent ziel, boolean global)<br/>
 *   wenn der Parameter <b>global=false</b> ist <br/>
 * Die Tastatur reagiert nur dann auf Tastendr&uuml;cke, wenn das <b>ziel</b> den Fokus hat.</li>
 * </ul>
 * 
 *  Ist der Kommunikationslink gesetzt, meldet das Objekt den Tastendruck. <br/>
 *  <br/>
 *  Das Link-Objekt implementiert <b><a href="ITastatur.html">ITastatur</a></b> ! <br/> 
 *  Es muss die Methode <b>public void tastenAktion(String rueckgabe)</b> implementieren 
 *  
 *  <br/>
 *  <br/>
 * 
 * <h2>Anmelden einer Taste: meldeAnTaste(...);</h2>
 * <ul>
 * <li>	Anmelden eines Zeichens: meldeAnTaste(char c, String rueckgabe) <br />
 * <b>WICHTIG:</b> Die Tastenbezeichnungen sind <b>GROSS</b>, die Tastenbezeichnungen fuer alt, ctrl, shift, pressed ... <b>klein</b> <BR/><BR/>
 *
 * Beispiel:<br/>
 * taste.meldeAnTaste('E', String "E"); // Grossbuchstabe E <br/>
 * </li>
 * <li> Anmelden einer Zeichenkette :meldeAnTaste(String s, String rueckgabe)<br/>
 * Die Zeichenkette beschreibt das Zeichen, auf das die Tastatur reagieren soll.<br/> 
 * Beispiele:<br/>
 * taste.meldeAnTaste("E", String "E"); // Buchstabe E <br/>
 * taste.meldeAnTaste("HOME", String "E"); // Die Taste Home-Pos1 <br/>
 * taste.meldeAnTaste("released A", String "E"); // Die Taste A loslassen<br/>
 * taste.meldeAnTaste("alt X", String "E"); // Die Tastenkombination ALT+A<br/>
 * taste.meldeAnTaste("ctrl SPACE", String "E"); // Die Tastenkombination STRG+Leertaste<br/>
 * taste.meldeAnTaste("alt ctrl released Q", String "E"); // Die Tastenkombination ALT+STRG+Q beim Loslassen<br/>
 * <hr>
 * Aus der Klasse KeyStroke:<br/>
 * Parses a string s . The string must have the following syntax:<br/>
 * <br/>
 * <b>&lt;modifiers&gt;* (&lt;typedID&gt; | &lt;pressedReleasedID&gt;)</b/><br/>
 * modifiers := shift | control | ctrl | meta | alt | altGraph<br/>
 * typedID := typed &lt;TypedKey&gt;<br/>
 * typedKey := string of length 1 giving Unicode character.<br/>
 * pressedReleasedID := (pressed | released) key<br/>
 * <br/>
 * key := KeyEvent key code name, i.e. the name following "VK_".<br/><br/>
 * <b>Eine Auswahl der KeyCodes</b><br/>
 * 
 * Symbolischer Name Bedeutung VK_0 ... VK_9 0 ... 9 VK_A ... VK_Z A ... Z<br/>
 * VK_ENTER Enter VK_SPACE Leertaste VK_TAB Tabulator VK_ESCAPE Escape<br/>
 * VK_BACK_SPACE Rueckschritt VK_F1 ... VK_F12 Die Funktionstasten F1 ... F12<br/>
 * VK_HOME, VK_END Home, End VK_INSERT, VK_DELETE Einfg, Entf VK_PAGE_UP,<br/>
 * VK_PAGE_DOWN BildHoch, BildRunter VK_DOWN, VK_UP CursorHoch, CursorRunter<br/>
 * VK_LEFT, VK_RIGHT CursorLinks, CursorRechts<br/>
 * <br/>
 * VK_ALT ALT-Taste VK_ALT_GRAPH AltGR-Taste VK_CAPS_LOCK Caps-Lock-Taste<br/>
 * VK_CONTROL Steuerungstaste VK_NUM_LOCK Numlock-Taste VK_KP_UP,VK_KP_DOWN<br/>
 * Tasten auf dem Ziffernblock VK_KP_LEFT,VK_KP_RIGHT VK_NUMPAD0 ... VK_NUMPAD9<br/>
 * <br/>
 * VK_PAUSE VK_PRINTSCREEN VK_SCROLL_LOCK VK_WINDOWS<br/>
 * <hr>
 * </li>
 * <li> <b>tstRichtung, tstAbisZ, tstaBISz, tstZiffern</b> melden ganze Tastenbl&oumlcke an. 
 *  
 * </li>
 * </ul>
 * <br/>
 * Der Parameter <b>rueckgabe</b> identifiziert die Taste. Gleichzeitig ist es der Wert, der beim
 * Aufruf von tastenAktion uebergeben wird. Verschiedene Tasten koennen den
 * gleichen Rueckgabewert haben. <br/>
 * Der Rueckgabewert ist ein immer String. Vergleichen <b>NICHT</b> mit == sondern mit <b>equals</b>. <br/>
 * <br/>  
 * Durch folgende Methode kann der Tastenwert auch ohne Kommunikation ausgewertet werden.<br/>
 * leseTaste()<br/>
 * leseTaste setzt den R&uuml;ckgabestring auf null. null signalisiert als <b>keine Taste</b>
 * 
 * <hr>
 * <b> Hinweis:</b> Ein Programm kann mehrere Objekte vom Typ Tastatur besitzen, die z.B. an verschiedne Stellen im Programm melden.
 * <hr>  
 * 
 * @author Witt
 * 
 */
public class Tastatur {
	
	JComponent	ziel;
	ITastatur	link		= null;
	String		rueckgabe;
	
	int			condition	= JComponent.WHEN_FOCUSED;
	
	/**
	 * Meldet die Tastatur beim Programmfenster an
	 */
	public Tastatur() {
		condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		this.ziel = Zeichnung.gibZeichenflaeche();
		
		this.ziel.requestFocusInWindow();
	}
	
	/**
	 * Meldet die Tastatur bei der Komponente und nicht beim Programmfenster an, wenn der Parameter <b>global=false</b> ist<br>
	 * 
	 * Die Tastatur reagiert nur dann auf Tastendr&uuml;ecke, wenn das <b>ziel</b> den Fokus hat.
	 *  	
	 * @param ziel
	 * @param global
	 */
	public Tastatur(JComponent ziel, boolean global) {
		if (global) {
			condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		} else {
			condition = JComponent.WHEN_FOCUSED;
		}
		this.ziel = ziel;
		this.ziel.requestFocusInWindow();

	}
	
	public void setzeLink(ITastatur link) {
		this.link = link;
	}
	
	public void meldeAnTaste(char c, String rueckgabe) {
		if (ziel != null) {
			ziel.getInputMap(condition).put(KeyStroke.getKeyStroke(c),
					rueckgabe);
			ziel.getActionMap().put(rueckgabe, new JTaAction(this, rueckgabe));
		}
	}
	
	
	public void meldeAnTaste(String s, String rueckgabe) {
		if (ziel != null) {
			ziel.getInputMap(condition).put(KeyStroke.getKeyStroke(s),
					rueckgabe);
			ziel.getActionMap().put(rueckgabe, new JTaAction(this, rueckgabe));
		}
	}
	
	public void rueckgabe(String rueckgabe) {
		final String STRrunnable = rueckgabe;
		if (link != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					link.tastenAktion(STRrunnable);
				}
			}).start();
		}
		
	}
	
	public String leseTaste() {
		String erg = new String(rueckgabe);
		rueckgabe = null;
		return erg;
	}
	
	/**
	 * Meldet die Richtungstasten an
	 */
	public void tstRichtung() {
		String[] richtungen = new String[] { "LEFT", "RIGHT", "UP", "DOWN" };
		for (String s : richtungen) {
			meldeAnTaste(s, s);
		}
	}
	
	/**
	 * Meldet alle Grossbuchstaben an<br />
	 * Vorsicht bei anderen Zeichens&auml;tzen bei Umlauten
	 */
	public static void tstAbisZ(Tastatur tastatur) {
		char[] chars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z' ,'Ä' , 'Ö' , 'Ü' }; // neues Zeichen-Array anlegen
		for (char c : chars) {
			tastatur.meldeAnTaste(c, "" + c);
		}
	}
	
	/**
	 * Meldet alle Kleinen Buchstaben an<br />
	 * Vorsicht bei anderen Zeichens&auml;tzen bei Umlauten
	 */
	public  void tstaBISz() {
		char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z' , 'ä' ,'ö' , 'ü' , 'ß'}; // neues Zeichen-Array anlegen
		for (char c : chars) {
			meldeAnTaste(c, "" + c);
		}
	}
	
	/**
	 * Meldet Ziffern an
	 */
	public void tstZiffern() {
		char[] chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9' }; // neues Zeichen-Array anlegen
		for (char c : chars) {
			meldeAnTaste(c, "" + c);
		}
	}
	
	// Das gleiche fuer eine abgeleitete Tastatur
	protected ITastatur tastaturauftrag = new ITastatur() {
		
		@Override
		public void tastenAktion(String rueckgabe) {
			final String STRrunnable = rueckgabe;
			if (link != null) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						link.tastenAktion(STRrunnable);
					}
				}).start();
			}
		}

	};
	protected void tastaturauftrag(String rueckgabe) {
		
		
	}

	
}

/*
 * Action-Klasse fuer Tastaturevents.
 * 
 * gibt den rueckgabe-Parameter zurueck bei Eintreten des Events
 */
@SuppressWarnings("serial")
class JTaAction extends AbstractAction {
	
	Tastatur	link;
	
	String		rueckgabe	= "";
	
	public JTaAction(Tastatur link, String rueckgabe) {
		this.rueckgabe = rueckgabe;
		this.link = link;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (link != null) {
			link.rueckgabe(rueckgabe);
		}
	}
	
}

//%$JGUIToolbox$%//ID fuer Toolboxdateien
/**
 * Interface fuer Kommunikations-Link von Tastatur.
 */
interface ITastatur {
	public void tastenAktion(String rueckgabe);
}
