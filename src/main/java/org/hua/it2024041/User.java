// author : Paschalis Koukos
// This class represents a user in the system, containing their ID, username, and password hash.
// It provides a constructor to initialize these fields and getter methods to access them.

package org.hua.it2024041;

public class User {

	private final int id;
	private final String username;
	private final String password_hash;
	
	public User(int id, String username, String password_hash) { // Constructor to initialize the user object with id, username, and password hash
		this.id = id;
		this.username = username;
		this.password_hash = password_hash;
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return password_hash;
	}

}
