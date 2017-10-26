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

@WebServlet(name = "PasswordChangeServlet", urlPatterns = "/profile/password_change")
public class PasswordChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String newPassword = request.getParameter("new_password");
        String confirmNewPassword = request.getParameter("confirm_new_password");
        User user = (User) request.getSession().getAttribute("user");
        String incompleteForm;
        String passChange;
        String invalidInput;

        boolean inputIncomplete = password.isEmpty()
                || newPassword.isEmpty()
                || confirmNewPassword.isEmpty();

        //checks if form is empty
        if (inputIncomplete){
            incompleteForm = "Please fill out entire form";
            System.out.println("Form is not complete");
            request.setAttribute("incompleteForm", incompleteForm);
            response.sendRedirect("/profile/password_change");

            return;
        }

        //checking if old pass is correct and new pass matches
        boolean validAttempt = Password.check(password, user.getPassword()) && (newPassword.equals(confirmNewPassword) && !password.equals(newPassword));

        //updating database
        if (validAttempt){
//            user.setPassword(newPassword);
            DaoFactory.getUsersDao().changePassword(user, newPassword);
            passChange = "Password was changed successfully";
            request.getSession().setAttribute("user", DaoFactory.getUsersDao().findByUsername(user.getUsername()));
            request.setAttribute("passChange", passChange);
            response.sendRedirect("/profile");

        } else {
            invalidInput = "Invalid input";
            request.setAttribute("invalidInput", invalidInput);
            response.sendRedirect("/profile/password_change");


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        User currentUser = DaoFactory.getUsersDao().findByUsername(user.getUsername());
        request.setAttribute("user", currentUser);
        request.getRequestDispatcher("/WEB-INF/profile/password-change.jsp").forward(request, response);
    }
}
