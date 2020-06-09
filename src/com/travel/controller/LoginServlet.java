package com.travel.controller;

import com.travel.model.UserModel;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserService userService = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        try {
            userService = new UserService();
            boolean check = userService.Login(username, password);
            if (check){
                // ....save session
                session.setAttribute("username", username);
            }else{
                // .... direct error page
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
