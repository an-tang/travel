package com.travel.viewmodel;

public class ChartValue {
    String key;
    int value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ChartValue(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ChartValue{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
