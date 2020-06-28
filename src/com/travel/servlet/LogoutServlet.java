package com.travel.servlet;

import com.travel.helper.UserHelpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAuthenticated = UserHelpers.checkSession(request);
        if (isAuthenticated) {
            request.getSession(false).invalidate();
        }
        response.sendRedirect("/login");
    }
}
