
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
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Gui extends JFrame implements ActionListener {

	private final int HEIGHT = 70;
	private final int WIDTH = 100;
	private final int refreshRate = 150;

	private boolean dead;
	private int board[][] = new int[HEIGHT][WIDTH];
	PlayerOne playerOne;
	PlayerOne playerTwo;
	private Image car;
	private Image carStop;
	private Image tracks;
	private Image gearbox;

	public static void main(String[] args) {
		new Gui();
	}

	public Gui() {
		this.setBackground(Color.white);
		addKeyListener(new TAdapter());

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 700);
		this.setTitle("Race");
		this.setUndecorated(true);
		this.setVisible(true);

		this.createBufferStrategy(2);

		ImageIcon iid = new ImageIcon(this.getClass().getResource(
				"car9.gif"));
		car = iid.getImage();
		iid = new ImageIcon(this.getClass().getResource(
				"car10.gif"));
		tracks = iid.getImage();
		iid = new ImageIcon(this.getClass().getResource(
				"car11.gif"));
		carStop = iid.getImage();

		iid = new ImageIcon(this.getClass().getResource(
				"gearbox.gif"));
		gearbox = iid.getImage();
		
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
	
		
		if (checkCollision(playerOne.getxCoord(), playerOne.getyCoord())){
						board[playerOne.getyCoord()][playerOne.getxCoord()] = 1;
						board[playerOne.getOldYCoord()][playerOne.getOldXCoord()] = 11;
						int minX = Math.min(playerOne.getxCoord(), playerOne.getOldXCoord());
						int minY = Math.min(playerOne.getyCoord(), playerOne.getOldYCoord());
						int maxX = Math.max(playerOne.getxCoord(), playerOne.getOldXCoord());
						int maxY = Math.max(playerOne.getyCoord(), playerOne.getOldYCoord());
						//TODO fixa svängarna med dx och dy i signs
						
						double dx = Math.abs(playerOne.getxCoord() - playerOne.getOldXCoord());
						double dy = Math.abs(playerOne.getyCoord() - playerOne.getOldYCoord());
						int kX, kY;
						if(dx == 0){
							kX = 0;
							kY = 1;
						}
							
						else if(dy == 0){
							kX = 1;
							kY = 0;
						}
						else{
							kX = 1;
							kY = 1;
						}
						for(int i= 1; i < playerOne.getSpeed(); i++){
							board[minY+kY*i][minX + kX*i] = 10;
							System.out.println("x: "+( minX+ kX*i) + " y: " + (minY+kY*i));
						}
		}

		// playerTwo.move();
		// if (Math.random() < playerTwo.getRandom()) {
		// moves++;
		// x[moves] = playerTwo.getxCoord();
		// y[moves] = playerTwo.getyCoord();
		// checkCollision(playerTwo.getxCoord(),playerTwo.getyCoord() );
		// }

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		draw();
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
			for (int j = 0; j < this.WIDTH; j++) {
				if (board[pY][pX] == 1) {
					dead = true;
					return false;
				}
			}
		}

		return true;
	}

	public void draw() {
		Graphics g = null;
		BufferStrategy bf = this.getBufferStrategy();
		
		try {
			g = bf.getDrawGraphics();
			
			if (!dead) {
				displayGear(g, playerOne.getSpeed());
				for (int i = 0; i < HEIGHT; i++) {
					g.setColor(Color.gray);
					g.drawLine(0, i*10, WIDTH*10, i*10);
					for (int j = 0; j < WIDTH; j++) {
						if(i==0){
							g.drawLine(j*10, 0, j*10, HEIGHT*10);
						}
						if (board[i][j] == 1)
							g.drawImage(car, j*10-8, i*10-5, this);
						else if( board[i][j] == 10)
							g.drawImage(tracks, j*10-4, i*10-3, this);
						else if( board[i][j] == 11)
							g.drawImage(carStop, j*10-8, i*10-5, this);
						
					}
				}
			} else
				gameOver(g);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();

	}

	public void gameOver(Graphics g) {
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

		g.setColor(Color.black);
		g.setFont(small);
		g.drawString(msg, (WIDTH*10 - metr.stringWidth(msg)) / 2, HEIGHT*10 / 2);
	}
	
	public void displayGear(Graphics g, int gear){
		
		
		String msg = ""+gear;
		Font small = new Font("Helvetica", Font.BOLD, 30);
		FontMetrics metr = this.getFontMetrics(small);
		
		g.setColor(new Color(244,244,244));
		g.fillRect((WIDTH*10 - 130), 5, 130, 50);
		
		g.setColor(Color.black);
		g.setFont(small);
		g.drawString(msg, (WIDTH*10 - metr.stringWidth(msg)-10) , 40);
		
		
		
		g.drawImage(gearbox, WIDTH*10 - gearbox.getWidth(this) - metr.stringWidth(msg)-20, 10, this);
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
			if (key == KeyEvent.VK_LEFT) {
				playerOne.setDirection(PlayerOne.LEFT);
			}

			if (key == KeyEvent.VK_RIGHT){
				playerOne.setDirection(PlayerOne.RIGHT);
			}

//			if (key == KeyEvent.VK_UP){
//				playerOne.setDirection(PlayerOne.UP);
//			}
//
//			if (key == KeyEvent.VK_DOWN){
//				playerOne.setDirection(PlayerOne.DOWN);
//			}
			if (key == KeyEvent.VK_SPACE){
				if (!dead) {
					move();
				}
			}
			if (key == KeyEvent.VK_F){
					playerOne.accelerate();
			}
			if (key == KeyEvent.VK_D){
				playerOne.brake();
			}
			
		}

	}

}
