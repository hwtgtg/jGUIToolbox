//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_Dezimalzahltest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Dezimal d1 = new Dezimal("17,327345");
		Dezimal d2 = new Dezimal("25,300");
		
		Dezimal erg = d1.addiere(d2) ;
		
		System.out.print(d1+" + ");
		System.out.print(d2+" = ");
		System.out.println(erg);		
		
		Dezimal d3 = new Dezimal("19,02");
		Dezimal d4 = new Dezimal("15,0930");
		
		erg = d3.subtrahiere(d4) ;
		
		System.out.print(d3+" - ");
		System.out.print(d4+" = ");
		System.out.println(erg);
		
		System.out.println("Nachkommastellen beim Ergebnis: " + erg.leseNachkommastellen());		
		erg=erg.negieren();
		System.out.println("Vorzeichen aendern: "+erg);		
		erg=erg.abs();
		System.out.println("Absolutwert: "+erg);	
		
		erg.setzeNachkommastellen(10);
		System.out.println("10 Nachkommastellen: "+erg);	
		erg.setzeNachkommastellen(1);
		System.out.println("1 Nachkommastelle: "+erg);	

		
		Dezimal d5 = new Dezimal("8,21");
		Dezimal d6 = new Dezimal("9,0");
		
		erg = d5.multipliziere(d6) ;
		
		System.out.print(d5+" * ");
		System.out.print(d6+" = ");
		System.out.println(erg);		
		
		double derg = erg.toDouble();
		System.out.println("als double : " +derg);		
		
		long lerg = erg.toLong();
		System.out.println("als long : " +lerg);		

		
		Dezimal d9 = new Dezimal("15,000000");
		Dezimal d10 = new Dezimal("18");
		
		erg = d9.dividiereGerundet(d10) ;
		
		System.out.print(d9+" /  ");
		System.out.print(d10+" = ");
		System.out.println(erg);		

		erg = d9.dividiere(d10, Dezimal.Art.gerundet);
		System.out.print(d9+" /  ");
		System.out.print(d10+" = ");
		System.out.println(erg);		

		d9 = new Dezimal("12,1000");
		d10 = new Dezimal("1,4");
		
		erg = d9.dividiereGerundet(d10) ;
		
		System.out.print(d9+" /  ");
		System.out.print(d10+" = ");
		System.out.println(erg);		

		d9 = new Dezimal("12,10");
		d10 = new Dezimal("1,4");
		
		erg = d9.dividiereGerundet(d10) ;
		
		System.out.print(d9+" /  ");
		System.out.print(d10+" = ");
		System.out.println(erg);		

		Dezimal d11 = new Dezimal("15,000000");
		Dezimal d12 = new Dezimal("24");
		
		erg = d11.dividiereExact(d12) ;
		
		System.out.print(d11+" /X  ");
		System.out.print(d12+" = ");
		System.out.println(erg);		
			
		erg = d11.dividiere(d12, Dezimal.Art.exakt);

		System.out.print(d11+" /X  ");
		System.out.print(d12+" = ");
		System.out.println(erg);		
		
		Dezimal d13 = new Dezimal("15,000000");
		Dezimal d14 = new Dezimal("18");
		
		erg = d13.dividiereExact(d14) ;
		
		System.out.print(d13+" /X  ");
		System.out.print(d14+" = ");
		System.out.println(erg);		
		System.out.println("Ergebins ist Zahl: "+ erg.isValid());
		

		Dezimal d15 = new Dezimal("15,000000");
		Dezimal d16 = new Dezimal("0,000");
		Dezimal d17 = new Dezimal("-0,000000");
		Dezimal d18 = new Dezimal("-0,0000008");
		
		System.out.println(d15+" <  0 " + d15.istKleinerNull());
		System.out.println(d15+" =  0 " + d15.istGleichNull());
		System.out.println(d15+" >  0 " + d15.istGroesserNull());

		System.out.println(d16+" <  0 " + d16.istKleinerNull());
		System.out.println(d16+" =  0 " + d16.istGleichNull());
		System.out.println(d16+" >  0 " + d16.istGroesserNull());
		
		System.out.println(d17+" <  0 " + d17.istKleinerNull());
		System.out.println(d17+" =  0 " + d17.istGleichNull());
		System.out.println(d17+" >  0 " + d17.istGroesserNull());
		
		System.out.println(d18+" <  0 " + d18.istKleinerNull());
		System.out.println(d18+" =  0 " + d18.istGleichNull());
		System.out.println(d18+" >  0 " + d18.istGroesserNull());

		System.out.println(d15+" <  " +d16 +" : " +d15.istKleiner(d16));
		System.out.println(d15+" =  " +d16 +" : " +d15.istGleich(d16));
		System.out.println(d15+" >  " +d16 +" : " +d15.istGroesser(d16));
		
		System.out.println(d15+" <  " +d17 +" : " +d15.istKleiner(d17));
		System.out.println(d15+" =  " +d17 +" : " +d15.istGleich(d17));
		System.out.println(d15+" >  " +d17 +" : " +d15.istGroesser(d17));

		System.out.println(d15+" <  " +d18 +" : " +d15.istKleiner(d18));
		System.out.println(d15+" =  " +d18 +" : " +d15.istGleich(d18));
		System.out.println(d15+" >  " +d18 +" : " +d15.istGroesser(d18));
		
		System.out.println(d16+" <  " +d15 +" : " +d16.istKleiner(d15));
		System.out.println(d16+" =  " +d15 +" : " +d16.istGleich(d15));
		System.out.println(d16+" >  " +d15 +" : " +d16.istGroesser(d15));
		
		System.out.println(d16+" <  " +d17 +" : " +d16.istKleiner(d17));
		System.out.println(d16+" =  " +d17 +" : " +d16.istGleich(d17));
		System.out.println(d16+" >  " +d17 +" : " +d16.istGroesser(d17));

		System.out.println(d16+" <  " +d18 +" : " +d16.istKleiner(d18));
		System.out.println(d16+" =  " +d18 +" : " +d16.istGleich(d18));
		System.out.println(d16+" >  " +d18 +" : " +d16.istGroesser(d18));

		System.out.println(d18+" <  " +d16 +" : " +d18.istKleiner(d16));
		System.out.println(d18+" =  " +d16 +" : " +d18.istGleich(d16));
		System.out.println(d18+" >  " +d16 +" : " +d18.istGroesser(d16));
		
		System.out.println(d18+" <  " +d17 +" : " +d18.istKleiner(d17));
		System.out.println(d18+" =  " +d17 +" : " +d18.istGleich(d17));
		System.out.println(d18+" >  " +d17 +" : " +d18.istGroesser(d17));

		System.out.println(d18+" <  " +d15 +" : " +d18.istKleiner(d15));
		System.out.println(d18+" =  " +d15 +" : " +d18.istGleich(d15));
		System.out.println(d18+" >  " +d15 +" : " +d18.istGroesser(d15));
		
		Dezimal d19 = new Dezimal("12,34");
		
		System.out.println(d19+" : " +(d19.toLongFeld())[0]+"*10^-"+(d19.toLongFeld())[1]);
		
		System.out.println("1500 -3 rechts "+(new Dezimal(1234567L,3)));
		
	}
	
}
