package ServerCommunicationAndData;

import java.util.*;
import java.io.*;
import java.sql.*;

public class DatabaseFile
{
  private Connection conn;
  public DatabaseFile()
  {
	  try {
		FileInputStream fis = new FileInputStream("ServerCommunicationAndData/db.properties");
		
		Properties prop = new Properties();
		
		try 
		{
			prop.load(fis);
			 String url = prop.getProperty("url");
			 String user = prop.getProperty("user");
			 String pass = prop.getProperty("password");  
		     try {
			 conn = DriverManager.getConnection(url,user,pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) 
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
  
  public ArrayList<String> query(String query)
  {
    ArrayList<String> list = new ArrayList<String>();
    try
    {
    Statement statement = conn.createStatement();
    
    ResultSet rs = statement.executeQuery(query);
    
    String row = new String();
    ResultSetMetaData rmd = rs.getMetaData();
    int no_columns = rmd.getColumnCount();
    
    while(rs.next())
    {
    	for(int i = 0; i < no_columns; i++)
    	{
    		row += rs.getString(i + 1);
    	}
    }
    
    list.add(row);
    
    if(row.length() == 0)
    {
    	return null;
    }
    else
    {
    	return list;
    }
    }
    catch(SQLException sql)
    {
    	return null;
    }
  }
  
  public void executeDML(String dml) throws SQLException
  {
	  
	  Statement statement1 = conn.createStatement();
	  statement1.execute(dml);

  }
  
}