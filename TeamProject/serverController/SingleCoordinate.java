package serverController;

public class SingleCoordinate {
	private boolean occupied;
	protected int x;
	protected int y;
	protected char status;
	
	public SingleCoordinate(int x, int y, boolean occupied, char status) {
		this.x = x;
		this.y = y;
		this.occupied = occupied;
		this.status = status;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public char getStatus() {
		return status;
	}
}
