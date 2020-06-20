package com.travel.servlet;

import com.travel.bean.HomeTourBean;
import com.travel.bean.TourBean;
import com.travel.service.TourService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        String userName = request.getParameter("userName");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int status = 1;
        Role role = Role.ADMIN;
        Status status = Status.ACTIVE;
        */

        /* Just sample data */
        HomeTourBean myTour = new HomeTourBean(
                123,
                "Da Lat - Vung Tau",
                "https://www.hoabinhtravel.net/images/product/9909nha-trang-thap-tram-huong.jpg"
        );
        request.setAttribute("myTour", myTour);
        /* End of sample data */

        ArrayList<TourBean> northernTours = null;
        ArrayList<TourBean> centralTours = null;
        ArrayList<TourBean> southernTours = null;
        ArrayList<TourBean> popularTours = null;

        try {
            TourService tourService = new TourService();

            // 1 - Get regional tours
            northernTours = tourService.GetToursByAreaID(1, 3);
            centralTours = tourService.GetToursByAreaID(2, 3);
            southernTours = tourService.GetToursByAreaID(3, 3);

            // 2 - Get popular tours
            popularTours = tourService.GetToursTopOrder(6);

            // 3 - Get top provinces

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("northernTours", northernTours);
        request.setAttribute("centralTours", centralTours);
        request.setAttribute("southernTours", southernTours);
        request.setAttribute("popularTours", popularTours);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
