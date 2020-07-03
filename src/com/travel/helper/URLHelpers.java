package com.travel.helper;

public class URLHelpers {

    public static String buildUrlQuery(String basePath, String... keysAndValues) throws Exception {
        if (keysAndValues.length == 0) return basePath;

        if (keysAndValues.length % 2 != 0)
            throw new Exception("Keys and values are not fully paired");

        StringBuilder stringBuilder = new StringBuilder(basePath + "?");
        for (int i = 0; i < keysAndValues.length; i += 2) {
            String key = keysAndValues[i];
            String value = keysAndValues[i + 1];
            boolean keyValuePairValid = key != null && value != null && !key.equals("") && !value.equals("");

            if (keyValuePairValid) {
                stringBuilder.append(key).append("=").append(value);
            }
            if (i < keysAndValues.length - 2) {
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }
}
