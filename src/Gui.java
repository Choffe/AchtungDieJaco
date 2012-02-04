import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gui extends JPanel implements ActionListener {

	private final int dotSize = 3;
	private final int refreshRate = 140;

	private boolean dead;
	private int x[] = new int[100];
	private int y[] = new int[70];
	PlayerOne playerOne;
	private Image ball;

	public Gui() {
		this.setBackground(Color.black);

		ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
		ball = iid.getImage();

		playerOne = new PlayerOne(200, 200);

		setFocusable(true);

		initGame();
	}

	public void initGame() {
		dead = false;

		Timer timer = new Timer(refreshRate, this);
		timer.start();

	}

	private void move() {
		playerOne.move();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (!dead)
			move();
		repaint();

	}

	public void paint(Graphics g) {
		super.paint(g);

		if (!dead) {
			int[] p1x = playerOne.getxCoord();
			int[] p1y = playerOne.getyCoord();

			for (int z = 0; z < p1x.length; z++) {
				g.drawImage(ball, p1x[z], p1y[z], this);

				Toolkit.getDefaultToolkit().sync();
				g.dispose();
			}
		}
	}

}
