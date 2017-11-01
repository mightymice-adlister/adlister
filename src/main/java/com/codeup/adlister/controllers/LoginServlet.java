package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);
        String usernameIsEmpty;
        String passwordIsEmpty;

        // validate input
        boolean inputHasErrors = username.isEmpty()
                || password.isEmpty();
        if (inputHasErrors) {
            if (username.isEmpty()) {
                usernameIsEmpty = "Please enter a valid username";
                request.setAttribute("usernameIsEmpty", usernameIsEmpty);
            }

            if (password.isEmpty()) {
                passwordIsEmpty = "Please enter a valid password";
                request.setAttribute("passwordIsEmpty", passwordIsEmpty);
            }
            request.setAttribute("stickyUsername", username);

            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            return;
        }
        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

        boolean validAttempt = Password.check(password, user.getPassword());

        if (validAttempt) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/profile");
        } else {
            request.setAttribute("stickyUsername", username);
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }
}
