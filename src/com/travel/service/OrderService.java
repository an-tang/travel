package com.travel.service;

import com.travel.bean.OrderBean;
import com.travel.dao.OrderDAO;
import com.travel.enumerize.OrderStatus;
import com.travel.response.OrderHistory;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderService {
    OrderDAO orderDAO = null;

    public OrderService() throws Exception {
        orderDAO = new OrderDAO();
    }

    public boolean CreateOrder(OrderBean orderBean) throws SQLException {
        if (orderBean == null) {
            return false;
        }
        if (orderBean.getUsername() == null || orderBean.getPhone() == null || orderBean.getPassenger() <= 0 ||
                orderBean.getTourID() <= 0 || orderBean.getAddress() == null) {
            return false;
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

    public OrderHistory GetOrderHistoryByUserName(String userName) {
        return orderDAO.GetOrderHistoryByUserName(userName);
    }

    public boolean ApprovedOrder(int orderID){
        return orderDAO.UpdateOrder(orderID, OrderStatus.CONFIRMED);
    }

    public boolean RejectedOrder(int orderID){
        return orderDAO.UpdateOrder(orderID, OrderStatus.REJECTED);
    }

    public boolean CompletedOrder(int orderID){
        return orderDAO.UpdateOrder(orderID, OrderStatus.COMPLETED);
    }
}
