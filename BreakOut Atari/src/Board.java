import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
/*
 * If there is anything wrong with this code or you dislike the way I did it, please feel free to 
 * change it how ever you see fit.
 * 
 */

public class Board  {
	//int padX = 200; // setting the size of x for paddle
	//int padY = 525; // setting the size of y for paddle

	private int x_value;
	private int y_value;
	private Color col;
	
	//public Paddle paddle = new Paddle(padX,padY); // make the paddle

	Color[] colors = new Color[]{Color.blue, Color.green, Color.yellow,Color.orange, Color.red};

	ArrayList<Brick> blocks = new ArrayList<Brick>();

	
	
	public Board(){ 			// this is intend to build the brick on the sky
		y_value=50;
		x_value = 5;
		//*****This is loop to put all the bricks into colors*****//
		for(int i =0; i<colors.length;i++){
			col = colors[i];
			
			while(x_value<=450){ // The value 450 represents the width of the board
				Brick brick = new Brick(col, x_value, y_value);
				blocks.add(brick);
				x_value+=50; // This value will add on to the x value
			}
			x_value = 5;
			y_value += 22;
		}
	}
	
	
	
	public void draw(Graphics g){
		//****This Draws the bricks****//
		//paddle.draw(g); // paddle is what;s underneath
		for(int i = 0;i < blocks.size();i++ ){
			blocks.get(i).draw(g);
		}

	}
}