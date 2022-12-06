package ServerCommunicationAndData;

import java.io.*;
import java.util.*;

import ClientCommunication.StartNewGame;
import ClientInterface.CreateAccountData;
import ClientInterface.LoginData;
import ClientInterface.ShotData;
import ocsf.server.*;
import serverController.Match;
import serverController.SingleCoordinate;

public class GameServer extends AbstractServer {
	private boolean running = false;
	private boolean listening = false;
	private Database db;
	private User user;
	private ArrayList<User> onlinePlayers = new ArrayList<User>();
	private ArrayList<User> startedGames = new ArrayList<User>();
	private Match match;
	private User player1;
	private User player2;
	private long player1_id;
	private long player2_id;
	private ConnectionToClient conn1;
	private ConnectionToClient conn2;
	private String message;

	public GameServer() {
		super(8300);
		this.setTimeout(500);
	}

	public void setDatabase(Database db) {
		this.db = db;
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

			if (currentPlayer == player1_id) {
				try {
					conn2.sendToClient(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if (currentPlayer == player2_id) {
				try {
					conn1.sendToClient(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

		else if (arg0 instanceof ArrayList) {
			long currentPlayer = arg1.getId();

			if (currentPlayer == player1_id) {
				try {
					conn2.sendToClient(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			else if (currentPlayer == player2_id) {
				try {
					conn1.sendToClient(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

		else if (arg0 instanceof ShotData) {
			long currentPlayer = arg1.getId();

			if (currentPlayer == player1_id) {
				try {
					conn2.sendToClient(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (currentPlayer == player2_id) {
				try {
					conn1.sendToClient(arg0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		else if (message.equals("YOU WIN!!!")) {
			long currentPlayer = arg1.getId();

			if (currentPlayer == player1_id) {
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
			else if (currentPlayer == player2_id) {
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
		System.out.println("Client Connected");
		try {
			connectedClient.sendToClient("Hello there");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't send message to client");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameServer server = new GameServer();
		Database db = new Database();
		Scanner scanner = new Scanner(System.in);
		String input;
		server.setDatabase(db);

		try {
			server.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Server failed to listen");
			e.printStackTrace();
		}

		System.out.println("Type 'close' to close the server and end the program.");

		do {
			input = scanner.nextLine();

			if (input.equals("close"))
				break;
		} while (server.isRunning()); 
		scanner.close();

		if (input.equals("close")) {

			try {
				db.finish();
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not successfully close server");
				e.printStackTrace();
			}
		}

	}

}
