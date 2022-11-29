package ClientInterface;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class TotalWinsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TotalWinsPanel() {
		setLayout(null);
		
		JLabel lblTotalWins = new JLabel("Total Wins");
		lblTotalWins.setBounds(182, 35, 87, 14);
		add(lblTotalWins);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(113, 93, 211, 131);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

	}

}
