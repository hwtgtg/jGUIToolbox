//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
public class B_Ball extends Kreis {
	
	private double	richtung		= 0;
	private int		geschwindigkeit	= 10;
	private double	masse			= 100;
	
	private int		mX				= 10;
	private int		mY				= 10;
	
	private int		dx				= 10;
	private int		dy				= 10;
	
	public int rBreit = 300 ;
	public int  rHoch = 300 ;
	
	
	public B_Ball() {
		
	}
	
	public int leseRadius() {
		return radius;
	}
	
	public void setzeRadius(double neuerRadius) {
		masse = neuerRadius * neuerRadius * neuerRadius / 1000;
		setzeMasse(masse);
	}
	
	public void setzeMasse(double neueMasse) {
		masse = neueMasse;
		super.setzeGroesse((int) (10 * Math.exp(Math.log(masse) / 3)));
	}
	
	public double leseMasse() {
		return masse;
	}
	
	public int leseMittelpunktX() {
		return mX;
	}
	
	public int leseMittelpunktY() {
		return mY;
	}
	
	@Override
	public void setzeMittelpunkt(int neuerMx, int neuerMy) {
		mX = neuerMx;
		mY = neuerMy;
		setzePosition(mX - radius, mY - radius);
	}
	
	public void setzeGeschwindigkeit(int neueGeschwindigkeit) {
		geschwindigkeit = neueGeschwindigkeit;
		dx = (int) (Math.cos(richtung) * geschwindigkeit);
		dy = (int) (Math.sin(richtung) * geschwindigkeit);
	}
	
	public void setzeRichtung(int neueRichtung) {
		richtung = neueRichtung * Math.PI / 180;
		dx = (int) (Math.cos(richtung) * geschwindigkeit); 
		// Koordinatensystem in y-Richtung nach unten ! 
		dy = - (int) (Math.sin(richtung) * geschwindigkeit);
	}
	
	public int leseRichtung() {
		return (int) (richtung / Math.PI * 180);
	}
	
	public void bewegung() {
		mX += dx;
		mY += dy;
		spiegeln();
		setzeMittelpunkt(mX, mY);
	}
	
	public void spiegeln() {
		if (mX < radius) {
			mX = 2 * radius - mX;
			dx = -dx;
		}
		if (mY < radius) {
			mY = 2 * radius - mY;
			dy = -dy;
		}
		
		if (mX > (rBreit - radius)) {
			mX = 2 * (rBreit - radius) - mX;
			dx = -dx;
		}
		if (mY > (rHoch - radius)) {
			mY = 2 * (rHoch - radius) - mY;
			dy = -dy;
		}
	}
}
