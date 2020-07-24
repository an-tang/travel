package com.travel.servlet.admin;

import com.travel.helper.SessionHelpers;
import com.travel.service.TourService;
import com.travel.service.UserService;
import com.travel.viewmodel.TourDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/admin/Tours")
public class AdminToursServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_tour_active = request.getParameter("id_tour_active");
        String id_tour_deactive = request.getParameter("id_tour_deactive");
        String id_tour_update = request.getParameter("id_tour_update");
        try {
            TourService tourService = new TourService();

            if (id_tour_update == null) {
                if (id_tour_active != null) {
                    tourService.ActiveTour(Integer.parseInt(id_tour_active));
                }
                else tourService.DeactivateTour(Integer.parseInt(id_tour_deactive));
            } else {
                request.getRequestDispatcher("AdminUpdateTour.jsp").forward(request, response);
            }

            //---------------Get List User-----------------
            ArrayList<TourDetail> list = tourService.GetAllTourHaveSorting("name", "asc", 2, 0, 10);
            request.setAttribute("listTours", list);

            request.getRequestDispatcher("AdminTours.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            try {
                String username = (String) currentSession.getAttribute("authenticatedUser");

                //-------------get list Orders-----------------
                TourService tourService = new TourService();
                //todo API getAllTourInfo -> status
                ArrayList<TourDetail> list = tourService.GetAllTourHaveSorting("name", "asc", 2, 0, 10);
                request.setAttribute("listTours", list);

                UserService userService = new UserService();
                if (userService.IsAdmin(username)) {
                    request.getRequestDispatcher("AdminTours.jsp").forward(request, response);
                } else response.sendRedirect("/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else response.sendRedirect("/login");
    }
}
