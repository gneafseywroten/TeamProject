package serverController;

import java.util.ArrayList;
import java.util.List;

public class GameData {
	private SingleCoordinate coordinate;
	private int x_coord;
	private char y_coord;
	private ArrayList<SingleCoordinate> player1Coords = new ArrayList<SingleCoordinate>();
	private ArrayList<SingleCoordinate> player2Coords = new ArrayList<SingleCoordinate>();
	//private boolean shipSelected;
	//private boolean horizontal = true;
	//private boolean placementValid;
	private boolean p1_win = false;
	private boolean p2_win = false;
	private boolean p1_defeat = false;
	private boolean p2_defeat = false;
	private int shipLength = 1;
	private int shipIndex;
	private int[] shipPlaced = {0,0,0,0,0};
	private int coord_index;
	private int numShipsPlaced;
	private int p1ShipsSunk = 0;
	private int p2ShipsSunk = 0;
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
	
	public void setAllPlayer1Coords(ArrayList<SingleCoordinate> player1Coords) {
		this.player1Coords = player1Coords;
	}
	
	public void addPlayer1Coordinate(int i, SingleCoordinate coord) {
		System.out.println("Coordinate stored");
		player1Coords.add(i, coord);
	}
	
	public void changePlayer1Coordinate(int i, SingleCoordinate coord) {
		player1Coords.set(i, coord);
	}
	
	public SingleCoordinate getPlayer1Coordinate(int i) {
		return player1Coords.get(i);
	}
	
	public void setAllPlayer2Coords(ArrayList<SingleCoordinate> player2Coords) {
		this.player2Coords = player2Coords;
	}
	
	public void addPlayer2Coordinate(int i, SingleCoordinate coord) {
		System.out.println("Coordinate stored");
		player2Coords.add(i, coord);
	}
	
	public void changePlayer2Coordinate(int i, SingleCoordinate coord) {
		player2Coords.set(i, coord);
	}
	
	public SingleCoordinate getPlayer2Coordinate(int i) {
		return player2Coords.get(i);
	}
	
//	public void setShipSelected(boolean shipSelected) {
//		this.shipSelected = shipSelected;
//	}
//	
//	public boolean getShipSelected() {
//		return shipSelected;
//	}
//	
//	public void setHorizontal(boolean horizontal) {
//		this.horizontal = horizontal;
//	}
//	
//	public boolean getHorizontal() {
//		return horizontal;
//	}
	
	public void setShipLength(int shipLength) {
		this.shipLength = shipLength;
	}
	
	public int getShipLength() {
		return shipLength;
	}
	
//	public void setPlacementValid(boolean placementValid) {
//		this.placementValid = placementValid;
//	}
//	
//	public boolean getPlacementValid() {
//		return placementValid;
//	}
	
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
	
	public void addP1ShipSunk() {
		p1ShipsSunk++;
		if (p1ShipsSunk == 5) {
			this.p2_win = true;
			this.p1_defeat = true;
		}
			
	}
	
	public void addP2ShipSunk() {
		p2ShipsSunk++;
		if (p2ShipsSunk == 5) {
			this.p1_win = true;
			this.p2_defeat = true;
		}
			
	}
	
	public boolean player1Won() {
		return p1_win;
	}
	
	public boolean player2Won() {
		return p2_win;
	}
	
	public boolean player1Defeated() {
		return p1_defeat;
	}
	
	public boolean player2Defeated() {
		return p2_defeat;
	}
	
	public int getP1ShipsSunk() {
		return p1ShipsSunk;
	}
	
	public int getP2ShipsSunk() {
		return p2ShipsSunk;
	}
	
	public int getNumShipsPlaced() {
		return numShipsPlaced;
	}
	
//	public void setNumShipsSunk(int numShipsSunk) {
//		this.numShipsSunk = numShipsSunk;
//	}
//	
//	public void testGUI() {
//		enemyCoords = playerCoords;
//	}

}
