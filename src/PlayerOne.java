public class PlayerOne {

	private int[] xCoord;
	private int[] yCoord;
	private int direction;
	private int move;

	public PlayerOne(int xCoord, int yCoord) {
		this.xCoord[0] = xCoord;
		this.yCoord[0] = yCoord;
		this.direction = 1;
		this.move = 0;
	}

	public int[] getxCoord() {
		return xCoord;
	}


	public int[] getyCoord() {
		return yCoord;
	}


	public void move(){
		int previousMove = move;
		move++;
		switch (direction) {
		case 1 :
				xCoord[move]=xCoord[previousMove]++;
				break;
		case -1 :
				xCoord[move]=xCoord[previousMove]--;
				break;
		case 2 :
				yCoord[move]=yCoord[previousMove]++;
				break;
		case -2 :
				yCoord[move]=yCoord[previousMove]--;
				break;
		}
		
		
	}

}
