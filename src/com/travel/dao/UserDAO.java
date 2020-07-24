package com.travel.dao;

import com.travel.bean.TourBean;
import com.travel.dbconnection.DBConnection;
import com.travel.bean.UserBean;
import com.travel.enumerize.Role;
import com.travel.enumerize.Status;
import com.travel.viewmodel.UserReport;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends BaseDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public UserDAO() throws Exception {
        super();
    }

    public void CreateUser(UserBean user, int role) throws SQLException {
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
                    createUserRole(role, id);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
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

            String sql = "SELECT * FROM USERS WHERE (user_name = ?) AND status = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, Status.ACTIVE.getValue());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int status = rs.getInt("status");
                user = new UserBean(userName, password, name, email, phone, status);
            }
        } catch (SQLException e) {
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
                    + " ORDER BY id ASC LIMIT ? OFFSET ?;";

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
                int status = rs.getInt("status");
                listUsers.add(new UserBean(id, userName, name, email, phone, status));
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return count;
    }

    public boolean UpdateUserStatus(int userID, Status status) {
        try {
            connection = DBConnection.getConnect();
            String sql = "UPDATE users SET status = ?, updated_at = now() WHERE id = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, status.getValue());
            preparedStatement.setInt(2, userID);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update user status failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return true;
    }

    public int GetUserRole(String username) {
        int role = -1;
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT r.id FROM users u INNER JOIN user_roles ur ON u.id = ur.user_id" +
                    " INNER JOIN roles r ON ur.role_id = r.id" +
                    " WHERE user_name = ? LIMIT 1;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                role = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return role;
    }

    public UserBean GetUserByID(int id) {
        UserBean userBean = null;
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM users WHERE id = ? LIMIT 1;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("user_name");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int status = rs.getInt("status");
                userBean = new UserBean(id, userName, password, name, email, phone, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return userBean;
    }

    public ArrayList<UserBean> GetAllUsersHaveSorting(String params, int page, int perPage) {
        ArrayList<UserBean> listUsers = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM users u INNER JOIN user_roles ur ON u.id = ur.user_id "
                    + " WHERE ur.role_id = ? "
                    + params
                    + " LIMIT ? OFFSET ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Role.CUSTOMER.getValue());
            preparedStatement.setInt(2, page * perPage + perPage);
            preparedStatement.setInt(3, page * perPage);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("user_name");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                int status = rs.getInt("status");
                listUsers.add(new UserBean(id, userName, name, email, phone, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return listUsers;
    }

    public boolean UpdatePassword(String userName, String password) {
        try {
            connection = DBConnection.getConnect();
            String sql = "UPDATE users SET password = ? WHERE user_name = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, userName);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update user password failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return true;
    }

    public ArrayList<UserReport> TopUsersByOrder(int limit) {
        ArrayList<UserReport> users = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT u.user_name, COUNT(*) as order, SUM(o.passenger * t.price) as total_amount"
                    + " FROM orders o INNER JOIN users u ON o.user_name = u.user_name"
                    + " INNER JOIN tour_infos t ON o.tour_id = t.tour_id"
                    + " GROUP BY u.user_name ORDER BY total_amount DESC LIMIT ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("user_name");
                int order = rs.getInt("order");
                long totalAmount = rs.getLong("total_amount");
                users.add(new UserReport(userName, order, totalAmount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return users;
    }

    private void createUserRole(int role, int id) throws SQLException {
        String sql = "INSERT INTO user_roles (user_id, role_id, created_at, updated_at) VALUES " + "( ?, ?, now(), now());";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, role);
        preparedStatement.execute();
    }
}
