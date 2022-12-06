package ClientInterface;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import ClientCommunication.GameClient;
import ocsf.client.*;

public class ClientGUI extends JFrame {

	public ClientGUI(GameClient client) {

		this.setTitle("Battleship");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the card layout container.
		CardLayout cardLayout = new CardLayout();
		JPanel container = new JPanel(cardLayout);

		InitialControl ic = new InitialControl(container);
		LoginControl lc = new LoginControl(container,client);
		CreateAccountControl cac = new CreateAccountControl(container,client);
		StartJoinGameControl sjgc = new StartJoinGameControl(container,client);
		BattleshipBoardData data = new BattleshipBoardData();
		
		BattleshipBoardPanel gameBoard = new BattleshipBoardPanel(container, client, data);
		

		client.setLoginControl(lc);
		client.setCreateAccountControl(cac);
		BattleshipBoardController gameController = new BattleshipBoardController(data,gameBoard);
		gameBoard.setBattleshipBoardController(gameController);
		gameBoard.buildBoard();

		JPanel view1 = new InitialPanel(ic);
		JPanel view2 = new LoginPanel(lc);
		JPanel view3 = new CreateAccountPanel(cac);
		JPanel view4 = new StartJoinGamePanel(sjgc);
		//JPanel view5 = new JoinPanel();
		//JPanel view6 = new ReadyUpPanel();
		JPanel view7 = gameBoard;

		container.add(view1,"1");
		container.add(view2,"2");
		container.add(view3,"3");
		container.add(view4,"4");
		//container.add(view5,"5");
		//container.add(view6,"6");
		container.add(view7,"7");

		cardLayout.show(container, "1");

		// Add the card layout container to the JFrame.
		// GridBagLayout makes the container stay centered in the window.
		this.setLayout(new GridBagLayout());
		this.add(container);

		// Show the JFrame.
		this.setSize(1250, 700);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String host = args[0];

		//Instantiate client
		GameClient client = new GameClient(host);

		new ClientGUI(client);

		//Open client connection
		try {
			client.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Client could not connect");
		}

	}

}