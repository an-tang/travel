<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/orderConfirmation.css">
    <title>Đặt tour thành công</title>
</head>
<body>
<div class="super_container">
    <!-- Header -->
    <jsp:include page="components/header.jsp" />

    <!-- Menu -->
    <jsp:include page="components/menu.jsp" />

    <section class="section-thank-you">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="d-flex flex-column align-items-center">
                        <img class="img-success d-block" src="assets/images/success.png" alt="order-success">
                        <h1 class="success-heading text-center my-3">Đơn hàng đã được đặt thành công!</h1>
                        <p class="thank-you-text text-center">
                            Cảm ơn bạn vì đã lựa chọn chúng tôi.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="section-suggestions">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="d-flex justify-content-center">
                        <a href="/" class="btn btn-outline-danger px-3 py-2 mr-3" role="button">
                            Tiếp tục mua sắm
                        </a>
                        <a href="/account#orders" class="btn btn-danger btn-accent px-3 py-2" role="button">
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
