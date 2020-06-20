package com.travel.enumerize;

public enum OrderStatus {
    NEW(0),
    CONFIRMED(1),
    COMPLETED(2);

    private final int orderStatus;
    OrderStatus(int value) { this.orderStatus = value; }

    public int getValue() {
        return orderStatus;
    }
}
