//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class BehaelterScroll extends BasisComponente implements IComponente,
		IContainer {

	public Zeichenflaeche panel;
	public JScrollPane scrollPane = null;

	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public BehaelterScroll() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterScroll(int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterScroll(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public BehaelterScroll(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterScroll(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {

		this.setLayout(new BorderLayout());

		panel = new Zeichenflaeche();
		panel.setLayout(null);

		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(panel);
		scrollPane.setPreferredSize(new Dimension(1000, 1000));
	
		behaelter.add(this, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);

		behaelter.validate();
	}

	public void setzeScrollbarDimension(int breite) {
		// Get the scroll-bar and make it a bit wider.
		JScrollBar sb = scrollPane.getVerticalScrollBar();
		sb.setPreferredSize(new Dimension(breite, 0));
		sb = scrollPane.getHorizontalScrollBar();
		sb.setPreferredSize(new Dimension(0,breite));
	}
	
	@Override
	public void expand(){
		panel.expand();
	}


	@Override
	public BasisComponente getBasisComponente() {
		return this;
	}

	@Override
	public void paintComponentSpezial(Graphics g) {
	}

    /**
     * Fuer Interface IContainer
     */
	@Override
	public Component add(Component comp, int index){
		return panel.add(comp,index);
	}

	
     public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
        int width, int height) {
    	 panel.setzeKomponentenKoordinaten(obj, x, y, width, height);
    }

    public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
    	panel.setzeKomponentenGroesse(obj, width, height);
    }

    @Override
	public void setzeKomponentenPosition(JComponent obj, int x, int y) {
    	panel.setzeKomponentenPosition(obj, x, y);
    }
	@Override
	public double getBehaelterZoom() {
		return 1;
	}
	
	// Eine Moeglichkeit, bei Ableitungen eine Bearbeitungsmehtode zu Ueberschreiben
	// Dazu wird als linkziel auftrag angegeben.
	// Die Methode heist auch auftrag;
	protected ITuWas auftrag = new ITuWas() {
		@Override
		public void tuWas(int ID) {
			final int linkID = ID ;
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
