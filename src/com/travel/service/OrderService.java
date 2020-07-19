package com.travel.service;

import com.travel.bean.OrderBean;
import com.travel.bean.TourInfoBean;
import com.travel.dao.OrderDAO;
import com.travel.enumerize.OrderStatus;
import com.travel.enumerize.PaymentStatus;
import com.travel.viewmodel.Checkout;
import com.travel.viewmodel.OrderHistory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderService {
    OrderDAO orderDAO = null;
    public OrderService() throws Exception {
        orderDAO = new OrderDAO();
    }

    public int CreateOrder(OrderBean orderBean) throws SQLException {
        if (orderBean == null) {
            return 0;
        }
        if (orderBean.getUsername() == null || orderBean.getPhone() == null || orderBean.getPassenger() <= 0 ||
                orderBean.getTourID() <= 0 || orderBean.getAddress() == null) {
            return 0;
        }
        return orderDAO.CreateOrder(orderBean);
    }

    public ArrayList<OrderBean> GetAllOrders(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;

        return orderDAO.GetAllOrders(page, perPage);
    }

    public ArrayList<OrderBean> GetOrdersByUserName(String userName) {
        return orderDAO.GetOrderByUserName(userName);
    }

    public ArrayList<OrderHistory> GetOrderHistoryByUserName(String userName) {
        return orderDAO.GetOrderHistoryByUserName(userName);
    }

//    public boolean ApprovedOrder(int orderID) {
//        return orderDAO.UpdateOrder(orderID, OrderStatus.CONFIRMED);
//    }

    public boolean CancelOrder(int orderID) {
        return orderDAO.UpdateOrder(orderID, OrderStatus.CANCELED);
    }

    public boolean CompletedOrder(int orderID) {
        return orderDAO.UpdateOrder(orderID, OrderStatus.COMPLETED);
    }

    public void Callback(int orderID, int status) {
        OrderBean loadOrder = orderDAO.GetOrderByID(orderID);
        if (loadOrder == null){
            System.out.println("Cannot found order" + orderID);
            return;
        }

        OrderStatus orderStatus = status == PaymentStatus.PAID.getValue() ? OrderStatus.PAID : OrderStatus.FAILED;
        orderDAO.UpdateOrder(orderID, orderStatus);
    }

    public OrderBean GetOrderByID(int orderID){
        return orderDAO.GetOrderByID(orderID);
    }

    public Checkout RequestPayment(TourInfoBean tourInfo, OrderBean orderBean){
        HttpURLConnection connection = null;
        Checkout checkout = null;
        int orderID = 0;
        try {
            orderID = orderDAO.CreateOrder(orderBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (orderID == 0){
                return null;
            }
            System.out.println(String.valueOf(orderID));
            //Create connection
            URL url = new URL("https://630efc75676c.ngrok.io/v1/payments");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            JSONObject request = new JSONObject();
            request.put("transaction_id", String.valueOf(orderID));
            request.put("tour_name", tourInfo.getTitle());
            request.put("amount", tourInfo.getPrice());

            //Send request
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            writer.write(request.toJSONString());
            writer.flush();
            writer.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject result = (JSONObject)jsonParser.parse(response.toString());

            checkout = new Checkout(
                    result.get("qr_text").toString(),
                    tourInfo.getTourID(),
                    tourInfo.getPrice(),
                    tourInfo.getTitle(),
                    PaymentStatus.NEW.getValue()
            );
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return checkout;
    }
}
