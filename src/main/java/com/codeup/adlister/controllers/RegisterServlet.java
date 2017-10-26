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
        Boolean fieldIsEmpty = false;
        // validate input
        boolean inputHasErrors = username.isEmpty()
            || email.isEmpty()
            || password.isEmpty()
            || (! password.equals(passwordConfirmation));

        // If input field is empty
        if (username.isEmpty()) {
            fieldIsEmpty = true;
        }
        if (email.isEmpty()) {
            fieldIsEmpty = true;
        }
        if (password.isEmpty()) {
            fieldIsEmpty = true;
        }

//        if (inputHasErrors) {
//            response.sendRedirect("/register");
//            return;
//        }

        // check if username is already in database
        User userIsFound = DaoFactory.getUsersDao().findByUsername(username);
        // if username IS found then send error message to .jsp
        if(userIsFound != null) {
            usernameIsUnique = "Sorry, that username is already taken.";
            System.out.println("Username is not unique");
        } else {
            usernameIsUnique = "You can use this username!";
            System.out.println("Username is unique");
            // create and save a new user
            User user = new User(username, email, password);
            DaoFactory.getUsersDao().insert(user);
            response.sendRedirect("/login");
        }
        request.setAttribute("fieldIsEmpty", fieldIsEmpty);
        request.setAttribute("usernameIsUnique", usernameIsUnique);
        request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
    }
}
