public class PlayerOne {

	private int xCoord;
	private int yCoord;
	private int direction;
	
	public static int LEFT = -1;
	public static int RIGHT = 1;
	public static int UP = -2;
	public static int DOWN = 2;
	
	

	

	public PlayerOne(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.direction = 1;
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


	public void move(){
		
		
		switch (direction) {
		case 1 :
				xCoord+=10;
				break;
		case -1 :
				xCoord-=10;
				break;
		case 2 :
				yCoord+=10;
				break;
		case -2 :
				yCoord-=10;
				break;
		}
		
		
	}

}
