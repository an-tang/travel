package com.travel.service;

import com.travel.bean.OrderBean;
import com.travel.dao.OrderDAO;
import com.travel.response.OrderHistory;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderService {
    OrderDAO orderDAO = null;

    public OrderService() throws Exception {
        orderDAO = new OrderDAO();
    }

    public boolean CreateOrder(OrderBean orderBean) throws SQLException {
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
}
