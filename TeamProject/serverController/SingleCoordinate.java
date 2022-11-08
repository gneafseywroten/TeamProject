package serverController;

public class SingleCoordinate {
	private int x;
	private int y;
	private boolean occupied;
	
	public SingleCoordinate(int x, int y, boolean occupied) {
		this.x = x;
		this.y = y;
		this.occupied = occupied;
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
}
