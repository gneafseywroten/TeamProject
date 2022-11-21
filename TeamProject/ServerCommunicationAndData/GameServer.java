package ServerCommunicationAndData;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer
{
  // Data fields for this chat server.
  private boolean running = false;
  private DatabaseFile database = new DatabaseFile();
  private int num_clients;

  // Constructor for initializing the server with default settings.
  public GameServer()
  {
    super(12345);
    this.setTimeout(500);
  }

  public boolean isRunning()
  {
    return running;
  }
  
  public void serverStarted()
  {
    running = true;
    System.out.println("Server started");
  }
  
   public void serverStopped()
   {
     System.out.println("Server stopped");
   }
 
  // When the server closes completely, update the GUI.
  public void serverClosed()
  {
    running = false;
    System.out.println("Server Closed");
  }

  // When a client connects or disconnects, display a message in the log.
  public void clientConnected(ConnectionToClient client)
  {
	  System.out.println("Client Connected");
  }
  
  public void clientDisconnected(ConnectionToClient client)
  {
	  System.out.println("Client Disconnected");
  }

  // When a message is received from a client, handle it.
  public void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
  {
	  System.out.println(arg0);
	 
    // If we received LoginData, verify the account information.
	 if (arg0 instanceof LoginData)
    {
      // Check the username and password with the database.
	LoginData data = (LoginData)arg0;
      Object result;
      String q = "select * from Users table";
      ArrayList<String> r = database.query(q);
      for (String row : r)
      {
      if (row.equals(data.getUsername() + data.getPassword() ))
      {
        result = "LoginSuccessful";
      }
      
      else
      {
        result =("The username and password are incorrect.");
      }
      // Send the result to the client.
      try
      {
    	  
        arg1.sendToClient(r);
      }
      catch (IOException e)
      {
        return;
      }
      }
      
   
    }
    
    // If we received CreateAccountData, create a new account.
    else if (arg0 instanceof CreateAccountData)
    {
      // Try to create the account.
      CreateAccountData data = (CreateAccountData)arg0;
      Object result;
      String q = "select * from Users table";
      ArrayList<String> r = database.query(q);
      
      for (String row : r)
      {
      if (row.equals(data.getUsername() + data.getPassword()))
      {
        result = "The username is already in use.";
      }
      else
      {
    	  System.out.println("false");
    	  result = "CreateAccountSuccessful";
    	  String d = "insert into Users values ('" + data.getUsername() + ", aes_encrypt('" + data.getPassword() + "' , 'key'";
    	  
    	  try {
			database.executeDML(d);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      // Send the result to the client.
      try
      {
        arg1.sendToClient(r);
      }
      catch (IOException e)
      {
        return;
      }
      }
      
      }
    }

  public void listeningException(Throwable exception) 
  {
    running = false;
    
  }
  
  
}



