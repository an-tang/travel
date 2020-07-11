package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.ajax.CheckoutResponse;
import com.travel.bean.OrderBean;
import com.travel.bean.TourBean;
import com.travel.bean.TourInfoBean;
import com.travel.bean.UserBean;
import com.travel.enumerize.OrderStatus;
import com.travel.helper.CookieHelpers;
import com.travel.helper.SessionHelpers;
import com.travel.helper.URLHelpers;
import com.travel.service.OrderService;
import com.travel.service.TourInfoService;
import com.travel.service.TourService;
import com.travel.service.UserService;
import com.travel.viewmodel.Checkout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
        if (isAuthenticated) {
            Cookie cookie = CookieHelpers.getExistingCookie(request, "checkoutTourId");
            if (cookie != null) {
                int checkoutTourId = Integer.parseInt(cookie.getValue());
                String username = (String) currentSession.getAttribute("authenticatedUser");
                UserBean customer = null;
                TourBean tour = null;
                TourInfoBean tourInfo = null;

                try {
                    UserService userService = new UserService();
                    TourService tourService = new TourService();
                    TourInfoService tourInfoService = new TourInfoService();

                    customer = userService.GetUserByUserName(username);
                    tour = tourService.GetTourByID(checkoutTourId);
                    tourInfo = tourInfoService.GetTourInfoByTourID(checkoutTourId);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                currentSession.setAttribute("orderTourId", cookie.getValue());
                request.setAttribute("customer", customer);
                request.setAttribute("checkoutTour", tour);
                request.setAttribute("checkoutTourInfo", tourInfo);
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect("/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResponse ajaxResponse = null;
        HttpSession currentSession = request.getSession(false);
        boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
        if (isAuthenticated) {
            try {
                UserService userService = new UserService();
                OrderService orderService = new OrderService();
                TourInfoService tourInfoService = new TourInfoService();

                String username = (String) currentSession.getAttribute("authenticatedUser");
                int orderTourId = Integer.parseInt((String) currentSession.getAttribute("orderTourId"));
                String description = request.getParameter("description");
                int paymentMethod = Integer.parseInt(request.getParameter("payment"));
                int passengers = Integer.parseInt(request.getParameter("qty"));

                UserBean user = userService.GetUserByUserName(username);
                OrderBean order = new OrderBean(user.getUserName(), user.getPhone(), "", passengers, description, orderTourId);
                order.setStatus(OrderStatus.NEW.getValue());

                if (paymentMethod == 0) {
                    TourInfoBean tourInfo = tourInfoService.GetTourInfoByTourID(orderTourId);
                    Checkout checkout = orderService.RequestPayment(tourInfo, order);

                    ajaxResponse = new CheckoutResponse(
                            true,
                            "OK",
                            checkout.getQrText(),
                            checkout
                    );
                } else {
                    orderService.CreateOrder(order);
                    ajaxResponse = new AjaxResponse(
                            true,
                            "OK",
                            "/orderconfirmation"
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResponse = new AjaxResponse(
                        false,
                        "Transaction not successful",
                        null
                );
            }
        } else {
            response.sendRedirect("/login");
            try {
                ajaxResponse = new AjaxResponse(
                        false,
                        "",
                        URLHelpers.buildUrlQuery("/login", "redirect", "checkout")
                );
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResponse = new AjaxResponse(
                        false,
                        "Exception thrown",
                        null
                );
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println(ajaxResponse.toJSONString());
        response.getWriter().write(ajaxResponse.toJSONString());
    }
}
