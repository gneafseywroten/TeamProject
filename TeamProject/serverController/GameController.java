package serverController;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

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
	
	public GameController(JPanel container) {
		this.container = container;
	}
	
	public GameController() {
		
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
