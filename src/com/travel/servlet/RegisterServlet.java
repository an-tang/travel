package com.travel.servlet;

import com.travel.bean.UserBean;
import com.travel.enumerize.Role;
import com.travel.enumerize.Status;
import com.travel.ajax.AjaxResponse;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get form fields
        String username = request.getParameter("user");
        String password = request.getParameter("pwd");
        String passwordConfirm = request.getParameter("pwdConfirm");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        UserBean newUser = new UserBean(
                !username.equals("") ? username : null,
                !password.equals("") && password.equals(passwordConfirm) ? password : null,
                !name.equals("") ? name : null,
                !email.equals("") ? email : null,
                !phone.equals("") ? phone : null,
                Status.ACTIVE.getValue()
        );
        AjaxResponse ajaxResponse;

        try {
            UserService userService = new UserService();
            String resultMessage = userService.CreateUser(newUser, Role.CUSTOMER.getValue());

            if (resultMessage.equals("success")) {
                ajaxResponse = new AjaxResponse(
                        true,
                        "Đăng ký thành công. Hãy đăng nhập bằng tài khoản mới của bạn.",
                        "/login"
                );
            } else {
                ajaxResponse = new AjaxResponse(
                        false,
                        resultMessage,
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
