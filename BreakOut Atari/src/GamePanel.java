import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.event.*;

public class GamePanel { // gamepanel doens't need to be a Jpanel
	JFrame frame = new JFrame("Breakout");
	private JPanel panel;

	private int padX = 200; //
	private int padY = 525; //
	Paddle pad = new Paddle(padX, padY); // are these truly needed if they are in board?
	Ball ball = new Ball(padX, padY);
	Board board = new Board();
	int speed = 3;
	private int width = 450;
	private int height = 600;
	private int endGameCount = 0;
	private int wOrL = 0;
	private Timer ticker = new Timer(speed, null);
	private boolean moving;
	private boolean dir = true;
	private boolean space = true;
	private boolean dir2 = true;
	wall wall1 = new wall(Color.red, 0, 0, 450, 1);
	wall wall2 = new wall(Color.green, 0, 0, 1, 600);
	wall wall3 = new wall(Color.blue, 450, 0, 1, 600);
	private JLabel label = new JLabel("you are screwed", JLabel.CENTER);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GamePanel().start();

	}

	private void start() {
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel() { // great syntax not learned
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGame(g);
			}

		};
		// panel.setBackground(Color.black);
		panel.setPreferredSize(new Dimension(width, height));

		frame.add(panel);
		frame.pack();

		setUpKeyMappings();
		// frame.repaint(); // needed or not

	}

	private void startTimer() { // so the space activate this so what
		ticker.addActionListener(new ActionListener() { // so no matter what I press it executes

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int brickx = 0;
				pad.x_value += pad.dx;
				if (ball.getX() <= 0) {
					dir2 = true;
				} else if (ball.getX() >= width) {
					dir2 = false;
				}
				if (dir2 == true) {
					ball.RIGHT();
				}
				if (dir2 == false) {
					ball.LEFT();
				}

				if (dir == true) {
					ball.UP();
					frame.repaint();
				}
				if (dir == false) {
					// ball.move(brickx);
					ball.DOWN();
					// frame.repaint();
				}

				for (int g = 0; g < board.blocks.size(); g++) {
					if ((testIntersection(ball.showyourself(), board.blocks.get(g).showyourself()))) {
						dir = false;
						brickx = board.blocks.get(g).get_X_Value();//
						// if(dir2 == false) {
						// dir2 = true;
						// }
						// else if(dir2== true) {
						// dir2 = false;
						// }
						board.blocks.get(g).setdeath(0);
						frame.repaint();

					}
				}

				if ((testIntersection(ball.showyourself(), pad.showyourself()))) {
					dir = true;
					ball.move();//
				}
				if ((testIntersection(ball.showyourself(), wall1.showyourself()))) {
					dir = false;
					frame.repaint();
				}
				if (ball.getX() >= width) {
					ball.move();
				}
				if ((testIntersection(ball.showyourself(), wall2.showyourself()))) {//
					dir = false;
					dir2 = true;
					frame.repaint();
				}
				if ((testIntersection(ball.showyourself(), wall3.showyourself()))) {//
					dir2 = false;
					frame.repaint();
				}
				if ((testIntersection(pad.showyourself(), wall2.showyourself()))) {
					pad.moveRight();
					frame.repaint();
				}
				if ((testIntersection(pad.showyourself(), wall3.showyourself()))) {
					pad.moveLeft();
					frame.repaint();
				}
				if (board.blocks.size() <= 0) {
					wOrL = 2;
					frame.add(new JLabel("you win"), JLabel.CENTER);
					frame.repaint();
				}

				if (ball.getY() > 620) {

					if (endGameCount > 4) {
						wOrL = 1;
						frame.repaint();

						panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "space");
						panel.getActionMap().put("space", new AbstractAction() {
							public void actionPerformed(ActionEvent e) {
								label.setAlignmentX(0);
								label.setAlignmentY(0);
								label.setForeground(Color.BLUE);
								frame.add(label);
								// frame.setVisible(false);
								// frame.dispose();
							}

						});

						panel.requestFocusInWindow();

					}

					ball.reset();
					pad.reset();
					space = true; // if space = true game would start, is it necessary
					endGameCount++;
					ticker.stop();

				}
				frame.repaint();


			}
		});

		if (moving = true) { // moving was never equaled to false? shouldn't it always be true, as long as
			// starting game is correct
			ticker.start();
		}

	}

	public void paintComponent(Graphics g) {
		panel.paintComponents(g);
		// paint parent's background
		// g.fill3DRect(100, 100, 100, 100, true );

	}

	private void drawGame(Graphics g) {
		// TODO Auto-generated method stub
		if (wOrL == 1) {
			g.drawString("YOU LOSE", 200, 300);
			frame.repaint();
		}
		if(board.blocks.size()==0) {
			g.drawString("YOU WIN", 225, 300);
			frame.repaint();
		}
		ball.draw(g);
		pad.draw(g);
		board.draw(g);
		frame.repaint(); // needed or not

	}

	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
		Area areaA = new Area(shapeA);
		areaA.intersect(new Area(shapeB));
		return !areaA.isEmpty();
	}

	private void setUpKeyMappings() {

		panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "space");
		panel.getActionMap().put("space", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				moving = true;
				if (space == true) { // so space act more as a counter
					startTimer();
					endGameCount++;
				}
				space = false; // what happpen if space is false

				panel.repaint();
			}
		});
		panel.requestFocusInWindow();

		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
		panel.getActionMap().put("left", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				pad.moveLeft();

				System.out.println("You have pressed LEFT!" + pad.getX());

				panel.repaint();
			}
		});
		panel.requestFocusInWindow();

		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
		panel.getActionMap().put("right", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				pad.moveRight();

				System.out.println("You have pressed RIGHT!" + pad.getX());

				panel.repaint();

			}
		});
		panel.requestFocusInWindow();
		//
		panel.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "Rright");
		panel.getActionMap().put("Rright", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				pad.release();

				System.out.println("You have released RIGHT!" + pad.getX());
				panel.repaint();
			}
		});
		panel.requestFocusInWindow();

		panel.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "Rleft");
		panel.getActionMap().put("Rleft", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				pad.release();

				System.out.println("You have released LEFT!" + pad.getX());
				panel.repaint();
			}
		});
		panel.requestFocusInWindow();
	}}