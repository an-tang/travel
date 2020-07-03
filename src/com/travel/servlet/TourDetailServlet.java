package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.bean.TourInfoBean;
import com.travel.helper.CookieHelpers;
import com.travel.helper.SessionHelpers;
import com.travel.helper.URLHelpers;
import com.travel.service.TourInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/tour"})
public class TourDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tourId = Integer.parseInt(request.getParameter("id"));
        TourInfoBean tourInfo = null;

        try {
            TourInfoService tourInfoService = new TourInfoService();
            tourInfo = tourInfoService.GetTourInfoByTourID(tourId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("tourInfo", tourInfo);
        request.getRequestDispatcher("tourDetail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResponse ajaxResponse;

        if (SessionHelpers.validateSession(request)) {
            ajaxResponse = new AjaxResponse(
                    true,
                    "Proceed to Checkout",
                    "/checkout"
            );
        } else {
            String redirectUrl = "/login";
            try {
                redirectUrl = URLHelpers.buildUrlQuery(redirectUrl, "redirect", "checkout");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            ajaxResponse = new AjaxResponse(
                    false,
                    "Vui lòng đăng nhập trước khi tiến hành thanh toán",
                    redirectUrl
            );
        }

        String checkoutTourId = request.getParameter("checkoutTourId");

        // Create a cookie that stores the tour ID before getting to Checkout
        if (checkoutTourId != null) {
            String cookieName = "checkoutTourId";
            Cookie existingCookie = CookieHelpers.getExistingCookie(request, cookieName);
            if (existingCookie != null) {
                existingCookie.setValue(checkoutTourId);
                response.addCookie(existingCookie);
            } else {
                response.addCookie(CookieHelpers.createCookie(cookieName, checkoutTourId, 5 * 60));
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ajaxResponse.toJSONString());
    }
}
