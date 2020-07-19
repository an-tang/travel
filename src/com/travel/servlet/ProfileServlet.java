package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.bean.UserBean;
import com.travel.helper.SessionHelpers;
import com.travel.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession currentSession = request.getSession(false);
            boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
            if (isAuthenticated) {
                String username = (String) currentSession.getAttribute("authenticatedUser");
                UserService userService = new UserService();
                UserBean customer = userService.GetUserByUserName(username);

                request.setAttribute("customer", customer);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else {
                response.sendRedirect("/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
        if (isAuthenticated) {
            AjaxResponse ajaxResponse = new AjaxResponse(false, "Unknown error", null);
            try {
                String formName = request.getParameter("form_name");
                String username = (String) currentSession.getAttribute("authenticatedUser");
                UserService userService = new UserService();
                UserBean customer = userService.GetUserByUserName(username);

                if (formName.equals("update_profile")) {
                    // Handle update_profile form submission
                    String name = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    customer.setName(name);
                    customer.setPhone(phone);
                    customer.setEmail(email);
                    String result = userService.UpdateUserByUserName(customer);

                    if (result.equals("success")) {
                        currentSession.setAttribute("authenticatedName", name); // update name saved in session
                        ajaxResponse = new AjaxResponse(
                                true,
                                "Cập nhật thành công",
                                null
                        );
                    } else {
                        ajaxResponse = new AjaxResponse(
                                false,
                                result,
                                null
                        );
                    }
                } else if (formName.equals("change_password")) {
                    // Handle change_password form submission
                    String currentPassword = request.getParameter("current_pwd");
                    String newPassword = request.getParameter("new_pwd");

                    boolean loginSuccess = userService.Login(username, currentPassword);
                    if (loginSuccess) {
                        customer.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                        String result = userService.UpdateUserByUserName(customer);

                        if (result.equals("success")) {
                            ajaxResponse = new AjaxResponse(
                                    true,
                                    "Đổi mật khẩu thành công. Hãy đăng nhập lại bằng mật khẩu mới.",
                                    "/logout"
                            );
                        } else {
                            ajaxResponse = new AjaxResponse(
                                    false,
                                    result,
                                    null
                            );
                        }
                    } else {
                        ajaxResponse = new AjaxResponse(
                                false,
                                "Mật khẩu hiện tại không chính xác",
                                null
                        );
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResponse = new AjaxResponse(false, "Exception thrown on our side", null);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(ajaxResponse.toJSONString());
        } else {
            response.sendRedirect("/login");
        }
    }
}
