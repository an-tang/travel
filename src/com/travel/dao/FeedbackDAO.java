package com.travel.dao;

import com.travel.bean.FeedbackBean;
import com.travel.dbconnection.DBConnection;
import com.travel.enumerize.State;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDAO extends BaseDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public FeedbackDAO() throws Exception {
        super();
    }

    public boolean CreateFeedback(FeedbackBean feedback) throws SQLException {
        try {
            connection = DBConnection.getConnect();
            String sql = "INSERT INTO feedbacks (user_name, email, title, content, status, created_at, updated_at)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, feedback.getUsername());
            preparedStatement.setString(2, feedback.getEmail());
            preparedStatement.setString(3, feedback.getTitle());
            preparedStatement.setString(4, feedback.getContent());
            preparedStatement.setInt(5, State.NEW.getValue());
            preparedStatement.execute();

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating feedback failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return true;
    }

    public ArrayList<FeedbackBean> GetAllFeedback(int page, int perPage) {
        ArrayList<FeedbackBean> feedback = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM feedbacks ORDER BY status DESC, created_at DESC LIMIT ? OFFSET ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, page * perPage + perPage);
            preparedStatement.setInt(2, page * perPage);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("user_name");
                String email = rs.getString("email");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int status = rs.getInt("status");
                feedback.add(new FeedbackBean(id, userName, email, title, content, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return feedback;
    }

    public boolean UpdateReadFeedback(int feedbackID) {
        try {
            connection = DBConnection.getConnect();
            String sql = "UPDATE feedbacks SET status = ?, updated_at = now() WHERE id = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, State.READ.getValue());
            preparedStatement.setInt(2, feedbackID);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update read feedback failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return true;
    }
}
