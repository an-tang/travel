package com.travel.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieHelpers {
    public static Cookie createCookie(String name, String value, int age) {
        if (name == null) return null;

        if (name.equals("")) return null;

        if (value == null) return null;

        Cookie cookie = new Cookie(name, value);
        if (age >= -1) {
            cookie.setMaxAge(age);
        }
        return cookie;
    }

    public static Cookie getExistingCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
