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
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gui extends JPanel implements ActionListener {

	private final int dotSize = 10;
	private final int HEIGHT = 700;
	private final int WIDTH = 1000;
	private final int refreshRate = 150;

	private boolean dead;
	private int board[][] = new int[HEIGHT][WIDTH];
	PlayerOne playerOne;
	PlayerOne playerTwo;
	private Image ball;

	public Gui() {
		this.setBackground(Color.white);
//TODO		this.setDoubleBuffered(true);
		addKeyListener(new TAdapter());

		ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
		ball = iid.getImage();

		setFocusable(true);

		initGame();
	}

	public void initGame() {
		dead = false;

		playerOne = new PlayerOne(15, 15);
		playerTwo = new PlayerOne(55, 25);

		Timer timer = new Timer(refreshRate, this);
		timer.start();

	}

	private void move() {

		playerOne.move();
//		(Math.random() < playerOne.getRandom()) {
			if(checkCollision(playerOne.getxCoord(),playerOne.getyCoord()))
				board[playerOne.getyCoord()][playerOne.getxCoord()] = 1;
//		}
		
//		playerTwo.move();
//		if (Math.random() < playerTwo.getRandom()) {
//			moves++;
//			x[moves] = playerTwo.getxCoord();
//			y[moves] = playerTwo.getyCoord();
//			checkCollision(playerTwo.getxCoord(),playerTwo.getyCoord() );
//		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!dead) {
			move();
		}
		repaint();
	}

	private boolean checkCollision(int pX, int pY) {
		if (pY > HEIGHT) {
			dead = true;
			return false;
		}
		if (pY < 0) {
			dead = true;
			return false;
		}
		if (pX > WIDTH) {
			dead = true;
			return false;
		}

		if (pX < 0) {
			dead = true;
			return false;
		}
		
		for (int i = 0; i < this.HEIGHT; i++) {
			for(int j = 0; j < this.WIDTH; j++){
			if (board[pY][pX] != 0) {
				dead = true;
				return false;
				}
			}
		}
		
		return true;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.blue);
		g.drawRect(0, 0, WIDTH, HEIGHT);
		
		if (!dead) {
			for(int i = 0; i < HEIGHT; i++){
				g.setColor(Color.gray);
				if(i % 10 == 0)
					g.drawLine(0, i, WIDTH, i);
				for(int j = 0; j < WIDTH; j++){
					if(board[i][j] != 0)
						g.drawImage(ball, j, i, this);
					if(j % 10 == 0)
						g.drawLine(j, 0, j, HEIGHT);
				}
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
	}
	

	private class TAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			playerOneMove(key);
			playerTwoMove(key);
			
		}

		private void playerTwoMove(int key) {
			if ((key == KeyEvent.VK_A)
					&& (playerTwo.getDirection() != PlayerOne.RIGHT)) {
				playerTwo.setDirection(PlayerOne.LEFT);
			}

			if ((key == KeyEvent.VK_D)
					&& (playerTwo.getDirection() != PlayerOne.LEFT)) {
				playerTwo.setDirection(PlayerOne.RIGHT);
			}

			if ((key == KeyEvent.VK_W)
					&& ((playerTwo.getDirection() != PlayerOne.DOWN))) {
				playerTwo.setDirection(PlayerOne.UP);
			}

			if ((key == KeyEvent.VK_S)
					&& (playerTwo.getDirection() != PlayerOne.UP)) {
				playerTwo.setDirection(PlayerOne.DOWN);
			}				
		}

		private void playerOneMove(int key) {
			if ((key == KeyEvent.VK_LEFT)
					&& (playerOne.getDirection() != PlayerOne.RIGHT)) {
				playerOne.setDirection(PlayerOne.LEFT);
			}

			if ((key == KeyEvent.VK_RIGHT)
					&& (playerOne.getDirection() != PlayerOne.LEFT)) {
				playerOne.setDirection(PlayerOne.RIGHT);
			}

			if ((key == KeyEvent.VK_UP)
					&& ((playerOne.getDirection() != PlayerOne.DOWN))) {
				playerOne.setDirection(PlayerOne.UP);
			}

			if ((key == KeyEvent.VK_DOWN)
					&& (playerOne.getDirection() != PlayerOne.UP)) {
				playerOne.setDirection(PlayerOne.DOWN);
			}			
		}
	}

}
