package com.travel.servlet.admin;

import com.travel.bean.UserBean;
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

@WebServlet(urlPatterns = "/admin/Users")
public class AdminUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id_user_active = request.getParameter("id_user_active");
        String id_user_deactive = request.getParameter("id_user_deactive");
        String id_user_history = request.getParameter("id_user_history");

        try {
            if (id_user_history == null) {
                UserService userService = new UserService();
                if (id_user_active != null) {
                    userService.ActivateUser(Integer.parseInt(id_user_active));
                } else userService.DeactivateUser(Integer.parseInt(id_user_deactive));

                //---------------Get List User-----------------
                ArrayList<UserBean> list = userService.GetAllUsersHaveSorting("name", "asc", 2, 0, 20);
                request.setAttribute("listUsers", list);
                request.getRequestDispatcher("AdminUsers.jsp").forward(request, response);
            } else {

                OrderService orderService = new OrderService();
                ArrayList<OrderDetail> list = orderService.GetOrderHistoryByUserName(id_user_history);
                request.setAttribute("listOrders", list);
                request.setAttribute("userName", id_user_history);

                request.getRequestDispatcher("AdminUserHistory.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            try {
                String username = (String) currentSession.getAttribute("authenticatedUser");
                UserService userService = new UserService();
//              //---------------Get List User-----------------
                ArrayList<UserBean> list = userService.GetAllUsersHaveSorting("name", "asc", 2, 0, 20);
                request.setAttribute("listUsers", list);
                if (userService.IsAdmin(username)) {
                    request.getRequestDispatcher("AdminUsers.jsp").forward(request, response);
                } else response.sendRedirect("/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else response.sendRedirect("/login");
    }
}
