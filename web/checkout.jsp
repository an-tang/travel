<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <div class="col-md-7">
                    <div class="card checkout-card mb-4" style="width: auto;">
                        <div class="card-header">
                            <div class="d-flex flex-row align-items-center">
                                <h5 class="card-header-title flex-grow-1">Thông tin nhận hàng</h5>

                                <a href="#" class="card-header-action">
                                    <div class="d-flex flex-row align-items-center">
                                        <ion-icon name="ios-create" class="small-icon mr-1"></ion-icon>
                                        Chỉnh sửa
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="delivery-info d-flex flex-column">
                                <div class=" d-flex flex-row pb-4 card-item-divider">
                                    <div class="flex-grow-1 d-flex flex-column">
                                        <div class="card-entry">
                                            <h6 class="card-title">Tên</h6>
                                            <p class="card-text">Nguyễn Văn A</p>
                                        </div>
                                        <div class="card-entry">
                                            <h6 class="card-title">Số điện thoại</h6>
                                            <p class="card-text">0123456789</p>
                                        </div>
                                        <div class="card-entry mb-0">
                                            <h6 class="card-title">Địa chỉ</h6>
                                            <p class="card-text">Abc</p>
                                        </div>
                                    </div>
                                </div>

                                <form class="pt-4">
                                    <div class="form-group m-0">
                                        <h6 class="card-title mb-2">Ghi chú</h6>
                                        <textarea name="message" class="form-control form-message"
                                                  placeholder="Nhập ghi chú (nếu có)"></textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="card checkout-card mb-4" style="width: auto;">
                        <div class="card-header">
                            <h5 class="card-header-title">Vận chuyển và thanh toán</h5>
                        </div>
                        <div class="card-body">
                            <div class="payment-methods d-flex flex-column">
                                <div class="card-entry">
                                    <h6 class="card-title">Thời gian giao hàng</h6>
                                    <p class="card-text"><em>- Khu vực TP.HCM:</em> Trong ngày hoặc ngày hôm sau</p>
                                    <p class="card-text"><em>- Các tỉnh thành khác:</em> Trong vòng 3 ngày làm việc</p>
                                </div>

                                <div class="card-entry">
                                    <h6 class="card-title">Vận chuyển</h6>
                                    <form>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="deliveryMethod"
                                                   id="deliveryMethod1" value="by?" checked>
                                            <label class="form-check-label" for="deliveryMethod1">
                                                Giao hàng siêu tốc (nửa ngày - chỉ áp dụng cho khu vực
                                                TP.HCM)
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="deliveryMethod"
                                                   id="deliveryMethod2" value="by?">
                                            <label class="form-check-label" for="deliveryMethod2">
                                                Giao hàng tiêu chuẩn (áp dụng cho tất cả)
                                            </label>
                                        </div>
                                    </form>
                                </div>

                                <div class="card-entry mb-0">
                                    <h6 class="card-title">Hình thức thanh toán</h6>
                                    <form>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="payment"
                                                   id="radioCashOnDelivery" value="cod" checked>
                                            <label class="form-check-label" for="radioCashOnDelivery">
                                                Thanh toán khi nhận hàng (COD)
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="payment"
                                                   id="radioPayAtShop" value="shop">
                                            <label class="form-check-label" for="radioPayAtShop">
                                                Thanh toán tại cửa hàng (Giữ hàng)
                                            </label>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="card checkout-order-card" style="width: auto;">
                        <!-- Products -->
                        <div class="card-header">
                            <!-- <h5 class="card-header-title"></h5> -->
                            <div class="d-flex flex-row align-items-center">
                                <h5 class="card-header-title flex-grow-1">Đơn hàng của bạn</h5>
                                <a href="#" class="card-header-action">Quay về Giỏ hàng</a>
                            </div>
                        </div>

                        <div class="card-body">
                            <div class="order-item-container d-flex flex-column">

                                <div class="order-item d-flex flex-row">
                                    <div class="item-image d-flex align-items-start mr-3" style="width: 30%;">
                                        <img src="images/citizen-watch-03.jpg" style="width: 100%;"
                                             alt="Citizen BL8144-89H">
                                    </div>

                                    <div class="flex-grow-1 d-flex flex-column">
                                        <div class="item-details flex-grow-1 d-flex flex-column">
                                            <div class="mb-1">
                                                <a href="#" class="order-product-link">Citizen BL8144-89H</a>
                                            </div>
                                            <p class="card-text price-small mb-1">16,500,000đ</p>
                                            <div>
                                                <p class="card-text" style="display: inline-block;">Số lượng: </p>
                                                <p class="card-text bold" style="display: inline-block;">02</p>
                                            </div>
                                        </div>

                                        <div class="item-price d-flex justify-content-end mt-3">
                                            <p class="card-text price-secondary-small">33,000,000đ</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="item-divider d-flex flex-column">
                                    <div class="pb-3 card-item-divider"></div>
                                    <div class="pt-3"></div>
                                </div>

                                <div class="order-item d-flex flex-row">
                                    <div class="item-image d-flex align-items-start mr-3" style="width: 30%;">
                                        <img src="images/citizen-watch-03.jpg" style="width: 100%;"
                                             alt="Citizen BL8144-89H">
                                    </div>

                                    <div class="flex-grow-1 d-flex flex-column">
                                        <div class="item-details flex-grow-1 d-flex flex-column">
                                            <div class="mb-1">
                                                <a href="#" class="order-product-link">Citizen BL8144-89H</a>
                                            </div>
                                            <p class="card-text price-small mb-1">16,500,000đ</p>
                                            <div>
                                                <p class="card-text" style="display: inline-block;">Số lượng: </p>
                                                <p class="card-text bold" style="display: inline-block;">01</p>
                                            </div>
                                        </div>

                                        <div class="item-price d-flex justify-content-end mt-3">
                                            <p class="card-text price-secondary-small">33,000,000đ</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="order-summary">

                            </div>

                        </div>
                        <div class="card-body" style="border-top: 1px solid #dfdfdf;">
                            <div class="cart-summary d-flex flex-column">
                                <div class="d-flex flex-column">
                                    <div class="d-flex flex-row align-items-center mb-2">
                                        <p class="card-text cart-summary-entry">Tạm tính:</p>
                                        <p
                                                class="card-text price-subtotal flex-grow-1 d-flex justify-content-end">
                                            33,000,000đ
                                        </p>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-2">
                                        <p class="card-text cart-summary-entry">Giảm giá:</p>
                                        <p
                                                class="card-text price-subtotal flex-grow-1 d-flex justify-content-end">
                                            0đ
                                        </p>
                                    </div>

                                    <div class="d-flex flex-row align-items-center">
                                        <p class="card-text cart-summary-entry">Phí vận chuyển:</p>
                                        <p
                                                class="card-text price-subtotal flex-grow-1 d-flex justify-content-end">
                                            50,000đ
                                        </p>
                                    </div>
                                </div>

                                <div class="item-divider d-flex flex-column">
                                    <div class="pb-3 card-item-divider"></div>
                                    <div class="pt-3"></div>
                                </div>

                                <div class="d-flex flex-column">
                                    <div class="d-flex flex-row align-items-center">
                                        <p class="card-text cart-summary-entry">Thành tiền:</p>
                                        <p class="card-text price total flex-grow-1 d-flex justify-content-end">
                                            33,000,000đ
                                        </p>
                                    </div>
                                    <div class="d-flex justify-content-end" style="font-size: 90%;">(Đã bao gồm VAT)
                                    </div>
                                </div>

                            </div>
                            <a href="#" class="btn btn-success full-width mt-4">Đặt hàng</a>
                        </div>
                    </div>
                </div>
            </div>
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
