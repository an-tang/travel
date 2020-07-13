package com.travel.servlet;

import com.travel.bean.TourBean;
import com.travel.helper.URLHelpers;
import com.travel.service.TourService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("q");
        ArrayList<TourBean> searchResult = null;
        String loginRedirectURL = null;

        try {
            TourService tourService = new TourService();
            searchResult = tourService.GetToursByName(searchTerm, "title", "ASC", 0, 8);
            loginRedirectURL = URLHelpers.buildUrlQuery("/login", "redirect", "search", "q", searchTerm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("q", searchTerm);
        request.setAttribute("searchResult", searchResult);
        if (loginRedirectURL != null) {
            request.setAttribute("loginReplacementURL", loginRedirectURL);
        }

        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
