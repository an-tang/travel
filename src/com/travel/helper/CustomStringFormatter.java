package com.travel.helper;

public class CustomStringFormatter {
    public static String getFormattedPrice(int integerPrice, String currency) {
        return String.format("%,d", integerPrice) + currency;
    }

    public static String getFormattedPrice(long longPrice, String currency) {
        return String.format("%,d", longPrice) + currency;
    }
}
