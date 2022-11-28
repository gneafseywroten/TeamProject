package ClientInterface;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartJoinGamePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public StartJoinGamePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnStartNewGame = new JButton("Start New Game");
		GridBagConstraints gbc_btnStartNewGame = new GridBagConstraints();
		gbc_btnStartNewGame.gridwidth = 4;
		gbc_btnStartNewGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartNewGame.gridx = 5;
		gbc_btnStartNewGame.gridy = 3;
		add(btnStartNewGame, gbc_btnStartNewGame);
		
		JButton btnNewButton = new JButton("Join Existing Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 4;
		add(btnNewButton, gbc_btnNewButton);
		
		JButton btnLogOut = new JButton("Log Out");
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.gridwidth = 2;
		gbc_btnLogOut.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogOut.gridx = 6;
		gbc_btnLogOut.gridy = 7;
		add(btnLogOut, gbc_btnLogOut);

	}

}
