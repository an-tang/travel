package com.travel.servlet;

import com.travel.bean.TourBean;
import com.travel.helper.SessionHelpers;
import com.travel.helper.URLHelpers;
import com.travel.service.TourService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/wishlist"})
public class WishlistServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isAuthenticated = SessionHelpers.validateSession(request);
            if (isAuthenticated) {
                request.getRequestDispatcher("wishlist.jsp").forward(request, response);
            } else {
                response.sendRedirect(URLHelpers.buildRelativeURL("/login", "redirect", "wishlist"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] stringTourIDs = request.getParameterValues("tourIDs[]");
            int[] tourIDs = Arrays.stream(stringTourIDs).mapToInt(Integer::parseInt).toArray();
            TourService tourService = new TourService();
            ArrayList<TourBean> tours = tourService.GetToursByListIDs(tourIDs);

            request.setAttribute("tours", tours);
            request.getRequestDispatcher("components/wishlist/wishlistItems.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
