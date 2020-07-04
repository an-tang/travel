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
        String idParam = request.getParameter("id");
        int tourId = Integer.parseInt(idParam);
        TourInfoBean tourInfo = null;
        String loginRedirectURL = null;

        try {
            TourInfoService tourInfoService = new TourInfoService();
            tourInfo = tourInfoService.GetTourInfoByTourID(tourId);
            loginRedirectURL = URLHelpers.buildUrlQuery("/login", "redirect", "tour", "id", idParam);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("tourInfo", tourInfo);
        if (loginRedirectURL != null) {
            request.setAttribute("loginReplacementURL", loginRedirectURL);
        }

        request.getRequestDispatcher("tourDetail.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResponse ajaxResponse;
        boolean isAuthenticated = SessionHelpers.validateSession(request);
        if (isAuthenticated) {
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
                // This cookie is session-lasting
                response.addCookie(CookieHelpers.createCookie(cookieName, checkoutTourId, -1));
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ajaxResponse.toJSONString());
    }
}
