package com.travel.servlet.admin;

import com.travel.helper.URLHelpers;
import com.travel.service.TourService;
import com.travel.viewmodel.TourDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/admin-tour-search")
public class AdminTourSearch extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get request parameters
            String searchTerm = request.getParameter("q");
            int start = request.getParameter("start") != null
                ? Integer.parseInt(request.getParameter("start"))
                : 0;
            int size = request.getParameter("size") != null
                ? Integer.parseInt(request.getParameter("size"))
                : 10;

            // Get tours on current page
            TourService tourService = new TourService();
            ArrayList<TourDetail> tourDetails = tourService.GetTourInAdminPageByKeyword(searchTerm, start, size);

            // Calculations for next page
            String showMoreURL = "";
            int nextStart = start + size;
            ArrayList<TourDetail> nextTourDetails = tourService.GetTourInAdminPageByKeyword(searchTerm, nextStart, size);
            if (nextTourDetails.size() > 0) {
                showMoreURL = URLHelpers.buildRelativeURL(
                    "/admin-tour-search",
                    "q", searchTerm,
                    "start", String.valueOf(nextStart),
                    "size", String.valueOf(size)
                );
            }

            request.setAttribute("listTours", tourDetails);
            request.setAttribute("showMoreURL", showMoreURL);
            request.getRequestDispatcher("components/admin/adminTourList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
