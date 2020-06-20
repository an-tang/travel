package com.travel.response;

import java.sql.Time;
import java.util.ArrayList;

public class OrderHistoryResponse {
    int userID;
    String username;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}