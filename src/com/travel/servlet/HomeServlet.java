package com.travel.servlet;

import com.travel.enumerize.Role;
import com.travel.enumerize.UserStatus;
import com.travel.bean.UserBean;
import com.travel.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    UserService service = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userName = request.getParameter("userName");
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String phone = request.getParameter("phone");
//        int status = 1;

        //if else role of user
        Role role = Role.ADMIN;
        UserStatus status = UserStatus.ACTIVE;
        UserBean user = new UserBean("admin", "123456", "Hoang An", "antang@gmail.com", "0977765121", status.getValue());
        try {
            service = new UserService();
            boolean check = service.Login("admin", "123456");
            System.out.println(check);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher view = request.getRequestDispatcher("home.jsp");
        view.forward(request, response);
    }
}
