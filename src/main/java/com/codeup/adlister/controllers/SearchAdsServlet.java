package com.codeup.adlister.controllers;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchAdsServlet", urlPatterns = "/ads/search")
public class SearchAdsServlet extends HttpServlet {
    private List<Ad> adList = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("search-query") != null) {
            adList = DaoFactory.getAdsDao().searchAll(request.getParameter("search-query"));
            String previousSearchQuery = request.getParameter("search-query");
            request.setAttribute("ads", adList);
            request.getSession().setAttribute("previousSearch", previousSearchQuery);

        }
        request.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(request, response);
    }
}
