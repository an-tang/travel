<%@ page import="com.travel.bean.TourInfoBean" %>
<%@ page import="com.travel.bean.UserBean" %>
<%@ page import="com.travel.bean.TourBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserBean customer = (UserBean) request.getAttribute("customer");
    TourBean checkoutTour = (TourBean) request.getAttribute("checkoutTour");
    TourInfoBean checkoutTourInfo = (TourInfoBean) request.getAttribute("checkoutTourInfo");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/checkout.css">
    <title>Thanh toán</title>
</head>

<body>
<div class="super_container">
    <!-- Header -->
    <jsp:include page="components/header.jsp" />

    <!-- Menu -->
    <jsp:include page="components/menu.jsp" />

    <!-- Checkout Section -->
    <section class="checkout-page">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h2 class="mb-4">Đặt tour</h2>
                </div>
            </div>
            <form
                    class="checkout row"
                    method="post"
                    action="/checkout"
                    data-request-error="Request to server unexpectedly failed. Please try again later.">
                <div class="col-md-7">
                    <div class="card checkout-card mb-4">
                        <div class="card-header">
                            <div class="d-flex flex-row align-items-center">
                                <h5 class="card-header-title flex-grow-1">Thông tin khách hàng</h5>
                                <a href="#" class="card-header-action">Chỉnh sửa</a>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="delivery-info">
                                <div class="card-entry">
                                    <h6 class="card-title">Tên</h6>
                                    <p class="card-text"><%=customer.getName()%></p>
                                </div>
                                <div class="card-entry">
                                    <h6 class="card-title">Số điện thoại</h6>
                                    <p class="card-text"><%=customer.getPhone()%></p>
                                </div>
                                <div class="card-entry">
                                    <h6 class="card-title">Email</h6>
                                    <p class="card-text"><%=customer.getEmail()%></p>
                                </div>
                                <div class="card-entry form-group mb-0">
                                    <h6 class="card-title mb-3">Ghi chú</h6>
                                    <textarea
                                            name="description"
                                            class="form-control"
                                            placeholder="Nhập ghi chú (nếu có)"
                                            maxlength="150"
                                            data-range-error="Tối đa 150 ký tự"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card checkout-card">
                        <div class="card-header">
                            <h5 class="card-header-title">Thông tin thanh toán</h5>
                        </div>
                        <div class="card-body">
                            <div class="payment-methods">
                                <div class="card-entry">
                                    <h6 class="card-title">Thời gian liên hệ</h6>
                                    <p class="card-text">
                                        <em>Chúng tôi sẽ liên hệ để xác nhận với bạn trong vòng 12-24 giờ từ khi đơn hàng được tạo thành công.</em>
                                    </p>
                                </div>
                                <div class="card-entry mb-0">
                                    <h6 class="card-title">Hình thức thanh toán</h6>
                                    <div class="form-check mt-2">
                                        <input class="form-check-input" type="radio" name="payment"
                                               id="momoWallet" value="0" checked>
                                        <label class="form-check-label" for="momoWallet">
                                            Thanh toán qua ví điện tử MoMo
                                        </label>
                                    </div>
                                    <div class="form-check mt-2">
                                        <input class="form-check-input" type="radio" name="payment"
                                               id="bankTransfer" value="1">
                                        <label class="form-check-label" for="bankTransfer">
                                            Chuyển khoản ngân hàng
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="card checkout-card">
                        <div class="card-header">
                            <h5 class="card-header-title">Đơn hàng của bạn</h5>
                        </div>
                        <div class="card-body">
                            <div class="order-item row">
                                <div class="col-5 pr-0">
                                    <img src="<%=checkoutTour.getImage()%>" alt="">
                                </div>
                                <div class="col-7">
                                    <c:url var="tourUrl" value="/tour">
                                        <c:param name="id" value="${checkoutTourInfo.getTourID()}"/>
                                    </c:url>
                                    <a href="${tourUrl}" class="order-product-link"><%=checkoutTourInfo.getTitle()%></a>
                                    <p class="card-text price-small"
                                       data-price="<%=checkoutTourInfo.getPrice()%>"><%=String.format("%,d", checkoutTourInfo.getPrice())%></p>
                                    <div class="form-group mb-0">
                                        <label class="form-control-label mr-2" for="quantity">Số người:</label>
                                        <input id="quantity"
                                               class="text-center"
                                               name="qty" type="number"
                                               value="1"
                                               min="1" max="10"
                                               required data-missing-error="Vui lòng nhập số lượng hành khách">
                                        <div class="invalid-msg"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body" style="border-top: 1px solid #dfdfdf;">
                            <div class="cart-summary">
                                <div class="d-flex mb-2">
                                    <p class="card-text cart-summary-entry">Tạm tính:</p>
                                    <p class="card-text price-subtotal flex-grow-1 text-right"
                                       data-price="<%=checkoutTourInfo.getPrice()%>"><%=String.format("%,d", checkoutTourInfo.getPrice())%></p>
                                </div>
                                <div class="d-flex card-item-divider pb-3 mb-3">
                                    <p class="card-text cart-summary-entry">Giảm giá:</p>
                                    <p class="card-text price-subtotal flex-grow-1 text-right">0đ</p>
                                </div>
                                <div class="d-flex">
                                    <p class="card-text cart-summary-entry">Thành tiền:</p>
                                    <p class="card-text price total flex-grow-1 text-right"
                                       data-price="<%=checkoutTourInfo.getPrice()%>"><%=String.format("%,d", checkoutTourInfo.getPrice())%></p>
                                </div>
                                <p class="vat-included text-right">(Đã bao gồm VAT)</p>
                            </div>
                            <div class="d-flex justify-content-center mt-4">
                                <button type="submit" class="btn btn-danger btn-accent col-6 col-xl-4">Đặt tour</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <!-- Footer -->
    <jsp:include page="components/footer.jsp" />
</div>

<jsp:include page="components/globalScripts.jsp" />
<script src="assets/js/components/clientSideValidation.js"></script>
<script src="assets/js/checkout.js"></script>

</body>

</html>
