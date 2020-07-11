<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <title>Đặt tour thành công</title>
</head>
<body>
<div class="super_container">
    <!-- Header -->
    <jsp:include page="components/header.jsp" />

    <!-- Menu -->
    <jsp:include page="components/menu.jsp" />

    <section class="section-thank-you py-4">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="d-flex flex-column align-items-center">
                        <img class="img-success" src="assets/images/success.png" alt="order-success">
                        <h1 class="success-heading text-center">Đơn hàng đã được đặt thành công!</h1>
                        <p class="thank-you-text text-center">
                            Cảm ơn bạn vì đã lựa chọn chúng tôi. Hi vọng bạn đã có một buổi mua sắm thú vị.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="section-suggestions py-4">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="d-flex justify-content-center">
                        <a href="/" class="btn btn-success px-3 py-2 mr-3" role="button">
                            Tiếp tục mua sắm
                        </a>
                        <a href="/account#orders" class="btn btn-outline-success px-3 py-2" role="button">
                            Xem đơn hàng
                        </a>
                    </div>
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
