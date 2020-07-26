package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.bean.UserBean;
import com.travel.helper.SessionHelpers;
import com.travel.helper.TokenHelpers;
import com.travel.service.EmailService;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/forgot-password"})
public class ForgotPasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResponse ajaxResponse;
        try {
            String username = request.getParameter("user");
            UserService userService = new UserService();
            UserBean user = userService.GetUserByUserName(username);
            if (user != null && user.getEmail() != null && !user.getEmail().equals("")) {
                String token = TokenHelpers.generateToken("rpw_");
                SessionHelpers.invalidateCurrentSession(request);
                HttpSession newSession = request.getSession(true);
                newSession.setAttribute("resetPasswordToken", token);
                newSession.setAttribute("resetPasswordUser", user.getUserName());
                newSession.setMaxInactiveInterval(10 * 60); // Session lasts 10 minutes

                EmailService emailService = new EmailService();
                emailService.SendEmail(user, token);
            }

            // For security reasons, valid and invalid username cases produce the same result
            ajaxResponse = new AjaxResponse(
                    true,
                    "Đã gửi email đặt lại mật khẩu đến địa chỉ email được đăng ký với tài khoản. Vui lòng kiểm tra hộp thư đến trong email của bạn.",
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResponse = new AjaxResponse(
                    false,
                    "Exception thrown on our side",
                    null
            );
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ajaxResponse.toJSONString());
    }
}
