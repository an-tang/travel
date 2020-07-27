package com.travel.servlet.admin;

import com.travel.bean.UserBean;
import com.travel.enumerize.PagingSize;
import com.travel.service.TourService;
import com.travel.service.UserService;
import com.travel.viewmodel.TourDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/admin-user-search")
public class AdminUserSearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get request parameters
            String searchTerm = request.getParameter("q");

            // Get tours
            UserService userService = new UserService();
            ArrayList<UserBean> users = userService.GetUserInAdminPageByKeyword(searchTerm, 0, PagingSize.ADMIN_USERS.getValue());

            // Render
            request.setAttribute("listUsers", users);
            request.getRequestDispatcher("components/admin/adminUserList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
