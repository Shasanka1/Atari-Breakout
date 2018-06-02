import java.awt.Color;
import java.awt.Graphics;

import javax.security.auth.login.AppConfigurationEntry;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 *  This class was just for making a ball bounce.
 *  We can use some of this for out Ball class on Monday. 
 * 
 */

public class BouncingBall extends JPanel{
	int x = 100, y = 100;
	int angleX =1, angleY =1;
	int width = 50, height = 20;

	public static void main(String[] args) throws InterruptedException{
		JFrame frame = new JFrame("Moving Ball"); 
		BouncingBall bb = new BouncingBall();
		frame.add(bb);
		frame.setSize(400, 400);
//		frame.setResizable();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true){
			bb.move();
			bb.repaint();
			Thread.sleep(5);
		}
	}
	
	public void move(){
		
		/*
		 * The move method tells the change in value for x and y as the ball is moving from one area to another. 
		 */
		int constant = 20;
		if(x + angleX < 0){
			angleX = 1;
		}
		else if(x + angleX> getWidth() -constant){
			angleX = -1;
		}
		else if(y + angleY<0){
			angleY = 1;
		}
		else if(y +angleY > getHeight()-constant){
			angleY = -1;
		}
		
		x = x+ angleX;
		y = y + angleY;
		
	}
	@Override
	public void paint(Graphics g){
		
		
		g.setColor(Color.BLUE);
		 g.fillOval(x, y, width, height);
	}


}
