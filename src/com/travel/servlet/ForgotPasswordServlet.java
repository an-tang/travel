package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.bean.UserBean;
import com.travel.service.EmailService;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            EmailService emailService = new EmailService();

            UserBean user = userService.GetUserByUserName(username);
            emailService.SendEmail(user);
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
