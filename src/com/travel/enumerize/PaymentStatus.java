package com.travel.enumerize;

public enum PaymentStatus {
    NEW(1),
    PAID(2),
    FAILED(3);

    private final int paymentStatus;
    PaymentStatus(int value) {
        this.paymentStatus = value;
    }

    public int getValue() {
        return paymentStatus;
    }
}
