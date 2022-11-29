package ServerCommunicationAndData;

import java.io.Serializable;

public class LoginData implements Serializable 
{
  private String username;
  private String password;
  private int num_win;
  
  public int getNum_Win()
  {
	  return num_win;
  }

  public String getUsername()
  {
    return username;
  }
  public String getPassword()
  {
    return password;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public void setNum_win(int num_win)
  {
	  this.num_win = num_win;
  }
  
  public LoginData(String username, String password)
  {
    setUsername(username);
    setPassword(password);
  }
}
