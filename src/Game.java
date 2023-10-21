import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

public class Game implements KeyListener{
	
	Frame frame = new Frame();
	
	ImageIcon[] images = new ImageIcon[6];
	Image img;
	
	JLabel label0=new JLabel();
    JLabel[] label1=new JLabel[2];
    JLabel label2=new JLabel();
    JLabel label3=new JLabel();
    JLabel[] label4=new JLabel[5];
    JLabel label5=new JLabel();
    JLabel label6=new JLabel();
    JLabel label7=new JLabel();
    Timer timer0;
    Timer timer;
    Timer timer2;
    Timer timer3;
    int imindx=1;
    int a,j,s,value,x;
    Random random = new Random();
    boolean b,c,d,f,g;
    
    int gspeed,gwidth,gpos;
    int dwidth,dspeed;
    int cpos,cheight;
    int jspeed;
    
    HighScore hiscore=new HighScore();
    
	
	Game() throws IOException{
			c=false;
			d=false;
			f=true;
			g=true;
			s=0;
			x=18;
			value=hiscore.getvalue();
			
		    //Resources
		 	images[0]=new ImageIcon(getClass().getResource("/assets/DinoJump.png"));
	        images[1]=new ImageIcon(getClass().getResource("/assets/DinoRun1.png"));
	        images[2]=new ImageIcon(getClass().getResource("/assets/DinoRun2.png"));
	        images[3]=new ImageIcon(getClass().getResource("/assets/ground.png"));
	        images[4]=new ImageIcon(getClass().getResource("/assets/cactus.png"));
	        images[5]=new ImageIcon(getClass().getResource("/assets/retry.png"));
	        
	        img=images[5].getImage();
	        Image reimg=img.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
	        ImageIcon resimage = new ImageIcon(reimg);
	        //Score
	        label0.setBounds(20, 30, 300, 25);
	        label0.setText("Your Score: "+s);
	        label0.setFont(new Font("Times New Roman",Font.ITALIC,18));
	        
	        //HighScore
	        label5.setBounds(450, 30, 200, 25);
	        label5.setFont(new Font("Times New Roman",Font.BOLD,18));
	        label5.setText("High Score: "+value);
	        
	        //EndGame
	        label6.setBounds(250, 70, 200, 25);
	        label6.setFont(new Font("Times New Roman",Font.BOLD,x));
	        label6.setText("Press 'S' to Continue");
	        timer0 = new Timer(400,new ActionListener() {
	        	@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
	        		startup();
					
				}
	        	
	        });
	        timer0.start();
	        label7.setBounds(250, 120, 75, 75);
	        label7.setIcon(resimage);
	        label7.addMouseListener((MouseListener) new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                // Handle the click action here, e.g., open a new frame
	                try {
	                	frame.dispose();
						new Game();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        });
	        
	        //Ground
	        gspeed=15;
	        gwidth=750;
	        gpos=285;
	        label1[0]=new JLabel();
	        label1[0].setBounds(0,gpos,gwidth,20);
	        label1[0].setIcon(images[3]);
	        label1[1]=new JLabel();
	        label1[1].setBounds(750,gpos,gwidth,20);
	        label1[1].setIcon(images[3]);
	        timer = new Timer(gspeed,new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// Ground Animation
					try {
						runground();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(c==true) {
						if(gspeed-5>0) {
							gspeed-=5;
						}
						c=false;
					}
				}
	        });
	        
	        
	      //Dino Animation
	        dwidth=82;
	        dspeed=150;
	        label2.setBounds(30, 202, dwidth, 94);
	        label2.setIcon(images[0]);
	        timer2 = new Timer(dspeed,new ActionListener() {
	        	@Override
				public void actionPerformed(ActionEvent e) {
					// Image Switch
					switchimg();
				}
	        	
	        });
	       
	        
	        //Add cactus
	        cheight=80;
	        cpos=gpos-cheight+15;
	        for(int i=0;i<5;i++) {
	        	label4[i]=new JLabel();
	        	label4[i].setIcon(images[4]);
	        	if(i==0) {
	        		label4[i].setBounds(500,cpos,32,cheight);
	        	}
	        	else {
	        		a=random.nextInt(6)+450;
	        		label4[i].setBounds(label4[i-1].getX()+32+a,cpos,32,cheight);
	        	}
	        }
	        
	        //Add Jump
	        jspeed=15;
	        b=false;
	        timer3= new Timer(jspeed,new ActionListener() {
	        	@Override
				public void actionPerformed(ActionEvent e) {
					// Jumping
					jump();
				}
	        		
	        });
	        
	        
	        frame.add(label1[0]);
	        frame.add(label1[1]);
	        frame.add(label2);
	        for(int i=0;i<5;i++) {
	        	frame.add(label4[i]);
	        }
	        frame.addKeyListener(this);
	        frame.add(label0);
	        frame.add(label5);
	        frame.add(label6);
	        frame.revalidate();
	        frame.setVisible(true);
	        
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar()=='s') {
			timer.start();
			timer2.start();
			d=true;
			label6.setText("");
			timer0.stop();
		}
		else if(e.getKeyChar()=='S') {
			timer.start();
			timer2.start();
			d=true;
			label6.setText("");
			timer0.stop();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==32) {
			if(d==true) {
			timer2.stop();
			timer3.start();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void runground() throws IOException {
		//Ground Animation
		for(int i=0;i<2;i++) {
			if(label1[i].getX()>=-750) {
				label1[i].setLocation(label1[i].getX()-5,label1[i].getY());
			}
			if(label1[i].getX()<-750) {
				if(i==0) {
					label1[i].setLocation(label1[i+1].getX()+gwidth-5, label1[i].getY());
				}
				if(i==1) {
					label1[i].setLocation(label1[i-1].getX()+gwidth-5, label1[i].getY());
				}
			}
		}
		//Cactus Animation
		for(int i=0;i<5;i++) {
			if(label4[i].getX()>=0) {
				label4[i].setLocation(label4[i].getX()-5, label4[i].getY());
			}
			if(label4[i].getX()<0) {
				a=random.nextInt(6)+450;
				if(i==0) {
					label4[i].setLocation(label4[4].getX()+32+a, label4[i].getY());
				}
				else {
					label4[i].setLocation(label4[i-1].getX()+32+a, label4[i].getY());
					if(i==4&&jspeed>0) {
						c=true;
						dspeed-=10;
						jspeed--;
						
					}
				}
			}
		}
		//GameOver Condition
		for(int i=0;i<5;i++) {
			if(label4[i].getX()<=(label2.getX()+dwidth-38)&&(label4[i].getX()+32)>=(label2.getX()+20)) {
				if((label2.getY()+94-25)>=label4[i].getY()) {
					timer2.stop();
					timer.stop();
					timer3.stop();
					frame.removeKeyListener(this);
					hiscore.upvalue(s);
					value=hiscore.getvalue();
					label5.setText("High Score: "+value);
					f=false;
					label6.setText("Game Over");
					frame.add(label7);
				}
			}
		}
		if(f==true) {
		s++;
		label0.setText("Your Score: "+s);
		}
	}
	
	public void switchimg() {
		label2.setIcon(images[imindx]);
		imindx++;
		if(imindx>2) {
			imindx=1;
		}
	}
	
	public void jump() {
		label2.setIcon(images[1]);
		if(b==false) {
			label2.setLocation(label2.getX(),label2.getY()-5);
			if(label2.getY()<=110) {
				b=true;
			}
		}
		if(b==true) {
			label2.setLocation(label2.getX(),label2.getY()+5);
			if(label2.getY()>=202) {
				b=false;
				label2.setLocation(label2.getX(),202);
				timer3.stop();
				timer2.start();
			}
		}
	}

	public void startup() {
		if(g==true) {
			label6.setFont(new Font("Times New Roman",Font.BOLD,x--));
			if(x==14) {
				g=false;
			}
		}
		if(g==false) {
			label6.setFont(new Font("Times New Roman",Font.BOLD,x++));
			if(x==18) {
				g=true;
			}
		}
	}
	
}
