package com.travel.servlet.admin;

import com.travel.bean.FeedbackBean;
import com.travel.helper.SessionHelpers;
import com.travel.service.FeedbackService;
import com.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/admin/Feedback")
public class AdminFeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_feedback_active = request.getParameter("id_feedback_active");
        String id_feedback_deactive = request.getParameter("id_feedback_deactive");
        /// TODO: 22/07/2020 API Active, Deactive Feedback 
//        try {
//            FeedbackService feedbackService = new FeedbackService();
//            if (id_feedback_active != null) {
//                feedbackService.ActiveComment(Integer.parseInt(id_feedback_active));
//            } else feedbackService.DeactivateComment(Integer.parseInt(id_feedback_deactive));
//
//            //-------------get list Comments-----------------
//            ArrayList<CommentBean> list = feedbackService.GetComments(0, 10);
//            request.setAttribute("listComments", list);
//            request.getRequestDispatcher("AdminComment.jsp").forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("/");
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);

        if (isAuthenticated) {
            try {
                String username = (String) currentSession.getAttribute("authenticatedUser");

                //-------------get list Comments-----------------
                FeedbackService feedbackService = new FeedbackService();
                ArrayList<FeedbackBean> list = feedbackService.GetAllFeedback(0, 10);
                request.setAttribute("listFeedback", list);

                UserService userService = new UserService();
                if (userService.IsAdmin(username)) {
                    request.getRequestDispatcher("AdminFeedback.jsp").forward(request, response);
                } else response.sendRedirect("/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else response.sendRedirect("/login");
    }
}
