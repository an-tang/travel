package com.travel.service;

import com.sun.mail.smtp.SMTPMessage;
import com.travel.bean.UserBean;
import com.travel.helper.URLHelpers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

public class EmailService {
    private final String sender = "uittravel.cs@gmail.com";
    private final String password = "nsqptbthfftvebcn";

    public void SendEmail(UserBean user, String token) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, password);
                    }
                });

        try {
            SMTPMessage message = new SMTPMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

            String host = System.getenv("BASE_URL");
            if (host == null || host.equals("")) {
                host = "http://localhost:8080";
            }

            String baseURL = host + "/reset-password";
            String endpoint = URLHelpers.buildRelativeURL(baseURL, "t", token);
            message.setSubject("UIT Travel - Reset password");
            message.setText("Dear " + user.getName() + ",\n\nClick the link bellow to reset password:\n" + endpoint + "\n\nRegards!");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}