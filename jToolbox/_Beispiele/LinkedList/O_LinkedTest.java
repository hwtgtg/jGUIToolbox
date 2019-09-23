//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
/**
 * 
 */
package jOtto;

import logging.Log;
import logging.LogDialogNonModal;
import logging.LogLevel;

/**
 * @author Witt
 *
 */
public class O_LinkedTest {

	public static O_LinkedList<String> lTest ;
	/**
	 * 
	 */
	public O_LinkedTest() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Log.setLogOutput(new LogDialogNonModal("Log"));

		
		lTest=new O_LinkedList<String>();
		Log.outColorLn("leer? "+lTest.isEmpty(), LogLevel.info);
		lTest.addFirst("Hallo");
		lTest.addLast("Next");

		Log.outColorLn("" , LogLevel.info);
		Log.outColorLn("leer? "+lTest.isEmpty(), LogLevel.info);

		Log.outColorLn("Erster Versuch", LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}

		lTest.clear();
		lTest.addSort("Hallo");
		lTest.addSort("Next");
		lTest.addSort("ZZZ");
		lTest.addSort("Mitte");
		
		
		Log.outColorLn("" , LogLevel.info);
		Log.outColorLn("leer? "+lTest.isEmpty(), LogLevel.info);
		Log.outColorLn("sortiert", LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}
		
		lTest.clear();
		lTest.addSort("Hallo");
		lTest.addSort("Next");
		lTest.addSort("Mitte");
		

		Log.outColorLn("" , LogLevel.info);
		Log.outColorLn("Erster Versuch", LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		
		Log.outColorLn("Nr 1: " +lTest.get(1) , LogLevel.info);
		Log.outColorLn("Nr 2: " +lTest.get(2) , LogLevel.info);

		Log.outColorLn("" , LogLevel.info);
		lTest.replace(2, "Ende");
		Log.outColorLn("Nr 2 ersetzt: " +lTest.get(2) , LogLevel.info);

		lTest.remove(1);
		Log.outColorLn("Nr 1 entfernt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		

		Log.outColorLn("" , LogLevel.info);
		lTest.add(1, "Neue Mitte");
		Log.outColorLn("Nr 1 ergaenzt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		

		Log.outColorLn("" , LogLevel.info);
		lTest.removeLast();
		Log.outColorLn("Ende entfernt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		
		
		Log.outColorLn("" , LogLevel.info);
		lTest.add(2, "Neues Ende");
		Log.outColorLn("Nr 2 ergaenzt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		

		Log.outColorLn("" , LogLevel.info);
		lTest.add(99, "Ganz neues Ende");
		Log.outColorLn("Nr 99 ergaenzt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		

		Log.outColorLn("" , LogLevel.info);
		lTest.remove(0);
		Log.outColorLn("Nr 0 entfernt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		

		Log.outColorLn("" , LogLevel.info);
		lTest.add(0, "0 Hinzufefuegt");
		lTest.removeLast();
		Log.outColorLn("0 dazu, LAST entfernt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		

		Log.outColorLn("" , LogLevel.info);
		lTest.removeFirst();
		Log.outColorLn("First  entfernt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		

		Log.outColorLn("" , LogLevel.info);
		lTest.add(1, "DAZU");
		lTest.add(3, "DAZU");
		Log.outColorLn("DAZU mehrfach hinzugefuegt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		
		
		Log.outColorLn("" , LogLevel.info);
		lTest.removeFirstOcurrence("DAZU");
		Log.outColorLn("erstes DAZU entfernt: " , LogLevel.info);
		for(String t : lTest){
			Log.outColorLn(t, LogLevel.info);
		}		
		
	}

}
