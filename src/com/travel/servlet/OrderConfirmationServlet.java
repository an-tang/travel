package com.travel.servlet;

import com.travel.bean.OrderBean;
import com.travel.helper.TokenHelpers;
import com.travel.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/orderconfirmation"})
public class OrderConfirmationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (TokenHelpers.verifyRequestToken(request)) {
                request.getSession().removeAttribute("orderConfirmationToken");
                int orderId = Integer.parseInt(request.getParameter("order"));

                OrderService orderService = new OrderService();
                OrderBean order = orderService.GetOrderByID(orderId);

                request.setAttribute("order", order);
                request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
            } else {
                // request.getRequestDispatcher("error.jsp").forward(request, response);

                PrintWriter out = response.getWriter();
                out.println("<h1>" + "Sorry, the requested page no longer exists." + "</h1>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
