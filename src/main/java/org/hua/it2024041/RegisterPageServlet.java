// author : Pachalis Koukos
// Refers to the registerPage.html and handles the registration logic,
// such as checking if the username is already taken and inserting a new user into the database if the registration is successful.

package org.hua.it2024041;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/RegisterPageServlet")
public class RegisterPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // get parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");

        if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            response.sendRedirect("index.html"); // empty fields, redirect to login page
            return; // stop further processing
        }

        if(isUsernameTaken(username)) { // check if the username is already taken
            response.sendRedirect("registerPage.html?error=user_exists");
            return; // stop further processing
        }

        String hashedPassword = Util.getHash256(password); // hash the password using SHA-256
        boolean success = insertUser(username, hashedPassword); // calls the function 'insertUser' to insert the new user into the database

        if(success) {
            response.sendRedirect("index.html?signup=ok"); // successful registration, redirect to login page 
        } else {
            response.sendRedirect("registerPage.html?error=db_error"); // unexpected error, redirect back to registration page
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // handle GET requests the same way as POST requests
    }

    /*
        This method checks if a given username is already taken in the database.
    */
    public static synchronized boolean isUsernameTaken(String username) {
        String sql = "SELECT id FROM users WHERE uname = ?"; // SQL query to check if the username exists in the 'users' table

        try (Connection conn = DatabaseConnection.getConnection(); // get connection with MariaDB
             PreparedStatement pst = conn.prepareStatement(sql)) { // prepared statement to prevent SQL injection

            pst.setString(1, username); // set the username in the query

            try (ResultSet rs = pst.executeQuery()) { // execute the query and get the result set
                return rs.next(); // if rs.next() returns true, it means a record was found, so the username is taken. otherwise, it's available
            }
        } catch (Exception e) { // unexpected error, print stack trace and return false to prevent registration with that username
            e.printStackTrace();
            return false;
        }
    }

    /*
        This method inserts a new user into the database.
    */
    public static synchronized boolean insertUser(String username, String password_hash) {
        String sql = "INSERT INTO users (uname, upasshash) VALUES (?, ?)"; // SQL query to insert a new user into the 'users' table with the provided username and hashed password

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, password_hash);
            return  pst.executeUpdate() == 1; // execute the update and check if one record was inserted successfully

        } catch (Exception e) { // unexpected error, print stack trace and return false to indicate failure
            e.printStackTrace();
            return false;
        }
    }
}
