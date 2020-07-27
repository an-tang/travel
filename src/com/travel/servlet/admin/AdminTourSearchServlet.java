package com.travel.servlet.admin;

import com.travel.enumerize.PagingSize;
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
public class AdminTourSearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get request parameters
            String searchTerm = request.getParameter("q");

            // Get tours
            TourService tourService = new TourService();
            ArrayList<TourDetail> tourDetails = tourService.GetTourInAdminPageByKeyword(searchTerm, 0, PagingSize.ADMIN_TOURS.getValue());

            // Render
            request.setAttribute("listTours", tourDetails);
            request.getRequestDispatcher("components/admin/adminTourList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
