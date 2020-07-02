package com.travel.servlet;

import com.travel.helper.SessionHelpers;
import com.travel.ajax.AjaxResponse;
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
        boolean isAuthenticated = SessionHelpers.checkCurrentSession(request);
        if (!isAuthenticated) {
            String referer = request.getHeader("referer");
            boolean willRefererBeUsed = referer != null
                    && (referer.contains("/search")
                    || referer.contains("/tour")
                    || referer.contains("/wishlist"));
            String refererUrl = willRefererBeUsed ? referer : "";

            request.setAttribute("refererUrl", refererUrl);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            response.sendRedirect("/account");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("pwd");
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
                newSession.setMaxInactiveInterval(5 * 60);

                ajaxResponse = new AjaxResponse(
                        true,
                        "Đăng nhập thành công",
                        "/account"
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
