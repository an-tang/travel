package com.travel.servlet;

import com.travel.ajax.AjaxResponse;
import com.travel.ajax.CheckoutResponse;
import com.travel.bean.OrderBean;
import com.travel.bean.TourBean;
import com.travel.bean.TourInfoBean;
import com.travel.bean.UserBean;
import com.travel.enumerize.OrderStatus;
import com.travel.enumerize.PaymentMethod;
import com.travel.helper.CookieHelpers;
import com.travel.helper.SessionHelpers;
import com.travel.helper.TokenHelpers;
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
        try {
            HttpSession currentSession = request.getSession(false);
            boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
            if (isAuthenticated) {
                Cookie cookie = CookieHelpers.getExistingCookie(request, "checkoutTourId");
                if (cookie != null) {
                    UserService userService = new UserService();
                    TourService tourService = new TourService();
                    TourInfoService tourInfoService = new TourInfoService();

                    String username = (String) currentSession.getAttribute("authenticatedUser");
                    int checkoutTourId = Integer.parseInt(cookie.getValue());

                    UserBean customer = userService.GetUserByUserName(username);
                    TourBean tour = tourService.GetTourByID(checkoutTourId);
                    TourInfoBean tourInfo = tourInfoService.GetTourInfoByTourID(checkoutTourId);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResponse ajaxResponse = new AjaxResponse();

        try {
            HttpSession currentSession = request.getSession(false);
            boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
            if (isAuthenticated) {
                // Init services
                OrderService orderService = new OrderService();
                TourInfoService tourInfoService = new TourInfoService();

                // Get form fields
                String customerPhone = request.getParameter("customer_phone");
                String customerAddress = request.getParameter("customer_address");
                String customerDescription = request.getParameter("customer_description");
                int paymentMethod = Integer.parseInt(request.getParameter("payment"));
                int passengers = Integer.parseInt(request.getParameter("qty"));

                // Get authenticate username and tour ID from session
                String username = (String) currentSession.getAttribute("authenticatedUser");
                int orderTourId = Integer.parseInt((String) currentSession.getAttribute("orderTourId"));

                // Prepare new order
                OrderBean order = new OrderBean(
                        username,
                        customerPhone,
                        customerAddress,
                        passengers,
                        customerDescription,
                        orderTourId
                );
                order.setStatus(OrderStatus.NEW.getValue());

                // Handle payment cases
                if (paymentMethod == PaymentMethod.MOMO.getValue()) {
                    TourInfoBean tourInfo = tourInfoService.GetTourInfoByTourID(orderTourId);
                    Checkout checkout = orderService.RequestPayment(tourInfo, order);
                    ajaxResponse = new CheckoutResponse(
                            true,
                            "Giao dịch thành công",
                            checkout.getQrText(),
                            checkout
                    );
                } else if (paymentMethod == PaymentMethod.BANK_TRANSFER.getValue()) {
                    int orderId = orderService.CreateOrder(order);

                    // Generate one-time token
                    String token = TokenHelpers.generateToken();
                    currentSession.setAttribute("orderConfirmationToken", token);
                    ajaxResponse = new AjaxResponse(
                            true,
                            "Giao dịch thành công",
                            URLHelpers.buildUrlQuery(
                                    "/orderconfirmation",
                                    "order", String.valueOf(orderId),
                                    "t", token
                            )
                    );
                }
            } else {
                ajaxResponse = new AjaxResponse(
                        false,
                        "Phiên đăng nhập hết hạn. Vui lòng đăng nhập lại.",
                        URLHelpers.buildUrlQuery("/login", "redirect", "checkout")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            ajaxResponse = new AjaxResponse(
                    false,
                    "Exception thrown on our side",
                    null
            );
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ajaxResponse.toJSONString());
    }
}
