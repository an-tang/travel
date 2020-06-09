package com.travel.enumerize;

public enum UserStatus {
    DEACTIVE(0),
    ACTIVE(1);

    private final int userStatus;
    UserStatus(int value) {
        this.userStatus = value;
    }

    public int getValue() {
        return userStatus;
    }
}
