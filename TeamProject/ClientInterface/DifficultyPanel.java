package ClientInterface;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

public class DifficultyPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DifficultyPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSelectDifficulty = new JLabel("Select Difficulty");
		GridBagConstraints gbc_lblSelectDifficulty = new GridBagConstraints();
		gbc_lblSelectDifficulty.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectDifficulty.gridx = 7;
		gbc_lblSelectDifficulty.gridy = 1;
		add(lblSelectDifficulty, gbc_lblSelectDifficulty);
		
		JButton btnEasy = new JButton("Easy");
		GridBagConstraints gbc_btnEasy = new GridBagConstraints();
		gbc_btnEasy.insets = new Insets(0, 0, 5, 0);
		gbc_btnEasy.gridwidth = 3;
		gbc_btnEasy.gridx = 6;
		gbc_btnEasy.gridy = 3;
		add(btnEasy, gbc_btnEasy);
		
		JButton btnStandard = new JButton("Standard");
		GridBagConstraints gbc_btnStandard = new GridBagConstraints();
		gbc_btnStandard.insets = new Insets(0, 0, 5, 5);
		gbc_btnStandard.gridx = 7;
		gbc_btnStandard.gridy = 4;
		add(btnStandard, gbc_btnStandard);
		
		JButton btnDifficulty = new JButton("Difficult");
		GridBagConstraints gbc_btnDifficulty = new GridBagConstraints();
		gbc_btnDifficulty.insets = new Insets(0, 0, 5, 5);
		gbc_btnDifficulty.gridx = 7;
		gbc_btnDifficulty.gridy = 5;
		add(btnDifficulty, gbc_btnDifficulty);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 7;
		gbc_btnCancel.gridy = 7;
		add(btnCancel, gbc_btnCancel);

	}

}
