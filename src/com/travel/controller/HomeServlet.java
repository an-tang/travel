package com.travel.controller;

import com.travel.dbconnection.DBConnection;
import com.travel.model.UserModel;
import com.travel.repository.UserRepository;
import com.travel.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

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

        UserModel user = new UserModel("An", "123456", "Hoang An", "123456", "0977765121", 1);
        try {
            service = new UserService();
            service.CreateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher view = request.getRequestDispatcher("home.jsp");
        view.forward(request, response);
    }
}
