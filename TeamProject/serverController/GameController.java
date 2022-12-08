package serverController;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class GameController {
	private int size;
	private int sunkenShips;
	private int hitCount;
	private int missCount;
	private JPanel container;
	private ArrayList<SingleCoordinate> coordButton;
	private char[] rows = {'A','B','C','D','E','F','G','H','I','J'};
	private String[] cols = {"1","2","3","4","5","6","7","8","9","10"};
	private int row;
	private int col;
	private char x_coord;
	private GameBoard gameBoard;
	private GameData data;
	private Ship destroyer;
	private Ship submarine;
	private Ship cruiser;
	private Ship battleship;
	private Ship carrier;
	private final int M_GRID_SIZE = 10;
	private JButton playerGridButton[][] = new JButton[M_GRID_SIZE][M_GRID_SIZE];
	private JButton enemyGridButton[][] = new JButton[M_GRID_SIZE][M_GRID_SIZE];
	private JButton shipButton[] = new JButton[5];
	private JButton orient = new JButton();
 	private JButton place = new JButton();
 	private JButton cancelPlace = new JButton();
	private JButton beginBattle = new JButton();
	private JButton fire = new JButton();
	private JButton cancelFire = new JButton();
	private JLabel playerStatus = new JLabel(" ");
	private boolean shipSelected;
	private boolean playerSquaresSelected;
	private boolean targetSquareSelected;
	private boolean horizontal = true;
	private boolean inBounds;
	private boolean placementValid;
	private int numShipsPlaced = 0;
	private int shipLength;
	private int shipIndex;
	private int shipPlaced[] = {0,0,0,0,0};
	private int coord_index;
	private String shipType;
	private List<Integer> coordsSelected = new ArrayList<>();
	private List<Integer> coordsPlaced = new ArrayList<>();
	private List<Integer> shipCoords = new ArrayList<>();
	private List<Integer> firedAt = new ArrayList<>();
	private List<SingleCoordinate> tempShip = new ArrayList<>();
	private String opponentHit;
	private String opponentMiss;
	
	public GameController(GameData data) {
		this.data = data;
	}
	
	
	public String p1FiresAtp2(int coord_index, int shot_row, int shot_col) {
		String message;
		SingleCoordinate fire_coord = data.getPlayer2Coordinate(coord_index);
		boolean hit = fire_coord.isOccupied();
		
		if(hit) {
			String firedShip = fire_coord.getshipType();
			boolean isSunk = false;
			if (firedShip.equals("Destroyer")) {
				destroyer.hit();
				isSunk = destroyer.sunk();
				if (isSunk)
					data.addP2ShipSunk();
			}
			else if (firedShip.equals("Submarine")) {
				submarine.hit();
				isSunk = submarine.sunk();
				if (isSunk)
					data.addP2ShipSunk();
			}
			else if (firedShip.equals("Cruiser")) {
				cruiser.hit();
				isSunk = cruiser.sunk();
				if (isSunk)
					data.addP2ShipSunk();
			}
			else if (firedShip.equals("Battleship")) {
				battleship.hit();
				isSunk = battleship.sunk();
				if (isSunk)
					data.addP2ShipSunk();
			}
			else if (firedShip.equals("Carrier")) {
				carrier.hit();
				isSunk = carrier.sunk();
				if (isSunk)
					data.addP2ShipSunk();
			}
			
			message = "Enemy hit your |" + firedShip + "|" + coord_index + "|" + shot_row + "|" + shot_col;
			if(isSunk) {
				fire_coord = new SingleCoordinate(coord_index,true,'s');
				int num = data.getP2ShipsSunk();
			}
			else {
				fire_coord = new SingleCoordinate(coord_index,true,'h');
			}
			boolean defeat = data.player2Defeated();
			if(defeat) {
				message = "Defeated";
			}
			alertOpponentOfHit(coord_index,shot_row,shot_col, defeat);
		}
		else {
			message = "Enemy missed |" + coord_index + "|" + shot_row + "|" + shot_col;
			fire_coord = new SingleCoordinate(coord_index, false, 'm');
			alertOpponentOfMiss(coord_index,shot_row,shot_col);
		}
		data.changePlayer2Coordinate(coord_index, fire_coord);
		
		return message;
	}
	
	public String p2FiresAtp1(int coord_index, int shot_row, int shot_col) {
		String message;
		SingleCoordinate fire_coord = data.getPlayer1Coordinate(coord_index);
		boolean hit = fire_coord.isOccupied();
		
		if(hit) {
			String firedShip = fire_coord.getshipType();
			boolean isSunk = false;
			if (firedShip.equals("Destroyer")) {
				destroyer.hit();
				isSunk = destroyer.sunk();
				if (isSunk)
					data.addP1ShipSunk();
			}
			else if (firedShip.equals("Submarine")) {
				submarine.hit();
				isSunk = submarine.sunk();
				if (isSunk)
					data.addP1ShipSunk();
			}
			else if (firedShip.equals("Cruiser")) {
				cruiser.hit();
				isSunk = cruiser.sunk();
				if (isSunk)
					data.addP1ShipSunk();
			}
			else if (firedShip.equals("Battleship")) {
				battleship.hit();
				isSunk = battleship.sunk();
				if (isSunk)
					data.addP1ShipSunk();
			}
			else if (firedShip.equals("Carrier")) {
				carrier.hit();
				isSunk = carrier.sunk();
				if (isSunk)
					data.addP1ShipSunk();
			}
			
			message = "Enemy hit your |" + firedShip + "|" + coord_index + "|" + shot_row + "|" + shot_col;
			if(isSunk) {
				fire_coord = new SingleCoordinate(coord_index,true,'s');
				int num = data.getP1ShipsSunk();
			}
			else {
				fire_coord = new SingleCoordinate(coord_index,true,'h');
			}
			boolean defeat = data.player1Defeated();
			if(defeat) {
				message = "Defeated";
			}
			alertOpponentOfHit(coord_index, shot_row, shot_col, defeat);
		}
		else {
			message = "Enemy missed |" + coord_index + "|" + shot_row + "|" + shot_col;
			fire_coord = new SingleCoordinate(coord_index, false, 'm');
			alertOpponentOfMiss(coord_index, shot_row, shot_col);
		}
		data.changePlayer1Coordinate(coord_index, fire_coord);
		
		return message;
	}
	
	public void alertOpponentOfHit(int coord_index, int shot_row, int shot_col, boolean win) {
		if(win)
			opponentHit = "YOU WIN!!!";
		else
			opponentHit = ("Hit!" + "|" + coord_index + "|" + shot_row + "|" + shot_col);
	}
	
	public void alertOpponentOfMiss(int coord_index, int shot_row, int shot_col) {
		opponentMiss = "Miss" + "|" + coord_index + "|" + "|" + shot_row + "|" + shot_col;
	}
	
	public String getOpponentHit() {
		return opponentHit;
	}
	
	public String getOpponentMiss() {
		return opponentMiss;
	}
}
