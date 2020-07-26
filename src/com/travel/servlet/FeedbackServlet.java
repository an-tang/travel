package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.bean.FeedbackBean;
import com.travel.enumerize.Status;
import com.travel.service.FeedbackService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/feedback"})
public class FeedbackServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResponse ajaxResponse;
        try {
            String sender = request.getParameter("sender");
            String email = request.getParameter("email");
            String title = request.getParameter("feedback_title");
            String content = request.getParameter("feedback_content");

            FeedbackService feedbackService = new FeedbackService();
            feedbackService.CreateFeedback(new FeedbackBean(sender, email, title, content, Status.ACTIVE.getValue()));
            ajaxResponse = new AjaxResponse(
                    true,
                    "Chúng tôi đã tiếp nhận phản hồi của bạn. Cảm ơn bạn đã đóng góp ý kiến để giúp chúng tôi có thể nâng cao trải nghiệm cho dịch vụ.",
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResponse = new AjaxResponse(
                    false,
                    "Exception thrown on our side",
                    null
            );
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ajaxResponse.toJSONString());
    }
}
