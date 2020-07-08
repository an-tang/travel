package com.travel.enumerize;

public enum OrderStatus {
    NEW(1),
    PAID(2),
    FAILED(3),
    COMPLETED(4),
    CANCELED(5);

    private final int orderStatus;
    OrderStatus(int value) { this.orderStatus = value; }

    public int getValue() {
        return orderStatus;
    }
}
