package railway;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class Data_transfer{
      Database db = new Database();
     
      Queue<Train> t_q = new LinkedList<Train>();
      public Queue<Train> t_qnew ;
      int np=0;
      public Data_transfer(){
      try {
    	  
    	  while(db.rs.next())
		  {
			  Train t = new Train();
			  t.no= db.rs.getInt("Train No");
			  t.at= db.rs.getInt("Arrival Time");
			  t.halt= db.rs.getInt("Halt Time");
			  t.dir=db.rs.getBoolean("Direction");
			  t.prior=db.rs.getBoolean("Priority");
			  // System.out.println(t.no);
			  t_q.add(t);
			  
		  }
    	  while(db.rs1.next()){
    		  np = db.rs1.getInt("Number Of Platforms");
    		  
    	  }
    	 // System.out.print("here"+np);
    	  db.rs.close();
    	  db.st.close();
    	  db.rs1.close();
    	  db.st1.close();
    	  
	   }catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	   t_qnew= queueSorter(t_q);
      }
      
      public static Queue<Train> queueSorter(Queue<Train> t_q2) {
			//Queue<Train> q1 = new LinkedList<Train>();
		    //Queue<Train> q2 = new LinkedList<Train>();
    	  PriorityQueue<Train> pQueue = new PriorityQueue<Train>(t_q2.size(), new Comparator<Train>() {
    		  
              public int compare(Train t1, Train t2) {
                  return (int) t1.at-t2.at;
              }
          });
		  while(t_q2.size()!=0){
		//	  System.out.print(t_q2.peek().at);
			pQueue.add(t_q2.remove());  
			//System.out.println(pQueue.peek().at);
			  
		  }	
		 
		  return pQueue;
			/*while (!t_q2.isEmpty()) 
				q1.add(t_q2.remove());

				while (!q1.isEmpty()) {
					Train next = q1.remove();
				//	System.out.println(next.a + " " + q2);
					while (!q2.isEmpty() && next.at < q2.peek().at) 
						if (next.at < q2.peek().at){
						q1.add(q2.remove());
						}
						q2.add(next);
						
				}

			return q2;*/
		}
      
     public Queue<Train> getVal(){
          return t_qnew;	 
     }
     
     public void print(){
    	  while(t_qnew.size()!=0)
    	  {Train t = t_qnew.remove();
    	    System.out.print(t.no+"\t"+t.at+"\t"+t.halt+"\t"+t.dir+"\n");
         }
       }
 }