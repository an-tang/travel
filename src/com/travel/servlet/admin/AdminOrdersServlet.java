package com.travel.servlet.admin;

import com.travel.helper.SessionHelpers;
import com.travel.service.OrderService;
import com.travel.service.UserService;
import com.travel.viewmodel.OrderDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/admin/Orders")
public class AdminOrdersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_order_active = request.getParameter("id_order_active");
        String id_order_deactive = request.getParameter("id_order_deactive");
        try {
            OrderService orderService = new OrderService();
            if (id_order_active != null) {
                orderService.CompletedOrder(Integer.parseInt(id_order_active));
            } else orderService.CancelOrder(Integer.parseInt(id_order_deactive));

            //-------------get list Orders-----------------
            ArrayList<OrderDetail> list = orderService.GetOrdersHaveSorting("id", "asc", 0, 10);
            request.setAttribute("listOrders", list);
            request.getRequestDispatcher("AdminOrders.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/Dashboard");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            try {
                String username = (String) currentSession.getAttribute("authenticatedUser");

                //-------------get list Orders-----------------
                OrderService orderService = new OrderService();
                ArrayList<OrderDetail> list = orderService.GetOrdersHaveSorting("id", "asc", 0, 10);
                request.setAttribute("listOrders", list);

                UserService userService = new UserService();
                if (userService.IsAdmin(username)) {
                    request.getRequestDispatcher("AdminOrders.jsp").forward(request, response);
                } else response.sendRedirect("/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else response.sendRedirect("/login");
    }
}
