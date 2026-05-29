<!--author : Paschalis Koukos-->
<!--HTML file to display the user a success message after changing their password successfully-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // check if the user is logged in, if not redirect to login page
    if (session.getAttribute("username") == null || session.getAttribute("userID")==null) {
        response.sendRedirect("index.html");
        return; // prevent further processing of the page
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Password Changed</title>
    <link rel="stylesheet" type="text/css" href="css/changePassSuccessStyle.css"> <!--link to css file-->
    <link rel="icon" type="image/png" href="imgs/allIcon.png"> <!-- favicon-->
</head>
<body>
    <div id="container-success"> <!--Container in order to display the success message-->
        <h1>Password Changed Successfully!</h1>
        <!--2 Buttons : One for returning to the main page and one for logging out-->
        <button onclick="window.location.href='mainPage.jsp'">Return to Our Home Page</button>
        <button onclick="window.location.href='LogoutServlet'">LogOut</button>
    </div>
</body>
</html>