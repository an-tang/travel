package com.travel.servlet.admin;

import com.travel.bean.ImageBean;
import com.travel.bean.ProvinceBean;
import com.travel.dao.ImageDAO;
import com.travel.dao.ProvinceDAO;
import com.travel.dao.TourInfoDAO;
import com.travel.service.ProvinceService;
import com.travel.service.TourService;
import com.travel.viewmodel.CreateTourRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/admin/UpdateTour")
public class AdminUpdateTourServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String[] image_URLs = request.getParameterValues("image_URL");
        String[] image_DESs = request.getParameterValues("image_description");
        String tour_name = request.getParameter("tour_name");
        String tour_id = request.getParameter("tour_id");
        String tour_title = request.getParameter("tour_title");
        String tour_detail = request.getParameter("tour_detail");
        String tour_price = request.getParameter("tour_price");
        String province_id = request.getParameter("province_id");
        String tour_image = request.getParameter("tour_image");

        ArrayList<ImageBean> images = new ArrayList<>();
        for (int i = 0; i < image_URLs.length; i++) {
            ImageBean imageBean = new ImageBean(image_URLs[i], image_DESs[i]);
            images.add(imageBean);
        }

        try {
            ProvinceDAO provinceDAO = new ProvinceDAO();
            TourService tourService = new TourService();

            ArrayList<ProvinceBean> listProvince = provinceDAO.GetAllProvinces();
            CreateTourRequest createTourRequest = new CreateTourRequest(Integer.parseInt(tour_id),tour_name, tour_image, Integer.parseInt(province_id), tour_title, tour_detail, Long.parseLong(tour_price), images);

            boolean updateTour = tourService.UpdateTour(createTourRequest, Integer.parseInt(tour_id));

            if (updateTour) {
                ProvinceService provinceService = new ProvinceService();
                ProvinceBean province = provinceService.GetProvinceByID(Integer.parseInt(province_id));

                request.setAttribute("tourUpdate", createTourRequest);
                request.setAttribute("listProvince", listProvince);
                request.setAttribute("province", province);
                request.getRequestDispatcher("AdminUpdateTour.jsp").forward(request, response);
            } else response.sendRedirect("/");

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
