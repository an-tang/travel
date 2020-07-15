package com.travel.servlet;

import com.travel.helper.SessionHelpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/Dashboard")
public class AdminDashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
        if (isAuthenticated) {
            request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
        } else response.sendRedirect("/login");

    }
}
