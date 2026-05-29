// author : Paschalis Koukos
// This servlet handles the creation of new topics by receiving POST requests with topic data in JSON format
// It saves the topic to the database and returns a JSON response indicating success or failure of the operation.
// The servlet is mapped to the URL pattern "/CreateNewTopicServlet" and uses the Gson library for JSON processing.
// OUR WEB SERVICE API ENDPOINT : http://localhost:8080/it2024041/CreateNewTopicServlet

package org.hua.it2024041;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CreateNewTopicServlet")
public class CreateNewTopicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Topic topic = ServletUtil.getRequestData(Topic.class, request); // Get Topic data from HTTP Request Body (JSON) and Convert it to Object of Type Topic

        String sql = "INSERT INTO topics (id, name, description) VALUES (null, ?, ?)"; // SQL query to insert a new topic into the database, with id set to null for auto-increment
        Map<String, String> responseMap = saveTopicToDatabase(sql, topic); // Save the topic to the database and get a response message indicating success or failure

        ServletUtil.sendResponseData(responseMap, response); // Send the response message as JSON in the HTTP Response Body
    }

    private static Map<String, String> saveTopicToDatabase(String sql, Topic topic) {
        boolean status = false; // Variable to track the success of the database operation
        // Use try-with-resources to ensure that database resources are properly closed after use
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, topic.getName());// Set the first parameter (name) in the SQL query
            pst.setString(2, topic.getDescription());// Set the second parameter (description) in the SQL query
            status = pst.executeUpdate() > 0;// Execute the SQL query and check if it affected any rows (indicating success)
        } catch (Exception e) {
            throw new RuntimeException(e);// If an exception occurs during the database operation, wrap it in a RuntimeException and rethrow it
        }

        Map<String, String> responseMap = new HashMap<>(); // Create a response map to hold the response message
        if(status) { // If the topic was successfully saved to the database, add a success message to the response map
            responseMap.put("message", "Topic created successfully!");
        } else {
            responseMap.put("message", "Error creating topic.");
        }
        return responseMap; // Return the response map indicating the success or failure of the topic creation operation
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // For simplicity, we can handle GET requests the same way as POST requests, allowing clients to create topics using either method
    }
}