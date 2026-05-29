// author : Pachalis Koukos
// A servlet for the deletion of a user account. 
// It checks if the user is logged in, validates the password, and if everything is correct,
// it deletes the user from the database and invalidates the session.
// REFERS TO deleteAccount.jsp

package org.hua.it2024041;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the current session and retrieve user information
        HttpSession session = request.getSession();
        String pass =  request.getParameter("password");
        String confirmPass = request.getParameter("confirm-password");
        Integer userId = (Integer) session.getAttribute("userID");
        String username = session.getAttribute("username").toString();


        if (userId==null || username == null) { // check that the user is logged in
            response.sendRedirect("index.html"); // if not, redirect to login page
            return; // stop operation of the servlet
        }

        if(!pass.equals(confirmPass)) { // check equality of passwords given
            response.sendRedirect("deleteAccount.jsp?error=mismatch"); // if not equal, redirect back to delete page with error message
            return; // stop operation of the servlet
        }

        // check that the password is correct by hashing it and comparing with the stored hash in the database
        // check that the user exists in the database with the given username and password hash
        if(!(Hashing.isUserValid(session.getAttribute("username").toString(),Util.getHash256(pass)))){
            response.sendRedirect("deleteAccount.jsp?error=invalid"); // wrong passwords
            return; // stop operation of the servlet
        }

        
        boolean deleteSuccess = Hashing.deleteUser(userId); // delete user from ur DB

        if (deleteSuccess) {
            //! IMPORTANT: Invalidate the session after deleting the account to log the user out
            //  We're doing this in order to prevent any unauthorized access to the account after deletion, and to ensure that the user is properly logged out.
            session.invalidate();
            response.sendRedirect("index.html"); // send to login page
        } else {
            // error deleting user
            response.sendRedirect("mainPage.jsp?error=delete_failed"); // send back
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Handle GET requests the same way as POST requests
    }
}