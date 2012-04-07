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

//TODO Fixa så att det inte är sådanna hära bollar som printas
//TODO Fixa dubbel buffer painting

//TODO Fixa styrningen igen
//TODO Fixa  hur lång steglängde ska vara. runda upp? diagonalen är en krash.

public class Gui extends JPanel implements ActionListener {

	private final int HEIGHT = 700;
	private final int WIDTH = 1000;
	private final int refreshRate = 140;

	private boolean dead;
	private int board[][] = new int[1000][1000];
	Player playerOne;
	Player playerTwo;
	private Image ball;
	private int moves;
	private JButton btnRestart;
	private Timer timer;
	
	public Gui() {
		this.setBackground(Color.black);

		addKeyListener(new TAdapter());

		ImageIcon iid = new ImageIcon(this.getClass().getResource("smalldot.png"));
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

		timer = new Timer(refreshRate, this);
		timer.start();

	}
	
	private void restartGame(){
		for(int i = 0; i < HEIGHT; i++)
			for(int j = 0; j < WIDTH; j++)
				board[i][j] = 0;
		
		btnRestart.setVisible(false);
		timer.stop();
		initGame();
	}

	private void move() {

		playerOne.move();
		if (Math.random() < playerOne.getRandom()) {
			if(checkCollision(playerOne.getxCoord(), playerOne.getyCoord()))
				board[playerOne.getxCoord()][playerOne.getyCoord()] =1;
		}
		
		playerTwo.move();
		if (Math.random() < playerTwo.getRandom()) {
			if(checkCollision(playerTwo.getxCoord(), playerTwo.getyCoord()))
				board[playerTwo.getxCoord()][playerTwo.getyCoord()] =2;
		}
		


	}

	private boolean checkCollision(int xCoord, int yCoord) {
		if(xCoord <= 0 || xCoord >= this.WIDTH){
			dead = true;
			return false;
		}
		if(yCoord <= 0 || yCoord >= this.HEIGHT){
			dead = true;
			return false;
		}
		if(board[xCoord][yCoord] != 0){
			dead = true;
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (!dead) {
			move();

		}
		repaint();

	}



	public void paint(Graphics g) {
		super.paint(g);

		if (!dead) {
			for (int i = 0; i < 1000; i++) {
				for(int j = 0; j < 1000; j++){
					switch (board[i][j]) {
						case 1 :
							g.drawImage(ball, i, j, this);
							break;
						case 2 :
							g.drawImage(ball, i, j, this);
							break;
					}
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
