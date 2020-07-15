package com.travel.servlet.admin;

import com.travel.helper.SessionHelpers;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/Users")
public class AdminUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        try {

            UserService userService = new UserService();
            String username = (String) currentSession.getAttribute("authenticatedUser");
            if (isAuthenticated) {
                if (userService.IsAdmin(username)){
                    request.getRequestDispatcher("AdminUsers.jsp").forward(request, response);
                } else response.sendRedirect("/");
            } else response.sendRedirect("/login");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
