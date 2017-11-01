package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteAdServlet", urlPatterns = "/ads/delete")
public class DeleteAdServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long deleteId = Long.parseLong(request.getParameter("id"));

		System.out.println("Delete Id has been passed: " + deleteId);

		if(deleteId != null) {
			DaoFactory.getAdsDao().delete(deleteId);
		} else {
			System.out.println("deleteId is pointing to a null value! Oh lawdy!");
		}
		response.sendRedirect("/profile/ads");
	}
}
