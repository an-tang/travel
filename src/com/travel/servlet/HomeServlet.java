package com.travel.servlet;

import com.travel.bean.HomeProvinceBean;
import com.travel.bean.TourBean;
import com.travel.enumerize.Region;
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
        try {
            // Get the service
            TourService tourService = new TourService();
            HomeProvinceService homeProvinceService = new HomeProvinceService();

            // Get regional tours
            ArrayList<TourBean> northernTours = tourService.GetToursByAreaID(Region.NORTH.getValue(), 3);
            ArrayList<TourBean> centralTours = tourService.GetToursByAreaID(Region.CENTRAL.getValue(), 3);
            ArrayList<TourBean> southernTours = tourService.GetToursByAreaID(Region.SOUTH.getValue(), 3);

            // Get popular tours
            ArrayList<TourBean> popularTours = tourService.GetToursTopOrder(8);

            // Get top provinces
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
