package ServerCommunicationAndData;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {
	private Connection conn;
	private String username;
	private String password;
	private String url;
	private String aeskey;
	private FileInputStream fis;
	
		
		public void setConnection(String fn) {
			try {
				fis = new FileInputStream("./serverCommunicationAndData/db.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not find properties file");
				e.printStackTrace();
			}
			
			Properties props = new Properties();
			try {
				props.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not load properties file");
				e.printStackTrace();
			}
			username = props.getProperty("user");
			password = props.getProperty("password");
			url = props.getProperty("url");
			aeskey = props.getProperty("aeskey");
			
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not establish connection to database");
				e.printStackTrace();
			}
		}
		
		public Connection getConnection() {
			return conn;
		}
		
	
	public ResultSet query(String query) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(query);
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Query was unsuccessful");
			return null;
		}
	}
	
	public boolean executeDML(String dml) {
		Statement stmt;
		System.out.println("Readying to execute query");
		try {
			System.out.println("Almost there");
			stmt = conn.createStatement();
			System.out.println("Executing query");
			stmt.execute(dml);
			System.out.println("Query excuted");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Statement execution unsuccessful");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean createAccount(String username, String password) {
		String dml = "INSERT INTO users VALUES('"+username+"',aes_encrypt('"+password+"','"+aeskey+"'),0);";
		System.out.println("Outgoing query to create account");
		boolean executed = executeDML(dml);
		return executed;
	}
	
	public boolean verifyAccount(String username, String password) {
		ResultSet results = query("SELECT * FROM users WHERE username='"+username+"' AND password=aes_encrypt('"+password+"','"+aeskey+"');");
		try {
			return !(results == null || (!results.next()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not verify account");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean gameWon(String username) {
		String dml = "UPDATE users SET wins=wins+1 where username='"+username+"';";
		boolean executed = executeDML(dml);
		return executed;
	}
	
	public boolean finish() {
		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL connection not established");
			e.printStackTrace();
			return false;
		}
	}

}
