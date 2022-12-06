package ClientInterface;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InitialControl implements ActionListener {
private JPanel container;
	
	public InitialControl(JPanel container) {
		this.container = container;
	}
	
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();

		//If login button pressed, go to login panel
		if (command.equals("Login")) {
			// LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "2");
		//If the create button pressed, go to the create panel
		} else if (command.equals("Create")) {
			// CreatePanel createPanel = (CreatePanel)container.getComponent(2);
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "3");
		}
	}
}
