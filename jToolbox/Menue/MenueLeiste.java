//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JMenuBar;

/**
 * <h1>Menueleiste</h1>
 * 
 * Komponente zur Erzeugung von Menues in der Toolbox.<BR/>
 * 
 * Erzeugung: new MenueLeiste()<BR/>
 * Die Menueleiste erscheint nach Ausfuehren der Methode aktiviereMenue()<BR/>
 * 
 * Hinzufuegen von Menueelementen: menueEintragHinzufuegen(MenueEintrag eintrag)<BR/>
 * 
 * 
 * @author Witt
 *
 */
public class MenueLeiste {
	
	private JMenuBar menuBar ;
	
	public MenueLeiste(){
		menuBar = new JMenuBar();
	}
	
	public void aktiviereMenue(){
		Zeichnung.gibJFrame().setJMenuBar( menuBar );
		Zeichnung.gibJFrame().validate();
	}

	public void menueEintragHinzufuegen( MenueEintrag eintrag ){
		menuBar.add( eintrag.getMenueItem() );
	}
	
	public void abstandHinzufuegen(int breite){
		menuBar.add(Box.createRigidArea(new Dimension(breite,0)));
	}
	
	/**
	 * Fuegt einen Zwischenraum ein, der die Menueeintrage vor und nach der Methode auf der ganzen Breite verteilt.
	 * Hilfreich, um z.B. das Hilfe-Menue rechtsbuendig zu stellen.
	 */
	public void fuellerHinzufuegen(){
		menuBar.add(Box.createHorizontalGlue());
	}
}
