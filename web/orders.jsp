<%@ page import="com.travel.helper.CustomStringFormatter" %>
<%@ page import="com.travel.enumerize.PaymentStatus" %>
<%@ page import="com.travel.viewmodel.OrderDetail" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<OrderDetail> orders = (ArrayList<OrderDetail>) request.getAttribute("orders");
    PaymentStatus.FAILED.getValue();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/orders.css">
    <title>Đơn hàng</title>
</head>
<body>
<div class="super_container">
    <!-- Header -->
    <jsp:include page="components/header.jsp" />

    <!-- Menu -->
    <jsp:include page="components/menu.jsp" />

    <section class="order-history">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h2 id="customerProfile">Đơn hàng của bạn</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <c:choose>
                        <c:when test="${orders.size() > 0}">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table id="customerOrderTable" class="table">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Tên tour</th>
                                                <th scope="col">Đơn giá</th>
                                                <th scope="col">Số hành khách</th>
                                                <th scope="col">Giá trị</th>
                                                <th scope="col">Hình thức thanh toán</th>
                                                <th scope="col">Trạng thái</th>
                                                <th scope="col">Thời gian</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${orders}" var="order" varStatus="status">
                                                <c:choose>
                                                    <c:when test="${order.getStatus() == PaymentStatus.NEW.getValue()}">
                                                        <c:set var="paymentStatus" value="Chưa thanh toán" scope="page"></c:set>
                                                    </c:when>
                                                    <c:when test="${order.getStatus() == PaymentStatus.PAID.getValue()}">
                                                        <c:set var="paymentStatus" value="Đã thanh toán" scope="page"></c:set>
                                                    </c:when>
                                                    <c:when test="${order.getStatus() == PaymentStatus.FAILED.getValue()}">
                                                        <c:set var="paymentStatus" value="Thất bại" scope="page"></c:set>
                                                    </c:when>
                                                </c:choose>
                                                <tr>
                                                    <th scope="row">${status.index + 1}</th>
                                                    <td>${order.getName()}</td>
                                                    <td>${CustomStringFormatter.getFormattedPrice(order.getPrice(), "đ")}</td>
                                                    <td>${order.getPassenger()}</td>
                                                    <td>${CustomStringFormatter.getFormattedPrice(order.getTotalAmount(), "đ")}</td>
                                                    <td>${order.getPaymentMethod()}</td>
                                                    <td>${paymentStatus}</td>
                                                    <td>${order.getCreated_at()}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h5 class="text-center">Chưa có đơn hàng nào.</h5>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <jsp:include page="components/footer.jsp" />
</div>

<jsp:include page="components/globalScripts.jsp" />
</body>
</html>
