// author : Paschalis Koukos
// Servlet for changing the password of the user,
// checks if the old password is correct and if so updates the password in the database

package org.hua.it2024041;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ChangePassServlet")
public class ChangePassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response); // call doPost to handle the request
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // take the current session, if it doesn't exist return null
        if (session == null || session.getAttribute("username") == null) { // if there is no session or the session doesn't have a username attribute, redirect to login page
            response.sendRedirect("index.html");  // redirect to login page
            return; // stop further execution of the servlet
        }
        String oldPassHash = Util.getHash256(request.getParameter("oldPassword")); // hash the old password to compare with the hash in the database
        String newPass = request.getParameter("newPassword1"); // get the new password from the request

        if(Hashing.isUserValid(session.getAttribute("username").toString(),oldPassHash)){ // check if the old password is correct by comparing the hash of the old password with the hash in the database
            // if so , update the password in the database with the hash of the new password and redirect to success page
            if (Hashing.updatePassword(session.getAttribute("username").toString(), oldPassHash, Util.getHash256(newPass))) { // update the password in the database, if the old password is correct and the new password is hashed successfully
                response.sendRedirect("changePassSuccess.jsp"); // send to success page
            }
        } else {
            response.sendRedirect("changePassword.jsp?error=wrongPass"); // if the old password is incorrect, redirect back to the change password page with an error message
        }

    }
}
