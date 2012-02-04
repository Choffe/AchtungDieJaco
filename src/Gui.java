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
	private int x[] = new int[1000];
	private int y[] = new int[1000];
	PlayerOne playerOne;
	private Image ball;
	private int moves;

	public Gui() {
		this.setBackground(Color.black);

		ImageIcon iid = new ImageIcon(this.getClass().getResource("dot.png"));
		ball = iid.getImage();

		playerOne = new PlayerOne(10, 10);

		setFocusable(true);
		
		initGame();
	}

	public void initGame() {
		dead = false;
		moves = 0;
		Timer timer = new Timer(refreshRate, this);
		timer.start();

	}

	private void move() {
		moves++;
		playerOne.move();
		x[moves]=playerOne.getxCoord();
		y[moves]=playerOne.getyCoord();
	
		
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
			

			for (int z = 0; z < moves; z++) {
				g.drawImage(ball, x[z], y[z], this);

				
			}
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
	}

}
