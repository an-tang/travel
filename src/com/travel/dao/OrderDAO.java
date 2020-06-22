package com.travel.dao;

import com.travel.bean.OrderBean;
import com.travel.dbconnection.DBConnection;
import com.travel.enumerize.OrderStatus;
import com.travel.enumerize.Status;
import com.travel.response.OrderHistory;
import com.travel.response.TourDetail;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO extends BaseDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public OrderDAO() throws Exception {
        super();
    }

    public boolean CreateOrder(OrderBean order) throws SQLException {
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
            preparedStatement.setInt(6, order.getPassenger());
            preparedStatement.setString(7, order.getDescription());
            preparedStatement.setInt(8, OrderStatus.NEW.getValue());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return true;
    }

    public ArrayList<OrderBean> GetAllOrders(int page, int perPage) {
        ArrayList<OrderBean> listOrders = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM orders ORDER BY status DESC, created_at DESC LIMIT ? OFFSET ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, page * perPage + perPage);
            preparedStatement.setInt(2, page * perPage);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int tourID = rs.getInt("tour_id");
                String userName = rs.getString("user_name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int userID = rs.getInt("user_id");
                int passenger = rs.getInt("passenger");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                listOrders.add(new OrderBean(id, userName, tourID, phone, address, userID, passenger, description, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return listOrders;
    }

    public ArrayList<OrderBean> GetOrderByUserName(String userName) {
        ArrayList<OrderBean> listOrders = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM orders WHERE user_name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int tourID = rs.getInt("tour_id");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int userID = rs.getInt("user_id");
                int passenger = rs.getInt("passenger");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                listOrders.add(new OrderBean(id, userName, tourID, phone, address, userID, passenger, description, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return listOrders;
    }

    public OrderHistory GetOrderHistoryByUserName(String userName) {
        OrderHistory result = null;
        ArrayList<TourDetail> tourDetails = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT t.id, t.name, t.image, ti.price , o.created_at, o.passenger, o.user_name"
                    + " FROM orders o INNER JOIN tours t on o.tour_id = t.id"
                    + " INNER JOIN tour_infos ti ON t.id = ti.tour_id WHERE user_name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                long price = rs.getLong("price");
                Time created_at = rs.getTime("created_at");
                int passenger = rs.getInt("passenger");
                tourDetails.add(new TourDetail(id, name, created_at, image, price, passenger));
            }

            result = new OrderHistory(userName, tourDetails);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean UpdateOrder(int orderID, OrderStatus status){
        try {
            connection = DBConnection.getConnect();
            String sql = "UPDATE users SET status = ?, updated_at = now() WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, status.getValue());
            preparedStatement.setInt(2, orderID);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Approving order failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return true;
    }
}
