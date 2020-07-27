package com.travel.service;

import com.travel.bean.OrderBean;
import com.travel.bean.TourInfoBean;
import com.travel.dao.OrderDAO;
import com.travel.enumerize.OrderStatus;
import com.travel.enumerize.PaymentStatus;
import com.travel.viewmodel.Checkout;
import com.travel.viewmodel.OrderDetail;
import com.travel.viewmodel.OrderReport;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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

    public ArrayList<OrderDetail> GetOrderHistoryByUserName(String userName) {
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

    public ArrayList<OrderDetail> GetOrdersHaveSorting(String fieldName, String sortType, int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        fieldName = ((fieldName == null) || (fieldName == "")) ? "user_name" : fieldName;
        sortType = ((sortType == null) || (sortType == "")) ? "ASC" : sortType;
        String params = "ORDER BY " + fieldName + " " + sortType;
        return orderDAO.GetOrdersHaveSorting(params, page, perPage);
    }

    public void Callback(int orderID, int status) {
        OrderBean loadOrder = orderDAO.GetOrderByID(orderID);
        if (loadOrder == null) {
            System.out.println("Cannot found order" + orderID);
            return;
        }

        OrderStatus orderStatus = status == PaymentStatus.PAID.getValue() ? OrderStatus.PAID : OrderStatus.FAILED;
        orderDAO.UpdateOrder(orderID, orderStatus);
    }

    public OrderBean GetOrderByID(int orderID) {
        return orderDAO.GetOrderByID(orderID);
    }

    public Checkout RequestPayment(TourInfoBean tourInfo, OrderBean orderBean) {
        HttpURLConnection connection = null;
        Checkout checkout = null;
        int orderID = 0;
        try {
            orderID = orderDAO.CreateOrder(orderBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (orderID == 0) {
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
            JSONObject result = (JSONObject) jsonParser.parse(response.toString());

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

    public ArrayList<OrderReport> GetReportOrder(int areaID, int provinceID, String from, String to) {
        if ((from == null) || from == "") {
            from = getFirstDayOfMonth();
        }
        if ((to == null) || to == "") {
            to = getToDay();
        }
        String params = "";
        if (areaID > 0) {
            params += " AND a.id = " + areaID;
        }
        if (provinceID >0){
            params += " AND p.id = " + provinceID;
        }

        return orderDAO.GetReportOrder(params, from, to);
    }

    private String getToDay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    private String getFirstDayOfMonth() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        return year + "-" + month + "-01";
    }
}
