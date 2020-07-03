package com.travel.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHelpers {
    public static void invalidateCurrentSession(HttpServletRequest request) {
        HttpSession currentSession = request.getSession(false);
        if (currentSession != null && currentSession.getAttribute("authenticatedUser") != null) {
            currentSession.removeAttribute("authenticatedUser");
            currentSession.invalidate();
        }
    }

    public static boolean validateSession(HttpSession session) {
        if (session != null && session.getAttribute("authenticatedUser") != null) {
            return true;
        }
        return false;
    }

    public static boolean validateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return validateSession(session);
    }
}
