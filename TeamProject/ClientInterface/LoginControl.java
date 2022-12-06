package ClientInterface;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JPanel;

import ClientCommunication.GameClient;

public class LoginControl implements ActionListener {
	
	private GameClient client;
	private JPanel container;
	
	public LoginControl(JPanel container, GameClient client) {
		this.container = container;
		this.client = client;
	}
	
public void actionPerformed(ActionEvent ae) {
		
		String command = ae.getActionCommand();
		
		if (command == "Cancel") {
			CardLayout cardLayout = (CardLayout)container.getLayout();
		    cardLayout.show(container, "1");
		}
		else if (command == "Submit") {
			// Get the username and password the user entered.
		      LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
		      LoginData data = new LoginData(loginPanel.getUsername(), loginPanel.getPassword());
		      
		      // Check the validity of the information locally first.
		      if (data.getUsername().equals("") || data.getPassword().equals("")) {
		    	  displayError("You must enter a username and password.");
		          return;
		      }
		      
		      try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Data not sent");
			}
		}
		
	}
	
	public void loginSuccess() {
		LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
		
		CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "7");
	}
	
	public void displayError(String error) {
		 LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
		 loginPanel.setError(error);
	}

}