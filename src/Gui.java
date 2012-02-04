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
	private final int refreshRate = 140;

	private boolean dead;
	private int x[] = new int[1000];
	private int y[] = new int[1000];
	PlayerOne playerOne;
	private Image ball;
	private int moves;

	public Gui() {
		this.setBackground(Color.black);

		addKeyListener(new TAdapter());

		ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
		ball = iid.getImage();

		setFocusable(true);

		initGame();
	}

	public void initGame() {
		dead = false;
		moves = 0;

		playerOne = new PlayerOne(10, 10);
		x[moves] = playerOne.getxCoord();
		y[moves] = playerOne.getyCoord();

		Timer timer = new Timer(refreshRate, this);
		timer.start();

	}

	private void move() {
		moves++;
		playerOne.move();
		x[moves] = playerOne.getxCoord();
		y[moves] = playerOne.getyCoord();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (!dead) {
			move();
			checkCollision();
		}
		repaint();

	}

	private void checkCollision() {

		int pX = playerOne.getxCoord();
		int pY = playerOne.getyCoord();

		for (int z = 0; z < moves - 1; z++) {

			if (x[z] == pX && y[z] == pY) {
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
	}

	private class TAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

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
