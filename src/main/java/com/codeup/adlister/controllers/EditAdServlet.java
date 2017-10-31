package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditAdServlet", urlPatterns = "/ads/edit")
public class EditAdServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		Long adId = Long.parseLong(request.getParameter("id"));

		Ad ad = DaoFactory.getAdsDao().viewOneAd(adId);
		String description = request.getParameter("description");
		String title = request.getParameter("title");
		Long catId = Long.parseLong(request.getParameter("catId"));
		System.out.println(catId);
		String titleIsEmpty;
		String descriptionIsEmpty;
		String catIdIsEmpty;


		if (user == null) {
			response.sendRedirect("/login");
			return;
		}

		// validate input
		boolean inputHasErrors =
						title.isEmpty() || description.isEmpty() || catId == null;

		if (inputHasErrors) {
			if (description.isEmpty()) {
				descriptionIsEmpty = "Please enter a description";
				request.setAttribute("descriptionIsEmpty", descriptionIsEmpty);
			}
			if (title.isEmpty()) {
				titleIsEmpty = "Please enter a title";
				request.setAttribute("titleIsEmpty", titleIsEmpty);
			}
			request.setAttribute("titleEntered", title);

			if (catId == null) {
				catIdIsEmpty = "Please select a category";
				request.setAttribute("catIdIsEmpty", catIdIsEmpty);
			}
			request.setAttribute("descriptionEntered", description);
			request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);
		} else {
			DaoFactory.getAdsDao().edit(adId, title, description, catId);
			response.sendRedirect("/profile/ads");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/login");
			return;
		}
		Long adId = Long.parseLong(request.getParameter("id"));
		Ad ad = DaoFactory.getAdsDao().viewOneAd(adId);
		request.setAttribute("ad", ad);
		request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);



	}
}
