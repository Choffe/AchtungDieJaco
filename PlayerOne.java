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
		switch (this.direction) {
		case RIGHT :
				this.direction = (direction == RIGHT ? DOWNRIGHT : UPRIGHT);
				break;
		case LEFT :
			this.direction = (direction == RIGHT ? UPLEFT : DOWNLEFT);
				break;
		case DOWN :
			this.direction = (direction == RIGHT ? UPLEFT : DOWNLEFT);
				break;
		case UP :
			this.direction = (direction == RIGHT ? UPRIGHT : UPLEFT);
				break;
		case UPLEFT :
			this.direction = (direction == RIGHT ? UP : LEFT);
				break;
		case UPRIGHT :
			this.direction = (direction == RIGHT ? RIGHT : UP);
				break;
		case DOWNLEFT :
			this.direction = (direction == RIGHT ? LEFT : DOWN);
				break;
		case DOWNRIGHT :
			this.direction = (direction == RIGHT ? DOWN : RIGHT);
				break;
		}
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
