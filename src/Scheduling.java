package railway;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduling {
	//int p_no;
	int time = 0, t = 0;

	Data_transfer dt = new Data_transfer();
    int p_no=dt.np;
	public Platform pt[] = new Platform[p_no];
	public Prev pv[] = new Prev[p_no];
	public Queue<Train> outerleft = new LinkedList<Train>();
	public Queue<Train> outerright = new LinkedList<Train>();
	public Queue<Train> train = dt.getVal();
	public Queue<Train> q_train = new LinkedList<Train>();
	public Queue<Train> prior_t = new LinkedList<Train>();
	public Queue<Train> prior_t_a = new LinkedList<Train>();
	public Queue<Train> final_t = new LinkedList<Train>();
    
	public Scheduling() {

		for (int i = 0; i < p_no; i++) {
			pt[i] = new Platform();
			pv[i] = new Prev();
			
		}
		while(!train.isEmpty()) {
			if (train.peek().prior) {
				prior_t.add(train.remove());
				
			}
			else q_train.add(train.remove());
		}
	   // begin();
		/*while(time<2400){

			change(t);
			t++;
			System.out.print(time + "\t" +!outerleft.isEmpty()+"\t");

			for (int i = 0; i < p_no; i++) {
				System.out.print(pt[i].engage + "\t");
			}
			// System.out.print("\n");
		  	System.out.print(!outerright.isEmpty()+"\n");
			
		}*/
		
	}

	public void change(int tm) {
		if (tm % 1== 0) {
			// System.out.println(pt[2].engage);

			time++;

			if ((time % 100) % 60 == 0) {
				time = (time / 100) * 100 + 100;
			}

			//System.out.print(time + "\t");

			//System.out.print(!outerleft.isEmpty() + "\t");

			for (int i = 0; i < pt.length; i++) {
				//System.out.print(pt[i].engage + "\t");
			}
			//System.out.print(!outerright.isEmpty() + "\n");

			// departing the trains from the platforms
			for (int i = 0; i < p_no; i++) {
				if (pt[i].engage) {
					if (pt[i].trn.sdt() <= time) {
						pt[i].trn = null;
						pt[i].engage = false;
					}
				}
			}
			
            //first check the high priority train 
			for(int k=0; k < prior_t_a.size();k++){
				if(prior_t_a.peek().at==time){
					for(int l=0;l<p_no;l++){
						if(!pt[l].avail){
							pt[l].avail=true;
							pt[l].engage=true;
							Train tf=prior_t_a.remove();
							
							tf.sat=time;
							tf.platform=l+1;
							final_t.add(tf);
							pt[l].trn=tf;
							k--;
						}
					}
				}
			} 
			
			// case when both outers are empty
			if (outerleft.isEmpty() == true && outerright.isEmpty() == true) {

				for (int j = 0; j < q_train.size(); j++) {

					if (q_train.peek().at <= time) {
						

						Train tr = q_train.remove();j--;
						
						// iterate through platforms 
						for (int i = 0; i < p_no; i++) {

							// check if any platform empty
							if (!pt[i].engage && pt[i].avail) {
								if(!check_priority(i,tr)){
								tr.sat = time;
								tr.status = 2;
								tr.platform = i + 1;
								// System.out.println(tr.sdt());
								pt[i].trn = tr;
								pt[i].engage = true;
                                final_t.add(tr);
								
								break;
								}
								else continue;	
							}
						}
						// if all platforms filled
						if (tr.status == 0) {
							// System.out.println(tr.no);
							if (tr.dir == true) {
								outerleft.add(tr);
							} else {
								outerright.add(tr);
							}
							tr.status = 1;
						}

						// q_train.remove();
						// System.out.println(pt[2].engage);
					}
					// System.out.println(pt[2].engage+"here");
				}

			}

			// if any outer has any no. of trains
			else {

				for (int i = 0; i < p_no; i++) {
					// check if any platform is empty
					if (pt[i].engage == false && pt[i].avail) {

						// both outers have some train
						if (outerleft.isEmpty() == false
								&& outerright.isEmpty() == false) {
							if (outerleft.peek().at <= outerright.peek().at) {
								Train t = outerleft.peek();
								if(!check_priority(i,t)){
								t.sat = time;
								t.status = 2;
								t.platform = i + 1;
								pt[i].trn = t;
								pt[i].engage = true;
								final_t.add(t);
								outerleft.remove() ;
								}

							} else {
								Train t = outerright.peek();
								if(!check_priority(i,t)){
								t.sat = time;
								t.status = 2;
								t.platform = i + 1;
								pt[i].trn = t;
								pt[i].engage = true;
								final_t.add(t);
								outerright.remove();
								}
							}

						}
						// right outer is empty
						else if (outerleft.isEmpty() == false
								&& outerright.isEmpty() == true) {
							Train t = outerleft.peek();
							if(!check_priority(i,t)){
							t.sat = time;
							t.status = 2;
							t.platform = i + 1;
							pt[i].trn = t;
							pt[i].engage = true;
							final_t.add(t);
							outerleft.remove();
							}
						}

						// left outer is empty
						else if (outerleft.isEmpty() == true
								&& outerright.isEmpty() == false) {
							Train t = outerright.remove();
							t.sat = time;
							t.status = 2;
							t.platform = i + 1;
							pt[i].trn = t;
							pt[i].engage = true;
							final_t.add(t);
						}
						else if (outerleft.isEmpty() == true
								&& outerright.isEmpty() == true) {
							for (int i1 = 0; i1 < q_train.size(); i1++) {

								if (q_train.peek().at <= time) {
									Train tr = q_train.peek();
									if(!check_priority(i,tr)){
									i1--;
									tr.sat = time;
									tr.status = 2;
									tr.platform = i + 1;
									pt[i].trn = tr;
									pt[i].engage = true;
									final_t.add(tr);
									q_train.remove();
									}
									// 
								}
							}
						}
					}

					// we have to schedule the train coming....... in any
					// outer-
					for (int i1 = 0; i1 < q_train.size(); i1++) {

						if (q_train.peek().at <= time) {
							Train tr = q_train.remove();
							// if all platforms filled
							if (tr.status == 0) {
								if (tr.dir == true) {
									outerleft.add(tr);
								} else {
									outerright.add(tr);
								}
								tr.status = 1;
							}
							// q_train.remove();
						}
					}

				}

			}
			// System.out.println(q_train.peek().no+"here");

			// System.out.println("here");

		}
			
	}

	public void begin() {

		while (time < 2400) {

			// time handling
			t++;
			if (t % 10 == 0) {
				// \System.out.println(pt[2].engage);

				time++;

				if ((time % 100) % 60 == 0) {
					time = (time / 100) * 100 + 100;
				}

				//System.out.print(time + "\t");

				//System.out.print(!outerleft.isEmpty() + "\t");

				for (int i = 0; i < pt.length; i++) {
					//System.out.print(pt[i].engage + "\t");
				}
			//	System.out.print(!outerright.isEmpty() + "\n");

				// departing the trains from the platforms
				for (int i = 0; i < p_no; i++) {
					if (pt[i].engage) {
						if (pt[i].trn.sdt() <= time) {
							pt[i].trn = null;
							pt[i].engage = false;
						}
					}
				}
				
                //first check the high priority train 
				for(int k=0; k < prior_t_a.size();k++){
					if(prior_t_a.peek().at==time){
						for(int l=0;l<p_no;l++){
							if(!pt[l].avail){
								pt[l].avail=true;
								pt[l].engage=true;
								Train tf=prior_t_a.remove();
								
								tf.sat=time;
								tf.platform=l+1;
								pt[l].trn=tf;
								final_t.add(tf);
								k--;
							}
						}
					}
				} 
				
				// case when both outers are empty
				if (outerleft.isEmpty() == true && outerright.isEmpty() == true) {

					for (int j = 0; j < q_train.size(); j++) {

						if (q_train.peek().at <= time) {
							

							Train tr = q_train.remove();j--;
							
							// iterate through platforms 
							for (int i = 0; i < p_no; i++) {

								// check if any platform empty
								if (!pt[i].engage && pt[i].avail) {
									if(!check_priority(i,tr)){
									tr.sat = time;
									tr.status = 2;
									tr.platform = i + 1;
									// System.out.println(tr.sdt());
									pt[i].trn = tr;
									pt[i].engage = true;
                                    final_t.add(tr);
									
									break;
									}
									else continue;	
								}
							}
							// if all platforms filled
							if (tr.status == 0) {
								// System.out.println(tr.no);
								if (tr.dir == true) {
									outerleft.add(tr);
								} else {
									outerright.add(tr);
								}
								tr.status = 1;
							}

							// q_train.remove();
							// System.out.println(pt[2].engage);
						}
						// System.out.println(pt[2].engage+"here");
					}

				}

				// if any outer has any no. of trains
				else {

					for (int i = 0; i < p_no; i++) {
						// check if any platform is empty
						if (pt[i].engage == false && pt[i].avail) {

							// both outers have some train
							if (outerleft.isEmpty() == false
									&& outerright.isEmpty() == false) {
								if (outerleft.peek().at <= outerright.peek().at) {
									Train t = outerleft.peek();
									if(!check_priority(i,t)){
									t.sat = time;
									t.status = 2;
									t.platform = i + 1;
									pt[i].trn = t;
									pt[i].engage = true;
									final_t.add(t);
									outerleft.remove() ;
									}

								} else {
									Train t = outerright.peek();
									if(!check_priority(i,t)){
									t.sat = time;
									t.status = 2;
									t.platform = i + 1;
									pt[i].trn = t;
									pt[i].engage = true;
									final_t.add(t);
									outerright.remove();
									}
								}

							}
							// right outer is empty
							else if (outerleft.isEmpty() == false
									&& outerright.isEmpty() == true) {
								Train t = outerleft.peek();
								if(!check_priority(i,t)){
								t.sat = time;
								t.status = 2;
								t.platform = i + 1;
								pt[i].trn = t;
								pt[i].engage = true;
								final_t.add(t);
								outerleft.remove();
								}
							}

							// left outer is empty
							else if (outerleft.isEmpty() == true
									&& outerright.isEmpty() == false) {
								Train t = outerright.remove();
								t.sat = time;
								t.status = 2;
								t.platform = i + 1;
								pt[i].trn = t;
								pt[i].engage = true;
								final_t.add(t);
							}
							else if (outerleft.isEmpty() == true
									&& outerright.isEmpty() == true) {
								for (int i1 = 0; i1 < q_train.size(); i1++) {

									if (q_train.peek().at <= time) {
										Train tr = q_train.peek();
										if(!check_priority(i,tr)){
										i1--;
										tr.sat = time;
										tr.status = 2;
										tr.platform = i + 1;
										pt[i].trn = tr;
										pt[i].engage = true;
										final_t.add(tr);
										q_train.remove();
										}
										// 
									}
								}
							}
						}

						// we have to schedule the train coming....... in any
						// outer-
						for (int i1 = 0; i1 < q_train.size(); i1++) {

							if (q_train.peek().at <= time) {
								Train tr = q_train.remove();
								// if all platforms filled
								if (tr.status == 0) {
									if (tr.dir == true) {
										outerleft.add(tr);
									} else {
										outerright.add(tr);
									}
									tr.status = 1;
								}
								// q_train.remove();
							}
						}

					}

				}
				// System.out.println(q_train.peek().no+"here");

				// System.out.println("here");

			}
		}
	}

	public boolean check_priority(int i, Train t) {
		if(!prior_t.isEmpty()){
		if (time + t.halt > prior_t.peek().at) {
			pt[i].avail = false;
			prior_t_a.add(prior_t.remove());
			return true;

		}
		}
		return false;
	}

	public void print() {
		for(Train t:final_t){
			System.out.println(t.no+"\t"+t.platform+"\t"+t.sat+"\t"+t.sdt()+"\t"+t.prior);
		}
	}
          
}
