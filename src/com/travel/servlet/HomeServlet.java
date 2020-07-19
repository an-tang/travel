package com.travel.servlet;

import com.travel.bean.HomeProvinceBean;
import com.travel.bean.TourBean;
import com.travel.service.HomeProvinceService;
import com.travel.service.TourService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {""})
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String userName = request.getParameter("userName");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int status = 1;
        Role role = Role.ADMIN;
        Status status = Status.ACTIVE;*/

        final int NORTH_VN_ID = 1;
        final int CENTRAL_VN_ID = 2;
        final int SOUTH_VN_ID = 3;

        try {
            TourService tourService = new TourService();
            HomeProvinceService homeProvinceService = new HomeProvinceService();

            // 1 - Get regional tours
            ArrayList<TourBean> northernTours = tourService.GetToursByAreaID(NORTH_VN_ID, 3);
            ArrayList<TourBean> centralTours = tourService.GetToursByAreaID(CENTRAL_VN_ID, 3);
            ArrayList<TourBean> southernTours = tourService.GetToursByAreaID(SOUTH_VN_ID, 3);

            // 2 - Get popular tours
            ArrayList<TourBean> popularTours = tourService.GetToursTopOrder(8);

            // 3 - Get top provinces
            ArrayList<HomeProvinceBean> homepageProvinces = homeProvinceService.GetHomePageProvinces(5);

            request.setAttribute("northernTours", northernTours);
            request.setAttribute("centralTours", centralTours);
            request.setAttribute("southernTours", southernTours);
            request.setAttribute("popularTours", popularTours);
            request.setAttribute("homepageProvinces", homepageProvinces);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
