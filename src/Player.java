public class Player {

	private int xCoord;
	private int yCoord;
	private double direction;
	private double random;
	
	public static int LEFT = -1;
	public static int RIGHT = 1;
	public static int UP = -2;
	public static int DOWN = 2;
	
	

	

	public Player(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.direction = 1;
		this.random = 0.97;
	}

	public double getRandom() {
		return random;
	}

	public void setRandom(double random) {
		this.random = random;
	}

	public int getxCoord() {
		return xCoord;
	}
	

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public int getyCoord() {
		return yCoord;
	}


	public void move(){
		if(direction == LEFT)
			xCoord -= 10;
		if(direction == RIGHT)
			xCoord += 10;
		if(direction == DOWN)
			yCoord += 10;
		if(direction == UP)
			yCoord -= 10;
				
		
	}

}
