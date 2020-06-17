package com.travel.dao;

import com.travel.dbconnection.DBConnection;
import com.travel.bean.UserBean;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends BaseDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public UserDAO() throws Exception {
        super();
    }

    public void CreateUser(UserBean user, int type) throws SQLException {
        try {
            connection = DBConnection.getConnect();
            String sql = "INSERT INTO USERS (user_name, password, name, email, phone, status, created_at, updated_at) VALUES "
                    + " (? , ?, ?, ?, ?, ?, now(), now());";
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setInt(6, user.getStatus());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    createUserRole(type, id);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
    }

    public UserBean GetUserByUserName(String userName) {
        UserBean user = null;
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM USERS WHERE (user_name = ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int status = rs.getInt("status");
                user = new UserBean(userName, password, name, email, phone, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return user;
    }

    public ArrayList<UserBean> GetAllUsers(int page, int perPage) {
        ArrayList<UserBean> listUsers = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM users "
                    + " where status = 1 ORDER BY id ASC "
                    + " LIMIT ? OFFSET ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, page * perPage + perPage);
            preparedStatement.setInt(2, page * perPage);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("user_name");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                listUsers.add(new UserBean(id, userName, name, email, phone));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return listUsers;
    }

    public int UpdateUser(UserBean user) {
        int count = 0;
        try {
            connection = DBConnection.getConnect();
            String sql = "UPDATE users SET password = ?, name = ?, phone = ?, email = ?, updated_at = NOW()"
                    + " WHERE user_name = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getUserName());
            count = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return count;
    }

    private void createUserRole(int type, int id) throws SQLException {
        String sql = "INSERT INTO user_roles (user_id, role_id, created_at, updated_at) VALUES " + "( ?, ?, now(), now());";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, type);
        preparedStatement.execute();
    }

}
