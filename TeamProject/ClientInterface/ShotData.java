package ClientInterface;

import java.io.Serializable;

public class ShotData implements Serializable{
	private int row;
	private int col;
	private int index;
	
	public ShotData (int row, int col) {
		this.row = row;
		this.col = col;
		this.index = (row*10) + col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getIndex() {
		return index;
	}

}
