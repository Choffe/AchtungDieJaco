public class PlayerOne {

	private int xCoord;
	private int yCoord;
	private int direction;
	private int speed;
	private int oldYCoord;
	private int oldXCoord;
	public static int LEFT = -1;
	public static int RIGHT = 1;
	public static int UP = -2;
	public static int DOWN = 2;
	public static int UPLEFT = -3;
	public static int UPRIGHT = 3;
	public static int DOWNLEFT = -4;
	public static int DOWNRIGHT = 4;
	
	

	

	public PlayerOne(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.direction = 1;
		this.speed = 1;
	}




	public int getxCoord() {
		return xCoord;
	}
	
	public int getDirection(){
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getyCoord() {
		return yCoord;
	}
	public void accelerate(){
		speed++;
	}
	public void brake(){
		if(speed > 1)
			speed--;
	}

	public int[] getLastMove(){
		
		int[] move = new int[4];
		move[0] = oldXCoord;
		move[1] = oldYCoord;
		move[2] = xCoord;
		move[3] = yCoord;
		
		return move;
	}


	public void move(){
		oldXCoord = xCoord;
		oldYCoord = yCoord;
		
		switch (direction) {
		case 1 :
				xCoord+=speed;
				break;
		case -1 :
				xCoord-=speed;
				break;
		case 2 :
				yCoord+=speed;
				break;
		case -2 :
				yCoord-=speed;
				break;
		case -3 :
				xCoord-=speed;
				yCoord-=speed;
				break;
		case 3 :
				xCoord+=speed;
				yCoord-=speed;
				break;
				
		case -4 :
				xCoord-=speed;
				yCoord+=speed;
				break;
		case 4 :
				xCoord+=speed;
				yCoord+=speed;
				break;
			
		}
		
		
		
	}


	public int getSpeed() {
		return speed;
	}




	public int getOldYCoord() {
		return oldYCoord;
	}




	public int getOldXCoord() {
		return oldXCoord;
	}

}
