package ServerCommunicationAndData;

import java.io.*;
import java.util.*;
import ClientInterface.CreateAccountData;
import ClientInterface.LoginData;
import ocsf.server.*;

public class GameServer extends AbstractServer
{
	private boolean running = false;
	private boolean listening = false;
	private Database db;
	
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

		if (arg0 instanceof LoginData) {
			System.out.println("This is an instance of LoginData");
			// Check the username and password with the database.
			LoginData data = (LoginData)arg0;
			boolean verify = false;
			Object result;

			verify = db.verifyAccount(data.getUsername(), data.getPassword());

			if (verify) {
				//User user = new User(data.getUsername(), data.getPassword(), db.getUserRatio(data.getUsername()));
				System.out.println("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
				result = "LoginSuccessful";
			}
			else {
				result = "LoginError";
				System.out.println("Client " + arg1.getId() + " failed to login\n");
			}

			try {
				arg1.sendToClient(result);
				arg1.sendToClient("MyName: " + data.getUsername());
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
				//User user = new User(data.getUsername(), data.getPassword());
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
	}

	public void listeningException(Throwable exception) {
		running = false;
		System.out.println("Listening exception: " + exception.getMessage());
	}

	public void clientConnected(ConnectionToClient clientConnection) {
		System.out.println("Client Connected");
		try {
			clientConnection.sendToClient("Hello there");
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



