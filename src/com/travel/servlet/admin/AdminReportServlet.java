package com.travel.servlet.admin;

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
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            try {
                OrderService orderService = new OrderService();
                String dateFrom = request.getParameter("dateFrom");
                String dateTo = request.getParameter("dateTo");
                ArrayList<OrderReport> orderReports = orderService.GetReportOrder(0, 0, dateFrom, dateTo);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            try {
                OrderService orderService = new OrderService();
                String dateFrom = (String) request.getAttribute("dateFrom");
                String dateTo = (String) request.getAttribute("dateTo");
                ArrayList<OrderReport> orderReports = orderService.GetReportOrder(0, 0, "", "");
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
