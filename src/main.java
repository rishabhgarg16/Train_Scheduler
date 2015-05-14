package railway;
import java.util.*;

import java.awt.event.*;
import java.awt.*;
import java.applet.*;

//import javax.swing.ImageIcon;

public class main extends Applet implements ActionListener {

	Scheduling sd1= new Scheduling();
	Scheduling sd2 = new Scheduling();
	private static final long serialVersionUID = 1L;
	int platformnumber = sd2.p_no;

	//public Image pic;
	TableDemo td=new TableDemo();
	int flag = 0, flag1 = 0;


	TextField timebox;
	Button b,b1;
    
	public void init() {

		setSize(1500, 1500);
		setBackground(Color.gray);
		timebox = new TextField();
		timebox.setBounds(200, 30, 150, 40);
		add(timebox);
		b = new Button("starter");
		b1=new Button("time table info");
		b.setBounds(200, 530, 50, 25);
		b1.setBounds(200,600,150,25);
		b.addActionListener(this);
		b1.addActionListener(this);
		
		add(b);
        add(b1);
		setLayout(null);// to set position

		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "starter")
			new GUI();
		if (ae.getActionCommand() == "time table info");
			td.Display(); 
	}

	public void start() {

		for (int i = 0; i < platformnumber; i++) {

			sd2.pt[i] = new Platform(180, 120 + 70 * i, 1180, 120 + 70 * i);
			// if(i==2){sd2.pt[i].toleft_d=0;sd2.pt[i].toright_d=1;}
		}
		//sd1.begin();
		//sd1.print();
	    gui_new();
	}

	public void gui_new() {

		// new GUI();
		sd2.time = 1058;
		int t = 0;

		try {
			while (sd2.time < 2400) {
				
				sd2.change(t);
				t++;
				//System.out.print(sd2.time + "\t" +!sd2.outerleft.isEmpty()+"\t");

				for (int i = 0; i < sd2.pt.length; i++) {
					//System.out.print(sd2.pt[i].engage + "\t");
				}
				// System.out.print("\n");
			  	//System.out.print(!sd2.outerright.isEmpty()+"\n");
				String s = Integer.toString(sd2.time);
				String s2 = Character.toString(s.charAt(0));
				String s3 = Character.toString(s.charAt(1));
				String s4 = Character.toString(s.charAt(2));
				String s5 = Character.toString(s.charAt(3));
				timebox.setBackground(Color.black);
				timebox.setForeground(Color.red);

				timebox.setFont(new Font("TimesRoman", Font.PLAIN, 20));
				timebox.setText(s2 + s3 + ":" + s4 + s5);

				repaint();

				Thread.sleep(1000);
				// for()

			}
		} catch (Exception r) {

		}
	}

	public void paint(Graphics e) {

		try {
			Graphics2D g2 = (Graphics2D) e;
			g2.setStroke(new BasicStroke(2));
			e.setFont(new Font("TimesRoman", Font.PLAIN, 22));
			e.setColor(Color.BLACK);
			e.drawString("OUTER", 10, 100);
			e.drawString("PLATFORM", 400, 100);
			e.drawString("OUTER", 1280, 100);
			e.drawLine(10, 120, 150, 120);
			e.drawLine(10, 190, 150, 190);
			e.drawLine(10, 260, 150, 260);
			e.drawLine(10, 330, 150, 330);
			e.drawLine(1210, 120, 1350, 120);
			e.drawLine(1210, 190, 1350, 190);
			e.drawLine(1210, 260, 1350, 260);
			e.drawLine(1210, 330, 1350, 330);
			e.drawLine(180, 120 + 70 * platformnumber, 1180,120 + 70 * platformnumber);
			// pic=new
			// ImageIcon("C:\\Users\\Aditya\\Desktop\\b-425102-animated_train.gif").getImage();
			// e.drawImage(pic,50,50,null);
			// erase(pic,w,h);

			e.setFont(new Font("TimesRoman", Font.PLAIN, 22));
			for (int i = 0; i < platformnumber; i++)
				sd2.pt[i].paint(e);
			// System.out.println(sd2.pt[0].engage);

			// draw on platform initialisation
			for (int i = 0; i < platformnumber; i++) {
				if (sd2.pv[i].p_engage) {
					if (sd2.pv[i].p_trn.dir) {
						if(sd2.pv[i].p_trn.prior)e.setColor(Color.blue);
						else e.setColor(Color.red);
						
						e.fillRect(630, 125 + 70 * i, 80, 60);
						e.setColor(Color.white);
						e.drawString(Integer.toString(sd2.pv[i].p_trn.no),630 + 40, 125 + 70 * i + 30);
					} else {
						if(sd2.pv[i].p_trn.prior)e.setColor(Color.blue);
						else e.setColor(Color.red);
	
						e.fillRect(720, 125 + 70 * i, 80, 60);
						e.setColor(Color.white);
						e.drawString(Integer.toString(sd2.pv[i].p_trn.no),720 + 40, 125 + 70 * i + 30);

					}
					// Thread.sleep(50);
				}
			}
			//draw outer initialisation
			int a=0;
			for(Train t:sd2.outerleft){
				if(t.prior)e.setColor(Color.blue);
				else e.setColor(Color.red);
								
				e.fillRect(10, 125+70*a,80,60);
				e.setColor(Color.white);
				e.drawString(Integer.toString(t.no),15, 125 + 70 *a + 30);				
				a++;
				if(a>=2)break;
			}
			a=0;
			for(Train t:sd2.outerright){
				if(t.prior)e.setColor(Color.blue);
				else e.setColor(Color.red);
				e.fillRect( 1220, 125+70*a, 80, 60);
				e.setColor(Color.white);
				e.drawString(Integer.toString(t.no),1230, 125 + 70 *a + 30);				
				a++;
				if(a>=2)break;
			}



			// departure functioning
			for (int i = 0; i < platformnumber; i++) {
				if (sd2.pv[i].p_engage) {
					if (sd2.pv[i].p_trn.dir) {
						if(sd2.pv[i].p_trn.prior)e.setColor(Color.blue);
						else e.setColor(Color.red);
						//e.setColor(Color.red);
						e.fillRect(630, 125 + 70 * i, 80, 60);
						e.setColor(Color.white);
						e.drawString(Integer.toString(sd2.pv[i].p_trn.no),630 + 40, 125 + 70 * i + 30);
					} else {
						if(sd2.pv[i].p_trn.prior)e.setColor(Color.blue);
						else e.setColor(Color.red);
						e.fillRect(720, 125 + 70 * i, 80, 60);
						e.setColor(Color.white);
						e.drawString(Integer.toString(sd2.pv[i].p_trn.no),720 + 40, 125 + 70 * i + 30);

					}
				}
				if (depinfo(i) != null) {
					Train t = depinfo(i);

					moveout(i, e, t);

				}
				if (arrinfo(i) != null) {
					Train t = arrinfo(i);
					movein(i, e, t);
				}

				if (sd2.pt[i].engage) {
					if (sd2.pt[i].trn.dir) {
						if(sd2.pt[i].trn.prior)e.setColor(Color.blue);
						else e.setColor(Color.red);
						e.fillRect(630,125 + 70 * i, 80, 60);
						e.setColor(Color.white);
						e.drawString(Integer.toString(sd2.pt[i].trn.no),630 + 40, 125 + 70 * i + 30);
					} else {
						if(sd2.pt[i].trn.prior)e.setColor(Color.blue);
						else e.setColor(Color.red);	
						e.fillRect(720, 125 + 70 * i, 80, 60);
						e.setColor(Color.white);
						e.drawString(Integer.toString(sd2.pt[i].trn.no),720 + 40, 125 + 70 * i + 30);

					}
				}
				sd2.pv[i].p_engage = sd2.pt[i].engage;
				sd2.pv[i].p_trn = sd2.pt[i].trn;
			}

		} catch (Exception r) {
		}

	}

	public Train depinfo(int i) {
		if (sd2.pv[i].p_engage) {
			if (sd2.pt[i].engage == false)
				return sd2.pv[i].p_trn;
			else
				return (sd2.pv[i].p_trn.no != sd2.pt[i].trn.no) ? (sd2.pv[i].p_trn)
						: null;
		}
		return null;
	}

	public Train arrinfo(int i) {
		if (sd2.pt[i].engage) {
			if (sd2.pv[i].p_engage == false)
				return sd2.pt[i].trn;
			else
				return (sd2.pv[i].p_trn.no != sd2.pt[i].trn.no) ? (sd2.pt[i].trn)
						: null;
		}
		return null;
	}

	public void moveout(int i, Graphics e, Train t) {
		try {
			for (int j = 0; j < 460; j += 90) {
				e.setColor(Color.gray);
				/*if (t.dir) {
					e.fillRect(180, 125 + 70 * i, 80, 60);
				} else {
					e.fillRect(1080, 125 + 70 * i, 80, 60);
				}*/

				if(t.prior)e.setColor(Color.blue);
				else e.setColor(Color.red);
				
				if (t.dir) {
					e.fillRect(630 + j, 125 + 70 * i, 80, 60);
					e.setColor(Color.white);
					e.drawString(Integer.toString(t.no), 630 + j + 40,125 + 70 * i + 30);

				} else {
					e.fillRect(720 - j, 125 + 70 * i, 80, 60);
					e.setColor(Color.white);
					e.drawString(Integer.toString(t.no), 720 - j + 40,125 + 70 * i + 30);

				}

				Thread.sleep(300);
				e.setColor(Color.gray);
				if (t.dir) {
					e.fillRect(630 + j, 125 + 70 * i, 80, 60);
				} else
					e.fillRect(720 - j, 125 + 70 * i, 80, 60);
			}

			}
		 catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void movein(int i, Graphics e, Train t) {
		try {
			for (int j = 0; j <=360; j += 90) {
				
				if(t.prior)e.setColor(Color.blue);
				else e.setColor(Color.red);
				if (t.dir) {
					e.fillRect(180 + j, 125 + 70 * i, 80, 60);
					e.setColor(Color.white);
					e.drawString(Integer.toString(t.no), 180 + j + 40,125 + 70 * i + 30);

				} else {
					e.fillRect(1080 - j, 125 + 70 * i, 80, 60);
					e.setColor(Color.white);
					e.drawString(Integer.toString(t.no), 1080 - j + 40,125 + 70 * i + 30);

				}

				Thread.sleep(200);
				e.setColor(Color.gray);
				if (t.dir) {
					e.fillRect(180 + j, 125 + 70 * i, 80, 60);
				} else
					e.fillRect(1080 - j, 125 + 70 * i, 80, 60);
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}

