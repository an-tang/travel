package com.travel.dao;

import com.travel.bean.OrderBean;
import com.travel.dbconnection.DBConnection;
import com.travel.enumerize.OrderStatus;
import com.travel.viewmodel.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class OrderDAO extends BaseDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public OrderDAO() throws Exception {
        super();
    }

    public int CreateOrder(OrderBean order) throws SQLException {
        int id = 0;
        try {
            connection = DBConnection.getConnect();
            String sql = "INSERT INTO orders (user_name, tour_id, phone, address, passenger, description, status, payment_id, created_at, updated_at) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, now(), now());";

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getUsername());
            preparedStatement.setInt(2, order.getTourID());
            preparedStatement.setString(3, order.getPhone());
            preparedStatement.setString(4, order.getAddress());
            preparedStatement.setInt(5, order.getPassenger());
            preparedStatement.setString(6, order.getDescription());
            preparedStatement.setInt(7, OrderStatus.NEW.getValue());
            preparedStatement.setInt(8, order.getPaymentMethod().getValue());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating tour failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return id;
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
            String sql = "SELECT * FROM orders WHERE user_name = ?;";

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

    public ArrayList<OrderDetail> GetOrderHistoryByUserName(String userName) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT t.id, t.name, t.image, ti.price , o.created_at, o.passenger, o.user_name, o.status, o.description, p.name as payment_name"
                    + " FROM orders o INNER JOIN tours t ON o.tour_id = t.id INNER JOIN payments p ON o.payment_id = p.id"
                    + " INNER JOIN tour_infos ti ON t.id = ti.tour_id WHERE user_name = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                long price = rs.getLong("price");
                Date date = rs.getDate("created_at");
                Time time = rs.getTime("created_at");
                String created_at = time.toString() + " " + date.toString();
                int status = rs.getInt("status");
                String paymentMethod = rs.getString("payment_name");
                int passenger = rs.getInt("passenger");
                String description = rs.getString("description");
                orderDetails.add(new OrderDetail(id, name, created_at, image, price, passenger, paymentMethod, status, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetails;
    }

    public ArrayList<OrderDetail> GetOrdersHaveSorting(String params, int page, int perPage) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT t.id, t.name, t.image, ti.price , o.created_at, o.passenger, o.user_name, o.status, o.description, p.name as payment_name"
                    + " FROM orders o INNER JOIN tours t ON o.tour_id = t.id INNER JOIN payments p ON o.payment_id = p.id"
                    + " INNER JOIN tour_infos ti ON t.id = ti.tour_id "
                    + params
                    + " LIMIT ? OFFSET ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, page * perPage + perPage);
            preparedStatement.setInt(2, page * perPage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                long price = rs.getLong("price");
                Date date = rs.getDate("created_at");
                Time time = rs.getTime("created_at");
                String created_at = time.toString() + " " + date.toString();
                int status = rs.getInt("status");
                String paymentMethod = rs.getString("payment_name");
                int passenger = rs.getInt("passenger");
                String description = rs.getString("description");
                orderDetails.add(new OrderDetail(id, name, created_at, image, price, passenger, paymentMethod, status, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetails;
    }

    public boolean UpdateOrder(int orderID, OrderStatus status) {
        try {
            connection = DBConnection.getConnect();
            String sql = "UPDATE orders SET status = ?, updated_at = now() WHERE id = ?;";

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

    public OrderBean GetOrderByID(int orderID) {
        OrderBean order = null;
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM orders WHERE id = ? ORDER BY ID ASC LIMIT 1;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderID);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("user_name");
                int tourID = rs.getInt("tour_id");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int userID = rs.getInt("user_id");
                int passenger = rs.getInt("passenger");
                String description = rs.getString("description");
                int status = rs.getInt("status");

                order = new OrderBean(orderID, username, tourID, phone, address, userID, passenger, description, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }
}
