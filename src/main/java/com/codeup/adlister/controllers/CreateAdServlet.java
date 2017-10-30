package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String categoryId = request.getParameter("catId");

        String titleIsEmpty;
        String descriptionIsEmpty;
        String catIdIsEmpty;


        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

        // validate input
        boolean inputHasErrors =
                title.isEmpty() || description.isEmpty() || categoryId.isEmpty();

        if (inputHasErrors) {
            if (title.isEmpty()) {
                titleIsEmpty = "Please enter a title";
                request.setAttribute("titleIsEmpty", titleIsEmpty);
            }

            if (description.isEmpty()) {
                descriptionIsEmpty = "Please enter a description";
                request.setAttribute("descriptionIsEmpty", descriptionIsEmpty);
            }
            if (categoryId == null) {
                catIdIsEmpty = "Please select a category";
                request.setAttribute("catIdIsEmpty", catIdIsEmpty);
            }
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
        } else {
            Long catId = Long.parseLong(categoryId);
            Ad ad = new Ad(
                    user.getId(),
                    title,
                    description,
                    catId
            );

            DaoFactory.getAdsDao().insert(ad);
            response.sendRedirect("/ads");
        }



    }
}
