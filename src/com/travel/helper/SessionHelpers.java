package com.travel.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHelpers {
    public static boolean checkCurrentSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("authenticatedUser") != null;
    }

    public static void invalidateCurrentSession (HttpServletRequest request) {
        HttpSession currentSession = request.getSession(false);
        if (currentSession != null && currentSession.getAttribute("authenticatedUser") != null) {
            currentSession.removeAttribute("authenticatedUser");
            currentSession.invalidate();
        }
    }
}
