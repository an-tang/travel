package com.travel.enumerize;

public enum Region {
    NORTH(1),
    CENTRAL(2),
    SOUTH(3);

    private final int areaId;
    Region(int value) {
        this.areaId = value;
    }

    public int getValue() {
        return areaId;
    }
}
