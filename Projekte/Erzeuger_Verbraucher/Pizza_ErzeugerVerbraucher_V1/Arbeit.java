/**
 * Write a description of class Arbeit here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Arbeit { 

	Lager lager;
	int maxE = 3;
	Erzeuger[] e;

	int maxV = 5;
	Verbraucher[] v;

	/**
	 * Constructor for objects of class Arbeit
	 */
	public Arbeit() {
		new GUI();
		lager = new Lager();

		v = new Verbraucher[maxV];
		for (int i = 0; i < maxV; i++) {
			v[i] = new Verbraucher( i, "V_"+i,lager);
		}

		e = new Erzeuger[maxE];
		for (int i = 0; i < maxE; i++) {
			e[i] = new Erzeuger(i ,"E_" + i, lager);
		}


	}

	public static void main(String[] args) {
		new Arbeit();
	}
}
