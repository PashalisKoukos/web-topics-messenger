<!-- author : Paschalis Koukos -->
<!-- Change Password Page -->
<!-- This JSP page allows logged-in users to change their password. It includes form validation and redirects to
the login page if the user is not authenticated. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("username") == null || session.getAttribute("userID")==null) {
        response.sendRedirect("index.html"); // Redirect to login page if user is not logged in
        return; // Stop further processing of the page
    }
%>
<html>
<head>
    <title>Change password</title>
    <link rel="stylesheet" type="text/css" href="css/changePasswordStyle.css"/> <!-- Link to the CSS file for styling -->
    <link rel="icon" type="image/png" href="imgs/allIcon.png"> <!-- Link to the favicon -->
</head>
<body>
    <header>
        <h2>Project of Paschalis Koukos</h2> <!-- Header section with my name for title -->
    </header>

    <div id="change-pass-container">
        <!--isValid() function is for validating the password change form -->
        <form action=ChangePassServlet method="POST" onsubmit="return isValid()"> <!-- Form to submit the password change request to the ChangePassServlet -->
            <!-- Form to submit the password change request to the ChangePassServlet -->
            <p>Changing password for: <strong><%= session.getAttribute("username") %></strong></p> <!-- Display the username of the logged-in user -->
            <br>
            <label for="oldPassword">Your old Password:</label> <!-- Label and input field for the old password -->
            <input type="password" name="oldPassword" id="oldPassword" required/>
            <br>
            <label for="newPassword1">New Password:</label> <!-- Label and input field for the new password -->
            <input type="password" name="newPassword1" id="newPassword1" required/>
            <br>
            <label for="newPassword2">Type your new Password again:</label> <!-- Label and input field for confirming the new password -->
            <input type="password" name="newPassword2" id="newPassword2" required/>
            <br>
            <label for="newPassword3">Type your new Password one more time!</label> <!-- Label and input field for the third confirmation -->
            <input type="password" name="newPassword3" id="newPassword3" required/>
            <br>
            <button type="submit">Submit Change</button> <!-- Submit button to submit the form -->
        </form>
    </div>
    <script type="text/javascript" src="js/changePass.js"></script> <!-- Link to the JavaScript file for form validation -->
</body>
</html>
