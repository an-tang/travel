package com.travel.enumerize;

public enum Status {
    DEACTIVE(0),
    ACTIVE(1);

    private final int userStatus;
    Status(int value) {
        this.userStatus = value;
    }

    public int getValue() {
        return userStatus;
    }
}
