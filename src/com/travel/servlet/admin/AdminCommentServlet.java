package com.travel.servlet.admin;

import com.travel.bean.CommentBean;
import com.travel.helper.SessionHelpers;
import com.travel.service.CommentService;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/admin/Comment")
public class AdminCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_comment_active = request.getParameter("id_comment_active");
        String id_comment_deactive = request.getParameter("id_comment_deactive");
        try {
            CommentService commentService = new CommentService();
            if (id_comment_active != null) {
                commentService.ActiveComment(Integer.parseInt(id_comment_active));
            } else commentService.DeactivateComment(Integer.parseInt(id_comment_deactive));

            //-------------get list Comments-----------------
            ArrayList<CommentBean> list = commentService.GetComments(0, 10);
            request.setAttribute("listComments", list);
            request.getRequestDispatcher("AdminComment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            try {
                String username = (String) currentSession.getAttribute("authenticatedUser");

                //-------------get list Comments-----------------
                CommentService commentService = new CommentService();
                ArrayList<CommentBean> list = commentService.GetComments(0, 10);
                request.setAttribute("listComments", list);

                UserService userService = new UserService();
                if (userService.IsAdmin(username)) {
                    request.getRequestDispatcher("AdminComment.jsp").forward(request, response);
                } else response.sendRedirect("/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else response.sendRedirect("/login");
    }
}
