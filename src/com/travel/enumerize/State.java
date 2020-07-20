package com.travel.enumerize;

public enum State {
    NEW(0),
    READ(1);

    private final int state;
    State(int value) {
        this.state = value;
    }

    public int getValue() {
        return state;
    }
}
