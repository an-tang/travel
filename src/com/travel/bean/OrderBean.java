package com.travel.bean;

import java.io.Serializable;

public class OrderBean implements Serializable {
    int id;
    String username;
    String phone;
    String address;
    int userID;
    int passenger;
    String description;
    int status;
    int tourID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public OrderBean(int id, String username, int tourID, String phone, String address, int userID, int passenger, String description, int status) {
        this.id = id;
        this.username = username;
        this.tourID = tourID;
        this.phone = phone;
        this.address = address;
        this.userID = userID;
        this.passenger = passenger;
        this.description = description;
        this.status = status;
    }

    public OrderBean(String username, int tourID, String phone, String address, int userID, int passenger, String description, int status) {
        this.username = username;
        this.tourID = tourID;
        this.phone = phone;
        this.address = address;
        this.userID = userID;
        this.passenger = passenger;
        this.description = description;
        this.status = status;
    }

    public OrderBean(String username, String phone, String address, int passenger, String description, int tourID) {
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.passenger = passenger;
        this.description = description;
        this.tourID = tourID;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userID=" + userID +
                ", passenger=" + passenger +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", tourID=" + tourID +
                '}';
    }
}
