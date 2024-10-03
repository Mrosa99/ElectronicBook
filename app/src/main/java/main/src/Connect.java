package main.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connect extends LogPage {
	// static protected String userID;

	protected static ResultSet resultset;
	protected static PreparedStatement statement;
	protected static Connection c;

	// General string to create a SQL query
	protected static String query = "SELECT userID FROM users WHERE BINARY username = ? AND password = SHA2(?,256)";

	/** Method that attempts to connects to SQL database */
	Connect() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://root@localhost:3306/eBook_db", "Milton",
					"Aparicio99*");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection could not be established", "Connection Failed",
					JOptionPane.PLAIN_MESSAGE);
			LoginFrame.dispose();
			System.out.println(e);
		}
	}

	/** Login Page sends SQL command to database to search for user */
	public void login() throws SQLException {
		/** Prevents SQL Injection during the Login */
		statement = c.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, password);
		resultset = statement.executeQuery();
		if (resultset.next()) {
			userID = resultset.getString("UserID");
			LoginFrame.dispose();
			MainFrames mainframe = new MainFrames();
			mainframe.MainAppFrames();
		} else {
			JOptionPane.showMessageDialog(null, "Could not find account with username/password combination",
					"Invalid Login", JOptionPane.PLAIN_MESSAGE);
		}
	}

	/** Allows for first time user to create Account to use application */
	public void Signup() throws SQLException {
		String Signup_Query = "Select Username from Users where binary username=? ";
		statement = c.prepareStatement(Signup_Query);
		statement.setString(1, username);
		resultset = statement.executeQuery();
		// Will not allow empty and or blank username/password fields.
		if (username.isEmpty() || username.isBlank() || password.isBlank() || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field cannot be empty", "Empty Field", JOptionPane.PLAIN_MESSAGE);
		} else if (resultset.next()) {
			JOptionPane.showMessageDialog(null, "Username already exists", "Existing Account",
					JOptionPane.PLAIN_MESSAGE);
		} else {
			/** Prevents SQL Injection during the Login */
			String SignUp_Update = "INSERT INTO Users(username, password) VALUES (?, ?)";
			statement = c.prepareStatement(SignUp_Update);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.executeUpdate();
			LoginFrame.dispose();
			login();
		}
	}
}