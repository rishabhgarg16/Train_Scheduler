package railway;
import java.sql.*;
import java.util.Arrays;

public class Database {

	Connection con;
	Statement st,st1,st2;
	ResultSet rs,rs1;
	
	public Database(){
		connect();
	}
	
	public void connect(){
		
		try{
		
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    con = DriverManager.getConnection("jdbc:odbc:RLDB");
		    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    rs = st.executeQuery("SELECT * from TRAINS ");
		    st1= con.createStatement();
		    rs1= st1.executeQuery("SELECT * from PLATFORMS");
		    st2= con.createStatement();
		    /*while(rs.next()){
		    	System.out.print(rs.getInt("Train No")+"\n");
		    	
		    	
		    }*/
		        		    
		}
		catch(Exception e){
	          System.err.println("1");
		      System.err.println(e);
		      System.err.println("\n2");
		      System.err.println(e.getMessage());
		      System.err.println("\n3");
		      System.err.println(e.getLocalizedMessage());
		      System.err.println("\n4");
		      System.err.println(e.getCause());
		      System.err.println("\n5");
		      System.err.println("\n6");
		      e.printStackTrace();
			
		}
	}
	/*public static void main(String args[]){
		//new Database();
		//Data_transfer dt= new Data_transfer();
		//dt.print();
		new Scheduling();
		//new GUI();
	}*/
}
