package serverController;

import java.util.ArrayList;

public class GameData {
	private SingleCoordinate coordinate;
	private int x_coords[];
	private char y_coords[];
	private int x_coord;
	private char y_coord;
	private Ship ship;
	private ArrayList<SingleCoordinate> coordsList = new ArrayList<SingleCoordinate>();
	private boolean shipSelected;
	private boolean horizontal = true;
	private boolean placementValid = false;
	private int shipLength = 1;
	private int shipIndex;
	private int[] shipPlaced = {0,0,0,0,0};
	
	//Setters and getters for coordinates and ships
	public void setCoordinate(SingleCoordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public SingleCoordinate getCoordinate() {
		return coordinate;
	}
	
	public void setXCoord(int x_coord) {
		this.x_coord = x_coord;
	}
	
	public int getXCoord() {
		return x_coord;
	}
	
	public void setYCoord(char y_coord) {
		this.y_coord = y_coord;
	}
	
	public char getYCoord() {
		return y_coord;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public void addCoordinate(int i, SingleCoordinate coord) {
		coordsList.add(i, coord);
	}
	
	public SingleCoordinate getCoordinate(int i) {
		return coordsList.get(i);
	}
	
	public void setShipSelected(boolean shipSelected) {
		this.shipSelected = shipSelected;
	}
	
	public boolean getShipSelected() {
		return shipSelected;
	}
	
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	public boolean getHorizontal() {
		return horizontal;
	}
	
	public void setShipLength(int shipLength) {
		this.shipLength = shipLength;
	}
	
	public int getShipLength() {
		return shipLength;
	}
	
	public void setPlacementValid(boolean placementValid) {
		this.placementValid = placementValid;
	}
	
	public boolean getPlacementValid() {
		return placementValid;
	}
	
	public void setShipIndex(int shipIndex) {
		this.shipIndex = shipIndex;
	}
	
	public int getShipIndex() {
		return shipIndex;
	}
	
	public void setShipPlaced (int shipIndex) {
		this.shipPlaced[shipIndex] = 1;
	}
	
	public int getShipPlaced (int shipIndex) {
		return shipPlaced[shipIndex];
	}
}
