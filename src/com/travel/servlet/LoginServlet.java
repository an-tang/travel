package com.travel.servlet;

import com.travel.helper.UserHelpers;
import com.travel.jsonobject.LoginResponse;
import com.travel.service.UserService;
import org.json.simple.JSONObject;

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
        boolean isAuthenticated = UserHelpers.checkSession(request);
        if (!isAuthenticated) {
            String referer = request.getHeader("referer");
            boolean willRefererBeUsed = referer != null
                    && (referer.contains("/search")
                    || referer.contains("/tour")
                    || referer.contains("/wishlist"));
            String refererUrl = willRefererBeUsed ? referer : "";

            request.setAttribute("refererUrl", refererUrl);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            response.sendRedirect("/account");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("pwd");
        JSONObject jsonObject = new JSONObject();

        try {
            UserService userService = new UserService();
            boolean check = userService.Login(username, password);
            LoginResponse loginResponse;

            if (check) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                String redirectUrl = request.getParameter("rurl");
                loginResponse = new LoginResponse(
                        true,
                        "Đăng nhập thành công",
                        !redirectUrl.isEmpty() ? redirectUrl : "/account"
                );
            } else {
                loginResponse = new LoginResponse(
                        false,
                        "Tên đăng nhập hoặc mật khẩu không chính xác",
                        null
                );
            }

            jsonObject.put("success", loginResponse.isSuccess());
            jsonObject.put("message", loginResponse.getMessage());
            jsonObject.put("redirectUrl", loginResponse.getRedirectUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toJSONString());
    }
}
