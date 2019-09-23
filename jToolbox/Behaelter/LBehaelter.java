//%$JGUIToolbox$%//ID fuer Toolboxdateien
/**
 * <h1>Layerd MausBehaelter</h1> erweitert den MausBehaelter.<br />
 * 
 * erweitert den Mausbehaelter um mehrere Ebenen. <br/>
 * 
 * Der Parameter <b>ebenen</b> gibt die Anzahl der Ebenen an. <br/>
 * Ebene 0 ist oben. <br/>
 * 
 * Die beiden hinzufuegen-Methoden fuegen zum entsprechenden Layer hinzu. <br/>
 * 
 * 
 * @author Hans Witt
 * 
 * 
 * @version Version: 1 (24.5.2013)
 * 
 *  <br/>
 * 
 */
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


public class LBehaelter extends MausBehaelter {

	public MausBehaelter [] layer ;

	public LBehaelter(int ebenen) {
		this(Zeichnung.gibZeichenflaeche(), ebenen, 0, 0, 400, 400);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public LBehaelter(int ebenen,int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), ebenen, 0, 0, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public LBehaelter(int ebenen,int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(),ebenen, neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public LBehaelter(IContainer behaelter,int ebenen) {
		this(behaelter,ebenen, 0, 0, 400, 400);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public LBehaelter(IContainer behaelter, final int ebenen , int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {

		super(behaelter, neuesX, neuesY, neueBreite, neueHoehe);

		layer = new MausBehaelter[ebenen];
		
		for (int i = ebenen-1 ; i >=0 ; i--){
			layer[i] = new MausBehaelter(this);
			layer[i].setzeGroesse(getBasisComponente().getWidth(),
					getBasisComponente().getHeight());
		}
		
		
	
		getBasisComponente().addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				for (int i = ebenen-1 ; i >=0 ; i--){
					
					layer[i].setzeGroesse(getBasisComponente().getWidth(),
							getBasisComponente().getHeight());
				}
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
			}
		});

	}
	
    public void hinzufuegen(IComponente comp , int ebene ) {
    	layer[ebene].hinzufuegen(comp);
    }

    public void hinzufuegenUndAnpassen(IComponente comp,int ebene) {
    	layer[ebene].hinzufuegenUndAnpassen(comp);
    }
    
    public IContainer getLayer(int i) {
    	return layer[i];
    }
	
}
