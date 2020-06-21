package com.travel.servlet;

import com.travel.bean.TourInfoBean;
import com.travel.service.TourInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/tour"})
public class TourDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tourId = Integer.parseInt(request.getParameter("id"));
        TourInfoBean tourInfo = null;

        try {
            TourInfoService tourInfoService = new TourInfoService();
            tourInfo = tourInfoService.GetTourInfoByTourID(tourId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("tourInfo", tourInfo);
        request.getRequestDispatcher("tourDetail.jsp").forward(request, response);
    }
}
