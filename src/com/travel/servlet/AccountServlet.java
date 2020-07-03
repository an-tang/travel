package com.travel.servlet;

import com.travel.helper.SessionHelpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAuthenticated = SessionHelpers.validateSession(request);
        if (isAuthenticated) {
            request.getRequestDispatcher("account.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
