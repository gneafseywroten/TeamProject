package ClientCommunication;

import ClientInterface.LoginControl;
import ClientInterface.CreateAccountControl;
import ocsf.client.AbstractClient;

public class GameClient extends AbstractClient {

	private boolean connected = false;
	private LoginControl loginControl;
	private CreateAccountControl createAccountControl;

	public GameClient(String host) {
		super(host, 8300);
	}

	public void setLoginControl(LoginControl loginControl) {
		this.loginControl = loginControl;
	}

	public void setCreateAccountControl(CreateAccountControl cac) {
		this.createAccountControl = cac;
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
		System.out.println("Message received from server:" + message);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameClient client = new GameClient(args[0]);

	}
}
