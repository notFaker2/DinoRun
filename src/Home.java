import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Home implements ActionListener{
	Frame frame = new Frame();
	ImageIcon[] img=new ImageIcon[2];
	JButton button1 = new JButton("GO");
	JButton button2 = new JButton("About");
	JLabel label0 = new JLabel("Dino Run 1.0");
	JLabel label1 = new JLabel();
	Timer timer1;
	int imindx=0;
	Home(){
		img[0]=new ImageIcon(getClass().getResource("/assets/DinoRun1.png"));
        img[1]=new ImageIcon(getClass().getResource("/assets/DinoRun2.png"));
		button1.setBounds(30,150,75,25);
		button1.setFont(new Font("Times new Roman",Font.ITALIC,18));
		button1.addActionListener(this);
		button1.setFocusable(false);
		
		label0.setBounds(300, 30, 400, 25);
		label0.setFont(new Font("Times new Roman",Font.TRUETYPE_FONT,18));
		
		label1.setBounds(230, 202, 82, 94);
        label1.setIcon(img[0]);
        timer1 = new Timer(150,new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				// Image Switch
				switchimg();
			}
        	
        });
        timer1.start();
        
        button2.setBounds(30,200,95,25);
		button2.setFont(new Font("Times new Roman",Font.ITALIC,18));
		button2.setFocusable(false);
		button2.addActionListener(this);
		
		frame.add(label0);
		frame.add(button1);
		frame.add(label1);
		frame.add(button2);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button1) {
			frame.dispose();
			try {
				new Game();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==button2) {
			JOptionPane.showMessageDialog(null, "DinoRun1.0");
		}
	}
	public void switchimg() {
		label1.setIcon(img[imindx]);
		imindx++;
		if(imindx>1) {
			imindx=0;
		}
	}
}
