package com.travel.servlet;

import com.travel.bean.UserBean;
import com.travel.helper.SessionHelpers;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/orders"})
public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession currentSession = request.getSession(false);
            boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
            if (isAuthenticated) {
                String username = (String) currentSession.getAttribute("authenticatedUser");
                UserService userService = new UserService();
                UserBean customer = userService.GetUserByUserName(username);

                request.setAttribute("customer", customer);
                request.getRequestDispatcher("orders.jsp").forward(request, response);
            } else {
                response.sendRedirect("/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
