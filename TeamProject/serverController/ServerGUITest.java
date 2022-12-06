package serverController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;

public class ServerGUITest extends JFrame {
	private JLabel opboard;
	private JLabel myboard;
	private GameBoard oceanGrid;
	private GameData playerData;
	private GameController playerGC;
	
	public ServerGUITest(String title) {
		int i = 0;
		
		//Set the title and default close operation
		this.setTitle("Battleship");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create the card layout container
		//CardLayout cardLayout = new CardLayout();
		//FlowLayout flowLayout = new FlowLayout();
		JPanel container1 = new JPanel();
		//JPanel container2 = new JPanel();
		
		//Create the Controller
		//ADD TWO CONTAINERS AS ARGUMENTS FOR GAMECONTROLLER LATER//
		//GameController gc = new GameController(container1);
		//GameController gc2 = new GameController(container2);
		
		playerData = new GameData();
		
		oceanGrid = new GameBoard(playerData);
		
		playerGC = new GameController(playerData, oceanGrid);
		oceanGrid.setGameController(playerGC);
		oceanGrid.buildBoard();
		
		//Create the view
		//oceanGrid = new GameBoard(gc);
		//JPanel targetGrid = new GameBoard(gc2);
		
		//Add the view to the card layout container
		container1.add(oceanGrid);
		//container2.add(targetGrid);
		
		//Show the initial view in the card layout
		//cardLayout.show(board, "1");
		
		//Add the card layout container to the JFrame
		//GradBagLayout makes the container stay centered in the window
		this.setLayout(new GridBagLayout());
		this.add(container1);
		//this.add(container2);
		
		//Show the JFrame
		this.setSize(1250, 700);
		this.setVisible(true);
		
		//opboard = new JLabel("Opponent's Board");
		//myboard = new JLabel("My Board");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ServerGUITest(args[0]);
	}

}
