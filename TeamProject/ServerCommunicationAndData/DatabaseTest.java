package ServerCommunicationAndData;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;

public class DatabaseTest {

	private String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	private String[] passwords = {"hello123","pass123","123456","hello1234"};
	private Database db;
	private int rando;
	
	@Before
	public void setUp() throws Exception 
	{
	  db = new Database(); 
	  rando = ((int)Math.random()*users.length); 
	}
	
	@Test
	public void testSetConnection()
	{
		db.setConnection("./serverCommunicationAndData/db.properties");
		Connection connection = (Connection) db.getConnection();
		assertNotNull("Check setConnection", connection);
	}
	
	
	@Test
	public void testExecuteDML() throws SQLException
	{
		//involk the setConnection method
		db.setConnection("./serverCommunicationAndData/db.properties");
		//retrieve connection using getConnection method
		Connection connection = (Connection) db.getConnection();
		
		db.executeDML("insert into users values('mbrown3@uca.edu', aes_encrypt('001257904', 'key'))");
		assertNotNull("Check testExecuteDML", connection);
		
		
	}


}
