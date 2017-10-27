package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProfileServlet", urlPatterns = "/profile/edit")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        boolean emailIsEmpty = email.isEmpty();
        User user = (User) request.getSession().getAttribute("user");

        if (emailIsEmpty || email.equals(user.getEmail())) {
            response.sendRedirect("/profile/edit");
        } else {
            DaoFactory.getUsersDao().updateEmail(user, email);
            request.getSession().setAttribute("user", DaoFactory.getUsersDao().findByUsername(user.getUsername()));
            response.sendRedirect("/profile");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        User currentUser = DaoFactory.getUsersDao().findByUsername(user.getUsername());
        request.setAttribute("user", currentUser);
        request.getRequestDispatcher("/WEB-INF/profile/edit.jsp").forward(request, response);
    }
}
