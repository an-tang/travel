package com.travel.dao;

import com.travel.bean.OrderBean;
import com.travel.dbconnection.DBConnection;
import com.travel.enumerize.OrderStatus;
import com.travel.response.OrderHistoryResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO extends BaseDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public OrderDAO() throws Exception {
        super();
    }

    public void CreateOrder(OrderBean order) throws SQLException{
        try {
            connection = DBConnection.getConnect();
            String sql = "INSERT INTO orders (user_name, tour_id, phone, address, user_id, passenger, description, status, created_at, updated_at) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, order.getUsername());
            preparedStatement.setInt(2, order.getTourID());
            preparedStatement.setString(3, order.getPhone());
            preparedStatement.setString(4, order.getAddress());
            preparedStatement.setInt(5, order.getUserID());
            preparedStatement.setInt(6,order.getPassenger());
            preparedStatement.setString(7, order.getDescription());
            preparedStatement.setInt(8, OrderStatus.NEW.getValue());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
    }
}
