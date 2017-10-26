package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");
        String usernameIsUnique;
        String usernameIsEmpty;
        String emailIsEmpty;
        String passwordIsEmpty;
        String passwordConfirmationIsEmpty;

        System.out.println("Here's the uername: " + username);
        // validate input
        boolean inputHasErrors = username.isEmpty()
            || email.isEmpty()
            || password.isEmpty()
            || (! password.equals(passwordConfirmation));
        if (inputHasErrors) {
            if (username.isEmpty()) {
                usernameIsEmpty = "Please enter a valid username";
                request.setAttribute("usernameIsEmpty", usernameIsEmpty);
            }
            if (email.isEmpty()) {
                emailIsEmpty = "Please enter a valid email";
                request.setAttribute("emailIsEmpty", emailIsEmpty);
            }
            if (password.isEmpty()) {
                passwordIsEmpty = "Please enter a valid password";
                request.setAttribute("passwordIsEmpty", passwordIsEmpty);
            }
            if (passwordConfirmation.isEmpty()) {
                passwordConfirmationIsEmpty = "Please confirm your password";
                request.setAttribute("passwordConfirmationIsEmpty", passwordConfirmationIsEmpty);
            }
            request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
            return;
        }

        // check if username is already in database
        User userIsFound = DaoFactory.getUsersDao().findByUsername(username);
        // if username IS found then send error message to .jsp
        if(userIsFound != null) {
            usernameIsUnique = "Sorry, that username is already taken.";
            request.setAttribute("usernameIsUnique", usernameIsUnique);
            System.out.println("Username is not unique");
        } else {
            usernameIsUnique = "You can use this username!";
            System.out.println("Username is unique");
            request.setAttribute("usernameIsUnique", usernameIsUnique);
            // create and save a new user
            User user = new User(username, email, password);
            DaoFactory.getUsersDao().insert(user);
            response.sendRedirect("/login");
            return;
        }




        request.setAttribute("username", username);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("usernameIsUnique", usernameIsUnique);
//        request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
    }
}
