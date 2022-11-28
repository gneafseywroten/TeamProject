package ClientInterface;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

public class ReadyUpPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ReadyUpPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblWaitingForSecond = new JLabel("Waiting for Second Player To Join Game ...");
		GridBagConstraints gbc_lblWaitingForSecond = new GridBagConstraints();
		gbc_lblWaitingForSecond.gridwidth = 4;
		gbc_lblWaitingForSecond.insets = new Insets(0, 0, 5, 0);
		gbc_lblWaitingForSecond.gridx = 5;
		gbc_lblWaitingForSecond.gridy = 1;
		add(lblWaitingForSecond, gbc_lblWaitingForSecond);
		
		JButton btnReadyUp = new JButton("Ready Up");
		GridBagConstraints gbc_btnReadyUp = new GridBagConstraints();
		gbc_btnReadyUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnReadyUp.gridx = 7;
		gbc_btnReadyUp.gridy = 3;
		add(btnReadyUp, gbc_btnReadyUp);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 7;
		gbc_btnCancel.gridy = 5;
		add(btnCancel, gbc_btnCancel);

	}

}
