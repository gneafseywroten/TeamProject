package ClientInterface;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import ClientCommunication.GameClient;

public class CreateAccountControl implements ActionListener {
	private JPanel container;
	private GameClient client;

	public CreateAccountControl(JPanel container, GameClient client){
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae) {
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Cancel button takes the user back to the initial panel.
		if (command == "Cancel") {
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");
		}

		// The Submit button creates a new account.
		else if (command == "Submit") {
			// Get the text the user entered in the three fields.
			CreateAccountPanel createAccountPanel = (CreateAccountPanel)container.getComponent(2);
			String username = createAccountPanel.getUsername();
			String password = createAccountPanel.getPassword();
			String passwordVerify = createAccountPanel.getPasswordVerify();

			// Check the validity of the information locally first.
			if (username.equals("") || password.equals("")) {
				System.out.println("You must enter a username and password.");
				return;
			}
			else if (!password.equals(passwordVerify)) {
				System.out.println("The passwords do not match");
				return;
			}
			if (password.length() < 6) {
				System.out.println("The password must be at least 6 characters.");
				return;
			}
			// Submit the new account information to the server.
			CreateAccountData data = new CreateAccountData(username, password);
			try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not send account data to server");
				e.printStackTrace();
			}
		}

	}

	public void createAccountSuccess() {
		CreateAccountPanel createPanel = (CreateAccountPanel) container.getComponent(2);
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "2");
	}

	public void displayError(String error) {
		CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
		createAccountPanel.setError(error);
	}
}