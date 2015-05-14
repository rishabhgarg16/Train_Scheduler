package railway;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class GUI extends Database {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame f;
	JLabel no,name;
	JLabel arr;
	JLabel dep;
	JLabel dir,prior,no1;
	JTextField t1,t4;
	JTextField t2;
	JTextField t3;
	JCheckBox c4,c5;
	JPanel p= new JPanel();
    
	JButton add,update,delete;
	
	 
	public GUI(){
		
		frame();
		btnAction();
	}
	
	public void frame(){
		
		f = new JFrame();
	    f.setVisible(true);
	    f.setSize(600,800);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    name = new JLabel("Admin Panel");
	    name.setBounds(200,50,100,100);
	    no = new JLabel("Train Number :");
	    no.setBounds(100,100,100,100);
	    arr = new JLabel("Arrival Time :");
	    arr.setBounds(100,150,100,100);

	    dep = new JLabel("Halt Time :");
	   dep.setBounds(100,200,100,100);

	    dir = new JLabel("Direction :");
	    dir.setBounds(100,250,100,100);
       
	    prior = new JLabel("Priority :");
	    prior.setBounds(100,300,100,100);
	    
	    t1 = new JTextField(10);
	    t1.setBounds(200,140,100,20);

	    t2 = new JTextField(10);
	    t2.setBounds(200,190,100,20);

	    t3 = new JTextField(10);
	    t3.setBounds(200,240,100,20);
	    
      c4=new JCheckBox();
      c4.setBounds(200,290,100,20);
      c5=new JCheckBox();
      c5.setBounds(200,340,100,20);
	    
     add=new JButton("Add train");
      add.setBounds(100,400,140,20);
      update=new JButton("Update train");  
      update.setBounds(300,400,140,20);
      
      delete=new JButton("Delete train");
      delete.setBounds(200,500,140,20);
      
      no1 = new JLabel("Train Number :");
	    no1.setBounds(100,420,100,100);
	    t4 = new JTextField(10);
	    t4.setBounds(200,460,100,20);
	    p.add(name);
        p.add(add);
        p.add(update);
        p.add(delete);
	    p.add(no);
	    p.add(t1);
	    p.add(arr);
	    p.add(t2);
	    p.add(dep);
	    p.add(t3);
	    p.add(dir);
	    p.add(c4);
	    p.add(c5);
	    p.add(prior);
	    p.add(no1);
	    p.add(t4);
	    
	    //p.add(add);
	    p.setLayout(null);
	    f.add(p);

		//to set  position
		
		//setVisible(true);
	    
	        
	}
	
    public void btnAction(){
    	
    	add.addActionListener(new ActionListener(){

    		public void actionPerformed(ActionEvent ev) {
				// TODO Auto-generated method stub
    			//System.out.print("Done01");
    			int s1 = Integer.parseInt(t1.getText());
    			int s2 = Integer.parseInt(t2.getText());
    			int s3 = Integer.parseInt(t3.getText());
    			boolean dr= c4.isSelected();
				boolean pr= c5.isSelected();
			
    			try{
    			    PreparedStatement ps= (PreparedStatement) con.prepareStatement("INSERT INTO TRAINS (Train No,Arrival Time, Halt Time) VALUES (?,?,?)");	
    				ps.setInt(1, s1);
    				ps.setInt(2, s2);
    				ps.setInt(3, s3);
    				//ps.setBoolean(4, dr);
    				//ps.setBoolean(5, pr);
    			    //st2.executeQuery();
    			/*	rs.moveToInsertRow();
    			rs.updateInt("Train No",s1);
				rs.updateInt("Arrival Time",s2);
				rs.updateInt("Halt Time",s3);
				rs.updateBoolean("Direction",dr);
				rs.updateBoolean("Priority",pr);
				rs.updateRow();
				JOptionPane.showMessageDialog(null,"Record Updated");
				st.close();
				rs.close();*/
    			    ps.executeUpdate();
    			    ps.close();
    			} 
				catch(Exception ex){
					System.err.println("hai"+ex);
					
				}				
			}   		
    		
    	});
    	   	
    	update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ev) {
				// TODO Auto-generated method stub
				
				int s1 = Integer.parseInt(t1.getText());
    			int s2 = Integer.parseInt(t2.getText());
    			int s3 = Integer.parseInt(t3.getText());
    			boolean dr= c4.isSelected();
				boolean pr= c5.isSelected();
				try{
					rs.updateInt("Train No",s1);
					rs.updateInt("Arrival Time",s2);
					rs.updateInt("Halt Time",s3);
					rs.updateBoolean("Direction",dr);
					rs.updateBoolean("Priority",pr);
					rs.updateRow();
					JOptionPane.showMessageDialog(null,"Record Updated");
				}catch(Exception e){
					e.printStackTrace();
				}
				//System.out.print(s1+"\t"+s2+"\t"+s3+"\t"+dr+pr);
			}
    		
  
    		
    	});
    }
}
