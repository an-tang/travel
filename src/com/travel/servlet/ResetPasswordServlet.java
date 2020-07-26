package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.bean.UserBean;
import com.travel.helper.TokenHelpers;
import com.travel.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/reset-password"})
public class ResetPasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession resetPasswordSession = request.getSession(false);
        if (
            resetPasswordSession != null
            && resetPasswordSession.getAttribute("resetPasswordUser") != null
            && TokenHelpers.verifyRequestToken(request, "resetPasswordToken")
        ) {
            request.setAttribute("resetPasswordToken", resetPasswordSession.getAttribute("resetPasswordToken"));
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResponse ajaxResponse;
        try {
            String resetPasswordToken = request.getParameter("token");
            HttpSession resetPasswordSession = request.getSession(false);

            if (
                resetPasswordSession != null
                && resetPasswordSession.getAttribute("resetPasswordUser") != null
                && resetPasswordSession.getAttribute("resetPasswordToken") != null
                && resetPasswordSession.getAttribute("resetPasswordToken").equals(resetPasswordToken)
            ) {
                String resetPasswordUser = (String) resetPasswordSession.getAttribute("resetPasswordUser");
                String newPassword = request.getParameter("new_pwd");

                UserService userService = new UserService();
                UserBean customer = userService.GetUserByUserName(resetPasswordUser);
                customer.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                String result = userService.UpdateUserByUserName(customer);

                if (result.equals("success")) {
                    ajaxResponse = new AjaxResponse(
                            true,
                            "Đặt lại khẩu thành công. Hãy đăng nhập lại bằng mật khẩu mới.",
                            "/logout"
                    );
                } else {
                    ajaxResponse = new AjaxResponse(
                            false,
                            result,
                            null
                    );
                }

                resetPasswordSession.removeAttribute("resetPasswordUser");
                resetPasswordSession.removeAttribute("resetPasswordToken");
                resetPasswordSession.invalidate();
            } else {
                ajaxResponse = new AjaxResponse(
                        false,
                        "Xảy ra lỗi xác thực",
                        null
                );
            }
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
