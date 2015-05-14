package railway;
//dt is halt
public class Train {

	int no,at,halt,sat;
	boolean dir,prior;
	int platform,outer;
	int status;
	int length;
	public Train(){
		platform=0;
		//outer=0;
		status=0;
		//sat=0;	
		
		//dir=false;
	} 
	public int sdt(){
		int min;
		min= sat%100+halt;
		return (sat/100+min/60)*100+(min%60);
	}
}
