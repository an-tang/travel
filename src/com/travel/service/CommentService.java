package com.travel.service;

import com.travel.bean.CommentBean;
import com.travel.dao.CommentDAO;
import com.travel.enumerize.Status;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommentService {
    CommentDAO commentDAO = null;

    public CommentService() throws Exception {
        commentDAO = new CommentDAO();
    }

    public boolean CreateComment(CommentBean comment) throws SQLException {
        if (comment == null){
            return false;
        }
        if (comment.getUserName() == null || comment.getContent() ==null || comment.getTourInfoID() == 0){
            return false;
        }

        return commentDAO.CreateComment(comment);
    }

    public ArrayList<CommentBean> GetCommentsByTourInfoID(int tourInfoID, int start, int size) {
        return commentDAO.GetCommentsByTourInfoID(tourInfoID, start, size);
    }

    public ArrayList<CommentBean> GetComments(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;

        return commentDAO.GetAllComments(page, perPage);
    }

    public boolean DeactivateComment(int commentID) {
        return commentDAO.UpdateCommentStatus(commentID, Status.DEACTIVE);
    }

    public boolean ActiveComment(int commentID){
        return commentDAO.UpdateCommentStatus(commentID, Status.ACTIVE);
    }
}
