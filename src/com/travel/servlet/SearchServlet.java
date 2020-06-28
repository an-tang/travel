package com.travel.servlet;

import com.travel.bean.TourBean;
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
        String searchQuery = request.getParameter("q");
        ArrayList<TourBean> searchResult = null;

        try {
            TourService tourService = new TourService();
            searchResult = tourService.GetToursByName(searchQuery, 0, 8);
//            searchResult = tourService.GetToursTopOrder(8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("searchResult", searchResult);
        request.setAttribute("q", searchQuery);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
