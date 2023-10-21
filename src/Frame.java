import javax.swing.*;

import java.awt.*;

public class Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Frame(){
		this.setSize(750,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		Dimension ssize = Toolkit.getDefaultToolkit().getScreenSize();
		int cx = (int)(ssize.getWidth()-this.getWidth())/2;
		int cy = (int)(ssize.getHeight()-this.getHeight())/2;
		this.setLocation(cx, cy);
	}
}
