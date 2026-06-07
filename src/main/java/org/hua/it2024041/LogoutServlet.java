// author : Paschalis Koukos
// This servlet handles user logout by invalidating the session and redirecting to the index page-login page.

package org.hua.it2024041;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the existing session, do not create a new one
        if (session != null) {
            session.invalidate(); //! Invalidate the session to log out the user
        }
        response.sendRedirect("index.html"); // Redirect to the index page (login page)
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response); // Handle POST requests the same way as GET requests
    }
}