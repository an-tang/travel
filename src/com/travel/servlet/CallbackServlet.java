package com.travel.servlet;

import com.travel.helper.SessionHelpers;
import com.travel.service.OrderService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/callback"})
public class CallbackServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject result = (JSONObject)jsonParser.parse(jb.toString());
            int orderID = Integer.parseInt(result.get("transaction_id").toString());
            int status = Integer.parseInt(result.get("status").toString());

            OrderService orderService = new OrderService();
            orderService.Callback(orderID, status);
        } catch (ParseException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
