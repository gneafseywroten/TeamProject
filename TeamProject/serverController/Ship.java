package serverController;

import java.util.*;

public class Ship {
	//private List<SingleCoordinate> newShip = new ArrayList<>();
	private String shipType;
	private char status;
	private int length = 1;
	private int intactParts;
	private boolean placed;
	private boolean sunk;
	
	public Ship(int length, String shipType) {
		this.shipType = shipType;
		this.length = length;
		this.intactParts = length;
		this.sunk = false;
	}
	
	public String getType() {
		return shipType;
	}
	
	public int getLength() {
		return length;
	}
	
	public void hit() {
		intactParts--;
		if (intactParts == 0)
			this.sunk = true;
	}
	
	public boolean sunk() {
		return sunk;
	}
	
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	
	public boolean isPlaced() {
		return placed;
	}

}
