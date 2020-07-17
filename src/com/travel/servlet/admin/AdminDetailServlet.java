package com.travel.servlet.admin;

import com.travel.bean.UserBean;
import com.travel.enumerize.Status;
import com.travel.helper.SessionHelpers;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/Detail")
public class AdminDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            UserBean admin = null;
            String username = (String) currentSession.getAttribute("authenticatedUser");

            try {
                UserService userService = new UserService();
                admin = userService.GetUserByUserName(username);

                if (userService.IsAdmin(username)) {
                    request.setAttribute("admin", admin);
                    request.getRequestDispatcher("AdminDetail.jsp").forward(request, response);
                } else response.sendRedirect("/");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else response.sendRedirect("/login");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        String adminPhone = request.getParameter("admin_phone");
        String adminName = request.getParameter("admin_name");
        String adminEmail = request.getParameter("admin_email");
        String username = (String) currentSession.getAttribute("authenticatedUser");

        try {
            UserService userService = new UserService();
            String adminPassword = userService.GetUserByUserName(username).getPassword();
            UserBean newUser = new UserBean(
                    !username.equals("") ? username : null,
                    adminPassword,
                    !adminName.equals("") ? adminName : null,
                    !adminEmail.equals("") ? adminEmail : null,
                    !adminPhone.equals("") ? adminPhone : null,
                    Status.ACTIVE.getValue()
            );
            int resultMessage = userService.UpdateUserByUserNameWithoutPassword(newUser);

            if (resultMessage == 1) {
                request.setAttribute("admin", newUser);
                request.getRequestDispatcher("AdminDetail.jsp").forward(request, response);
            } else response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
