package com.travel.repository;

import com.travel.dbconnection.DBConnection;
import com.travel.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository extends BaseRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public UserRepository() throws Exception {
        super();
    }

    public void CreateUser(UserModel user) throws SQLException {
        try {
            connection = DBConnection.getConnect();
            String sql = "INSERT INTO USERS (user_name, password, name, email, phone, status, created_at, updated_at) VALUES "
                    + " (? , ?, ?, ?, ?, ?, now(), now());";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setInt(6, user.getStatus());

            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
