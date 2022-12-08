package ClientCommunication;

import java.util.ArrayList;

import ClientInterface.BattleshipBoardController;
import ClientInterface.CreateAccountControl;
import ClientInterface.LoginControl;
import ClientInterface.ShotData;
import ServerCommunicationAndData.User;
import ocsf.client.AbstractClient;
import serverController.SingleCoordinate;

public class GameClient extends AbstractClient {
		
		private boolean connected = false;
		private LoginControl loginControl;
		private CreateAccountControl createAccountControl;
		private BattleshipBoardController gameControl;
		
		public GameClient(String host) {
			super(host, 8300);
		}
		
		public void setLoginControl(LoginControl loginControl) {
			this.loginControl = loginControl;
		}
		
		public void setCreateAccountControl(CreateAccountControl cac) {
			this.createAccountControl = cac;
		}
		
		public void setGameControl (BattleshipBoardController gc) {
			this.gameControl = gc;
		}
		
		public void connectionException(Throwable exception) {
			System.out.println("Connection Exception Occurred");
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}

		public void connectionEstablished() {
			connected = true;
			System.out.println("Connected to server");
		}
		
		public void connectionClosed() {
			System.out.println("Connection closed");
		}
		
		protected void handleMessageFromServer(Object arg0) {
			// TODO Auto-generated method stub
			String message = (String)arg0;
			if (message.equals("LoginSuccessful")) {
				loginControl.loginSuccess();
			}
			else if (message.equals("LoginError")) {
				loginControl.displayError("Login failed");
			}
			else if (message.equals("CreateSuccessful")) {
				createAccountControl.createAccountSuccess();
			}
			else if (message.equals("CreateError")) {
				createAccountControl.displayError("Account could not be created");
			}
			else if (message.startsWith("Welcome, ")) {
				System.out.println(message);
				gameControl.setPlayerMessage(message);
			}
			else if (message.startsWith("Enemy hit ")) {
				gameControl.parsePlayerHitString(message);
			}
			else if (message.startsWith("Enemy missed ")) {
				gameControl.parsePlayerMissString(message);
			}
			else if (message.startsWith("Hit!")) {
				gameControl.parseEnemyHitString(message);
			}
			else if (message.startsWith("Miss")) {
				gameControl.parseEnemyMissString(message);
			}
			else if (message.equals("Defeated")) {
				
			}
			else if (message.equals("YOU WIN!!!")) {
				gameControl.gameWon();
			}
			else if (arg0 instanceof SingleCoordinate) {
				System.out.println("Received Coordinate");
				gameControl.receiveEnemyCoordinate((SingleCoordinate) arg0);
			}
			else if (arg0 instanceof ArrayList) {
				System.out.println("Received Arraylist");
				gameControl.setEnemyCoordinates((ArrayList<SingleCoordinate>) arg0);
			}
			else if (message.equals("Hello there ")) {
				System.out.println(message);
			}
			else if (arg0 instanceof ShotData) {
				System.out.println("Received Shot");
				gameControl.receiveFire((ShotData) arg0);
			}
			
			//System.out.println("Message received from server:" + message);
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameClient client = new GameClient(args[0]);

	}

}
