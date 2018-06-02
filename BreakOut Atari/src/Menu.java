import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {

	
	public void render(Graphics g){
		
		Font font = new Font("arial",Font.BOLD,50);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("ATARI BREAKOUT",450/2 , 100);
	}
}
