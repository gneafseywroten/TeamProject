package ClientInterface;

import java.awt.BorderLayout;
import java.awt.GameLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientInterface.GameClient;
import ClientInterface.ClientGUI;
import ClientInterface.GamePanel;
import ClientInterface.CreateAccountControl;
import ClientInterface.CreateAccountPanel;
import ClientInterface.InitialControl;
import ClientInterface.InitialPanel;
import ClientInterface.LoginControl;
import ClientInterface.TotalWinsPanel;
import ClientInterface.ReadyUpPanel;
import ClientInterface.StartJoinGamePanel;
import ClientInterface.DifficultyPanel;

public class ClientGUI extends JFrame
{
  
  
  // Constructor that creates the client GUI.
  public ClientGUI()
  {
    // Set up the chat client.
   GameClient client = new GameClient();
    client.setHost("localhost");
    client.setPort(8300);
    try
    {
      client.openConnection();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    
    
    // Set the title
    this.setTitle("Game Client");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    // Create the card layout container.
    GameLayout gameLayout = new GameLayout();
    JPanel container = new JPanel(gameLayout);
    
    //Create the Controllers next
    //Next, create the Controllers
    InitialControl ic = new InitialControl(container,client);
    LoginControl lc = new LoginControl(container,client);
    CreateAccountControl cac = new CreateAccountControl(container,client);
    
    //Set the client info
    client.setLoginControl(lc);
    client.setCreateAccountControl(cac);
   
    
    // Create the four views. (need the controller to register with the Panels
    JPanel view1 = new InitialPanel(ic);
    JPanel view2 = new LoginPanel(lc);
    JPanel view3 = new CreateAccountPanel(cac);
    JPanel view4 = new BattleshipBoardPanel();
    JPanel view5 = new DifficultyPanel();
    JPanel view6 = new ReadyUpPanel();
    JPanel view7 = new TotalWinsPanel();
    
    // Add the views to the card layout container.
    container.add(view1, "1");
    container.add(view2, "2");
    container.add(view3, "3");
    container.add(view4, "4");
    container.add(view5, "5");
    container.add(view6, "6");
    container.add(view7, "7");
   
    
    // Show the initial view in the card layout.
    gameLayout.show(container, "1");
    
    // Add the card layout container to the JFrame.
    // GridBagLayout makes the container stay centered in the window.
    this.setLayout(new GridBagLayout());
    this.add(container);

    // Show the JFrame.
    this.setSize(550, 350);
    this.setVisible(true);
  }

  // Main function that creates the client GUI when the program is started.
  public static void main(String[] args)
  {
    new ClientGUI();
  }
}
