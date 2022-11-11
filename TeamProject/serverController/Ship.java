package serverController;

import java.util.*;

public abstract class Ship {
	private List<SingleCoordinate> NewShip;
	protected String shipType;
	protected boolean occupied = true;
	protected int length = 1;
	
	public Ship(List<SingleCoordinate> newShip, String shipType) {
		NewShip = newShip;
		this.shipType = shipType;
		this.occupied = true;
	}
	
	public List<SingleCoordinate> getNewShip() {
		return NewShip;
	}
	
	public String getType() {
		return shipType;
	}
	
	public int getLength() {
		return length;
	}
	
	public boolean isOccupied() {
		return true;
	}

}
