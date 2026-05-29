// author : Paschalis Koukos
// This servlet checks if the user is logged in by checking the session attributes "userID" and "username".
//  If the user is not logged in, it redirects them to the login page (index.html). If the user is logged in, it forwards them to the main page (mainPage.jsp).

package org.hua.it2024041;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/MainPageServlet")
public class MainPageServlet  extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response); // Call doGet to handle POST requests the same way as GET requests
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Get the current session
        if(session.getAttribute("userID") == null || session.getAttribute("username") == null) { // Check if the user is logged in by checking the session
            response.sendRedirect("index.html"); // If not logged in, redirect to the login page
        } else {
            request.getRequestDispatcher("mainPage.jsp").forward(request, response); // If logged in, forward to the main page
        }
    }
    
}
