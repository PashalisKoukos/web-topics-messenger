// author : Paschalis Koukos 
// this file is from LAB3 inspired
// This class is responsible for all the database operations related to users and topics, such as validating user credentials,
// retrieving user information, deleting users, and updating passwords. It uses JDBC to interact with the database.

package org.hua.it2024041;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class Hashing {

	private static final String GET_USER_ROW_SQL = "SELECT id FROM users WHERE UNAME = ? AND UPASSHASH = ?";
	
	/* Checks if a user with the given username and password hash exists in the database */
	public static synchronized boolean isUserValid(String username, String password_hash) {
		
		try {
			if (username == null || password_hash == null) return false;
			// Open Connection
			Connection conn = DatabaseConnection.getConnection();
			
			// Create PreparedStatement, Set missing values and then Execute it (SELECT SQL query)
			final PreparedStatement pst = conn.prepareStatement(GET_USER_ROW_SQL);
			pst.setString(1, username);
			pst.setString(2, password_hash);
			final ResultSet rs = pst.executeQuery();
			
			// Check if a ROW found (with the given data) or not
			boolean check = rs.next();
			
			// Close ResultSet and PreparedStatement
			rs.close();
			pst.close();
			
			// Close Connection
			conn.close();
			
			return check;
			
		} catch (Throwable t) {
			throw new RuntimeException("isUserValid(..) problem !", t);
		}	
	
	} // END OF isUserValid(..)

	/* Deletes a user from the database */
	public static synchronized boolean deleteUser(int id) {
		String deleteMessagesSql = "DELETE FROM messages WHERE USER_ID = ?";
		String deleteUserSql = "DELETE FROM users WHERE ID = ?";

		try (Connection conn = DatabaseConnection.getConnection()) {
			// deletes all messages of the user first to avoid foreign key constraint issues
			try (PreparedStatement pst1 = conn.prepareStatement(deleteMessagesSql)) {
				pst1.setInt(1, id);
				pst1.executeUpdate();
			}

			// then deletes the user safely
			try (PreparedStatement pst2 = conn.prepareStatement(deleteUserSql)) {
				pst2.setInt(1, id);
				int rowsAffected = pst2.executeUpdate();
				return (rowsAffected == 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error deleting user with ID: " + id, e);
		}
	}

	/* Returns the ID of a user with the given username */
	public static synchronized int getUserId(String username) {
		String sql = "SELECT ID FROM users WHERE UNAME = ?";
		try {
			Connection conn = DatabaseConnection.getConnection(); // Open Connection
			final PreparedStatement pst = conn.prepareStatement(sql); // Create PreparedStatement
			pst.setString(1, username); // Set missing value (username) and then Execute it (SELECT SQL query)

			final ResultSet rs = pst.executeQuery();
			int id = -1; // Default value if user not found
			if (rs.next()) {
				id = rs.getInt("ID");
			}

			rs.close();
			pst.close();
			conn.close();
			return id;
		} catch (Throwable t) {
			throw new RuntimeException("getUserId() problem!", t);
		}
	}

	/* Returns a list of all topics in the database */
	public static synchronized List<Topic> getAllTopics() {
		List<Topic> topicList = new ArrayList<>();
		String sql = "SELECT id, name, description FROM topics";

		try (Connection conn = DatabaseConnection.getConnection(); // Open Connection
		     Statement st = conn.createStatement(); // Create Statement and then Execute SELECT SQL query
		     ResultSet rs = st.executeQuery(sql)) { // Get data and store them to Java Objects

			while (rs.next()) { // Use column names instead of indices for better readability
				topicList.add(new Topic(
						rs.getString("name"), 
						rs.getString("description")
				));
			}
		} catch (Exception e) { // Handle exceptions properly
			e.printStackTrace();
			throw new RuntimeException("Database error in getAllTopics: " + e.getMessage());
		}
		return topicList;
	}
	/* Updates the password hash for a user if the old password hash matches */
	public static boolean updatePassword(String username, String oldPassHash, String newPassHash) {
		String sql = "UPDATE users SET upasshash = ? WHERE id IN (SELECT id FROM users WHERE uname = ? AND upasshash = ?)";

		// Use try-with-resources to ensure proper resource management and handle exceptions
		try (Connection conn = DatabaseConnection.getConnection(); // Open Connection
		     PreparedStatement pstmt = conn.prepareStatement(sql)) { // Create PreparedStatement, Set missing values and then Execute it (UPDATE SQL query)

			// Set parameters for the prepared statement
			pstmt.setString(1, newPassHash);  
			pstmt.setString(2, username);  
			pstmt.setString(3, oldPassHash);  

			int rowsUpdated = pstmt.executeUpdate(); // Check if the update was successful (i.e., one row was updated)

			return rowsUpdated == 1; // Return true if the password was updated successfully, false otherwise

		} catch (Exception e) { // unexpected exceptions are caught and handled properly
			e.printStackTrace();
			return false;
		}
	}

}
