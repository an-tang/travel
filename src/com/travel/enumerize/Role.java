package com.travel.enumerize;

public enum Role {
    ADMIN(1),
    CUSTOMER(2);

    private final int role;
    Role(int value) {
        this.role = value;
    }

    public int getValue() {
        return role;
    }

}
