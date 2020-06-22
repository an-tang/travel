package com.travel.service;

import com.travel.bean.FeedbackBean;
import com.travel.dao.FeedbackDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackService {
    FeedbackDAO feedbackDAO = null;

    public FeedbackService() throws Exception {
        feedbackDAO = new FeedbackDAO();
    }

    public boolean CreateFeedback(FeedbackBean feedback) throws SQLException {
        if (feedback == null) {
            return false;
        }
        if (feedback.getUsername() == null || feedback.getContent() == null || feedback.getTitle() == null) {
            return false;
        }
        return feedbackDAO.CreateFeedback(feedback);
    }

    public ArrayList<FeedbackBean> GetAllFeedback(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        return feedbackDAO.GetAllFeedback(page, perPage);
    }

    public boolean UpdateReadFeedback(int feedbackID) {
        if (feedbackID == 0) {
            return false;
        }
        return feedbackDAO.UpdateReadFeedback(feedbackID);
    }
}
