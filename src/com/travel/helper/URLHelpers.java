package com.travel.helper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URLHelpers {

    public static String buildRelativeURL(String basePath, String... keysAndValues) {
        if (keysAndValues.length == 0) return basePath;

        if (keysAndValues.length % 2 != 0) return null;

        StringBuilder stringBuilder = new StringBuilder(basePath + "?");
        for (int i = 0; i < keysAndValues.length; i += 2) {
            String key = keysAndValues[i];
            String rawValue = keysAndValues[i + 1];

            boolean isKeyValuePairValid = key != null && rawValue != null && !key.equals("") && !rawValue.equals("");
            if (!isKeyValuePairValid) continue;

            String encodedValue = URLEncoder.encode(rawValue, StandardCharsets.UTF_8);
            stringBuilder.append(key).append("=").append(encodedValue);
            if (i < keysAndValues.length - 2) {
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }
}
