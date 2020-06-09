package com.travel.controller;

import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"}, name = "login")
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
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                session.setAttribute("username", username);
            }else{
                request.setAttribute("errorMessage", "Invalid User name or Password. Please try again!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                // .... direct error page
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print("login-name" +request.getParameter("username") + " password" + request.getParameter("password"));

    }
}
