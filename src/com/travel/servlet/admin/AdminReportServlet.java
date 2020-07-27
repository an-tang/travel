package com.travel.servlet.admin;

import com.travel.bean.AreaBean;
import com.travel.bean.ProvinceBean;
import com.travel.dao.AreaDAO;
import com.travel.dao.ProvinceDAO;
import com.travel.helper.SessionHelpers;
import com.travel.service.OrderService;
import com.travel.service.UserService;
import com.travel.viewmodel.OrderReport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/admin/Report")
public class AdminReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            try {
                OrderService orderService = new OrderService();
                ProvinceDAO provinceDAO = new ProvinceDAO();
                AreaDAO areaDAO = new AreaDAO();

                //-------------get list Provinces-----------------
                ArrayList<ProvinceBean> listProvince = provinceDAO.GetAllProvinces();
                request.setAttribute("listProvince", listProvince);

                //-------------get list Area-----------------
                ArrayList<AreaBean> listArea = areaDAO.GetListArea();
                request.setAttribute("listArea", listArea);

                String area = request.getParameter("area_id");
                int area_id = (area == null || area.equals("")) ? 0 : Integer.parseInt(area);

                if (area_id != 0) {
                    AreaBean areaBean = areaDAO.GetAreaByID(area_id);
                    request.setAttribute("areaBean", areaBean);
                }
                String province = request.getParameter("province_id");
                int province_id = (province == null || province.equals("")) ? 0 : Integer.parseInt(province);
                if (province_id != 0) {
                    ProvinceBean provinceBean = provinceDAO.GetProvinceByID(province_id);
                    request.setAttribute("provinceBean", provinceBean);

                }

                String dateFrom = request.getParameter("dateFrom");
                if (dateFrom != null) {
                    request.setAttribute("dateFrom", dateFrom);
                }
                String dateTo = request.getParameter("dateTo");
                if (dateTo != null) {
                    request.setAttribute("dateTo", dateTo);
                }
                ArrayList<OrderReport> orderReports = orderService.GetReportOrder(area_id, province_id, dateFrom, dateTo);
                request.setAttribute("orderReports", orderReports);


                String username = (String) currentSession.getAttribute("authenticatedUser");
                UserService userService = new UserService();
                if (userService.IsAdmin(username)) {
                    request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
                } else response.sendRedirect("/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else response.sendRedirect("/login");
    }
}
