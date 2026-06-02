<!--author: Paschalis Koukos-->
<!--JSP file for our main page. This will display the main interface after successful login -->
<!--Here the user can interact with the main menu, the main operations -->

<%@ page import="org.hua.it2024041.Hashing" %>
<%@ page import="org.hua.it2024041.Topic" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("username") == null || session.getAttribute("userID")==null) { // If the user is not logged in, redirect to login page
        response.sendRedirect("index.html"); // send user to login page
        return; // Stop further processing of the JSP
    }
%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="css/mainPageStyle.css"> <!-- Link to the CSS file for styling -->
    <link rel="icon" type="image/png" href="imgs/homeIcon.png"> <!-- Set the favicon for the page -->
</head>
<body>
    <a href="LogoutServlet" class="logout-btn">Logout</a> <!-- Logout button -->
    <!-- Get the username from the session -->
    <h1>Welcome back to our WebApp, <%= session.getAttribute("username") %>!</h1> <!--Welcome message to the proper user-->

    <div class="menu-buttons"> <!-- A div for the menu buttons / the main operations -->
        <!-- Each button has a redirect link to the corresponding JSP page -->
        <button class="my-button" onclick="window.location.href='sendMessageTopic.jsp'">Send message</button> <!-- Button to send a message to a topic -->
        <button class="my-button" onclick="window.location.href='createNewTopic.html'">Create topic</button> <!-- Button to create a new topic -->
        <button class="my-button" id="changePassButton" onclick="window.location.href='changePassword.jsp'">Change Password</button> <!-- Button to change the user's password -->
        <button class="my-button" id="deleteAcc" onclick="window.location.href='deleteAccount.jsp'">Delete your account</button> <!-- Button to delete the user's account -->
    </div>


    <!-- Extra container - div to display available topics -->
    <div class="topics-container">
        <h2>Available Topics</h2>
        <ul style="list-style-type: none; padding: 0;">
            <%
                try {
                    List<Topic> topics = Hashing.getAllTopics(); // get all topics from the database
                    if (topics.isEmpty()) {
            %>
            <li>No topics available yet.</li> <!-- If there are no topics, display a message -->
            <%
            } else {
                for (Topic t : topics) { // For each topic, display its name and description
            %>
            <li style="margin-bottom: 10px; border-bottom: 1px solid #100b0b;">
                <strong><%= t.name %></strong>: <%= t.description %> <!-- Display the topic name and description -->
            </li>
            <%
                    }
                }
            } catch (Exception e) {
            %>
            <li>Error loading topics: <%= e.getMessage() %></li> <!-- If there is an error loading the topics, display an error message -->
            <%
                }
            %>
        </ul>
    </div>
</body>
</html>