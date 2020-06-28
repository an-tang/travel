package com.travel.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserHelpers {
    public static boolean checkSession(HttpServletRequest request) {
        HttpSession currentSession = request.getSession(false);
        if (currentSession != null) {
            return true;
        }
        return false;
    }
}
