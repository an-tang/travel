package com.travel.servlet;

import com.travel.helper.SessionHelpers;
import com.travel.helper.URLHelpers;
import com.travel.service.OrderService;
import com.travel.viewmodel.OrderDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/orders"})
public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession currentSession = request.getSession(false);
            boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
            if (isAuthenticated) {
                String username = (String) currentSession.getAttribute("authenticatedUser");
                OrderService orderService = new OrderService();
                ArrayList<OrderDetail> orders = orderService.GetOrderHistoryByUserName(username);

                request.setAttribute("orders", orders);
                request.getRequestDispatcher("orders.jsp").forward(request, response);
            } else {
                response.sendRedirect(URLHelpers.buildRelativeURL("/login", "redirect", "orders"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
