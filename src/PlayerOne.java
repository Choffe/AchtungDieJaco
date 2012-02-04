public class PlayerOne {

	private int xCoord;
	private int yCoord;
	private int direction;

	public PlayerOne(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.direction = 1;
	}

	public int getxCoord() {
		return xCoord;
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
