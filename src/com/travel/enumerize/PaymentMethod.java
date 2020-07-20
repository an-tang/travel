package com.travel.enumerize;

public enum PaymentMethod {
    MOMO(1),
    BANK_TRANSFER(2);

    private final int paymentMethod;

    PaymentMethod(int value) {
        this.paymentMethod = value;
    }

    public int getValue() {
        return paymentMethod;
    }
}
