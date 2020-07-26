package com.travel.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;

public class TokenHelpers {
    public static String generateToken(String prefix) {
        SecureRandom random = new SecureRandom();
        long randomLong = Math.abs(random.nextLong());
        String token = prefix != null
                ? prefix + Long.toString(randomLong, 16)
                : Long.toString(randomLong, 16);
        return token;
    }

    public static boolean verifyRequestToken(HttpServletRequest request, String sessionAttrName) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;

        String sessionToken = (String) session.getAttribute(sessionAttrName);
        String clientToken = request.getParameter("t");

        if (sessionToken == null) return false;
        if (clientToken == null) return false;
        return sessionToken.equals(clientToken);
    }
}
