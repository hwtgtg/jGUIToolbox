//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.BorderLayout;
import java.awt.Component;

public class BehaelterBorderlayout extends Behaelter  {
	/**
	 * Behaelter zum Zwischenspeichern vor dem versetzen nach NOSW
	 */
	static Behaelter tp ;
	
	/**
	 * Konstuktor fuer Hauptfenster
	 */
	public BehaelterBorderlayout() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor fuer Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterBorderlayout(int neueBreite, int neueHoehe) {
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
	public BehaelterBorderlayout(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public BehaelterBorderlayout(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}

	/**
	 * allgemeiner Konstuktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public BehaelterBorderlayout(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		obj = new CBehaelter();
		obj.setLayout(new BorderLayout());
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
		tp = new Behaelter(this,0,0,1,1);
	}

	public void verschiebeNord(IComponente comp){
		comp.getBasisComponente().ausContainerEntfernen();
		tp.add(comp.getBasisComponente(), 0);
		validate();
		obj.add(comp.getBasisComponente(), BorderLayout.NORTH);
	}
	public void verschiebeOst(IComponente comp){
		comp.getBasisComponente().ausContainerEntfernen();
		tp.add(comp.getBasisComponente(), 0);
		validate();
		obj.add(comp.getBasisComponente(), BorderLayout.EAST);
	}
	public void verschiebeSued(IComponente comp){
		comp.getBasisComponente().ausContainerEntfernen();
		tp.add(comp.getBasisComponente(), 0);
		validate();
		obj.add(comp.getBasisComponente(), BorderLayout.SOUTH);
	}
	public void verschiebeWest(IComponente comp){
		comp.getBasisComponente().ausContainerEntfernen();
		tp.add(comp.getBasisComponente(), 0);
		validate();
		obj.add(comp.getBasisComponente(), BorderLayout.WEST);
	}
	
	enum Richtung {
		NORD, OST, SUED, WEST, ZENTRAL
	}

	Richtung ausrichtung = Richtung.ZENTRAL;

	public void Zentral() {
		ausrichtung = Richtung.ZENTRAL;
	}

	public void Nord() {
		ausrichtung = Richtung.NORD;
	}

	public void Ost() {
		ausrichtung = Richtung.OST;
	}

	public void Sued() {
		ausrichtung = Richtung.SUED;
	}

	public void West() {
		ausrichtung = Richtung.WEST;
	}

	/**
	 * Fuer Interface IContainer
	 */
	@Override
	public Component add(Component comp, int index) {
		switch (ausrichtung) {
		case ZENTRAL:
			return this.obj.add(comp, index);
		case NORD:
			this.obj.add(comp, BorderLayout.NORTH);
			return comp;
		case OST:
			this.obj.add(comp, BorderLayout.EAST);
			return comp;
		case SUED:
			this.obj.add(comp, BorderLayout.SOUTH);
			return comp;
		case WEST:
			this.obj.add(comp, BorderLayout.WEST);
			return comp;
		default:
			return this.obj.add(comp, index);
		}
	}

	
}

