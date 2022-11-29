package serverController;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class GameController implements ActionListener {
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
	private final int M_GRID_SIZE = 10;
	private JButton playerGridButton[][] = new JButton[M_GRID_SIZE][M_GRID_SIZE];
	private JButton enemyGridButton[][] = new JButton[M_GRID_SIZE][M_GRID_SIZE];
	private JButton shipButton[] = new JButton[5];
	private JLabel playerStatus = new JLabel(" ");
	private boolean shipSelected;
	private boolean horizontal = true;
	private boolean placementValid = false;
	private int shipLength;
	private int shipIndex;
	private int shipPlaced[] = {0,0,0,0,0};
	private int coord_index;
	private List<Integer> coordsSelected = new ArrayList<>();
	private List<Integer> coordsPlaced = new ArrayList<>();
	
	public GameController(GameData data, GameBoard gb) {
		this.data = data;
		this.gameBoard = gb;
		
		//this.container = container;
		//LoginData data = new LoginData(loginPanel.getUsername(), loginPanel.getPassword());
		for (int i = 0; i < M_GRID_SIZE; i++) {
			for (int j = 0; j < M_GRID_SIZE; j++) {
				this.playerGridButton[i][j] = new JButton("~");
				this.enemyGridButton[i][j] = new JButton("~");
			}
		}
		for (int i = 0; i < 5; i++) {
			switch(i) {
				case 0:
					this.shipButton[i] = new JButton("Destroyer (2)");
					break;
				case 1:
					this.shipButton[i] = new JButton("Cruiser (3)");
					break;
				case 2:
					this.shipButton[i] = new JButton("Submarine (3)");
					break;
				case 3:
					this.shipButton[i] = new JButton("Battleship (4)");
					break;
				case 4:
					this.shipButton[i] = new JButton("Carrier (5)");
					break;
			}
		//gameBoard.setGameController(this);
		}
	

	}
	
	public void actionPerformed(ActionEvent ae) {
		
//		button.addMouseListener(new MouseAdapter()
//        {
//          @Override
//          public void mousePressed(MouseEvent event)
//          {
//            JButton button = (JButton) event.getSource();
//            Rectangle rectangle = button.getBounds();
//            Point point = button.getLocation();
//            
//            // claculate field position
//            int row = point.y / rectangle.height;
//            int col = point.x / rectangle.width;
//            
//            // shoot on field
//            //shoot(col, row);
//          }
//        });
	}
	
	public JButton setGridButton(int buttonRow, int buttonCol) {
		Font font = new Font("Arial Black", Font.BOLD, 8);
		//shipSelected = true;
		//data.setShipSelected(shipSelected);
		
		placementValid = data.getPlacementValid();
		
		//int shipLength = 1;
		coord_index = (buttonRow * M_GRID_SIZE) + buttonCol;
		SingleCoordinate coordinate = new SingleCoordinate(buttonRow,buttonCol,false,'e');
		coordinate.setButton(playerGridButton[buttonRow][buttonCol]);
		data.addCoordinate(coord_index, coordinate);
		//coords.add(coordinate);

		playerGridButton[buttonRow][buttonCol].setFont(font);
		playerGridButton[buttonRow][buttonCol].setBackground(new Color(131, 209, 232));
		playerGridButton[buttonRow][buttonCol].setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
		playerGridButton[buttonRow][buttonCol].setCursor(new Cursor(Cursor.HAND_CURSOR));
		//playerGridButton[buttonRow][buttonCol].addActionListener(selection);
		playerGridButton[buttonRow][buttonCol].addMouseListener(new MouseAdapter() {
			  public void mouseEntered(MouseEvent evt) {
			    JButton button = (JButton) evt.getSource();
			    shipSelected = data.getShipSelected();
			    horizontal = data.getHorizontal();
			    shipLength = data.getShipLength();
			    if (shipSelected == true) {
			    	if (horizontal == true) {
			    		if (buttonCol <= M_GRID_SIZE - shipLength) {
				    		for (int index = 0; index < shipLength; index++) {
						    	playerGridButton[buttonRow][buttonCol+index].setBackground(Color.CYAN);
				    		}
				    	}
				    	else {
				    		for (int index = 0; index < shipLength; index++) {
				    			//button.setBackground(Color.RED);
				    			if (buttonCol+index < M_GRID_SIZE) {
				    				playerGridButton[buttonRow][buttonCol+index].setBackground(Color.RED);
				    			}
				    		}
				    	}
			    	}
			    	else {
			    		if (buttonRow <= M_GRID_SIZE - shipLength) {
			    			for (int index = 0; index < shipLength; index++) {
			    				playerGridButton[buttonRow+index][buttonCol].setBackground(Color.CYAN);
			    			}
			    		}
			    		else {
			    			for (int index = 0; index < shipLength; index++) {
				    			//button.setBackground(Color.RED);
				    			if (buttonRow+index < M_GRID_SIZE) {
				    				playerGridButton[buttonRow+index][buttonCol].setBackground(Color.RED);
				    			}
				    		}
			    		}
			    	}
			    }
			  }

			  public void mouseExited(java.awt.event.MouseEvent evt) {
			    JButton button = (JButton) evt.getSource();
			    shipSelected = data.getShipSelected();
			    horizontal = data.getHorizontal();
			    shipLength = data.getShipLength();
			    	if (shipSelected == true) {
			    		if (horizontal == true) {
			    			if (buttonCol < M_GRID_SIZE - shipLength) {
					    		for (int index = 0; index < shipLength; index++) {
								    playerGridButton[buttonRow][buttonCol+index].setBackground(new Color(131, 209, 232));
					    		}
					    	}
					    	else {
					    		for (int index = 0; index < shipLength; index++) {
					    			//button.setBackground(new Color(131, 209, 232));
					    			if (buttonCol+index < M_GRID_SIZE) {
					    				playerGridButton[buttonRow][buttonCol+index].setBackground(new Color(131, 209, 232));
					    			}
					    		}
					    	}
			    		}
			    		else {
			    			if (buttonRow < M_GRID_SIZE - shipLength) {
					    		for (int index = 0; index < shipLength; index++) {
								    playerGridButton[buttonRow+index][buttonCol].setBackground(new Color(131, 209, 232));
					    		}
					    	}
					    	else {
					    		for (int index = 0; index < shipLength; index++) {
					    			//button.setBackground(new Color(131, 209, 232));
					    			if (buttonRow+index < M_GRID_SIZE) {
					    				playerGridButton[buttonRow+index][buttonCol].setBackground(new Color(131, 209, 232));
					    			}
					    		}
					    	}
			    		}
				    }
			    
			  }
			  
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  JButton button = (JButton) evt.getSource();
				  shipSelected = data.getShipSelected();
				  horizontal = data.getHorizontal();
				  shipLength = data.getShipLength();
				    if (shipSelected == true) {
				    	if (horizontal == true) {
				    		if (buttonCol <= M_GRID_SIZE - shipLength) {
				    			placementValid = true;
				    			data.setPlacementValid(placementValid);
					    		for (int index = 0; index < shipLength; index++) {
//					    			button.setBackground(Color.GREEN);
//					    			button.setBorder(new LineBorder(Color.BLACK));
							    	playerGridButton[buttonRow][buttonCol+index].setBackground(Color.GREEN);
							    	playerGridButton[buttonRow][buttonCol+index].setBorder(new LineBorder(Color.BLACK));
							    	//playerGridButton[buttonRow][buttonCol+index].setText("");
							    	coord_index = (buttonRow * M_GRID_SIZE) + (buttonCol+index);
							    	coordsSelected.add(coord_index);
							    	//playerGridButton[buttonRow][buttonCol+index].setBorder(new LineBorder(Color.BLACK));
					    		}
					    		//System.out.println("List: " + coordsSelected);
					    		for (int i = 0; i < M_GRID_SIZE; i++) {
									for (int j = 0; j < M_GRID_SIZE; j++) {
										playerGridButton[i][j].setEnabled(false);
									}
								}
					    	}
					    	else {
					    		playerStatus.setText("You cannot place a ship out of bounds");
					    		for (int index = 0; index < shipLength; index++) {
					    			//button.setBackground(Color.RED);
					    			if (buttonCol+index < M_GRID_SIZE) {
					    				playerGridButton[buttonRow][buttonCol+index].setBackground(Color.RED);
					    			}
					    		}
					    	}
					    }
				    	else {
				    		if (buttonRow <= M_GRID_SIZE - shipLength) {
				    			placementValid = true;
				    			data.setPlacementValid(placementValid);
					    		for (int index = 0; index < shipLength; index++) {
//					    			button.setBackground(Color.GREEN);
//					    			button.setBorder(new LineBorder(Color.BLACK));
							    	playerGridButton[buttonRow+index][buttonCol].setBackground(Color.GREEN);
							    	playerGridButton[buttonRow+index][buttonCol].setBorder(new LineBorder(Color.BLACK));
							    	//playerGridButton[buttonRow+index][buttonCol].setText("");
							    	coord_index = ((buttonRow+index) * M_GRID_SIZE) + buttonCol;
							    	coordsSelected.add(coord_index);
					    		}
					    		//System.out.println("List: " + coordsSelected);
					    		for (int i = 0; i < M_GRID_SIZE; i++) {
									for (int j = 0; j < M_GRID_SIZE; j++) {
										playerGridButton[i][j].setEnabled(false);
									}
								}
					    	}
					    	else {
					    		playerStatus.setText("You cannot place a ship out of bounds");
					    		for (int index = 0; index < shipLength; index++) {
					    			//button.setBackground(Color.RED);
					    			if (buttonRow+index < M_GRID_SIZE) {
					    				playerGridButton[buttonRow+index][buttonCol].setBackground(Color.RED);
					    			}
					    		}
					    	}
				    	}
				    }
				    else {
				    	//playerStatus.setText("You must select a ship to place");
				    	gameBoard.setPlayerMessage("You must select a ship to place");
				    }
			  }
		});
		return playerGridButton[buttonRow][buttonCol];
	}
	
	public JButton setShipButton(int i) {
		switch(i) {
		case 0:
			shipButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shipButton[i].setEnabled(false);
					shipIndex = 0;
					shipSelected = true;
					data.setShipSelected(shipSelected);
					shipLength = 2;
					data.setShipLength(shipLength);
					String message = "You have selected the Destroyer";
					gameBoard.setPlayerMessage(message);
					
				}
			});
			break;
		case 1:
			shipButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shipButton[i].setEnabled(false);
					shipIndex = 1;
					shipSelected = true;
					data.setShipSelected(shipSelected);
					shipLength = 3;
					data.setShipLength(shipLength);
					String message = "You have selected the Cruiser";
					gameBoard.setPlayerMessage(message);
					
				}
			});
			break;
		case 2:
			shipButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shipButton[i].setEnabled(false);
					shipIndex = 2;
					shipSelected = true;
					data.setShipSelected(shipSelected);
					shipLength = 3;
					data.setShipLength(shipLength);
					String message = "You have selected the Submarine";
					gameBoard.setPlayerMessage(message);
					
				}
			});
			break;
		case 3:
			shipButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shipButton[i].setEnabled(false);
					shipIndex = 3;
					shipSelected = true;
					data.setShipSelected(shipSelected);
					shipLength = 4;
					data.setShipLength(shipLength);
					String message = "You have selected the Battleship";
					gameBoard.setPlayerMessage(message);
					
				}
			});
			break;
		case 4:
			shipButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shipButton[i].setEnabled(false);
					shipIndex = 4;
					shipSelected = true;
					data.setShipSelected(shipSelected);
					shipLength = 5;
					data.setShipLength(shipLength);
					String message = "You have selected the Carrier";
					gameBoard.setPlayerMessage(message);
					
				}
			});
			break;
		}
		return shipButton[i];
	}
	
	public void selectCoordinate(ActionEvent ae) {
		JButton button = (JButton) ae.getSource();
		
        Rectangle rectangle = button.getBounds();
        Point point = button.getLocation();
        
        // calculate field position
        int row = point.y / rectangle.height;
        int col = point.x / rectangle.width;

        setRow(row);
        setCol(col);
        
//        System.out.println("y= "+point.y);
//        System.out.println("x= "+point.x);
//        System.out.println("height= "+rectangle.height);
//        System.out.println("width= "+rectangle.width);
//        System.out.println("row: "+row);
//        System.out.println("column: "+col);
	}
	
	public void selectShip(ActionEvent ae) {
		//Select the ship to place
		JButton button = (JButton) ae.getSource();
		button.setEnabled(false);
		Rectangle rectangle = button.getBounds();
        Point point = button.getLocation();
        
        // calculate field position
//        int row = point.y / rectangle.height;
//        int col = point.x / rectangle.width;
//
//        setRow(row);
//        setCol(col);
        
        
		
	}
	
	public JButton setOrientButton(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				horizontal = data.getHorizontal();
				if (horizontal == true) {
					horizontal = false;
					data.setHorizontal(horizontal);
					button.setText("Set Horizontal");
				}
				else {
					horizontal = true;
					data.setHorizontal(horizontal);
					button.setText("Set Vertical");
				}
			}
		});
		return button;
	}
	
	public JButton setPlaceButton (JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = " ";
				shipSelected = data.getShipSelected();
				if (shipSelected == true) {
					data.setShipPlaced(shipIndex);
					int temp = 0;
					for (int i = 0; i < 5; i++) {
						temp = data.getShipPlaced(i);
						shipPlaced[i] = temp;
					}
					shipPlaced[shipIndex] = 1;
					data.setShipPlaced(shipPlaced[shipIndex]);
					for (int i = 0; i < 5; i++) {
						if (shipPlaced[i] == 0) {
							shipButton[i].setEnabled(true);
						}
					}
					if (shipIndex == 0) {
						message = "Destroyer Placed";
					}
					else if (shipIndex == 1) {
						message = "Cruiser Placed";
					}
					else if (shipIndex == 2) {
						message = "Submarine Placed";
					}
					else if (shipIndex == 3) {
						message = "Battleship Placed";
					}
					else if (shipIndex == 4) {
						message = "Carrier Placed";
					}
					gameBoard.setPlayerMessage(message);
				}
				int selectedLength = coordsSelected.size();
				int[] select_arr;
				select_arr = new int[selectedLength];
				for (int i = 0; i < selectedLength; i++) {
					select_arr[i] = coordsSelected.get(i);
					coordsPlaced.add(select_arr[i]);
				}
				int placedLength = coordsPlaced.size();
				int[] place_arr;
				place_arr = new int[placedLength];
				for (int i = 0; i < placedLength; i++) {
					place_arr[i] = coordsPlaced.get(i);
				}
				
				for (int i = 0; i < M_GRID_SIZE; i++) {
					for (int j = 0; j < M_GRID_SIZE; j++) {
						playerGridButton[i][j].setEnabled(true);
					}
				}

				int temp;
				for (int i = 0; i < selectedLength; i++) {
					temp = select_arr[i];
					for (int j = 0; j < M_GRID_SIZE; j++) {
						for (int k = 0; k < M_GRID_SIZE; k++) {
							coord_index = (j * M_GRID_SIZE) + k;
							if (temp == coord_index) {
								playerGridButton[j][k].setText("o");
								playerGridButton[j][k].setEnabled(false);
							}
						}
					}
				}
				for (int i = 0; i < placedLength; i++) {
					temp = place_arr[i];
					for (int j = 0; j < M_GRID_SIZE; j++) {
						for (int k = 0; k < M_GRID_SIZE; k++) {
							coord_index = (j * M_GRID_SIZE) + k;
							if (temp == coord_index) {
								playerGridButton[j][k].setText("o");
								playerGridButton[j][k].setEnabled(false);
							}
						}
					}
				}
				for (int i = 0; i < M_GRID_SIZE; i++) {
					for (int j = 0; j < M_GRID_SIZE; j++) {
						playerGridButton[i][j].setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
					}
				}
				coordsSelected.clear();
				shipSelected = false;
				data.setShipSelected(shipSelected);
				//coordSelected = false;
			}
		});
		return button;
	}
	
	public JButton setCancelPlaceButton(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int temp = 0;
				for (int i = 0; i < 5; i++) {
					temp = data.getShipPlaced(i);
					shipPlaced[i] = temp;
				}
				for (int i = 0; i < 5; i++) {
					if (shipPlaced[i] == 0) {
						shipButton[i].setEnabled(true);
					}
				}
				int listLength = coordsPlaced.size();
				int[] temp_arr;
				temp_arr = new int[listLength];
				for (int i = 0; i < listLength; i++) {
					temp_arr[i] = coordsPlaced.get(i);
				}
				
				for (int i = 0; i < M_GRID_SIZE; i++) {
					for (int j = 0; j < M_GRID_SIZE; j++) {
						playerGridButton[i][j].setEnabled(true);
					}
				}

				int temp2;
				for (int i = 0; i < listLength; i++) {
					temp2 = temp_arr[i];
					for (int j = 0; j < M_GRID_SIZE; j++) {
						for (int k = 0; k < M_GRID_SIZE; k++) {
							coord_index = (j * M_GRID_SIZE) + k;
							if (temp2 == coord_index) {
								playerGridButton[j][k].setText("o");
								playerGridButton[j][k].setEnabled(false);
							}
						}
					}
				}
				
				for (int i = 0; i < M_GRID_SIZE; i++) {
					for (int j = 0; j < M_GRID_SIZE; j++) {
						playerGridButton[i][j].setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
					}
				}
				coordsSelected.clear();
				gameBoard.setPlayerMessage(" ");
				shipSelected = false;
				data.setShipSelected(shipSelected);
				//coordSelected = false;
			}
		});
		return button;
	}
	
	public void placeShip(ActionEvent ae) {
		//Place ship on grid
		JButton button = (JButton) ae.getSource();
		Rectangle rectangle = button.getBounds();
        Point point = button.getLocation();
        
        // calculate field position
        int row = point.y / rectangle.height;
        int col = point.x / rectangle.width;

        setRow(row);
        setCol(col);
		//Use isOccupied method to determine valid placement
		//Use inBounds method to determine valid placement
		//If invalid, go to invalidPlacement method
		//If valid go to successfulPlacement method
	}
	
	public void fire(ActionEvent event) {
          
          
          //Determine whether coord is occupied
          //If occupied, go to hit method
          //If unoccupied, go to miss method
	}
	
	public void displayError(String message) {
		 gameBoard.setError(message);
	}
	
	public void displayPlayerMessage(String message) {
		//Pass selected, placed, hit or miss, or error message into playerStatus JLabel in GameBoard
	    GameBoard gameBoard = (GameBoard)container.getComponent(1);
	    gameBoard.setPlayerMessage(message);
	}
	
	public void displayEnemyMessage(String message) {
		//Pass selected, placed, hit or miss, or error message into enemyStatus JLabel in GameBoard
	    GameBoard gameBoard = (GameBoard)container.getComponent(1);
	    gameBoard.setEnemyMessage(message);
	}
	
	public void setRow(int row) {
		this.row = row;
		this.x_coord = rows[row-1];
	}
	
	public char getRow() {
		return x_coord;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public int getCol() {
		return col;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
