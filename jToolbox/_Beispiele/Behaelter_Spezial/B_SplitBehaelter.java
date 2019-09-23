//%$JGUIToolboxEX$%//ID fuer Toolboxdateien
import java.awt.Rectangle;



public class B_SplitBehaelter implements ITuWas {

	BehaelterSplit bs ;
	
	public B_SplitBehaelter(){
		
	}
	
	@Override
	public void tuWas(int ID) {
		if(ID ==Zeichenflaeche.GROESSEANDERN){
			Rectangle re =  bs.panelRU.getBounds();
			System.out.println("x:"+re.x+" y:"+re.y+" b:"+re.width+" h:"+re.height);			
		}
	}
	

	private void test() {
		Zeichnung.gibZeichenflaeche().expand();
		bs = new BehaelterSplit(10,10,500,600);
		bs.panelRU.expand();
		bs.panelRU.setzeLinkBasis(this);
	}

	public static void main (String[] args){
		B_SplitBehaelter b = new B_SplitBehaelter();
		b.test();
	}
	
}
