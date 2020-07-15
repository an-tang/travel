package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.bean.CommentBean;
import com.travel.helper.SessionHelpers;
import com.travel.helper.URLHelpers;
import com.travel.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAuthenticated = SessionHelpers.validateSession(request);
        if (isAuthenticated) {
            int tourInfoId = Integer.parseInt(request.getParameter("tour_info"));
            int start = request.getParameter("start") != null
                    ? Integer.parseInt(request.getParameter("start"))
                    : 0;
            int size = request.getParameter("size") != null
                    ? Integer.parseInt(request.getParameter("size"))
                    : 10;
            ArrayList<CommentBean> comments = new ArrayList<>();
            String showMoreURL = "";

            try {
                // Get comments on current page
                CommentService commentService = new CommentService();
                comments = commentService.GetCommentsByTourInfoID(tourInfoId, start, size);

                // Calculations for next page
                int nextStart = start + size;
                ArrayList<CommentBean> nextComments = commentService.GetCommentsByTourInfoID(tourInfoId, nextStart, size);
                if (nextComments.size() > 0) {
                    showMoreURL = URLHelpers.buildUrlQuery(
                            "/comment",
                            "tour_info", String.valueOf(tourInfoId),
                            "start", String.valueOf(nextStart),
                            "size", String.valueOf(size)
                    );
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            request.setAttribute("comments", comments);
            request.setAttribute("showMoreURL", showMoreURL);
            request.getRequestDispatcher("components/tourDetail/commentCards.jsp").forward(request, response);
        } else {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResponse ajaxResponse;
        int tourInfoId = Integer.parseInt(request.getParameter("tour_info"));
        boolean isAuthenticated = SessionHelpers.validateSession(request);

        if (isAuthenticated) {
            ajaxResponse = new AjaxResponse(
                    false,
                    "Đăng tải bình luận không thành công",
                    null
            );
            String username = request.getParameter("username");
            String commentContent = request.getParameter("comment_content");
            CommentBean comment = new CommentBean(username, commentContent, tourInfoId, 1);

            try {
                CommentService commentService = new CommentService();
                boolean success = commentService.CreateComment(comment);
                if (success) {
                    ajaxResponse = new AjaxResponse(
                            true,
                            "Đăng tải bình luận thành công",
                            null
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ajaxResponse = new AjaxResponse(
                        false,
                        "Phiên đăng nhập hết hạn. Vui lòng đăng nhập lại.",
                        URLHelpers.buildUrlQuery(
                                "/login",
                                "redirect", "tour",
                                "id", String.valueOf(tourInfoId)
                        )
                );
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();

                ajaxResponse = new AjaxResponse(
                        false,
                        "Exception thrown",
                        null
                );
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ajaxResponse.toJSONString());
    }
}
