import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;


public class wall {

	private Color color = Color.gray;
	private   final int width;
	private   final int height;
	private int x_value;
	private int y_value;
 
	public wall(Color c, int x, int y, int width, int height) {
		color = c;
		x_value = x;
		y_value = y;
		this.width = width;
		this.height = height;

	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
 		Shape rectangle = new Rectangle(x_value, y_value, width, height);
		g2d.setColor(color);
		g2d.fill(rectangle);
		g2d.dispose();
		 
	}
	
  
	public Shape showyourself() { 
 		return new Rectangle(x_value, y_value, width, height);
	}

		

	public int get_X_Value() {
		return x_value;
	}

	public int get_Y_Value() {
		return y_value;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

}
