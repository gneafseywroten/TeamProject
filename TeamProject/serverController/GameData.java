package serverController;

import java.util.ArrayList;
import java.util.List;

public class GameData {
	private SingleCoordinate coordinate;
	private int x_coord;
	private char y_coord;
	private ArrayList<SingleCoordinate> playerCoords = new ArrayList<SingleCoordinate>();
	private ArrayList<SingleCoordinate> enemyCoords = new ArrayList<SingleCoordinate>();
	private boolean shipSelected;
	private boolean horizontal = true;
	private boolean placementValid;
	private boolean win = false;
	private int shipLength = 1;
	private int shipIndex;
	private int[] shipPlaced = {0,0,0,0,0};
	private int coord_index;
	private int numShipsPlaced;
	private int numShipsSunk = 0;
	private List<Integer> coordsPlaced = new ArrayList<>();
	private String shipType;
	
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
	
	public void addPlayerCoordinate(int i, SingleCoordinate coord) {
		playerCoords.add(i, coord);
	}
	
	public void changePlayerCoordinate(int i, SingleCoordinate coord) {
		playerCoords.set(i, coord);
	}
	
	public SingleCoordinate getPlayerCoordinate(int i) {
		return playerCoords.get(i);
	}
	
	public void addEnemyCoordinate(int i, SingleCoordinate coord) {
		enemyCoords.add(i, coord);
	}
	
	public void changeEnemyCoordinate(int i, SingleCoordinate coord) {
		enemyCoords.set(i, coord);
	}
	
	public SingleCoordinate getEnemyCoordinate(int i) {
		return enemyCoords.get(i);
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
	
	public void setShipPlaced(int shipIndex) {
		this.shipPlaced[shipIndex] = 1;
	}
	
	public int getShipPlaced(int shipIndex) {
		return shipPlaced[shipIndex];
	}
	
	public void setCoordIndex(int coord_index) {
		this.coord_index = coord_index;
	}
	
	public int getCoordIndex() {
		return coord_index;
	}
	
	public void addShipSunk() {
		numShipsSunk++;
		if (numShipsSunk == 5)
			this.win = true;
	}
	
	public boolean playerWon() {
		return win;
	}
	
	public int getNumShipsSunk() {
		return numShipsSunk;
	}
	
	public int getNumShipsPlaced() {
		return numShipsPlaced;
	}
	
	public void setNumShipsSunk(int numShipsSunk) {
		this.numShipsSunk = numShipsSunk;
	}
	
	public void testGUI() {
		enemyCoords = playerCoords;
	}

}
