<%@ page import="com.travel.bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserBean customer = (UserBean) request.getAttribute("customer");
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

    <section class="order-history py-5">

    </section>

    <!-- Footer -->
    <jsp:include page="components/footer.jsp" />
</div>

<jsp:include page="components/globalScripts.jsp" />
<%--<script src="assets/js/orders.js"></script>--%>
</body>
</html>
