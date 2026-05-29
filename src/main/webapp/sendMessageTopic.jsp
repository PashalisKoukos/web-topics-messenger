<!--author : Paschalis Koukos-->
<!-- Send Message to Topic Page -->
<!-- This JSP page allows logged-in users to send messages to specific topics. It includes a form for message submission 
and displays a list of available topics. -->

<%@ page import="java.sql.Connection" %>
<%@ page import="org.hua.it2024041.DatabaseConnection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // check if the user is logged in, if not redirect to the login page
    if (session.getAttribute("username") == null || session.getAttribute("userID")==null) {
        response.sendRedirect("index.html");
        return;
    }
%>
<html>
<head>
    <title>Send message</title>
    <link rel="stylesheet" href="css/sendMessageTopicStyle.css"> <!-- Link to the CSS file for styling -->
    <link rel="icon" type="image/png" href="imgs/allIcon.png"> <!-- Link to the favicon -->
</head>
<body>

    <div class="send-container">
        <form action=SendMessageTopicServlet method="POST"> <!-- Form to submit the message to the SendMessageTopicServlet -->
            <!-- Display a welcome message with the username and instructions for sending a message -->
            <p><b><strong>Welcome <%=session.getAttribute("username")%></strong>.</b><br>In this section you can send
                your own message<br>to a topic of your interest and share<br>your opinion among other users!
            </p>
            <label>Select a topic to send your message:</label> <!-- Label for the topic selection dropdown -->
            <label>
                <select name="topic_id"> <!-- Dropdown to select a topic, populated with topics from the database -->
                    <%
                        Connection conn = null;
                        Statement st = null;
                        ResultSet rs = null;
                        try {
                            conn = DatabaseConnection.getConnection(); // Get a connection to the database
                            st = conn.createStatement(); // Create a statement to execute SQL queries
                            String sql = "SELECT * FROM topics ORDER BY id"; // SQL query to retrieve all topics ordered by their ID
                            rs = st.executeQuery(sql); // Execute the query and get the result set
                            while (rs.next()){ // Iterate through the result set and populate the dropdown with topic options
                    %>
                    <option value="<%= rs.getInt("id") %>"><%= rs.getString("name") %></option> <!-- Create an option element for each topic, setting the value to the topic ID and displaying the topic name -->
                    <%
                            }
                        } catch (Exception e) { // Handle any exceptions that occur during database operations
                            e.printStackTrace();
                        } finally {
                            // Close the result set, statement, and connection to free up resources
                            if (rs != null) rs.close();
                            if (st != null) st.close();
                            if (conn != null) conn.close();
                        }
                    %>
                </select>
            </label>
            <br>
            <label>
                <textarea name="message" required></textarea> <!-- Textarea for the user to enter their message, marked as required -->
            </label>
            <button type="submit">Send Message</button> <!-- Button to submit the form and send the message -->
        </form>
    </div>
</body>
</html>
