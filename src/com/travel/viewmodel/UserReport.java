package com.travel.viewmodel;

public class UserReport {
    String userName;
    int orders;
    long totalAmount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public UserReport(String userName, int orders, long totalAmount) {
        this.userName = userName;
        this.orders = orders;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "UserReport{" +
                "userName='" + userName + '\'' +
                ", orders=" + orders +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
