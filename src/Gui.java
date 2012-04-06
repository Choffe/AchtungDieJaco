import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

//TODO Fixa så att båda kan svänga samtidigt!
//TODO Fixa collision controll tillsammans med att eventuellt
// måste fixa om plan matrisen. Ska den verkligen vara så här?
// Borde ha en matris Board som har lika många platser som 
// pixlar. Noll = obesökt, 1 = Besökt = Krash.

//TODO Fixa så att det inte är sådanna hära bollar som printas
//TODO Fixa dubbel buffer painting

public class Gui extends JPanel implements ActionListener {

	private final int dotSize = 10;
	private final int HEIGHT = 700;
	private final int WIDTH = 1000;
	private final int refreshRate = 140;

	private boolean dead;
	private int x[] = new int[1000];
	private int y[] = new int[1000];
	Player playerOne;
	Player playerTwo;
	private Image ball;
	private int moves;
	private JButton btnRestart;
	private Timer timer;
	
	public Gui() {
		this.setBackground(Color.black);

		addKeyListener(new TAdapter());

		ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
		ball = iid.getImage();

		setFocusable(true);
		
		btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				restartGame();
				
			}
		});
		
		btnRestart.setVisible(false);
		this.add(btnRestart);
		
		initGame();
	}

	public void initGame() {
		dead = false;
		moves = 0;

		playerOne = new Player(10, 10);
		playerTwo = new Player(50, 20);
		x[moves] = playerOne.getxCoord();
		y[moves] = playerOne.getyCoord();

		timer = new Timer(refreshRate, this);
		timer.start();

	}
	
	private void restartGame(){
		for(int a : x)
			a = -10;
		
		for(int a : y)
			a = -10;
		
		btnRestart.setVisible(false);
		timer.stop();
		initGame();
	}

	private void move() {

		playerOne.move();
		if (Math.random() < playerOne.getRandom()) {
			moves++;
			x[moves] = playerOne.getxCoord();
			y[moves] = playerOne.getyCoord();
			checkCollision(playerOne.getxCoord(),playerOne.getyCoord() );
		}
		
		playerTwo.move();
		if (Math.random() < playerTwo.getRandom()) {
			moves++;
			x[moves] = playerTwo.getxCoord();
			y[moves] = playerTwo.getyCoord();
			checkCollision(playerTwo.getxCoord(),playerTwo.getyCoord() );
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (!dead) {
			move();

		}
		repaint();

	}

	private void checkCollision(int pX, int pY) {

		for (int z = 0; z < moves - 1; z++) {

			if (Math.hypot(Math.abs(pX - x[z]), Math.abs(pY - y[z]))
					< 1){
				dead = true;
			}
		}

		if (pY > HEIGHT) {
			dead = true;
		}

		if (pY < 0) {
			dead = true;
		}

		if (pX > WIDTH) {
			dead = true;
		}

		if (pX < 0) {
			dead = true;
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		if (!dead) {
			for (int z = 0; z < moves; z++) {
				g.drawImage(ball, x[z], y[z], this);

			}
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		} else
			
			gameOver(g);

	}

	public void gameOver(Graphics g) {
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
		
		btnRestart.setVisible(true);
		
	}
	

	private class TAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			playerOneMove(key);
			playerTwoMove(key);
			
		}

		private void playerTwoMove(int key) {
			if (key == KeyEvent.VK_Z){
				playerTwo.setDirection(-0.1);
			}

			if (key == KeyEvent.VK_X){
				playerTwo.setDirection(0.1);
			}

		}

		private void playerOneMove(int key) {
			if (key == KeyEvent.VK_COMMA){
				playerOne.setDirection(-0.1);
			}

			if (key == KeyEvent.VK_PERIOD){
				playerOne.setDirection(0.1);
			}
		}
	}

}
