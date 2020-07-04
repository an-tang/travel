package com.travel.servlet;

import com.travel.helper.CookieHelpers;
import com.travel.helper.SessionHelpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAuthenticated = SessionHelpers.validateSession(request);
        if (isAuthenticated) {
            Cookie cookie = CookieHelpers.getExistingCookie(request, "checkoutTourId");
            if (cookie != null) {
                request.setAttribute("checkoutTourId", cookie.getValue());
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}
