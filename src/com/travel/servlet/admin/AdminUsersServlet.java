package com.travel.servlet.admin;

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
import java.util.ArrayList;

@WebServlet(urlPatterns = "/admin/Users")
public class AdminUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            try {
                String username = (String) currentSession.getAttribute("authenticatedUser");
                UserService userService = new UserService();
//              //---------------Get List User-----------------
                ArrayList<UserBean> list = userService.GetAllUsers(0, 20);
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
