package ServerCommunicationAndData;

import java.io.Serializable;

import ocsf.server.ConnectionToClient;

public class User implements Serializable {
	
	private String username;
	private String password;
	private int wins = 0;
	private ConnectionToClient conn;

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addWin() {
		wins++;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getNumWins() {
		return wins;
	}
	
	public ConnectionToClient getConnectionToClient() {
		return conn;
	}
	
	public void setConnectionToClient(ConnectionToClient conn) {
		this.conn = conn;
	}
	
	//Constructor
	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

}
