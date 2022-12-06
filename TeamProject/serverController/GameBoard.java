package serverController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.util.*;

public class GameBoard extends JPanel {
	public JLabel enemyStatus;
	public JLabel playerStatus;
	public JLabel error;
	public JPanel container;
	private int[] shipPlaced = {0,0,0,0,0};
	private int shipIndex;
	private String[] rows = {"A","B","C","D","E","F","G","H","I","J"};
	private String[] cols = {"1","2","3","4","5","6","7","8","9","10"};
	private final int M_GRID_SIZE = 10;
	private final int M_PLUS_ONE = 11;
	private int coord_index;
	private String coordName = new String();
	private JButton playerGridButton[][] = new JButton[M_GRID_SIZE][M_GRID_SIZE];
	private JButton enemyGridButton[][] = new JButton[M_GRID_SIZE][M_GRID_SIZE];
	private JButton shipButton[] = new JButton[5];
 	private JButton button = new JButton();
 	private JButton orient = new JButton();
 	private JButton place = new JButton();
 	private JButton cancelPlace = new JButton();
 	private JButton beginBattle = new JButton();
	private ArrayList<SingleCoordinate> coords;
	private final int ST_SIZE = 45;
	private JPanel oceanGrid;
	private JPanel targetGrid;
	private JPanel playerSide;
	private JPanel enemySide;
	private JPanel placeCommands;
	private JPanel fireButtons;
	private GameController gc;
	private GameData data;
	
	public GameBoard(GameData gameData) {
		this.data = gameData;
		oceanGrid = new JPanel();
		targetGrid = new JPanel();
	}
	
	public void buildBoard() {
		playerSide = new JPanel();
		enemySide = new JPanel();
		fireButtons = new JPanel();
		placeCommands = new JPanel();
		
		
		buildPlaceCommands(placeCommands);
		buildPlayerSide(playerSide);
		buildFireButtons(fireButtons);
		buildEnemySide(enemySide);
		
		this.add(placeCommands);
		this.add(playerSide);
		this.add(fireButtons);
		this.add(enemySide);
		this.setVisible(true);
	}
	
	public void buildPlayerGrid(JPanel grid, int gridSide) {
		grid.setLayout(new GridLayout(11, 11, 0, 0));
		grid.setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
		
		Font font = new Font("Arial Black", Font.BOLD, 8);
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				int buttonRow = i-1;
				int buttonCol = j-1;
				if (i == 0 && j == 0) {
					button = new JButton();
					button.setOpaque(false);
					button.setContentAreaFilled(false);
					button.setBorderPainted(false);
					button.setPreferredSize(new Dimension(ST_SIZE, ST_SIZE));
					grid.add(button);
				}
				else if (i == 0) {
					String column = cols[j-1];
					button = new JButton(column);
					button.setFont(font);
					button.setContentAreaFilled(false);
					button.setBorderPainted(false);
					button.setPreferredSize(new Dimension(ST_SIZE, ST_SIZE));
					grid.add(button);
				}
				else if (j == 0) {
					String row = rows[i-1];
					button = new JButton(row);
					button.setFont(font);
					button.setContentAreaFilled(false);
					button.setBorderPainted(false);
					button.setPreferredSize(new Dimension(ST_SIZE, ST_SIZE));
					grid.add(button);
				}
				else {
					coord_index = (buttonRow * M_GRID_SIZE) + buttonCol;
					playerGridButton[buttonRow][buttonCol] = new JButton("~");
					playerGridButton[buttonRow][buttonCol] = gc.setPlayerGridButton(buttonRow, buttonCol);
					playerGridButton[buttonRow][buttonCol].setPreferredSize(new Dimension(ST_SIZE, ST_SIZE));
					grid.add(playerGridButton[buttonRow][buttonCol]);
				}
			}
		}
	}
	
	public void buildEnemyGrid (JPanel grid, int gridSide) {
		
		grid.setLayout(new GridLayout(11, 11, 0, 0));
		grid.setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
		
		Font font = new Font("Arial Black", Font.BOLD, 8);
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				int buttonRow = i-1;
				int buttonCol = j-1;
				if (i == 0 && j == 0) {
					button = new JButton();
					button.setOpaque(false);
					button.setContentAreaFilled(false);
					button.setBorderPainted(false);
					button.setPreferredSize(new Dimension(ST_SIZE, ST_SIZE));
					grid.add(button);
				}
				else if (i == 0) {
					String column = cols[j-1];
					button = new JButton(column);
					button.setFont(font);
					button.setContentAreaFilled(false);
					button.setBorderPainted(false);
					button.setPreferredSize(new Dimension(ST_SIZE, ST_SIZE));
					grid.add(button);
				}
				else if (j == 0) {
					String row = rows[i-1];
					button = new JButton(row);
					button.setFont(font);
					button.setContentAreaFilled(false);
					button.setBorderPainted(false);
					button.setPreferredSize(new Dimension(ST_SIZE, ST_SIZE));
					grid.add(button);
				}
				else {
					coord_index = (buttonRow * M_GRID_SIZE) + buttonCol;
					enemyGridButton[buttonRow][buttonCol] = new JButton("");
					enemyGridButton[buttonRow][buttonCol] = gc.setEnemyGridButton(buttonRow, buttonCol);
					enemyGridButton[buttonRow][buttonCol].setPreferredSize(new Dimension(ST_SIZE, ST_SIZE));
					grid.add(enemyGridButton[buttonRow][buttonCol]);
				}
			}
		}
	}
	
	public void buildPlaceCommands(JPanel placeCommands) {
		placeCommands.setLayout(new GridLayout(3, 1, 0, 25));
		
		JPanel shipButtons = new JPanel();
		JPanel placeButtons = new JPanel();
		JPanel startOrExit = new JPanel();
		
		//Build shipButtons panel
		shipButtons.setLayout(new GridLayout(5, 1, 0, 5));
		
		for (int i = 0; i < 5; i++) {
			switch(i) {
				case 0:
					shipButton[i] = new JButton("Destroyer (2)");
					break;
				case 1:
					shipButton[i] = new JButton("Submarine (3)");
					break;
				case 2:
					shipButton[i] = new JButton("Cruiser (3)");
					break;
				case 3:
					shipButton[i] = new JButton("Battleship (4)");
					break;
				case 4:
					shipButton[i] = new JButton("Carrier (5)");
					break;
			}
			shipButton[i] = gc.setShipButton(i);
			shipButtons.add(shipButton[i]);
		}
		
		
		//Build placeButtons panel
		placeButtons.setLayout(new GridLayout(3, 1, 0, 10));
		placeButtons.setPreferredSize(new Dimension(100,100));
		
		orient = new JButton("Set Vertical");
		orient = gc.setOrientButton();
		orient.setPreferredSize(new Dimension(10, 10));
		placeButtons.add(orient);
		
		place = new JButton("Place Ship");
		place = gc.setPlaceButton();
		place.setPreferredSize(new Dimension(10, 10));
		placeButtons.add(place);
		
		cancelPlace = new JButton("Cancel");
		cancelPlace = gc.setCancelPlaceButton();
		cancelPlace.setPreferredSize(new Dimension(10, 10));
		placeButtons.add(cancelPlace);
		
		//Build startOrExit panel
		startOrExit.setLayout(new GridLayout(2, 1, 0, 10));
		startOrExit.setPreferredSize(new Dimension(100,100));
		
		beginBattle = new JButton("BATTLE!");
		beginBattle = gc.setBeginButton();
		startOrExit.add(beginBattle);
		
		JButton exitGame = new JButton("Exit Game");
		startOrExit.add(exitGame);
		
		placeCommands.add(shipButtons);
		placeCommands.add(placeButtons);
		placeCommands.add(startOrExit);
	}
	
	public void buildPlayerSide(JPanel ps) {
		JPanel oceanStatus = new JPanel();
		
		int player = 1;
		buildPlayerGrid(oceanGrid, player);
		
		JLabel playerLabel = new JLabel("Your Grid");
		
		oceanStatus.setLayout(new BoxLayout(oceanStatus, BoxLayout.Y_AXIS));
		
		playerStatus = new JLabel(" ");
		JLabel yourShipsAfloat = new JLabel("Your Ships Afloat: ");
		JLabel yourShipsSunk = new JLabel("Your Ships Sunk: ");
		
		oceanStatus.add(playerStatus);
		oceanStatus.add(yourShipsAfloat);
		oceanStatus.add(yourShipsSunk);
		
		playerSide.setLayout(new BoxLayout(playerSide, BoxLayout.Y_AXIS));
		playerSide.add(playerLabel);
		playerSide.add(oceanGrid);
		playerSide.add(oceanStatus);
	}
	
	public void buildFireButtons(JPanel fireButtons) {
		fireButtons.setLayout(new GridLayout(2, 1, 0, 15));
		
		JButton fire = new JButton("Fire!");
		fire = gc.setFireButton();
		fire.setBackground(new Color(200, 0, 0));
		fireButtons.add(fire);
		
		JButton cancelFire = new JButton("Cancel");
		cancelFire = gc.setCancelFireButton();
		cancelFire.setBackground(new Color(131, 209, 232));
		fireButtons.add(cancelFire);
	}
	
	public void buildEnemySide(JPanel enemySide) {
		JLabel enemyLabel = new JLabel("Enemy Grid");
		JPanel targetStatus = new JPanel();
		
		int enemy = 2;
		buildEnemyGrid(targetGrid, enemy);
		
		targetStatus.setLayout(new BoxLayout(targetStatus, BoxLayout.Y_AXIS));
		
		enemyStatus = new JLabel(" ");
		JLabel enemyShipsAfloat = new JLabel("  ");
		JLabel enemyShipsSunk = new JLabel("  ");
		
		targetStatus.add(enemyStatus);
		targetStatus.add(enemyShipsAfloat);
		targetStatus.add(enemyShipsSunk);
		
		enemySide.setLayout(new BoxLayout(enemySide, BoxLayout.Y_AXIS));
		//enemySide.setLayout(new GridLayout(3, 1, 0, 0));
		enemySide.add(enemyLabel);
		enemySide.add(targetGrid);
		enemySide.add(targetStatus);
	}
	
	public void setError(String message) {
		error.setText(message);
	}
	
	public void setPlayerStatus(JLabel playerStatus) {
		this.playerStatus = playerStatus;
	}
	
	public void setPlayerMessage(String message) {
		playerStatus.setText(message);
	  }
	
	public void setEnemyMessage(String message) {
		enemyStatus.setText(message);
	}
	
	public void setGameController(GameController gc) {
		this.gc = gc;
	}
	
	public GameController getGameController() {
		return gc;
	}

	public static void main(String[] args) {
	
	}


}
