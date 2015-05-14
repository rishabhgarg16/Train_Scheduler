package railway;

import java.awt.*;
import javax.swing.*;
public class TableDemo {
	
	Scheduling sd3= new Scheduling();
	private Object[][] data;
    public void Display()
    {
        sd3.begin();
        
        JFrame frame = new JFrame("Table Demo - MyCoding.net");
        String columns[] = {"Train No.","Arrival","Departure","To_right(true)/To_left(false)","platform"};
        data = new Object[sd3.final_t.size()][5];
        int i=0;
        for(Train t:sd3.final_t){
        			 data[i][0]=t.no;
        		
        			 data[i][1]=t.sat;
        			
        			 data[i][2]=t.sat+t.halt;
        			
        			 data[i][3]=t.dir;
        			 data[i][4]=t.platform;
        			
        			i++;
        	}
        
        JTable table = new JTable(data,columns);
        frame.setVisible(true);
        frame.setBounds(0,0,500,500);
        frame.add(table.getTableHeader(),BorderLayout.PAGE_START);
        frame.add(table);
    }
}