package com.travel.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;

public class TokenHelpers {
    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        long longToken = Math.abs(random.nextLong());
        String token = Long.toString(longToken, 16);
        return token;
    }

    public static boolean verifyRequestToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;

        String sessionToken = (String) session.getAttribute("orderConfirmationToken");
        String clientToken = request.getParameter("t");

        if (sessionToken == null) return false;
        if (clientToken == null) return false;
        return sessionToken.equals(clientToken);
    }
}
