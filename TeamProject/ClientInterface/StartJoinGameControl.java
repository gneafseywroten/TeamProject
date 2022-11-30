package ClientInterface;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StartJoinGameControl implements ActionListener
{
  // Private data field for storing the container.
  private JPanel sj;
  private GameClient gc;
  
  public StartJoinGameControl(JPanel sj, GameClient gc)
  {
    this.sj = sj;
    this.gc = gc;
  }
  
  
  public void actionPerformed(ActionEvent ae)
  {
    
    String command = ae.getActionCommand();
    
    
    if (command.equals("Start Game"))
    {
      BattleshipBoardPanel boardPanel = (BattleshipBoardPanel)container.getComponent(1);
      boardPanel.setError("");
      CardLayout cardLayout = (CardLayout)sj.getLayout();
      cardLayout.show(sj, "2");
     
    }
    
   
    else if (command.equals("Join Game"))
    {
    	BattleshipBoardPanel boardPanel = (BattleshipBoardPanel)container.getComponent(2);
    	boardPanel.setError("");
      CardLayout cardLayout = (CardLayout)sj.getLayout();
      cardLayout.show(sj, "3");
    }
  }
}
