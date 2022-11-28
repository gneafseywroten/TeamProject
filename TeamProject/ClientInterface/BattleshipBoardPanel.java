package ClientInterface;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class BattleshipBoardPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BattleshipBoardPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnDestroyer = new JButton("Destroyer");
		GridBagConstraints gbc_btnDestroyer = new GridBagConstraints();
		gbc_btnDestroyer.insets = new Insets(0, 0, 5, 0);
		gbc_btnDestroyer.gridx = 0;
		gbc_btnDestroyer.gridy = 2;
		add(btnDestroyer, gbc_btnDestroyer);
		
		JButton btnCruiser = new JButton("Cruiser");
		GridBagConstraints gbc_btnCruiser = new GridBagConstraints();
		gbc_btnCruiser.insets = new Insets(0, 0, 5, 0);
		gbc_btnCruiser.gridx = 0;
		gbc_btnCruiser.gridy = 3;
		add(btnCruiser, gbc_btnCruiser);
		
		JButton btnSubmarine = new JButton("Submarine");
		GridBagConstraints gbc_btnSubmarine = new GridBagConstraints();
		gbc_btnSubmarine.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmarine.gridx = 0;
		gbc_btnSubmarine.gridy = 4;
		add(btnSubmarine, gbc_btnSubmarine);
		
		JButton btnBattleship = new JButton("Battleship");
		GridBagConstraints gbc_btnBattleship = new GridBagConstraints();
		gbc_btnBattleship.insets = new Insets(0, 0, 5, 0);
		gbc_btnBattleship.gridx = 0;
		gbc_btnBattleship.gridy = 5;
		add(btnBattleship, gbc_btnBattleship);
		
		JButton btnCarrier = new JButton("Carrier");
		GridBagConstraints gbc_btnCarrier = new GridBagConstraints();
		gbc_btnCarrier.insets = new Insets(0, 0, 5, 0);
		gbc_btnCarrier.gridx = 0;
		gbc_btnCarrier.gridy = 6;
		add(btnCarrier, gbc_btnCarrier);
		
		JButton btnPlaceShip = new JButton("Place Ship");
		GridBagConstraints gbc_btnPlaceShip = new GridBagConstraints();
		gbc_btnPlaceShip.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlaceShip.gridx = 0;
		gbc_btnPlaceShip.gridy = 8;
		add(btnPlaceShip, gbc_btnPlaceShip);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 9;
		add(btnCancel, gbc_btnCancel);

	}

}
