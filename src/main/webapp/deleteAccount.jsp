<!--author: Paschalis Koukos-->
<!-- This JSP page allows users to delete their account. It checks if the user is logged in, and if not, it redirects
them to the login page. The page includes a form where users can enter and confirm their password before submitting 
the delete request. The form submits to a servlet called DeleteServlet, which will handle the account deletion logic.
The page also includes links to a CSS file for styling and a JavaScript file for client-side validation. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("username") == null) { // Check if the user is logged in
        response.sendRedirect("index.html"); // send user to login page
        return; // Stop further processing of the JSP

    }
%>
<html>
<head>
    <title>Delete your account</title>
    <link rel="icon" type="image/png" href="imgs/lock.webp"> <!-- Set the favicon -->
    <link rel="stylesheet" type="text/css" href="css/deleteAccountStyle.css"> <!-- Link to the CSS file -->
</head>
<body>
    <!--DIV container for the delete account form-->
    <div class="delete-container">
        <form action=DeleteServlet method="POST" autocomplete="off">
            <p>Here you are able to delete your account</p>
            <label for="password">Enter your password:</label>
            <input type="password" id="password" name="password" placeholder="Your password" required> <!-- Input field for the user to enter their password -->
            <label for="confirm-password">Confirm your password:</label>
            <input type="password" id="confirm-password" name="confirm-password" placeholder="Confirm your password" required> <!-- Input field for the user to confirm their password -->
            <button type="submit">Delete</button> <!--button for submitting the delete request-->
        </form>
    </div>
    <script type="text/javascript" src="js/deleteAccount.js"></script> <!-- Link to the JavaScript file for client-side validation -->
</body>
</html>
