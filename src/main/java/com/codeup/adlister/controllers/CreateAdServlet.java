package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.AdIdAndCatId;
import com.codeup.adlister.models.User;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("categories", DaoFactory.getCategoriesDao().All());
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");

        String title = request.getParameter("title");
        String description = request.getParameter("description");



        String titleIsEmpty;
        String descriptionIsEmpty;
        String catIdIsEmpty;



        if (user == null) {
            response.sendRedirect("/login");
            return;
        }

        // validate input
        boolean inputHasErrors =
                title.isEmpty() || description.isEmpty() || request.getParameterValues("catIds") == null;

        if (inputHasErrors) {
            if (title.isEmpty()) {
                titleIsEmpty = "Please enter a title";
                request.setAttribute("titleIsEmpty", titleIsEmpty);
            }
            request.setAttribute("titleEntered", title);

            if (description.isEmpty()) {
                descriptionIsEmpty = "Please enter a description";
                request.setAttribute("descriptionIsEmpty", descriptionIsEmpty);
            }
            if (request.getParameterValues("catIds") == null) {
                catIdIsEmpty = "Please select a category";
                request.setAttribute("catIdIsEmpty", catIdIsEmpty);
            }
            String [] categoryId = request.getParameterValues("catIds");
            List<Long> catIdsEntered = new ArrayList<>();
            for(String catId: categoryId){
                catIdsEntered.add(Long.valueOf(catId));
            }
            request.setAttribute("catIdsEntered", catIdsEntered);
            request.setAttribute("descriptionEntered", description);
            request.setAttribute("categories", DaoFactory.getCategoriesDao().All());
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
        } else {
            String [] categoryId = request.getParameterValues("catIds");
            List<Long> catIds = new ArrayList<>();
            for(String catId: categoryId){
                catIds.add(Long.parseLong(catId));
            }
            Ad ad = new Ad(
                    user.getId(),
                    title,
                    description,
                    catIds
            );

            Long adId = DaoFactory.getAdsDao().insert(ad);
            DaoFactory.getAdAndCatsDao().insert(new AdIdAndCatId(adId, ad.getCatId()));
            response.sendRedirect("/ads");
        }

    }
}
