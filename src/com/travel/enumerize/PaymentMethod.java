package com.travel.enumerize;

public enum PaymentMethod {
    MOMO(0),
    BANK_TRANSFER(1);

    private final int paymentMethod;

    PaymentMethod(int value) {
        this.paymentMethod = value;
    }

    public int getValue() {
        return paymentMethod;
    }
}
