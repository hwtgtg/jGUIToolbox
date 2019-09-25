//%$JGUIToolbox$%//ID fuer Toolboxdateien

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * <h1>Popupmenue</h1>
 * 
 * Komponente zur Erzeugung von Popupmenues in der Toolbox.<BR/>
 * 
 * Erzeugung: new MenuePopup()<BR/>
 * 
 * Aktiv schalten fuer alles:aktivierePopupMenue()<BR/>
 * Aktiv schalten fuer eine graphische Komponente: aktivierePopupMenue(komponente.getBasisComponente())<BR/>
 * <BR/>
 * Hinzufuegen von Menueelementen wie bei der Menue-Komponente<BR/>
 * 
 * 
 * @author Witt
 *
 */
public class MenuePopup {

	JPopupMenu popupmenu;
	private int posX = 0;
	private int posY = 0;
	public Point getPoint(){
		return new Point(posX,posY);
	}
	
	public MenuePopup() {
		popupmenu = new JPopupMenu();
	}

	public void menueEintragHinzufuegen(MenueEintrag eintrag) {
		popupmenu.add(eintrag.getMenueItem());
	}

	public void adSeparator() {
		popupmenu.addSeparator();
	}

	public void aktivierePopupMenue() {
		MouseListener popupListener = new PopupListener(popupmenu);
		Zeichnung.gibZeichenflaeche().addMouseListener(popupListener);
	}
	
	public void aktivierePopupMenue(JPanel zfl ) {
		MouseListener popupListener = new PopupListener(popupmenu);
		zfl.addMouseListener(popupListener);
	}

	public void aktivierePopupMenue(BasisComponente comp) {
		MouseListener popupListener = new PopupListener(popupmenu);
		comp.addMouseListener(popupListener);
	}

	private class PopupListener extends MouseAdapter {

		JPopupMenu popup;

		PopupListener(JPopupMenu popupMenu) {
			popup = popupMenu;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		@SuppressWarnings("synthetic-access")
		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				posX=e.getX();
				posY=e.getY();
				popup.show(e.getComponent(), posX,posY);
			}
		}
	}
	
}
