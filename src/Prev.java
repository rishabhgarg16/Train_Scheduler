package railway;
public class Prev {
	boolean p_engage;
	Train p_trn;
	Prev(){
		p_engage=false;
		p_trn=null;
	}
	public void setval(boolean b, Train t){
		p_engage=b;
		p_trn=t;
		
	}
}
