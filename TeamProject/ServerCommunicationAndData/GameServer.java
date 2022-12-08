package ServerCommunicationAndData;

import java.io.*;
import java.util.*;

import ClientInterface.CreateAccountData;
import ClientInterface.LoginData;
import ClientInterface.ShotData;
import ocsf.server.*;
import serverController.GameController;
import serverController.GameData;
import serverController.SingleCoordinate;

public class GameServer extends AbstractServer {
	private boolean running = false;
	private boolean listening = false;
	private Database db;
	private GameController gc;
	private GameData gameData;
	private User user;
	private ArrayList<User> onlinePlayers = new ArrayList<User>();
	//private ArrayList<User> startedGames = new ArrayList<User>();
	private User player1;
	private User player2;
	private long player1_id;
	private long player2_id;
	private int p1_num;
	private int p2_num;
	private ConnectionToClient conn1;
	private ConnectionToClient conn2;
	private String message;

	public GameServer() {
		super(8300);
		this.setTimeout(50000);
		db = new Database();
		gameData = new GameData();
		gc = new GameController(gameData);
	}

	public void setDatabase(Database db) {
		this.db = db;
	}
	
	public void setGameData(GameData gameData) {
		this.gameData = gameData;
	}
	
	public void setGameController(GameController gc) {
		this.gc = gc;
	}

	public void serverStarted() {
		running = true;
		System.out.println("Sever Started");
		listening = isListening();
		if (listening)
			System.out.println("Server Listening");
		else
			System.out.println("Server NOT Listening");
	}

	public void serverStopped() {
		System.out.println("Server Stopped");
	}

	public void serverClosed() {
		running = false;
		System.out.println("Server Closed");
	}

	// Getter that returns whether the server is currently running.
	public boolean isRunning() {
		return running;
	}

	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		System.out.println("Attempting to receive message from client");
		if (arg0 instanceof String) {
			message = (String) arg0;
		}

		if (arg0 instanceof LoginData) {
			System.out.println("This is an instance of LoginData");
			// Check the username and password with the database.
			LoginData data = (LoginData)arg0;
			boolean verify = false;
			Object result;

			verify = db.verifyAccount(data.getUsername(), data.getPassword());

			if (verify) {
				user = new User(data.getUsername(), data.getPassword());
				user.setConnectionToClient(arg1);
				System.out.println("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
				result = "LoginSuccessful";
				onlinePlayers.add(user);

			}
			else {
				result = "LoginError";
				System.out.println("Client " + arg1.getId() + " failed to login\n");
			}

			try {
				arg1.sendToClient(result);
				arg1.sendToClient("I am: " + data.getUsername());
				int playersReady = onlinePlayers.size();
				if (playersReady >= 2) {
					player1 = onlinePlayers.get(0);
					player2 = onlinePlayers.get(1);
					conn1 = player1.getConnectionToClient();
					conn2 = player2.getConnectionToClient();
					player1_id = conn1.getId();
					player2_id = conn2.getId();
					p1_num = (int) player1_id;
					p2_num = (int) player2_id;
					String message1 = "Welcome, Player 1";
					String message2 = "Welcome, Player 2";
					try {
						conn1.sendToClient(message1);
						conn2.sendToClient(message2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("Could not send login verification data to client");
				e.printStackTrace();
			}

		}

		else if (arg0 instanceof CreateAccountData) {
			System.out.println("This is an instance of CreateAccountData");
			// Try to create the account.
			CreateAccountData data = (CreateAccountData)arg0;
			boolean created = false;
			Object result;

			created = db.createAccount(data.getUsername(), data.getPassword());


			if (created) {
				result = "CreateSuccessful";
				System.out.println("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
			}
			else {
				result = "CreateError";
				System.out.println("Client " + arg1.getId() + " failed to create a new account\n");
			}

			try {
				arg1.sendToClient(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not send account creation result to client");
				e.printStackTrace();
			}
		}
		
		else if (arg0 instanceof SingleCoordinate) {
			long currentPlayer = arg1.getId();
			int currentPlayerNum = (int) currentPlayer;
			SingleCoordinate coordToPass = (SingleCoordinate) arg0;
			int indexToPass = coordToPass.getCoordIndex();
			System.out.println("Storing coordinate " + indexToPass);
			
			if (currentPlayerNum == p1_num) {
				gameData.addPlayer1Coordinate(indexToPass, coordToPass);
			}
			else if (currentPlayerNum == p2_num) {
				gameData.addPlayer2Coordinate(indexToPass, coordToPass);
			}
			
//			if (currentPlayerNum == p1_num) {
//				try {
//					conn2.sendToClient(arg0);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//			else if (currentPlayerNum == p2_num) {
//				try {
//					conn1.sendToClient(arg0);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
		}

		else if (arg0 instanceof ArrayList) {
			long currentPlayer = arg1.getId();
			int currentPlayerNum = (int) currentPlayer;
			
			ArrayList<SingleCoordinate> arrToPass = (ArrayList<SingleCoordinate>) arg0;
			
			if (currentPlayerNum == p1_num) {
				gameData.setAllPlayer1Coords(arrToPass);
			}
			else if (currentPlayerNum == p2_num) {
				gameData.setAllPlayer2Coords(arrToPass);
			}
//			if (currentPlayerNum == p1_num) {
//				try {
//					conn2.sendToClient(arg0);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//			else if (currentPlayerNum == p2_num) {
//				try {
//					conn1.sendToClient(arg0);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
		}

		else if (arg0 instanceof ShotData) {
			long currentPlayer = arg1.getId();
			int currentPlayerNum = (int) currentPlayer;
			ShotData shot = (ShotData) arg0;
			int shot_coord = shot.getIndex();
			int shot_row = shot.getRow();
			int shot_col = shot.getCol();
			String shotResult;
			
			if (currentPlayerNum == p1_num) {
				gc.p1FiresAtp2(shot_coord, shot_row, shot_col);
			}
			else if (currentPlayerNum == p2_num) {
				gc.p2FiresAtp1(shot_coord, shot_row, shot_col);
			}
			
			
//			if (currentPlayerNum == p1_num) {
//				try {
//					conn2.sendToClient(arg0);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//			else if (currentPlayerNum == p2_num) {
//				try {
//					conn1.sendToClient(arg0);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
		}
		
		else if (message.equals("YOU WIN!!!")) {
			long currentPlayer = arg1.getId();
			int currentPlayerNum = (int) currentPlayer;
			
			if (currentPlayerNum == p1_num) {
				try {
					conn2.sendToClient(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				boolean won = db.gameWon(player2.getUsername());
				if (won)
					System.out.println("Successfully updated database");
				
			}
			else if (currentPlayerNum == p2_num) {
				try {
					conn1.sendToClient(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				boolean won = db.gameWon(player1.getUsername());
				if (won)
					System.out.println("Successfully updated database");
			}
		}
	}


	public void listeningException(Throwable exception) {
		running = false;
		System.out.println("Listening exception: " + exception.getMessage());
	}

	public void clientConnected(ConnectionToClient connectedClient) {
		System.out.println("Client " + connectedClient.getId() +  " Connected");
		
		try {
			connectedClient.sendToClient("Hello there ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't send message to client");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameServer server = new GameServer();
		Scanner scanner = new Scanner(System.in);
		String input;

		try {
			server.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Server failed to listen");
			e.printStackTrace();
		}

//		System.out.println("Type 'close' to close the server and end the program.");
//
//		do {
//			input = scanner.nextLine();
//
//			if (input.equals("close"))
//				break;
//		} while (server.isRunning()); 
//		scanner.close();
//
//		if (input.equals("close")) {
//
//			try {
//				db.finish();
//				server.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				System.out.println("Could not successfully close server");
//				e.printStackTrace();
//			}
//		}

	}

}
