package com.travel.viewmodel;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderHistory implements Serializable {
    String username;
    ArrayList<TourDetail> tours;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<TourDetail> getTours() {
        return tours;
    }

    public void setTours(ArrayList<TourDetail> tours) {
        this.tours = tours;
    }

    public OrderHistory(String username, ArrayList<TourDetail> tours) {
        this.username = username;
        this.tours = tours;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "username='" + username + '\'' +
                ", tours=" + tours +
                '}';
    }
}