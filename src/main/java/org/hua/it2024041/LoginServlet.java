// author : Paschalis Koukos
// this servlet is responsible for handling the login process. It receives the username and password from the login form,
// hashes the password using the Util class, and checks if the user exists in the database using the Hashing class.
// If the user is valid, it creates a session and redirects to the main page. If not, it redirects back to the login page with an error message.

package org.hua.it2024041;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response); // Κλήση της doGet για να επεξεργαστεί το αίτημα
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) { // check if username or password is null
            response.sendRedirect("index.html"); // redirect to login page if username or password is null
            return; // stop further processing
        }

        String hashedPassword = Util.getHash256(password); // hash the password using the Util class

        boolean isUserValid = Hashing.isUserValid(username, hashedPassword); // check if the user is valid using the Hashing class

        if (isUserValid) { // successful login
            int userId = Hashing.getUserId(username); // get the user ID from the database using the Hashing class
            //System.out.println(userId);
            // create a session and set the user ID and username as attributes
            request.getSession().setAttribute("userID", userId);
            request.getSession().setAttribute("username", username);
            response.sendRedirect("mainPage.jsp"); // redirect to the main page
        } else {
            response.sendRedirect("index.html?error=login"); // redirect back to the login page with an error message
        }
    }
}