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
					this.shipButton[i] = new JButton("Submarine (3)");
					break;
				case 2:
					this.shipButton[i] = new JButton("Cruiser (3)");
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
		orient = new JButton("Set Vertical");
		place = new JButton("Place Ship");
		cancelPlace = new JButton("Cancel");
		beginBattle = new JButton("BATTLE!");
		
	

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
	
	public JButton setPlayerGridButton(int buttonRow, int buttonCol) {
		Font font = new Font("Arial Black", Font.BOLD, 8);
		//shipSelected = true;
		//data.setShipSelected(shipSelected);
		
		//placementValid = data.getPlacementValid();
		
		//int shipLength = 1;
		coord_index = (buttonRow * M_GRID_SIZE) + buttonCol;
		SingleCoordinate coordinate = new SingleCoordinate(coord_index,false,'e');
		coordinate.setButton(playerGridButton[buttonRow][buttonCol]);
		data.addPlayerCoordinate(coord_index, coordinate);
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
				  if (playerSquaresSelected) {
					  String message = "You already have coordinates selected";
					  gameBoard.setPlayerMessage(message);
				  }
				  else {
					  if (shipSelected == true) {
					    	if (horizontal == true) {
					    		if (buttonCol <= M_GRID_SIZE - shipLength) {
					    			inBounds = true;
					    			//data.setPlacementValid(placementValid);
						    		for (int index = 0; index < shipLength; index++) {
//						    			button.setBackground(Color.GREEN);
//						    			button.setBorder(new LineBorder(Color.BLACK));
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
					    			inBounds = true;
					    			//data.setPlacementValid(placementValid);
						    		for (int index = 0; index < shipLength; index++) {
//						    			button.setBackground(Color.GREEN);
//						    			button.setBorder(new LineBorder(Color.BLACK));
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
					    	playerSquaresSelected = true;
					    }
					    else {
					    	gameBoard.setPlayerMessage("You must select a ship to place");
					    }
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
					place.setEnabled(true);
					data.setShipSelected(shipSelected);
					shipLength = 2;
					data.setShipLength(shipLength);
					shipType = "Destroyer";
					String message = "You have selected the "+ shipType;
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
					place.setEnabled(true);
					data.setShipSelected(shipSelected);
					shipLength = 3;
					data.setShipLength(shipLength);
					shipType = "Submarine";
					String message = "You have selected the "+ shipType;
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
					place.setEnabled(true);
					data.setShipSelected(shipSelected);
					shipLength = 3;
					data.setShipLength(shipLength);
					shipType = "Cruiser";
					String message = "You have selected the "+ shipType;
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
					place.setEnabled(true);
					data.setShipSelected(shipSelected);
					shipLength = 4;
					data.setShipLength(shipLength);
					shipType = "Battleship";
					String message = "You have selected the "+ shipType;
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
					place.setEnabled(true);
					data.setShipSelected(shipSelected);
					shipLength = 5;
					data.setShipLength(shipLength);
					shipType = "Carrier";
					String message = "You have selected the "+ shipType;
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
	
	
	public JButton setOrientButton() {
		orient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				horizontal = data.getHorizontal();
				if (horizontal == true) {
					horizontal = false;
					data.setHorizontal(horizontal);
					orient.setText("Set Horizontal");
				}
				else {
					horizontal = true;
					data.setHorizontal(horizontal);
					orient.setText("Set Vertical");
				}
			}
		});
		return orient;
	}
	
	public JButton setPlaceButton () {
		place.setEnabled(false);
		place.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Add code here to check for valid ship placement
				String message = " ";
				//shipSelected = data.getShipSelected();
				if (!shipSelected) {
					message = "You must select a ship to place";
					gameBoard.setPlayerMessage(message);
				}
				else {
					//int selectedLength = shipLength;
					for (int i = 0; i < shipLength; i++) {
						int temp_index = coordsSelected.get(i);
						SingleCoordinate coord = data.getPlayerCoordinate(temp_index);
						boolean occupied = coord.isOccupied();
						if (occupied) {
							placementValid = false;
							//Display invalid placement message
							message = "Your ships cannot occupy the same space";
							gameBoard.setPlayerMessage(message);
							//Temporarily enable every grid button
							for (int j = 0; j < M_GRID_SIZE; j++) {
								for (int k = 0; k < M_GRID_SIZE; k++) {
									playerGridButton[j][k].setEnabled(true);
								}
							}
							//Compare every grid button to every value in the "PlacedCoords" array
							//If the grid button value equals the "PlacedCoords" value, disable button
							int placedLength = coordsPlaced.size();
							int temp;
							for (int j = 0; j < placedLength; j++) {
								temp = coordsPlaced.get(j);
								for (int k = 0; k < M_GRID_SIZE; k++) {
									for (int l = 0; l < M_GRID_SIZE; l++) {
										coord_index = (k * M_GRID_SIZE) + l;
										if (temp == coord_index) {
											playerGridButton[k][l].setText("o");
											playerGridButton[k][l].setEnabled(false);
										}
									}
								}
							}
							
							//Re-enable every button for every ship that is not yet placed
							for (int j = 0; j < 5; j++) {
								if (shipPlaced[j] == 0) {
									shipButton[j].setEnabled(true);
								}
							}
							
							//Repaint borders for all buttons
							for (int j = 0; j < M_GRID_SIZE; j++) {
								for (int k = 0; k < M_GRID_SIZE; k++) {
									playerGridButton[j][k].setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
								}
							}
							//squaresSelected = false;
							break;
						}
						else {
							placementValid = true;
						}
					}
					
				}
				
				if (placementValid) {
					//Instantiate a ship based on type selected
					if (shipType.equals("Destroyer"))
						destroyer = new Ship(shipLength, shipType);
					else if (shipType.equals("Submarine"))
						submarine = new Ship(shipLength, shipType);
					else if (shipType.equals("Cruiser"))
						cruiser = new Ship(shipLength, shipType);
					else if (shipType.equals("Battleship"))
						battleship = new Ship(shipLength, shipType);
					else if (shipType.equals("Carrier"))
						carrier = new Ship(shipLength, shipType);
					
					//Re-enable every button for every ship that is not yet placed
					shipPlaced[shipIndex] = 1;
					for (int i = 0; i < 5; i++) {
						if (shipPlaced[i] == 0) {
							shipButton[i].setEnabled(true);
						}
					}
					
					//Set message to indicate that the ship is placed
					message = shipType + " Placed";
					gameBoard.setPlayerMessage(message);
					
					//Change coords to occupied in playerCoords array
					//Add new coord indexes to coordsPlaced array
					for (int i = 0; i < shipLength; i++) {
						int temp_index = coordsSelected.get(i);
						SingleCoordinate temp_coord = new SingleCoordinate(temp_index,true,'o');
						temp_coord.setshipType(shipType);
						data.changePlayerCoordinate(temp_index, temp_coord);
						coordsPlaced.add(temp_index);
					}
					
					//Temporarily re-enable all playerGridButtons
					for (int i = 0; i < M_GRID_SIZE; i++) {
						for (int j = 0; j < M_GRID_SIZE; j++) {
							playerGridButton[i][j].setEnabled(true);
						}
					}
					
					//Compare every grid button to every value in the "PlacedCoords" array
					//If the grid button value equals the "PlacedCoords" value, disable button
					int placedLength = coordsPlaced.size();
					int temp = 0;
					for (int i = 0; i < placedLength; i++) {
						temp = coordsPlaced.get(i);
						for (int j = 0; j < M_GRID_SIZE; j++) {
							for (int k = 0; k < M_GRID_SIZE; k++) {
								coord_index = (j * M_GRID_SIZE) + k;
								if (temp == coord_index) {
									playerGridButton[j][k].setText("^");
									playerGridButton[j][k].setBackground(Color.DARK_GRAY);
									playerGridButton[j][k].setEnabled(false);
								}
							}
						}
					}
					
					//When this number equals 5, enable Start Battle button
					numShipsPlaced++;
					
					if (numShipsPlaced == 5) {
						orient.setEnabled(false);
						place.setEnabled(false);
						cancelPlace.setEnabled(false);
						beginBattle.setEnabled(true);
						for (int j = 0; j < M_GRID_SIZE; j++) {
							for (int k = 0; k < M_GRID_SIZE; k++) {
								playerGridButton[j][k].setEnabled(false);
							}
						}
					}
				}
				place.setEnabled(false);
				playerSquaresSelected = false;
				placementValid = false;
				coordsSelected.clear();
				shipCoords.clear();
				shipSelected = false;
				data.setShipSelected(shipSelected);
				//coordSelected = false;
			}
		});
		return place;
	}
	
	public JButton setCancelPlaceButton() {
		cancelPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 5; i++) {
					if (shipPlaced[i] == 0) {
						shipButton[i].setEnabled(true);
					}
				}
				
				for (int i = 0; i < M_GRID_SIZE; i++) {
					for (int j = 0; j < M_GRID_SIZE; j++) {
						playerGridButton[i][j].setEnabled(true);
					}
				}

				int listLength = coordsPlaced.size();
				int temp = 0;
				for (int i = 0; i < listLength; i++) {
					temp = coordsPlaced.get(i);
					for (int j = 0; j < M_GRID_SIZE; j++) {
						for (int k = 0; k < M_GRID_SIZE; k++) {
							coord_index = (j * M_GRID_SIZE) + k;
							if (temp == coord_index) {
								playerGridButton[j][k].setText("^");
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
				playerSquaresSelected = false;
				coordsSelected.clear();
				gameBoard.setPlayerMessage(" ");
				shipSelected = false;
				data.setShipSelected(shipSelected);
				//coordSelected = false;
			}
		});
		return cancelPlace;
	}
	
	public JButton setBeginButton() {
		beginBattle.setEnabled(false);
		beginBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.testGUI();
			}
		});
		return beginBattle;
	}
	
	public JButton setEnemyGridButton(int buttonRow, int buttonCol) {
		Font font = new Font("Arial Black", Font.BOLD, 8);
		coord_index = (buttonRow * M_GRID_SIZE) + buttonCol;
		//////Add boolean parameter for unknown occupied/unoccupied////////
		SingleCoordinate coordinate = new SingleCoordinate(coord_index,false,'e');
		coordinate.setButton(enemyGridButton[buttonRow][buttonCol]);
		data.addEnemyCoordinate(coord_index, coordinate);

		enemyGridButton[buttonRow][buttonCol].setFont(font);
		enemyGridButton[buttonRow][buttonCol].setBackground(new Color(131, 209, 232));
		enemyGridButton[buttonRow][buttonCol].setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
		enemyGridButton[buttonRow][buttonCol].setCursor(new Cursor(Cursor.HAND_CURSOR));
		enemyGridButton[buttonRow][buttonCol].addMouseListener(new MouseAdapter() {
//			  public void mouseEntered(MouseEvent evt) {
//			    JButton button = (JButton) evt.getSource();
//			    enemyGridButton[buttonRow][buttonCol].setBackground(Color.CYAN);
//			  }

//			  public void mouseExited(java.awt.event.MouseEvent evt) {
//			    JButton button = (JButton) evt.getSource();
//			    enemyGridButton[buttonRow][buttonCol].setBackground(new Color(131, 209, 232));
//			  }
			  
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  JButton button = (JButton) evt.getSource();
				  if (targetSquareSelected) {
					  String message = "You already have a coordinate selected";
					  gameBoard.setEnemyMessage(message);
				  }
				  else {
					  enemyGridButton[buttonRow][buttonCol].setBorder(new LineBorder(Color.BLACK));
					  coord_index = (buttonRow * M_GRID_SIZE) + (buttonCol);
					  char x = rows[buttonRow];
					  String y = cols[buttonCol];
					  String message = "Fire on coordinate " + x + "-" + y + "?";
					  gameBoard.setEnemyMessage(message);
					  fire.setEnabled(true);
					  targetSquareSelected = true;
				  }
			  }
		});
		return enemyGridButton[buttonRow][buttonCol];
	}
	
	public JButton setFireButton() {
		fire = new JButton("Fire!");
		fire.setEnabled(false);
		fire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String playerMessage = " ";
				String enemyMessage = " ";
				//if (!targetSquareSelected) {
				//message = "You must select a coordinate to fire on";
				//gameBoard.setEnemyMessage(message);
				//SingleCoordinate fire_coord = getCoord(coord_index);
				//boolean occupied = fire_coord.isOccupied();
				//if (occupied) {
				//	String firedShip = fire_coord.getShipType();
				//	if (firedShip.equals("Destroyer"))
				//		destroyer.hit();
				//	else if (firedShip.equals("Submarine"))
				//		submarine.hit();
				//	fire_coord = new SingleCoordinate(coord_index,true,'h');
				//	changeCoord(coord_index, fire_coord);
				//else
				//	fire_coord = new SingleCoordinate(coord_index,false,'m');
				//firedAtCoords.add(fire_coord);
				//int fire_col = coord_index mod 10;
				//int fire_row = coord_index - fire_col;
				//enemyGridButton[fire_row][fire_col].setEnabled(false);
				//}
				
				//Test method
				if (!targetSquareSelected ) {
					enemyMessage = "You must select a coordinate to fire on";
					gameBoard.setEnemyMessage(enemyMessage);
				}
				else {
					SingleCoordinate fire_coord = data.getEnemyCoordinate(coord_index);
					boolean fired = fire_coord.isFired();
					if (fired) {
						enemyMessage = "You have already fired on this coordinate";
						gameBoard.setEnemyMessage(enemyMessage);
					}
					else {
						double column = (double)coord_index % M_GRID_SIZE;
						double row = (coord_index - column) / (double)M_GRID_SIZE;
						int fire_col = (int)column;
						int fire_row = (int)row;
						boolean occupied = fire_coord.isOccupied();
						if (occupied) {
							String firedShip = fire_coord.getshipType();
							boolean isSunk = false;
							if (firedShip.equals("Destroyer")) {
								destroyer.hit();
								isSunk = destroyer.sunk();
								if (isSunk)
									data.addShipSunk();
							}
							else if (firedShip.equals("Submarine")) {
								submarine.hit();
								isSunk = submarine.sunk();
								if (isSunk)
									data.addShipSunk();
							}
							else if (firedShip.equals("Cruiser")) {
								cruiser.hit();
								isSunk = cruiser.sunk();
								if (isSunk)
									data.addShipSunk();
							}
							else if (firedShip.equals("Battleship")) {
								battleship.hit();
								isSunk = battleship.sunk();
								if (isSunk)
									data.addShipSunk();
							}
							else if (firedShip.equals("Carrier")) {
								carrier.hit();
								isSunk = carrier.sunk();
								if (isSunk)
									data.addShipSunk();
							}
							enemyMessage = "It's a hit!";
							gameBoard.setEnemyMessage(enemyMessage);
							playerMessage = "The enemy has hit your " + shipType;
							gameBoard.setPlayerMessage(playerMessage);
							if (isSunk) {
								fire_coord = new SingleCoordinate(coord_index,true,'s');
								int num = data.getNumShipsSunk();
								enemyMessage = "Enemy Ships Sunk: " + num;
								gameBoard.setEnemyMessage(enemyMessage);
							}
							else {
								fire_coord = new SingleCoordinate(coord_index,true,'h');
							}
							enemyGridButton[fire_row][fire_col].setText("X");
							enemyGridButton[fire_row][fire_col].setBackground(Color.RED);
							playerGridButton[fire_row][fire_col].setText("X");
							playerGridButton[fire_row][fire_col].setBackground(Color.RED);
							boolean won = data.playerWon();
							if (won) {
								enemyMessage = "YOU WIN!!!";
								gameBoard.setEnemyMessage(enemyMessage);
								playerMessage = "Defeated";
								gameBoard.setPlayerMessage(playerMessage);
							}
						}
						else {
							fire_coord = new SingleCoordinate(coord_index,true,'m');
							enemyMessage = "It's a miss";
							gameBoard.setEnemyMessage(enemyMessage);
							playerMessage = "Enemy missed";
							gameBoard.setPlayerMessage(playerMessage);
							enemyGridButton[fire_row][fire_col].setText("O");
							enemyGridButton[fire_row][fire_col].setBackground(Color.WHITE);
							playerGridButton[fire_row][fire_col].setText("O");
							playerGridButton[fire_row][fire_col].setBackground(Color.WHITE);
						}
						fire_coord.setFiredAt(true);
						data.changeEnemyCoordinate(coord_index, fire_coord);
						firedAt.add(coord_index);
						enemyGridButton[fire_row][fire_col].setEnabled(false);
					}
					//enemyGridButton[j][k].setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
					targetSquareSelected = false;
				}
			}
		});
		return fire;
	}
	
	public JButton setCancelFireButton() {
		cancelFire = new JButton("Cancel");
		cancelFire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (targetSquareSelected) {
					double column = (double)coord_index % M_GRID_SIZE;
					double row = (coord_index - column) / (double)M_GRID_SIZE;
					int select_col = (int)column;
					int select_row = (int)row;
					enemyGridButton[select_row][select_col].setEnabled(true);
					enemyGridButton[select_row][select_col].setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
				}
				targetSquareSelected = false;
			}
		});
		return cancelFire;
	}
	
	public void displayError(String message) {
		 gameBoard.setError(message);
	}
	
//	public void displayPlayerMessage(String message) {
//		//Pass selected, placed, hit or miss, or error message into playerStatus JLabel in GameBoard
//	    GameBoard gameBoard = (GameBoard)container.getComponent(1);
//	    gameBoard.setPlayerMessage(message);
//	}
//	
//	public void displayEnemyMessage(String message) {
//		//Pass selected, placed, hit or miss, or error message into enemyStatus JLabel in GameBoard
//	    GameBoard gameBoard = (GameBoard)container.getComponent(1);
//	    gameBoard.setEnemyMessage(message);
//	}
	
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
