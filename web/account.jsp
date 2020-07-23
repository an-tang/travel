<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <title>Tài khoản</title>
</head>
<body>
    <div class="super_container">
        <!-- Header -->
        <jsp:include page="components/header.jsp" />

        <!-- Menu -->
        <jsp:include page="components/menu.jsp" />

        <section class="account-page py-5">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h2 class="section-title mb-4">Tài khoản</h2>
                    </div>
                </div>
                <div class="justify-content-center account-cards">
                    <div class="row profile-card">
                        <div class="col-3 col-md-2 image-card">
                            <a href="/profile">
                                <i class="sa-icon icon-ico-my-profile"></i>
                            </a>
                        </div>
                        <div class="text-card col-8 col-md-9">
                            <a href="/profile">
                                <h4>Thông tin khách hàng</h4>
                            </a>
                        </div>
                        <div class="arrowed">
                            <a href="/profile">
                                <div class="arrow-1"></div>
                            </a>
                        </div>
                    </div>

                    <div class="row order-card">
                        <div class="col-3 col-md-2 image-card">
                            <a href="/orders">
                                <i class="sa-icon icon-ico-orders"></i>
                            </a>
                        </div>
                        <div class="text-card col-8 col-md-9">
                            <a href="/orders">
                                <h4>Đơn hàng</h4>
                            </a>
                        </div>
                        <div class="arrowed">
                            <a href="/orders">
                                <div class="arrow-1"></div>
                            </a>
                        </div>
                    </div>

                    <div class="row wishlist-card">
                        <div class="col-3 col-md-2 image-card">
                            <a href="/wishlist">
                                <i class="sa-icon icon-ico-address-book"></i>
                            </a>
                        </div>
                        <div class="text-card col-8 col-md-9">
                            <a href="/wishlist">
                                <h4>Tour yêu thích</h4>
                            </a>
                        </div>
                        <div class="arrowed">
                            <a href="/wishlist">
                                <div class="arrow-1"></div>
                            </a>
                        </div>
                    </div>

                    <div class="row wishlist-card">
                        <div class="col-3 col-md-2 image-card">
                            <a href="/profile#changePassword">
                                <i class="sa-icon icon-ico-address-book"></i>
                            </a>
                        </div>
                        <div class="text-card col-8 col-md-9">
                            <a href="/profile#changePassword">
                                <h4>Đổi mật khẩu</h4>
                            </a>
                        </div>
                        <div class="arrowed">
                            <a href="/profile#changePassword">
                                <div class="arrow-1"></div>
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
