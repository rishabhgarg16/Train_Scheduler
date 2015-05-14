package railway;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Platform 
{
	int x3,y3,x4,y4;
	boolean engage=false;
	int train_number;
	//int toleft_d=1,toright_d=0;
	Train trn;
	boolean avail=true;
	
	
	
	public Platform(){
		trn=null;
		engage=false;
		avail=true;
	}
	public Platform(int x1,int y1,int x2,int y2)
	{
		x3=x1;
		x4=x2;
		y3=y1;
		y4=y2;
	}
	public void paint(Graphics e)
	{
		Graphics2D g2=(Graphics2D) e;
		g2.setStroke(new BasicStroke(2));
		e.setColor(Color.BLACK);
		e.drawLine(x3,y3,x4,y4);
	}
	
	
}

