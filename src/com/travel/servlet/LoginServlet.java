package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.helper.SessionHelpers;
import com.travel.helper.URLHelpers;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAuthenticated = SessionHelpers.validateSession(request);
        if (!isAuthenticated) {
            // Prepare redirect URL for successful login
            String redirectParam = request.getParameter("redirect") != null
                    ? request.getParameter("redirect")
                    : "";
            String redirectUrl;
            switch (redirectParam) {
                case "checkout":
                    redirectUrl = "/checkout";
                    break;
                case "wishlist":
                    redirectUrl = "/wishlist";
                    break;
                case "search":
                    redirectUrl = "";
                    try {
                        redirectUrl = URLHelpers.buildUrlQuery("/search", "q", request.getParameter("q"));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case "tour":
                    redirectUrl = "";
                    try {
                        redirectUrl = URLHelpers.buildUrlQuery("/tour", "id", request.getParameter("id"));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                default:
                    redirectUrl = "";
            }

            request.setAttribute("redirectUrl", redirectUrl);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            response.sendRedirect("/account");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("pwd");
        String redirectUrl = request.getParameter("redirect_url");
        AjaxResponse ajaxResponse;

        try {
            UserService userService = new UserService();
            boolean loginSuccess = userService.Login(username, password);

            if (loginSuccess) {
                // Remove old session if there's any
                SessionHelpers.invalidateCurrentSession(request);

                // Create a new session
                HttpSession newSession = request.getSession(true);
                newSession.setAttribute("authenticatedUser", username);
                newSession.setAttribute("authenticatedName", userService.GetUserByUserName(username).getName());
                newSession.setMaxInactiveInterval(15 * 60); // Session lasts 15 minutes

                ajaxResponse = new AjaxResponse(
                        true,
                        "Đăng nhập thành công",
                        !redirectUrl.equals("") ? redirectUrl : (userService.IsAdmin(username) ? "/admin/Dashboard" : "/account")
                );
            } else {
                ajaxResponse = new AjaxResponse(
                        false,
                        "Tên đăng nhập hoặc mật khẩu không chính xác",
                        null
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResponse = new AjaxResponse(
                    false,
                    "Exception thrown",
                    null
            );
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ajaxResponse.toJSONString());
    }
}
