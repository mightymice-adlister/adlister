package com.codeup.adlister.controllers;


import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewUsersAdsServlet", urlPatterns = "/profile/ads")
public class ViewUserAdsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        User user = (User)request.getSession().getAttribute("user");
        request.setAttribute("ads", DaoFactory.getAdsDao().viewAdsByUser(user.getId()));
        request.getRequestDispatcher("/WEB-INF/profile/view-profile-ads.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long deleteId = Long.parseLong(request.getParameter("deleteId"));

        System.out.println("Delete Id has been passed: " + deleteId);

        if(deleteId != null) {
            DaoFactory.getAdsDao().delete(deleteId);
        } else {
            System.out.println("deleteId is pointing to a null value! Oh lawdy!");
        }
        response.sendRedirect("/profile/ads");


    }
}
