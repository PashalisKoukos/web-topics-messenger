// author: Paschalis Koukos 
// Servlet that handles the sending of messages to a topic. It retrieves the user ID from the session,
// gets the topic ID and message text from the request parameters, and inserts the message into the database.
// After inserting, it counts the total number of messages for that topic and displays a confirmation message along with the count.
// If the user is not logged in, it redirects them to the index page.

package org.hua.it2024041;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SendMessageTopicServlet")
public class SendMessageTopicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user ID and username from the session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userID");
        String userName = (String) session.getAttribute("username");

        if (userId == null || userName == null) { // If user is not logged in, redirect to index page
            response.sendRedirect("index.html");
            return;
        }
        // get parameters from the request
        String topicId = request.getParameter("topic_id");
        String messageText = request.getParameter("message");

        // sql queries to insert the message and count the total messages for the topic
        String insertSql = "INSERT INTO messages (ID, USER_ID,TOPIC_ID,MSG, DATE_SENT) VALUES (null, ?, ?, ?, null)";
        String countSql = "SELECT count(*) FROM messages WHERE topic_id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection(); // Get a connection to the database MariaDB
            {   // block to insert the message into the database
                PreparedStatement preparedStatement = conn.prepareStatement(insertSql); // Prepare the SQL statement for inserting the message
                preparedStatement.setInt(1, userId); // Set the user ID parameter
                preparedStatement.setInt(2, Integer.parseInt(topicId)); // Set the topic ID parameter
                preparedStatement.setString(3, messageText); // Set the message text parameter
                preparedStatement.executeUpdate(); // Execute the insert statement to add the message to the database
            }
            {   // block to count the total messages for the topic and display the confirmation message 
                PreparedStatement preparedStatement = conn.prepareStatement(countSql); // Prepare the SQL statement for counting the messages for the topic
                preparedStatement.setInt(1, Integer.parseInt(topicId)); // Set the topic ID parameter for counting the messages
                ResultSet rs = preparedStatement.executeQuery(); // Execute the query to get the count of messages for the topic
                if (rs.next()) { // If there is a result, retrieve the count and display the confirmation message along with the count of messages for the topic
                    int count = rs.getInt(1); // Get the count of messages for the topic from the result set, num 1 because it's the first column in the result set
                    // dynamic html response to show the confirmation message and the count of messages for the topic, using the same CSS class for styling
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().println("<html><head><title>Message sent!</title><link rel='icon' type='image/png' href='imgs/allIcon.png'><link rel='stylesheet' href='css/sendMessageTopicStyle.css'></head>");
                    response.getWriter().println("<body><div class='send-container'>"); // same CSS class as the sendMessageTopic.jsp for consistent styling
                    response.getWriter().println("<h2>Message sent successfully!</h2>");
                    response.getWriter().println("<p>Total messages for this topic: " + count + "</p>");
                    response.getWriter().println("<a href='sendMessageTopic.jsp' class='back-link'>Go back</a>");
                    response.getWriter().println("</div></body></html>");
                }

            }
        } catch (Exception e) { // Handle any exceptions that occur during database operations and throw a ServletException with a message indicating that the database operation failed
            throw new ServletException("Database operation failed", e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Handle GET requests by calling the doPost method, allowing the servlet to process both GET and POST requests in the same way
    }
}
