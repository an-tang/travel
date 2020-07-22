package com.travel.enumerize;

public enum PagingSize {
    SEARCH(6),
    PROVINCE_TOURS(6);

    private final int pageSize;
    PagingSize(int value) {
        this.pageSize = value;
    }

    public int getValue() {
        return pageSize;
    }
}
