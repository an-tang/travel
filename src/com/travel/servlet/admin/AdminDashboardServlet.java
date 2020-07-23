package com.travel.servlet.admin;

import com.travel.dao.AreaDAO;
import com.travel.helper.SessionHelpers;
import com.travel.service.UserService;
import com.travel.viewmodel.ChartValue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/Dashboard")
public class AdminDashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
        if (isAuthenticated) {
            try {
//                get List ChartArea
                AreaDAO areaDAO = new AreaDAO();
                List<ChartValue> chartValues = areaDAO.AreaWithOrders();
                request.setAttribute("chartValues", chartValues);

                String username = (String) currentSession.getAttribute("authenticatedUser");
                UserService userService = new UserService();

                if (userService.IsAdmin(username)) {
                    request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
                } else response.sendRedirect("/");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else response.sendRedirect("/login");
    }

}
